package coe528.project; //  declares the package for organizing related classes.

public class Gold extends Level { // The Gold class extends the Level class, indicating it is a specific type of account level.
    
    // Constructor that initializes the Gold level with a specified balance.
    public Gold(double balance) {
        this.balance = balance; // Sets the balance for the Gold account level.
    }

    /*
    Handles online purchases for the Gold account level.
    Requires: The amount to be spent on the purchase.
    Effects: Deducts the purchase amount plus a fee from the account balance.
    Returns: The remaining balance after the purchase.
    */
    @Override
    public double onlinePurchase(double amount) {
        double payment = amount + 10; // Calculate total payment, including a fixed fee of $10.

        // Check if the balance is sufficient for the payment.
        if (payment <= balance) {
            balance -= payment; // Deduct the payment from the balance.
            return balance; // Return the updated balance.
        } else {
            return balance; // If not enough balance, return the current balance without changes.
        }
    }

    /*
    Returns the string representation of the account level.
    Returns: The level of the account as a string ("GOLD").
    */
    @Override
    public String getLevel() {
        return "GOLD"; // Indicates that this is a Gold account level.
    }

    /*
    Returns the fee for online purchases at this level.
    Returns: The fee amount ($10).
    */
    @Override
    public double getFee() {
        return 10; // The fee for online purchases at the Gold level is $10.
    }
}
