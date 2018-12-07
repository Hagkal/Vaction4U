package View;

import Controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LoginView {

    private LoginController m_controller;


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


    public void setCreateForm(MouseEvent mouseEvent) throws IOException {
        Stage registration = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/fxml/createXML.fxml"));
        registration.setTitle("Registration");
        registration.setScene(new Scene(root, 800, 400));
        registration.setResizable(false);
        registration.show();
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

    /**
     * this method checks if a user age is above 18
     * @param age - datepicker object
     * @return true if date is larger then 18, false otherwise
     */
    private boolean isBiggerThen18 (DatePicker age) {
        LocalDate Date = age.getValue();
        LocalDate today = LocalDate.now();
        if (Date == null || Date.getYear() + 18 > today.getYear())
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




}
