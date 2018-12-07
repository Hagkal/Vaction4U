package Model;

import Controller.LoginController;
import Controller.MainController;

import java.sql.*;
import java.util.ArrayList;

public class Model {
    //Const
    private final String DB_URL = "jdbc:sqlite:src/main/resources/DB/DataBase.db";

    // helpful attributes
    private MainController c;
    private LoginController lc;
    private ResultSet m_results;

    // setters
    public void set_controller(MainController c){
        this.c = c;
    }



    /**
     * a method to return a connection with the Database
     * @return - a connection if success, null otherwise
     */
    private Connection make_connection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * checks if a given username already exist
     * @param userName - the given username to check
     * @return - true if exist, false otherwise
     */
    private boolean user_exist(String userName){
        String sql = "SELECT * FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, userName);
            m_results = pstmt.executeQuery();

            if (!m_results.next()){
                return false;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return true;
    }

    /**
     * a method to create a user
     * @param attributes - list of needed attributed by a specific order!
     */
    public void create_user(ArrayList<String> attributes){

        if (user_exist(attributes.get(0))){
            c.create_response("Username: " + attributes.get(0) + "\nalready exist!");
            return;
        }

        String sql = "INSERT INTO Users (UserName,Password,Birthday,FirstName,LastName,Hometown)"
                + " VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, attributes.get(0)); // username
            pstmt.setString(2, attributes.get(1)); // password
            pstmt.setString(3, attributes.get(2)); // birthday
            pstmt.setString(4, attributes.get(3)); // first name
            pstmt.setString(5, attributes.get(4)); // last name
            pstmt.setString(6, attributes.get(5)); // hometown

            pstmt.executeUpdate();
            c.create_response("Created :)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            c.create_response("Failed :/");
        }
    }

    /**
     * a method to read a user tuple by it's UserName
     * @param userName - the username desired
     */
    public void read_user(String userName){
        String sql = "SELECT * FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, userName);
            m_results = pstmt.executeQuery();
            ArrayList<String> toReturn = new ArrayList<>();

            toReturn.add(m_results.getString(1));
            toReturn.add(m_results.getString(2));
            toReturn.add(m_results.getString(3));
            toReturn.add(m_results.getString(4));
            toReturn.add(m_results.getString(5));
            toReturn.add(m_results.getString(6));

            c.read_response(toReturn);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            c.read_response(null);
        }
    }

    /**
     * a method to update the database
     * @param toChange - username to be changed
     * @param newatt - arraylist of attributes to update
     */
    public void update_user(String toChange, ArrayList<String> newatt){
        // checking if the new username already exist
        if (!user_exist(toChange)){
            c.update_response("User: " + toChange +"\ndoes not exist!");
            return;
        }
        /**else{
            if (user_exist(newatt.get(0))){
                c.create_response("Username: " + newatt.get(0) + " already exist.");
                return;
            }
        }**/

        String sql = "UPDATE Users "
                + "SET UserName = ? , "
                + "Password = ? , "
                + "Birthday = ? , "
                + "FirstName = ? , "
                + "LastName = ? , "
                + "Hometown = ? "
                + "WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, newatt.get(0));
            pstmt.setString(2, newatt.get(1));
            pstmt.setString(3, newatt.get(2));
            pstmt.setString(4, newatt.get(3));
            pstmt.setString(5, newatt.get(4));
            pstmt.setString(6, newatt.get(5));
            pstmt.setString(7, toChange);

            // update
            pstmt.executeUpdate();
            c.update_response("Update success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            c.update_response("Update failed");
        }
    }

    /**
     * a method to delete user
     * @param toDelete - username to delete
     */
    public void delete_user(String toDelete){
        if (!user_exist(toDelete)){
            c.delete_response("Username: " + toDelete + "\ndoes not exist");
            return;
        }

        String sql = "DELETE FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, toDelete);

            // execute the delete statement
            pstmt.executeUpdate();
            c.delete_response("Delete success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            c.delete_response("Delete failed");
        }
    }

    public void read_update_user(String s) {
        String sql = "SELECT * FROM Users WHERE UserName = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, s);
            m_results = pstmt.executeQuery();
            ArrayList<String> toReturn = new ArrayList<>();

            toReturn.add(m_results.getString(1));
            toReturn.add(m_results.getString(2));
            toReturn.add(m_results.getString(3));
            toReturn.add(m_results.getString(4));
            toReturn.add(m_results.getString(5));
            toReturn.add(m_results.getString(6));

            c.read_update_response(toReturn);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            c.read_update_response(null);
        }
    }
}
