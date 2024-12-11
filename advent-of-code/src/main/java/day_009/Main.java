package day_009;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;


// Part 1 is correct but Part 2 is not correct
public class Main {
    public static void main(String[] args) {
        // Read input from "test.txt"
        String diskMap = readInput("test.txt");
        if (diskMap == null || diskMap.isEmpty()) {
            System.err.println("No valid input found in test.txt");
            return;
        }

        // Parse the disk map into blocks
        List<Integer> disk = parseDiskMap(diskMap);

        // Compact the disk step by step
        List<Integer> compactedDisk = compactDiskStepByStep(disk);

        // Calculate the checksum
        long checksum = calculateChecksum(compactedDisk);
        System.out.println("Filesystem checksum: " + checksum);

        // Part 2
        List<Integer> compactedDiskWithWholeFiles = compactDiskByWholeFiles(disk);
        long checksumWithWholeFiles = calculateChecksum(compactedDiskWithWholeFiles);
        System.out.println("Filesystem checksum with whole files: " + checksumWithWholeFiles);
    }

    // Read the input from the file
    private static String readInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    // Parse the dense disk map format into a list of blocks
    private static List<Integer> parseDiskMap(String diskMap) {
        List<Integer> disk = new ArrayList<>();
        boolean isFile = true; // Start with file blocks
        int fileId = 0;

        for (int i = 0; i < diskMap.length(); i++) {
            int length = Character.getNumericValue(diskMap.charAt(i));

            for (int j = 0; j < length; j++) {
                disk.add(isFile ? fileId : -1); // -1 represents free space
            }

            if (isFile) {
                fileId++; // Increment file ID for the next file
            }

            isFile = !isFile; // Alternate between file and free space
        }

        return disk;
    }

    // Compact the disk step by step, moving files to the leftmost free space
    private static List<Integer> compactDiskStepByStep(List<Integer> disk) {
        List<Integer> compactedDisk = new ArrayList<>(disk);

        boolean changeMade;
        do {
            changeMade = false;
            for (int i = compactedDisk.size() - 1; i >= 0; i--) {
                if (compactedDisk.get(i) != -1) { // If it's a file block
                    // Find the leftmost free space
                    int freeSpaceIndex = findLeftmostFreeSpace(compactedDisk);

                    if (freeSpaceIndex != -1 && freeSpaceIndex < i) {
                        // Move the file block to the free space
                        compactedDisk.set(freeSpaceIndex, compactedDisk.get(i));
                        compactedDisk.set(i, -1);
                        changeMade = true;
                        break; // Restart the process
                    }
                }
            }
        } while (changeMade);

        return compactedDisk;
    }


