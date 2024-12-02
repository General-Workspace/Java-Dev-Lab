package day_2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read input from file
        Scanner scanner = new Scanner(new File("day_2_input_list.txt"));
        List<String> reports = new ArrayList<>();

        while (scanner.hasNextLine()) {
            reports.add(scanner.nextLine().trim());
        }
        scanner.close();

        String input = String.join("\n", reports);
        System.out.println("Safe reports: " + safeReports(input));
    }

    public static int safeReports(String input) {
        String[] reports = input.split("\n");
        int safeReports = 0;

        for (String report : reports) {
            String[] levels = report.split(" ");
            if (isSafe(levels)) {
                safeReports++;
            } else {
                boolean canBeSafe = false;
                for (int i = 0; i < levels.length; i++) {
                    String[] newLevels = new String[levels.length - 1];
                    int index = 0;
                    for (int j = 0; j < levels.length; j++) {
                        if (j != i) {
                            newLevels[index] = levels[j];
                            index++;
                        }
                    }
                    if (isSafe(newLevels)) {
                        canBeSafe = true;
                        break;
                    }
                }
                if (canBeSafe) {
                    safeReports++;
                }
            }
        }

        return safeReports;
    }

    public static boolean isSafe(String[] levels) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 1; i < levels.length; i++) {
            int diff = Integer.parseInt(levels[i]) - Integer.parseInt(levels[i - 1]);
            if (diff < 1 || diff > 3) {
                increasing = false;
            }
            if (diff > -1 || diff < -3) {
                decreasing = false;
            }
        }
        return increasing || decreasing;
    }
}
