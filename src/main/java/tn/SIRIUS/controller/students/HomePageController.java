package tn.SIRIUS.controller.students;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.SIRIUS.entities.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import tn.SIRIUS.services.CourseService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private VBox coursesVboxHomePage;
    @FXML
    private Pane mainPageContainer;
    private String styleMenuBtnClicked;
    private String styleMenuBtnNormal;
    private List<Course> courses;
    private CourseService courseService;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Profile Image
        Image profileImg = new Image("/images/profilePictures/12.jpg");
        profilImgContainer.setFill(new ImagePattern(profileImg));
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
        Image mostProgressedCourseImg = new Image("/images/coursesImg/Finished-Basket-3-cropped.jpg");
        mostProgressedCourseImgContainer.setFill(new ImagePattern(mostProgressedCourseImg));
        double prcProg = 99.0;
        double progressCourseValue = (prcProg / 100.0) * 360;
        mostProgressedCoursePrc.setLength(progressCourseValue);
        mostProgressedCoursePrcVal.setText(String.valueOf(prcProg)+"%");
        //Menu
        styleMenuBtnClicked =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: #D5FFDC;
                -fx-background-radius: 24px;
                -fx-text-fill: #1D6611;
                -fx-cursor: hand;""";
        styleMenuBtnNormal =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: transparent;
                -fx-text-fill: #D5FFDC;
                -fx-cursor: hand;""";
        homeBtn.setStyle(styleMenuBtnClicked);
        homeBtnImg.setImage(new Image("/icons/dark/home-4-fill.png"));
        // Services
        courseService = new CourseService();
        // Init Page
        showAllCourses();
    }
    // Menu
    @FXML
    public void homeBtnClicked(MouseEvent event){
        homeBtn.setStyle(styleMenuBtnClicked);
        homeBtnImg.setImage(new Image("/icons/dark/home-4-fill.png"));
        if(coursesBtn.getStyle().equals(styleMenuBtnClicked)){
            coursesBtn.setStyle(styleMenuBtnNormal);
            coursesBtnImg.setImage(new Image("/icons/light/graduation-cap-fill.png"));
        } else if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
            eventsBtn.setStyle(styleMenuBtnNormal);
            eventsBtnImg.setImage(new Image("/icons/light/calendar-event-line.png"));
        } else if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
            forumBtn.setStyle(styleMenuBtnNormal);
            forumBtnImg.setImage(new Image("/icons/light/group-fill.png"));
        } else if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
            productsBtn.setStyle(styleMenuBtnNormal);
            productsBtnImg.setImage(new Image("/icons/light/shopping-bag-3-fill.png"));
        } else if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
            collectsBtn.setStyle(styleMenuBtnNormal);
            collectsBtnImg.setImage(new Image("/icons/light/recycle-fill.png"));
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/homePage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root,1350,720);
            Stage stage = (Stage) mainPageContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void coursesBtnClicked(MouseEvent event){
        coursesBtn.setStyle(styleMenuBtnClicked);
        coursesBtnImg.setImage(new Image("/icons/dark/graduation-cap-fill.png"));
        if(homeBtn.getStyle().equals(styleMenuBtnClicked)){
            homeBtn.setStyle(styleMenuBtnNormal);
            homeBtnImg.setImage(new Image("/icons/light/home-4-fill.png"));
        } else if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
            eventsBtn.setStyle(styleMenuBtnNormal);
            eventsBtnImg.setImage(new Image("/icons/light/calendar-event-line.png"));
        } else if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
            forumBtn.setStyle(styleMenuBtnNormal);
            forumBtnImg.setImage(new Image("/icons/light/group-fill.png"));
        } else if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
            productsBtn.setStyle(styleMenuBtnNormal);
            productsBtnImg.setImage(new Image("/icons/light/shopping-bag-3-fill.png"));
        } else if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
            collectsBtn.setStyle(styleMenuBtnNormal);
            collectsBtnImg.setImage(new Image("/icons/light/recycle-fill.png"));
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPage.fxml"));
            Parent root = fxmlLoader.load();
            CoursesMainPageController controller = fxmlLoader.getController();
            controller.getCourses(courses);
            mainPageContainer.getChildren().clear();
            mainPageContainer.getChildren().add(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // Home Page Start
    public void showAllCourses(){
        courses = courseService.getAll();
        for (Course course : courses){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesHomePageItem.fxml"));
                Parent root = fxmlLoader.load();
                CoursesHomePageItemController itemController = fxmlLoader.getController();
                itemController.setCourseData(course);
                coursesVboxHomePage.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
