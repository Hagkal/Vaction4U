package Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainViewController {

    @FXML
    public BorderPane lyt_mainPane;

    /**
     * Functions that presents the correct form on the primary stage by clicking the mouse.
     * @param mouseEvent
     * @throws IOException
     */

    public void setCreate(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/createXML.fxml")));
    }

    public void setRead(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/readXML.fxml")));
    }

    public void setUpdate(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/updateXML.fxml")));
    }

    public void setDelete(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/deleteXML.fxml")));
    }
}
