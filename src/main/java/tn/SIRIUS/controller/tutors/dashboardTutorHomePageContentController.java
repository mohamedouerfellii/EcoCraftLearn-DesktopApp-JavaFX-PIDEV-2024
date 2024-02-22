package tn.SIRIUS.controller.tutors;

import tn.SIRIUS.entities.Feedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.User;

import java.io.IOException;
import java.net.URL;
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
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Feedback
        Feedback feedback = new Feedback(1,new User(2,"Ouerfelli","Mohemed","mohamedouerfelli3@gmail.com",""),"02-10-2024","“Your knowledge and clear communication made learning",4,1);
        try{
            for(int i=0; i<5; i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/feedbackDashboardItem.fxml"));
                Parent item = fxmlLoader.load();
                feedbackDashboardItemController itemController = fxmlLoader.getController();
                itemController.setFeedBackData(feedback);
                feedbackContentContainer.getChildren().add(item);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        //Gender Ratio
        ObservableList<PieChart.Data> genderRatioDate = FXCollections.observableArrayList(new PieChart.Data("Male",25),new PieChart.Data("Female",15));
        genderRatio.setData(genderRatioDate);
        genderRatioMDesc.setText("( 25 Male Students )");
        genderRatioFDesc.setText("( 15 Female Students )");
        //Top 3 Courses
        /*Image top1Img = new Image(getClass().getResourceAsStream("/images/coursesImg/Furoshiki_Wrapping_Step_2_546_595_s_c1dd_546_595_int_c1.png"));
        top1CourseImgContainer.setFill(new ImagePattern(top1Img));*/
        top1CourseTitle.setText("Japanese Furoshiki");
        top1NbrRegistred.setText("107 Registered students");
       /* Image top2Img = new Image(getClass().getResourceAsStream("/images/coursesImg/tote_428_597_s_c1_428_597_int_c1.png"));
        top2CourseImgContainer.setFill(new ImagePattern(top2Img));*/
        top2CourseTitle.setText("Reversible Tote");
        top2NbrRegistred.setText("88 Registered students");
       /* Image top3Img = new Image(getClass().getResourceAsStream("/images/coursesImg/Finished-Basket-3-cropped.jpg"));
        top3CourseImgContainer.setFill(new ImagePattern(top3Img));*/
        top3CourseTitle.setText("Newspaper weave baskets");
        top3NbrRegistred.setText("57 Registered students");
    }
}
