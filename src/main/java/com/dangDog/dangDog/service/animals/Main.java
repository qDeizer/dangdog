package com.dangDog.dangDog.service.animals;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        while (!game.isWon()){
            game.playAgain();
        }

        System.out.println(game.printWinner());

        if (true){
            return;
        }

        List<Animal> animals = new ArrayList<>();
        Animal[] animalArray = animals.toArray(new Animal[animals.size()]);
        animals.add(new Cat());

        Dog dog = new Dog();
        animals.add(dog);

        /**
         * dog kostur
         * dog baktim
         *
         */

        animals.add(new Cat());
        animals.add(new Dog());
        animals.add(new Dog());

        dog = new Dog();
        animals.add(dog);

        for (int i = 0; i <animalArray.length; i++) {
            Animal animal = animalArray[i];
            animal.walk();
        }

        for (int i = 0; i <animals.size(); i++) {
            Animal animal = animals.get(i);
            animal.walk();
        }

        for (Animal animal : animals) {
            animal.walk();
        }
    }
}
