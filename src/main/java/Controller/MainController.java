package Controller;

import View.MainView;
import Model.Model;

import java.util.ArrayList;

public class MainController {

    private Model m;
    private MainView v;

    public void set_model(Model m){
        this.m = m;
    }

    public void set_view(MainView v){
        this.v = v;
    }

    public void create_user(ArrayList<String> data){
        m.create_user(data);
    }

    public void create_response(String response){
        v.create_response(response);
    }

    public void delete_user(String toDelete){
        m.delete_user(toDelete);
    }

    public void delete_response(String response){
        v.delete_response(response);
    }

    public void update_user(String toUpdate, ArrayList<String> data){
        m.update_user(toUpdate, data);
    }

    public void update_response(String response){
        v.update_response(response);
    }

    public void update_read (String user_check){
        m.update_read(user_check);
    }

    public void read_user(String toRead){
        m.read_user(toRead);
    }

    public void read_response(ArrayList<String> response){
        v.read_response(response);
    }

}
