package while_loops;

import java.util.Objects;
import java.util.Scanner;

public class MinMaxChallenge {
    public void minMax() {
        String guide = "start or s to start. 0 or q to quit";
        System.out.println(guide);

        Scanner scanner = new Scanner(System.in);
        String keyword = scanner.nextLine();
        int min = 0, max = 0, counter = 0;

        while (Objects.equals(keyword, "start") || keyword.charAt(0) == 's') {
            System.out.println("Enter your number");
            System.out.println("To quit, enter q or 0");
            String input = scanner.nextLine();
            if (input.charAt(0) == 'q' || Integer.parseInt(input) == 0) {
                System.out.println("Goodbye! Come back soon");
                break;
            }

            try {
                int userInput = Integer.parseInt(input);
                counter++;
                if (counter == 1) {
                    max = userInput;
                    min = userInput;
                }
                else if (userInput > max) {
                    max = userInput;
                } else if (userInput < min) {
                    min = userInput;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number input.");
                break;
            }

        }
        scanner.close();
        System.out.printf("Minimum number: %d%n", min);
        System.out.printf("Maximum number: %d%n", max);
    }

    public void minMax2() {
        Scanner scanner = new Scanner(System.in);
        double min = 0, max = 0;
        int counter = 0;

        while (true) {
            System.out.println("Enter a number to get started or any character to quit");
            String userInput = scanner.nextLine();

            try {
                double convertedInput = Double.parseDouble(userInput);
                counter++;
                if (counter == 1) {
                    min = convertedInput;
                    max = convertedInput;
                } else if (convertedInput < min) {
                    min = convertedInput;
                } else if (convertedInput > max) {
                    max = convertedInput;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Exiting the program.");
                break;
            }
        }
        System.out.printf("Minimum number: %s%n", min);
        System.out.printf("Maximum number: %s%n", max);
    }
}
