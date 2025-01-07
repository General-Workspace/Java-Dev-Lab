package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        SortingArray result = new SortingArray();
//        int[] randomArrayDesc = result.getRandomArray(10);
//        System.out.println(Arrays.toString(randomArrayDesc));
//
//        int[] randomArrayAsc = result.getRandomArray(10, 500);
//        System.out.println(Arrays.toString(randomArrayAsc));

        MinimumElement minimumElement = new MinimumElement();
        System.out.println("Enter numbers separated by commas");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int[] numArray = minimumElement.readIntegers(input);
        System.out.println(Arrays.toString(numArray));

        System.out.println("- ".repeat(30));

        int minimumNumber = minimumElement.findMin(numArray);
        System.out.println("Minimum Number: " + minimumNumber);

    }
}