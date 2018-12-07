package Controller;

import Model.Model;

import java.util.ArrayList;

public class LoginController {

    private Model m;


    public void create_user(ArrayList<String> toSend) {
        m.create_user(toSend);
    }
}
