package interfaces;

public record DragonFly(String name, String type) implements FlightEnabled {

    @Override
    public void takeoff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}
