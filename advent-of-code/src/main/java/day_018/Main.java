package day_018;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<int[]> nodes = readInput("input_list/day_018_puzzle_input.txt");
        RAMRun(nodes);
    }

    public static List<int[]> readInput(String filePath) throws IOException {
        List<int[]> nodes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                nodes.add(new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])});
            }
        }
        return nodes;
    }

    public static void RAMRun(List<int[]> nodes) {
        Graph<String, DefaultEdge> grid = createGridGraph(71, 71);

        for (int i = 0; i < nodes.size(); i++) {
            int[] p = nodes.get(i);
            String node = "%d,%d".formatted(p[0], p[1]);
            grid.removeVertex(node);

            if (i == 1023) {
                // Part 1
                long startTime = System.currentTimeMillis();
                DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(grid);
                long endTime = System.currentTimeMillis();
                System.out.printf("Part 1: %d%n", dijkstraAlg.getPath("0,0", "70,70").getLength());
                System.out.printf("Time taken for Part 1: %d ms%n", endTime - startTime);
            } else if (!hasPath(grid, "0,0", "70,70")) {
                // Part 2
                long startTime = System.currentTimeMillis();
                System.out.printf("Part 2: %d,%d%n", p[0], p[1]);
                long endTime = System.currentTimeMillis();
                System.out.printf("Time taken for Part 2: %d ms%n", endTime - startTime);
                break;
            }
        }
    }

    private static Graph<String, DefaultEdge> createGridGraph(int width, int height) {
        Graph<String, DefaultEdge> grid = new SimpleGraph<>(DefaultEdge.class);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String node = "%d,%d".formatted(x, y);
                grid.addVertex(node);
                if (x > 0) {
                    grid.addEdge(node, "%d,%d".formatted(x - 1, y));
                }
                if (y > 0) {
                    grid.addEdge(node, "%d,%d".formatted(x, y - 1));
                }
            }
        }
        return grid;
    }

    private static boolean hasPath(Graph<String, DefaultEdge> grid, String start, String end) {
        DijkstraShortestPath<String, DefaultEdge> dijkstraAlg = new DijkstraShortestPath<>(grid);
        return dijkstraAlg.getPath(start, end) != null;
    }
}