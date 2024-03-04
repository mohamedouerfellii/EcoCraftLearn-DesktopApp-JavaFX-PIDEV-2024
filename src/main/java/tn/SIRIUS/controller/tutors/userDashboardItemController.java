package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.UserService;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class userDashboardItemController implements Initializable {


    @FXML
    private VBox feedbackItemContainer;
@FXML
private AnchorPane doneAction;
    @FXML
    private Label fullNameLabel;
    @FXML
    private Button banButton;

    @FXML
    private Circle imageCircle;
@FXML
private Circle banImg;
    @FXML
    private Label roleLabel;
    private User user;

    public void setUserData(User user){
        imageCircle.setFill(new ImagePattern(
                new Image(user.getImage().replace("\\","/"))
        ));
        if(!user.isActive()){
            banImg.setVisible(true);
            banImg.setFill(new ImagePattern(new Image("/images/banIcon.png")));
        }

        else
            banImg.setVisible(false);
        fullNameLabel.setText(user.getFirstName()+" "+user.getLastName());
        roleLabel.setText(user.getRoles());
        this.user = user;
        banButton.setOnAction(event ->
        {
            banUser(user.getId(),false);
        });

    }

    @FXML
    public void onDetailsClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/tutors/operationAdmin.fxml"));
        Parent root = fxmlLoader.load();
        OperationAdminController controller = fxmlLoader.getController();
        controller.showUserDetails(this.user);
        Scene scene = new Scene(root, 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void banUser(int id,boolean b)
    {

        UserService usr = new UserService();
        boolean currentUserState = usr.isUserActive(id);

        if (currentUserState) {

            usr.updateUserIsActive(id, false);
            doneAction.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> doneAction.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
            banImg.setVisible(true);
            banImg.setFill(new ImagePattern(new Image("/images/banIcon.png")));

        } else {

            usr.updateUserIsActive(id, true);
            doneAction.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> doneAction.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();

            banImg.setVisible(false);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doneAction.setVisible(false);
    }
}
