package tn.SIRIUS.services;

import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;

public class UserService {
    private Connection con;
    public UserService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean isPasswordMatch(int idUser,String password){
        String qry = "SELECT firstName FROM USERS WHERE idUser = ? AND password = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idUser);
            stm.setString(2,password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                stm.close();
                return true;
            }
            stm.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public User getCourseTutor(int idTutor){
        String qry = "SELECT firstName,lastName,image,email FROM USERS WHERE idUser = ?";
        User tutor = new User();
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idTutor);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                tutor = new User(
                        idTutor,rs.getString(1),rs.getString(2),
                        rs.getString(4),rs.getString(3)
                );
                return tutor;
            }
            stm.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return tutor;
    }
    public String getUserGender(int idUser){
        String qry = "SELECT gender FROM USERS WHERE idUser = ?";
        String gender = "";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idUser);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                gender = rs.getString("gender");
            }
            stm.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return gender;
    }
}