    // Find the leftmost free space in the disk
    private static int findLeftmostFreeSpace(List<Integer> disk) {
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == -1) {
                return i;
            }
        }
        return -1;
    }


    // Calculate the checksum for the compacted disk
    private static long calculateChecksum(List<Integer> disk) {
        long checksum = 0;

        for (int i = 0; i < disk.size(); i++) {
            int fileId = disk.get(i);
            if (fileId != -1) { // Skip free space
                checksum += (long) i * fileId;
            }
        }

        return checksum;
    }

    /**
     * Part 2:
     */

    private static List<Integer> compactDiskByWholeFiles(List<Integer> disk) {
        List<Integer> compactedDisk = new ArrayList<>(disk);

        // Get the file IDs in decreasing order
        int maxFileId = compactedDisk.stream().max(Integer::compare).orElse(-1);

        // Track which files have already been moved
        Set<Integer> movedFiles = new HashSet<>();

        boolean changeMade;
        do {
            changeMade = false;
            for (int fileId = maxFileId; fileId >= 0; fileId--) {
                if (movedFiles.contains(fileId)) continue;

                // Find all indices where the current file is located
                List<Integer> fileIndices = new ArrayList<>();
                for (int i = 0; i < compactedDisk.size(); i++) {
                    if (compactedDisk.get(i) == fileId) {
                        fileIndices.add(i);
                    }
                }

                if (!fileIndices.isEmpty()) {
                    int fileSize = fileIndices.size();
                    int leftmostIndex = Collections.min(fileIndices);

                    // Find the leftmost possible start for this file to move left
                    int startOfFreeSpace = findLeftmostFreeSpanToLeft(compactedDisk, leftmostIndex, fileSize);

                    if (startOfFreeSpace != -1 && startOfFreeSpace < leftmostIndex) {
                        // Move the file to the free space
                        for (int i = 0; i < fileSize; i++) {
                            compactedDisk.set(startOfFreeSpace + i, fileId);
                            compactedDisk.set(fileIndices.get(i), -1);
                        }

                        changeMade = true;
                        movedFiles.add(fileId);
                        break; // Restart the process to ensure left-most movement
                    }
                }
            }
        } while (changeMade);

        return compactedDisk;
    }

    private static int findLeftmostFreeSpanToLeft(List<Integer> disk, int startSearchIndex, int fileSize) {
        // Search only to the left of the current file
        for (int i = 0; i < startSearchIndex; i++) {
            // Check if we have enough consecutive free spaces
            boolean canFitFile = true;
            for (int j = 0; j < fileSize; j++) {
                if (i + j >= disk.size() || disk.get(i + j) != -1) {
                    canFitFile = false;
                    break;
                }
            }

            if (canFitFile) {
                return i;
            }
        }
        return -1;
    }
}

