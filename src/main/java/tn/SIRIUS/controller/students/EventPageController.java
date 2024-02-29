package tn.SIRIUS.controller.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import tn.SIRIUS.entities.*;
import tn.SIRIUS.services.EventService;
import tn.SIRIUS.services.EventevaluationService;
import tn.SIRIUS.services.EventparticipationsService;
import tn.SIRIUS.services.SectionService;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventPageController implements Initializable {
    @FXML
    private AnchorPane AnchorPanedetail;

    @FXML
    private AnchorPane AnchorPaneParticipationStudent;

    @FXML
    private Rectangle AttachmentEventDetail;

    @FXML
    private Text DescEventDetail;

    @FXML
    private Text EndDateEventDetail;

    @FXML
    private AnchorPane MiniDescDetail;

    @FXML
    private AnchorPane MiniPaneDetail;

    @FXML
    private Text NBRPlaceEventDetail;

    @FXML
    private Text PlaceEventDetail;

    @FXML
    private Text PriceDetail;

    @FXML
    private Text StartDateEventDetail;

    @FXML
    private Text TitleEventdetail;

    @FXML
    private Text TypeEventDetail;


    @FXML
    private Button allCoursesBtn;

    @FXML
    private Button minidescriptionBtn;

    @FXML
    private Button miniBtnDetail;

    @FXML
    private Button miniReviewBtn;

    @FXML
    private Button newestCoursesBtn;

    @FXML
    private Button topRatedCoursesBtn;


    @FXML
    private AnchorPane rateContainer;

    @FXML
    private Button closerate;

    @FXML
    private Button AddRateBtn;

    @FXML
    private Button openrate;
    @FXML
    private Button goMyparticipant;

    @FXML
    private GridPane gridPane;

    @FXML
    private AnchorPane AnchorPaneMyparticipation;
    @FXML
    private Rating RateEvent;


    @FXML
    private Rating RatingDetail;

    @FXML
    private Text TotalRate;

       @FXML
       private Button TopRatedEvent;

    @FXML
    private Button AllEvents;
    @FXML
    private GridPane GridPaneParticipation;
    List<Event> events;
    List<Event> eventsRegistered;

    List<Event>  eventUser;
   User user = new User(1,"hicham","hicham","hicham",56318383,"hicham","hicham",1,1,"hicham");

    User user2 = new User(2,"hicham","hicham","hicham",56318383,"hicham","hicham",1,1,"hicham");

    private int userID;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getAllEvent();
        rateContainer.setVisible(false);

        goMyparticipant.setOnAction(event -> {
         getAllEventByUser();
          });
        openrate.setOnAction(event1 -> {
            rateContainer.setVisible(true);
        });
        closerate.setOnAction(event2 -> {
            rateContainer.setVisible(false);
        });

        AllEvents.setOnAction(event -> {
            getAllEvent();
        });

        gridPane.getChildren().clear();
        TopRatedEvent.setOnAction(event -> {

            getTopRated();
        });



    }
    public void loadEventItem(int column, int row, Event event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/students/eventItem.fxml"));
        Parent root = fxmlLoader.load();
        EventItemController controller = fxmlLoader.getController();
        controller.setData(event,this);
        gridPane.add(root,column,row);
    }

  public void loadEventItem2(int column, int row, Event event) throws IOException {

      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("/gui/students/MyParticipationItem.fxml"));
      Parent root = fxmlLoader.load();
      MyParticipationItemController controller = fxmlLoader.getController();
      controller.setData(event,this);
      GridPaneParticipation.add(root,column,row);

  }


  public void getAllEventByUser(){
      AnchorPaneMyparticipation.setVisible(true);
      EventService eventService = new EventService();
      eventUser = eventService.getEventUser(user.getIdUser());
     // events.remove(eventUser);
      System.out.println("teetetetet");
      int column = 0;
      int row = 1;
      GridPaneParticipation.getChildren().clear();
      for(Event event : eventUser){
          try{
              if(column == 3){
                  column = 0;
                  row++;
              }
              loadEventItem2(column++,row,event);
          }catch(IOException e){
              System.out.println(e.getMessage());
          }
      }
  }

    public void getAllEvent(){
        EventService eventService = new EventService();
        eventsRegistered = eventService.getEventRegistered(userID);
        events = eventService.getAll();
        events.remove(eventsRegistered);
        System.out.println("teetetetet");
        //System.out.println(events);
        int column = 0;
        int row = 1;
        gridPane.getChildren().clear();
        for(Event event : events){
            try{
                if(column == 3){
                    column = 0;
                    row++;
                }
                loadEventItem(column++,row,event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }


    public void getTopRated(){

        EventService eventService = new EventService();
        events = eventService.TrieEventsTopRated();
       //events.remove(eventsRegistered);
        System.out.println("teetetetet");
        System.out.println(events);
        int column = 0;
        int row = 1;
        gridPane.getChildren().clear();
        for(Event event : events){
            try{
                if(column == 3){
                    column = 0;
                    row++;
                }
                loadEventItem(column++,row,event);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }



    @FXML
    void handleminiDescriptionBtn(ActionEvent event) {
         MiniDescDetail.setVisible(true);
         MiniPaneDetail.setVisible(false);
    }

    @FXML
    void handleminiDetailsBtn(ActionEvent event) {
        MiniDescDetail.setVisible(false);
        MiniPaneDetail.setVisible(true);
    }

    public void ShowEventDetail(Event event){
        AnchorPanedetail.setVisible(true);
        TitleEventdetail.setText(event.getTitle());
        DescEventDetail.setText(event.getDescription());
        TypeEventDetail.setText(event.getEventType());
        StartDateEventDetail.setText(event.getStartDate());
        EndDateEventDetail.setText(event.getEndDate());
        PriceDetail.setText(Float.toString(event.getPrice()));
        PlaceEventDetail.setText(event.getPlace());
        NBRPlaceEventDetail.setText(Integer.toString(event.getPlaceNbr()));
        AttachmentEventDetail.setFill(new ImagePattern(new Image(event.getAttachment())));

        AddRateBtn.setOnAction(event1 -> addrate(event));

        EventService eventService = new EventService();
        int NbRRate = eventService.countNBrOfRate(event.getIdEvent());
        double Sommerate = eventService.SommeRateofEvent(event.getIdEvent());
        double rate2 = (Sommerate / NbRRate);
        double rate = rate2;
        RatingDetail.setRating(rate);

        TotalRate.setText(Integer.toString(NbRRate));

    }



    void addrate(Event ev) {
        int e = ev.getIdEvent();
        double rate = RateEvent.getRating();
       EventevaluationService eventevaluationService = new EventevaluationService();
        int id = eventevaluationService.getEvaluatorValue(ev.getIdEvent());
        if (id != user.getIdUser()) {
        if (rate != 0)
        {
            Eventsevaluation eventsevaluation = new Eventsevaluation(0,e,user.getIdUser(),rate);
            EventevaluationService eventsevaluationsService = new EventevaluationService();
            eventsevaluationsService.add(eventsevaluation);
        }
        }
        else System.out.println("you are already rated");


    }


    @FXML
    void handlegobackEventDetail(ActionEvent event) {
        AnchorPanedetail.setVisible(false);
    }
    @FXML
    void handlegobackparticipation(ActionEvent event) {
        AnchorPaneMyparticipation.setVisible(false);
    }

    public void AddParticipant(Event e){
        EventparticipationsService eventparticipationsService = new EventparticipationsService();
        int id = eventparticipationsService.getParticipantValue(e.getIdEvent());
        System.out.println(id);
        if (id != user.getIdUser()) {
            Eventsparticipations eventsparticipations = new Eventsparticipations(0, e, user);
            eventparticipationsService.add(eventsparticipations);

            EventService eventService = new EventService();
            eventService.decrementPlaceNbr(e.getIdEvent());
            getAllEvent();

        }
        else System.out.println("you are already registered");
    }


public void CancelParticipation(Event e) {
 EventService eventService = new EventService();
 eventService.deleteParticipation(e.getIdEvent(), user.getIdUser());
 eventService.incrementPlaceNbr(e.getIdEvent());
    getAllEventByUser();
    getAllEvent();
}





}
