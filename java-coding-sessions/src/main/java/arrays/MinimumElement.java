package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElement {
    public int[] readIntegers() {
        System.out.println("Enter numbers separated by commas");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] stringList = input.split(", ");

        int[] numbers = new int[stringList.length];

        for (int i = 0; i < stringList.length; ++i) {
            numbers[i] = Integer.parseInt(stringList[i]);
        }

        return numbers;
    }

    public int findMin(int[] input) {
        Arrays.sort(input);

        return input[0];
    }

    public int findMin2(int[] input) {
        int min = input[0];
        for (int num : input) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public void reverse(int[] input) {
        int maxIndex = input.length - 1;
        int mid = input.length / 2;
        int temp;

        for (int i = 0; i < mid; ++i) {
            temp = input[i];
            input[i] = input[maxIndex - i];
            input[maxIndex - i] = temp;
        }

        System.out.println("Array after reverse: " + Arrays.toString(input));
    }

    public void reverse2(int[] input) {
        int[] reversedArray = new int[input.length];
        for (int i = 0; i < input.length; ++i) {
            reversedArray[i] = input[input.length - 1 - i];
        }

        System.out.println("Array after reverse: " + Arrays.toString(reversedArray));
    }
}
