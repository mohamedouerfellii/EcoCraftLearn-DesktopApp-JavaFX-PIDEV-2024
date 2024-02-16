package tn.SIRIUS.controller.tutors;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import tn.SIRIUS.services.CourseService;

import java.io.IOException;
import java.io.InputStream;
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
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
            mainContentContainer.getChildren().removeAll();
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
                Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/tutors/CoursesMainPage.fxml")));
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
                mainContentContainer.getChildren().clear();
                mainContentContainer.getChildren().setAll(page);
            }catch (IOException ex){
                throw new RuntimeException();
            }
        });

        eventsBtn.setOnMouseClicked(e -> {
            eventsBtn.setStyle(styleMenuBtnClicked);
            eventsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/calendar-event-line.png")));
            if(coursesBtn.getStyle().equals(styleMenuBtnClicked)){
                coursesBtn.setStyle(styleMenuBtnNormal);
                coursesBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/graduation-cap-fill.png")));
            }
            if(dashboardBtn.getStyle().equals(styleMenuBtnClicked)){
                dashboardBtn.setStyle(styleMenuBtnNormal);
                dashboardBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/dashboard-fill.png")));
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
                Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/tutors/HomePageEventController.fxml")));
                mainContentContainer.getChildren().clear();
                mainContentContainer.getChildren().setAll(page);
            }catch (IOException ex){
                throw new RuntimeException();
            }
        });
    }
}
