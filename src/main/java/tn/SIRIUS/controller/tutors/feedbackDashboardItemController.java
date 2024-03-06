package tn.SIRIUS.controller.tutors;

import javafx.scene.shape.Rectangle;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.Feedback;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import tn.SIRIUS.services.CourseService;

public class feedbackDashboardItemController {
    @FXML
    private Circle feedbackAuthorImgContainer;
    @FXML
    private Label feedbackAuthorName;
    @FXML
    private Label feedbackDate;
    @FXML
    private Text feedbackContentText;
    @FXML private Rectangle starBarRate;
    @FXML private Label courseInfo;
    public void setFeedBackData(Feedback feedback){
        Image profileImg = new Image("file:/"+feedback.getOwner().getImage());
        feedbackAuthorImgContainer.setFill(new ImagePattern(profileImg));
        feedbackAuthorName.setText(feedback.getOwner().getLastName()+" "+feedback.getOwner().getFirstName());
        feedbackDate.setText(feedback.getDate());
        feedbackContentText.setText(feedback.getContent());
        starBarRate.setWidth(((double) feedback.getRate() /5)*100);
        CourseService courseService = new CourseService();
        Course course = courseService.getOne(feedback.getCourse());
        courseInfo.setText("#"+course.getId()+" "+course.getTitle());
    }
}
