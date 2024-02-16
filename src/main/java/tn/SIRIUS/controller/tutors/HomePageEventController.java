package tn.SIRIUS.controller.tutors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import tn.SIRIUS.entities.Event;
import tn.SIRIUS.services.EventService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.KeyStore;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageEventController implements Initializable {


    @FXML
    private Button AddEvent;

    @FXML
    private AnchorPane AnchorPaneContainerEvent;

    @FXML
    private AnchorPane AnchorPaneFormEvent;

    @FXML
    private Button ChooseAttachmentBtn;

    @FXML
    private Button GoBackEvent;

    @FXML
    private VBox VBoxContainerEvent;
    @FXML
    private Button HomeAddEvent;

    @FXML
    private Button HomeBtn;

    @FXML
    private Button HomeBtn1;

    @FXML
    private Button HomeBtn2;

    @FXML
    private Button HomeBtn3;

    @FXML
    private Button HomeBtn4;

    @FXML
    private Button HomeBtn5;

    @FXML
    private Button HomeBtn6;

    @FXML
    private ImageView InputAttachementEvent;

    @FXML
    private TextField InputDescrEvent;

    @FXML
    private DatePicker InputEndDateEvent;

    @FXML
    private TextField InputEventType;

    @FXML
    private TextField InputNbrPlaceEvents;

    @FXML
    private TextField InputPlaceEvent;

    @FXML
    private TextField InputPriceEvent;

    @FXML
    private DatePicker InputStartDateEvent;

    @FXML
    private TextField InputTitleEvent;

    @FXML
    private VBox MenuVbox;

    private List<Event> recentlyAdded;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AnchorPaneContainerEvent.setVisible(true);
        AnchorPaneFormEvent.setVisible(false);
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {


            for (int i = 0; i < recentlyAdded.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/ItemEvent.fxml"));
                HBox eventBox = fxmlLoader.load();
                ItemEvent itemEvent = fxmlLoader.getController();
                itemEvent.setDataEvent(recentlyAdded.get(i));
                VBoxContainerEvent.getChildren().add(eventBox);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
   public void refreshEvent()
   {
       recentlyAdded = new ArrayList<>(recentlyAdded());
       try {


           for (int i = 0; i < recentlyAdded.size(); i++) {
               FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/gui/tutors/ItemEvent.fxml"));
               HBox eventBox = fxmlLoader.load();
               ItemEvent itemEvent = fxmlLoader.getController();
               itemEvent.setDataEvent(recentlyAdded.get(i));
               VBoxContainerEvent.getChildren().add(eventBox);


           }
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
*/
    private List<Event> recentlyAdded() {


        List<Event> eventList = new ArrayList<>();
        EventService eventService = new EventService();
        eventList = eventService.getAll();
        return eventList;
    }
    @FXML
    void handleOnChooseAttachmentBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null)
        {
            Image image = new Image(selectedFile.toURI().toString());
            InputAttachementEvent.setImage(image);
        }
    }

    @FXML
    void HandleOnGoback(ActionEvent event)
    {
        AnchorPaneFormEvent.setVisible(false);


    }
    @FXML
    void handleOnHomeAddEvent(ActionEvent event) {
        AnchorPaneFormEvent.setVisible(true);

    }


    @FXML
    void HandleAddEvent(ActionEvent event) {

        try {
            String title = InputTitleEvent.getText();
            String description = InputDescrEvent.getText();
            String startDate= InputStartDateEvent.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String endDate= InputEndDateEvent.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String attachment = InputAttachementEvent.getImage().getUrl().toString();
            String eventType = InputEventType.getText();
            String place = InputPlaceEvent.getText();

            int placeNbr = Integer.parseInt(InputNbrPlaceEvents.getText());
            int price = Integer.parseInt(InputPriceEvent.getText());

            EventService eventService = new EventService();
            Event event1 = new Event(0,title, description, startDate, endDate,attachment,0,eventType,place,placeNbr,price);


            if (eventService.add(event1)) {
                System.out.println("event added successfully.");
              // refreshEvent();
            } else {
                System.out.println("Failed to add the event.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid price format. Please enter a valid number.");
        } catch (Exception ex) {
            System.out.println("An error occurred while adding the product: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    }





