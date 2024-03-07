package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;

public class PopularPeopleItemController {
    @FXML
    private Label popularNbLikes;

    @FXML
    private Circle popularUserImage;

    @FXML
    private Label popularUsername;


public void SetPopularData(int nblikes , User user){

    Image image = new Image(user.getImage());
    popularUserImage.setFill(new ImagePattern(image));
    popularUsername.setText(user.getFirstName()+" "+user.getLastName());
    popularNbLikes.setText(nblikes + " Likes");
}




}
