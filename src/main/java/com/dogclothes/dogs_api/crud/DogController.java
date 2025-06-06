package com.dogclothes.dogs_api.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping()
    public Object getAllDogs(Model model) {
        model.addAttribute("dogsList" , dogService.getAllDogs());
        model.addAttribute("title", "All Dogs");
        return "animal-list";
        //return dogService.getAllDogs();
    }

    @GetMapping("/{id}")
    public Object getDogById(@PathVariable Long id, Model model) {
        model.addAttribute("dogsL" , dogService.getDogById(id));
        model.addAttribute("title", "Dog #: " + id);
        return "animal-details";
        //return dogService.getDogById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/breed/{breed}")
    public List<Dog> getDogsByBreed(@PathVariable String breed) {
        return dogService.getDogsByBreed(breed);
    }

    @GetMapping("/search/{name}")
    public List<Dog> getDogsByNameContains(@PathVariable String name) {
        return dogService.getDogsByNameContains(name);
    }  

    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        return dogService.updateDog(id, dog)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        return dogService.deleteDog(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
