package tn.SIRIUS.services;

import javafx.fxml.Initializable;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserService {
    private Connection con;
    public UserService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean isPasswordMatch(String username,String password,String passwordSeen){
        String qry = "SELECT firstName,password FROM users WHERE  firstName = ? AND password = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setString(1,username);
            stm.setString(2,password);
            stm.setString(2,passwordSeen);
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
    private boolean performTwoFactorAuthentication(String username) {

        return true;
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
    public boolean isUserActive(int id) {
        String query = "SELECT isActive FROM users WHERE idUser = ?";
        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("isActive");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void updateUserIsActive(int id, boolean newValue) {
            String updateQuery = "UPDATE users SET isActive = ? WHERE idUser = ?";
        try {
            PreparedStatement stm = con.prepareStatement(updateQuery);
            stm.setBoolean(1, newValue);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public int getNbrStudent(String someParameter) {
        String sql = "SELECT COUNT(idUser) AS studentCount FROM users WHERE role = ?";
        int nbStudents = 0;
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, someParameter);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                nbStudents = rs.getInt("studentCount");
            }

            return nbStudents;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nbStudents;
    }
    public List<User> searchUsers(String searchTerm) {
        List<User> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE firstName LIKE ? OR lastName LIKE ? OR role LIKE ? AND image IS NOT NULL";

        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            String likeTerm = "%" + searchTerm + "%";
            preparedStatement.setString(1, likeTerm); // firstName
            preparedStatement.setString(2, likeTerm); // lastName
            preparedStatement.setString(3, likeTerm); // role
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setRoles(resultSet.getString("role"));
                user.setImage(resultSet.getString("image"));

                searchResults.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    public boolean getPassword(String username){
        String sql = "SELECT question FROM users WHERE firstName LIKE ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public String getquestion(String q){

        return q;

    }
}
