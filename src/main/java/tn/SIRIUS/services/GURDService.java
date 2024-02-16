package tn.SIRIUS.services;

import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GURDService implements ICRUD<User> {
    private Connection con;
    public GURDService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(User user){
        String query = "INSERT INTO users (`firstName`, `lastName`, `email`, `password`, `numTel`, `role`, `gender`, `image`) VALUES (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5,user.getNumber());
            statement.setString(6, user.getRoles());
            statement.setString(7, user.getGender());
            statement.setString(8, user.getImage());


            if(statement.executeUpdate() == 1){
                statement.close();
                return 1;
            }
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    @Override
    public List<User> getAll(){
        String query = "SELECT * FROM users  ORDER BY role DESC";
        List<User> userList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                userList.add(new User(
                        rs.getInt("idUser"),rs.getString("firstname"),
                        rs.getString("lastname"),rs.getInt("numTel"),
                        rs.getString("Email"),rs.getString("gender"),
                        rs.getString("password"),rs.getString("role"),
                        rs.getString("image"),rs.getBoolean("isActive"),
                        rs.getInt("nbrPtsCollects")));

            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return userList;
    }
    @Override
    public boolean update(User user){
        String query = "UPDATE `users` SET `firstName`=?,`lastName`=?,`email`=?,`password`=?,`numTel`=?,`gender`=?,`image`=? where idUser=? ";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2,user.getLastName());
            statement.setString(3,user.getEmail());
            statement.setString(4,user.getPassword());
            statement.setInt(5, user.getNumber());
            statement.setString(6,user.getGender());
            statement.setString(7, user.getImage());
            statement.setInt(8, user.getId());

            if(statement.executeUpdate() == 1){
                statement.close();
                return true;
            }
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean delete(int userId){
        String qry = "DELETE FROM Users WHERE idUser = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,userId);
            if(stm.executeUpdate() == 1){
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
