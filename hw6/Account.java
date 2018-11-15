import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author hkim944
 * @version 1.0
 */

public class Account {

    private List<Transaction> pastTransactions;

    /**
     * Creates Account with transactions
     * @param transactions List of Transactions
     */
    public Account(List<Transaction> transactions) {
        pastTransactions = transactions;
    }

    /**
     * @return Nth transaction from pastTransactions
     * @param n Order of transaction
     */
    public Transaction getTransaction(int n) {
        return pastTransactions.get(n);
    }

    /**
     * @return List of filtered Transactions from pastTransactions
     * @param predicate Filters Transaction
     */
    public List<Transaction>
        findTransactionsByPredicate(Predicate<Transaction> predicate) {
        List<Transaction> lists = new ArrayList<Transaction>();
        for (Transaction t : pastTransactions) {
            if (predicate.test(t)) {
                lists.add(t);
            }
        }
        return lists;
    }

    /**
     * @return List of Transactions with an equal amount
     * @param amount Amount of Transaction
     */
    public List<Transaction> getTransactionsByAmount(double amount) {
        Predicate<Transaction> pred = new Predicate<Transaction>() {
            public boolean test(Transaction t) {
                return t.getAmount() == amount;
            }
        };
        List<Transaction> lists = findTransactionsByPredicate(pred);
        return lists;
    }

    /**
     * @return List of Transactions that are withdrawals
     */
    public List<Transaction> getWithdrawals() {
        List<Transaction> lists = findTransactionsByPredicate(
            new Predicate<Transaction>() {
                public boolean test(Transaction t) {
                    return t.getType() == TransactionType.WITHDRAWAL;
                }
            });
        return lists;
    }

    /**
     * @return List of Transactions that are deposits
     */
    public List<Transaction> getDeposits() {
        List<Transaction> lists =
            findTransactionsByPredicate(t ->
                { return t.getType() == TransactionType.DEPOSIT; });
        return lists;
    }

    /**
     * @return List of Transactions with comment same or longer than length
     * @param length Length of Transaction comment
     */
    public List<Transaction> getTransactionsWithCommentLongerThan(int length) {
        List<Transaction> lists =
            findTransactionsByPredicate(t ->
                { return t.getComment().orElse("").length() > length; });
        return lists;
    }

    /**
     * @return List of Transactions with comments that are not empty
     */
    public List<Transaction> getTransactionsWithComment() {
        List<Transaction> lists =
            findTransactionsByPredicate(t ->
                { return t.hasComment(); });
        return lists;
    }

    /**
     * @return Past Transactions
     */
    public List<Transaction> getPastTransactions() {
        return pastTransactions;
    }
}