package tn.SIRIUS.controller.tutors;

import com.google.protobuf.StringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import tn.SIRIUS.controller.SignUpController;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GURDService;
import tn.SIRIUS.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class dashboardAdminHomePageContainerController implements Initializable {
    @FXML
    private VBox feedbackContentContainer;
    @FXML
    private PieChart genderRatio;
    @FXML
    private Label nbrStudents;
    @FXML
    private Label genderRatioFDesc;
    @FXML
    private Label genderRatioMDesc;
    @FXML
    private Circle top1CourseImgContainer;
    @FXML
    private Label top1CourseTitle;
    @FXML
    private Label top1NbrRegistred;
    @FXML
    private Circle top2CourseImgContainer;
    @FXML
    private Label top2CourseTitle;
    @FXML
    private Label top2NbrRegistred;
    @FXML
    private Circle top3CourseImgContainer;
    @FXML
    private Label top3CourseTitle;
    @FXML
    private Label top3NbrRegistred;
    @FXML
    private TextField searchInput;

    @FXML
    private Label nbrTotStudents1;
@FXML
        private Button filter;
    List<User> users = new ArrayList<>();
User user;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        UserService srv=new UserService();
        int nb=srv.getNbrStudent("student");
       nbrStudents.setText(String.valueOf(nb));
        //showUsers
            showAllUsers();
        //handleSearch();

    }

    @FXML
    public void showFilterUser() {
        UserService userService = new UserService();

        if ("By name".equals(filter.getText())) {
            // Initial action
            filter.setText("By role");
            users = userService.getAllFilterByRole();
        } else {
            // Alternative action when the button is clicked again
            filter.setText("By name");
            users = userService.getAllFilterByName();
        }

        displayUsers();
    }
    @FXML
    private void handleSearch() {
        String searchTerm = searchInput.getText().trim();
        feedbackContentContainer.getChildren().clear();
        showAllUsers(searchTerm);
    }

    public void showAllUsers(String searchTerm) {
        UserService userService = new UserService();
        users = userService.searchUsers(searchTerm);
        displayUsers();
    }

    public void showAllUsers() {
        GURDService use = new GURDService();
        users = use.getAll();
        displayUsers();
    }

    private void displayUsers() {
        try {
            feedbackContentContainer.getChildren().clear();
            for (User u : users) {
                if (!(u.getRoles().equals("admin")))
                {  FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/usersDashboardItem.fxml"));
                Parent root = fxmlLoader.load();
                userDashboardItemController itemController = fxmlLoader.getController();
                itemController.setFeedBackData(u);
                feedbackContentContainer.getChildren().add(root);
            }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
