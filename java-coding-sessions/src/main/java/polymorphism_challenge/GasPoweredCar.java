package polymorphism_challenge;

public class GasPoweredCar extends Car {
    private double avgKmPerLitre;
    private int cylinders = 6;

    public GasPoweredCar(String description) {
        super(description);
    }

    public GasPoweredCar(String description, double avgKmPerLitre, int cylinders) {
        super(description);
        this.avgKmPerLitre = avgKmPerLitre;
        this.cylinders = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.printf("All %d cylinders are firing up in the GasPoweredCar -> startEngine%n", cylinders);
    }

    @Override
    protected void runEngine() {
        System.out.printf("Usage of GasPoweredCar -> runEngine exceeds the average of %.2f km per litre%n", avgKmPerLitre);
    }
}
