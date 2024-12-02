package day_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

            List<List<Integer>> combinedList = readListFromFile("input_list.txt");
            int[] left = combinedList.get(0).stream().mapToInt(i -> i).toArray();
            int[] right = combinedList.get(1).stream().mapToInt(i -> i).toArray();

            int distance = calculateDistance(left, right);

            System.out.println(distance);
    }

    public static int calculateDistance(int[] left, int[] right) {
        // sort left and right
        Arrays.sort(left);
        Arrays.sort(right);

        int distance = 0;

        for (int i = 0; i < left.length; i++) {
            distance += Math.abs(left[i] - right[i]);
        }
        return distance;
    }

    public static List<List<Integer>> readListFromFile(String path) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        List<List<Integer>> combinedList = new ArrayList<>();

        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                if (numbers.length >= 2 && !line.trim().isEmpty()) {
                    leftList.add(Integer.parseInt(numbers[0]));
                    rightList.add(Integer.parseInt(numbers[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
        combinedList.add(leftList);
        combinedList.add(rightList);
        return combinedList;
    }
}
