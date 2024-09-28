package coe528.project; // Declares the package for organizing related classes.

public class Silver extends Level { // Silver class extends the Level class.
    
    // Constructor to initialize the balance.
    public Silver(double balance) {
        this.balance = balance; // Sets the initial balance.
    }

    // Method to handle online purchases.
    @Override
    public double onlinePurchase(double amount) {
        double payment = amount + 20; // Adds a fee of 20 to the purchase amount.
        
        // Check if the total payment (purchase + fee) is less than or equal to the available balance.
        if (payment <= balance) {
            balance -= payment; // Deduct the total payment from the balance.
            return balance; // Return the updated balance.
        } else {
            return balance; // Return the current balance if insufficient funds.
        }
    }
    
    // Method to get the membership level.
    @Override
    public String getLevel() {
        return "SILVER"; // Returns the level as a string.
    }
    
    // Method to get the purchase fee for the level.
    @Override
    public double getFee() {
        return 20; // Silver members have a purchase fee of 20.
    }
}
