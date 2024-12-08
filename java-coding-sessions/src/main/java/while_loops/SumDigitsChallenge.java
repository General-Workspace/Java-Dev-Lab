package while_loops;

public class SumDigitsChallenge {
    // solution 1
    public int sumDigitsUsingString(int number) {
        if (number < 0) return -1;
        if (number < 10) return number;

        String numString = String.valueOf(number);
        int sum = 0;
        for (int i = 0; i < numString.length(); ++i) {
            char character = numString.charAt(i);
            int num = Integer.parseInt(String.valueOf(character));
            sum += num;
        }

        return sum;

    }

    // Solution 2
    public int sumDigits(int number) {
        if (number < 0) return -1;

        int sum = 0;
        while (number > 9) {
            sum += number % 10;
            number = number / 10;
        }

        sum += number;

        return sum;

    }
}
