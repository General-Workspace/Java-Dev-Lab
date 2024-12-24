package day_024;

import java.io.*;
import java.util.*;

public class Main {
    private static class Gate {
        String a;
        String b;
        String op;
        String output;

        public Gate(String a, String b, String op, String output) {
            this.a = a;
            this.b = b;
            this.op = op;
            this.output = output;
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, Integer> wires = new HashMap<>();
        List<Gate> gates = parseInput("input_list/test.txt");

        long start = System.currentTimeMillis();
        long part1 = convertToDecimal(wires, gates);
        System.out.println("Part 1: " + part1 + " " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        String part2 = swappedWires(gates);
        System.out.println("Part 2: " + part2 + " " + (System.currentTimeMillis() - start) + "ms");
    }

    private static List<Gate> parseInput(String filepath) throws IOException {
        List<String> wiresRaw = new ArrayList<>();
        List<String> gatesRaw = new ArrayList<>();
        boolean isGates = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    isGates = true;
                    continue;
                }
                if (isGates) {
                    gatesRaw.add(line);
                } else {
                    wiresRaw.add(line);
                }
            }
        }

        Map<String, Integer> wires = new HashMap<>();
        for (String line : wiresRaw) {
            String[] parts = line.split(": ");
            wires.put(parts[0], Integer.parseInt(parts[1]));
        }

        List<Gate> gates = new ArrayList<>();
        for (String line : gatesRaw) {
            String[] parts = line.split(" -> ");
            String[] inputs = parts[0].split(" ");
            Gate gate = new Gate(inputs[0], inputs[1], inputs[2], parts[1]);
            gates.add(gate);

            wires.putIfAbsent(gate.a, null);
            wires.putIfAbsent(gate.b, null);
            wires.putIfAbsent(gate.output, null);
        }

        return gates;
    }

    private static long convertToDecimal(Map<String, Integer> wires, List<Gate> gates) {
        while (true) {
            boolean updated = false;

            for (Gate gate : gates) {
                if (wires.get(gate.a) == null || wires.get(gate.b) == null || wires.get(gate.output) != null) {
                    continue;
                }

                int value1 = wires.get(gate.a);
                int value2 = wires.get(gate.b);
                int result = 0;

                switch (gate.op) {
                    case "AND":
                        result = value1 & value2;
                        break;
                    case "OR":
                        result = value1 | value2;
                        break;
                    case "XOR":
                        result = value1 ^ value2;
                        break;
                    default:
                        throw new IllegalStateException("Unknown operation: " + gate.op);
                }

                wires.put(gate.output, result);
                updated = true;
            }

            if (!updated) {
                break;
            }
        }

        List<Map.Entry<String, Integer>> values = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wires.entrySet()) {
            if (entry.getKey().startsWith("z") && entry.getValue() != null) {
                values.add(entry);
            }
        }

        if (values.isEmpty()) {
            throw new NumberFormatException("Binary number string is empty");
        }

        values.sort((a, b) -> b.getKey().compareTo(a.getKey()));

        StringBuilder binaryNumber = new StringBuilder();
        for (Map.Entry<String, Integer> entry : values) {
            binaryNumber.append(entry.getValue());
        }

        System.out.println("Binary number string: " + binaryNumber.toString());

        return Long.parseLong(binaryNumber.toString(), 2);
    }


    private static String swappedWires(List<Gate> gates) {
        List<String> swapped = new ArrayList<>();
        String c0 = null;

        for (int i = 0; i < 45; i++) {
            String n = String.format("%02d", i);
            String m1 = findGate("x" + n, "y" + n, "XOR", gates);
            String n1 = findGate("x" + n, "y" + n, "AND", gates);
            String r1 = null, z1 = null, c1 = null;

            if (c0 != null) {
                r1 = findGate(c0, m1, "AND", gates);
                if (r1 == null) {
                    String temp = m1;
                    m1 = n1;
                    n1 = temp;
                    swapped.add(m1);
                    swapped.add(n1);
                    r1 = findGate(c0, m1, "AND", gates);
                }
                z1 = findGate(c0, m1, "XOR", gates);
                if (m1.startsWith("z")) {
                    String temp = m1;
                    m1 = z1;
                    z1 = temp;
                    swapped.add(m1);
                    swapped.add(z1);
                }
                if (n1.startsWith("z")) {
                    String temp = n1;
                    n1 = z1;
                    z1 = temp;
                    swapped.add(n1);
                    swapped.add(z1);
                }
                if (r1.startsWith("z")) {
                    String temp = r1;
                    r1 = z1;
                    z1 = temp;
                    swapped.add(r1);
                    swapped.add(z1);
                }
                c1 = findGate(r1, n1, "OR", gates);
            }

            if (c1 != null && c1.startsWith("z") && !c1.equals("z45")) {
                String temp = c1;
                c1 = z1;
                z1 = temp;
                swapped.add(c1);
                swapped.add(z1);
            }

            if (c0 == null) {
                c0 = n1;
            } else {
                c0 = c1;
            }
        }

        Collections.sort(swapped);
        return String.join(",", swapped);
    }

    private static String findGate(String a, String b, String operator, List<Gate> gates) {
        for (Gate gate : gates) {
            if (gate.op.equals(operator) && ((a.equals(gate.a) && b.equals(gate.b)) || (a.equals(gate.b) && b.equals(gate.a)))) {
                return gate.output;
            }
        }
        return null;
    }
}