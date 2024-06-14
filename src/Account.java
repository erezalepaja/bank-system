import java.util.ArrayList;

public class Account {
    private int accountId;
    private String nameOfUser;
    private double accountBalance;
    private ArrayList<Transaction> transactions;

    public Account(int accountId, String nameOfUser, double accountBalance) {
        this.accountId = accountId;
        this.nameOfUser = nameOfUser;
        this.accountBalance = accountBalance;
        this.transactions = new ArrayList<>();
    }

    public int getAccountId() {
        return accountId;
    }

    public String getNameOfUser(){
        return nameOfUser;
    }

    public void setNameOfUser(String name){
        nameOfUser = name;
    }

    public double getAccountBalance(){
        return accountBalance;
    }

    public void setAccountBalance(double balance){
        accountBalance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amountToDeposit) {
        accountBalance += amountToDeposit;
    }

    public void withdraw(double amountToWithdraw) throws InsufficientFundsException {
        if (accountBalance >= amountToWithdraw) {
            accountBalance -= amountToWithdraw;
        } else {
            throw new InsufficientFundsException("Your balance is lower than: " + amountToWithdraw);
        }
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String toString() {
        return "Account ID: " + accountId + ", Name: " + nameOfUser + ", Balance: $" + String.format("%.2f", accountBalance);
    }

}
