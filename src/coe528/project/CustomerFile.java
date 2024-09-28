package coe528.project; // This declares the package for organizing related classes.

import java.io.*; // Import necessary classes for handling input and output operations.
import java.nio.file.Path; // Import Path for handling file paths.
import java.util.*; // Import all utility classes, such as ArrayList and HashMap.
import java.util.logging.Level; // Import Level for logging messages with various severity levels.
import java.util.logging.Logger; // Import Logger for logging error messages.

public class CustomerFile { // This class handles the creation and management of customer files.
    protected String username, password; // Fields to store the customer's username and password.
    protected double balance; // Field to store the customer's balance.
    
    /*
    Constructor: Initializes a new CustomerFile with the given username, password, and balance.
    Requires: username, password, and balance must be provided.
    */
    public CustomerFile(String username, String password, double balance) {
        this.username = username; // Set the customer's username.
        this.password = password; // Set the customer's password.
        this.balance = balance; // Set the customer's balance.
    }
    
    /*
    Constructor: Initializes a CustomerFile using an existing Customer object.
    Requires: A Customer object must be provided.
    Effects: Creates a new file for the customer.
    */
    public CustomerFile(Customer c) {
        // Call the other constructor with the username, password, and a default balance of 100.
        this(c.username, c.password, 100);
        try {
            boolean success = createFile(c); // Attempt to create the customer file.
        } catch (IOException ex) {
            // Log any IOException that occurs during file creation.
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Requires: A Customer object to create a corresponding file.
    Modifies: Creates a new file in the specified directory if it doesn't exist.
    Effects: Writes the customer's details (username, password, and balance) to the file.
    Returns: true if the file was created successfully; otherwise, false if it already exists.
    */
    public boolean createFile(Customer a) throws IOException {
        File file = new File("./Customers" + File.separator + a.username + ".txt"); // Define the file path.
        if (file.exists()) {
            return false; // Return false if the file already exists.
        } else {
            file.getParentFile().mkdirs(); // Create the necessary directories for the file.
            file.createNewFile(); // Create the new file.

            // Write the customer's details to the file.
            try (FileWriter write = new FileWriter(file)) {
                write.write("Username: " + a.username + "\n" + "Password: " + a.password + "\n" + "Balance: " + this.balance);
            }
            return true; // Return true if the file was created and written successfully.
        }
    }

    /*
    Requires: Valid username, password, and balance to update the corresponding customer's file.
    Modifies: Updates the existing file with the new customer details.
    Effects: Writes the updated details to the file.
    Returns: true if the file was updated successfully; otherwise, false if the file does not exist.
    */
    public boolean updateFile(String username, String password, double balance) throws IOException {
        File file = new File("./Customers" + File.separator + username + ".txt"); // Define the file path for the user.
        if (!file.exists()) {
            return false; // Return false if the file does not exist.
        } else {
            // Write the updated customer details to the file.
            try (FileWriter write = new FileWriter(file)) {
                write.write("Username: " + username + "\n" + "Password: " + password + "\n" + "Balance: " + balance);
            }
            return true; // Return true if the file was updated successfully.
        }
    }
    
    // Effects: Returns the current username of the customer.
    public String getUsername() {
        return this.username; // Provide access to the customer's username.
    }
    
    // Modifies: Sets a new username for the customer.
    public void setUsername(String user) {
        this.username = user; // Update the customer's username.
    }
    
    // Effects: Returns the current password of the customer.
    public String getPassword() {
        return this.password; // Provide access to the customer's password.
    }
    
    // Modifies: Sets a new password for the customer.
    public void setPassword(String pass) {
        this.password = pass; // Update the customer's password.
    }
    
    // Effects: Returns the current balance of the customer.
    public double getBalance() {
        return this.balance; // Provide access to the customer's balance.
    }
    
    // Modifies: Sets a new balance for the customer.
    public void setBalance(double bnc) {
        this.balance = bnc; // Update the customer's balance.
    }
}
