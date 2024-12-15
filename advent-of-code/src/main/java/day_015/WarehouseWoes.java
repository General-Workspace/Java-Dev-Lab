package day_015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WarehouseWoes {

    public static void main(String[] args) throws IOException {
        String[] inputParts = parseInput("input.txt");
        String rawGrid = inputParts[0];
        String rawMoves = inputParts[1];

        List<int[]> moves = rawMoves.replace("\n", "")
                .chars()
                .mapToObj(WarehouseWoes::moveDirection)
                .collect(Collectors.toList());

        Grid grid = createGrid(rawGrid);

        long p1 = partOne(grid.start, grid.grid, moves);
        System.out.println("Part 1: " + p1);

        String scaledText = scaleUpGridText(rawGrid);
        grid = createGrid(scaledText);

        long p2 = partTwo(grid.start, grid.grid, moves);
        System.out.println("Part 2: " + p2);
    }

    public static String[] parseInput(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath))).trim();
        return content.split("\n\n");
    }

    public static Grid createGrid(String rawGrid) {
        Map<Position, Character> grid = new HashMap<>();
        Position start = null;

        String[] lines = rawGrid.split("\n");
        for (int y = 0; y < lines.length; y++) {
            for (int x = 0; x < lines[y].length(); x++) {
                char tile = lines[y].charAt(x);
                grid.put(new Position(x, y), tile);
                if (tile == '@') {
                    start = new Position(x, y);
                }
            }
        }

        return new Grid(grid, start);
    }

    public static String scaleUpGridText(String rawGrid) {
        StringBuilder scaledUp = new StringBuilder();
        for (String line : rawGrid.split("\n")) {
            scaledUp.append(line.replace("O", "[]")
                    .replace(".", "..")
                    .replace("#", "##")
                    .replace("@", "@.")).append("\n");
        }
        return scaledUp.toString().trim();
    }

    public static int[] moveDirection(int symbol) {
        switch (symbol) {
            case '^': return new int[]{0, -1};
            case 'v': return new int[]{0, 1};
            case '<': return new int[]{-1, 0};
            case '>': return new int[]{1, 0};
            default: throw new IllegalArgumentException("Invalid move direction");
        }
    }

    public static Position nextPosition(Position position, int[] move) {
        return new Position(position.x + move[0], position.y + move[1]);
    }

    public static long sumGpsCoordinates(Map<Position, Character> grid) {
        return grid.entrySet().stream()
                .filter(e -> "O[".indexOf(e.getValue()) != -1)
                .mapToLong(e -> 100L * e.getKey().y + e.getKey().x)
                .sum();
    }

    public static long partOne(Position currentPos, Map<Position, Character> grid, List<int[]> moves) {
        for (int[] move : moves) {
            Position newPos = nextPosition(currentPos, move);
            char tile = grid.getOrDefault(newPos, '#');

            if (tile == '#') {
                continue;
            } else if (tile == 'O') {
                Position endBox = newPos;
                while (grid.getOrDefault(endBox, '#') == 'O') {
                    endBox = nextPosition(endBox, move);
                }

                if (grid.getOrDefault(endBox, '#') == '#') {
                    continue;
                } else {
                    grid.put(endBox, 'O');
                    grid.put(newPos, '@');
                    grid.put(currentPos, '.');
                    currentPos = newPos;
                }
            } else if (tile == '.') {
                grid.put(newPos, '@');
                grid.put(currentPos, '.');
                currentPos = newPos;
            }
        }

        return sumGpsCoordinates(grid);
    }

    public static long partTwo(Position currentPos, Map<Position, Character> grid, List<int[]> moves) {
        for (int[] move : moves) {
            Position newPos = nextPosition(currentPos, move);
            char tile = grid.getOrDefault(newPos, '#');

            if (tile == '#') {
                continue;
            } else if (tile == '[' || tile == ']') {
                Set<Position> boxes = new HashSet<>();
                if (canMove(newPos, move, boxes, grid)) {
                    Map<Position, Character> newBoxes = new HashMap<>();
                    for (Position box : boxes) {
                        newBoxes.put(nextPosition(box, move), grid.get(box));
                        grid.put(box, '.');
                    }
                    newBoxes.forEach(grid::put);
                    grid.put(newPos, '@');
                    grid.put(currentPos, '.');
                    currentPos = newPos;
                }
            } else if (tile == '.') {
                grid.put(newPos, '@');
                grid.put(currentPos, '.');
                currentPos = newPos;
            }
        }

        return sumGpsCoordinates(grid);
    }

    public static boolean canMove(Position boxPos, int[] move, Set<Position> boxes, Map<Position, Character> grid) {
        Set<Position> checked = new HashSet<>();
        if (grid.get(boxPos) == '[') {
            checked.add(boxPos);
            checked.add(nextPosition(boxPos, moveDirection('>')));
        } else if (grid.get(boxPos) == ']') {
            checked.add(boxPos);
            checked.add(nextPosition(boxPos, moveDirection('<')));
        }

        Set<Position> newPositions = checked.stream()
                .map(p -> nextPosition(p, move))
                .filter(p -> !checked.contains(p))
                .collect(Collectors.toSet());

        if (newPositions.stream().anyMatch(p -> grid.getOrDefault(p, '#') == '#')) {
            return false;
        }

        boolean valid = newPositions.stream()
                .allMatch(p -> grid.getOrDefault(p, '.') == '.' || canMove(p, move, boxes, grid));

        if (valid) {
            boxes.addAll(checked);
        }

        return valid;
    }

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Grid {
        Map<Position, Character> grid;
        Position start;

        Grid(Map<Position, Character> grid, Position start) {
            this.grid = grid;
            this.start = start;
        }
    }
}
