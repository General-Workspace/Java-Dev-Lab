package day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            String[] map = readMapFromFile("day_6_puzzle_input.txt");
            // Part 1
            int visitedPositions = countVisitedPositions(map);

            // Part 2
            int loopCausingPositions = countLoopCausingPositions(map);
            System.out.println("Number of visited positions by the guard: " + visitedPositions);
            System.out.println("Number of positions causing loops: " + loopCausingPositions);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Part 1: Count the number of visited positions by the guard
     * @param map
     * @return
     */

    // Main function to count visited positions
    public static int countVisitedPositions(String[] map) {
        char[][] grid = convertToGrid(map);
        int[] guardInfo = findGuard(grid);
        int guardX = guardInfo[0], guardY = guardInfo[1], direction = guardInfo[2];
        Set<String> visited = new HashSet<>();
        visited.add(guardX + "," + guardY);

        simulatePatrol(grid, guardX, guardY, direction, visited);
        return visited.size();
    }

    // Converts the map to a 2D char grid
    private static char[][] convertToGrid(String[] map) {
        int rows = map.length, cols = map[0].length();
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = map[i].toCharArray();
        }
        return grid;
    }

    // Finds the guard's initial position and direction
    private static int[] findGuard(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Map<Character, Integer> dirMap = Map.of('^', 0, '>', 1, 'v', 2, '<', 3);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid[i][j];
                if (dirMap.containsKey(cell)) {
                    grid[i][j] = '.'; // Clear guard's initial position
                    return new int[]{i, j, dirMap.get(cell)};
                }
            }
        }
        throw new IllegalArgumentException("Guard not found on the map.");
    }

    // Simulates the guard's patrol and tracks visited positions
    private static void simulatePatrol(char[][] grid, int guardX, int guardY, int direction, Set<String> visited) {
        int[] dx = {-1, 0, 1, 0}; // Up, Right, Down, Left
        int[] dy = {0, 1, 0, -1}; // Up, Right, Down, Left
        int rows = grid.length, cols = grid[0].length;

        while (true) {
            int nx = guardX + dx[direction];
            int ny = guardY + dy[direction];

            // Check if the guard is out of bounds
            if (nx < 0 || nx >= rows || ny < 0 || ny >= cols) {
                break;
            }

            // Check for obstacles
            if (grid[nx][ny] == '#') {
                direction = turnRight(direction);
            } else {
                // Move forward
                guardX = nx;
                guardY = ny;
                visited.add(guardX + "," + guardY);
            }
        }
    }

    // Turns the guard 90 degrees to the right
    private static int turnRight(int direction) {
        return (direction + 1) % 4;
    }

    public static String[] readMapFromFile(String filename) throws IOException {
        // Read all lines from the file and convert them to an array of strings
        return Files.readAllLines(Paths.get(filename)).toArray(String[]::new);
    }

    /**
     * Part 2: Count the number of visited positions by the guard by adding obstruction
     */

    // Main function to count positions causing loops
    public static int countLoopCausingPositions(String[] map) {
        char[][] grid = convertToGrid(map);
        int[] guardInfo = findGuard(grid);
        int guardX = guardInfo[0], guardY = guardInfo[1], direction = guardInfo[2];

        Set<String> loopCausingPositions = new HashSet<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                // Skip starting position or existing obstacles
                if (grid[x][y] == '#' || (x == guardX && y == guardY)) continue;

                // Place an obstruction temporarily
                grid[x][y] = '#';
                if (isGuardTrappedInLoop(grid, guardX, guardY, direction)) {
                    loopCausingPositions.add(x + "," + y);
                }
                // Remove the obstruction
                grid[x][y] = '.';
            }
        }

        return loopCausingPositions.size();
    }

    // Check if the guard gets trapped in a loop
    private static boolean isGuardTrappedInLoop(char[][] grid, int guardX, int guardY, int direction) {
        int[] dx = {-1, 0, 1, 0}; // Up, Right, Down, Left
        int[] dy = {0, 1, 0, -1}; // Up, Right, Down, Left
        Set<String> visitedStates = new HashSet<>();
        int rows = grid.length, cols = grid[0].length;

        while (true) {
            String state = guardX + "," + guardY + "," + direction;
            if (visitedStates.contains(state)) return true; // Loop detected
            visitedStates.add(state);

            int nx = guardX + dx[direction];
            int ny = guardY + dy[direction];

            // Check if the guard is out of bounds
            if (nx < 0 || nx >= rows || ny < 0 || ny >= cols) break;

            // Check for obstacles
            if (grid[nx][ny] == '#') {
                direction = turnRight(direction);
            } else {
                // Move forward
                guardX = nx;
                guardY = ny;
            }
        }
        return false; // No loop
    }
}
