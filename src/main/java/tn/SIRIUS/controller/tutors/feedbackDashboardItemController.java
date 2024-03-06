package tn.SIRIUS.controller.tutors;

import tn.SIRIUS.entities.Feedback;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class feedbackDashboardItemController {
    @FXML
    private Circle feedbackAuthorImgContainer;
    @FXML
    private Label feedbackAuthorName;
    @FXML
    private Label feedbackDate;
    @FXML
    private Text feedbackContentText;
    public void setFeedBackData(Feedback feedback){
        Image profileImg = new Image(getClass().getResourceAsStream("/images/profilePictures/12.jpg"));
        feedbackAuthorImgContainer.setFill(new ImagePattern(profileImg));
        feedbackAuthorName.setText(feedback.getAuthorName());
        feedbackDate.setText(feedback.getDate());
        feedbackContentText.setText(feedback.getContent());
    }
}
