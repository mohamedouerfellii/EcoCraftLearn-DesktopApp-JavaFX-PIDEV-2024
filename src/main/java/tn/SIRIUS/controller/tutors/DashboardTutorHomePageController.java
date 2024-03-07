package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.SIRIUS.controller.students.HomePageController;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CourseService;
import tn.SIRIUS.services.UserService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardTutorHomePageController implements Initializable {
    @FXML
    private Circle profileImgContainer;
    @FXML
    private HBox notifCount;
    @FXML
    private HBox msgCount;
    @FXML
    private Button dashboardBtn;
    @FXML
    private ImageView dashboardBtnImg;
    @FXML
    private Button coursesBtn;
    @FXML
    private ImageView coursesBtnImg;
    @FXML
    private Button eventsBtn;
    @FXML
    private ImageView eventsBtnImg;
    @FXML
    private Button forumBtn;
    @FXML
    private ImageView forumBtnImg;
    @FXML
    private Button productsBtn;
    @FXML
    private ImageView productsBtnImg;
    @FXML
    private Button collectsBtn;
    @FXML
    private ImageView collectsBtnImg;
    @FXML
    private Button logoutBtn;
    @FXML
    private ImageView logoutBtnImg;
    @FXML
    private Pane mainContentContainer;
    @FXML private AnchorPane demandeJoinWBContainer;
    @FXML private Circle tutorImgWB;
    @FXML private Label tutorInvWBName;
    private CoursesMainPageController coursesMainPageController;
    // for whiteboard server
    private static DatagramSocket socket;
    private static InetAddress address;
    private static final int SERVER_PORT = 12345;
    private Thread clientThread;
    private boolean isThreadRunning;
    private final User USER_TEST = new User(6,"Moetaz Hechmi","Groun","mohamedouerfelli2@gmail.com","");
    UserService userService = new UserService();
    private MediaPlayer whiteboardPlayer;
    private int courseWBInvite;
    private int tutorIdWBInvite;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        isThreadRunning = true;
        //Profile Image
        Image profileImg = new Image(getClass().getResourceAsStream("/images/profilePictures/12.jpg"));
        profileImgContainer.setFill(new ImagePattern(profileImg));
        //Msg and Notifications
        Label nbrNotif = new Label();
        Label nbrMsg = new Label();
        nbrNotif.setText("5");
        nbrMsg.setText("2");
        nbrNotif.setStyle("-fx-font-family: \"Jost\";-fx-font-weight: bold;-fx-font-size: 16;-fx-text-fill: white;");
        nbrMsg.setStyle("-fx-font-family: \"Jost\";-fx-font-weight: bold;-fx-font-size: 16;-fx-text-fill: white;");
        notifCount.getChildren().add(nbrNotif);
        msgCount.getChildren().add(nbrMsg);
        //Main Page
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/dashboardTutorHomePageContent.fxml"));
            mainContentContainer.getChildren().clear();
            Parent page = fxmlLoader.load();
            mainContentContainer.getChildren().setAll(page);
        }catch (IOException ex){
            throw new RuntimeException();
        }
        //Menu
        String styleMenuBtnClicked =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: #fff;
                -fx-background-radius: 24 0 0 24;
                -fx-text-fill: #1D6611;""";
        String styleMenuBtnNormal =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: transparent;
                -fx-text-fill: #D5FFDC;""";
        dashboardBtn.setStyle(styleMenuBtnClicked);
        dashboardBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/dashboard-fill.png")));
        //Menu Btn Clicked
        coursesBtn.setOnMouseClicked(e -> {
            coursesBtn.setStyle(styleMenuBtnClicked);
            coursesBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/graduation-cap-fill.png")));
            if(dashboardBtn.getStyle().equals(styleMenuBtnClicked)){
                dashboardBtn.setStyle(styleMenuBtnNormal);
                dashboardBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/dashboard-fill.png")));
            }
            if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
                eventsBtn.setStyle(styleMenuBtnNormal);
                eventsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/calendar-event-line.png")));
            }
            if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
                forumBtn.setStyle(styleMenuBtnNormal);
                forumBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/group-fill.png")));
            }
            if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
                productsBtn.setStyle(styleMenuBtnNormal);
                productsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/shopping-bag-3-fill.png")));
            }
            if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
                collectsBtn.setStyle(styleMenuBtnNormal);
                collectsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/recycle-fill.png")));
            }
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/gui/tutors/CoursesMainPage.fxml")));
                Parent page = fxmlLoader.load();
                coursesMainPageController = fxmlLoader.getController();
                coursesMainPageController.setCoursesMainPageController(this);
                mainContentContainer.getChildren().clear();
                mainContentContainer.getChildren().setAll(page);
            }catch (IOException ex){
                throw new RuntimeException();
            }
        });
        dashboardBtn.setOnMouseClicked(e -> {
            dashboardBtn.setStyle(styleMenuBtnClicked);
            dashboardBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/dashboard-fill.png")));
            if(coursesBtn.getStyle().equals(styleMenuBtnClicked)){
                coursesBtn.setStyle(styleMenuBtnNormal);
                coursesBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/graduation-cap-fill.png")));
            }
            if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
                eventsBtn.setStyle(styleMenuBtnNormal);
                eventsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/calendar-event-line.png")));
            }
            if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
                forumBtn.setStyle(styleMenuBtnNormal);
                forumBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/group-fill.png")));
            }
            if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
                productsBtn.setStyle(styleMenuBtnNormal);
                productsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/shopping-bag-3-fill.png")));
            }
            if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
                collectsBtn.setStyle(styleMenuBtnNormal);
                collectsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/recycle-fill.png")));
            }
            try{
                Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/tutors/dashboardTutorHomePageContent.fxml")));
                coursesMainPageController.cleanup();
                coursesMainPageController = null;
                mainContentContainer.getChildren().clear();
                mainContentContainer.getChildren().setAll(page);
            }catch (IOException ex){
                throw new RuntimeException();
            }
        });
        // Sending msg connect to ther server
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            byte[] uuid = ("tutorJoinServer;" + USER_TEST.getId()+ ";"+USER_TEST.getEmail()+";").getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clientThread = new Thread(new DashboardTutorHomePageController.ClientThread());
        clientThread.start();
    }
    public void backToCoursesPage(){
        String styleMenuBtnClicked =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: #fff;
                -fx-background-radius: 24 0 0 24;
                -fx-text-fill: #1D6611;""";
        String styleMenuBtnNormal =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: transparent;
                -fx-text-fill: #D5FFDC;""";
        coursesBtn.setStyle(styleMenuBtnClicked);
        coursesBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/graduation-cap-fill.png")));
        if(dashboardBtn.getStyle().equals(styleMenuBtnClicked)){
            dashboardBtn.setStyle(styleMenuBtnNormal);
            dashboardBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/dashboard-fill.png")));
        }
        if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
            eventsBtn.setStyle(styleMenuBtnNormal);
            eventsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/calendar-event-line.png")));
        }
        if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
            forumBtn.setStyle(styleMenuBtnNormal);
            forumBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/group-fill.png")));
        }
        if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
            productsBtn.setStyle(styleMenuBtnNormal);
            productsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/shopping-bag-3-fill.png")));
        }
        if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
            collectsBtn.setStyle(styleMenuBtnNormal);
            collectsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/recycle-fill.png")));
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/gui/tutors/CoursesMainPage.fxml")));
            Parent page = fxmlLoader.load();
            coursesMainPageController = fxmlLoader.getController();
            coursesMainPageController.setCoursesMainPageController(this);
            mainContentContainer.getChildren().clear();
            mainContentContainer.getChildren().setAll(page);
        }catch (IOException ex){
            throw new RuntimeException();
        }
    }
    // Thread for listening invite
    private class ClientThread implements Runnable{
        @Override
        public void run(){
            System.out.println("thread start");
            byte[] incoming = new byte[256];
            while (isThreadRunning) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
                try {
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    if (msg.contains("courseInvite;")) {
                        String[] allData = msg.split(";");
                        courseWBInvite = Integer.parseInt(allData[1]);
                        tutorIdWBInvite = Integer.parseInt(allData[2]);
                        User organizer = userService.getCourseTutor(tutorIdWBInvite);
                        Platform.runLater(() -> {
                            tutorImgWB.setFill(new ImagePattern(new Image(
                                    "file:/"+organizer.getImage()
                            )));
                            tutorInvWBName.setText(organizer.getLastName()+" "+organizer.getFirstName());
                            demandeJoinWBContainer.setVisible(true);
                            String musicFile = "C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/Microsoft Teams Call Sound.mp3";
                            Media sound = new Media(new File(musicFile).toURI().toString());
                            whiteboardPlayer = new MediaPlayer(sound);
                            whiteboardPlayer.play();
                            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(41000), e -> {
                                whiteboardPlayer.dispose();
                                demandeJoinWBContainer.setVisible(false);
                            }));
                            timeline.setCycleCount(1);
                            timeline.play();
                        });
                    }
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

            }
        }
    }
    @FXML
    public void joWBMeeting(MouseEvent event){
        whiteboardPlayer.dispose();
        demandeJoinWBContainer.setVisible(false);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/whiteboard.fxml"));
            Parent root = fxmlLoader.load();
            WhiteBoardController controller = fxmlLoader.getController();
            controller.setCourseId(courseWBInvite,this,true,tutorIdWBInvite);
            mainContentContainer.getChildren().clear();
            mainContentContainer.getChildren().add(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void declineWBMeeting(MouseEvent event){
        whiteboardPlayer.dispose();
        demandeJoinWBContainer.setVisible(false);
    }
}
