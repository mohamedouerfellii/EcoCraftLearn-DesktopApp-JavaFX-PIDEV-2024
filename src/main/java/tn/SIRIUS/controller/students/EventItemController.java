package tn.SIRIUS.controller.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.w3c.dom.css.Rect;
import tn.SIRIUS.entities.Event;

public class EventItemController {
    @FXML
    private Button participateBtn;
    @FXML
    private Text nbrPersonRatedText;
    @FXML
    private Rectangle imgContainer;
    @FXML
    private Text nbrPlace;
    @FXML
    private Rectangle starBarRate;
    @FXML
    private Text titleEvent;

    @FXML
    private AnchorPane BtnViewdetailEvent;

    private Event event;
    private EventPageController eventPageController;
    public void setData(Event event,EventPageController controller){
        this.event = event;
        this.eventPageController = controller;
        titleEvent.setText(event.getTitle());
        nbrPlace.setText(event.getPlaceNbr()+" Places");
        imgContainer.setFill(new ImagePattern(new Image(event.getAttachment())));

        BtnViewdetailEvent.setOnMouseClicked(event1 -> {
            eventPageController.ShowEventDetail(event);

        });

        participateBtn.setOnMouseClicked(event1 -> {
            eventPageController.AddParticipant(event);
        });

    }


}
