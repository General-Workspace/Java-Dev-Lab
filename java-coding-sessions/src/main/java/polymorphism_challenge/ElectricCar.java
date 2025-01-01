package polymorphism_challenge;

public class ElectricCar extends Car {
    private double avgKmPerCharge;
    private int batterySize = 6;

    public ElectricCar(String description) {
        super(description);
    }

    public ElectricCar(String description, double avgKmPerLitre, int cylinders) {
        super(description);
        this.avgKmPerCharge = avgKmPerLitre;
        this.batterySize = cylinders;
    }

    @Override
    public void startEngine() {
        System.out.printf("BEV with %d kWh battery is starting up in the ElectricCar -> startEngine%n", batterySize);
    }

    @Override
    protected void runEngine() {
        System.out.printf("BEV usage of ElectricCar -> runEngine exceeds the average of %.2f km per charge%n", avgKmPerCharge);
    }
}
