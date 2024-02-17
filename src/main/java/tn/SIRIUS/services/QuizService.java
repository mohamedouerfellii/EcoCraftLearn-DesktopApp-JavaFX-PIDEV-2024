package tn.SIRIUS.services;

import tn.SIRIUS.entities.Quiz;
import tn.SIRIUS.entities.Section;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizService{
    private Connection con;
    public QuizService(){ con = MyDB.getInstance().getCon();}
    public boolean add(Quiz quiz){
        String query = "INSERT INTO QUIZZES (section) VALUES (?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, quiz.getSection());
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
    public Quiz getQuizBySection(int section){
        String query = "SELECT * FROM QUIZZES WHERE section = ?";
        Quiz quiz = new Quiz();
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, section);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                        quiz.setIdQuiz(rs.getInt(1));
                        quiz.setSection(rs.getInt(2));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return quiz;
    }
    public boolean delete(int quizId){
        String qry = "DELETE FROM QUIZZES WHERE idQuiz = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,quizId);
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
}
