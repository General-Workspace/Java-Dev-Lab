package arrays;

import java.util.Arrays;

public class MinimumElement {
    public int[] readIntegers(String list) {
        String[] stringList = list.split(",");

        int[] numbers = new int[stringList.length];
        for (int i = 0; i < stringList.length; ++i) {
            numbers[i] = Integer.parseInt(stringList[i].trim());
        }

//        Arrays.sort(numbers);
        return numbers;
    }

    public int findMin(int[] input) {
        Arrays.sort(input);

        return input[0];
    }
}
