package classes_challenge;

public class Customer {
    private String customerName;
    private double creditLimit;
    private String emailAddress;

    public Customer(String customerName, double creditLimit, String emailAddress) {
        this.customerName = customerName;
        this.creditLimit = creditLimit;
        this.emailAddress = emailAddress;
    }

    public Customer() {
        this("Aniebiet Afia", "aniebiet@email.com");
    }

    public Customer(String customerName, String emailAddress) {
        this(customerName, 120, emailAddress);
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
