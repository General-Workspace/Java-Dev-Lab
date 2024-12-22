package day_022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> values = readFile("input_list/day_022_puzzle_input.txt");

        System.out.println("Part 1: " + generateTwoThousandNewSecretNumbers(values));
        System.out.println("Part 2: " + getMostBananas(values));
    }

    public static List<Integer> readFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Integer> values = new ArrayList<>();
        for (String line : lines) {
            values.add(Integer.parseInt(line));
        }
        return values;
    }

    public static long generateTwoThousandNewSecretNumbers(List<Integer> values) {
        long startTime = System.nanoTime();
        long total = 0;
        for (int x : values) {
            for (int i = 0; i < 2000; i++) {
                x = evolveSecret(x);
            }
            total += x;
        }
        long endTime = System.nanoTime();
        System.out.printf("Method generateTwoThousandNewSecretNumbers took: %.5f sec%n", (endTime - startTime) / 1e9);
        return total;
    }

    public static int getMostBananas(List<Integer> values) {
        long startTime = System.nanoTime();
        Map<List<Integer>, Integer> total = new HashMap<>();
        for (int x : values) {
            int last = x % 10;
            List<int[]> patternList = new ArrayList<>();
            for (int i = 0; i < 2000; i++) {
                x = evolveSecret(x);
                int temp = x % 10;
                patternList.add(new int[]{temp - last, temp});
                last = temp;
            }
            Set<List<Integer>> seen = new HashSet<>();
            for (int i = 0; i < patternList.size() - 3; i++) {
                List<Integer> pat = Arrays.asList(
                        patternList.get(i)[0],
                        patternList.get(i + 1)[0],
                        patternList.get(i + 2)[0],
                        patternList.get(i + 3)[0]
                );
                int val = patternList.get(i + 3)[1];
                if (!seen.contains(pat)) {
                    seen.add(pat);
                    total.put(pat, total.getOrDefault(pat, 0) + val);
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.printf("Method getMostBananas took: %.5f sec%n", (endTime - startTime) / 1e9);
        return Collections.max(total.values());
    }

    public static int evolveSecret(int secretNumber) {
        // Step 1: Multiply by 64, mix, prune
        secretNumber ^= (secretNumber * 64) % 16777216;
        secretNumber %= 16777216;

        // Step 2: Divide by 32, round down, mix, prune
        secretNumber ^= (secretNumber / 32) % 16777216;
        secretNumber %= 16777216;

        // Step 3: Multiply by 2048, mix, prune
        secretNumber ^= (secretNumber * 2048) % 16777216;
        secretNumber %= 16777216;

        return secretNumber;
    }
}