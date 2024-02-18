package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.QuizQuestion;

public class QuizItemTutorController {
    @FXML
    private CheckBox checkChoice1;
    @FXML
    private CheckBox checkChoice2;
    @FXML
    private CheckBox checkChoice3;
    @FXML
    private CheckBox checkChoice4;
    @FXML
    private Text correctAnswer;
    @FXML
    private Text question;
    @FXML
    private Button editQuestionBtn;
    @FXML
    private Button deleteQuestionBtn;
    private CoursesMainPageController coursesMainPageController;
    private QuizQuestion qq;
    public void setData(QuizQuestion quizQuestion,boolean isUpdated,CoursesMainPageController con){
        editQuestionBtn.setVisible(isUpdated);
        deleteQuestionBtn.setVisible(isUpdated);
        question.setText(quizQuestion.getQuestion());
        checkChoice1.setText("1) "+quizQuestion.getChoice_1());
        checkChoice2.setText("2) "+quizQuestion.getChoice_2());
        checkChoice3.setText("3) "+quizQuestion.getChoice_3());
        checkChoice4.setText("4) "+quizQuestion.getChoice_4());
        correctAnswer.setText(quizQuestion.getCorrect_choice());
        switch (quizQuestion.getCorrect_choice()) {
            case "Choice 1" -> checkChoice1.setSelected(true);
            case "Choice 2" -> checkChoice2.setSelected(true);
            case "Choice 3" -> checkChoice3.setSelected(true);
            default -> checkChoice4.setSelected(true);
        }
        qq = quizQuestion;
        coursesMainPageController = con;
    }
    @FXML
    public void editQuestionBtnClicked(MouseEvent event){
        coursesMainPageController.setQuestionEditForm(qq);
    }
    @FXML
    public void deleteQuestionBtnClicked(MouseEvent event){
        coursesMainPageController.deleteQuestionQuiz(qq);
    }
}
