package abstract_classes;

public class Fish extends Animal{
    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {

        if (speed.equals("slow")) {
            System.out.println(type + " gliding...");
        } else {
            System.out.println(type + " swimming...");
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
