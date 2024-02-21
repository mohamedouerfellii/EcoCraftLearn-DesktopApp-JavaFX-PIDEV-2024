package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;

public class profileItemController {
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

    public void setUserItemData(User user){
        courseItemImgContainer.setFill(new ImagePattern(
                new Image("file:///" +user.getImage().replace("\\","/"))
        ));
    }

}

