package com.dogclothes.dogs_api.crud;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dogId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String breed;

    private double age;

    @Temporal(TemporalType.DATE)
    private Date activeDate;

    @Column(nullable = false)
    private String imgPath;

    // Constructors
    public Dog() {
    }

    public Dog(String name, String description, String breed, double age, Date activeDate, String imgPath) {
        this.name = name;
        this.description = description;
        this.breed = breed;
        this.age = age;
        this.activeDate = activeDate;
        this.imgPath = imgPath;
    }

    // Getters and Setters
    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", activeDate=" + activeDate +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }

}
