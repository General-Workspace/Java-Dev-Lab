package day_013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Machine {
        int ax, ay, bx, by, px, py;

        public Machine(int ax, int ay, int bx, int by, int px, int py) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
            this.px = px;
            this.py = py;
        }

        public static void main(String[] args) {
            List<Machine> machines = readInput("input_list/day_013_puzzle_input.txt");

            int totalTokens = 0;
            int prizesWon = 0;

            for (Machine machine : machines) {
                int tokens = machine.calculateMinimumTokens();
                if (tokens != -1) {
                    totalTokens += tokens;
                    prizesWon++;
                }
            }

            System.out.println("Total tokens: " + totalTokens);
            System.out.println("Prizes won: " + prizesWon);

        }

        // Method to read input from file
        public static List<Machine> readInput(String filename) {
            List<Machine> machines = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;

                    int ax = Integer.parseInt(line.split("X\\+")[1].split(",")[0].trim());
                    int ay = Integer.parseInt(line.split("Y\\+")[1].split("\\)")[0].trim());

                    line = br.readLine();
                    int bx = Integer.parseInt(line.split("X\\+")[1].split(",")[0].trim());
                    int by = Integer.parseInt(line.split("Y\\+")[1].split("\\)")[0].trim());

                    line = br.readLine();
                    int px = Integer.parseInt(line.split("X=")[1].split(",")[0].trim());
                    int py = Integer.parseInt(line.split("Y=")[1].trim());

                    machines.add(new Machine(ax, ay, bx, by, px, py));
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }

            return machines;
        }

        public int calculateMinimumTokens() {
            int maxPresses = 100;
            int minTokens = Integer.MAX_VALUE;

            for (int aPresses = 0; aPresses <= maxPresses; aPresses++) {
                for (int bPresses = 0; bPresses <= maxPresses; bPresses++) {
                    int x = aPresses * ax + bPresses * bx;
                    int y = aPresses * ay + bPresses * by;
                    if (x == px && y == py) {
                        int tokens = aPresses * 3 + bPresses;
                        minTokens = Math.min(minTokens, tokens);
                    }
                }
            }
            return minTokens == Integer.MAX_VALUE ? -1 : minTokens;
        }
    }

    /**
     * Part 2: Will update this later. Successfully solved using Python.
     *
     */

}
