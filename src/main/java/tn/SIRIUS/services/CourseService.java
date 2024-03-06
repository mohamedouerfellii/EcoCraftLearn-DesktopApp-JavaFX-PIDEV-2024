package tn.SIRIUS.services;

import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.User;
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
                UserService userService = new UserService();
                User tutor = userService.getCourseTutor(rs.getInt("tutor"));
                courseList.add(new Course(
                        rs.getInt("idCourse"),rs.getString("image"),
                        rs.getString("title"),rs.getString("description"),
                        tutor,rs.getString("duration"),
                        rs.getFloat("price"),rs.getInt("nbrSection"),
                        rs.getDate("postedDate").toString(),rs.getInt("nbrRegistred"),
                        rs.getFloat("rate"),
                        rs.getInt("nbrPersonRated")
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
                UserService userService = new UserService();
                User tutor = userService.getCourseTutor(rs.getInt("tutor"));
                course.setId(rs.getInt("idCourse"));
                course.setImage(rs.getString("image"));
                course.setTitle(rs.getString("title"));
                course.setDescription(rs.getString("description"));
                course.setDuration(rs.getString("duration"));
                course.setPrice(rs.getFloat("price"));
                course.setNbrSection(rs.getInt("nbrSection"));
                course.setPostedDate(rs.getDate("postedDate").toString());
                course.setNbrRegistred(rs.getInt("nbrRegistred"));
                course.setRate(rs.getFloat("rate"));
                course.setNbrPersonRated(rs.getInt("nbrPersonRated"));
                course.setTutor(tutor);
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
    public boolean updateNbrRegistered(int idCourse,int nbrRegistered){
        String query = "UPDATE COURSES SET nbrRegistred = ? WHERE idCourse = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, nbrRegistered);
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
    public List<Course> getCoursesNotRegistered(int idUser){
        List<Course> courseRegistered = getCourseRegistered(idUser);
        String query = "SELECT * FROM COURSES WHERE nbrSection > 0";
        List<Course> courseList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                UserService userService = new UserService();
                User tutor = userService.getCourseTutor(rs.getInt("tutor"));
                courseList.add(new Course(
                        rs.getInt("idCourse"),rs.getString("image"),
                        rs.getString("title"),rs.getString("description"),
                        tutor,rs.getString("duration"),
                        rs.getFloat("price"),rs.getInt("nbrSection"),
                        rs.getDate("postedDate").toString(),rs.getInt("nbrRegistred"),
                        rs.getFloat("rate"),
                        rs.getInt("nbrPersonRated")
                ));
            }
            if (!courseList.isEmpty() && !courseRegistered.isEmpty())
                courseList.removeAll(courseRegistered);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return courseList;
    }
    public List<Course> getCourseRegistered(int idUser){
        List<Course> courseRegistered = new ArrayList<>();
        String query = "SELECT * FROM COURSES C JOIN COURSEPARTICIPATIONS CP ON C.idCourse = CP.course WHERE CP.participant = ?";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idUser);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                UserService userService = new UserService();
                User tutor = userService.getCourseTutor(rs.getInt("tutor"));
                courseRegistered.add(new Course(
                        rs.getInt("idCourse"),rs.getString("image"),
                        rs.getString("title"),rs.getString("description"),
                        tutor,rs.getString("duration"),
                        rs.getFloat("price"),rs.getInt("nbrSection"),
                        rs.getDate("postedDate").toString(),rs.getInt("nbrRegistred"),
                        rs.getFloat("rate"),
                        rs.getInt("nbrPersonRated")
                ));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return courseRegistered;
    }
    public List<Integer> getListIdCoursesByTutor(int idTutor){
        String qry = "SELECT idCourse FROM COURSES WHERE tutor = ?";
        List<Integer> idLists = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idTutor);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                idLists.add(rs.getInt("idCourse"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idLists;
    }
    public List<Course> top3CoursesByTutor(int idTutor){
        String query = "SELECT * FROM COURSES ORDER BY nbrRegistred DESC LIMIT 3";
        List<Course> courseList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                UserService userService = new UserService();
                User tutor = userService.getCourseTutor(rs.getInt("tutor"));
                courseList.add(new Course(
                        rs.getInt("idCourse"),rs.getString("image"),
                        rs.getString("title"),rs.getString("description"),
                        tutor,rs.getString("duration"),
                        rs.getFloat("price"),rs.getInt("nbrSection"),
                        rs.getDate("postedDate").toString(),rs.getInt("nbrRegistred"),
                        rs.getFloat("rate"),
                        rs.getInt("nbrPersonRated")
                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return courseList;
    }
}
