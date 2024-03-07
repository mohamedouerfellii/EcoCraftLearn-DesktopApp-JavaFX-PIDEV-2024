package tn.SIRIUS.controller.admins;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardTutorHomePageContentController implements Initializable {
    @FXML
    private VBox feedbackContentContainer;
    @FXML
    private PieChart genderRatio;
    @FXML
    private Label genderRatioFDesc;
    @FXML
    private Label genderRatioMDesc;
    @FXML
    private Circle top1CourseImgContainer;
    @FXML
    private Label top1CourseTitle;
    @FXML
    private Label top1NbrRegistred;
    @FXML
    private Circle top2CourseImgContainer;
    @FXML
    private Label top2CourseTitle;
    @FXML
    private Label top2NbrRegistred;
    @FXML
    private Circle top3CourseImgContainer;
    @FXML
    private Label top3CourseTitle;
    @FXML
    private Label top3NbrRegistred;
    @FXML private Label nbrTotStudents;
    @FXML private Label nbrNewStudent;
    @FXML private Label nbrCoursesTutor;
    @FXML private AnchorPane courseT1;
    @FXML private AnchorPane courseT2;
    @FXML private AnchorPane courseT3;
    private int userTest;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
