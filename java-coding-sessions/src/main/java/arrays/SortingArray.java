package arrays;

import java.util.Arrays;
import java.util.Random;

public class SortingArray {
    public int[] getRandomArray(int len) {
        Random random = new Random();
        int[] randomArray = new int[len];

        for (int i = 0; i < len; ++i) {
            randomArray[i] = random.nextInt(1000);
        }

        return sortArrayDesc(randomArray);
    }

    public int[] getRandomArray(int len, int bound) {
        Random random = new Random();
        int[] randomArray = new int[len];

        for (int i = 0; i < len; ++i) {
            randomArray[i] = random.nextInt(bound);
        }

        return sortArray(randomArray);
    }

//    public int[] sortDescBySwapping(int[] arr) {
//        int[] sortDesc = new int[arr.length];
//        Arrays.sort(arr);
//
//        for (int i = arr.length - 1; i > 0; i--) {
//
//        }
//
//        return sortDesc;
//    }

    public int[] sortDesc(int[] arr) {
        int[] sortedArray = Arrays.copyOf(arr, arr.length);
        boolean flag = true;
        int temp;

        while (flag) {
            flag = false;
            for (int i = 0; i < sortedArray.length - 1; ++i) {
                if (sortedArray[i] < sortedArray[i + 1]) {
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                    flag = true;
                }
            }
        }

        return sortedArray;
    }

    public int[] sortArray(int[] randomArray) {
        for (int i = 0; i < randomArray.length; ++i) {
            for (int j = i + 1; j < randomArray.length; ++j) {
                if (randomArray[i] > randomArray[j]) {
                    int temp = randomArray[i];
                    randomArray[i] = randomArray[j];
                    randomArray[j] = temp;
                }
            }
        }

        return randomArray;
    }

    public int[] sortArrayDesc(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                if (array[i] < array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }
}
