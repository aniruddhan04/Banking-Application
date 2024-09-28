package coe528.project; // This declares the package for organizing related classes.

// The Level class is declared as abstract, meaning it cannot be instantiated directly.
// It serves as a blueprint for other classes (like Gold, Silver, Platinum) that will extend it.
public abstract class Level {
    public double balance; // This variable holds the balance for an account level.

    // This method must be implemented by any subclass that extends Level.
    // It handles online purchases and returns the updated balance after the purchase.
    public abstract double onlinePurchase(double amount);

    // This method must also be implemented by subclasses.
    // It returns the name of the account level as a string (e.g., "GOLD").
    public abstract String getLevel();

    // This method must be implemented by subclasses as well.
    // It returns the fee associated with online purchases for that account level.
    public abstract double getFee();
}
