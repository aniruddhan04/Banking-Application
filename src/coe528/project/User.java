package coe528.project; // Declares the package for organizing related classes.

public abstract class User { // Abstract class User, serving as a base class for different user types.
    protected String username; // Username of the user.
    protected String password; // Password of the user.
    protected String role; // Role of the user (e.g., admin, customer, manager).
    protected double balance; // Balance associated with the user, typically for financial accounts.

    // Abstract method for logging in, must be implemented by subclasses.
    abstract public boolean login();

    // Abstract method for logging out, must be implemented by subclasses.
    abstract protected void logout();
}
