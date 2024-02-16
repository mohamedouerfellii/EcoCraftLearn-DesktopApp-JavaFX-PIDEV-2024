package tn.SIRIUS.controller.tutors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GURDService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class dashboardTutorHomePageContentController implements Initializable {
    @FXML
    private VBox feedbackContentContainer;
    @FXML
    private PieChart genderRatio;
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

    List<User> users = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Feedback
        showAllUsers();
        //Gender Ratio
       /* ObservableList<PieChart.Data> genderRatioDate = FXCollections.observableArrayList(new PieChart.Data("Male",25),new PieChart.Data("Female",15));
        genderRatio.setData(genderRatioDate);
        genderRatioMDesc.setText("( 25 Male Students )");
        genderRatioFDesc.setText("( 15 Female Students )");
        //Top 3 Courses

        top1CourseTitle.setText("Japanese Furoshiki");
        top1NbrRegistred.setText("107 Registered students");

        top2CourseTitle.setText("Reversible Tote");
        top2NbrRegistred.setText("88 Registered students");

        top3CourseTitle.setText("Newspaper weave baskets");
        top3NbrRegistred.setText("57 Registered students");*/
    }



    public void showAllUsers(){
        GURDService use=new GURDService();
        users = use.getAll();
        try {
            for(User u : users) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/usersDashboardItem.fxml"));
                Parent root = fxmlLoader.load();
                userDashboardItemController itemController = fxmlLoader.getController();
                itemController.setFeedBackData(u);
                feedbackContentContainer.getChildren().add(root);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
