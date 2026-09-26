package topic21_custom_exceptions;

public class Wallet {

    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    // 'throws' is required for the checked exception, optional for the unchecked one
    public void pay(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(amount - balance);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
