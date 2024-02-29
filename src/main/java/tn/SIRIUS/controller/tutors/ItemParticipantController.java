package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.Eventsparticipations;

public class ItemParticipantController {

    @FXML
    private Button DeleteeParticipant;

    @FXML
    private Label EmailParticipant;

    @FXML
    private HBox HboxContainerParticipant;

    @FXML
    private Circle ParticipantImageContainer;

    @FXML
    private Label ParticipantName;


    public void setHomePageEventController(HomePageEventController homePageEventController) {
        this.homePageEventController = homePageEventController;
    }

    private HomePageEventController homePageEventController = new HomePageEventController();



 public void setDataParticipant(Eventsparticipations participant) {
        ParticipantName.setText(participant.getParticipant().getFirstName()+" "+participant.getParticipant().getLastName());
        EmailParticipant.setText(participant.getParticipant().getEmail());

        ParticipantImageContainer.setFill(new ImagePattern(new Image(participant.getParticipant().getImage())));

     DeleteeParticipant.setOnAction(e->{
         homePageEventController.deleteparticipant(participant);
     });

    }

}
