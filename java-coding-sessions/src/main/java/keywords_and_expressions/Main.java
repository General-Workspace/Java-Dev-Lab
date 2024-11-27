package keywords_and_expressions;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
// expressions
        double kilometres = (100 * 1.609344);

        int highScore = 50;

        if (highScore > 25) {
            highScore = 1000 + highScore;
        }

        String expectedOutcome = """
                Twinkle twinkle little star
                Up above the world so high
                Like a diamond in the sky.
                """;
        System.out.println(expectedOutcome);
        String[] collections = expectedOutcome.split("\n");
        System.out.println(Arrays.toString(collections));

        int count = 0;
        for (int i = 0; i < collections.length; ++i) {
            count = collections.length;
        }
        System.out.println(count);
    }
}
