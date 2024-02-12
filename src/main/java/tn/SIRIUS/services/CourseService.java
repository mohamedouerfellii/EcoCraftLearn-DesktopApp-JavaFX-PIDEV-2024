package tn.SIRIUS.services;

import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements ICRUD<Course, User> {
    private Connection con;
    public CourseService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(Course course){
        String query = "INSERT INTO courses (image,title,description,tutor,duration,price,nbrSection) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, course.getImage());
            statement.setString(2,course.getTitle());
            statement.setString(3,course.getDescription());
            statement.setInt(4,course.getTutor());
            statement.setString(5, course.getDuration());
            statement.setFloat(6,course.getPrice());
            statement.setInt(7,0);
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
                        rs.getDate("postedDate").toString(),0
                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return courseList;
    }
    @Override
    public int update(Course course){
        return 1;
    }
    @Override
    public int delete(Course course){
        return 1;
    }
}
