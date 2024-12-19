package day_019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] ignoredArgs) throws IOException {
        // Parse input
        String filePath = "input_list/day_019_puzzle_input.txt";
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        // Separate patterns and designs
        int separatorIndex = lines.indexOf("");
        List<String> towelPatterns = List.of(lines.getFirst().split(", "));
        List<String> designs = new ArrayList<>(lines.subList(separatorIndex + 1, lines.size()));

        // Part 1: Count possible designs
        long startTimePart1 = System.nanoTime();
        int possibleDesignCount = possibleDesignsCount(towelPatterns, designs);
        long endTimePart1 = System.nanoTime();
        System.out.printf("Part 1: Number of possible designs = %d%n", possibleDesignCount);
        System.out.printf("Time to solve Part 1: %dms%n", (endTimePart1 - startTimePart1) / 1_000_000);

        // Part 2: Total number of arrangements
        long startTimePart2 = System.nanoTime();
        long totalArrangementsCount = totalArrangements(towelPatterns, designs);
        long endTimePart2 = System.nanoTime();
        System.out.printf("Part 2: Number of ways towels could be arranged = %d%n", totalArrangementsCount);
        System.out.printf("Time to solve Part 2: %dms%n", (endTimePart2 - startTimePart2) / 1_000_000);
    }

    /**
     * Part 1 - Count the number of possible designs that can be created from the given towel patterns.
     * @param patterns List of towel patterns
     * @param designs List of designs
     * @return Number of possible designs
     */
    private static int possibleDesignsCount(List<String> patterns, List<String> designs) {
        int possibleCount = 0;
        for (String design : designs) {
            if (canCreateDesign(design, patterns)) {
                possibleCount++;
            }
        }
        return possibleCount;
    }

    private static boolean canCreateDesign(String design, List<String> towelSet) {
        int n = design.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && towelSet.contains(design.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    /**
     * Part 2 - Count the total number of arrangements that can be created from the given towel patterns.
     * @param patterns List of towel patterns
     * @param designs List of designs
     * @return Total number of arrangements
     */
    private static long totalArrangements(List<String> patterns, List<String> designs) {
        long totalCount = 0;
        for (String design : designs) {
            totalCount += countDesignArrangements(design, patterns);
        }
        return totalCount;
    }

    private static long countDesignArrangements(String design, List<String> patterns) {
        int n = design.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case

        for (int i = 1; i <= n; i++) {
            for (String pattern : patterns) {
                if (i >= pattern.length() && design.startsWith(pattern, i - pattern.length())) {
                    dp[i] += dp[i - pattern.length()];
                }
            }
        }

        return dp[n];
    }
}