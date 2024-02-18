package tn.SIRIUS.services;

import tn.SIRIUS.entities.QuizQuestion;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuizQuestionService{
    private Connection con;
    public QuizQuestionService(){ con = MyDB.instance.getCon();}
    public boolean add(QuizQuestion qq){
        String query = "INSERT INTO QUIZQUESTIONS (quiz,question,choice_1,choice_2,choice_3,choice_4,correct_choice) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, qq.getQuiz());
            statement.setString(2,qq.getQuestion());
            statement.setString(3,qq.getChoice_1());
            statement.setString(4, qq.getChoice_2());
            statement.setString(5, qq.getChoice_3());
            statement.setString(6, qq.getChoice_4());
            statement.setString(7, qq.getCorrect_choice());
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
    public List<QuizQuestion> getAllByQuiz(int idQuiz){
        String query = "SELECT * FROM QUIZQUESTIONS WHERE quiz = ?";
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idQuiz);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                quizQuestions.add(new QuizQuestion(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)
                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return quizQuestions;
    }
    public boolean update(QuizQuestion qq){
        String query = "UPDATE QUIZQUESTIONS SET question = ? , choice_1 = ? , choice_2 = ? , choice_3 = ?, choice_4 = ?, correct_choice = ? WHERE idQuestion = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, qq.getQuestion());
            statement.setString(2,qq.getChoice_1());
            statement.setString(3, qq.getChoice_2());
            statement.setString(4,qq.getChoice_3());
            statement.setString(5,qq.getChoice_4());
            statement.setString(6,qq.getCorrect_choice());
            statement.setInt(7,qq.getIdQuestion());
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
    public boolean delete(int idQuestion){
        String qry = "DELETE FROM QUIZQUESTIONS WHERE idQuestion = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idQuestion);
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
    public int getNextIdAvailable(){
        String query = "SELECT * FROM QUIZQUESTIONS ORDER BY idQuestion DESC LIMIT 1";
        int idAvailable = 0;
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                idAvailable = rs.getInt(1);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return idAvailable;
    }

}
