package day_014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Robot {
    int x;
    int y;
    int vx;
    int vy;

    public Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public void move(int seconds, int width, int height) {
        x = (x + vx * seconds) % width;
        y = (y + vy * seconds) % height;

        if (x < 0) x += width; // Handle negative wrapping
        if (y < 0) y += height; // Handle negative wrapping
    }

    public int getQuadrant(int width, int height) {
        int midX = width / 2;
        int midY = height / 2;

        if (x == midX || y == midY) return 0; // On middle lines, not in any quadrant
        if (x < midX && y < midY) return 1; // Top-left quadrant
        if (x > midX && y < midY) return 2; // Top-right quadrant
        if (x < midX && y > midY) return 3; // Bottom-left quadrant
        if (x > midX && y > midY) return 4; // Bottom-right quadrant

        return 0;
    }
}

public class Main {

    private static final int WIDTH = 101;
    private static final int HEIGHT = 103;
    private static final int SECONDS = 100;
    private static final String FILE_PATH = "input_list/day_014_puzzle_input.txt";

    public static void main(String[] args) {
        List<Robot> robots = loadRobots(FILE_PATH);
        if (robots == null) return;

        moveRobots(robots, SECONDS);
        displayRobotPositions(robots);

        int safetyFactor = calculateSafetyFactor(robots);
        displaySafetyFactor(safetyFactor);
    }

    private static List<Robot> loadRobots(String filePath) {
        List<Robot> robots = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String[] position = parts[0].substring(2).split(",");
                String[] velocity = parts[1].substring(2).split(",");

                int x = Integer.parseInt(position[0]);
                int y = Integer.parseInt(position[1]);
                int vx = Integer.parseInt(velocity[0]);
                int vy = Integer.parseInt(velocity[1]);

                robots.add(new Robot(x, y, vx, vy));
            }
        } catch (IOException e) {
            System.err.printf("Error reading the file: %s%n", e.getMessage());
            return null;
        }
        return robots;
    }

    private static void moveRobots(List<Robot> robots, int seconds) {
        for (Robot robot : robots) {
            robot.move(seconds, WIDTH, HEIGHT);
        }
    }

    private static void displayRobotPositions(List<Robot> robots) {
        System.out.println("Robots' positions after moving:");
        for (Robot robot : robots) {
            System.out.printf("Position: (%d, %d), Quadrant: %d%n", robot.x, robot.y, robot.getQuadrant(WIDTH, HEIGHT));
        }
    }

    private static int calculateSafetyFactor(List<Robot> robots) {
        int[] quadrantCounts = new int[5];
        for (Robot robot : robots) {
            int quadrant = robot.getQuadrant(WIDTH, HEIGHT);
            if (quadrant > 0) {
                quadrantCounts[quadrant]++;
            }
        }
        System.out.println("Robots in each quadrant:");
        System.out.printf("Top-left: %d%n", quadrantCounts[1]);
        System.out.printf("Top-right: %d%n", quadrantCounts[2]);
        System.out.printf("Bottom-left: %d%n", quadrantCounts[3]);
        System.out.printf("Bottom-right: %d%n", quadrantCounts[4]);
        return quadrantCounts[1] * quadrantCounts[2] * quadrantCounts[3] * quadrantCounts[4];
    }

    private static void displaySafetyFactor(int safetyFactor) {
        System.out.printf("Safety Factor: %d%n", safetyFactor);
    }
}


