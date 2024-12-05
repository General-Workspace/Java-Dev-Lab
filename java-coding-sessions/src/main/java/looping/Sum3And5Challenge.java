package looping;

public class Sum3And5Challenge {
    public int sumNumbers(int start, int end) {
        int sum = 0;
        int found = 0;

        for (int i = start; i <= end; ++i) {
            if (i % 3 == 0 && i % 5 == 0) {
                found++;
                sum += i;
                System.out.println("Found a match = " + i);
            }

            if (found == 5) break;;

        }
        return sum;
    }
}
