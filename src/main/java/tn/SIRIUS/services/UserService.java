package tn.SIRIUS.services;

import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserService {
    private Connection con;
    public UserService(){
        con = MyDB.getInstance().getCon();
    }
    public UserService(Connection con) {
        this.con = con;
    }
    public int isPasswordMatch(User user) {
        String qry = "SELECT * FROM users WHERE firstName = ? AND role = ?";
        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getRoles());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                String enteredPassword = String.valueOf(user.getPassword().hashCode());

                // Check if the entered password matches the stored hashed password
                if (storedHashedPassword.equals(enteredPassword)) {
                    return rs.getInt("idUser");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public int isPasswordMatch2(User user) {
        String qry = "SELECT * FROM users WHERE firstName = ? AND role = ?";
        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setString(1, user.getFirstName());
            stm.setString(2, user.getRoles());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String storedHashedPassword = rs.getString("password");
                String enteredPassword = user.getPassword();

                // Check if the entered password matches the stored hashed password
                if (storedHashedPassword.equals(enteredPassword)) {
                    return rs.getInt("idUser");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
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

    public String getPassword(String username){
        String sql = "SELECT * FROM users WHERE firstName LIKE ?";
        try{
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("password");
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public String getquestion(String q) throws SQLException {
        String sql = "SELECT question FROM users WHERE firstName = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, q);
        ResultSet resultSet = stm.executeQuery();
        while(resultSet.next())
        {
            return resultSet.getString("question");

        }
        return null;

    }
    public boolean confirmAnswer(String q,String a) throws SQLException {
        String sql = "SELECT * FROM users WHERE question = ? AND answer = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, q);
        stm.setString(2, a);
        ResultSet resultSet = stm.executeQuery();
        while(resultSet.next())
        {
            return true;

        }
        return false;
    }


    public User getUserById(int id){
        String sql = "SELECT * FROM users WHERE idUser = ?";
        try{
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,id);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setNumber(resultSet.getInt("numTel"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getString("gender"));
                user.setPassword(resultSet.getString("password"));
                user.setActive(resultSet.getBoolean("isActive"));
                user.setNbrPtsCollects(resultSet.getInt("nbrPtsCollects"));
                user.setRoles(resultSet.getString("role"));
                user.setImage(resultSet.getString("image"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByEmail(String email){
        String sql = "SELECT * FROM users WHERE email = ?";
        try{
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("idUser"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setNumber(resultSet.getInt("numTel"));
                user.setEmail(resultSet.getString("email"));
                user.setGender(resultSet.getString("gender"));
                user.setPassword(resultSet.getString("password"));
                user.setActive(resultSet.getBoolean("isActive"));
                user.setNbrPtsCollects(resultSet.getInt("nbrPtsCollects"));
                user.setRoles(resultSet.getString("role"));
                user.setImage(resultSet.getString("image"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByFirstName(String name){
        String sql = "SELECT * FROM users WHERE firstName = ?";
        User user = null;
        try{
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1,name);
            ResultSet resultSet = stm.executeQuery();

            while(resultSet.next()){
                user= new User(resultSet.getInt("idUser"),resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getInt("numTel"),resultSet.getString("email"),resultSet.getString("gender") ,resultSet.getString("password"),resultSet.getString("image"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    public List<User> getAllFilterByName(){
        String query = "SELECT * FROM users  ORDER BY firstname ASC";
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
    public List<User> getAllFilterByRole(){
        String query = "SELECT * FROM users  ORDER BY role ASC";
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

    public User getOneUser(String email) throws SQLException {
        String req = "SELECT * FROM `user` where email = ?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);

        while (rs.next()) {
            user.setId(rs.getInt("idUser"));
            user.setFirstName(rs.getString("firstName"));
            user.setEmail(rs.getString("email"));
            user.setNumber(rs.getInt("num tel"));
            user.setLastName(rs.getString("lastName"));
            user.setActive(rs.getBoolean("isActive"));
            user.setPassword(rs.getString("password"));
            user.setRoles(rs.getString("role"));
            user.setGender(rs.getString("gender"));
            user.setNbrPtsCollects(rs.getInt("nbrPtsCollects"));
            user.setImage(rs.getString("image"));
            user.setQuestion(rs.getString("question"));
            user.setAnswer(rs.getString("answer"));

        }
        ps.close();
        return user;
    }
    public boolean emailExist(String email){
        GRUDService g=new GRUDService();
        return g.getAll().stream().anyMatch(e->e.getEmail().equals(email));
    }
}

