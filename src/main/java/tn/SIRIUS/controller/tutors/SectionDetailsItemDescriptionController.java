package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tn.SIRIUS.controller.tutors.CoursesMainPageController;
import tn.SIRIUS.entities.Quiz;
import tn.SIRIUS.entities.QuizQuestion;
import tn.SIRIUS.entities.Section;
import tn.SIRIUS.services.QuizQuestionService;
import tn.SIRIUS.services.QuizService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SectionDetailsItemDescriptionController implements Initializable {
    @FXML
    private Label sectionTitle;
    @FXML
    private Text sectionDescContent;
    @FXML
    private VBox questionListContainer;
    @FXML
    private Button addQuizFormGoBtn;
    @FXML
    private Button deleteQuizBtn;
    @FXML
    private Button editQuizBtn;
    private CoursesMainPageController mainPageController;
    private Section section;
    private Quiz quiz;
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
    public void setData(Section sec,boolean isUpdated,CoursesMainPageController con) throws IOException {
        this.mainPageController = con;
        this.section = sec;
        sectionTitle.setText(sec.getTitle());
        sectionDescContent.setText(sec.getDescription());
        QuizService quizService = new QuizService();
        quiz = quizService.getQuizBySection(sec.getIdSection());
        System.out.println("test");
        System.out.println(quiz.getIdQuiz());
        if(quiz.getIdQuiz() == 0){
            questionListContainer.setVisible(false);
            addQuizFormGoBtn.setVisible(true);
        } else {
            editQuizBtn.setVisible(true);
            deleteQuizBtn.setVisible(true);
            for (QuizQuestion qq : quiz.getQuestions()){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/QuizItemTutor.fxml"));
                Parent root = fxmlLoader.load();
                QuizItemTutorController cntrll = fxmlLoader.getController();
                cntrll.setData(qq,isUpdated,con);
                questionListContainer.getChildren().add(root);
            }
        }
    }
    public void setCoursesMainPageController(CoursesMainPageController controller){
        mainPageController = controller;
    }
    @FXML
    public void editSectionBtnClicked(MouseEvent event){
        mainPageController.setUpEditSectionPage(section);
    }
    @FXML
    public void deleteSectionBtnClicked(MouseEvent event){
        mainPageController.deleteSection(section);
    }
    @FXML
    public void addQuizBtnClicked(MouseEvent event){
        mainPageController.setAddQuizPage(section);
    }
    @FXML
    public void editQuizBtnClicked(MouseEvent event){
        mainPageController.setEditQuizPage(quiz);
    }
}
