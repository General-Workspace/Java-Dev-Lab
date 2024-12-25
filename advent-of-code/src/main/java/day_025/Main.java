package day_025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Set<Coordinate>> locks = parseInput("input_list/day_025_puzzle_input.txt");
        long startTime = System.nanoTime();
        int lockCount = convertToHeights(locks);
        long endTime = System.nanoTime();
        System.out.printf("Count of unique lock/key pairs that fit together took: %.5f sec%n", (endTime - startTime) / 1e9);
        System.out.printf("Count of unique lock/key pairs that fit together: %d%n", lockCount);
    }

    private static List<Set<Coordinate>> parseInput(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        List<Set<Coordinate>> locks = new ArrayList<>();
        Set<Coordinate> currentLock = new HashSet<>();
        int row = 0;

        for (String line : lines) {
            if (line.isEmpty()) {
                if (!currentLock.isEmpty()) {
                    locks.add(currentLock);
                    currentLock = new HashSet<>();
                }
                row = 0;
            } else {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == '#') {
                        currentLock.add(new Coordinate(col, row));
                    }
                }
                row++;
            }
        }

        if (!currentLock.isEmpty()) {
            locks.add(currentLock);
        }

        return locks;
    }

    private static int convertToHeights(List<Set<Coordinate>> locks) {
        int pairs = 0;
        for (int i = 0; i < locks.size(); i++) {
            for (int j = i + 1; j < locks.size(); j++) {
                if (locks.get(i).stream().noneMatch(locks.get(j)::contains)) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    private static class Coordinate {
        int col;
        int row;

        Coordinate(int col, int row) {
            this.col = col;
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return col == that.col && row == that.row;
        }

        @Override
        public int hashCode() {
            return 31 * col + row;
        }
    }
}