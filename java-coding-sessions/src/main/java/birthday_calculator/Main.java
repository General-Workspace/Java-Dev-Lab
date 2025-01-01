package birthday_calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String keyGuide = """
                1 - Check the day of the week you were born on
                2 - Check your age in years, months, days, hours, minutes, and seconds
                3 - Check remaining days until next birthday
                4 - Check your age before a specific date
                5 - Check the duration since your last birthday
                6 - Future date of birthday based on age provided
                7 - Years passed since becoming an adult
                8 - Number of years left to attain a specific age
                0 - Quit or Exit
                """;
        System.out.println("Welcome to Years Checker. What would you like to do?");
        System.out.println(keyGuide.trim());

        // Create a new BirthdayCalculator object
        BirthdayCalculator birthdayCalculator = new BirthdayCalculator();

        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());

        while (input != 0) {
            switch (input) {
                case 1 -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String dayOfWeek = birthdayCalculator.checkDayOfBirthDay(year, month, day);
                    System.out.printf("You were born on a %s!%n", dayOfWeek);
                }
                case 2 -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String age = birthdayCalculator.ageInYears(year, month, day);
                    System.out.println(age);
                }
                case 3 -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String daysUntilNextBirthday = birthdayCalculator.daysUntilNextBirthday(year, month, day);
                    System.out.println(daysUntilNextBirthday);
                }
                case 4 -> {
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
                case 5 -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String durationSinceLastBirthday = birthdayCalculator.durationSinceLastBirthday(year, month, day);
                    System.out.println(durationSinceLastBirthday);
                }
                case 6 -> {
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
                case 7 -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    String yearsSinceAdult = birthdayCalculator.yearsSinceBecomingAdult(year, month, day);
                    System.out.println(yearsSinceAdult);
                }
                case 8 -> {
                    System.out.println("Enter your birth year:");
                    String year = scanner.nextLine();
                    System.out.println("Enter your birth month:");
                    String month = scanner.nextLine();
                    System.out.println("Enter your birth day:");
                    String day = scanner.nextLine();
                    System.out.println("Enter the age you want to attain:");
                    String age = scanner.nextLine();
                    String yearsLeftToAttainAge = birthdayCalculator.yearsLeftBeforeReachingCertainAge(year, month, day, age);
                    System.out.println(yearsLeftToAttainAge);
                }
                default -> {
                    System.out.println("Invalid input. Please try again.");
                    System.out.println(keyGuide);
                }
            }
            System.out.println("What would you like to do next?");
            System.out.println(keyGuide.trim());
            input = Integer.parseInt(scanner.nextLine());
        }
        System.out.println("Goodbye!");
    }
}
