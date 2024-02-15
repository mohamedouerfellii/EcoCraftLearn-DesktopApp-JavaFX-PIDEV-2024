package tn.SIRIUS.services;

import tn.SIRIUS.entities.Course;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements ICRUD<Course> {
    private Connection con;
    public CourseService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public boolean add(Course course){
        String query = "INSERT INTO COURSES (image,title,description,tutor,duration,price,nbrSection) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, course.getImage());
            statement.setString(2,course.getTitle());
            statement.setString(3,course.getDescription());
            statement.setInt(4,1); // static , just for testing
            statement.setString(5, course.getDuration());
            statement.setFloat(6,course.getPrice());
            statement.setInt(7,0);
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
    public List<Course> getAll(){
        String query = "SELECT * FROM COURSES ORDER BY postedDate DESC";
        List<Course> courseList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                courseList.add(new Course(
                        rs.getInt("idCourse"),rs.getString("image"),
                        rs.getString("title"),rs.getString("description"),
                        rs.getInt("tutor"),rs.getString("duration"),
                        rs.getFloat("price"),rs.getInt("nbrSection"),
                        rs.getDate("postedDate").toString(),rs.getInt("nbrRegistred")
                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return courseList;
    }
    @Override
    public boolean update(Course course){
        String query = "UPDATE COURSES SET title = ? , description = ? , duration = ? , price = ? , image = ? WHERE idCourse = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, course.getTitle());
            statement.setString(2,course.getDescription());
            statement.setString(3,course.getDuration());
            statement.setFloat(4,course.getPrice());
            statement.setString(5, course.getImage());
            statement.setInt(6,course.getId());
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
    public boolean delete(int courseId){
        String qry = "DELETE FROM COURSES WHERE idCourse = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,courseId);
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
    public Course getOne(int idCourse){
        String query = "SELECT * FROM COURSES WHERE idCourse = ?";
        Course course = new Course();
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idCourse);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                course.setId(rs.getInt("idCourse"));
                course.setImage(rs.getString("image"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setTutor(rs.getInt("tutor"));
                course.setDuration(rs.getString("duration"));
                course.setPrice(rs.getFloat("price"));
                course.setNbrSection(rs.getInt("nbrSection"));
                course.setPostedDate(rs.getDate("postedDate").toString());
                course.setNbrRegistred(rs.getInt("nbrRegistred"));
            }
            stm.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return course;
    }
    public boolean updateNbrSection(int idCourse,int nbrSection){
        String query = "UPDATE COURSES SET nbrSection = ? WHERE idCourse = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, nbrSection);
            statement.setInt(2, idCourse);
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
}
