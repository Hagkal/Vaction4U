package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.format.DateTimeFormatter;

public class CreateController {
    @FXML
    public TextField firstText;
    public DatePicker date;
    public Button btn_send;

    public void testit(MouseEvent mouseEvent) {
        String test = date.getEditor().getText();//date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        firstText.setText(test);

    }
}
