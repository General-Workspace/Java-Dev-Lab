package control_flow;

public class ControlFlowChallenge {
    public static void main(String[] args) {
        // Step 1 & 2
        double firstValue = 20.00;
        double secondValue = 80.00;

        // Step 3
        double resultOfFirsAndSecondValues = (firstValue + secondValue) * 100.00;
        System.out.println("Result of Step 3: " + resultOfFirsAndSecondValues);

        double fourthValue = resultOfFirsAndSecondValues % 40.00;
        System.out.println("Result of Step 4: " + fourthValue);

        //Step 5 & 6
        boolean output = fourthValue == 0 ? true : false;
        System.out.println("Step 5: " + output);

        // Step 7
        if (!output)
            System.out.println("Got some remainder");

    }
}
