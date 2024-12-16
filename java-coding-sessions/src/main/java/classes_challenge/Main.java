package classes_challenge;

public class Main {
    public static void main(String[] args) {
//        BankAccount aniebietAccount = new BankAccount();
//
//        aniebietAccount.setAccountNumber("0039002047");
//        aniebietAccount.setCustomerName("aniebiet afia");
//        aniebietAccount.setCustomerEmail("aniebietafia@gmsil.com");
//        aniebietAccount.setPhoneNumber("08027872415");
//        aniebietAccount.setAccountBalance(500);
//
//        System.out.printf("Customer Account Name: %s%n", aniebietAccount.getCustomerName());
//        System.out.printf("Customer Account Number: %s%n", aniebietAccount.getAccountNumber());
//
//        // Withdraw Funds
//        String action = aniebietAccount.withdrawFunds(55.89);
//        System.out.println(action);
//
//        System.out.printf("New account balance after withdrawal: %s%n", aniebietAccount.getAccountBalance());


        Customer aniebiet = new Customer();
        System.out.println("Customer name: " + aniebiet.getCustomerName());
        System.out.println("Customer email: " + aniebiet.getEmailAddress());
        System.out.println("Credit Limit: " + aniebiet.getCreditLimit());

        Customer yetunde = new Customer("Yetunde Captain", 99, "yetunde@email.com");
        System.out.println("Customer name: " + yetunde.getCustomerName());
        System.out.println("Customer email: " + yetunde.getEmailAddress());
        System.out.println("Credit Limit: " + yetunde.getCreditLimit());

        Customer mandi = new Customer("Mandi Host", "mandi@email.com");
        System.out.println("Customer name: " + mandi.getCustomerName());
        System.out.println("Customer email: " + mandi.getEmailAddress());
    }
}
