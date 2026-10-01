package topic11_encapsulation_and_access_modifiers;

/*
 * Run      : java -cp out topic11_encapsulation_and_access_modifiers.EncapsulationDemo
 * Try this : Remove the // from the 'account.balance' line - it won't compile.
 *            That is exactly the point: outside code can't touch the balance directly.
 */
public class EncapsulationDemo {

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Aditya", 1000);

        account.deposit(500);
        account.withdraw(200);
        System.out.println(account.getOwner() + "'s balance: " + account.getBalance());

        // account.balance = -500;   // compile error: balance is private, so it can't be touched from here

        // try to take out more than the balance -> the account says no
        try {
            account.withdraw(5000);
        } catch (IllegalStateException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        // try to deposit a negative amount -> the account says no
        try {
            account.deposit(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }

        System.out.println("Balance is still valid: " + account.getBalance());   // bad requests changed nothing
    }
}
