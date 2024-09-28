package coe528.project; // This declares the package for organizing related classes.

import java.io.IOException; // Import necessary classes for handling input/output exceptions.
import java.net.URL; // Import URL for handling network resources.
import java.util.ResourceBundle; // Import ResourceBundle for localization.
import javafx.event.ActionEvent; // Import ActionEvent for handling button actions.
import javafx.fxml.FXML; // Import FXML annotations for linking UI components.
import javafx.fxml.FXMLLoader; // Import FXMLLoader for loading FXML files.
import javafx.fxml.Initializable; // Import Initializable for initializing the controller.
import javafx.scene.Node; // Import Node for manipulating UI elements.
import javafx.scene.Parent; // Import Parent for loading FXML layouts.
import javafx.scene.Scene; // Import Scene for setting up the GUI.
import javafx.scene.control.Alert; // Import Alert for displaying messages to the user.
import javafx.scene.control.Button; // Import Button for button UI elements.
import javafx.scene.control.TextField; // Import TextField for user input fields.
import javafx.stage.Stage; // Import Stage for managing application windows.

public class CustomerGUIController implements Initializable {
    // FXML annotations link these fields to UI elements defined in an FXML file.
    @FXML private TextField depositAmount, withdrawAmount, cUsername, amountOnlinePurchase; // Input fields for user actions.
    @FXML private Button deposit, withdraw, checkBalance, onlinePurchase, cLogout; // Buttons for user actions.

    // Create a Manager instance to manage customer actions, initialized with default admin credentials.
    protected Manager m1 = new Manager("admin", "admin", "manager");

    /*
    Handles the deposit button action.
    Requires: The user to input an amount to deposit.
    Effects: If the input is valid, deposits the amount and updates the customer information.
    Displays an alert to indicate success or failure.
    */
    public void handleDepositButton() throws IOException {
        double cDeposit = Double.parseDouble(depositAmount.getText()); // Parse the deposit amount from the input field.
        if (cDeposit == 0) { // Check if the input is zero.
            // Show an alert if no amount was entered.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("PLEASE ENTER AMOUNT TO DEPOSIT!");
            alert.showAndWait();
        } else {
            Customer c2 = m1.getCurrentCustomer(); // Get the current customer.
            c2.depositBalance(cDeposit); // Deposit the amount to the customer's account.
            
            // Update the customer information in the system.
            boolean update = m1.updateCustomer(c2);
            if (update) {
                // Show a success alert if the update was successful.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("DEPOSIT INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("DEPOSITED!");
                alert.showAndWait();
            } else {
                // Show an error alert if the update failed.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText(null);
                alert.setContentText("ERROR, PLEASE TRY AGAIN!");
                alert.showAndWait();
            }
        }
    }

    /*
    Handles the withdraw button action.
    Requires: The user to input an amount to withdraw.
    Effects: If the input is valid and sufficient balance is available, withdraws the amount and updates the customer information.
    Displays an alert to indicate success or failure.
    */
    public void handleWithdrawButton() throws IOException {
        double cWithdraw = Double.parseDouble(withdrawAmount.getText()); // Parse the withdrawal amount from the input field.
        
        if (cWithdraw == 0) { // Check if the input is zero.
            // Show an alert if no amount was entered.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("PLEASE ENTER AMOUNT TO WITHDRAW!");
            alert.showAndWait();
        } else {
            Customer c3 = m1.getCurrentCustomer(); // Get the current customer.
            c3.withdrawBalance(cWithdraw); // Withdraw the amount from the customer's account.
            
            // Update the customer information in the system.
            boolean update = m1.updateCustomer(c3);
            
            if (update && c3.getBalance() > cWithdraw) { // Check if the update was successful and there is sufficient balance.
                // Show a success alert if the withdrawal was successful.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WITHDRAW INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("WITHDRAWN!");
                alert.showAndWait();
            } else {
                // Show an error alert if there is not enough balance.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("NOT ENOUGH BALANCE!");
                alert.showAndWait();
            }
        }
    }

    /*
    Handles the check balance button action.
    Effects: Displays the current balance and account level of the customer in an alert.
    */
    public void handleCheckBalanceButton() {
        Customer c4 = m1.getCurrentCustomer(); // Get the current customer.
        double balance = c4.getBalance(); // Retrieve the customer's balance.
        // Show an alert with the customer's balance and account information.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BALANCE INFORMATION");
        alert.setHeaderText(null);
        alert.setContentText("BALANCE: $" + balance + "!\nLEVEL: " + c4.a.getLevel().toUpperCase() + "\nONLINE PURCHASE FEE: $" + c4.a.getFee());
        alert.showAndWait();
    }

    /*
    Handles the online purchase button action.
    Requires: The user to input an amount to spend.
    Effects: If the input is valid and above the minimum, processes the purchase.
    Displays an alert to indicate success or failure.
    */
    public void handleOnlinePurchaseButton() {
        double amount = Double.parseDouble(amountOnlinePurchase.getText()); // Parse the purchase amount from the input field.
        
        if (amount == 0) { // Check if the input is zero.
            // Show an alert if no amount was entered.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("ENTER AMOUNT TO PURCHASE!");
            alert.showAndWait();
        }
        if (amount < 50) { // Check if the amount is below the minimum for online purchases.
            // Show an alert if the amount is too low.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("ONLY PURCHASES ABOVE $50!");
            alert.showAndWait();
        } else {
            Customer c5 = m1.getCurrentCustomer(); // Get the current customer.
            boolean result = c5.onlinePurchase(amount); // Attempt to make the online purchase.
            boolean update = m1.updateCustomer(c5); // Update the customer information in the system.
            
            if (result && update) { // Check if both the purchase and update were successful.
                // Show a success alert if the purchase was successful.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("PURCHASE INFORMATION");
                alert.setHeaderText(null);
                alert.setContentText("PURCHASED!");
                alert.showAndWait();
            } else {
                // Show an error alert if the purchase failed due to insufficient balance.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("NOT ENOUGH BALANCE!");
                alert.showAndWait();
            }
        }
    }

    /*
    Handles the customer logout button action.
    Effects: Loads the main bank application GUI and switches to it.
    */
    public void handleCLogoutButton(ActionEvent event) throws IOException {
        Parent bankAppGUI = FXMLLoader.load(getClass().getResource("BankGUI.fxml")); // Load the main bank GUI.
        Scene mainGUI = new Scene(bankAppGUI); // Create a new scene with the main GUI.
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow(); // Get the current window.
        window.setScene(mainGUI); // Switch the scene to the main GUI.
        window.show(); // Show the new scene.
    }

    // Method to initialize any setup when the controller is loaded.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // This method can be used to set up initial states, but it's empty here.
    }
}
