package day_020;

import java.io.*;
import java.util.*;

public class Main {

    static int rows, cols;
    static char[][] grid;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] ignoredArgs) throws IOException {
        String inputFilename = "input_list/day_020_puzzle_input.txt";

        // Read the map from the file
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        rows = lines.size();
        cols = lines.getFirst().length();
        grid = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        // Find S and E
        int[] start = null, end = null;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 'S') {
                    start = new int[]{r, c};
                } else if (grid[r][c] == 'E') {
                    end = new int[]{r, c};
                }
            }
        }

        if (start == null || end == null) {
            System.out.println("Invalid input: Missing start or end point.");
            return;
        }

        // Get distances from S and E
        int[][] distFromS = bfs(start);
        int[][] distFromE = bfs(end);

        int normalDist = distFromS[end[0]][end[1]];
        if (normalDist == Integer.MAX_VALUE) {
            // No normal path from S to E
            System.out.println(0);
            return;
        }

        // Count valid cheats
        Set<String> cheats = getStrings(distFromS, distFromE, normalDist);
        Set<String> cheats2 = getStrings2(distFromS, distFromE, normalDist);

        // Print the count of cheats
        System.out.printf("Part 1: %d\n", cheats.size());
        System.out.printf("Part 2: %d\n", cheats2.size());

    }

    /**
     * Part 1 - Brute Force
     * @param distFromS 2D array of distances from S
     * @param distFromE 2D array of distances from E
     * @param normalDist distance from S to E
     * @return set of cheats
     */
    private static Set<String> getStrings(int[][] distFromS, int[][] distFromE, int normalDist) {
        Set<String> cheats = new HashSet<>();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (distFromS[x][y] == Integer.MAX_VALUE || !isTrack(grid[x][y])) {
                    continue;
                }

                int baseDist = distFromS[x][y];

                // 1-step cheat
                for (int[] dir : directions) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (isInBounds(nx, ny) && distFromE[nx][ny] != Integer.MAX_VALUE) {
                        int routeWithCheat = baseDist + 1 + distFromE[nx][ny];
                        if (normalDist - routeWithCheat >= 100) {
                            cheats.add(x + "," + y + "," + nx + "," + ny);
                        }
                    }
                }

                // 2-step cheat
                for (int[] dir1 : directions) {
                    int ix = x + dir1[0], iy = y + dir1[1];
                    if (isInBounds(ix, iy)) {
                        for (int[] dir2 : directions) {
                            int fx = ix + dir2[0], fy = iy + dir2[1];
                            if (isInBounds(fx, fy) && isTrack(grid[fx][fy]) && distFromE[fx][fy] != Integer.MAX_VALUE) {
                                int routeWithCheat = baseDist + 2 + distFromE[fx][fy];
                                if (normalDist - routeWithCheat >= 100) {
                                    cheats.add("%d,%d,%d,%d".formatted(x, y, fx, fy));
                                }
                            }
                        }
                    }
                }
            }
        }
        return cheats;
    }
    // end of getStrings

    /**
     * Part 2 - Optimized
     * @param distFromS 2D array of distances from S
     * @param distFromE 2D array of distances from E
     * @param normalDist distance from S to E
     * @return set of cheats
     */
    private static Set<String> getStrings2(int[][] distFromS, int[][] distFromE, int normalDist) {
        // Pre-collect all track cells reachable from S
        List<int[]> trackCellsFromS = new ArrayList<>();
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (distFromS[x][y] != Integer.MAX_VALUE && isTrack(grid[x][y])) {
                    trackCellsFromS.add(new int[]{x, y});
                }
            }
        }

        Set<String> cheats = new HashSet<>();

        for (int[] cell : trackCellsFromS) {
            int sx = cell[0], sy = cell[1];

            // BFS ignoring walls from (sx, sy) up to distance 20
            int[][] distIgnoreWalls = new int[rows][cols];
            for (int[] row : distIgnoreWalls) {
                Arrays.fill(row, Integer.MAX_VALUE);
            }

            distIgnoreWalls[sx][sy] = 0;
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{sx, sy});

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0], y = current[1];
                int d = distIgnoreWalls[x][y];

                if (d == 20) {
                    continue;
                }

                for (int[] dir : directions) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (isInBounds(nx, ny) && distIgnoreWalls[nx][ny] > d + 1) {
                        distIgnoreWalls[nx][ny] = d + 1;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }

            // Check all track cells reachable within ≤ 20 steps
            int baseDist = distFromS[sx][sy];
            for (int fx = 0; fx < rows; fx++) {
                for (int fy = 0; fy < cols; fy++) {
                    int d = distIgnoreWalls[fx][fy];
                    if (d != Integer.MAX_VALUE && d >= 1 && d <= 20 && isTrack(grid[fx][fy])) {
                        if (distFromE[fx][fy] != Integer.MAX_VALUE) {
                            int routeWithCheat = baseDist + d + distFromE[fx][fy];
                            int saving = normalDist - routeWithCheat;
                            if (saving >= 100) {
                                cheats.add("%d,%d,%d,%d".formatted(sx, sy, fx, fy));
                            }
                        }
                    }
                }
            }
        }

        return cheats;
    }
    // end of getStrings2


    static boolean isInBounds(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    static boolean isTrack(char ch) {
        return ch == '.' || ch == 'S' || ch == 'E';
    }

    static int[][] bfs(int[] start) {
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        dist[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];

            for (int[] dir : directions) {
                int nx = x + dir[0], ny = y + dir[1];
                if (isInBounds(nx, ny) && isTrack(grid[nx][ny]) && dist[nx][ny] > dist[x][y] + 1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return dist;
    }
}






//package day_020;
//
//
//import java.io.*;
//import java.util.*;
//
//public class Main {
//
//    static int rows, cols;
//    static char[][] grid;
//    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//
//    public static void main(String[] ignoredArgs) throws IOException {
//        String inputFilename = "input_list/day_020_puzzle_input.txt";
//
//        // Read the map from the file
//        List<String> lines = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(inputFilename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                lines.add(line);
//            }
//        }
//
//        rows = lines.size();
//        cols = lines.get(0).length();
//        grid = new char[rows][cols];
//
//        for (int i = 0; i < rows; i++) {
//            grid[i] = lines.get(i).toCharArray();
//        }
//
//        // Find S and E
//        int[] start = null, end = null;
//        for (int r = 0; r < rows; r++) {
//            for (int c = 0; c < cols; c++) {
//                if (grid[r][c] == 'S') {
//                    start = new int[]{r, c};
//                } else if (grid[r][c] == 'E') {
//                    end = new int[]{r, c};
//                }
//            }
//        }
//
//        if (start == null || end == null) {
//            System.out.println("Invalid input: Missing start or end point.");
//            return;
//        }
//
//        // Get distances from S and E
//        int[][] distFromS = bfs(start);
//        int[][] distFromE = bfs(end);
//
//        int normalDist = distFromS[end[0]][end[1]];
//        if (normalDist == Integer.MAX_VALUE) {
//            // No normal path from S to E
//            System.out.println(0);
//            return;
//        }
//
//        // Count valid cheats
//        Map<Integer, Integer> cheats = getStrings(distFromS, distFromE, normalDist);
//        Map<Integer, Integer> cheats2 = getStrings2(distFromS, distFromE, normalDist);
//
//        // Print the count of cheats
//        printCheats(cheats);
//        printCheats(cheats2);
//    }
//
//    private static void printCheats(Map<Integer, Integer> cheats) {
//        for (Map.Entry<Integer, Integer> entry : cheats.entrySet()) {
//            System.out.printf("There are %d cheats that save %d picoseconds.\n", entry.getValue(), entry.getKey());
//        }
//    }
//
//    private static Map<Integer, Integer> getStrings(int[][] distFromS, int[][] distFromE, int normalDist) {
//        Map<Integer, Integer> cheats = new HashMap<>();
//        for (int x = 0; x < rows; x++) {
//            for (int y = 0; y < cols; y++) {
//                if (distFromS[x][y] == Integer.MAX_VALUE || !isTrack(grid[x][y])) {
//                    continue;
//                }
//
//                int baseDist = distFromS[x][y];
//
//                // 1-step cheat
//                for (int[] dir : directions) {
//                    int nx = x + dir[0], ny = y + dir[1];
//                    if (isInBounds(nx, ny) && distFromE[nx][ny] != Integer.MAX_VALUE) {
//                        int routeWithCheat = baseDist + 1 + distFromE[nx][ny];
//                        int saving = normalDist - routeWithCheat;
//                        if (saving >= 50) {
//                            cheats.put(saving, cheats.getOrDefault(saving, 0) + 1);
//                        }
//                    }
//                }
//
//                // 2-step cheat
//                for (int[] dir1 : directions) {
//                    int ix = x + dir1[0], iy = y + dir1[1];
//                    if (isInBounds(ix, iy)) {
//                        for (int[] dir2 : directions) {
//                            int fx = ix + dir2[0], fy = iy + dir2[1];
//                            if (isInBounds(fx, fy) && isTrack(grid[fx][fy]) && distFromE[fx][fy] != Integer.MAX_VALUE) {
//                                int routeWithCheat = baseDist + 2 + distFromE[fx][fy];
//                                int saving = normalDist - routeWithCheat;
//                                if (saving >= 50) {
//                                    cheats.put(saving, cheats.getOrDefault(saving, 0) + 1);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return cheats;
//    }
//
//    private static Map<Integer, Integer> getStrings2(int[][] distFromS, int[][] distFromE, int normalDist) {
//        List<int[]> trackCellsFromS = new ArrayList<>();
//        for (int x = 0; x < rows; x++) {
//            for (int y = 0; y < cols; y++) {
//                if (distFromS[x][y] != Integer.MAX_VALUE && isTrack(grid[x][y])) {
//                    trackCellsFromS.add(new int[]{x, y});
//                }
//            }
//        }
//
//        Map<Integer, Integer> cheats = new HashMap<>();
//
//        for (int[] cell : trackCellsFromS) {
//            int sx = cell[0], sy = cell[1];
//
//            int[][] distIgnoreWalls = new int[rows][cols];
//            for (int[] row : distIgnoreWalls) {
//                Arrays.fill(row, Integer.MAX_VALUE);
//            }
//
//            distIgnoreWalls[sx][sy] = 0;
//            Queue<int[]> queue = new LinkedList<>();
//            queue.add(new int[]{sx, sy});
//
//            while (!queue.isEmpty()) {
//                int[] current = queue.poll();
//                int x = current[0], y = current[1];
//                int d = distIgnoreWalls[x][y];
//
//                if (d == 20) {
//                    continue;
//                }
//
//                for (int[] dir : directions) {
//                    int nx = x + dir[0], ny = y + dir[1];
//                    if (isInBounds(nx, ny) && distIgnoreWalls[nx][ny] > d + 1) {
//                        distIgnoreWalls[nx][ny] = d + 1;
//                        queue.add(new int[]{nx, ny});
//                    }
//                }
//            }
//
//            int baseDist = distFromS[sx][sy];
//            for (int fx = 0; fx < rows; fx++) {
//                for (int fy = 0; fy < cols; fy++) {
//                    int d = distIgnoreWalls[fx][fy];
//                    if (d != Integer.MAX_VALUE && d >= 1 && d <= 20 && isTrack(grid[fx][fy])) {
//                        if (distFromE[fx][fy] != Integer.MAX_VALUE) {
//                            int routeWithCheat = baseDist + d + distFromE[fx][fy];
//                            int saving = normalDist - routeWithCheat;
//                            if (saving >= 50) {
//                                cheats.put(saving, cheats.getOrDefault(saving, 0) + 1);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return cheats;
//    }
//
//    static boolean isInBounds(int r, int c) {
//        return r >= 0 && r < rows && c >= 0 && c < cols;
//    }
//
//    static boolean isTrack(char ch) {
//        return ch == '.' || ch == 'S' || ch == 'E';
//    }
//
//    static int[][] bfs(int[] start) {
//        int[][] dist = new int[rows][cols];
//        for (int[] row : dist) {
//            Arrays.fill(row, Integer.MAX_VALUE);
//        }
//
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(start);
//        dist[start[0]][start[1]] = 0;
//
//        while (!queue.isEmpty()) {
//            int[] current = queue.poll();
//            int x = current[0], y = current[1];
//
//            for (int[] dir : directions) {
//                int nx = x + dir[0], ny = y + dir[1];
//                if (isInBounds(nx, ny) && isTrack(grid[nx][ny]) && dist[nx][ny] > dist[x][y] + 1) {
//                    dist[nx][ny] = dist[x][y] + 1;
//                    queue.add(new int[]{nx, ny});
//                }
//            }
//        }
//
//        return dist;
//    }
//}