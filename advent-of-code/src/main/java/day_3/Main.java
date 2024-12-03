package day_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var input = sumOfNumbers("day_3_puzzle_input.txt");
        System.out.println("Sum of all numbers: " + input);

        var input2 = sumOfNumbers_2("day_3_puzzle_input.txt");
        System.out.println("Sum of all numbers: " + input2);
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

    public static int sumOfNumbers_2(String input) {
        String regex = "mul[\\(\\[]?(\\d+),(\\d+)[\\)\\]]?";
        String regex2 = "do\\(\\)";
        String regex3 = "don't\\(\\)";
        int sum = 0;
        boolean isMulEnabled = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    if (isMulEnabled) {
                        int num1 = Integer.parseInt(matcher.group(1));
                        int num2 = Integer.parseInt(matcher.group(2));
                        sum += num1 * num2;
                    }
                }

                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher2 = pattern2.matcher(line);

                while (matcher2.find()) {
                    isMulEnabled = true;
                }

                Pattern pattern3 = Pattern.compile(regex3);
                Matcher matcher3 = pattern3.matcher(line);

                while (matcher3.find()) {
                    isMulEnabled = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
