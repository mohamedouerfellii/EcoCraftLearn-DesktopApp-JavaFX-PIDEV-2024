package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;
import org.w3c.dom.events.MouseEvent;
import tn.SIRIUS.controller.SignUpController;
import tn.SIRIUS.entities.Session;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GURDService;
import tn.SIRIUS.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OperationAdminController implements Initializable {


    @FXML
    private ImageView closeWarningPassword;

    @FXML
    private Button confirmDeleteCourseBtn;

    @FXML
    private AnchorPane coursesDetailsContainer;

    @FXML
    private ImageView deleteCourseBtnImg;

    @FXML
    private Button deleteuser;

    @FXML
    private Label done;

    @FXML
    private AnchorPane doneEditCourse;

    @FXML
    private ImageView editCourseBtnImg;

    @FXML
    private Button editUser;

    @FXML
    private AnchorPane editanchorpane;

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
    private TextField editstatususer;

    @FXML
    private Text emailLabelDetail;

    @FXML
    private Text firstNameLabelDetail;

    @FXML
    private Text genderLabelDetail;

    @FXML
    private Button goBackCourseBtnEdit;

    @FXML
    private Button goBackCourseDetBtn;

    @FXML
    private Label idLabelDetail;

    @FXML
    private Text isActiveLabelDetail;

    @FXML
    private Text lastNameLabelDetail;

    @FXML
    private Text nbrPtCollectLabelDetail;

    @FXML
    private Text numberLabelDetail;

    @FXML
    private PasswordField passwordConfirmDelete;

    @FXML
    private Text passwordLabelDetail;

    @FXML
    private ImageView userImgContainer;

    @FXML
    private AnchorPane warningDeleteCourseContainer;


    private String addCourseImgPath;
    private Image detailImg;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        coursesDetailsContainer.setVisible(true);
        warningDeleteCourseContainer.setVisible(false);
        editanchorpane.setVisible(false);

    }

    public void showUserDetails(User user) {
        addCourseImgPath = user.getImage();
        System.out.println(addCourseImgPath);
        detailImg = new Image(addCourseImgPath);
        coursesDetailsContainer.setVisible(true);
        userImgContainer.setImage(detailImg);
        firstNameLabelDetail.setText(user.getFirstName());
        idLabelDetail.setText(String.valueOf("#"+user.getId()));
        lastNameLabelDetail.setText(user.getLastName());
        emailLabelDetail.setText(user.getEmail());
        passwordLabelDetail.setText(user.getPassword());
        genderLabelDetail.setText(user.getGender());
        nbrPtCollectLabelDetail.setText(String.valueOf(user.getNbrPtsCollects()));
        isActiveLabelDetail.setText(String.valueOf(user.isActive()));
        numberLabelDetail.setText(String.valueOf(user.getNumber()));

    }



    @FXML
    public void deleteCourseBtnClicked(ActionEvent event){
        warningDeleteCourseContainer.setVisible(true);
    }
    @FXML
    public void closeWarningDeleteCourse(ActionEvent event){
        warningDeleteCourseContainer.setVisible(false);
    }
    @FXML
    public void confirmDeleteUser(ActionEvent event) throws IOException {
        String password = String.valueOf(passwordConfirmDelete.getText().hashCode());
        System.out.println(password);
        UserService userService = new UserService();
        if(userService.isPasswordConfirmed(Session.getUser().getId(), password)){
            System.out.println("done");
            GURDService courseService = new GURDService();
            if (courseService.delete(Integer.parseInt(idLabelDetail.getText().replace("#","")))){
                passwordConfirmDelete.clear();
                doneEditCourse.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> doneEditCourse.setVisible(false)));
                timeline.setCycleCount(1);
                timeline.play();
                FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminHomePageController.class.getResource("/gui/tutors/dashboardAdminHomePage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
                stage.setScene(scene);
                stage.show();

            }

        }
    }
    @FXML
    public void editUserBtnClicked(ActionEvent event) throws IOException {
        editanchorpane.setVisible(true);
        coursesDetailsContainer.setVisible(false);
        editfirstnameUser.setText(firstNameLabelDetail.getText());
        editlastnameuser.setText(lastNameLabelDetail.getText());
        editemailuser.setText(emailLabelDetail.getText());
        editgenderuser.setText(genderLabelDetail.getText());
        editnumberuser.setText(String.valueOf(numberLabelDetail.getText()));
        editpassworduser.setText(passwordLabelDetail.getText());
        editimguser.setImage(detailImg);
        editstatususer.setText(String.valueOf(isActiveLabelDetail.getText()));

    }
    @FXML
    public void editUserButton(ActionEvent event){
int id=Integer.parseInt(idLabelDetail.getText().replace("#",""));
String firstName=editfirstnameUser.getText();
String lastName=editlastnameuser.getText();
String Email=editemailuser.getText();
int number=Integer.parseInt(editnumberuser.getText());
String gender=editgenderuser.getText();
        String Password = String.valueOf(editpassworduser.getText().hashCode());

        GURDService serv=new GURDService();

        User user=new User(id, firstName, lastName, number, Email, gender, Password,addCourseImgPath);


        if (serv.update(user))
        {
            System.out.println("SUCCES");
            showUserDetails(user);
        }

        editanchorpane.setVisible(false);
        coursesDetailsContainer.setVisible(true);
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
            addCourseImgPath = selectedFile.toURI().toString();
            Image image = new Image(selectedFile.toURI().toString());
            editimguser.setImage(image);
        }
    }
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminHomePageController.class.getResource("/gui/tutors/dashboardAdminHomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }


}
