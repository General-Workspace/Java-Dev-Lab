package arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SortingArray result = new SortingArray();
        int[] randomArrayDesc = result.getRandomArray(10);
        System.out.println(Arrays.toString(randomArrayDesc));

        int[] randomArrayAsc = result.getRandomArray(10, 500);
        System.out.println(Arrays.toString(randomArrayAsc));
    }
}