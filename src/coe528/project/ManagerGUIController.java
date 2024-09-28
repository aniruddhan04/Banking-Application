package coe528.project; // This declares the package for organizing related classes.

import java.io.IOException; // Importing IOException for handling input/output errors.
import java.net.URL; // Importing URL class for handling URLs.
import java.util.ResourceBundle; // Importing ResourceBundle for managing resources.
import javafx.event.ActionEvent; // Importing ActionEvent class for handling button clicks.
import javafx.fxml.FXML; // Importing FXML annotation for linking Java code to FXML UI.
import javafx.fxml.FXMLLoader; // Importing FXMLLoader for loading FXML files.
import javafx.fxml.Initializable; // Importing Initializable interface for initializing UI components.
import javafx.scene.Node; // Importing Node class for handling UI nodes.
import javafx.scene.Parent; // Importing Parent class for FXML parent nodes.
import javafx.scene.Scene; // Importing Scene class for managing UI scenes.
import javafx.scene.control.Button; // Importing Button class for button controls.
import javafx.scene.control.PasswordField; // Importing PasswordField class for password input.
import javafx.scene.control.TextField; // Importing TextField class for regular text input.
import javafx.stage.Stage; // Importing Stage class for managing application windows.
import javafx.scene.control.Alert; // Importing Alert class for showing alerts.
import javafx.scene.control.Alert.AlertType; // Importing AlertType for different alert types.

public class ManagerGUIController implements Initializable { // Class for managing the GUI for the manager.
    
    // FXML-linked UI components
    @FXML private TextField mUsername; // TextField for manager username input.
    @FXML private PasswordField mPassword; // PasswordField for manager password input.
    @FXML private Button addCustomer, deleteCustomer, logoutButton; // Buttons for actions.

    // Method to validate username and password inputs.
    public boolean checkForInput(String username, String password) {
        // Check if both inputs are non-empty.
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            return true; // Inputs are valid.
        } else {
            return false; // Inputs are invalid.
        }
    }
    
    // Method to handle adding a new customer.
    public void handleAddCustomerButton(ActionEvent event) {
        String username = mUsername.getText(); // Get the username from the text field.
        String password = mPassword.getText(); // Get the password from the password field.

        boolean result = checkForInput(username, password); // Validate the inputs.

        if (result) { // If inputs are valid, proceed to add the customer.
            Customer c1 = new Customer(username, password); // Create a new Customer object.
            Manager m1 = new Manager("admin", "admin", "manager"); // Create a manager object (static admin for simplicity).
            boolean add = m1.addCustomer(c1); // Attempt to add the customer.

            if (add) { // If adding was successful:
                Alert alert = new Alert(AlertType.INFORMATION); // Create an info alert.
                alert.setTitle("CUSTOMER INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("CUSTOMER ADDED!"); // Inform the user.
                alert.showAndWait(); // Show the alert and wait for it to close.
            } else { // If adding failed (duplicate username):
                Alert alert = new Alert(AlertType.INFORMATION); // Create an error alert.
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("CUSTOMERS CAN'T HAVE THE SAME NAMES!"); // Inform the user.
                alert.showAndWait(); // Show the alert and wait for it to close.
            }
        }
    }

    // Method to handle deleting a customer.
    public void handleDeleteCustomerButton(ActionEvent event) {
        String username = mUsername.getText(); // Get the username from the text field.
        String password = mPassword.getText(); // Get the password from the password field.

        boolean result = checkForInput(username, password); // Validate the inputs.

        if (result) { // If inputs are valid, proceed to delete the customer.
            Customer c2 = new Customer(username, password); // Create a new Customer object.
            Manager m2 = new Manager("admin", "admin", "manager"); // Create a manager object (static admin for simplicity).
            boolean delete = m2.deleteCustomer(c2); // Attempt to delete the customer.

            if (delete) { // If deletion was successful:
                Alert alert = new Alert(AlertType.INFORMATION); // Create an info alert.
                alert.setTitle("CUSTOMER INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("CUSTOMER DELETED!"); // Inform the user.
                alert.showAndWait(); // Show the alert and wait for it to close.
            } else { // If deletion failed (customer does not exist):
                Alert alert = new Alert(AlertType.INFORMATION); // Create an error alert.
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("CUSTOMER DOES NOT EXIST!"); // Inform the user.
                alert.showAndWait(); // Show the alert and wait for it to close.
            }
        }
    }

    // Method to handle logging out the manager.
    public void handleLogoutButton(ActionEvent event) throws IOException {
        Parent bankAppGUI = FXMLLoader.load(getClass().getResource("BankGUI.fxml")); // Load the main bank GUI.
        Scene mainGUI = new Scene(bankAppGUI); // Create a new scene for the main GUI.
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current window.
        window.setScene(mainGUI); // Set the new scene.
        window.show(); // Show the new scene.
    }

    // Method to initialize the controller (optional setup).
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code can go here (currently empty).
    }
}
