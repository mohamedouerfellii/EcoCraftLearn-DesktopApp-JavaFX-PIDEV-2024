package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GRUDService;
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
    private ScrollPane feedbackScrollPane;

    @FXML
    private Button filter;

    @FXML
    private Pane mainDashContentContainer;

    @FXML
    private TextField searchInput;

    List<User> users = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb){
        feedbackContentContainer.setAlignment(Pos.CENTER);
            showAllUsers();
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
        GRUDService use = new GRUDService();
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
                itemController.setUserData(u);
                feedbackContentContainer.setAlignment(Pos.CENTER);
                feedbackContentContainer.getChildren().add(root);
            }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
