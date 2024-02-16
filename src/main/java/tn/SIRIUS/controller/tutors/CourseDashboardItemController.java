package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;

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
private OperationAdminController o;

    public void setUserItemData(User user){
        courseItemImgContainer.setFill(new ImagePattern(
                new Image("file:///" +user.getImage().replace("\\","/"))
        ));
        courseItemTitle.setText(user.getFirstName()+" "+user.getLastName());
        dateCourseItem.setText(user.getRoles());
        registredCourseNbrLabel.setText(String.valueOf(user.getNbrPtsCollects()));
       // showCourseDetailsBtn.setOnMouseClicked(e ->  OperationAdminController.showUserDetails(user));
    }

}

