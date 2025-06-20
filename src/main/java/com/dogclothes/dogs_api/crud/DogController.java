package com.dogclothes.dogs_api.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HexFormat;

@Controller
@RequestMapping("/api/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping()
    public Object getAllDogs(Model model) {
        model.addAttribute("dogsList", dogService.getAllDogs());
        model.addAttribute("title", "All Dogs");
        return "animal-list";
    }

   @GetMapping("/{id}")
   public Object getDogById(@PathVariable Long id, Model model) {
       Dog dog = dogService.getDogById(id).orElse(null);
       model.addAttribute("dog", dog);
       model.addAttribute("title", "Dog #: " + id);
       return "animal-details";
   }

    // @GetMapping("/breed/{breed}")
    // public List<Dog> getDogsByBreed(@PathVariable String breed) {
    //     return dogService.getDogsByBreed(breed);
    // }

    @GetMapping("/search")
    public String getDogsByNameContains(@RequestParam(required=false) String name, Model model) {

        List<Dog> dogs;
        if (name != null && !name.isBlank()) {
            dogs = dogService.getDogsByNameContains(name);
        } else {
            dogs = dogService.getAllDogs();
        }

        model.addAttribute("dogsList", dogs);
        model.addAttribute("name", name);
        model.addAttribute("title", "Dogs named " + name);
        return "animal-list";

    }  

    @PostMapping
    public Object createDog(@RequestParam("name") String name,
                        @RequestParam("description") String description,
                        @RequestParam("breed") String breed,
                        @RequestParam(value = "age", required = false) Double age,
                        @RequestParam(value = "activeDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate activeDate,
                        @RequestParam("image") MultipartFile image,
                        Model model) {
        try {
            String originalFilename = image.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String hashedFilename = hashFilename(originalFilename) + extension;

            String uploadDir = new File("src/main/resources/public/images/").getAbsolutePath();
            Files.copy(image.getInputStream(), Paths.get(uploadDir, hashedFilename), StandardCopyOption.REPLACE_EXISTING);

            Dog dog = new Dog(name, description, null, 0, null, "/images/" + hashedFilename);
            System.out.println("Dog object to save: " + dog);
            dogService.createDog(dog);
            model.addAttribute("dogsList", dogService.getAllDogs());
            model.addAttribute("title", "All Dogs");
            //The directory used for saving the file does not copy to the target directory immediately so added a delay to avoid an empty picture on redirect.
            //I only kept using that directory since I could not check in a system directory to git (ie: c:\\myimages)  
            Thread.sleep(3000); 

            } catch (Exception e) {
                e.printStackTrace();
        }
        return "animal-list";
    }

    private String hashFilename(String filename) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest((filename + System.nanoTime()).getBytes());
        return HexFormat.of().formatHex(hashBytes);
    }

    @GetMapping("/create-form")
    public String showCreateDogForm(Model model) {
        model.addAttribute("dog", new Dog());
        model.addAttribute("title", "Add a Dog");
        return "animal-create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Dog dog = dogService.getDogById(id).orElse(null);
        if (dog == null) {
            return "redirect:/api/dogs"; // or return a 404 page
        }
        model.addAttribute("dog", dog);
        model.addAttribute("title", "Edit Dog");
        return "animal-update";
    }

    @PostMapping("/edit/{id}")
    public String updateDog(@PathVariable Long id,
                            @RequestParam("name") String name,
                            @RequestParam("description") String description,
                            @RequestParam("breed") String breed,
                            @RequestParam(value = "age", required = false) Double age,
                            @RequestParam(value = "activeDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate activeDate,
                            @RequestParam(value = "existingImagePath", required = false) String existingImagePath,
                            @RequestParam(value = "image", required = false) MultipartFile image,
                            Model model) {
        try {
            Dog existingDog = dogService.getDogById(id).orElse(null);
            if (existingDog == null) {
                return "redirect:/api/dogs"; // Or handle 404 properly
            }

            // Handle image - use new one if uploaded, else keep existing
            String imgPath = existingImagePath;
            if (image != null && !image.isEmpty()) {

                //Start - Image has changed so delete the previous image file
                String uploadDir1 = new File("src/main/resources/public/").getAbsolutePath();
                try {
                    Files.delete(Paths.get(uploadDir1, existingDog.getImgPath()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //End - Image has changed so delete the previous image file

                String originalFilename = image.getOriginalFilename();
                String extension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String hashedFilename = hashFilename(originalFilename) + extension;
                String uploadDir = new File("src/main/resources/public/images/").getAbsolutePath();
                Files.copy(image.getInputStream(), Paths.get(uploadDir, hashedFilename), StandardCopyOption.REPLACE_EXISTING);
                imgPath = "/images/" + hashedFilename;
            }

            existingDog.setName(name);
            existingDog.setDescription(description);
            existingDog.setBreed(breed);
            existingDog.setAge(age != null ? age : 0);

            if (activeDate != null) {
                java.sql.Date sqlDate = java.sql.Date.valueOf(activeDate);
                existingDog.setActiveDate(sqlDate);
            } else {
                existingDog.setActiveDate(null);
            }
            existingDog.setImgPath(imgPath);

            dogService.updateDog(id, existingDog);

            model.addAttribute("dogsList", dogService.getAllDogs());
            model.addAttribute("title", "All Dogs");
            //The directory used for saving the file does not copy to the target directory immediately so added a delay to avoid an empty picture on redirect.
            //I only kept using that directory since I could not check in a system directory to git (ie: c:\\myimages)  
            Thread.sleep(3000); 
            
            return "animal-list";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/api/dogs";
        }
    }


    // @PutMapping("/{id}")
    // public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog dog) {
    //     return dogService.updateDog(id, dog)
    //             .map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    @GetMapping("/delete/{id}")
    public Object deleteDog(@PathVariable Long id, Model model) {
        Dog dog = dogService.getDogById(id).orElse(null);
        String uploadDir = new File("src/main/resources/public/").getAbsolutePath();
        try {
            Files.delete(Paths.get(uploadDir, dog.getImgPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        dogService.deleteDog(id);
        model.addAttribute("dogsList", dogService.getAllDogs());
        model.addAttribute("title", "All Dogs");
        return "animal-list";
    }
}
