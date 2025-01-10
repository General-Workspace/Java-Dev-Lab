package array_list;

import java.util.*;

public class Sorting {
    public int[] readInput(int length, int bound) {
        int[] numbers = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; ++i) {
            numbers[i] = random.nextInt(bound);
        }

        return numbers;
    }

//    public ArrayList<Integer> sortedList(int[] input) {
//        //List<int> transformedInput = List.of(input);
//        //ArrayList<Integer> arrayList = new ArrayList<Integer>(input.length);
//        // ArrayList<int[]> arrayList = new ArrayList<>();
//        var arrayList = Arrays.asList(input);
//        arrayList.sort(Comparator.naturalOrder());
//
//        return arrayList;
//    }
}
