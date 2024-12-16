package day_016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
private static final int[][] DIRECTIONS = {
        {0, 1},   // East
        {1, 0},   // South
        {0, -1},  // West
        {-1, 0}   // North
};
private static final int TURN_COST = 1000;
private static final int INF = Integer.MAX_VALUE;

    /**
     * Represents the maze with its start and end positions.
     */
    private static class Maze {
        int rows, cols;
        int[] start, end;
        List<String> grid;

        Maze(List<String> mazeLines) {
            this.grid = mazeLines;
            this.rows = mazeLines.size();
            this.cols = mazeLines.getFirst().length();
            this.start = locatePosition('S');
            this.end = locatePosition('E');
        }

        private int[] locatePosition(char target) {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid.get(r).charAt(c) == target) {
                        return new int[]{r, c};
                    }
                }
            }
            throw new IllegalArgumentException("Could not find '%s' in the maze.".formatted(target));
        }

        boolean isValidCell(int r, int c) {
            return r >= 0 && r < rows && c >= 0 && c < cols && grid.get(r).charAt(c) != '#';
        }
    }

public static void main(String[] args) {
    try {
        // Read the maze input from a file
        List<String> mazeLines = readMaze("input_list/day_016_puzzle_input.txt");

        // Find the minimum cost to reach the end position in the maze (Part 1)
        int minimumCost = findMinimumCost(mazeLines);
        System.out.printf("Part 1 - Lowest possible score: %d%n", minimumCost);

        // Find the number of tiles on at least one best path from start to end (Part 2)
        int bestPathTiles = findBestPathMaze(mazeLines);
        System.out.printf("Part 2 - Number of tiles on at least one best path: %d%n", bestPathTiles);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * Reads the maze input from a file.
 * @param filePath Path to the maze input file.
 * @return A list of strings representing the maze.
 * @throws IOException If an error occurs while reading the file.
 */
public static List<String> readMaze(String filePath) throws IOException {
    List<String> lines = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line.trim());
        }
    }
    return lines;
}

/**
 * Part 1 - Finds the minimum cost to reach the end position in the maze.
 * @param mazeLines List of strings representing the maze.
 * @return Minimum cost to reach the end position.
 */
public static int findMinimumCost(List<String> mazeLines) {
    Maze maze = new Maze(mazeLines);
    int[][][] dist = initializeDistances(maze.rows, maze.cols);
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    Set<String> visited = new HashSet<>();

    dist[maze.start[0]][maze.start[1]][0] = 0;
    pq.offer(new int[]{0, maze.start[0], maze.start[1], 0}); // cost, row, col, direction

    while (!pq.isEmpty()) {
        int[] current = pq.poll();
        int cost = current[0], r = current[1], c = current[2], d = current[3];

        if (r == maze.end[0] && c == maze.end[1]) {
            return cost;
        }

        String stateKey = generateStateKey(r, c, d);
        if (visited.contains(stateKey)) continue;
        visited.add(stateKey);

        exploreNeighbors(pq, maze, dist, cost, r, c, d);
    }

    return -1; // No path found
}

private static int[][][] initializeDistances(int rows, int cols) {
    int[][][] dist = new int[rows][cols][4];
    for (int[][] layer : dist) {
        for (int[] row : layer) {
            Arrays.fill(row, INF);
        }
    }
    return dist;
}

private static String generateStateKey(int row, int col, int direction) {
    return "%d,%d,%d".formatted(row, col, direction);
}

private static void exploreNeighbors(PriorityQueue<int[]> pq, Maze maze, int[][][] dist, int cost, int r, int c, int d) {
    // Move forward
    int nr = r + DIRECTIONS[d][0];
    int nc = c + DIRECTIONS[d][1];
    if (maze.isValidCell(nr, nc)) {
        updateDistanceAndQueue(pq, dist, cost + 1, nr, nc, d);
    }

    // Turn left
    int leftDir = (d + 3) % 4;
    updateDistanceAndQueue(pq, dist, cost + TURN_COST, r, c, leftDir);

    // Turn right
    int rightDir = (d + 1) % 4;
    updateDistanceAndQueue(pq, dist, cost + TURN_COST, r, c, rightDir);
}

