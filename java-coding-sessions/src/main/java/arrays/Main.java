package arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        SortingArray result = new SortingArray();
//        int[] randomArrayDesc = result.getRandomArray(10);
//        System.out.println(Arrays.toString(randomArrayDesc));
//
//        int[] randomArrayAsc = result.getRandomArray(10, 500);
//        System.out.println(Arrays.toString(randomArrayAsc));

        MinimumElement minimumElement = new MinimumElement();
        int[] numArray = minimumElement.readIntegers();
        System.out.println(Arrays.toString(numArray));

        System.out.println("- ".repeat(30));

        int minimumNumber = minimumElement.findMin2(numArray);
        System.out.println("Minimum Number: " + minimumNumber);

        System.out.println("- ".repeat(30));
        System.out.println("Array before reverse: " + Arrays.toString(numArray));
        minimumElement.reverse2(numArray);

    }
}