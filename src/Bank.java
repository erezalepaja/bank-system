import java.util.ArrayList;
import javax.security.auth.login.AccountNotFoundException;

public class Bank {
    private String bankName;
    private ArrayList<Account> accounts;
    private double totalTransactionFee;
    private double totalTransferAmount;
    private double flatFee;
    private double percentFee;

    public Bank (String bankName, double flatFee, double percentFee) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.totalTransactionFee = 0.0;
        this.totalTransferAmount = 0.0;
        this.flatFee = flatFee;
        this.percentFee = percentFee;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getFlatFee(){
        return flatFee;
    }

    public void setFlatFee(double flatFee){
        this.flatFee = flatFee;
    }

    public double getPercentFee(){
        return percentFee;
    }

    public void setPercentFee(double percentFee){
        this.percentFee = percentFee;
    }

    public double getTotalTransferAmount(){
        return totalTransferAmount;
    }

    public double getTotalTransactionFee(){
        return totalTransactionFee;
    }

    public void createAccount(Account newAcc) {
        accounts.add(newAcc);
    }

    public double calculateTransactionFee(double amount) {
        return Math.max(flatFee, amount * (percentFee / 100));
    }

    public void addTransactionFee(double fee) {
        totalTransactionFee += fee;
    }

    public void addTransferAmount(double amount) {
        totalTransferAmount += amount;
    }

    public Account getAccountById(int accountId) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountId() == accountId) {
                return acc;
            }
        }
        throw new AccountNotFoundException("Account with ID " + accountId + " not found.");
    }

    public void listAllBankAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    public void listAccountTransactions(int accountId) {
        try {
            Account account = getAccountById(accountId);
            System.out.println("Transactions for Account ID: " + accountId);
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        } catch (AccountNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
