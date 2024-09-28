package coe528.project; // This declares the package for organizing related classes.

import java.io.File; // Importing File class for file operations.
import java.io.IOException; // Importing IOException for handling input/output errors.
import java.nio.file.*; // Importing classes for file path and file operations.
import java.util.ArrayList; // Importing ArrayList for using dynamic arrays.

public class Manager extends User { // Manager class extends User, meaning it inherits User's properties and methods.
    private static ArrayList<Customer> customers = new ArrayList<>(); // A static list to hold all Customer objects.
    protected static ArrayList<CustomerFile> file = new ArrayList<>(); // A static list to hold CustomerFile objects for file management.
    protected static Customer currentCustomer = new Customer("a", "b"); // A static variable to track the currently active customer.

    // Constructor to create a Manager object with username, password, and role.
    public Manager(String username, String password, String role) {
        this.username = username; // Set the username for the manager.
        this.password = password; // Set the password for the manager.
        this.role = role; // Set the role (should be "manager").
    }

    // Method to retrieve the currently active customer.
    public Customer getCurrentCustomer() {
        return currentCustomer; // Return the current customer.
    }

    // Method to set the currently active customer.
    public void setCurrentCustomer(Customer u) {
        currentCustomer = u; // Update the current customer.
    }

    // Method to log in the manager; returns true if credentials match.
    @Override
    public boolean login() {
        // Checks if the username, password, and role are correct.
        if (this.username.equals("admin") && this.password.equals("admin") && this.role.equals("manager")) {
            return true; // Login successful.
        } else {
            return false; // Login failed.
        }
    }

    // Method to log out the manager; clears the credentials.
    @Override
    protected void logout() {
        this.username = ""; // Clear the username.
        this.password = ""; // Clear the password.
        this.role = ""; // Clear the role.
    }

    // Method to add a new customer to the list.
    public boolean addCustomer(Customer a) {
        // If the customer list is empty, add the new customer.
        if (customers.isEmpty()) {
            customers.add(a); // Add the customer.
            CustomerFile f1 = new CustomerFile(a); // Create a new CustomerFile for this customer.
            file.add(f1); // Add the CustomerFile to the file list.
            return true; // Successfully added.
        }

        // Check if the customer already exists.
        for (int i = 0; i < customers.size(); i++) {
            if (a.username.equals(customers.get(i).username)) {
                return false; // Customer already exists; cannot add.
            } else {
                customers.add(a); // Add the customer.
                CustomerFile f1 = new CustomerFile(a); // Create a new CustomerFile for this customer.
                file.add(f1); // Add the CustomerFile to the file list.
                return true; // Successfully added.
            }
        }
        return false; // Default return if no conditions were met.
    }

    // Method to delete a customer from the list.
    public boolean deleteCustomer(Customer b) {
        // Remove the customer from the list if found.
        if (customers.removeIf(c -> c.username.equals(b.username))) {
            file.removeIf(f -> f.getUsername().equals(b.username)); // Remove the associated CustomerFile.

            try {
                // Create a path for the customer's file to delete it.
                Path path = Paths.get("./Customers" + File.separator + b.username + ".txt");
                Files.deleteIfExists(path); // Delete the file if it exists.
            } catch (IOException e) {
                // Handle any I/O exceptions (optional error handling can be added here).
            }
            return true; // Successfully deleted.
        } else {
            return false; // Customer not found; deletion failed.
        }
    }

    // Method to update an existing customer's details.
    public boolean updateCustomer(Customer c) {
        CustomerFile f2 = new CustomerFile(c); // Create a new CustomerFile for the updated customer.

        // Find the customer in the list and update their details.
        for (int i = 0; i < customers.size(); i++) {
            if (c.username.equals(customers.get(i).username)) {
                customers.set(i, c); // Update the customer in the list.
                file.set(i, f2); // Update the associated CustomerFile.
                return true; // Successfully updated.
            }
        }
        return false; // Customer not found; update failed.
    }

    // Method to search for a customer by username.
    public boolean searchCustomer(Customer d) {
        // If the customer list is empty, return false.
        if (customers.isEmpty()) {
            return false; // No customers to search.
        }

        // Check if the customer exists in the list.
        for (int i = 0; i < customers.size(); i++) {
            if (d.username.equals(customers.get(i).username)) {
                return true; // Customer found.
            }
        }
        return false; // Customer not found.
    }
}
