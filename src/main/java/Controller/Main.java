package Controller;

import Model.Model;
import View.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        // receiving fxml loader and setting primary node
        FXMLLoader loader = new FXMLLoader();
        String fxmlFile = "/fxml/MainView.fxml";
        Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));

        // seeting up
        Model m = new Model();
        MainController c = new MainController();
        MainView v = loader.getController();
        m.set_controller(c);
        c.set_model(m);
        c.set_view(v);
        v.set_controller(c);

        // displaying the first presentation
        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
