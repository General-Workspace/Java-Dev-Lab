package polymorphism_challenge;

public class Main {
    public static void main(String[] args) {

        Car car = new Car("2022 Blue Ferrari 296 GTS");
        runRace(car);

        System.out.println();

        Car gasPoweredCar = new GasPoweredCar("2022 Red Ferrari 296 GTS", 15.4, 6);
        runRace(gasPoweredCar);

        System.out.println();

        Car electricCar = new ElectricCar("2022 White Tesla Model S", 500, 100);
        runRace(electricCar);

        System.out.println();

        Car hybridCar = new HybridCar("2022 Black Toyota Prius", 25.5, 4, 50);
        runRace(hybridCar);
    }

    public static void runRace(Car car) {
        car.startEngine();
        car.drive();
    }
}
