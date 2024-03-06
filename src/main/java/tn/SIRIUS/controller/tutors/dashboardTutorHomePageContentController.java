package tn.SIRIUS.controller.tutors;

import javafx.scene.layout.AnchorPane;
import tn.SIRIUS.entities.Course;
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
import tn.SIRIUS.services.CourseParticipationService;
import tn.SIRIUS.services.CourseService;
import tn.SIRIUS.services.FeedbackService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
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
    @FXML private Label nbrTotStudents;
    @FXML private Label nbrNewStudent;
    @FXML private Label nbrCoursesTutor;
    @FXML private AnchorPane courseT1;
    @FXML private AnchorPane courseT2;
    @FXML private AnchorPane courseT3;
    private int userTest;
    private CourseParticipationService courseParticipationService = new CourseParticipationService();
    private CourseService courseService = new CourseService();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        userTest = 1;
        // Stat
        int nbrTotStd = courseParticipationService.getTotNbrStudentByTutor(userTest).size();
        int nbrNewStd = courseParticipationService.getLastMonthEnrolled(userTest);
        int nbrCourseTt = courseService.getListIdCoursesByTutor(userTest).size();
        if(nbrTotStd < 10 ) nbrTotStudents.setText("00"+nbrTotStd);
        else if(nbrTotStd > 10 && nbrTotStd < 100) nbrTotStudents.setText("0"+nbrTotStd);
        else nbrTotStudents.setText(String.valueOf(nbrTotStd));
        if(nbrNewStd < 10 ) nbrNewStudent.setText("00"+nbrNewStd);
        else if(nbrNewStd > 10 && nbrNewStd < 100) nbrNewStudent.setText("0"+nbrNewStd);
        else nbrNewStudent.setText(String.valueOf(nbrNewStd));
        if(nbrCourseTt < 10 ) nbrCoursesTutor.setText("00"+nbrNewStd);
        else if(nbrCourseTt > 10 && nbrCourseTt < 100) nbrCoursesTutor.setText("0"+nbrNewStd);
        else nbrCoursesTutor.setText(String.valueOf(nbrNewStd));
        //Gender Ratio
        List<Integer> genderCountList = courseParticipationService.countGenderStudentByTutor(userTest);
        int nbrMale = genderCountList.get(0);
        int nbrFemale = genderCountList.get(1);
        ObservableList<PieChart.Data> genderRatioDate = FXCollections.observableArrayList(new PieChart.Data("Male",nbrMale),new PieChart.Data("Female",nbrFemale));
        genderRatio.setData(genderRatioDate);
        genderRatioMDesc.setText("( "+nbrMale+" Male Students )");
        genderRatioFDesc.setText("( "+nbrFemale+" Female Students )");
        //Feedback
        showFeedbacks();
        //Top 3 Courses
        showTop3Courses();
    }
    public void showFeedbacks(){
        FeedbackService feedbackService = new FeedbackService();
        List<Feedback> feedbacks = feedbackService.getFeedbackByTutor(userTest);
        try{
            for(Feedback feedback : feedbacks){
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
    }
    public void showTop3Courses(){
        List<Course> top3Courses = courseService.top3CoursesByTutor(userTest);
        if(top3Courses.isEmpty()){
            courseT1.setVisible(false);
            courseT2.setVisible(false);
            courseT3.setVisible(false);
        } else if(top3Courses.size() == 1){
            Image top1Img = new Image("file:/" + top3Courses.get(0).getImage());
            top1CourseImgContainer.setFill(new ImagePattern(top1Img));
            top1CourseTitle.setText(top3Courses.get(0).getTitle());
            top1NbrRegistred.setText(top3Courses.get(0).getNbrRegistred()+" Registered students");
            courseT2.setVisible(false);
            courseT3.setVisible(false);
        } else if(top3Courses.size() == 2){
            Image top1Img = new Image("file:/" + top3Courses.get(0).getImage());
            top1CourseImgContainer.setFill(new ImagePattern(top1Img));
            top1CourseTitle.setText(top3Courses.get(0).getTitle());
            top1NbrRegistred.setText(top3Courses.get(0).getNbrRegistred()+" Registered students");
            Image top2Img = new Image("file:/" + top3Courses.get(1).getImage());
            top2CourseImgContainer.setFill(new ImagePattern(top2Img));
            top2CourseTitle.setText(top3Courses.get(1).getTitle());
            top2NbrRegistred.setText(top3Courses.get(1).getNbrRegistred()+" Registered students");
            courseT3.setVisible(false);
        } else{
            Image top1Img = new Image("file:/" + top3Courses.get(0).getImage());
            top1CourseImgContainer.setFill(new ImagePattern(top1Img));
            top1CourseTitle.setText(top3Courses.get(0).getTitle());
            top1NbrRegistred.setText(top3Courses.get(0).getNbrRegistred()+" Registered students");
            Image top2Img = new Image("file:/" + top3Courses.get(1).getImage());
            top2CourseImgContainer.setFill(new ImagePattern(top2Img));
            top2CourseTitle.setText(top3Courses.get(1).getTitle());
            top2NbrRegistred.setText(top3Courses.get(1).getNbrRegistred()+" Registered students");
            Image top3Img = new Image("file:/" + top3Courses.get(2).getImage());
            top3CourseImgContainer.setFill(new ImagePattern(top3Img));
            top3CourseTitle.setText(top3Courses.get(2).getTitle());
            top3NbrRegistred.setText(top3Courses.get(2).getNbrRegistred()+" Registered students");
        }
    }
}
