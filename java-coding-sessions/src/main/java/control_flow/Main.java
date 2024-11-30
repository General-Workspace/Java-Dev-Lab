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

        // NATO Phonetic Alphabet
        System.out.println("************************************");
        System.out.println("Traditional Switch Challenge (NATO Phonetic Alphabet)");
        var challenge = new TraditionalSwitchChallenge();
        challenge.solution('C');
        challenge.solution('B');
        challenge.solution('h');

        // Day of the week challenge
        System.out.println("************************************");
        System.out.println("Enhanced Switch Challenge (Day of the week.");
        var dayOfWeekChallenge = new EnhancedSwitchChallenge();
        System.out.println(dayOfWeekChallenge.printDayOfWeek(0));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(1));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(2));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(3));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(4));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(5));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(6));
        System.out.println(dayOfWeekChallenge.printDayOfWeek(7));

        // Day of the week challenge using if statement
        System.out.println("************************************");
        System.out.println(dayOfWeekChallenge.printWeekDay(0));
        System.out.println(dayOfWeekChallenge.printWeekDay(1));

    }
}
