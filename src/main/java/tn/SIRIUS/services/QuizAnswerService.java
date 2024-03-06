package tn.SIRIUS.services;

import tn.SIRIUS.entities.Quiz;
import tn.SIRIUS.entities.QuizAnswer;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizAnswerService {
    private Connection con;
    private UserService userService = new UserService();
    private QuizService quizService = new QuizService();
    public QuizAnswerService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean addAnswer(QuizAnswer quizAnswer){
        String qry = "INSERT INTO QUIZANSWERS (student,quizz,result) VALUES (?,?,?)";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,quizAnswer.getStudent().getId());
            stm.setInt(2,quizAnswer.getQuiz().getIdQuiz());
            stm.setFloat(3,quizAnswer.getResult());
            if(stm.executeUpdate() == 1) return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public QuizAnswer getAnswer(int idUser,int idQuiz){
        QuizAnswer quizAnswer = new QuizAnswer();
        String qry = "SELECT * FROM QUIZANSWERS WHERE student = ? AND quizz = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idUser);
            stm.setInt(2,idQuiz);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                quizAnswer.setIdAnswer(rs.getInt("idAnswer"));
                User student = userService.getCourseTutor(rs.getInt("student"));
                Quiz quiz = quizService.getQuizById(rs.getInt("quizz"));
                quizAnswer.setStudent(student);
                quizAnswer.setQuiz(quiz);
                quizAnswer.setResult(rs.getFloat("result"));
                quizAnswer.setAnswerDate("answerDate");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return quizAnswer;
    }
}
