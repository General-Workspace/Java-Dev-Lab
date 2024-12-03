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

    }
}
