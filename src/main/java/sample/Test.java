package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Test {
    @FXML
    public TextField firstText;

    public void testit(MouseEvent mouseEvent) {
        firstText.setText("Good Job!");
    }
}
