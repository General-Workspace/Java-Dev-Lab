package interfaces;

public interface FlightEnabled {
    double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    void takeoff();
   void land();
    void fly();

    default FlightStages transition(FlightStages stage) {
//        System.out.println("transition not implemented on " + getClass().getName());
//        return null;

        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + " to " + nextStage);
        return nextStage;
    }
}
