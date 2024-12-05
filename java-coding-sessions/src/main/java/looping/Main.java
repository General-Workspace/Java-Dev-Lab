package looping;

public class Main {
    public static void main(String[] args) {
        MiniChallenge response = new MiniChallenge();
        response.miniChallenge();

        System.out.println("******************************************************");
        ThePrimeNumberChallenge primeNumberChallenge = new ThePrimeNumberChallenge();
        int number = 16;
        String primeResponse = primeNumberChallenge.isPrime(number) ? number + " is a prime number." : number + " " + "is not a prime number.";
        System.out.println(primeResponse);

        number = 5;
        primeResponse = primeNumberChallenge.isPrime(number) ? number + " is a prime number." : number + " " + "is not a prime number.";
        System.out.println(primeResponse);

        number = 18;
        primeResponse = primeNumberChallenge.isPrime(number) ? number + " is a prime number." : number + " " + "is not a prime number.";
        System.out.println(primeResponse);

        number = 7;
        primeResponse = primeNumberChallenge.isPrime(number) ? number + " is a prime number." : number + " " + "is not a prime number.";
        System.out.println(primeResponse);

        // Prime number counter
        System.out.println(primeNumberChallenge.primeNumberCounter(12, 37));

        // Sum 3 and 5 Challenge
        System.out.println("******************************************");
        Sum3And5Challenge sum3And5Challenge = new Sum3And5Challenge();
        var sum = sum3And5Challenge.sumNumbers(1, 1000);
        System.out.println(sum);

    }
}
