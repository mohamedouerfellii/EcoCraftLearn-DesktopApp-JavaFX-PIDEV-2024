package tn.SIRIUS.services;

import tn.SIRIUS.entities.Feedback;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public List<Feedback> getFeedbacksCourse(int idCourse){
        String query = "SELECT * FROM COURSEFEEDBACKS WHERE course = ?";
        List<Feedback> feedbacks = new ArrayList<>();
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idCourse);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
            UserService userService = new UserService();
            User user = userService.getCourseTutor(rs.getInt(2));
            feedbacks.add(
                    new Feedback(rs.getInt(1),user,rs.getString(6),
                            rs.getString(4),rs.getInt(5),rs.getInt(3)));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return feedbacks;
    }
    public boolean deleteFeedback(Feedback feedback){
        String query = "DELETE FROM COURSEFEEDBACKS WHERE idFeedback = ?";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,feedback.getId());
            if(stm.executeUpdate() == 1){
                String query2 = "SELECT rate, nbrPersonRated FROM COURSES WHERE idCourse = ?";
                stm = con.prepareStatement(query2);
                stm.setInt(1,feedback.getCourse());
                ResultSet rs = stm.executeQuery();
                while (rs.next()){
                    int OldNbrPersonRated = rs.getInt(2);
                    int newNbrPersonRated = OldNbrPersonRated-1;
                    float newRate = 0f;
                    if(newNbrPersonRated!=0){
                        double oldSommeRate = OldNbrPersonRated * rs.getFloat(1);
                        newRate = (float) (( oldSommeRate - feedback.getRate() ) / newNbrPersonRated);
                    }
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
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean updateFeedback(Feedback feedback){
        Feedback oldFeedback = getFeedback(feedback.getOwner().getId(),feedback.getCourse());
        if(oldFeedback.equals(feedback)){
            return true;
        } else {
            String query = "SELECT rate, nbrPersonRated FROM COURSES WHERE idCourse = ?";
            try {
                PreparedStatement stm = con.prepareStatement(query);
                stm.setInt(1,feedback.getCourse());
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int nbrPersonRated = rs.getInt(2);
                    double oldSommeRate = nbrPersonRated * rs.getFloat(1);
                    double newSommeRate = oldSommeRate - oldFeedback.getRate();
                    float newRate = (float) ((newSommeRate + feedback.getRate()) / nbrPersonRated);
                    String query2 = "UPDATE COURSES SET rate = ? WHERE idCourse = ?";
                    stm = con.prepareStatement(query2);
                    stm.setFloat(1, newRate);
                    stm.setInt(2, feedback.getCourse());
                    if (stm.executeUpdate() == 1) {
                        String query3 = "UPDATE COURSEFEEDBACKS SET rate = ?, content = ? WHERE idFeedback = ?";
                        stm = con.prepareStatement(query3);
                        stm.setInt(1,feedback.getRate());
                        stm.setString(2,feedback.getContent());
                        stm.setInt(3,oldFeedback.getId());
                        if(stm.executeUpdate() == 1){
                            stm.close();
                            return true;
                        }
                         else return false;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public List<Feedback> getFeedbackByTutor(int idTutor){
        CourseService courseService = new CourseService();
        UserService userService = new UserService();
        List<Integer> coursesIds = courseService.getListIdCoursesByTutor(idTutor);
        List<Feedback> feedbacks = new ArrayList<>();
        String qry = "SELECT * FROM COURSEFEEDBACKS WHERE course = ?";
        for(int id : coursesIds){
            try {
                PreparedStatement stm = con.prepareStatement(qry);
                stm.setInt(1,id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()){
                    User owner = userService.getCourseTutor(rs.getInt("owner"));
                    feedbacks.add(
                            new Feedback(rs.getInt("idFeedback"),owner,
                                    rs.getString("postedDate"),rs.getString("content"),
                                    rs.getInt("rate"),rs.getInt("course"))
                    );
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return feedbacks;
    }
}
