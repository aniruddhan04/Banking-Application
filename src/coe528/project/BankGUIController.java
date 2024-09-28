package coe528.project; // This declares the package for organizing related classes.

import java.io.IOException; // Import necessary classes for handling I/O exceptions.
import java.net.URL; // Import URL class for handling URLs.
import java.util.ResourceBundle; // Import ResourceBundle for managing localized resources.
import javafx.event.ActionEvent; // Import ActionEvent for handling button actions.
import javafx.fxml.FXML; // Import FXML annotation for JavaFX.
import javafx.fxml.FXMLLoader; // Import FXMLLoader for loading FXML files.
import javafx.fxml.Initializable; // Import Initializable to allow initialization of FXML controllers.
import javafx.scene.Node; // Import Node for managing scene elements.
import javafx.scene.Parent; // Import Parent for managing UI elements.
import javafx.scene.Scene; // Import Scene for creating the application scene.
import javafx.scene.control.Alert; // Import Alert for displaying error messages.
import javafx.scene.control.Alert.AlertType; // Import AlertType for specifying alert types.
import javafx.scene.control.Button; // Import Button for UI buttons.
import javafx.scene.control.PasswordField; // Import PasswordField for password inputs.
import javafx.scene.control.TextField; // Import TextField for regular text inputs.
import javafx.stage.Stage; // Import Stage for managing application windows.

public class BankGUIController implements Initializable {
    // FXML annotations link UI elements from the FXML file to these variables.
    @FXML private TextField usernameText, roleText; // Text fields for username and role input.
    @FXML private PasswordField passwordText; // Password field for password input.
    @FXML private Button loginManager, loginCustomer; // Buttons for manager and customer login.

    /*
    Effects: Checks if the provided inputs (s1, s2, s3) are non-null and non-empty.
    Returns: true if all inputs are valid; otherwise shows an error alert and returns false.
    */
    public boolean checkForInput(String s1, String s2, String s3) {
        // Validate that none of the input strings are null or empty.
        boolean isValid = s1 != null && !s1.isEmpty() && s2 != null && !s2.isEmpty() && s3 != null && !s3.isEmpty();
        
        // If any input is invalid, show an alert dialog.
        if (!isValid) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION); // Create an informational alert.
            alert.setTitle("ERROR"); // Set the title of the alert.
            alert.setHeaderText(null); // No header text.
            alert.setContentText("INVALID INPUT!"); // Set the content message.
            alert.showAndWait(); // Display the alert and wait for the user to close it.
        }
        return isValid; // Return the validation result.
    }

    /*
    Requires: An ActionEvent (event) from the login manager button.
    Modifies: The current scene if the login is successful.
    Effects: Loads the manager GUI scene if the login credentials are valid; otherwise shows an error alert.
    */
    public void handleManagerButton(ActionEvent event) throws IOException {
        // Load the Manager GUI layout from FXML.
        Parent managerGUI = FXMLLoader.load(getClass().getResource("ManagerGUI.fxml"));
        Scene managerScene = new Scene(managerGUI); // Create a new scene for the manager.
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current window.

        // Retrieve user inputs from the text fields.
        String username = usernameText.getText();
        String password = passwordText.getText();
        String role = roleText.getText().toLowerCase(); // Convert role to lowercase for consistency.

        // Check if the input values are valid.
        boolean input = checkForInput(username, password, role);

        // If input is valid, proceed to login.
        if (input) {
            Manager m1 = new Manager(username, password, role); // Create a new Manager object.
            boolean loginSuccess = m1.login(); // Attempt to log in.

            // If login is successful, switch to the manager scene.
            if (loginSuccess) {
                window.setScene(managerScene);
                window.show();
            } else {
                // Show an alert if login fails.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("NOT MANAGER!"); // Inform the user of the failure.
                alert.showAndWait();
            }
        }
    }

    /*
    Requires: An ActionEvent (event) from the login customer button.
    Modifies: The current scene if the login is successful.
    Effects: Loads the customer GUI scene if the login credentials are valid; otherwise shows an error alert.
    */
    public void handleCustomerButton(ActionEvent event) throws IOException {
        // Load the Customer GUI layout from FXML.
        Parent customerGUI = FXMLLoader.load(getClass().getResource("CustomerGUI.fxml"));
        Scene customerScene = new Scene(customerGUI); // Create a new scene for the customer.
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current window.

        // Retrieve user inputs from the text fields.
        String username = usernameText.getText();
        String password = passwordText.getText();
        String role = roleText.getText().toLowerCase(); // Convert role to lowercase for consistency.

        // Check if the input values are valid.
        boolean input = checkForInput(username, password, role);

        // If input is valid, proceed to login.
        if (input) {
            // Check if the provided role is "customer".
            if (role.equals("customer")) {
                Manager m1 = new Manager("admin", "admin", "manager"); // Create an admin manager object.
                Customer c1 = new Customer(username, password); // Create a new Customer object.

                // Check if the customer exists using the manager's search method.
                boolean result = m1.searchCustomer(c1);
                if (result) {
                    m1.setCurrentCustomer(c1); // Set the current customer in the manager.
                    window.setScene(customerScene); // Switch to the customer scene.
                    window.show();
                } else {
                    // Show an alert if the customer is not found.
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ERROR");
                    alert.setHeaderText(null);
                    alert.setContentText("NOT CUSTOMER!"); // Inform the user of the failure.
                    alert.showAndWait();
                }
            } else {
                // Show an alert if the role is not "customer".
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("NOT CUSTOMER!"); // Inform the user of the failure.
                alert.showAndWait();
            }
        }
    }

    // Effects: This method is called to initialize the controller when the application starts.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Any necessary initialization can be done here, if needed.
    }
}
