import javax.security.auth.login.AccountNotFoundException;

public class Transfer extends Transaction {
    public Transfer(double amount, int originatingId, int resultingId, String transactionReason) {
        super(amount, originatingId, resultingId, transactionReason);
    }

    public void executeTransaction(Bank bank) {
        try {
            Account originAcc = bank.getAccountById(originatingId);
            Account destinationAcc = bank.getAccountById(resultingId);

            double transactionFee = bank.calculateTransactionFee(amount);
            double totalTransferAmount = amount + transactionFee;

            if (originAcc.getAccountBalance() < totalTransferAmount) {
                System.out.println("Not enough funds!");
                return;
            }
            try {
                originAcc.withdraw(totalTransferAmount);
                destinationAcc.deposit(amount);

                bank.addTransactionFee(transactionFee);
                bank.addTransferAmount(amount);
                originAcc.addTransaction(this);
                destinationAcc.addTransaction(this);
            } catch (InsufficientFundsException e) {
                System.out.println(e.getMessage());
            }
           
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString() {
        return "Transfer: $" + amount + " from account " + originatingId + " to account " + resultingId + ", Transaction reason: " + transactionReason;
    }
}
