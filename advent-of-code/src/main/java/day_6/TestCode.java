package day_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestCode {
    public static void main(String[] args) {
        try {
            String[] map = readMapFromFile("test.txt");
            int visitedPositions = countVisitedPositions(map);
            System.out.println("Number of visited positions by the guard: " + visitedPositions);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    enum Direction {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        private final int dx;
        private final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        // Rotate the direction 90 degrees to the right
        public Direction rotateRight() {
            return values()[(ordinal() + 1) % values().length];
        }
    }

    public static int countVisitedPositions(String[] map) {
        int rows = map.length;
        int cols = map[0].length();
        char[][] grid = new char[rows][cols];
        int guardX = -1, guardY = -1;
        char direction = '^';

        // Convert map to grid and locate the guard
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = map[i].charAt(j);
                if (grid[i][j] == '^' || grid[i][j] == 'v' || grid[i][j] == '<' || grid[i][j] == '>') {
                    guardX = i;
                    guardY = j;
                    direction = grid[i][j];
                    grid[i][j] = '.'; // Clear guard's initial position
                }
            }
        }

        // Directions: Up, Right, Down, Left
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Map<Character, Integer> dirMap = Map.of('^', 0, '>', 1, 'v', 2, '<', 3);

        int dir = dirMap.get(direction); // Initial direction
        Set<String> visited = new HashSet<>();
        visited.add(guardX + "," + guardY);

        while (true) {
            int nx = guardX + dx[dir];
            int ny = guardY + dy[dir];

            if (nx < 0 || nx >= rows || ny < 0 || ny >= cols) {
                // Guard leaves the map
                break;
            }

            if (grid[nx][ny] == '#') {
                // Turn right 90 degrees
                dir = (dir + 1) % 4;
            } else {
                // Move forward
                guardX = nx;
                guardY = ny;
                visited.add(guardX + "," + guardY);
            }
        }

        return visited.size();
    }

    public static int countGuardPositions(String[] map) {
        Set<String> visitedPositions = new HashSet<>();

        int startX = -1, startY = -1;
        Direction currentDirection = null;

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length(); x++) {
                if (map[y].charAt(x) == '^') {
                    startX = x;
                    startY = y;
                    currentDirection = Direction.UP;
                    break;
                }
            }
            if (startX != -1) {
                break;
            }
        }

        int x = startX, y = startY;

        visitedPositions.add(x + "," + y);

        while (true) {
            assert currentDirection != null;
            int nextX = x + currentDirection.dx;
            int nextY = y + currentDirection.dy;

            if (nextX < 0 || nextX >= map[0].length() || nextY < 0 || nextY >= map.length) {
                break;
            }

            if (isBlocked(map, nextX, nextY)) {
                currentDirection = currentDirection.rotateRight();
            } else {
                x = nextX;
                y = nextY;
                visitedPositions.add(x + "," + y);
            }
        }

        return visitedPositions.size();
    }

    private static boolean isBlocked(String[] map, int x, int y) {
        return map[y].charAt(x) == '#';
    }

    public static String[] readMapFromFile(String filename) throws IOException {
        return Files.readAllLines(Paths.get(filename)).toArray(String[]::new);  // Read all lines from the file and convert them to an array of strings
    }
}
