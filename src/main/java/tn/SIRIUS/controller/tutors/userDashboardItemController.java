package tn.SIRIUS.controller.tutors;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.SIRIUS.controller.SignUpController;
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
    private Label fullNameLabel;
    @FXML
    private Button banButton;

    @FXML
    private Circle imageCircle;

    @FXML
    private Label roleLabel;
    private User user;

    public void setFeedBackData(User user){
        imageCircle.setFill(new ImagePattern(
                new Image(user.getImage().replace("\\","/"))
        ));
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
        controller.showUserDetails(user);
        Scene scene = new Scene(root, 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void banUser(int id,boolean b)
    {

        UserService usr=new UserService();
        if(usr.updateUserIsActive(id,b)==1)
        {
            System.out.println("done");
        }
        else
            System.out.println("error");

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
