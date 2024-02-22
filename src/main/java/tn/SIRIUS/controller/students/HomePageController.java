package tn.SIRIUS.controller.students;


import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomePageController implements Initializable {
    @FXML
    private Circle profilImgContainer;
    @FXML
    private HBox notifCount;
    @FXML
    private HBox msgCount;
    @FXML
    private Label welcomeMsg;
    @FXML
    private Circle mostProgressedCourseImgContainer;
    @FXML
    private Arc mostProgressedCoursePrc;
    @FXML
    private Label mostProgressedCoursePrcVal;
    @FXML
    private Button homeBtn;
    @FXML
    private ImageView homeBtnImg;
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
    private ScrollPane coursesScrollPane;
    @FXML
    private VBox coursesVboxHomePage;
    @FXML
    private Pane mainContentContainer;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Profile Image
        try {
            Image profileImg = new Image(getClass().getResourceAsStream("/images/profilePictures/12.jpg"));
            profilImgContainer.setFill(new ImagePattern(profileImg));
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }

        //Msg and Notifications
        Label nbrNotif = new Label();
        Label nbrMsg = new Label();
        nbrNotif.setText("5");
        nbrMsg.setText("2");
        nbrNotif.setStyle("-fx-font-family: \"Jost\";-fx-font-weight: bold;-fx-font-size: 16;-fx-text-fill: white;");
        nbrMsg.setStyle("-fx-font-family: \"Jost\";-fx-font-weight: bold;-fx-font-size: 16;-fx-text-fill: white;");
        notifCount.getChildren().add(nbrNotif);
        msgCount.getChildren().add(nbrMsg);
        //Welcome Msg
        String welcomeText = "Welcome Abdallah!";
        welcomeMsg.setText(welcomeText);
        //Most progressed courses
        Image mostProgressedCourseImg = new Image(getClass().getResourceAsStream("/images/coursesImg/Finished-Basket-3-cropped.jpg"));
        mostProgressedCourseImgContainer.setFill(new ImagePattern(mostProgressedCourseImg));
        double prcProg = 99.0;
        double progressCourseValue = (prcProg / 100.0) * 360;
        mostProgressedCoursePrc.setLength(progressCourseValue);
        mostProgressedCoursePrcVal.setText(String.valueOf(prcProg)+"%");
        //Menu
        String styleMenuBtnClicked =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: #D5FFDC;
                -fx-background-radius: 24px;
                -fx-text-fill: #1D6611;""";
        String styleMenuBtnHover =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: #1D6611;
                -fx-background-radius: 24px;
                -fx-opacity: 0.6;
                -fx-text-fill: #D5FFDC;""";
        String styleMenuBtnNormal =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: transparent;
                -fx-text-fill: #D5FFDC;""";
        homeBtn.setStyle(styleMenuBtnClicked);
        homeBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/home-4-fill.png")));

        //Menu Btn Clicked
        forumBtn.setOnMouseClicked(e -> {
            forumBtn.setStyle(styleMenuBtnClicked);
            forumBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/group-fill.png")));
            if(homeBtn.getStyle().equals(styleMenuBtnClicked)){
                homeBtn.setStyle(styleMenuBtnNormal);
                homeBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/home-4-fill.png")));
            }
            if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
                eventsBtn.setStyle(styleMenuBtnNormal);
                eventsBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/calendar-event-line.png")));
            }
            if(coursesBtn.getStyle().equals(styleMenuBtnClicked)){
                coursesBtn.setStyle(styleMenuBtnNormal);
                coursesBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/graduation-cap-fill.png")));
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
                FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/gui/students/ForumPage.fxml"));
                Parent page = fxmlLoader.load();
                mainContentContainer.getChildren().removeAll();
                mainContentContainer.getChildren().setAll(page);
            }catch (IOException ex){
                throw new RuntimeException();
            }
        });
        homeBtn.setOnMouseClicked(e -> {
            homeBtn.setStyle(styleMenuBtnClicked);
            homeBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/dark/home-4-fill.png")));
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
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/homePage.fxml"));
                mainContentContainer.getChildren().removeAll();
                Parent allPage = fxmlLoader.load();
                Parent page = (Parent) allPage.lookup("#mainContentContainer");
                mainContentContainer.getChildren().setAll(page.getChildrenUnmodifiable());
            }catch (IOException ex){
                throw new RuntimeException();
            }
        });
    }





//        //Courses part
//        coursesScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        Course course = new Course(1,"/images/coursesImg/tote_428_597_s_c1_428_597_int_c1.png","test course display","Description",1,"08h 30m",250.0f,5,"20-01-2024",0);
//        try {
//            for (int i = 0; i < 6; i++) {
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesHomePageItem.fxml"));
//                Parent root = fxmlLoader.load();
//                CoursesHomePageItemController itemController = fxmlLoader.getController();
//                itemController.setCourseData(course);
//                coursesVboxHomePage.getChildren().add(root);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }




}
