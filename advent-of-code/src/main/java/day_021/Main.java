package day_021;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    // Complex class to simulate complex numbers
    static class Complex {
        double real, imag;

        public Complex(double real, double imag) {
            this.real = real;
            this.imag = imag;
        }

        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imag + other.imag);
        }

        public double manhattanDistance(Complex other) {
            return Math.abs(this.real - other.real) + Math.abs(this.imag - other.imag);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Complex complex = (Complex) o;
            return Double.compare(complex.real, real) == 0 && Double.compare(complex.imag, imag) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(real, imag);
        }

        @Override
        public String toString() {
            return "(" + real + ", " + imag + ")";
        }
    }

    // Utility to measure execution time
    static void performanceProfiler(String methodName, Runnable method) {
        long start = System.nanoTime();
        method.run();
        long elapsed = System.nanoTime() - start;
        System.out.printf("Method %s took: %.5f sec\n", methodName, elapsed / 1e9);
    }

    // Generate keypad mapping
    static Map<Character, Complex> createKeypadMapping(String keypadLayout) {
        Map<Character, Complex> mapping = new HashMap<>();
        String[] rows = keypadLayout.split(",");
        for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
            char[] row = rows[rowIdx].toCharArray();
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                char key = row[colIdx];
                if (key != '_') {
                    mapping.put(key, new Complex(rowIdx, colIdx));
                }
            }
        }
        return mapping;
    }

    // Directions for movement: up, down, left, right
    static final Complex[] directions = {
            new Complex(-1, 0), // Up
            new Complex(1, 0),  // Down
            new Complex(0, -1), // Left
            new Complex(0, 1)   // Right
    };

    // PathState class to track position and path
    static class PathState {
        Complex position;
        List<Complex> path;

        public PathState(Complex position, List<Complex> path) {
            this.position = position;
            this.path = new ArrayList<>(path);
        }
    }

    // Convert path to directions
    static String convertPathToDirections(List<Complex> path) {
        StringBuilder directions = new StringBuilder();
        for (int i = 1; i < path.size(); i++) {
            Complex prev = path.get(i - 1);
            Complex curr = path.get(i);
            Complex diff = new Complex(curr.real - prev.real, curr.imag - prev.imag);

            if (diff.equals(new Complex(-1, 0))) directions.append("U");
            else if (diff.equals(new Complex(1, 0))) directions.append("D");
            else if (diff.equals(new Complex(0, -1))) directions.append("L");
            else if (diff.equals(new Complex(0, 1))) directions.append("R");
        }
        return directions.toString();
    }

    // Find shortest paths
    static List<String> findShortestPaths(Complex startPos, Complex endPos, Map<Character, Complex> keypad) {
        Deque<PathState> stack = new ArrayDeque<>();
        List<String> validPaths = new ArrayList<>();
        Map<Complex, Character> reverseKeypad = keypad.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        stack.push(new PathState(startPos, List.of(startPos)));

        while (!stack.isEmpty()) {
            PathState state = stack.pop();
            Complex currentPos = state.position;
            List<Complex> path = state.path;

            if (currentPos.equals(endPos)) {
                validPaths.add(convertPathToDirections(path));
                continue;
            }

            for (Complex dir : directions) {
                Complex nextPos = currentPos.add(dir);
                if (reverseKeypad.containsKey(nextPos) && !path.contains(nextPos)) {
                    List<Complex> newPath = new ArrayList<>(path);
                    newPath.add(nextPos);
                    stack.push(new PathState(nextPos, newPath));
                }
            }
        }

        return validPaths;
    }

    public static void main(String[] args) {
        // Read input from file
        List<String> inputLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("input_list/day_021_puzzle_input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputLines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return;
        }

        // Process each line from the input
        String keypadLayout = "123,456,789";
        Map<Character, Complex> keypad = createKeypadMapping(keypadLayout);

        for (String line : inputLines) {
            if (line.length() != 4) {
                System.err.println("Invalid input line: " + line);
                continue;
            }

            char startChar = line.charAt(0);
            char endChar = line.charAt(2);

            if (!keypad.containsKey(startChar) || !keypad.containsKey(endChar)) {
                System.err.println("Invalid characters in line: " + line);
                continue;
            }

            Complex startPos = keypad.get(startChar);
            Complex endPos = keypad.get(endChar);

            performanceProfiler("FindShortestPaths", () -> {
                List<String> paths = findShortestPaths(startPos, endPos, keypad);
                System.out.println("Paths from " + startChar + " to " + endChar + ": " + paths);
            });
        }
    }
}
