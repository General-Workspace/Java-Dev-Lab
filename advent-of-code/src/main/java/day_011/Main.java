//package day_011;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class Main {
//    private static final int MAX_STONES = 100_000_000;
//
//    public static void main(String[] args) {
//        try {
//            List<String> initialStones = Files.readAllLines(Paths.get("input_list/day_011_puzzle_input.txt"));
//            int numOfBlinks = 75;
//
//            // Parse the first line
//            String[] stoneValues = initialStones.get(0).trim().split("\\s+");
//            List<Long> stones = new ArrayList<>();
//            for (String value : stoneValues) {
//                stones.add(Long.parseLong(value));
//            }
//
//            System.out.println("Initial arrangement:");
//            printStones(stones);
//
//            for (int blink = 1; blink <= numOfBlinks; blink++) {
//                transformStonesInPlace(stones);
//
//                // Stop if we exceed the maximum number of stones
//                if (stones.size() > MAX_STONES) {
//                    System.out.printf("Stopped at blink %d due to stone count exceeding %d%n", blink, MAX_STONES);
//                    break;
//                }
//
//                System.out.printf("\nAfter %d blink%s:%n", blink, blink > 1 ? "s" : "");
//                System.out.printf("Number of stones: %d%n", stones.size());
//            }
//
//            System.out.printf("\nTotal number of stones after processing: %d%n", stones.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void printStones(List<Long> stones) {
//        stones.stream().limit(100).forEach(stone -> System.out.print(stone + " "));
//        if (stones.size() > 100) {
//            System.out.print("... (more stones)");
//        }
//        System.out.println();
//    }
//
//    private static void transformStonesInPlace(List<Long> stones) {
//        List<Long> newStones = new ArrayList<>();
//        Iterator<Long> iterator = stones.iterator();
//
//        while (iterator.hasNext()) {
//            long stone = iterator.next();
//            if (stone == 0) {
//                newStones.add(1L);
//            } else if (hasEvenDigits(stone)) {
//                String stoneStr = String.valueOf(stone);
//                int mid = stoneStr.length() / 2;
//                long leftStone = Long.parseLong(stoneStr.substring(0, mid));
//                long rightStone = Long.parseLong(stoneStr.substring(mid));
//                newStones.add(leftStone);
//                newStones.add(rightStone);
//            } else {
//                newStones.add(stone * 2024);
//            }
//
//            // Remove processed stone to free memory
//            iterator.remove();
//        }
//
//        // Replace old list contents with the new list
//        stones.addAll(newStones);
//
//        // Limit stone growth
//        if (stones.size() > MAX_STONES) {
//            stones.subList(MAX_STONES, stones.size()).clear();
//        }
//    }
//
//    private static boolean hasEvenDigits(Long number) {
//        return String.valueOf(number).length() % 2 == 0;
//    }
//}



package day_011;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> initialStones = Files.readAllLines(Paths.get("input_list/day_011_puzzle_input.txt"));
            int numOfBlinks = 75;

            // Parse the first line
            String[] stoneValues = initialStones.getFirst().trim().split("\\s+");
            List<Long> stones = new ArrayList<>();
            for (String value : stoneValues) {
                stones.add(Long.parseLong(value));
            }

            System.out.println("Initial arrangement:");
            printStones(stones);

            for (int blink = 1; blink <= numOfBlinks; blink++) {
                stones = transformStones(stones);
                System.out.printf("\nAfter %d blink%s:%n", blink, blink > 1 ? "s" : "");

                // Print only a summary to save memory
                System.out.printf("Number of stones: %d%n", stones.size());
            }

            System.out.printf("\nTotal number of stones after %d blinks: %d%n", numOfBlinks, stones.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printStones(List<Long> stones) {
        // Print only the first 100 stones as a preview
        stones.stream().limit(100).forEach(stone -> System.out.print(stone + " "));
        if (stones.size() > 100) {
            System.out.print("... (more stones)");
        }
        System.out.println();
    }

    private static List<Long> transformStones(List<Long> currentStones) {
        List<Long> newStones = new ArrayList<>(currentStones.size() * 2);

        for (Long stone : currentStones) {
            if (stone == 0) {
                newStones.add(1L);
            } else if (hasEvenDigits(stone)) {
                String stoneStr = String.valueOf(stone);
                int mid = stoneStr.length() / 2;
                long leftStone = Long.parseLong(stoneStr.substring(0, mid));
                long rightStone = Long.parseLong(stoneStr.substring(mid));
                newStones.add(leftStone);
                newStones.add(rightStone);
            } else {
                newStones.add(stone * 2024);
            }
        }

        return newStones;
    }

    private static boolean hasEvenDigits(Long number) {
        return String.valueOf(number).length() % 2 == 0;
    }
}
