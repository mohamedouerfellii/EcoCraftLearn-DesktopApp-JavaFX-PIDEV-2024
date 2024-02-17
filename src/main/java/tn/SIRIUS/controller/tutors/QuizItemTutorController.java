package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
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
    public void setData(QuizQuestion quizQuestion){
        question.setText(quizQuestion.getQuestion());
        checkChoice1.setText("1)"+quizQuestion.getChoice_1());
        checkChoice2.setText("2)"+quizQuestion.getChoice_2());
        checkChoice3.setText("3)"+quizQuestion.getChoice_3());
        checkChoice4.setText("4)"+quizQuestion.getChoice_4());
        correctAnswer.setText(quizQuestion.getCorrect_choice());
        switch (quizQuestion.getCorrect_choice()) {
            case "Choice 1" -> checkChoice1.setSelected(true);
            case "Choice 2" -> checkChoice2.setSelected(true);
            case "Choice 3" -> checkChoice3.setSelected(true);
            default -> checkChoice4.setSelected(true);
        }
    }
}
