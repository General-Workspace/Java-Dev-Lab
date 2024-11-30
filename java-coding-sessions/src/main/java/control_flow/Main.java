package control_flow;

public class Main {
    public static void main(String[] args) {
        var response = new SwitchStatement();

        // Normal switch statement
        System.out.println("Normal switch statement");
        response.useSwitchStatement("Sunday");

        // Enhanced switch statement
        System.out.println("Enhanced switch statement");
        response.enhancedSwitchStatement("Friday");

    }
}
