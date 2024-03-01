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
}