private static void updateDistanceAndQueue(PriorityQueue<int[]> pq, int[][][] dist, int newCost, int row, int col, int dir) {
    if (newCost < dist[row][col][dir]) {
        dist[row][col][dir] = newCost;
        pq.offer(new int[]{newCost, row, col, dir});
    }
}


    /**
     * Part 2 - Find the number of tiles on at least one best path from start to end.
     * @param mazeLines List of strings representing the maze
     * @return Number of tiles on at least one best path
     */
    public static int findBestPathMaze(List<String> mazeLines) {
        int[][] directions = {
                {0, 1},   // East
                {1, 0},   // South
                {0, -1},  // West
                {-1, 0}   // North
        };

        int rows = mazeLines.size();
        int cols = mazeLines.getFirst().length();
        int[] start = null, end = null;

        // Locate start and end positions
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = mazeLines.get(r).charAt(c);
                if (ch == 'S') {
                    start = new int[]{r, c};
                } else if (ch == 'E') {
                    end = new int[]{r, c};
                }
            }
        }

        if (start == null || end == null) {
            throw new IllegalArgumentException("Could not find 'S' or 'E' in the maze.");
        }

        int[][][] dist = new int[rows][cols][4];
        for (int[][] layer : dist) {
            for (int[] row : layer) {
                Arrays.fill(row, INF);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dist[start[0]][start[1]][0] = 0;
        pq.offer(new int[]{0, start[0], start[1], 0}); // cost, row, col, direction
        Set<String> visited = new HashSet<>();

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0], r = current[1], c = current[2], d = current[3];

            String stateKey = "%d,%d,%d".formatted(r, c, d);
            if (visited.contains(stateKey)) continue;
            visited.add(stateKey);

            // Move forward
            int nr = r + directions[d][0];
            int nc = c + directions[d][1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && mazeLines.get(nr).charAt(nc) != '#') {
                int newCost = cost + 1;
                if (newCost < dist[nr][nc][d]) {
                    dist[nr][nc][d] = newCost;
                    pq.offer(new int[]{newCost, nr, nc, d});
                }
            }

            // Turn left
            int leftDir = (d + 3) % 4;
            int leftCost = cost + 1000;
            if (leftCost < dist[r][c][leftDir]) {
                dist[r][c][leftDir] = leftCost;
                pq.offer(new int[]{leftCost, r, c, leftDir});
            }

            // Turn right
            int rightDir = (d + 1) % 4;
            int rightCost = cost + 1000;
            if (rightCost < dist[r][c][rightDir]) {
                dist[r][c][rightDir] = rightCost;
                pq.offer(new int[]{rightCost, r, c, rightDir});
            }
        }

        // Trace back the best path
        int minCostEnd = Arrays.stream(dist[end[0]][end[1]]).min().orElse(INF);
        if (minCostEnd == INF) {
            return 0;
        }

        boolean[][] onBestPath = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            if (dist[end[0]][end[1]][d] == minCostEnd) {
                queue.offer(new int[]{end[0], end[1], d});
            }
        }
        Set<String> visitedReverse = new HashSet<>();

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], d = current[2];
            onBestPath[r][c] = true;

            int costHere = dist[r][c][d];

            // Reverse move forward
            int nr = r - directions[d][0];
            int nc = c - directions[d][1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                if (mazeLines.get(nr).charAt(nc) != '#' && dist[nr][nc][d] == costHere - 1) {
                    String reverseKey = "%d,%d,%d".formatted(nr, nc, d);
                    if (!visitedReverse.contains(reverseKey)) {
                        visitedReverse.add(reverseKey);
                        queue.offer(new int[]{nr, nc, d});
                    }
                }
            }

            // Reverse turns
            for (int prevDir : new int[]{(d + 3) % 4, (d + 1) % 4}) {
                if (dist[r][c][prevDir] == costHere - 1000) {
                    String reverseKey = "%d,%d,%d".formatted(r, c, prevDir);
                    if (!visitedReverse.contains(reverseKey)) {
                        visitedReverse.add(reverseKey);
                        queue.offer(new int[]{r, c, prevDir});
                    }
                }
            }
        }

        int tilesOnBestPath = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (onBestPath[r][c]) tilesOnBestPath++;
            }
        }

        return tilesOnBestPath;
    }
}
