package coe528.project; // This declares the package for organizing related classes.

import javafx.application.Application; // Importing the Application class from JavaFX.
import javafx.fxml.FXMLLoader; // Importing FXMLLoader to load FXML files.
import javafx.scene.Parent; // Importing Parent, a class for creating a scene graph.
import javafx.scene.Scene; // Importing Scene, which represents the GUI.
import javafx.stage.Stage; // Importing Stage, which is the main window of the application.

// MainUI class extends the Application class, allowing it to be a JavaFX application.
public class MainUI extends Application {

    // This method is called when the application starts.
    @Override
    public void start(Stage stage) throws Exception {
        // Load the layout from the FXML file named "BankGUI.fxml".
        Parent root = FXMLLoader.load(getClass().getResource("BankGUI.fxml"));

        // Create a new Scene using the loaded layout (root).
        Scene scene = new Scene(root);

        // Set the scene for the primary stage (window).
        stage.setScene(scene);

        // Display the window to the user.
        stage.show();
    }

    // The main method, which is the entry point of the Java application.
    public static void main(String[] args) {
        launch(args); // Calls the launch method to start the JavaFX application.
    }
}
