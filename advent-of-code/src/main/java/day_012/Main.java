package day_012;

import java.io.*;
import java.util.*;

public class Main {

    private static class Region {
        int area;
        int perimeter;
        int sides;

        Region(int area, int perimeter, int sides) {
            this.area = area;
            this.perimeter = perimeter;
            this.sides = sides;
        }
    }

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

    public static void main(String[] args) {
        try {
            char[][] gardenMap = readGardenMap("input_list/day_012_puzzle_input.txt");
            var totalPrice = calculateTotalPrice(gardenMap);
            var grid = new HashMap<Complex, Character>();
            for (int i = 0; i < gardenMap.length; i++) {
                for (int j = 0; j < gardenMap[i].length; j++) {
                    grid.put(new Complex(i, j), gardenMap[i][j]);
                }
            }
            var totalPriceUsingSides = calculateTotalPriceUsingSides(grid);

            System.out.printf("Total Price: %d%n", totalPrice);
            System.out.printf("Total Price using sides: %d%n", totalPriceUsingSides);

        } catch (IOException e) {
            System.err.printf("Error reading the file: %s%n", e.getMessage());
        }
    }

    private static char[][] readGardenMap(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        char[][] map = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            map[i] = lines.get(i).toCharArray();
        }
        return map;
    }

    private static int calculateTotalPrice(char[][] gardenMap) {
        int totalPrice = 0;
        int rows = gardenMap.length;
        int cols = gardenMap[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    char plantType = gardenMap[i][j];
                    Region region = calculateRegion(gardenMap, visited, i, j, plantType);
                    //totalPrice += region.area * region.perimeter;
                    int price = region.area * region.perimeter;
                    totalPrice += price;
                    System.out.printf("A region of %c plants with price %d * %d = %d.%n", plantType, region.area, region.perimeter, price);
                }
            }
        }

        return totalPrice;
    }

    private static Region calculateRegion(char[][] map, boolean[][] visited, int startRow, int startCol, char plantType) {
        int area = 0;
        int perimeter = 0;
        int rows = map.length;
        int cols = map[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            area++;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || map[newRow][newCol] != plantType) {
                    // Out of bounds or different plant type, count as perimeter
                    perimeter++;
                } else if (!visited[newRow][newCol]) {
                    // Same plant type and not visited
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return new Region(area, perimeter, 0); // Sides not used here
    }

    /**
     * Part 2
     */

    static Set<Complex> floodFill(Map<Complex, Character> grid, Complex start) {
        Set<Complex> region = new HashSet<>();
        char symbol = grid.get(start);
        Queue<Complex> queue = new LinkedList<>();

        region.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Complex pos = queue.poll();

            for (Complex d : Arrays.asList(
                    new Complex(1, 0), new Complex(-1, 0),
                    new Complex(0, 1), new Complex(0, -1))) {
                Complex newPos = pos.add(d);

                if (grid.containsKey(newPos) &&
                        !region.contains(newPos) &&
                        grid.get(newPos) == symbol) {
                    region.add(newPos);
                    queue.add(newPos);
                }
            }
        }
        return region;
    }

    static int getArea(Set<Complex> region) {
        return region.size();
    }

    static int getSideCount(Set<Complex> region) {
        Set<String> perimeterEdges = new HashSet<>();

        for (Complex pos : region) {
            for (Complex direction : Arrays.asList(
                    new Complex(1, 0), new Complex(-1, 0),
                    new Complex(0, 1), new Complex(0, -1))) {
                Complex newPos = pos.add(direction);
                if (!region.contains(newPos)) {
                    perimeterEdges.add(pos + "|" + direction);
                }
            }
        }

        int distinctSides = 0;

        while (!perimeterEdges.isEmpty()) {
            Iterator<String> iterator = perimeterEdges.iterator();
            String edge = iterator.next();
            iterator.remove();
            distinctSides++;

            String[] parts = edge.split("\\|");
            Complex pos = Complex.fromString(parts[0]);
            Complex direction = Complex.fromString(parts[1]);

            Complex nextPos = pos.add(direction.multiply(new Complex(0, 1)));
            while (perimeterEdges.remove(nextPos + "|" + direction)) {
                nextPos = nextPos.add(direction.multiply(new Complex(0, 1)));
            }

            nextPos = pos.add(direction.multiply(new Complex(0, -1)));
            while (perimeterEdges.remove(nextPos + "|" + direction)) {
                nextPos = nextPos.add(direction.multiply(new Complex(0, -1)));
            }
        }

        return distinctSides;
    }

    static int calculateTotalPriceUsingSides(Map<Complex, Character> grid) {
        List<Map.Entry<Character, Set<Complex>>> regions = new ArrayList<>();
        Set<Complex> unexplored = new HashSet<>(grid.keySet());

        while (!unexplored.isEmpty()) {
            Iterator<Complex> iterator = unexplored.iterator();
            Complex start = iterator.next();
            iterator.remove();

            Set<Complex> region = floodFill(grid, start);
            unexplored.removeAll(region);
            regions.add(Map.entry(grid.get(start), region));
        }

        int price = 0;
        for (Map.Entry<Character, Set<Complex>> region : regions) {
            int area = getArea(region.getValue());
            int perimeter = getSideCount(region.getValue());
            price += area * perimeter;
        }

        return price;
    }

    static class Complex {
        int real, imag;

        Complex(int real, int imag) {
            this.real = real;
            this.imag = imag;
        }

        Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imag + other.imag);
        }

        Complex multiply(Complex other) {
            return new Complex(
                    this.real * other.real - this.imag * other.imag,
                    this.real * other.imag + this.imag * other.real);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Complex complex = (Complex) obj;
            return real == complex.real && imag == complex.imag;
        }

        @Override
        public int hashCode() {
            return Objects.hash(real, imag);
        }

        @Override
        public String toString() {
            return real + "+" + imag + "i";
        }

        static Complex fromString(String str) {
            String[] parts = str.split("\\+");
            int real = Integer.parseInt(parts[0]);
            int imag = Integer.parseInt(parts[1].replace("i", ""));
            return new Complex(real, imag);
        }
    }

}
