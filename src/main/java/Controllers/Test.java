package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Test {
    @FXML
    public TextField firstText;

    public void testit(MouseEvent mouseEvent) {
        firstText.setText("Good Job!");
    }
}
