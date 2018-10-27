package Model;

import java.sql.*;
import java.util.ArrayList;

public class Model {
    //Const
    private final String DB_URL = "jdbc:sqlite:/DB/DataBase.db";
    public enum tables {Users}

    // helpful attributes
    private String m_create_status;
    private String m_update_status;
    private String m_delete_status;
    private ResultSet m_results = null;

    // getters
    public String get_create_status(){
        return m_create_status;
    }
    public String get_update_status() {
        return m_update_status;
    }
    public String get_delete_status() {
        return m_delete_status;
    }
    public ResultSet get_results() {
        return m_results;
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
        read_user(userName);
        try{
            if (!m_results.next()){
                return false;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * a method to create a user
     * @param attributes - list of needed attributed by a specific order!
     */
    public void create_user(ArrayList<String> attributes){
        if (attributes.size() != 6)
            return;

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
            m_create_status = "Created";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            m_create_status = "Failed!";
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

        }catch (SQLException e){
            System.out.println(e.getMessage());
            m_results = null;
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
            m_update_status = "User: " + toChange +" does not exist!";
            return;
        }
        else{
            if (user_exist(newatt.get(0))){
                m_update_status = "Username: " + newatt.get(0) + " already exist.";
                return;
            }
        }

        String sql = "UPDATE Users "
                + " SET UserName = ? , "
                + "Password = ? , "
                + "FullName = ? , "
                + "Birthday = ? , "
                + "FirstName = ? , "
                + "LastName = ? , "
                + "Hometown = ? "
                + "WHERE UserName = " + toChange;

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, newatt.get(0));
            pstmt.setString(2, newatt.get(1));
            pstmt.setString(3, newatt.get(2));
            pstmt.setString(4, newatt.get(3));
            pstmt.setString(5, newatt.get(4));
            pstmt.setString(6, newatt.get(5));

            // update
            pstmt.executeUpdate();
            m_update_status = "Update success";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            m_update_status = "Error: Could not update!";
        }
    }

    /**
     * a method to delete user
     * @param toDelete - username to delete
     */
    public void delete_user(String toDelete){
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = this.make_connection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, toDelete);

            // execute the delete statement
            pstmt.executeUpdate();
            m_delete_status = "Delete success";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
