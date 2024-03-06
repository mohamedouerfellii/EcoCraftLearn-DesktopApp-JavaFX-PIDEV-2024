package tn.SIRIUS.services;

import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CourseParticipationService {
    private Connection con;
    public CourseParticipationService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean enrollNewCourse(int participant,int course){
        String query = "INSERT INTO COURSEPARTICIPATIONS (participant,course) VALUES (?,?)";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,participant);
            stm.setInt(2,course);
            if(stm.executeUpdate() == 1){
                stm.close();
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean unrollCourse(int idCourse,int idUser){
        String query = "DELETE FROM COURSEPARTICIPATIONS WHERE course = ? AND participant = ?";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idCourse);
            stm.setInt(2,idUser);
            if(stm.executeUpdate() == 1){
                stm.close();
                return true;
            }
            stm.close();
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public List<Integer> getEnrolledUsers(int idCourse){
        String qry = "SELECT participant FROM COURSEPARTICIPATIONS WHERE course = ?";
        List<Integer> participants = new ArrayList<>();
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idCourse);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                participants.add(rs.getInt("participant"));
            }
            stm.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return participants;
    }
    public Set<Integer> getTotNbrStudentByTutor(int idTutor){
        CourseService courseService = new CourseService();
        List<Integer> coursesIds = courseService.getListIdCoursesByTutor(idTutor);
        Set<Integer> usersEnrolledId = new HashSet<>();
        for(int idC : coursesIds){
            List<Integer> listIdByCourse = getEnrolledUsers(idC);
            usersEnrolledId.addAll(listIdByCourse);
        }
        return usersEnrolledId;
    }
    public int getLastMonthEnrolled(int idTutor){
        CourseService courseService = new CourseService();
        List<Integer> coursesIds = courseService.getListIdCoursesByTutor(idTutor);
        Set<Integer> usersEnrolled = new HashSet<>();
        for(int idC : coursesIds) {
            String qry = "SELECT participant FROM COURSEPARTICIPATIONS WHERE course = ? AND participationDate >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)";
            try {
                PreparedStatement stm = con.prepareStatement(qry);
                stm.setInt(1,idC);
                ResultSet rs = stm.executeQuery();
                while (rs.next()){
                    usersEnrolled.add(rs.getInt("participant"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return usersEnrolled.size();
    }
    public List<Integer> countGenderStudentByTutor(int idTutor){
        UserService userService = new UserService();
        List<Integer> genderNumberList = new ArrayList<>();
        int maleCount = 0;
        int femaleCount = 0;
        Set<Integer> enrolledIds = getTotNbrStudentByTutor(idTutor);
        for(int id : enrolledIds){
            String gender = userService.getUserGender(id);
            if(gender.equals("Male")) maleCount++;
            else femaleCount++;
        }
        genderNumberList.add(maleCount);
        genderNumberList.add(femaleCount);
        return genderNumberList;
    }
}
