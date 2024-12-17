package day_017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static List<Integer> extractIntegers(String text) {
        List<Integer> integers = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            integers.add(Integer.parseInt(matcher.group()));
        }
        return integers;
    }

    public static Tuple parseInput(String filePath) throws IOException {
        String rawText = new String(Files.readAllBytes(Paths.get(filePath))).trim();
        String[] parts = rawText.split("\n\n");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input format");
        }

        List<Integer> registerValues = extractIntegers(parts[0]);

        String[] rawProgram = parts[1].split(":")[1].trim().split(",");
        List<Integer> programInstructions = new ArrayList<>();
        for (String s : rawProgram) {
            programInstructions.add(Integer.parseInt(s));
        }

        return new Tuple(registerValues, programInstructions);
    }

    public static int getOperandValue(int registerA, int registerB, int registerC, int operand) {
        if (operand < 4) {
            return operand;
        }
        switch (operand) {
            case 4:
                return registerA;
            case 5:
                return registerB;
            case 6:
                return registerC;
            default:
                return -1;
        }
    }

    public static Result executeInstruction(int registerA, int registerB, int registerC, int instructionPointer, List<Integer> program) {
        int opcode = program.get(instructionPointer);
        int operand = program.get(instructionPointer + 1);
        int operandValue = getOperandValue(registerA, registerB, registerC, operand);

        switch (opcode) {
            case 0:
                return new Result(null, registerA / (int) Math.pow(2, operandValue), registerB, registerC, instructionPointer + 2);
            case 1:
                return new Result(null, registerA, registerB ^ operand, registerC, instructionPointer + 2);
            case 2:
                return new Result(null, registerA, operandValue % 8, registerC, instructionPointer + 2);
            case 3:
                return new Result(null, registerA, registerB, registerC, registerA != 0 ? operand : instructionPointer + 2);
            case 4:
                return new Result(null, registerA, registerB ^ registerC, registerC, instructionPointer + 2);
            case 5:
                return new Result(operandValue % 8, registerA, registerB, registerC, instructionPointer + 2);
            case 6:
                return new Result(null, registerA, registerA / (int) Math.pow(2, operandValue), registerC, instructionPointer + 2);
            case 7:
                return new Result(null, registerA, registerB, registerA / (int) Math.pow(2, operandValue), instructionPointer + 2);
            default:
                return null;
        }
    }

    public static List<Integer> runProgram(int registerA, int registerB, int registerC, List<Integer> program) {
        int instructionPointer = 0;
        List<Integer> outputValues = new ArrayList<>();

        while (instructionPointer < program.size() - 1) {
            Result result = executeInstruction(registerA, registerB, registerC, instructionPointer, program);
            if (result.output != null) {
                outputValues.add(result.output);
            }
            registerA = result.registerA;
            registerB = result.registerB;
            registerC = result.registerC;
            instructionPointer = result.instructionPointer;
        }

        return outputValues;
    }

    public static Integer findBestInput(List<Integer> program, int cursor, int currentValue) {
        for (int candidate = 0; candidate < 8; candidate++) {
            if (runProgram(currentValue * 8 + candidate, 0, 0, program).equals(program.subList(cursor, program.size()))) {
                if (cursor == 0) {
                    return currentValue * 8 + candidate;
                }
                Integer result = findBestInput(program, cursor - 1, currentValue * 8 + candidate);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Tuple input = parseInput("input_list/day_017_puzzle_input.txt");
        List<Integer> registers = input.registerValues;
        List<Integer> program = input.programInstructions;

        int registerA = registers.get(0);
        int registerB = registers.get(1);
        int registerC = registers.get(2);

        // Part 1: Print program output
        System.out.println("Part 1: " + String.join(",", runProgram(registerA, registerB, registerC, program).toString()));

        // Part 2: Find best input
        Integer bestInput = findBestInput(program, program.size() - 1, 0);
        System.out.println("Part 2: " + bestInput);
    }
}

class Tuple {
    List<Integer> registerValues;
    List<Integer> programInstructions;

    public Tuple(List<Integer> registerValues, List<Integer> programInstructions) {
        this.registerValues = registerValues;
        this.programInstructions = programInstructions;
    }
}

class Result {
    Integer output;
    int registerA;
    int registerB;
    int registerC;
    int instructionPointer;

    public Result(Integer output, int registerA, int registerB, int registerC, int instructionPointer) {
        this.output = output;
        this.registerA = registerA;
        this.registerB = registerB;
        this.registerC = registerC;
        this.instructionPointer = instructionPointer;
    }
}
