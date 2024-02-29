package tn.SIRIUS.controller.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Event;

public class MyParticipationItemController {

    @FXML
    private Rectangle imgContainer;


    @FXML
    private Button CancelBtn;
    @FXML
    private Button participateBtn;

    @FXML
    private Text titleEvent;

    @FXML
    private Text DateEvent;
    private Event event;
    private EventPageController eventPageController;

    public void setData(Event event,EventPageController controller){
        this.event = event;
        this.eventPageController = controller;
        DateEvent.setText(event.getStartDate()+" To "+event.getEndDate());
        titleEvent.setText(event.getTitle());
        imgContainer.setFill(new ImagePattern(new Image(event.getAttachment())));


      CancelBtn.setOnAction((ActionEvent e) -> {
            eventPageController.CancelParticipation(event);
        });

    }

}
