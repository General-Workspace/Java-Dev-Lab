package classes_challenge;

public class BankAccount {
    private String accountNumber;
    private double accountBalance;
    private String customerName;
    private String customerEmail;
    private String phoneNumber;

    public double depositFunds(double amount) {
        this.accountBalance += amount;
        return this.accountBalance;
    }

    public String withdrawFunds(double amount) {
        if (this.accountBalance < 0) {
            return "Insufficient funds. You have $%s.".formatted(this.accountBalance);
        } else if (this.accountBalance < amount) {
            return "Insufficient funds. You have $%s.".formatted(this.accountBalance);
        }

        this.accountBalance = this.accountBalance - amount;

        return "Success! $%s. New balance: $%s".formatted(amount, this.accountBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        String[] nameParts = customerName.split(" ");
        String firstName = nameParts[0].substring(0, 1).toUpperCase() + nameParts[0].substring(1);
        String lastName = nameParts[1].substring(0, 1).toUpperCase() + nameParts[1].substring(1);
        customerName = "%s %s".formatted(firstName, lastName);
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail.toLowerCase();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
