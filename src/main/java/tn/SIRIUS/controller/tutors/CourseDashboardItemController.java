package tn.SIRIUS.controller.tutors;

import tn.SIRIUS.entities.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class CourseDashboardItemController {
    @FXML
    private Circle courseItemImgContainer;
    @FXML
    private Label courseItemTitle;
    @FXML
    private Label dateCourseItem;
    @FXML
    private Label registredCourseNbrLabel;
    @FXML
    private Button showCourseDetailsBtn;
    private CoursesMainPageController mainPageController;
    public void setCourseItemData(Course course){
        courseItemImgContainer.setFill(new ImagePattern(
                new Image("file:///" +course.getImage().replace("\\","/"))
        ));
        courseItemTitle.setText(course.getTitle());
        dateCourseItem.setText(course.getPostedDate());
        registredCourseNbrLabel.setText(course.getNbrRegistred() +" Registred");
        showCourseDetailsBtn.setOnMouseClicked(e ->  mainPageController.showCourseDetails(course));
    }
    public void setMainPageController(CoursesMainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
 
}
