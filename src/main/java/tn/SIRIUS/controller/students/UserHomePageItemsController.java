package tn.SIRIUS.controller.students;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;

public class UserHomePageItemsController {
    @FXML
    private Circle userImgCercle;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Button replyButton;
    private User user;
    public void setUserData(User user){
        userImgCercle.setFill(new ImagePattern(
                new Image(user.getImage().replace("\\","/"))
        ));
        userNameLabel.setText(user.getFirstName());
        this.user = user;

    }

}