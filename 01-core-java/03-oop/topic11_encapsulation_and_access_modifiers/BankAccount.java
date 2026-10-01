package topic11_encapsulation_and_access_modifiers;

/*
 * Topic    : Encapsulation
 * Key idea : Keep the fields private, and allow changes ONLY through methods that check the rules.
 *            Like your bank account: you can't walk into the vault and change your balance.
 *            You go through the counter (deposit / withdraw), and the counter checks everything.
 *            So nobody outside this class can ever do balance = -500.
 * Read     : BankAccount -> EncapsulationDemo
 *
 * Access modifiers (who is allowed to see a field or method):
 *   private    - only this class
 *   (default)  - only classes in the same package (you write no keyword at all)
 *   protected  - same package + child classes (subclasses) in any package
 *   public     - everyone
 */
public class BankAccount {

    private final String owner;     // read-only: there is a getter but no setter
    private double balance;         // can change ONLY through deposit() and withdraw()

    public BankAccount(String owner, double openingBalance) {
        if (openingBalance < 0) {                   // check the rule even while creating the account
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
        if (amount > balance) {                     // you can't take out more than you have
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
