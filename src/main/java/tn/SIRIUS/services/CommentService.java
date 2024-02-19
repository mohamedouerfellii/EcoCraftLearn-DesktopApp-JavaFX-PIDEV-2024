package tn.SIRIUS.services;

import tn.SIRIUS.entities.Comment;
import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentService  {
    private Connection con;

    public CommentService(){
        con = MyDB.getInstance().getCon();
    }
    public int add(Comment comment) {
        String query = "INSERT INTO comments (post,owner,content,attachment) VALUES (?,?,?,?)";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,comment.getPost().getIdPost());
            statement.setInt(2,comment.getOwner().getIdUser());
            statement.setString(3, comment.getContent());
            statement.setString(4,comment.getAttachment());
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


    public List<Comment> getAll() {
        List<Comment> list = new ArrayList<>();
        String query = "SELECT c.idComment, c.content, c.attachment, c.owner, " +
                "p.idPost, p.content as postContent, p.attachment as postAttachment, p.owner as postOwner, p.postedDate as postPostedDate, " +
                "u.idUser, u.firstName, u.lastName, u.email, u.password, u.numTel, u.role, u.gender, u.isActive, u.nbrPtsCollects, u.image " +
                "FROM comments c " +
                "JOIN posts p ON c.post = p.idPost " +
                "JOIN users u ON c.owner = u.idUser";
        try (Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Comment comment = new Comment(
                        rs.getInt("idComment"),
                        new Post(
                                rs.getInt("idPost"),
                                rs.getString("postContent"),
                                rs.getString("postAttachment"),
                                rs.getInt("postOwner"),
                                rs.getTimestamp("postPostedDate").toLocalDateTime()
                        ),
                        new User(
                                rs.getInt("idUser"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("numTel"),
                                rs.getString("role"),
                                rs.getString("gender"),
                                rs.getInt("isActive"),
                                rs.getInt("nbrPtsCollects"),
                                rs.getString("image")
                        ),
                        rs.getString("content"),
                        rs.getString("attachment")
                );
                list.add(comment);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }


    public int update(Comment comment) {

        String query = "UPDATE comments SET content = ?, attachment = ? WHERE idComment = ? ";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, comment.getContent());
            statement.setString(2, comment.getAttachment());
            statement.setInt(3,comment.getIdComment());
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


    public int delete(Comment comment) {

        String query = "delete from comments  WHERE idComment = ? ";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, comment.getIdComment());
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
