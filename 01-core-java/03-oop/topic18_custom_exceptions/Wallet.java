package topic18_custom_exceptions;

// A simple wallet (think Paytm wallet) that uses our two custom exceptions
public class Wallet {

    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    // 'throws' MUST be written for the checked exception. For the unchecked one it is optional, so we skip it
    public void pay(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);                   // bad input
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(amount - balance);   // not enough money - tell them how much is short
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
