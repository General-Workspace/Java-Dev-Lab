package looping;

public class ThePrimeNumberChallenge {
    public boolean isPrime(int wholeNumber) {
        if (wholeNumber <= 1) return false;

        for (int i = 2; i <= Math.sqrt(wholeNumber); i++) {
            if (wholeNumber % i == 0) return false;
        }

//        for (int divisor = 2; divisor < wholeNumber / 2; divisor++) {
//            if (wholeNumber % divisor == 0) {
//                return false;
//            }
//        }

        return true;
    }

    public int primeNumberCounter(int start, int end) {
        int counter = 0;

        if (start > end) {
            System.out.println("Start number " + start + " cannot be greater than " + " the end number " + end);
            return counter;
        }

        for (int i = start; i <= end; i++) {
            if (isPrime(i) && counter < 3) {
                System.out.println("Number " + i + " is a prime number.");
                counter++;
            }
        }

        return counter;
    }
}
