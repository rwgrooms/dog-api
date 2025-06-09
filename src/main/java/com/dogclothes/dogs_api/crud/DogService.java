package com.dogclothes.dogs_api.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public Optional<Dog> getDogById(Long id) {
        return dogRepository.findById(id);
    }

    // public List<Dog> getDogsByBreed(String breed) {
    //     return dogRepository.findByBreed(breed);
    // }

    public List<Dog> getDogsByNameContains(String name) {
        return dogRepository.findByNameContainingIgnoreCase(name);
    }

    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Optional<Dog> updateDog(Long id, Dog updatedDog) {
        return dogRepository.findById(id).map(dog -> {
            dog.setName(updatedDog.getName());
            dog.setDescription(updatedDog.getDescription());
            dog.setBreed(updatedDog.getBreed());
            dog.setAge(updatedDog.getAge());
            dog.setActiveDate((updatedDog.getActiveDate()));
            dog.setImgPath(updatedDog.getImgPath());
            return dogRepository.save(dog);
        });
    }

    public boolean deleteDog(Long id) {
        return dogRepository.findById(id).map(dog -> {
            dogRepository.delete(dog);
            return true;
        }).orElse(false);
    }
}
