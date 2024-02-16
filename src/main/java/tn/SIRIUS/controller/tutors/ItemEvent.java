package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Event;

import java.io.InputStream;
import java.net.URL;


public class ItemEvent {

    @FXML
    private HBox HboxItemEvent;

    @FXML
    private ImageView attachmentEvent;

    @FXML
    private Text endDateEvent;

    @FXML
    private Text placeEvent;

    @FXML
    private Text priceEvent;

    @FXML
    private Text startDateEvent;

    @FXML
    private Text titleEvent;



    public void setDataEvent(Event event) {

        String imageUrl = event.getAttachment();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));
        attachmentEvent.setImage(img1);
        titleEvent.setText(event.getTitle());
        startDateEvent.setText(event.getStartDate());
        endDateEvent.setText(event.getEndDate());

    }


}
