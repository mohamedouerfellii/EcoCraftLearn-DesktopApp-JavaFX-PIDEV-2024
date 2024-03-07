package tn.SIRIUS.controller.admins;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.SIRIUS.controller.SignUpController;
import tn.SIRIUS.entities.Session;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GRUDService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardTutorHomePageController implements Initializable {
    @FXML
    private Button closeChat1;

    @FXML
    private ImageView closeWarningPassword;

    @FXML
    private Button collectsBtn;

    @FXML
    private Button coursesBtn;

    @FXML
    private ImageView coursesBtnImg;

    @FXML
    private Button dashboardBtn;

    @FXML
    private ImageView dashboardBtnImg;

    @FXML
    private ImageView deleteCourseBtnImg;

    @FXML
    private Button deleteuser;

    @FXML
    private Pane detailsPane;

    @FXML
    private ImageView editCourseBtnImg;

    @FXML
    private Pane editPane;

    @FXML
    private Button editUser;

    @FXML
    private Button editbutton;

    @FXML
    private TextField editemailuser;

    @FXML
    private TextField editfirstnameUser;

    @FXML
    private TextField editgenderuser;

    @FXML
    private Button editimgbutton;

    @FXML
    private ImageView editimguser;


    @FXML
    private TextField editlastnameuser;

    @FXML
    private TextField editnumberuser;

    @FXML
    private TextField editpassworduser;

    @FXML
    private Text emailLabelDetail;

    @FXML
    private Button eventsBtn;

    @FXML
    private Text firstNameLabelDetail;

    @FXML
    private Button forumBtn;

    @FXML
    private Text genderLabelDetail;

    @FXML
    private Text lastNameLabelDetail;

    @FXML
    private Button logoutBtn;

    @FXML
    private Pane mainContentContainer;

    @FXML
    private ImageView msgBtn;

    @FXML
    private HBox msgCount;

    @FXML
    private HBox notifCount;

    @FXML
    private Text numberLabelDetail;

    @FXML
    private AnchorPane pagesContainer;

    @FXML
    private Button productsBtn;

    @FXML
    private Circle profilImgContainer,profileImage;

    @FXML
    private Label nbrStudents;
    @FXML
    private ImageView eventsBtnImg;

    @FXML
    private ImageView forumBtnImg;

    @FXML
    private ImageView productsBtnImg;

    @FXML
    private ImageView collectsBtnImg;

    @FXML
    private ImageView logoutBtnImg;


    User loggedInUser = Session.getUser();

    public void initialize(URL url, ResourceBundle rb){

        User loggedInUser = Session.getUser();
        profileImage.setFill(new ImagePattern(new Image(loggedInUser.getImage())));
        profilImgContainer.setFill(new ImagePattern(new Image(loggedInUser.getImage())));
        lastNameLabelDetail.setText(loggedInUser.getLastName());
        firstNameLabelDetail.setText(loggedInUser.getFirstName());
        emailLabelDetail.setText(loggedInUser.getEmail());
        numberLabelDetail.setText(String.valueOf(loggedInUser.getNumber()));
        genderLabelDetail.setText(loggedInUser.getGender());
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
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/dashboardAdminHomePageContainer.fxml"));
            mainContentContainer.getChildren().removeAll();
            Parent page = fxmlLoader.load();
            mainContentContainer.getChildren().setAll(page);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
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
                Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/tutors/operationAdmin.fxml")));
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

    }
    @FXML
    public void logOut(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("/gui/Login.fxml"));
        Session.logout();
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void openUserDetails(ActionEvent event){
        detailsPane.setVisible(true);
        applyBlurEffect();


    }
    private String img=loggedInUser.getImage();
    @FXML
    public void editUserBtnClicked(ActionEvent event) throws IOException {
        detailsPane.setVisible(false);
        editPane.setVisible(true);
        editfirstnameUser.setText(firstNameLabelDetail.getText());
        editlastnameuser.setText(lastNameLabelDetail.getText());
        editemailuser.setText(emailLabelDetail.getText());
        editgenderuser.setText(genderLabelDetail.getText());
        editnumberuser.setText(String.valueOf(numberLabelDetail.getText()));
        editpassworduser.setText("");



    }
    @FXML
    public void editUserButton(ActionEvent event){
        int id=loggedInUser.getId();
        String firstName=editfirstnameUser.getText();
        String lastName=editlastnameuser.getText();
        String Email=editemailuser.getText();
        int number=Integer.parseInt(editnumberuser.getText());
        String gender=editgenderuser.getText();
        String Password = String.valueOf(editpassworduser.getText().hashCode());

        GRUDService serv=new GRUDService();

        User user=new User(id, firstName, lastName, number, Email, gender, Password,img);


        if (serv.update(user))
        {
            profilImgContainer.setFill(new ImagePattern(new Image(user.getImage())));
            editPane.setVisible(false);
            detailsPane.setVisible(false);
            blurEffect.setWidth(0);
            blurEffect.setHeight(0);
            // Create a timeline to animate the blur effect
            Timeline timeline = new Timeline(
                    new KeyFrame(javafx.util.Duration.seconds(0.5),
                            new KeyValue(blurEffect.widthProperty(), 0),
                            new KeyValue(blurEffect.heightProperty(), 0)
                    )
            );
            mainContentContainer.setEffect(blurEffect);
            vboxitem.setEffect(blurEffect);
            hboxitem.setEffect(blurEffect);

            // Play the timeline
            timeline.play();
        }


    }
    @FXML
    public void deleteCourseBtnClicked(ActionEvent event) throws IOException {
        GRUDService userService=new GRUDService();
        userService.delete(loggedInUser.getId());
        Session.logout();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardTutorHomePageController.class.getResource("/gui/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            img = selectedFile.toURI().toString();
            Image image = new Image(selectedFile.toURI().toString());
            editimguser.setImage(image);
        }
    }
    @FXML
    public void closeDetails(ActionEvent event){
        detailsPane.setVisible(false);
        editPane.setVisible(false);
        blurEffect.setWidth(0);
        blurEffect.setHeight(0);
        // Create a timeline to animate the blur effect
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(0.5),
                        new KeyValue(blurEffect.widthProperty(), 0),
                        new KeyValue(blurEffect.heightProperty(), 0)
                )
        );
        mainContentContainer.setEffect(blurEffect);
        vboxitem.setEffect(blurEffect);
        hboxitem.setEffect(blurEffect);

        // Play the timeline
        timeline.play();
    }
    private BoxBlur blurEffect = new BoxBlur();
    @FXML
    private VBox vboxitem;
    @FXML
    private HBox hboxitem;
    @FXML
    private Pane paneitem;
    @FXML
    private ImageView arrow;
    private void applyBlurEffect() {

        blurEffect.setWidth(0);
        blurEffect.setHeight(0);
        // Create a timeline to animate the blur effect
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(0.5),
                        new KeyValue(blurEffect.widthProperty(), 10),
                        new KeyValue(blurEffect.heightProperty(), 10)
                )
        );
        mainContentContainer.setEffect(blurEffect);
        vboxitem.setEffect(blurEffect);
        hboxitem.setEffect(blurEffect);


        // Play the timeline
        timeline.play();
    }





}
