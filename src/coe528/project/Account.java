package coe528.project;  // This declares the package for organizing related classes.

/*
Overview: 
The Account class represents a bank account with a balance and a level (Silver, Gold, or Platinum). 
The account level changes based on the balance, and each level has different behaviors for online purchases.

Abstraction Function:
An account is represented as a pair (balance, level), where:
- balance is a double that represents the account's current amount of money.
- level is an instance of a Level class that represents the account's tier (Silver, Gold, or Platinum).

Rep Invariant: 
- The balance must be greater than or equal to 0.
- The level must not be null.
*/
public class Account {
    protected double balance;  // The current amount of money in the account.
    protected Level level;     // The current tier of the account.

    /* 
    Requires: balance must be >= 0
    Modifies: this (the current Account object)
    Effects: Initializes this Account object with the specified balance and determines the level based on that balance.
    */
    Account(double balance) {
        this.balance = balance;  // Set the account's balance.
        // Determine the account's level based on the initial balance.
        if (this.balance < 10000) {
            this.level = new Silver(this.balance);  // Assign Silver level if balance is less than 10,000.
        } else if (this.balance >= 10000 && this.balance < 20000) {
            this.level = new Gold(this.balance);    // Assign Gold level if balance is between 10,000 and 20,000.
        } else if (this.balance >= 20000) {
            this.level = new Platinum(this.balance); // Assign Platinum level if balance is 20,000 or more.
        }
    }

    // Effects: Returns the current balance of this account.
    public double getBalance() {
        return this.balance;  // Provide access to the account's balance.
    }

    /* 
    Requires: b must be >= 0
    Modifies: this (the current Account object)
    Effects: Increases the account's balance by the amount b and updates the level based on the new balance.
    */
    public void setBalance(double b) {
        this.balance += b;  // Add the specified amount to the balance.
        updateLevel(this.balance);  // Update the account's level based on the new balance.
    }

    // Effects: Returns the current level of this account as a string.
    public String getLevel() {
        return this.level.getLevel();  // Provide access to the account's level.
    }
    
    // Effects: Returns the fee associated with the current level of this account.
    public double getFee() {
        return this.level.getFee();  // Retrieve the fee based on the account's level.
    }

    /* 
    Modifies: this (the current Account object)
    Effects: Updates the account's level based on the current balance.
    */
    public void updateLevel(double balance) {
        this.balance = balance;  // Set the balance.
        // Determine the new level based on the current balance.
        if (this.balance < 10000) {
            this.level = new Silver(this.balance);  // Assign Silver level if balance is less than 10,000.
        } else if (this.balance >= 10000 && this.balance < 20000) {
            this.level = new Gold(this.balance);    // Assign Gold level if balance is between 10,000 and 20,000.
        } else if (this.balance >= 20000) {
            this.level = new Platinum(this.balance); // Assign Platinum level if balance is 20,000 or more.
        }
    }

    /* 
    Requires: amount must be >= 0
    Modifies: this (the current Account object)
    Effects: Processes an online purchase of the specified amount using the current level's rules,
             updates the balance and level, and returns the new balance.
    */
    public double onlinePurchase(double amount) {
        this.balance = this.level.onlinePurchase(amount);  // Perform the purchase and update the balance.
        updateLevel(this.balance);  // Update the account's level based on the new balance.
        return this.balance;  // Return the updated balance.
    }

    // Effects: Returns a string representation of this account (balance and level).
    @Override
    public String toString() {
        return "(" + balance + ", " + level.getLevel() + ")";  // Format the account details for display.
    }

    // Effects: Checks if the representation invariant holds for this account.
    public boolean repOk() {
        return balance >= 0 && level != null;  // Validate that balance is non-negative and level is not null.
    }
}
