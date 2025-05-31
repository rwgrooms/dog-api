package com.dogclothes.dogs_api.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
        return dogService.getDogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
