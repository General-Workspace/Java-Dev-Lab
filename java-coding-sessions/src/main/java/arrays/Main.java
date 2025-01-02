package arrays;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SortingArray result = new SortingArray();
        int[] randomArray = result.getRandomArray(10);
        System.out.println(Arrays.toString(randomArray));
    }
}


//        Integer[] numbers = Arrays.stream(randomArray).boxed().toArray(Integer[]::new);
//
//        Arrays.sort(numbers, Collections.reverseOrder());
//
//        randomArray = Arrays.stream(numbers).mapToInt(Integer::intValue).toArray();