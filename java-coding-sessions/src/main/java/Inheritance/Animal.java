package Inheritance;

public class Animal {
    private String type;
    private String size;
    private double weight;

    public Animal() {

    }

    public Animal(String type, String size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{type='%s', size='%s', weight='%s'}".formatted(type, size, weight);
    }

    public void move(String speed) {
        System.out.println(this.type + " moves " + speed);
    }

    public void makeNoise() {
        System.out.println(this.type + " makes some kind of noise.");
    }
}
