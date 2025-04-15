package minilambdachallenges;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printWordsLambda = (String words) -> {
            String[] parts = words.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };
        //printWordsLambda.accept("Hello World! This is a test.");

        Consumer<String> printWordsConcise = sentence -> {
            Arrays.asList(sentence.split(" ")).forEach(s -> System.out.println(s));
        };

        //printWordsConcise.accept("Hello World! This is a test.");

        Function<String, String> everySecondChar = source -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < source.length(); i += 2) {
                result.append(source.charAt(i));
            }
            return result.toString();
        };

        System.out.println(everySecondChar.apply("Hello World"));
    }

}
