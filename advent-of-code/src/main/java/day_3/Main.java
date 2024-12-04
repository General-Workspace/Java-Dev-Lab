package day_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var input = sumOfNumbers("day_3_puzzle_input.txt");
        System.out.println("Sum of all numbers: " + input);

        try {
            var corruptedMemory = new String(Files.readAllBytes(Paths.get("day_3_puzzle_input.txt")));
            var sum = sumEnabledMulOperations(corruptedMemory);
            System.out.println("Sum of all numbers with enabled mul operations: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int sumOfNumbers(String input) {
        String regex = "mul\\((\\d+),(\\d+)\\)";
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    int num1 = Integer.parseInt(matcher.group(1));
                    int num2 = Integer.parseInt(matcher.group(2));
                    sum += num1 * num2;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static int sumEnabledMulOperations(String corruptedMemory) {
        // Regex pattern for do(), don't(), and mul(X,Y)
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\))");
        Matcher matcher = pattern.matcher(corruptedMemory);

        int totalSum = 0;
        boolean mulEnabled = true; // mul instructions are enabled at the start

        // Iterate over all matches
        while (matcher.find()) {
            String fullMatch = matcher.group(1);

            if (fullMatch.equals("do()")) {
                mulEnabled = true;
            } else if (fullMatch.equals("don't()")) {
                mulEnabled = false;
            } else if (matcher.group(2) != null && matcher.group(3) != null) {
                // This is a mul(X,Y) instruction
                if (mulEnabled) {
                    int num1 = Integer.parseInt(matcher.group(2));
                    int num2 = Integer.parseInt(matcher.group(3));
                    totalSum += num1 * num2;
                }
            }
        }

        return totalSum;
    }
}
