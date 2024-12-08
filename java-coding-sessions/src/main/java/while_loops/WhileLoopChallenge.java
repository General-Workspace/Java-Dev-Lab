package while_loops;

public class WhileLoopChallenge {
    public boolean isEvenNumber(int num) {
        boolean result = false;
        if (num % 2 == 0) {
            result = true;
        }

        return result;
    }

    public void printEvenNumbers(int start, int end) {
        int totalEvenNumbersFound = 0;
        int totalOddNumbersFound = 0;

        while (start <= end) {
            var even = isEvenNumber(start);
            if (even) {
                totalEvenNumbersFound++;
                System.out.println("Even Number: " + start);
            } else {
                totalOddNumbersFound++;
            }

            if (totalEvenNumbersFound == 5) break;
            start++;
        }

        System.out.println("Total even numbers found: " + totalEvenNumbersFound);
        System.out.println("Total odd numbers found: " + totalOddNumbersFound);
    }
}
