package com.dangDog.dangDog.service.animals;

import java.util.Random;

public class Cat implements Animal {
     int sayyi=12;
    Random rast = new Random();

    public Cat() {
        this.sayyi = sayyi;
    }

    @Override
    public void walk() {
        System.out.println("cat walk");
    }
}