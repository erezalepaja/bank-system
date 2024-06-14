import javax.security.auth.login.AccountNotFoundException;

public class Withdraw extends Transaction {
    public Withdraw(double amount, int originatingId, String transactionReason){
        super(amount, originatingId, 0, transactionReason);
    }


    public void executeTransaction(Bank bank){
        try {
            Account account = bank.getAccountById(originatingId);
            double fee = bank.getFlatFee();
            double totalAmount = amount + fee;
        
            if (account.getAccountBalance() < totalAmount) {
                System.out.println("Insufficient funds for withdrawal!");
                return;
            }
            try {
                account.withdraw(totalAmount);
                bank.addTransactionFee(fee);
                account.addTransaction(this);
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
           
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "Withdraw: $" + amount + ", Transaction reason: " + transactionReason;
    }
}
