package while_loops;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadUserInputChallenge {
//    public void readUserInput() {
//            Scanner scanner = new Scanner(System.in);
//            int count = 0;
//            int sum = 0;
//
//            while (count <= 5) {
//                try {
//                    count++;
//                    System.out.println("Enter number #" + count);
//                    int userInput = scanner.nextInt();
//                    sum += userInput;
//                    if (count >= 5) {
//                        System.out.println("Total sum of the " + count + " numbers = " + sum);
//                        break;
//                    }
//                } catch (InputMismatchException e) {
//                    System.out.println("Invalid number.");
//                }
//
//            }
//    }

    public void readUserInput() {
        Scanner scanner = new Scanner(System.in);

        int counter = 1;
        int sum = 0;

        while (counter <= 5) {
            System.out.println("Enter number #" + counter);
            String nextNumber = scanner.nextLine();
            try {
                int number = Integer.parseInt(nextNumber);
                counter++;
                sum += number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number");
            }
        }

        System.out.println("The sum of " + counter + " numbers is: " + sum);
    }
}
