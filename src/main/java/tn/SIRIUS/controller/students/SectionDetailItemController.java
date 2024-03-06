package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Quiz;
import tn.SIRIUS.entities.QuizAnswer;
import tn.SIRIUS.entities.Section;
import tn.SIRIUS.services.QuizAnswerService;
import tn.SIRIUS.services.QuizService;

public class SectionDetailItemController {
    @FXML
    private Label sectionTitle;
    @FXML
    private Text sectionDescription;
    @FXML private HBox quizGradeContainer;
    @FXML private Button passQuizBtn;
    @FXML private Text quizResult;
    private Section section;
    private CoursesMainPageController coursesMainPageController;
    private final int USERTEST = 4;
    private Quiz quiz;
    public void setData(Section section,CoursesMainPageController controller){
        QuizService quizService = new QuizService();
        quiz = quizService.getQuizBySection(section.getIdSection());
        if(quiz.getIdQuiz() != 0){
            QuizAnswerService quizAnswerService = new QuizAnswerService();
            QuizAnswer quizAnswer = quizAnswerService.getAnswer(USERTEST,quiz.getIdQuiz());
            if(quizAnswer.getIdAnswer() != 0){
                quizGradeContainer.setVisible(true);
                quizResult.setText(quizAnswer.getResult()+"%");
            } else{
                passQuizBtn.setVisible(true);
            }
        }
        sectionTitle.setText(section.getTitle());
        sectionDescription.setText("\" "+section.getDescription()+" \"");
        this.section = section;
        coursesMainPageController = controller;
    }
    @FXML
    public void passQuizClicked(MouseEvent event){
        coursesMainPageController.setUpQuizPlay(quiz);
    }
}
