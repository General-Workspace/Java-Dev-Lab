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
                if (isUpdateInCorrectOrder(update, pageOrderRules)) {
                    // Find and add the middle page
                    int middleIndex = update.size() / 2;
                    totalMiddlePageSum += update.get(middleIndex);
                    System.out.println("Correctly ordered update: " + update);
                } else {
                    System.out.println("Incorrectly ordered update: " + update);
                }
            }

            System.out.println("Sum of middle pages of correctly ordered updates: " + totalMiddlePageSum);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
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

}

/*
public static int getMiddlePageNumber(List<Integer> pages) {
        return pages.get(pages.size() / 2);
    }

    public static int getSumOfMiddlePageNumbers(List<List<Integer>> pages) {
        int sum = 0;
        for (List<Integer> page : pages) {
            sum += getMiddlePageNumber(page);
        }
        return sum;
    }

    public static Map<Integer, Set<Integer>> getRules(List<String> rules) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (String rule : rules) {
            String[] split = rule.split("\\|");
            int key = Integer.parseInt(split[0]);
            int value = Integer.parseInt(split[1]);
            if (map.containsKey(key)) {
                map.get(key).add(value);
            } else {
                Set<Integer> set = Set.of(value);
                map.put(key, set);
            }
        }
        return map;
    }

    public static List<Integer> getCorrectlyOrderedUpdates(String update) {
        String[] split = update.split(",");
        List<Integer> pages = new ArrayList<>();
        for (String s : split) {
            pages.add(Integer.parseInt(s));
        }
        return pages;
    }

    public static boolean isCorrectlyOrdered(Map<Integer, Set<Integer>> rules, List<Integer> pages) {
        for (int i = 0; i < pages.size(); i++) {
            int page = pages.get(i);
            if (rules.containsKey(page)) {
                for (int j = i + 1; j < pages.size(); j++) {
                    if (rules.get(page).contains(pages.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
 */