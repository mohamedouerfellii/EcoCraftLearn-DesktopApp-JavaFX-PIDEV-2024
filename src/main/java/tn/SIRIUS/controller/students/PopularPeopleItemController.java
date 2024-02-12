package tn.SIRIUS.controller.students;

import tn.SIRIUS.entities.Post;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Map;

public class PopularPeopleItemController {
    @FXML
    private Label popularNbLikes;

    @FXML
    private Circle popularUserImage;

    @FXML
    private Label popularUsername;

/*
public void SetPopularData(int nblikes , User user){

    Image image = new Image(user.getUserImage());
    popularUserImage.setFill(new ImagePattern(image));
    popularUsername.setText(user.getUsername() );
    popularNbLikes.setText(nblikes + " Likes");
}


*/

}
