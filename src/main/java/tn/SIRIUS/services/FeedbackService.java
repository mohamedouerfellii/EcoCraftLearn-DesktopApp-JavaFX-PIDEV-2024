package tn.SIRIUS.services;

import tn.SIRIUS.entities.Feedback;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeedbackService {
    private Connection con;
    public FeedbackService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean addFeedback(Feedback feedback){
        String query = "INSERT INTO COURSEFEEDBACKS (owner,course,content,rate) VALUES (?,?,?,?)";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,feedback.getOwner().getId());
            stm.setInt(2,feedback.getCourse());
            stm.setString(3,feedback.getContent());
            stm.setInt(4,feedback.getRate());
            if(stm.executeUpdate() == 1){
                String query2 = "SELECT rate, nbrPersonRated FROM COURSES WHERE idCourse = ?";
                stm = con.prepareStatement(query2);
                stm.setInt(1,feedback.getCourse());
                ResultSet rs = stm.executeQuery();
                while (rs.next()){
                    int OldNbrPersonRated = rs.getInt(2);
                    int newNbrPersonRated = OldNbrPersonRated+1;
                    double oldSommeRate = OldNbrPersonRated * rs.getFloat(1);
                    float newRate = (float) (( oldSommeRate + feedback.getRate() ) / newNbrPersonRated);
                    String query3 = "UPDATE COURSES SET rate = ?, nbrPersonRated = ? WHERE idCourse = ?";
                    stm = con.prepareStatement(query3);
                    stm.setFloat(1,newRate);
                    stm.setInt(2,newNbrPersonRated);
                    stm.setInt(3,feedback.getCourse());
                    if(stm.executeUpdate() == 1){
                        stm.close();
                        return true;
                    }
                }
                stm.close();
                return true;
            }
            stm.close();
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public Feedback getFeedback(int idUser,int idCourse){
        String query = "SELECT * FROM COURSEFEEDBACKS WHERE owner = ? AND course = ?";
        Feedback feedback = new Feedback();
        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idUser);
            stm.setInt(2,idCourse);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                feedback.setId(rs.getInt(1));
                feedback.setOwner(new User(rs.getInt(2),"","","","" ));
                feedback.setCourse(rs.getInt(3));
                feedback.setContent(rs.getString(4));
                feedback.setRate(rs.getInt(5));
                stm.close();
                return feedback;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return feedback;
    }
}
