package tn.SIRIUS.services;

import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

public class PostService implements ICRUD<Post,User> {

    private Connection con;

    public PostService() {
        con = MyDB.getInstance().getCon();
    }

    @Override
    public int add(Post post) {
        String query = "INSERT INTO posts (content,attachment,owner,postedDate) VALUES (?,?,?,?)";
        try {

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, post.getContent());
            statement.setString(2, post.getAttachment());
            statement.setInt(3, post.getOwner());
            statement.setString(4, post.getPostedDate().toString());
            if (statement.executeUpdate() == 1) {
                statement.close();
                return 1;
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }


    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        String query = "SELECT p.idPost AS postId, p.content, p.attachment, p.owner ,p.postedDate," +
                "       u.idUser AS userId, u.firstName, u.lastName, u.email, u.password, u.numTel, u.role, u.gender, u.isActive, u.nbrPtsCollects, u.image " +
                "FROM posts p " +
                "JOIN users u ON p.owner = u.idUser;";
        try (Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs.getInt("userId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), rs.getString("numTel"), rs.getString("role"), rs.getString("gender"), rs.getInt("isActive"), rs.getInt("nbrPtsCollects"), rs.getString("image"));
                Post post = new Post(rs.getInt("postId"), rs.getString("content"), rs.getString("attachment"), rs.getInt("owner"), rs.getTimestamp("postedDate").toLocalDateTime(), user);
                list.add(post);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }


    @Override
    public int update(Post post) {

        String query = "UPDATE posts SET content = ?, attachment = ? WHERE idPost = ? ";
        try {

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, post.getContent());
            statement.setString(2, post.getAttachment());
            statement.setInt(3, post.getIdPost());
            if (statement.executeUpdate() == 1) {
                statement.close();
                return 1;
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(Post post) {

        String query = "delete from posts  WHERE idPost = ? ";
        try {

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, post.getIdPost());
            if (statement.executeUpdate() == 1) {
                statement.close();
                return 1;
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public Post getPostById(int id) {
        String query = "SELECT posts.*, users.* FROM posts JOIN users ON posts.owner = users.idUser WHERE idPost = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
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
                Post post = new Post(rs.getInt("idPost"), rs.getString("content"), rs.getString("attachment"), owner.getIdUser(), rs.getTimestamp("postedDate").toLocalDateTime(), owner);
                return post;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    public int updateNbLikes(int id, int act) {

        String query = "UPDATE postslikes SET action = ? WHERE idLike = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, act);
            statement.setInt(2, id);
            if (statement.executeUpdate() == 1) return 1;
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int addLike(Post post, User user, int act) {

        String query = "INSERT INTO postslikes (post, idUser, action) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, post.getIdPost());
            statement.setInt(2, user.getIdUser());
            statement.setInt(3, act);
            if (statement.executeUpdate() == 1) return 1;
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;

    }

    public int deleteLike(int id) {

        String query = "DELETE FROM postslikes WHERE idLike = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);

            if (statement.executeUpdate() == 1) return 1;
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

   public  int getLikes(Post post) {
        String query = "SELECT (SELECT COUNT(*) FROM postslikes WHERE post = ? AND action = 1) - (SELECT COUNT(*) FROM postslikes WHERE post = ? AND action = -1) AS totalLikes";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, post.getIdPost());
            statement.setInt(2, post.getIdPost());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalLikes");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }


    public int likeExist(Post post, User user) {

        String query = "SELECT * FROM postslikes WHERE post = ? AND idUser = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, post.getIdPost());
            statement.setInt(2, user.getIdUser());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
               return rs.getInt(1);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }


    public int liketype(Post post, User user) {

        String query = "SELECT * FROM postslikes WHERE post = ? AND idUser = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, post.getIdPost());
            statement.setInt(2, user.getIdUser());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(4);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public TreeMap<User, Integer> PopularUser() {
        Map<User, Integer> popularUsers = new HashMap<>();
        String sqlQuery = "SELECT u.idUser, u.firstName, u.image, u.lastName, " +
                "SUM(CASE WHEN pl.action = 1 THEN 1 ELSE -1 END) AS totalLikesDifference " +
                "FROM users u " +
                "JOIN posts p ON u.idUser = p.owner " +
                "JOIN postslikes pl ON p.idPost = pl.post " +
                "GROUP BY u.idUser, u.firstName, u.image, u.lastName";

        try (PreparedStatement statement = con.prepareStatement(sqlQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String firstName = resultSet.getString(2);
                    String image = resultSet.getString(3);
                    String lastName = resultSet.getString(4);
                    int totalLikesDifference = resultSet.getInt("totalLikesDifference");
                    User currentUser = new User(id, firstName, lastName, image);
                    popularUsers.put(currentUser, totalLikesDifference);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Sort the map by values using a custom comparator
        TreeMap<User, Integer> sortedPopularUsers = new TreeMap<>((u1, u2) -> {
            int diff = popularUsers.get(u2) - popularUsers.get(u1);
            if (diff != 0) {
                return diff;
            } else {
                // If the number of likes is the same, compare by user ID
                return u1.getIdUser() - u2.getIdUser();
            }
        });
        sortedPopularUsers.putAll(popularUsers);
        return sortedPopularUsers;
    }



    public  List<Post> getSavedPosts(User user){
        List<Post> list = new ArrayList<>();
        String qry="select * from savedPosts where owner= ? ";
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, user.getIdUser());
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Post post = getPostById(rs.getInt("post"));
                    list.add(post);


            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void deleteSavedPosts(User user,Post post){
        String qry="delete from savedPosts where owner= ? and post= ? ";
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, user.getIdUser());
            statement.setInt(2, post.getIdPost());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void savePost(User user,Post post){
        String qry="insert into savedPosts (owner,post) values(?,?)";
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, user.getIdUser());
            statement.setInt(2, post.getIdPost());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
