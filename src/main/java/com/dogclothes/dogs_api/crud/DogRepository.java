package com.dogclothes.dogs_api.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
//    List<Dog> findByBreed(String breed);

    List<Dog> findByNameContainingIgnoreCase(String name);

}

    

