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
    private Button deleteSectionBtn;
    @FXML
    private Button editSectionBtn;
    @FXML
    private ImageView deleteSectionBtnImg;
    @FXML
    private ImageView editSectionBtnImg;
    @FXML
    private Label sectionTitle;
    @FXML
    private Text sectionDescContent;
    @FXML
    private VBox questionListContainer;
    @FXML
    private Button addQuizFormGoBtn;
    private CoursesMainPageController mainPageController;
    private Section section;
    private Quiz quiz;
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
    @FXML
    public void deleteSectionBtnHover(MouseEvent event){
        deleteSectionBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 12;" +
                "    -fx-background-color: red;" +
                "    -fx-text-fill: #fff;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;");
        deleteSectionBtnImg.setImage(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/close-circle-filll.png"))
        ));
    }
    @FXML
    public void deleteSectionBtnNormal(MouseEvent event){
        deleteSectionBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 12;" +
                "    -fx-background-color: #fff;" +
                "    -fx-text-fill: red;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-border-color: red;" +
                "    -fx-border-width: 2px;");
        deleteSectionBtnImg.setImage(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/close-circle-filll.png"))
        ));
    }
    @FXML
    public void editSectionBtnHover(MouseEvent event){
        editSectionBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 12;" +
                "    -fx-background-color: #BDA91A;" +
                "    -fx-text-fill: #fff;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;");
        editSectionBtnImg.setImage(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/edit-box-line.png"))
        ));
    }
    @FXML
    public void editSectionBtnNormal(MouseEvent event){
        editSectionBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 12;" +
                "    -fx-background-color: #fff;" +
                "    -fx-text-fill: #BDA91A;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-border-color: #BDA91A;" +
                "    -fx-border-width: 2px;");
        editSectionBtnImg.setImage(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/edit-box-line.png"))
        ));
    }
    public void setData(Section sec) throws IOException {
        sectionTitle.setText(sec.getTitle());
        sectionDescContent.setText(sec.getDescription());
        QuizService quizService = new QuizService();
        quiz = quizService.getQuizBySection(sec.getIdSection());
        if(quiz.getIdQuiz() == 0){
            questionListContainer.setVisible(false);
            addQuizFormGoBtn.setVisible(true);
        } else {
            QuizQuestionService quizQuestionService = new QuizQuestionService();
            List<QuizQuestion> quizQuestions = quizQuestionService.getAllBySection(sec.getIdSection());
            for (QuizQuestion qq : quizQuestions){
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent root = fxmlLoader.load(getClass().getResourceAsStream("/gui/tutors/QuizItemTutor.fxml"));
                QuizItemTutorController controller = fxmlLoader.getController();
                controller.setData(qq);
                questionListContainer.getChildren().add(root);
            }
        }
        this.section = sec;
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
        mainPageController.setAddQuizPage(section.getIdSection());
    }
}
