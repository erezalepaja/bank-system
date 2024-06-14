import java.util.InputMismatchException;
import java.util.Scanner;

public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = null;

        while (true) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Create a bank");
            System.out.println("2. Create an account");
            System.out.println("3. View all accounts");
            System.out.println("4. Check account balance");
            System.out.println("5. Withdraw");
            System.out.println("6. Deposit");
            System.out.println("7. Transfer");
            System.out.println("8. Display bank totals");
            System.out.println("9. Display account transactions");
            System.out.println("10. Exit");

            try {
            int action = scanner.nextInt();

                switch (action) {
                    case 1:
                        System.out.println("Enter the bank name:");
                        String bankName = scanner.next();
                        System.out.println("Enter the flat fee for transactions:");
                        double flatFee = scanner.nextDouble();
                        System.out.println("Enter the percent fee for transactions (as a decimal):");
                        double percentFee = scanner.nextDouble();
                        bank = new Bank(bankName, flatFee, percentFee);
                        System.out.println("Bank created successfully: " + bankName);
                    break;
                    case 2:
                    if (bank != null) {
                        System.out.println("Enter account ID:");
                        int accId = scanner.nextInt();
                        System.out.println("Enter account holder's name:");
                        String accountName = scanner.next();
                        System.out.println("Enter initial balance:");
                        double initialBalance = scanner.nextDouble();
                        Account account = new Account(accId, accountName, initialBalance);
                        bank.createAccount(account);
                        System.out.println("Account created successfully.");
                    } else {
                        System.out.println("Please create a bank first.");
                    }
                    case 3:
                        bank.listAllBankAccounts();
                        break;
                    case 4:
                        if (bank != null) {
                            System.out.println("Enter account ID to check balance:");
                            int accountID = scanner.nextInt();
                            Account account = bank.getAccountById(accountID);
                            if (account != null) {
                                System.out.println("Account balance: " + account.getAccountBalance());
                            } else {
                                System.out.println("Account not found.");
                            }
                        } else {
                            System.out.println("Please create a bank first.");
                        }
                        break;
                    case 5:
                        System.out.println("Enter account ID for withdrawal:");
                        int withdrawId = scanner.nextInt();
                        System.out.println("Enter amount:");
                        double withdrawAmount = scanner.nextDouble();
                        Transaction withdrawal = new Withdraw(withdrawAmount, withdrawId, "ATM Withdrawal");
                        withdrawal.executeTransaction(bank);
                        break;
                    case 6:
                        System.out.println("Enter account ID for deposit:");
                        int depositId = scanner.nextInt();
                        System.out.println("Enter amount:");
                        double depositAmount = scanner.nextDouble();
                        Transaction deposit = new Deposit(depositAmount, depositId, "Bank Deposit");
                        deposit.executeTransaction(bank);
                        break;
                    case 7:
                        System.out.println("Enter originating account ID for transfer:");
                        int fromId = scanner.nextInt();
                        System.out.println("Enter resulting account ID for transfer:");
                        int toId = scanner.nextInt();
                        System.out.println("Enter amount:");
                        double transferAmount = scanner.nextDouble();
                        Transaction transfer = new Transfer(transferAmount, fromId, toId, "Transfer");
                        transfer.executeTransaction(bank);
                        break;
                    case 8:
                        System.out.println("Bank's total transaction fee amount: " + bank.getTotalTransactionFee());
                        System.out.println("Bank's total transfer amount: " + bank.getTotalTransferAmount());
                        break;
                    case 9:
                        System.out.println("Enter account ID to display transactions:");
                        int accountId = scanner.nextInt();
                        bank.listAccountTransactions(accountId);
                        break;
                    case 10:
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct data type.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            
            }
        }
    }
    