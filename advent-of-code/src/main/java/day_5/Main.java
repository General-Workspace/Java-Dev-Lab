package day_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Read input from file
            List<String> pageOrderRules = new ArrayList<>();
            List<List<Integer>> updates = new ArrayList<>();

            readInputFile("day_5_puzzle_input.txt", pageOrderRules, updates);

            int totalMiddlePageSum = 0;
            for (List<Integer> update : updates) {
                List<Integer> orderedUpdate;
                if (!isUpdateInCorrectOrder(update, pageOrderRules)) {
                    // Reorder the update
                    orderedUpdate = correctlyOrderUpdate(update, pageOrderRules);
                    System.out.println("Incorrectly ordered update: " + update);
                    System.out.println("Correctly ordered update: " + orderedUpdate);

                    // Find and add the middle page
                    int middleIndex = orderedUpdate.size() / 2;
                    totalMiddlePageSum += orderedUpdate.get(middleIndex);
                }
            }

            System.out.println("Sum of middle pages of incorrectly ordered updates after reordering: " + totalMiddlePageSum);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

//        try {
//            // Read input from file
//            List<String> pageOrderRules = new ArrayList<>();
//            List<List<Integer>> updates = new ArrayList<>();
//
//            //readInputFile("day_5_puzzle_input.txt", pageOrderRules, updates);
//            readInputFile("test.txt", pageOrderRules, updates);
//
//            int totalMiddlePageSum = 0;
//            for (List<Integer> update : updates) {
//                if (isUpdateInCorrectOrder(update, pageOrderRules)) {
//                    // Find and add the middle page
//                    int middleIndex = update.size() / 2;
//                    totalMiddlePageSum += update.get(middleIndex);
//                    System.out.println("Correctly ordered update: " + update);
//                } else {
//                    System.out.println("Incorrectly ordered update: " + update);
//                }
//            }
//
//            System.out.println("Sum of middle pages of correctly ordered updates: " + totalMiddlePageSum);
//        } catch (IOException e) {
//            System.err.println("Error reading input file: " + e.getMessage());
//        }
    }

    public static void readInputFile(String filename,
                                     List<String> pageOrderRules,
                                     List<List<Integer>> updates) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean rulesSection = true;

            while ((line = br.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    rulesSection = false;
                    continue;
                }

                if (rulesSection) {
                    // Rules section
                    pageOrderRules.add(line.trim());
                } else {
                    // Updates section
                    String[] pageStrings = line.split(",");
                    List<Integer> update = new ArrayList<>();
                    for (String pageStr : pageStrings) {
                        update.add(Integer.parseInt(pageStr.trim()));
                    }
                    updates.add(update);
                }
            }
        }
    }

    public static boolean isUpdateInCorrectOrder(List<Integer> update, List<String> pageOrderRules) {
        // Create a directed graph of page dependencies
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> allPages = new HashSet<>(update);

        // Build graph based on the rules
        for (String rule : pageOrderRules) {
            String[] parts = rule.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);

            // Only consider rules where both pages are in the current update
            if (update.contains(before) && update.contains(after)) {
                graph.computeIfAbsent(before, k -> new HashSet<>()).add(after);
            }
        }

        // Topological sort to verify order
        return isValidOrder(update, graph);
    }

    public static boolean isValidOrder(List<Integer> update, Map<Integer, Set<Integer>> graph) {
        // Check if the order respects all dependency rules
        for (int i = 0; i < update.size(); i++) {
            for (int j = i + 1; j < update.size(); j++) {
                int firstPage = update.get(i);
                int secondPage = update.get(j);

                // Check if there's a rule that would be violated
                if (hasPathInGraph(graph, secondPage, firstPage)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean hasPathInGraph(Map<Integer, Set<Integer>> graph, int start, int end) {
        // DFS to check if there's a path from start to end in the graph
        Set<Integer> visited = new HashSet<>();
        return dfs(graph, start, end, visited);
    }

    private static boolean dfs(Map<Integer, Set<Integer>> graph, int current, int target, Set<Integer> visited) {
        if (current == target) return true;

        visited.add(current);

        // Check all neighbors
        Set<Integer> neighbors = graph.getOrDefault(current, new HashSet<>());
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (dfs(graph, neighbor, target, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Part 2
    private static List<Integer> topologicalSort(List<Integer> update, Map<Integer, Set<Integer>> graph) {
        // Create a map to track in-degree (number of incoming edges)
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int page : update) {
            inDegree.put(page, 0);
        }

        // Count in-degrees
        for (Set<Integer> deps : graph.values()) {
            for (int dep : deps) {
                inDegree.put(dep, inDegree.getOrDefault(dep, 0) + 1);
            }
        }

        // Queue for pages with no incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int page : update) {
            if (inDegree.get(page) == 0) {
                queue.offer(page);
            }
        }

        // Result list to store sorted order
        List<Integer> sortedOrder = new ArrayList<>();

        while (!queue.isEmpty()) {
            int currentPage = queue.poll();
            sortedOrder.add(currentPage);

            // Reduce in-degree for neighbors
            if (graph.containsKey(currentPage)) {
                for (int neighbor : graph.get(currentPage)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        // If we couldn't sort all pages, add remaining pages
        Set<Integer> sortedPages = new HashSet<>(sortedOrder);
        for (int page : update) {
            if (!sortedPages.contains(page)) {
                sortedOrder.add(page);
            }
        }

        return sortedOrder;
    }

    public static List<Integer> correctlyOrderUpdate(List<Integer> update, List<String> pageOrderRules) {
        // Create a set of initial pages
        Set<Integer> allPages = new HashSet<>(update);

        // Create a directed graph of page dependencies
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        // Build graph based on the rules for pages in this update
        for (String rule : pageOrderRules) {
            String[] parts = rule.split("\\|");
            int before = Integer.parseInt(parts[0]);
            int after = Integer.parseInt(parts[1]);

            // Only consider rules where both pages are in the current update
            if (update.contains(before) && update.contains(after)) {
                graph.computeIfAbsent(before, k -> new HashSet<>()).add(after);
            }
        }

        // Topological sort
        return topologicalSort(update, graph);
    }

}