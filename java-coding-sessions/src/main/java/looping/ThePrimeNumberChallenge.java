package looping;

public class ThePrimeNumberChallenge {
    public boolean isPrime(int wholeNumber) {
        if (wholeNumber <= 1) return false;

//        for (int i = 2; i <= Math.sqrt(wholeNumber); i++) {
//            if (wholeNumber % i == 0) return false;
//        }

        for (int divisor = 2; divisor < wholeNumber; divisor++) {
            if (wholeNumber % divisor == 0) {
                return false;
            }
        }

        return true;
    }
}
