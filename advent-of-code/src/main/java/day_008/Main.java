package day_008;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Part 1
            char[][] map = loadMapFromFile("day_008_puzzle_input.txt");
            Map<Character, List<int[]>> antennaMap = extractAntennas(map);
            Set<String> uniqueAntinodes = calculateUniqueAntinodes(antennaMap, map.length, map[0].length);
            System.out.println("Part 1: Number of unique antinode locations: " + uniqueAntinodes.size());

            // Part 2
            int numberOfAntinodes = calculateUniqueAntinodesPart2(antennaMap, map.length, map[0].length);
            System.out.println("Part 2: Number of unique antinode locations: " + numberOfAntinodes);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Reads the map from a file and returns it as a 2D character array.
     */
    private static char[][] loadMapFromFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] map = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            map[i] = lines.get(i).toCharArray();
        }
        return map;
    }

    /**
     * Extracts antenna positions from the map and groups them by frequency.
     */
    private static Map<Character, List<int[]>> extractAntennas(char[][] map) {
        Map<Character, List<int[]>> antennaMap = new HashMap<>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                char c = map[row][col];
                if (Character.isLetterOrDigit(c)) {
                    antennaMap.putIfAbsent(c, new ArrayList<>());
                    antennaMap.get(c).add(new int[]{row, col});
                }
            }
        }
        return antennaMap;
    }

    /**
     * Calculates the set of unique antinode positions within the map bounds.
     */
    private static Set<String> calculateUniqueAntinodes(Map<Character, List<int[]>> antennaMap, int rows, int cols) {
        Set<String> uniqueAntinodes = new HashSet<>();
        for (Map.Entry<Character, List<int[]>> entry : antennaMap.entrySet()) {
            List<int[]> antennas = entry.getValue();
            processFrequencyAntennas(antennas, uniqueAntinodes, rows, cols);
        }
        return uniqueAntinodes;
    }

    /**
     * Processes antennas of the same frequency to calculate potential antinodes.
     */
    private static void processFrequencyAntennas(List<int[]> antennas, Set<String> uniqueAntinodes, int rows, int cols) {
        for (int i = 0; i < antennas.size(); i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                calculateAntinodePositions(antennas.get(i), antennas.get(j), uniqueAntinodes, rows, cols);
            }
        }
    }

    /**
     * Calculates antinode positions for a pair of antennas and adds valid ones to the set.
     */
    private static void calculateAntinodePositions(int[] antenna1, int[] antenna2, Set<String> uniqueAntinodes, int rows, int cols) {
        int x1 = antenna1[0], y1 = antenna1[1];
        int x2 = antenna2[0], y2 = antenna2[1];

        // Calculate the vector between the two antennas
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Calculate the two possible antinode positions
        int[] antinode1 = {x1 - dx, y1 - dy};
        int[] antinode2 = {x2 + dx, y2 + dy};

        // Add valid antinodes to the set
        addAntinodeIfValid(antinode1, uniqueAntinodes, rows, cols);
        addAntinodeIfValid(antinode2, uniqueAntinodes, rows, cols);
    }

    /**
     * Adds an antinode to the set if it's within the bounds of the map.
     */
    private static void addAntinodeIfValid(int[] antinode, Set<String> uniqueAntinodes, int rows, int cols) {
        int x = antinode[0], y = antinode[1];
        if (isWithinBounds(x, y, rows, cols)) {
            uniqueAntinodes.add(x + "," + y);
        }
    }

    /**
     * Checks if a position is within the bounds of the map.
     */
    private static boolean isWithinBounds(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    /**
     * Part 2
     */

    private static int calculateUniqueAntinodesPart2(Map<Character, List<int[]>> antennaMap, int rows, int cols) {
        Set<String> uniqueAntinodes = new HashSet<>();
        for (Map.Entry<Character, List<int[]>> entry : antennaMap.entrySet()) {
            List<int[]> antennas = entry.getValue();

            // Add all antennas as antinodes
            for (int[] antenna : antennas) {
                uniqueAntinodes.add(antenna[0] + "," + antenna[1]);
            }

            // Add all possible antinodes created by every pair of antennas
            for (int i = 0; i < antennas.size(); i++) {
                for (int j = i + 1; j < antennas.size(); j++) {
                    addLineAntinodes(antennas.get(i), antennas.get(j), uniqueAntinodes, rows, cols);
                }
            }
        }
        return uniqueAntinodes.size();
    }

    private static void addLineAntinodes(int[] antenna1, int[] antenna2, Set<String> uniqueAntinodes, int rows, int cols) {
        int x1 = antenna1[0], y1 = antenna1[1];
        int x2 = antenna2[0], y2 = antenna2[1];

        // Calculate differences
        int dx = x2 - x1;
        int dy = y2 - y1;

        // Reduce dx and dy to their smallest integer ratio
        int gcd = gcd(Math.abs(dx), Math.abs(dy));
        dx /= gcd;
        dy /= gcd;

        // Add all points in both directions along the line
        addAntinodesInDirection(x1, y1, dx, dy, uniqueAntinodes, rows, cols);
        addAntinodesInDirection(x1, y1, -dx, -dy, uniqueAntinodes, rows, cols);
    }

    // Add all antinodes in a specific direction
    private static void addAntinodesInDirection(int x, int y, int dx, int dy, Set<String> uniqueAntinodes, int rows, int cols) {
        while (isWithinBounds(x, y, rows, cols)) {
            uniqueAntinodes.add(x + "," + y);
            x += dx;
            y += dy;
        }
    }

    // Find the greatest common divisor
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}