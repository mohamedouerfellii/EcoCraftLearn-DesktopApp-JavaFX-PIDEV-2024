package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.UserService;

public class CoursesMainPageItemController {
    @FXML
    private Text titleCourseText;
    @FXML
    private Text tutorNameText;
    @FXML
    private Circle tutorImgContainer;
    @FXML
    private Rectangle starBarRate;
    @FXML
    private Text nbrRegistredText;
    private CoursesMainPageController coursesMainPageController;
    public void setData(Course course,CoursesMainPageController controller){
        tutorNameText.setText(course.getTutor().getLastName()+" "+course.getTutor().getFirstName());
        tutorImgContainer.setFill(new ImagePattern(new Image("file:/"+course.getTutor().getImage().replace("\\","/"))));
        titleCourseText.setText(course.getTitle());
        starBarRate.setWidth((course.getRate() / 5)*100);
        nbrRegistredText.setText(course.getNbrRegistred()+" Registered");
        coursesMainPageController = controller;
    }
}
