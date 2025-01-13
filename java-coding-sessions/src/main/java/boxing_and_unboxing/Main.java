package boxing_and_unboxing;


public class Main {
    public static void main(String[] args) {
        Customer bob = new Customer("Bob", 1000.0);
        System.out.println(bob);

        Bank bank = new Bank("Brints Holdings");
        bank.addNewCustomer("James R.", 500.0);
        System.out.println(bank);

        bank.addTransaction("James R.", -10.25);
        bank.addTransaction("james r.", -75.01);
        bank.printStatement("james R.");

        bank.addNewCustomer("bob s.", 25);
        bank.addTransaction("bob s.", 100);
        bank.addTransaction("bob s.", 22.3);
        bank.printStatement("bob s.");
    }
}
