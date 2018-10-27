package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // receiving fxml loader and setting primary node
        FXMLLoader loader = new FXMLLoader();
        String fxmlFile = "/fxml/MainView.fxml";
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));

        // displaying the first presentation
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
