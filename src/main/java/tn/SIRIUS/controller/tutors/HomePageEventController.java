package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import tn.SIRIUS.entities.Event;
import tn.SIRIUS.entities.Eventsparticipations;
import tn.SIRIUS.services.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.input.MouseEvent;
import tn.SIRIUS.services.EventparticipationsService;
import tn.SIRIUS.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageEventController implements Initializable {
    @FXML
    private AnchorPane AnchorPaneParticipant;

    @FXML
    private VBox VboxParticipant;

    @FXML
    private TextArea addDescEvent;

    @FXML
    private DatePicker addEventEndDate;

    @FXML
    private Button addEventFormBtn;

    @FXML
    private TextField addEventNbrPlace;

    @FXML
    private AnchorPane addEventPageContainer;

    @FXML
    private TextField addEventPlace;

    @FXML
    private TextField addEventPrice;

    @FXML
    private DatePicker addEventStartDate;

    @FXML
    private TextField addEventType;

    @FXML
    private Button addNewEventBtn;

    @FXML
    private Button chooseImgAddEvent;

    @FXML
    private ImageView closeWarningPassword;

    @FXML
    private Button confirmDeleteCourseBtn;

    @FXML
    private Text dateLabelDetail;

    @FXML
    private ImageView deleteCourseBtnImg;

    @FXML
    private Button deleteEventBtn;

    @FXML
    private Text descriptionLabelDetail;

    @FXML
    private ImageView editCourseBtnImg;

    @FXML
    private Button editEventBtn;

    @FXML
    private AnchorPane eventDetailsContainer;

    @FXML
    private Rectangle eventDetailsImgContainer;

    @FXML
    private VBox eventsListContainer;

    @FXML
    private AnchorPane failedOperationContainer;

    @FXML
    private AnchorPane failedOperationEditContainer;

    @FXML
    private Button goBackEventDetBtn;

    @FXML
    private Button goBackEventDetailsBtn;

    @FXML
    private AnchorPane homePageEventController;

    @FXML
    private Label idLabelDetail;

    @FXML
    private ImageView imgAddEventPreview;

    @FXML
    private Text nbrPlaceLabelDetail;

    @FXML
    private PasswordField passwordConfirmDelete;

    @FXML
    private Label passwordIncorrectContainer;

    @FXML
    private Label passwordIncorrectContainer1;

    @FXML
    private Text placeLabelDetail;

    @FXML
    private Text priceLabelDetail;

    @FXML
    private TextField searchInput;

  @FXML
  private Button CloseParticipant;

    @FXML
    private Button showEventParticipantsBtn;

    @FXML
    private AnchorPane successOperationContainer;

    @FXML
    private TextField titleInputAddEvent;

    @FXML
    private Text titleLabelDetail;

    @FXML
    private Text typeLabelDetail;

    @FXML
    private AnchorPane warningDeleteEventContainer;
    private String addEventImgPath;
    private List<Event> events;
    private Event currentEvent;
    public Button getShowEventParticipantsBtn() {
        return showEventParticipantsBtn;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningDeleteEventContainer.setVisible(false);
        addEventPageContainer.setVisible(false);
        eventDetailsContainer.setVisible(false);
        AnchorPaneParticipant.setVisible(false);
        showAllEvents();


        CloseParticipant.setOnMouseClicked(event -> {
            AnchorPaneParticipant.setVisible(false);
        });


    }
    // Event Start
    public void showAllEvents(){
        eventsListContainer.getChildren().clear();
        EventService eventService = new EventService();
        events = eventService.getAll();
        for(Event event : events){
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(Objects.requireNonNull(getClass().getResource("/gui/tutors/ItemEvent.fxml")));
                Parent root = fxmlLoader.load();
                ItemEventController controller = fxmlLoader.getController();
                controller.setHomePageEventController(this);
                controller.setDataEvent(event);
                eventsListContainer.getChildren().add(root);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void showEventDeatils(Event event){
        idLabelDetail.setText("#"+event.getIdEvent());
        titleLabelDetail.setText(event.getTitle());
        descriptionLabelDetail.setText(event.getDescription());
        dateLabelDetail.setText("From "+event.getStartDate()+" To "+event.getEndDate());
        priceLabelDetail.setText(event.getPrice()+" TND");
        placeLabelDetail.setText(event.getPlace());
        typeLabelDetail.setText(event.getEventType());
        nbrPlaceLabelDetail.setText(event.getPlaceNbr()+" Place(s)");
        eventDetailsImgContainer.setFill(new ImagePattern(
                new Image(event.getAttachment())
        ));
        eventDetailsContainer.setVisible(true);
        currentEvent = event;
        showEventParticipantsBtn.setOnAction(e->showEventParticipants(event));
    }
    public void notifySuccess(){
        successOperationContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> successOperationContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void notifyFailed(){
        failedOperationContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> successOperationContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    @FXML
    public void addNewEventBtnClicked(MouseEvent ev){
        addEventPageContainer.setVisible(true);
    }
    @FXML
    public void chooseImgAddEventClicked(MouseEvent ev){
        FileChooser imageCourseChooser = new FileChooser();
        imageCourseChooser.setTitle("Choose your event image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files", "*.png", "*.jpg", "*.gif");
        imageCourseChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = imageCourseChooser.showOpenDialog(null);
        if (selectedFile != null) {
            addEventImgPath = "file:/"+selectedFile.getAbsolutePath().replace("\\","/");
            imgAddEventPreview.setImage(new Image(addEventImgPath));
        }
    }
    @FXML
    public void addEventFormBtnClicked(MouseEvent ev){
        EventService eventService = new EventService();
        String title = titleInputAddEvent.getText();
        String description = addDescEvent.getText();
        float price = Float.parseFloat(addEventPrice.getText());
        String place = addEventPlace.getText();
        String startDate = addEventStartDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = addEventEndDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int nbrPlace = Integer.parseInt(addEventNbrPlace.getText());
        String type = addEventType.getText();
        Event event = new Event(
                currentEvent.getIdEvent(),title,description,startDate,endDate,
                addEventImgPath,1,type,place,nbrPlace,price);
        if (addEventFormBtn.getText().equals("Add Event")){
            if(eventService.add(event)){
                showAllEvents();
                clearInputsEvent();
                notifySuccess();
            } else
                notifyFailed();
        } else {
            if(eventService.update(event)){
                addEventPageContainer.setVisible(false);
                addEventFormBtn.setText("Add Event");
                showEventDeatils(event);
                showAllEvents();
                clearInputsEvent();
                notifySuccess();
            }
        }
    }
    public void clearInputsEvent(){
        titleInputAddEvent.clear();
        addDescEvent.clear();
        addEventPrice.clear();
        addEventPlace.clear();
        addEventNbrPlace.clear();
        addEventType.clear();
    }
    @FXML
    public void goAllEventPage(MouseEvent ev){
        imgAddEventPreview.setImage(new Image("/images/image preview.png"));
        addEventImgPath = "";
        addEventPageContainer.setVisible(false);
        eventDetailsContainer.setVisible(false);
    }
    @FXML
    public void editEventBtnClicked(MouseEvent ev){
        addEventFormBtn.setText("Edit Event");
        titleInputAddEvent.setText(currentEvent.getTitle());
        addDescEvent.setText(currentEvent.getDescription());
        addEventPrice.setText(String.valueOf(currentEvent.getPrice()));
        addEventPlace.setText(currentEvent.getPlace());
        addEventStartDate.setValue(LocalDate.parse(currentEvent.getStartDate(), DateTimeFormatter.ISO_DATE));
        addEventEndDate.setValue(LocalDate.parse(currentEvent.getEndDate(), DateTimeFormatter.ISO_DATE));
        addEventNbrPlace.setText(String.valueOf(currentEvent.getPlaceNbr()));
        addEventType.setText(currentEvent.getEventType());
        addEventImgPath = currentEvent.getAttachment();
        imgAddEventPreview.setImage(new Image(addEventImgPath));
        addEventPageContainer.setVisible(true);
    }
    @FXML
    public void deleteEventBtnClicked(MouseEvent ev){
        warningDeleteEventContainer.setVisible(true);
    }
    @FXML
    public void closeWarningDeleteEvent(MouseEvent ev){
        warningDeleteEventContainer.setVisible(false);
        passwordConfirmDelete.clear();
    }
    @FXML
    public void confirmDeleteEvent(MouseEvent ev){
        String password = passwordConfirmDelete.getText();
        UserService userService = new UserService();
        if(userService.isPasswordMatch(1,password)){
            EventService eventService = new EventService();
            if(eventService.delete(currentEvent.getIdEvent())){
                showAllEvents();
                goAllEventPage(null);
                closeWarningDeleteEvent(null);
                notifySuccess();
            } else {
                closeWarningDeleteEvent(null);
                notifyFailed();
            }
        } else {
            closeWarningDeleteEvent(null);
            failedOperationEditContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationEditContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
  public  void showEventParticipants(Event event){
      VboxParticipant.getChildren().clear();
      AnchorPaneParticipant.setVisible(true);
      EventparticipationsService eventparticipationsService = new EventparticipationsService();
      List<Eventsparticipations> list = eventparticipationsService.getAll();
      try { for(Eventsparticipations e : list) {
          System.out.println(e.getEvent().getIdEvent());
          System.out.println(event.getIdEvent());
          if (e.getEvent().getIdEvent() == event.getIdEvent()) {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/tutors/ItemParticipant.fxml"));
              Parent root = null;

              root = loader.load();

              ItemParticipantController itemParticipantController = loader.getController();
              itemParticipantController.setDataParticipant(e);
              itemParticipantController.setHomePageEventController(this);
              VboxParticipant.getChildren().add(root);
              System.out.println(e);

          }


      }} catch (IOException e) {
          throw new RuntimeException(e);
      }}

    public void deleteparticipant(Eventsparticipations participant){
        EventparticipationsService eventparticipationsService = new EventparticipationsService();
         int idparticipant = participant.getIdParticipation();
        eventparticipationsService.delete(idparticipant);
        showEventParticipants(currentEvent);
    }


  }