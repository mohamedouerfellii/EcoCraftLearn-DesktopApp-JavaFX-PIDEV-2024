package tn.SIRIUS.controller.students;

import tn.SIRIUS.entities.Course;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class CoursesHomePageItemController {
    @FXML
    private Circle coursesHomePageItemImg;
    @FXML
    private Label coursesHomePageTitle;
    @FXML
    private Label coursesHomePageTutor;
    @FXML
    private Label coursesHomePageHours;
    @FXML
    private Label coursesHomePageRate;
    public void setCourseData(Course course){
        Image courseImg = new Image("file:/"+course.getImage().replace("\\","/"));
        coursesHomePageItemImg.setFill(new ImagePattern(courseImg));
        coursesHomePageTitle.setText(course.getTitle());
        coursesHomePageTutor.setText(String.valueOf(course.getTutor()));
        coursesHomePageHours.setText(course.getDuration());
        coursesHomePageRate.setText("4.9");
    }
    @FXML
    public void onViewCourseBtnClicked(){
        System.out.println(coursesHomePageHours.getText());
    }
}
