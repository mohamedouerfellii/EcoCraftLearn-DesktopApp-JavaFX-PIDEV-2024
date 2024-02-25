package tn.SIRIUS.services;

import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private Connection con;

    public UserService(){
        con = MyDB.getInstance().getCon();
    }
    public User getPostById(int id){
        String query = "SELECT * FROM users WHERE idUser = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                User owner = new User(
                        rs.getInt("idUser"),
                        rs.getString("FirstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("numTel"),
                        rs.getString("role"),
                        rs.getString("gender"),
                        rs.getInt("isActive"),
                        rs.getInt("nbrPtsCollects"),
                        rs.getString("image")
                );
              return owner;
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
