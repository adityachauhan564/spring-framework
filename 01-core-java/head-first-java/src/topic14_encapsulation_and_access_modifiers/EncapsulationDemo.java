package topic14_encapsulation_and_access_modifiers;

/*
 * Run      : java -cp out topic14_encapsulation_and_access_modifiers.EncapsulationDemo
 * Try this : uncomment the 'account.balance' line - it won't compile. That's the point.
 */
public class EncapsulationDemo {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Aditya", 1000);

        account.deposit(500);
        account.withdraw(200);
        System.out.println(account.getOwner() + "'s balance: " + account.getBalance());

        // account.balance = -500;   // compile error: balance has private access

        try {
            account.withdraw(5000);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        try {
            account.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("Balance is still valid: " + account.getBalance());
    }
}
