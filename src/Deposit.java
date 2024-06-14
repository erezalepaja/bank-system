import javax.security.auth.login.AccountNotFoundException;

public class Deposit extends Transaction {
    public Deposit(double amount, int resultingId, String transactionReason) {
        super(amount, 0, resultingId, transactionReason);
    }

    public void executeTransaction(Bank bank) {
        try {
            Account account = bank.getAccountById(resultingId);
            if (amount > 0) {
                account.deposit(amount);
                account.addTransaction(this);
            } else {
                System.out.println("Amount to deposit must be greater than zero.");
            }
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "Deposit: $" + amount + ", Transaction reason: " + transactionReason;
    }
}

