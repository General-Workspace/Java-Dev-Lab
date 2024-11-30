package control_flow;

public class Main {
    public static void main(String[] args) {
        var response = new SwitchStatement();

        // Normal switch statement
        System.out.println("Normal switch statement");
        response.useSwitchStatement("Sunday");

        // Enhanced switch statement
        System.out.println("************************************");
        System.out.println("Enhanced switch statement");
        response.enhancedSwitchStatement("Friday");

        // Enhanced switch with return keyword
        System.out.println("************************************");
        System.out.println("Enhanced switch with return keyword");
        String month = "June";
        System.out.println(month + " is in the " + response.enhancedSwitchWithReturn(month) + " quarter of the year");

    }
}
