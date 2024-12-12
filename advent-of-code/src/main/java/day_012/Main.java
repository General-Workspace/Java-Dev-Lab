package day_012;

import java.io.*;
import java.util.*;

public class Main {

    private static class Region {
        int area;
        int perimeter;
        int sides;

        Region(int area, int perimeter, int sides) {
            this.area = area;
            this.perimeter = perimeter;
            this.sides = sides;
        }
    }

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

    public static void main(String[] args) {
        try {
            char[][] gardenMap = readGardenMap("input_list/day_012_puzzle_input.txt");
            var totalPrice = calculateTotalPrice(gardenMap);
            var totalPriceUsingSides = calculateTotalPriceUsingSides(gardenMap);

            System.out.printf("Total Price: %d%n", totalPrice);
            System.out.printf("Total Price Using Sides: %d%n", totalPriceUsingSides);
        } catch (IOException e) {
            System.err.printf("Error reading the file: %s%n", e.getMessage());
        }
    }

    private static char[][] readGardenMap(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        char[][] map = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }
        return map;
    }

    private static int calculateTotalPrice(char[][] gardenMap) {
        int totalPrice = 0;
        int rows = gardenMap.length;
        int cols = gardenMap[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    char plantType = gardenMap[i][j];
                    Region region = calculateRegion(gardenMap, visited, i, j, plantType);
                    //totalPrice += region.area * region.perimeter;
                    int price = region.area * region.perimeter;
                    totalPrice += price;
                    System.out.printf("A region of %c plants with price %d * %d = %d.%n", plantType, region.area, region.perimeter, price);
                }
            }
        }

        return totalPrice;
    }

    private static Region calculateRegion(char[][] map, boolean[][] visited, int startRow, int startCol, char plantType) {
        int area = 0;
        int perimeter = 0;
        int rows = map.length;
        int cols = map[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            area++;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || map[newRow][newCol] != plantType) {
                    // Out of bounds or different plant type, count as perimeter
                    perimeter++;
                } else if (!visited[newRow][newCol]) {
                    // Same plant type and not visited
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return new Region(area, perimeter, 0); // Sides not used here
    }

    /**
     * Part 2
     */

    private static Region calculateRegionUsingSides(char[][] map, boolean[][] visited, int startRow, int startCol, char plantType) {
        int area = 0;
        int sides = 0;
        int rows = map.length;
        int cols = map[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            area++;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || map[newRow][newCol] != plantType) {
                    // Out of bounds or different plant type, count as side
                    sides++;
                } else if (!visited[newRow][newCol]) {
                    // Same plant type and not visited
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return new Region(area, 0, sides); // Perimeter not used here
    }

    private static int calculateTotalPriceUsingSides(char[][] gardenMap) {
        int totalPrice = 0;
        int rows = gardenMap.length;
        int cols = gardenMap[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    char plantType = gardenMap[i][j];
                    Region region = calculateRegionUsingSides(gardenMap, visited, i, j, plantType);
                    int price = region.area * region.sides;
                    totalPrice += price;
                    System.out.printf("A region of %c plants with price %d * %d = %d.%n", plantType, region.area, region.sides, price);
                }
            }
        }

        //System.out.printf("Total Price Using Sides: %d%n", totalPrice);
        return totalPrice;
    }
}
