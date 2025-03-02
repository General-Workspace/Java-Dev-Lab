package interfaces;

public class Satellite implements  OrbitEarth{
    FlightStages stage = FlightStages.GROUNDED;

    @Override
    public void achieveOrbit() {
       transition("Orbit was achieved");
    }

    @Override
    public void takeoff() {
        transition("Taking Off");
    }

    @Override
    public void land() {
        transition("Landing");
    }

    @Override
    public void fly() {
        achieveOrbit();
        transition("Data Collection while Orbiting");
    }

    public void transition(String description) {
        System.out.println(description);
        stage = transition(stage);
        stage.track();
    }
}
