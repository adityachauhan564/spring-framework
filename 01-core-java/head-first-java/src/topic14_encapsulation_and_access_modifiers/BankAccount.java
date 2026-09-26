package topic14_encapsulation_and_access_modifiers;

/*
 * Topic    : Encapsulation
 * Key idea : keep fields private and change them only through methods that
 *            check the rules. Nobody outside can set balance = -500.
 * Read     : BankAccount -> EncapsulationDemo
 *
 * Access modifiers (who can see a member):
 *   private    - this class only
 *   (default)  - this package only (no keyword)
 *   protected  - this package + subclasses anywhere
 *   public     - everyone
 */
public class BankAccount {

    private final String owner;     // read-only: getter, no setter
    private double balance;         // changed only by deposit()/withdraw()

    public BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance can't be negative");
        }
        this.owner = owner;
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds: balance is " + balance);
        }
        balance -= amount;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
}
