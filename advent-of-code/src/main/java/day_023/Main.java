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
     * @param graph - the graph
     * @return a set of all triangles
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
     * @param graph - the graph
     * @return the largest clique
     */
    private static Set<String> findLargestClique(Map<String, Set<String>> graph) {
        Set<String> largestClique = new HashSet<>();
        for (String node : graph.keySet()) {
            Set<String> clique = new HashSet<>();
            clique.add(node);
            findClique(graph, node, clique);
        }
        return largestClique;
    }

    private static void findClique(Map<String, Set<String>> graph, String startNode, Set<String> largestClique) {
        Stack<NodeState> stack = new Stack<>();
        stack.push(new NodeState(startNode, new HashSet<>(Set.of(startNode)), new HashSet<>()));

        while (!stack.isEmpty()) {
            NodeState currentState = stack.pop();
            String currentNode = currentState.node;
            Set<String> currentClique = currentState.currentClique;
            Set<String> visited = currentState.visited;

            visited.add(currentNode);

            for (String neighbor : graph.get(currentNode)) {
                if (!visited.contains(neighbor) && graph.get(neighbor).containsAll(currentClique)) {
                    Set<String> newClique = new HashSet<>(currentClique);
                    newClique.add(neighbor);
                    stack.push(new NodeState(neighbor, newClique, new HashSet<>(visited)));
                }
            }

            if (currentClique.size() > largestClique.size()) {
                largestClique.clear();
                largestClique.addAll(currentClique);
            }
        }
    }

    private static String generatePassword(Set<String> clique) {
        List<String> sortedNodes = new ArrayList<>(clique);
        Collections.sort(sortedNodes);
        return String.join(",", sortedNodes);
    }

    public static class NodeState {
        String node;
        Set<String> currentClique;
        Set<String> visited;

        public NodeState(String node, Set<String> currentClique, Set<String> visited) {
            this.node = node;
            this.currentClique = currentClique;
            this.visited = visited;
        }
    }


}


/*
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NetworkTriangles {

    public static void main(String[] args) throws IOException {
        solve();
        findLargestClique();
    }

    public static void solve() throws IOException {
        // 1. Read input and build adjacency list
        Map<String, Set<String>> adjacency = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("\\\\vmware-host\\Shared Folders\\C\\advent-of-code-002\\input-files\\2024\\2024-023\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("-");
                String a = parts[0];
                String b = parts[1];

                adjacency.computeIfAbsent(a, k -> new HashSet<>()).add(b);
                adjacency.computeIfAbsent(b, k -> new HashSet<>()).add(a);
            }
        }

        // 2. Find all triangles (sets of 3 computers that are all interconnected)
        Set<Set<String>> triangles = new HashSet<>();

        for (String n : adjacency.keySet()) {
            List<String> neighbors = new ArrayList<>(adjacency.get(n));
            Collections.sort(neighbors);
            for (int i = 0; i < neighbors.size(); i++) {
                for (int j = i + 1; j < neighbors.size(); j++) {
                    String x = neighbors.get(i);
                    String y = neighbors.get(j);
                    if (adjacency.get(x).contains(y)) {
                        Set<String> triangle = new HashSet<>(Arrays.asList(n, x, y));
                        triangles.add(triangle);
                    }
                }
            }
        }

        // 3. Filter those triangles so at least one name starts with 't'
        List<Set<String>> trianglesWithT = triangles.stream()
            .filter(tri -> tri.stream().anyMatch(computer -> computer.startsWith("t")))
            .collect(Collectors.toList());

        // 4. Print results
        System.out.println("Total number of triangles: " + triangles.size());
        System.out.println("Number of triangles containing a computer starting with 't': " + trianglesWithT.size());

        // Uncomment to print all triangles and triangles with 't'
        // System.out.println("All triangles:");
        // for (Set<String> tri : triangles) {
        //     System.out.println(String.join(",", tri));
        // }
        //
        // System.out.println("Triangles with a 't':");
        // for (Set<String> tri : trianglesWithT) {
        //     System.out.println(String.join(",", tri));
        // }
    }

    public static void bronKerbosch(Set<String> R, Set<String> P, Set<String> X, Map<String, Set<String>> adjacency, List<Set<String>> cliques) {
        if (P.isEmpty() && X.isEmpty()) {
            cliques.add(new HashSet<>(R));
        } else {
            String pivot = P.stream().findAny().orElse(null);
            Set<String> nonNeighbors = new HashSet<>(P);
            if (pivot != null) {
                nonNeighbors.removeAll(adjacency.getOrDefault(pivot, Collections.emptySet()));
            }

            for (String v : new HashSet<>(nonNeighbors)) {
                R.add(v);
                Set<String> newP = new HashSet<>(P);
                newP.retainAll(adjacency.getOrDefault(v, Collections.emptySet()));
                Set<String> newX = new HashSet<>(X);
                newX.retainAll(adjacency.getOrDefault(v, Collections.emptySet()));

                bronKerbosch(R, newP, newX, adjacency, cliques);

                R.remove(v);
                P.remove(v);
                X.add(v);
            }
        }
    }

    public static void findLargestClique() throws IOException {
        Map<String, Set<String>> adjacency = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("\\\\vmware-host\\Shared Folders\\C\\advent-of-code-002\\input-files\\2024\\2024-023\\input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("-");
                String a = parts[0];
                String b = parts[1];

                adjacency.computeIfAbsent(a, k -> new HashSet<>()).add(b);
                adjacency.computeIfAbsent(b, k -> new HashSet<>()).add(a);
            }
        }

        List<Set<String>> cliques = new ArrayList<>();
        bronKerbosch(new HashSet<>(), new HashSet<>(adjacency.keySet()), new HashSet<>(), adjacency, cliques);

        Set<String> largestClique = cliques.stream()
            .max(Comparator.comparingInt(Set::size))
            .orElse(Collections.emptySet());

        String password = String.join(",", new TreeSet<>(largestClique));
        System.out.println("Password to get into the LAN party: " + password);
    }
}
 */