/*
// Part 1 is not correct but part 2 is correct
package day_9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<Object> parseDiskMap(String diskMap) {
        var lengths = diskMap.chars()
                .mapToObj(c -> Character.getNumericValue(c))
                .toList();

        var disk = new ArrayList<Object>();
        var fileId = 0;

        for (var i = 0; i < lengths.size(); i++) {
            var length = lengths.get(i);
            if (i % 2 == 0) {
                disk.addAll(Collections.nCopies(length, fileId));
                fileId++;
            } else {
                disk.addAll(Collections.nCopies(length, '.'));
            }
        }
        return disk;
    }

    public static List<Object> compactBlocks(List<Object> disk) {
        var deque = new ArrayDeque<>(disk);
        var list = new ArrayList<>(deque); // Convert to List for index operations

        while (list.contains('.')) {
            var gapIndex = list.indexOf('.');
            var rightBlock = deque.pollLast(); // Pop from the end
            while (rightBlock.equals('.')) {
                rightBlock = deque.pollLast(); // Keep removing until non-'.'
            }
            list.set(gapIndex, rightBlock);
        }
        return list;
    }

    public static Map<Integer, FileInfo> getFileInfo(List<Object> disk) {
        var files = new HashMap<Integer, FileInfo>();

        for (var i = 0; i < disk.size(); i++) {
            var block = disk.get(i);
            if (block instanceof Integer fileId) {
                // Use a final local variable for the starting index
                final var startIndex = i;
                files.computeIfAbsent(fileId, id -> new FileInfo(startIndex, 0));
                files.get(fileId).length++;
            }
        }
        return files;
    }

    public static List<Object> compactFiles(List<Object> disk, Map<Integer, FileInfo> files) {
        // Sort keys in reverse order without lambdas
        var sortedKeys = new ArrayList<>(files.keySet());
        sortedKeys.sort(Comparator.reverseOrder());

        for (var fId : sortedKeys) {
            var file = files.get(fId);
            var fileLength = file.length;

            // Find leftmost span of free space
            var segmentStart = findFreeSegment(disk, fileLength, file.start);

            if (segmentStart != -1) {
                for (var pos = file.start; pos < file.start + fileLength; pos++) {
                    disk.set(pos, '.');
                }
                for (var pos = segmentStart; pos < segmentStart + fileLength; pos++) {
                    disk.set(pos, fId);
                }
            }
        }
        return disk;
    }

    private static int findFreeSegment(List<Object> disk, int fileLength, int maxIndex) {
        var count = 0;
        var segmentStart = -1;

        for (var i = 0; i < maxIndex; i++) {
            if (disk.get(i).equals('.')) {
                if (segmentStart == -1) segmentStart = i;
                count++;
                if (count == fileLength) return segmentStart;
            } else {
                count = 0;
                segmentStart = -1;
            }
        }
        return -1;
    }

    public static long calculateChecksum(List<Object> disk) {
        var checksum = 0L;
        for (var i = 0; i < disk.size(); i++) {
            if (disk.get(i) instanceof Integer fileId) {
                checksum += i * fileId;
            }
        }
        return checksum;
    }

    public static void main(String[] args) throws IOException {
        var startTotal = System.nanoTime();

        // Read input from file
        var diskMap = Files.readString(Path.of("day_009_puzzle_input.txt")).strip();

        // Parse the disk map
        var startPart1 = System.nanoTime();
        var disk = parseDiskMap(diskMap);
        var endPart1 = System.nanoTime();

        System.out.printf("Part 1 disk parsing done in %.9f s%n", (endPart1 - startPart1) / 1e9);

        // Compact blocks (Part 1)
        var startCompactBlocks = System.nanoTime();
        var compactedDisk = compactBlocks(new ArrayList<>(disk));
        var part1Checksum = calculateChecksum(compactedDisk);
        var endCompactBlocks = System.nanoTime();

        System.out.printf("Part 1 answer is %d, found in %.9f s%n", part1Checksum, (endCompactBlocks - startCompactBlocks) / 1e9);

        // Gather file information
        var files = getFileInfo(disk);

        // Compact files (Part 2)
        var startPart2 = System.nanoTime();
        var compactedDiskFiles = compactFiles(new ArrayList<>(disk), files);
        var part2Checksum = calculateChecksum(compactedDiskFiles);
        var endPart2 = System.nanoTime();

        System.out.printf("Part 2 answer is %d, found in %.9f s%n", part2Checksum, (endPart2 - startPart2) / 1e9);

        // Total time
        var endTotal = System.nanoTime();
        System.out.printf("Total time was %.9f s%n", (endTotal - startTotal) / 1e9);
    }

    private static class FileInfo {
        int start;
        int length;

        FileInfo(int start, int length) {
            this.start = start;
            this.length = length;
        }
    }
}


// Part 1 is correct but part 2 is incorrect
//package day_9;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        // Read input from "test.txt"
//        String diskMap = readInput("test.txt");
//        if (diskMap == null || diskMap.isEmpty()) {
//            System.err.println("No valid input found in test.txt");
//            return;
//        }
//
//        System.out.println("Input disk map: " + diskMap);
//
//        // Parse the disk map into blocks
//        List<Integer> disk = parseDiskMap(diskMap);
//
//        // Print initial disk state
//        printDiskState(disk, "Initial Disk State");
//
//        // Compact the disk using whole-file movements
//        List<Integer> compactedDisk = compactDiskByWholeFiles(disk);
//
//        // Print final disk state
//        printDiskState(compactedDisk, "Final Compacted Disk State");
//
//        // Calculate and print the checksum
//        long checksum = calculateChecksum(compactedDisk);
//        System.out.println("Filesystem checksum with whole files: " + checksum);
//    }
//
//    // Read the input from the file
//    private static String readInput(String fileName) {
//        StringBuilder sb = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line.trim());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return sb.toString();
//    }
//
//    // Parse the dense disk map format into a list of blocks
//    private static List<Integer> parseDiskMap(String diskMap) {
//        List<Integer> disk = new ArrayList<>();
//        boolean isFile = true; // Start with file blocks
//        int fileId = 0;
//
//        for (int i = 0; i < diskMap.length(); i++) {
//            int length = Character.getNumericValue(diskMap.charAt(i));
//
//            for (int j = 0; j < length; j++) {
//                disk.add(isFile ? fileId : -1); // -1 represents free space
//            }
//
//            if (isFile) {
//                fileId++; // Increment file ID for the next file
//            }
//
//            isFile = !isFile; // Alternate between file and free space
//        }
//
//        return disk;
//    }
//
//    // Print disk state for debugging
//    private static void printDiskState(List<Integer> disk, String message) {
//        System.out.println(message);
//
//        // Count occurrences of each file
//        Map<Integer, Integer> fileCounts = new HashMap<>();
//        for (Integer block : disk) {
//            if (block != -1) {
//                fileCounts.put(block, fileCounts.getOrDefault(block, 0) + 1);
//            }
//        }
//        System.out.println("File distribution: " + fileCounts);
//
//        // Print disk contents
//        System.out.print("Disk contents: ");
//        for (Integer block : disk) {
//            System.out.print(block == -1 ? "." : block);
//        }
//        System.out.println("\n");
//    }
//
//    // Calculate the checksum for the compacted disk
//    private static long calculateChecksum(List<Integer> disk) {
//        long checksum = 0;
//
//        for (int i = 0; i < disk.size(); i++) {
//            int fileId = disk.get(i);
//            if (fileId != -1) { // Skip free space
//                checksum += (long) i * fileId;
//            }
//        }
//
//        return checksum;
//    }
//
//    // Compact the disk using whole-file movements
//    private static List<Integer> compactDiskByWholeFiles(List<Integer> disk) {
//        List<Integer> compactedDisk = new ArrayList<>(disk);
//
//        // Get the file IDs in decreasing order
//        int maxFileId = compactedDisk.stream()
//                .filter(id -> id != -1)
//                .max(Integer::compare)
//                .orElse(-1);
//
//        boolean overallChangeMade;
//        do {
//            overallChangeMade = false;
//
//            for (int fileId = maxFileId; fileId >= 0; fileId--) {
//                // Find all indices where the current file is located
//                List<Integer> fileIndices = new ArrayList<>();
//                for (int i = 0; i < compactedDisk.size(); i++) {
//                    if (compactedDisk.get(i) == fileId) {
//                        fileIndices.add(i);
//                    }
//                }
//
//                if (!fileIndices.isEmpty()) {
//                    int fileSize = fileIndices.size();
//                    int leftmostIndex = Collections.min(fileIndices);
//
//                    // Find the leftmost possible start for this file to move left
//                    int startOfFreeSpace = findLeftmostFreeSpanToLeft(compactedDisk, leftmostIndex, fileSize);
//
//                    if (startOfFreeSpace != -1 && startOfFreeSpace < leftmostIndex) {
//                        // Move the file to the free space
//                        for (int i = 0; i < fileSize; i++) {
//                            compactedDisk.set(startOfFreeSpace + i, fileId);
//                            compactedDisk.set(fileIndices.get(i), -1);
//                        }
//
//                        System.out.println("Moved file " + fileId + " from " + leftmostIndex + " to " + startOfFreeSpace);
//                        printDiskState(compactedDisk, "After moving file " + fileId);
//
//                        overallChangeMade = true;
//                        break; // Restart the process
//                    }
//                }
//            }
//        } while (overallChangeMade);
//
//        return compactedDisk;
//    }
//
//    // Find the leftmost free space span to fit the file
//    private static int findLeftmostFreeSpanToLeft(List<Integer> disk, int startSearchIndex, int fileSize) {
//        for (int i = 0; i < startSearchIndex; i++) {
//            // Check if there is enough consecutive free space
//            boolean canFitFile = true;
//            for (int j = 0; j < fileSize; j++) {
//                if (i + j >= disk.size() || disk.get(i + j) != -1) {
//                    canFitFile = false;
//                    break;
//                }
//            }
//
//            if (canFitFile) {
//                return i;
//            }
//        }
//        return -1;
//    }
//}
*/