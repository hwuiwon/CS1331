import java.util.Optional;

/**
 * @author hkim944
 * @version 1.0
 */

public class Transaction {

    private TransactionType type;
    private double amount;
    private Optional<String> comment;

    /**
     * Creates Transaction with type and amount and set comment empty
     * @param type Type of Transaction
     * @param amount Amount of Transaction
     */
    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.comment = Optional.empty();
    }

    /**
     * Creates Transaction with type, amount, and comment
     * @param type Type of Transaction
     * @param amount Amount of Transaction
     * @param comment Comment of Transaction
     */
    public Transaction(TransactionType type, double amount, String comment) {
        this.type = type;
        this.amount = amount;
        this.comment = Optional.of(comment);
    }

    /**
     * @return Type of a transaction
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * @return Amount of a transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return Comment of a transaction
     */
    public Optional<String> getComment() {
        return comment;
    }

    /**
     * @return Whether a transaction has a comment
     */
    public boolean hasComment() {
        return comment.isPresent();
    }
}