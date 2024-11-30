package if_keyword;

import java.util.Scanner;

public class FirstClass {
    public static void main(String[] args) {
        System.out.println("Hello, Aniebiet");

        boolean isAlien = false;
        if (!isAlien)
            System.out.println("It is not an alien!");

        int topScore = 80;
        if (topScore != 100) {
            System.out.println("You got the highest score!");
        }

        int secondTopScore = 60;
        if ((topScore > secondTopScore) && (topScore < 100)) {
            System.out.println("Greater than second top score and less than 100.");
        }

        if ((topScore > 90) || (secondTopScore <= 90)) {
            System.out.println("Either or both of the conditions are true.");
        }

        // Ternary Operator
        int drivingAge = 18;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How old are you?");
        int userAge = scanner.nextInt();
        scanner.close();

        var message = userAge < drivingAge ? "You're not old enough to drive" : "Congratulations! You can drive.";
        System.out.println(message);
    }
}
