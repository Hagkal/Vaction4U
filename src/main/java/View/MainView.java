package View;
import Controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class MainView {

    private MainController m_controller;
    @FXML
    public BorderPane lyt_mainPane;
    public Button btn_exit;
    //create form controls
    public TextField tf_username;
    public TextField tf_firstname;
    public DatePicker dp_date;
    public TextField tf_lastname;
    public TextField tf_hometown;
    public PasswordField pf_password;
    //create error labels
    public Label lbl_usernameerr;
    public Label lbl_firstnameerr;
    public Label lbl_lastnameerr;
    public Label lbl_passworderr;
    public Label lbl_hometownerr;
    public Label lbl_dateerr;
    //read form control
    public TextField tf_usernameread;
    //read error label
    public Label lbl_userReadNameErr;
    //delete form control
    public TextField tf_usernamedelete;
    //delete form label
    public Label lbl_userNameDeleteErr;


    /**
     * Functions that presents the correct form on the primary stage by clicking the mouse.
     * @param mouseEvent
     * @throws IOException
     */

    public void setCreate(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/createXML.fxml")));
        MainView m = loader.getController();
        m.set_controller(m_controller);
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

    public void setExit (MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }

    public void send_create(MouseEvent mouseEvent)throws IOException{
        ArrayList<String> toSend = new ArrayList<String>();
        boolean allChecked = true;
        //temp string fields to get the text from the text field, will be checked before inserting to array list
        String username = tf_username.getText(),
                password = pf_password.getText(),
                firstname = tf_firstname.getText(),
                lastname = tf_lastname.getText(),
                hometown = tf_hometown.getText();

        //set error labels to be not visible
        lbl_usernameerr.setVisible(false);
        lbl_firstnameerr.setVisible(false);
        lbl_lastnameerr.setVisible(false);
        lbl_passworderr.setVisible(false);
        lbl_hometownerr.setVisible(false);
        lbl_dateerr.setVisible(false);


        //username check
        if (username.length() == 0){
            lbl_usernameerr.setVisible(true);
            allChecked = false;
        }

        //password check
        if (password.length() < 8){
            lbl_passworderr.setVisible(true);
            allChecked = false;
        }

        //first name check
        if (firstname.length() < 2){
            lbl_firstnameerr.setVisible(true);
            allChecked = false;
        }

        //last name check
        if (lastname.length() < 2){
            lbl_lastnameerr.setVisible(true);
            allChecked = false;
        }

        //date check



        //hometown check
        if (hometown.length() < 2){
            lbl_hometownerr.setVisible(true);
            allChecked = false;
        }

        if (allChecked){
            toSend.add(username);
            toSend.add(password);
            toSend.add(dp_date.getEditor().getText());
            toSend.add(firstname);
            toSend.add(lastname);
            toSend.add(hometown);
            m_controller.create_user(toSend);
        }

    }

    public void send_read (MouseEvent mouseEvent) throws IOException{
        String username = tf_usernameread.getText();
        //set error labels to be not visible
        lbl_userReadNameErr.setVisible(false);
        if (username.length() == 0){
            lbl_userReadNameErr.setVisible(true);
        }
        else
            m_controller.read_user(username);
    }

    public void send_update(MouseEvent mouseEvent)throws IOException{

    }

    public void send_delete(MouseEvent mouseEvent)throws IOException{
        String username = tf_usernamedelete.getText();
        //set error labels to be not visible
        lbl_userNameDeleteErr.setVisible(false);
        if (username.length() == 0){
            lbl_userNameDeleteErr.setVisible(true);
        }
        else
            m_controller.delete_user(username);
    }

    public void create_response (String response){

    }
    public void read_response (ArrayList<String> response){

    }
    public void update_response (String response){

    }
    public void delete_response (String response){

    }

    public void set_controller(MainController c) {
        this.m_controller = c;
    }
}
