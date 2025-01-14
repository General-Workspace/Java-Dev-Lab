package interfaces;

public class Test {
    public static void main(String[] args) {
        inFlight(new Jet());
        //OrbitEarth.log("Testing " + new Satellite());
    }

    private static void inFlight(FlightEnabled flier) {
        flier.takeoff();
        flier.transition(FlightStages.LAUNCH);
        flier.fly();
        if (flier instanceof Trackable) {
            ((Trackable) flier).track();
        }
        flier.land();
    }

    private static void orbit(OrbitEarth flier) {
        flier.takeoff();
        flier.fly();
        flier.land();
    }
}
