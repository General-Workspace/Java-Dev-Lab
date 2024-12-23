package day_023;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] ignoredArgs) throws IOException {
        List<String> lines = readInput("input_list/day_023_puzzle_input.txt");
        Map<String, Set<String>> graph = createGraph(lines);

        // Part 1
        var start = System.currentTimeMillis();
        Set<Set<String>> triangles = findTriangles(graph);
        long count = countTrianglesWithNodeStartingWithT(triangles);
        var end = System.currentTimeMillis();
        System.out.printf("Time taken to find triangles: %d ms%n", end - start);
        System.out.printf("Number of triangles with a node starting with 't': %d%n", count);

        // Part 2
        var start2 = System.currentTimeMillis();
        Set<String> largestClique = findLargestClique(graph);
        String password = generatePassword(largestClique);
        var end2 = System.currentTimeMillis();
        System.out.printf("Time taken to find the largest clique: %d ms%n", end2 - start2);
        System.out.printf("Password to get into the LAN party: %s%n", password);
    }

    private static List<String> readInput(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static Map<String, Set<String>> createGraph(List<String> lines) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split("-");
            String a = parts[0];
            String b = parts[1];
            graph.computeIfAbsent(a, k -> new HashSet<>()).add(b);
            graph.computeIfAbsent(b, k -> new HashSet<>()).add(a);
        }
        return graph;
    }

    /**
     * Part 1 - Find all triangles in the graph
     * @param graph
     * @return
     */
    private static Set<Set<String>> findTriangles(Map<String, Set<String>> graph) {
        Set<Set<String>> triangles = new HashSet<>();
        for (String node : graph.keySet()) {
            for (String neighbor1 : graph.get(node)) {
                for (String neighbor2 : graph.get(node)) {
                    if (!neighbor1.equals(neighbor2) && graph.get(neighbor1).contains(neighbor2)) {
                        Set<String> triangle = new TreeSet<>(Arrays.asList(node, neighbor1, neighbor2));
                        triangles.add(triangle);
                    }
                }
            }
        }
        return triangles;
    }

    private static long countTrianglesWithNodeStartingWithT(Set<Set<String>> triangles) {
        return triangles.stream()
                .filter(triangle -> triangle.stream().anyMatch(node -> node.startsWith("t")))
                .count();
    }


    /**
     * Part 2 - Find the largest clique in the graph
     * @param graph
     * @return
     */
    private static Set<String> findLargestClique(Map<String, Set<String>> graph) {
        Set<String> largestClique = new HashSet<>();
        for (String node : graph.keySet()) {
            Set<String> clique = new HashSet<>();
            clique.add(node);
            findClique(graph, node, clique, largestClique);
        }
        return largestClique;
    }

    private static void findClique(Map<String, Set<String>> graph, String node, Set<String> currentClique, Set<String> largestClique) {
        for (String neighbor : graph.get(node)) {
            if (graph.get(neighbor).containsAll(currentClique)) {
                currentClique.add(neighbor);
                findClique(graph, neighbor, currentClique, largestClique);
                currentClique.remove(neighbor);
            }
        }
        if (currentClique.size() > largestClique.size()) {
            largestClique.clear();
            largestClique.addAll(currentClique);
        }
    }

    private static String generatePassword(Set<String> clique) {
        List<String> sortedNodes = new ArrayList<>(clique);
        Collections.sort(sortedNodes);
        return String.join(",", sortedNodes);
    }
}