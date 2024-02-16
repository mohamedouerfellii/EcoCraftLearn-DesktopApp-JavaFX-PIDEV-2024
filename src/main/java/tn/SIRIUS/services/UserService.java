package tn.SIRIUS.services;

import javafx.fxml.Initializable;
import tn.SIRIUS.utils.MyDB;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UserService {
    private Connection con;
    public UserService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean isPasswordMatch(String username,String password){
        String qry = "SELECT firstName,password FROM users WHERE  firstName = ? AND password = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setString(1,username);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
          if(rs.next())
              return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean isPasswordConfirmed(int idUser,String password){
        String qry = "SELECT password FROM users WHERE  idUser = ? AND password = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idUser);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public String getUserRole(String username) {
        String qry = "SELECT role FROM users WHERE  firstName = ?";
        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public int updateUserIsActive(int id, boolean newValue) {
            String updateQuery = "UPDATE users SET isActive = ? WHERE idUser = ?";
        try {
            PreparedStatement stm = con.prepareStatement(updateQuery);
            stm.setBoolean(1, newValue);
            stm.setInt(2, id);
            stm.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
