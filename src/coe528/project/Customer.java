package coe528.project; // This declares the package for organizing related classes.

import java.io.*; // Import necessary classes for handling input and output operations.
import java.nio.file.Files; // Import Files for file manipulation utilities.
import java.nio.file.Path; // Import Path for handling file paths.
import java.util.ArrayList; // Import ArrayList for dynamic array functionality.

public class Customer extends User { // The Customer class extends the User class, inheriting its properties and methods.
    protected Account a = new Account(100); // Initialize an Account object with a starting balance of 100.

    /*
    Constructor: Initializes a new Customer with a username and password.
    Requires: username and password must be provided.
    */
    public Customer(String username, String password) {
        this.username = username; // Set the customer's username.
        this.password = password; // Set the customer's password.
        this.balance = 100; // Initialize the balance to 100.
    }

    /*
    Effects: Checks if the customer role is valid for login.
    Returns: true if the role is "customer"; otherwise, false.
    */
    @Override
    public boolean login() {
        return this.role.equals("customer"); // Verify if the user's role is "customer".
    }

    /*
    Effects: Clears the customer's credentials and balance upon logout.
    Modifies: username, password, role, and balance are reset to default values.
    */
    @Override
    public void logout() {
        this.username = ""; // Clear the username.
        this.password = ""; // Clear the password.
        this.role = ""; // Clear the role.
        this.balance = 0; // Reset the balance to zero.
    }

    /*
    Requires: amount to be deposited must be a positive value.
    Modifies: Updates the customer's balance and account balance.
    Effects: Adds the amount to both balances and updates the account level.
    */
    public void depositBalance(double amount) throws IOException {
        this.balance += amount; // Increase the customer's balance by the deposit amount.
        double aBalance = a.getBalance(); // Get the current account balance.
        aBalance += amount; // Increase the account balance by the deposit amount.
        a.setBalance(aBalance); // Update the account balance.
        a.updateLevel(aBalance); // Update the account level based on the new balance.

        // Create a file for the customer to store their balance.
        File cFile = new File("." + File.separator + "Customers" + File.separator + username + ".txt");
        Path path = cFile.toPath(); // Convert the file to a Path object.
        ArrayList<String> newLines = new ArrayList<>(); // Create a list to hold modified lines for the file.

        // Read all lines from the customer file and update the balance.
        for (String line : Files.readAllLines(path)) {
            if (line.contains("Balance: ")) // Check if the line contains balance information.
                newLines.add(line.replace("Balance: " + aBalance, "Balance: " + aBalance)); // Update the balance line.
            else
                newLines.add(line); // Keep other lines unchanged.
        }
        Files.write(path, newLines); // Write the updated lines back to the file.
    }

    /*
    Requires: amount to be withdrawn must not exceed the current balance.
    Modifies: Updates the customer's and account's balance after a withdrawal.
    Effects: Decreases the balance by the withdrawal amount and updates the account.
    */
    public void withdrawBalance(double amount) throws IOException {
        // Ensure the withdrawal amount does not exceed the current balance.
        if (this.balance > amount) {
            this.balance -= amount; // Decrease the customer's balance by the withdrawal amount.
            double aBalance2 = a.getBalance(); // Get the current account balance.
            aBalance2 -= amount; // Decrease the account balance by the withdrawal amount.
            a.setBalance(aBalance2); // Update the account balance.
            a.updateLevel(aBalance2); // Update the account level based on the new balance.

            File cFile = new File("./Customers" + File.separator + username + ".txt"); // Create a file for the customer.
            Path path = cFile.toPath(); // Convert the file to a Path object.

            ArrayList<String> newLines = new ArrayList<>(); // Create a list for modified lines.

            // Read all lines from the customer file and update the balance.
            for (String line : Files.readAllLines(path)) {
                if (line.contains("Balance: ")) // Check if the line contains balance information.
                    newLines.add(line.replace("Balance: " + aBalance2, "Balance: " + aBalance2)); // Update the balance line.
                else
                    newLines.add(line); // Keep other lines unchanged.
            }
            Files.write(path, newLines); // Write the updated lines back to the file.
        }
    }

    // Effects: Returns the current balance of the customer.
    public double getBalance() {
        return this.balance; // Provide access to the customer's balance.
    }

    /*
    Requires: amount for the online purchase must be at least 50.
    Modifies: Updates the customer's and account's balance after a purchase.
    Effects: Processes an online purchase and updates balances; returns false if the purchase is not allowed.
    */
    public boolean onlinePurchase(double amount) {
        if (amount < 50) { // Check if the purchase amount is less than the minimum allowed.
            return false; // Deny the purchase if it's too small.
        }

        double purchase = a.onlinePurchase(amount); // Attempt to make the online purchase.
       
        // Check if the purchase amount equals the balance, which means no purchase was made.
        if (balance == purchase) {
            return false; // Deny the purchase if the balance hasn't changed.
        } else {
            this.balance = purchase; // Update the customer's balance with the new amount.
            a.setBalance(purchase); // Update the account balance.
            a.updateLevel(purchase); // Update the account level based on the new balance.
            return true; // Indicate that the purchase was successful.
        }
    }
}
