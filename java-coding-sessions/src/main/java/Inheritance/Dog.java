package Inheritance;

import java.util.Objects;

public class Dog extends Animal{
    private String earShape;
    private String tailShape;

    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    public Dog(String type, double weight) {
        this(type, weight, "Perky", "Curled");
    }

    public Dog(String type, double weight, String earShape, String tailShape) {
        super(type, weight < 15 ? "small" : (weight < 35 ? "medium" : "large"), weight);
        this.earShape = earShape;
        this.tailShape = tailShape;
    }

    @Override
    public String toString() {
        return "Dog{earShape='%s', tailShape='%s'} %s".formatted(earShape, tailShape, super.toString());
    }

    public void makeNoise() {
        bark();
    }

    @Override
    public void move(String speed) {
        super.move(speed);
        if (Objects.equals(speed, "slow")) {
            walk();
            wagTail();
        } else {
            run();
            bark();
        }
    }

    private void bark() {
        System.out.println("Woof! ");
    }

    private void run() {
        System.out.println("Dog running! ");
    }

    private void walk() {
        System.out.println("Dog walking! ");
    }

    private void wagTail() {
        System.out.println("Dog tail wagging! ");
    }
}
