package day_004;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Read input from a file called day_004_puzzle_input.txt
        try (BufferedReader br = new BufferedReader(new FileReader("day_004_puzzle_input.txt"))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            char[][] grid = new char[lines.size()][lines.getFirst().length()];
            int rows = grid.length;
            int cols = grid[0].length;
            for (int i = 0; i < lines.size(); i++) {
                grid[i] = lines.get(i).toCharArray();
            }

            String targetWord = "XMAS";
            var gridTwo = lines.toArray(new String[0]);

            System.out.println(countXmas(grid, targetWord));
            System.out.println(countMASInXPatterns(gridTwo, rows, cols));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int countXmas(char[][] grid, String targetWord) {
        int[][] DIRECTIONS = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : DIRECTIONS) {
                    if (findXmas(grid, row, col, dir[0], dir[1], targetWord)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static boolean findXmas(char[][] grid, int row, int col, int rowStep, int colStep, String targetWord) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < targetWord.length(); i++) {
            if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != targetWord.charAt(i)) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }

    public static int countMASInXPatterns(String[] grid, int rows, int cols) {
        int count = 0;
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                char center = grid[r].charAt(c);
                char topLeft = grid[r - 1].charAt(c - 1);
                char topRight = grid[r - 1].charAt(c + 1);
                char bottomLeft = grid[r + 1].charAt(c - 1);
                char bottomRight = grid[r + 1].charAt(c + 1);

                if (center == 'A') {
                    // Pattern 1: M.S
                    if (topLeft == 'M' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'S') {
                        count++;
                    }
                    // Pattern 2: S.M
                    else if (topLeft == 'S' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'M') {
                        count++;
                    }
                    // Pattern 3: M.M
                    else if (topLeft == 'M' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'S') {
                        count++;
                    }
                    // Pattern 4: S.S
                    else if (topLeft == 'S' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'M') {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}