package com.dangDog.dangDog.service.animals;

public class Dog implements Animal {

    public Dog() {
        System.out.println("Yeni bir kopek yaratildi!");
    }

    @Override
    public void walk() {
        System.out.println("Dog walk");
    }
}
