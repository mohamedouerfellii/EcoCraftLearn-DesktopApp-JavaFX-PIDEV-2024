package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Course;

public class CoursesRegisteredItemController{
    @FXML
    private AnchorPane optionsContainer;
    @FXML
    private Label tutorName;
    @FXML
    private Text titleCourseText;
    @FXML
    private Text nbrRegistredText;
    @FXML
    private Text progressPrcText;
    @FXML
    private Rectangle prcProgressBar;
    @FXML
    private Rectangle courseImgContainer;
    private CoursesMainPageController coursesMainPageController;
    private Course course;
    @FXML
    public void moreOptionsBtnClicked(MouseEvent event){
        optionsContainer.setVisible(!optionsContainer.isVisible());
    }
    @FXML
    public void unrollOptionClicked(MouseEvent event){
        coursesMainPageController.showConfirmationUnroll(course);
    }
    @FXML
    public void rateOptionClicked(MouseEvent event){
        coursesMainPageController.showRatingForm(course);
    }
    public void setData(Course course, CoursesMainPageController controller){
        courseImgContainer.setFill(new ImagePattern(new Image("file:/"+course.getImage().replace("\\","/"))));
        tutorName.setText("Course | "+course.getTutor().getFirstName()+" "+course.getTutor().getLastName());
        titleCourseText.setText(course.getTitle());
        nbrRegistredText.setText(course.getNbrRegistred()+" Registered");
        progressPrcText.setText("0%");
        prcProgressBar.setWidth(1);
        coursesMainPageController = controller;
        this.course = course;
    }
    @FXML
    public void continueLearnBtnClicked(MouseEvent event){
        coursesMainPageController.setUpLearningPage(course);
    }

}
