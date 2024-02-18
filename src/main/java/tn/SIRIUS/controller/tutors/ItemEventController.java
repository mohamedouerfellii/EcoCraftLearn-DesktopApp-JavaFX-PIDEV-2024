package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.Event;


public class ItemEventController {
    @FXML
    private Label dateEvent;
    @FXML
    private Circle eventItemImgContainer;
    @FXML
    private Label eventItemTitle;
    @FXML
    private Label placeEventDispo;
    @FXML
    private Button showCourseDetailsBtn;
    private Event event;
    private HomePageEventController homePageEventController;

    public void setDataEvent(Event event,HomePageEventController controller) {
        eventItemTitle.setText(event.getTitle());
        placeEventDispo.setText(String.valueOf(event.getPlaceNbr())+" Places");
        dateEvent.setText("From "+event.getStartDate()+" To "+event.getEndDate());
        eventItemImgContainer.setFill(new ImagePattern(
                new Image(event.getAttachment())
        ));
        this.event = event;
        homePageEventController = controller;
    }
    @FXML
    public void showDetailsEventBtnClicked(MouseEvent e){
        homePageEventController.showEventDeatils(event);
    }
}
