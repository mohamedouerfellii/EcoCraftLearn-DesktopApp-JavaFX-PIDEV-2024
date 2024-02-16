/*package tn.SIRIUS.controller.students;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;

public class UserHomePageItemsController {
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
    @FXML
    private Button coursesHomePageBtn;
    public void setUserData(User user){
        Image courseImg = new Image(user.getImage());
        coursesHomePageItemImg.setFill(new ImagePattern(courseImg));
        coursesHomePageTitle.setText(user.getTitle());
        coursesHomePageTutor.setText(String.valueOf(user.getTutor()));
        coursesHomePageHours.setText(user.getDuration());
        coursesHomePageRate.setText("4.9");
    }
    @FXML
    public void onViewCourseBtnClicked(){
        System.out.println(coursesHomePageHours.getText());
    }
}*/