package tn.SIRIUS.services;

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
}
