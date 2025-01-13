package abstract_classes;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog("Wolf", "big", 100);
        //dog.makeNoise();
        //doAnimalStuff(dog);

        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(dog);
        animals.add(new Dog("German Shepherd", "big", 150));
        animals.add(new Fish("Goldfish", "small", 1));
        animals.add(new Fish("Barracuda", "big", 75));
        animals.add(new Fish("Pug", "small", 20));

        for (Animal animal : animals) {
            doAnimalStuff(animal);
        }
    }

    private static void doAnimalStuff(Animal animal) {
        animal.makeNoise();
        animal.move("slow");
    }
}
