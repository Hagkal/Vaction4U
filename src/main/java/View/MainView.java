package View;
import Controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainView {

    public TextField tf_usernameread_update;
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
    //update form controllers
    public TextField tf_usernameUpdate;
    public TextField tf_firstnameUpdate;
    public DatePicker dp_dateUpdate;
    public TextField tf_lastnameUpdate;
    public TextField tf_hometownUpdate;
    public TextField tf_passwordUpdate;
    public Button btn_sendUpdate;
    //update error label
    public Label lbl_userNameUpdateErr;
    public Label lbl_firstNameUpdateErr;
    public Label lbl_lastNameUpdateErr;
    public Label lbl_passwordUpdateErr;
    public Label lbl_hometownUpdateErr;
    public Label lbl_dateUpdateErr;


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

        MainView v = loader.getController();
        v.set_controller(m_controller);
    }

    public void setUpdate(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/updateXML.fxml")));
        MainView m = loader.getController();
        m.set_controller(m_controller);
    }

    public void setDelete(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        lyt_mainPane.setCenter(loader.load(getClass().getResourceAsStream("/fxml/deleteXML.fxml")));
        MainView m = loader.getController();
        m.set_controller(m_controller);
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
        if (!isBiggerThen18(dp_date)){
            lbl_dateerr.setVisible(true);
            allChecked = false;
        }

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
        ArrayList<String> toSend = new ArrayList<String>();
        boolean allChecked = true;
        //temp string fields to get the text from the text field, will be checked before inserting to array list
        String username = tf_usernameUpdate.getText(),
                password = tf_passwordUpdate.getText(),
                firstname = tf_firstnameUpdate.getText(),
                lastname = tf_lastnameUpdate.getText(),
                hometown = tf_hometownUpdate.getText();

        //set error labels to be not visible
        lbl_userNameUpdateErr.setVisible(false);
        lbl_firstNameUpdateErr.setVisible(false);
        lbl_lastNameUpdateErr.setVisible(false);
        lbl_passwordUpdateErr.setVisible(false);
        lbl_hometownUpdateErr.setVisible(false);
        lbl_dateUpdateErr.setVisible(false);


        //username check
        if (username.length() == 0){
            lbl_userNameUpdateErr.setVisible(true);
            allChecked = false;
        }

        //password check
        if (password.length() < 8){
            lbl_passwordUpdateErr.setVisible(true);
            allChecked = false;
        }

        //first name check
        if (firstname.length() < 2){
            lbl_firstNameUpdateErr.setVisible(true);
            allChecked = false;
        }

        //last name check
        if (lastname.length() < 2){
            lbl_lastNameUpdateErr.setVisible(true);
            allChecked = false;
        }

        //date check
        if (!isBiggerThen18(dp_dateUpdate)){
            lbl_dateUpdateErr.setVisible(true);
            allChecked = false;
        }

        //hometown check
        if (hometown.length() < 2){
            lbl_hometownUpdateErr.setVisible(true);
            allChecked = false;
        }

        if (allChecked){
            toSend.add(username);
            toSend.add(password);
            toSend.add(dp_dateUpdate.getEditor().getText());
            toSend.add(firstname);
            toSend.add(lastname);
            toSend.add(hometown);
            m_controller.update_user(username, toSend);
        }
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
        if (response.equals("Created :)"))
            popInfo(response);
        else
            popProblem(response);
    }

    public void read_response (ArrayList<String> response){
        if (response != null)
            popInfo(response);
        else
            popProblem("Username does not exist!");
    }

    public void delete_response (String response){
        if (response.equals("Delete success"))
            popInfo(response);
        else
            popProblem(response);
    }

    public void update_response(String response){
        if (response.equals("Update success"))
            popInfo(response);
        else
            popProblem(response);
    }

    /**
     * this method checks if a user age is above 18
     * @param age - datepicker object
     * @return true if date is larger then 18, false otherwise
     */
    private boolean isBiggerThen18 (DatePicker age) {
        LocalDate Date = age.getValue();
        LocalDate today = LocalDate.now();
        if (Date.getYear() + 18 > today.getYear())
            return false;
        else if (Date.getYear() + 18 == today.getYear()) {
            if (Date.getMonthValue() > today.getMonthValue())
                return false;
            else if (Date.getMonthValue() == today.getMonthValue()) {
                if (Date.getDayOfMonth() > today.getDayOfMonth())
                    return false;
                else
                    return true;
            }
        }

        return true;
    }

    public void set_controller(MainController c) {
        this.m_controller = c;
    }

    private void popProblem(String description) {
        Alert prob = new Alert(Alert.AlertType.ERROR);

        prob.setContentText(description);
        prob.showAndWait();
    }

    private void popInfo(ArrayList<String> data) {
        Alert prob = new Alert(Alert.AlertType.INFORMATION);

        String s = "Username: " + data.get(0) + "\n" +
                "Password: " + data.get(1) + "\n" +
                "Birthday: " + data.get(2) + "\n" +
                "First Name: " + data.get(3) + "\n" +
                "Last Name: " + data.get(4) + "\n" +
                "Hometown: " + data.get(5) + "\n";
        prob.setContentText(s);
        prob.showAndWait();
    }

    private void popInfo(String data){
        Alert prob = new Alert(Alert.AlertType.INFORMATION);
        prob.setContentText(data);
        prob.showAndWait();
    }

    public void send_read_update(MouseEvent mouseEvent) {
        String s = tf_usernameread_update.getText();
        if (s.length() == 0){
            popProblem("Please insert a valid Username");
        }
        else
            m_controller.read_update_user(s);
    }

    public void read_update_response (ArrayList<String> response){
        if (response != null) {
            String s = response.get(0);
            tf_usernameUpdate.setText(s);
            btn_sendUpdate.setDisable(false);
        }
        else
            popProblem("Username does not exist!");
    }


}
