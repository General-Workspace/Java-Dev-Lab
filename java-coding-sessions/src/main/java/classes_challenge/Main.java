package classes_challenge;

public class Main {
    public static void main(String[] args) {
        BankAccount aniebietAccount = new BankAccount();

        aniebietAccount.setAccountNumber("0039002047");
        aniebietAccount.setCustomerName("aniebiet afia");
        aniebietAccount.setCustomerEmail("aniebietafia@gmsil.com");
        aniebietAccount.setPhoneNumber("08027872415");
        aniebietAccount.setAccountBalance(500);

        System.out.printf("Customer Account Name: %s%n", aniebietAccount.getCustomerName());
        System.out.printf("Customer Account Number: %s%n", aniebietAccount.getAccountNumber());

        // Withdraw Funds
        String action = aniebietAccount.withdrawFunds(55.89);
        System.out.println(action);

        System.out.printf("New account balance after withdrawal: %s%n", aniebietAccount.getAccountBalance());
    }
}
