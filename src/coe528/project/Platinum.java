package coe528.project; // Declares the package for organizing related classes.

public class Platinum extends Level { // Platinum class extends the Level class.
    
    // Constructor to initialize the balance.
    public Platinum(double balance) {
        this.balance = balance; // Sets the initial balance.
    }

    // Method to handle online purchases.
    @Override
    public double onlinePurchase(double amount) {
        // Check if the amount is less than or equal to the available balance.
        if (amount <= balance) {
            balance -= amount; // Deduct the purchase amount from the balance.
            return balance; // Return the updated balance.
        } else {
            return balance; // Return the current balance if insufficient funds.
        }
    }
    
    // Method to get the membership level.
    @Override
    public String getLevel() {
        return "PLATINUM"; // Returns the level as a string.
    }
    
    // Method to get the purchase fee for the level.
    @Override
    public double getFee() {
        return 0; // Platinum members have no purchase fee.
    }
}
