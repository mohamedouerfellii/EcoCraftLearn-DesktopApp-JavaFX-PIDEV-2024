package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.CourseParticipation;
import tn.SIRIUS.services.CourseParticipationService;
import tn.SIRIUS.services.SectionService;

import java.text.DecimalFormat;

public class CoursesRegisteredItemController{
    @FXML
    private AnchorPane optionsContainer;
    @FXML
    private Label tutorName;
    @FXML
    private Text titleCourseText;
    @FXML
    private Text nbrRegistredText;
    @FXML
    private Text progressPrcText;
    @FXML
    private Rectangle prcProgressBar;
    @FXML
    private Rectangle courseImgContainer;
    private CoursesMainPageController coursesMainPageController;
    private Course course;
    private SectionService sectionService;
    private CourseParticipationService cpService;
    private final int USER_TEST = 4;
    @FXML
    public void moreOptionsBtnClicked(MouseEvent event){
        optionsContainer.setVisible(!optionsContainer.isVisible());
    }
    @FXML
    public void unrollOptionClicked(MouseEvent event){
        coursesMainPageController.showConfirmationUnroll(course);
    }
    @FXML
    public void rateOptionClicked(MouseEvent event){
        coursesMainPageController.showRatingForm(course);
    }
    public void setData(Course course, CoursesMainPageController controller){
        sectionService = new SectionService();
        cpService = new CourseParticipationService();
        courseImgContainer.setFill(new ImagePattern(new Image("file:/"+course.getImage().replace("\\","/"))));
        tutorName.setText("Course | "+course.getTutor().getFirstName()+" "+course.getTutor().getLastName());
        titleCourseText.setText(course.getTitle());
        nbrRegistredText.setText(course.getNbrRegistred()+" Registered");
        int nbrTotSection = sectionService.countSectionByCourse(course.getId());
        CourseParticipation cp = cpService.getCourseParticipationById(USER_TEST,course.getId());
        float prcProg = ((float) cp.getSectionDone()/nbrTotSection)*100;
        DecimalFormat df = new DecimalFormat("#.##");
        progressPrcText.setText(df.format(prcProg)+"%");
        prcProgressBar.setWidth(prcProg*2);
        coursesMainPageController = controller;
        this.course = course;
    }
    @FXML
    public void continueLearnBtnClicked(MouseEvent event){
        coursesMainPageController.setUpLearningPage(course,false);
    }

}
