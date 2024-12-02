package looping;

public class MiniChallenge {
    public double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate / 100));
    }

    public void miniChallenge() {
        for (double rate = 7.5; rate <= 10; rate += (1.0 / 4.0)) {
            double interestAmount = calculateInterest(100, rate);
            String result = "$100 at " + rate + "% interest = $" + interestAmount;
            System.out.println(result);
        }
    }
}
