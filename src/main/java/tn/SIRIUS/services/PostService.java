package tn.SIRIUS.services;

import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostService implements ICRUD<Post,User> {

   private Connection con;

    public PostService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public int add(Post post) {
        String query = "INSERT INTO posts (content,attachment,owner,postedDate) VALUES (?,?,?,?)";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, post.getContent());
            statement.setString(2,post.getAttachment());
            statement.setInt(3, post.getOwner());
            statement.setString(4,post.getPostedDate().toString());
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


    public Map<Post, User> getAll() {
        Map<Post, User> postUserMap = new HashMap<>();
        String query = "SELECT p.idPost AS postId, p.content, p.attachment, p.owner ,p.postedDate," +
                "       u.idUser AS userId, u.firstName, u.lastName, u.email, u.password, u.numTel, u.role, u.gender, u.isActive, u.nbrPtsCollects, u.image " +
                "FROM posts p " +
                "JOIN users u ON p.owner = u.idUser;";
        try (Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                User user = new User(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), rs.getString("numTel"), rs.getString("role"), rs.getString("gender"), rs.getInt("isActive"), rs.getInt("nbrPtsCollects"), rs.getString("image"));
                Post post = new Post(rs.getInt("postId"), rs.getString("content"), rs.getString("attachment"), rs.getInt("owner"), rs.getTimestamp("postedDate").toLocalDateTime());
                postUserMap.put(post, user);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return postUserMap;
    }


    @Override
    public int update(Post post) {

        String query = "UPDATE posts SET content = ?, attachment = ? WHERE idPost = ? ";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, post.getContent());
            statement.setString(2,post.getAttachment());
            statement.setInt(3,post.getIdPost());
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
    public int delete(Post post) {

        String query = "delete from posts  WHERE idPost = ? ";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, post.getIdPost());
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
}
