public abstract class Transaction {
    protected double amount;
    protected int originatingId;
    protected int resultingId;
    protected String transactionReason;

    public Transaction(double amount, int originatingId, int resultingId, String transactionReason) {
        this.amount = amount;
        this.originatingId = originatingId;
        this.resultingId = resultingId;
        this.transactionReason = transactionReason;
    }

    public abstract void executeTransaction(Bank bank);
}