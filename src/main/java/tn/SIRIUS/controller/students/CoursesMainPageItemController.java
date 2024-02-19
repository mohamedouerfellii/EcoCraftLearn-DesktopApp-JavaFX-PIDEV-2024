package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.UserService;

public class CoursesMainPageItemController {
    @FXML
    private Rectangle courseImgContainer;
    @FXML
    private Circle tutorImgContainer;
    @FXML
    private Label tutorNameLabel;
    @FXML
    private Text titleCourseText;
    @FXML
    private Label nbrRegistredLabel;
    @FXML
    private Rectangle starBar;
    private CoursesMainPageController coursesMainPageController;
    public void setData(Course course,CoursesMainPageController controller){
        UserService userService = new UserService();
        User tutor = userService.getCourseTutor(course.getTutor());
        courseImgContainer.setFill(new ImagePattern(new Image("file:/"+course.getImage().replace("\\","/"))));
        tutorNameLabel.setText(tutor.getLastName()+" "+tutor.getFirstName());
        tutorImgContainer.setFill(new ImagePattern(new Image("file:/"+tutor.getImage().replace("\\","/"))));
        titleCourseText.setText(course.getTitle());
        nbrRegistredLabel.setText(course.getNbrRegistred()+" Registred");
        coursesMainPageController = controller;
    }
}
