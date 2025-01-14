package abstract_classes;

public class Fish extends Animal{
    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {

        if (speed.equals("slow")) {
            System.out.println(getExplicitType() + " gliding...");
        } else {
            System.out.println(getExplicitType() + " swimming...");
        }
    }

    @Override
    public void makeNoise() {
        if (type.equals("Goldfish")) {
            System.out.println("Swish! ");
        } else {
            System.out.println("Splash! ");
        }
    }
}
