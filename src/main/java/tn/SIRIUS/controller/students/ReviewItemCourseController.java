package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Feedback;

public class ReviewItemCourseController {
    @FXML
    private Label feedbackAuthorName;
    @FXML
    private Circle feedbackAuthorImgContainer;
    @FXML
    private Rectangle starBarRate;
    @FXML
    private Text feedbackContentText;
    @FXML
    private Label feedbackDate;
    public void setData(Feedback feedback){
        feedbackAuthorName.setText(feedback.getOwner().getLastName()+" "+feedback.getOwner().getFirstName());
        feedbackAuthorImgContainer.setFill(new ImagePattern(
                new Image("file:/"+feedback.getOwner().getImage())
        ));
        feedbackContentText.setText(feedback.getContent());
        starBarRate.setWidth(((double) feedback.getRate() /5 )*100);
        feedbackDate.setText(feedback.getDate());
    }
}
