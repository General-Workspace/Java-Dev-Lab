package while_loops;

public class Main {
    public static void main(String[] args) {
        WhileLoopChallenge solution = new WhileLoopChallenge();
        solution.printEvenNumbers(5, 20);

        // Sum Digits Challenge
        SumDigitsChallenge sum = new SumDigitsChallenge();
        var output = sum.sumDigits(1234);
        System.out.println("Sum of digits: " + output);

        var output2 = sum.sumDigitsUsingString(125);
        System.out.println("Sum of digits: " + output2);
    }
}
