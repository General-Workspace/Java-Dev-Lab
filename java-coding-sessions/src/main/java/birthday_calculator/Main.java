package birthday_calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String keyGuide = """
                1. a - Check the day of the week you were born on
                2. b - Check your age in years, months, days, hours, minutes, and seconds
                3. c - Check remaining days until next birthday
                4. d - Check your age before a specific date
                5. e - Check the duration since your last birthday
                6. f - Future date of birthday based on age provided
                7. q - Quit or Exit
                """;
        System.out.println("Welcome to Birthday Checker. What would you like to do?");
        System.out.println(keyGuide);

        // Create a new BirthdayCalculator object
        BirthdayCalculator birthdayCalculator = new BirthdayCalculator();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("q")) {
            switch (input) {
                case "a" -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String dayOfWeek = birthdayCalculator.checkDayOfBirthDay(year, month, day);
                    System.out.printf("You were born on a %s!%n", dayOfWeek);
                }
                case "b" -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String age = birthdayCalculator.ageInYears(year, month, day);
                    System.out.println(age);
                }
                case "c" -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String daysUntilNextBirthday = birthdayCalculator.daysUntilNextBirthday(year, month, day);
                    System.out.println(daysUntilNextBirthday);
                }
                case "d" -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    System.out.println("Enter the year you want to check:");
                    String checkYear = scanner.nextLine();
                    System.out.println("Enter the month you want to check:");
                    String checkMonth = scanner.nextLine();
                    System.out.println("Enter the day you want to check:");
                    String checkDay = scanner.nextLine();
                    String ageBeforeDate = birthdayCalculator.ageBeforeDate(year, month, day, checkYear, checkMonth, checkDay);
                    System.out.println(ageBeforeDate);
                }
                case "e" -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String durationSinceLastBirthday = birthdayCalculator.durationSinceLastBirthday(year, month, day);
                    System.out.println(durationSinceLastBirthday);
                }
                case "f" -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    System.out.println("Enter the age:");
                    String age = scanner.nextLine();
                    String futureDate = birthdayCalculator.futureBirthday(year, month, day, age);
                    System.out.println(futureDate);
                }
                default -> {
                    System.out.println("Invalid input. Please try again.");
                    System.out.println(keyGuide);
                }
            };
            System.out.println("What would you like to do next?");
            System.out.println(keyGuide);
            input = scanner.nextLine();
        }
        System.out.println("Goodbye!");
    }
}
