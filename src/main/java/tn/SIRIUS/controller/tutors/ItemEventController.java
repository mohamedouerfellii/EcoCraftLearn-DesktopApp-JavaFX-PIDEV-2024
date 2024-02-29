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



    private HomePageEventController homePageEventController = new HomePageEventController();

    public void setDataEvent(Event ev) {
        eventItemTitle.setText(ev.getTitle());
        placeEventDispo.setText(String.valueOf(ev.getPlaceNbr())+" Places");
        dateEvent.setText("From "+ev.getStartDate()+" To "+ev.getEndDate());
        eventItemImgContainer.setFill(new ImagePattern(new Image(ev.getAttachment())));

        this.event = ev;
    }
    @FXML
    public void showDetailsEventBtnClicked(MouseEvent e){
        homePageEventController.showEventDeatils(event);
    }

    public void setHomePageEventController(HomePageEventController homePageEventController) {
        this.homePageEventController = homePageEventController;
    }



}
