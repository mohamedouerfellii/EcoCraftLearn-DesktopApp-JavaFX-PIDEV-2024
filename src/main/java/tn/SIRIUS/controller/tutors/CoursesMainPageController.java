package tn.SIRIUS.controller.tutors;

import tn.SIRIUS.entities.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import tn.SIRIUS.services.CourseService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CoursesMainPageController implements Initializable {

    @FXML
    private TextArea addCourseDesc;

    @FXML
    private TextField addCourseDuration;

    @FXML
    private Button addCourseFormBtn;

    @FXML
    private AnchorPane addCoursePane;

    @FXML
    private TextField addCoursePrice;

    @FXML
    private Button addNewCourseBtn;

    @FXML
    private Button addSectionBtn;

    @FXML
    private Button chooseCourseImgBtn;

    @FXML
    private Rectangle courseDetailsImgContainer;

    @FXML
    private ImageView courseImgPreview;

    @FXML
    private AnchorPane coursesDetailsContainer;

    @FXML
    private VBox coursesListContainer;

    @FXML
    private Button deleteCourseBtn;

    @FXML
    private Text descriptionLabelDetail;

    @FXML
    private Text durationLabelDetail;

    @FXML
    private Button editCourseBtn;

    @FXML
    private Button goBackCourseBtn;

    @FXML
    private Button goBackCourseDetBtn;

    @FXML
    private Label idLabelDetail;

    @FXML
    private Text nbrRegistredLabelDetail;

    @FXML
    private Text nbrSecLabelDetail;

    @FXML
    private Text postedDateLabelDetail;

    @FXML
    private Text priceLabelDetail;

    @FXML
    private TextField searchInput;

    @FXML
    private TextField titleInputAddCourse;

    @FXML
    private Text titleLabelDetail;

    @FXML
    private Text tutorLabelDetail;
    private String addCourseImgPath;
    List<Course> courses;
    private int idC;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        addCoursePane.setVisible(false);
        coursesDetailsContainer.setVisible(false);
        CourseService courseService = new CourseService();
        courses = courseService.getAll();
       try {
            for(Course course : courses) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/courseDashboardItem.fxml"));
                Parent root = fxmlLoader.load();
                CourseDashboardItemController itemController = fxmlLoader.getController();
                itemController.setMainPageController(this);
                itemController.setCourseItemData(course);
                coursesListContainer.getChildren().add(root);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addNewCourseBtn.setOnMouseClicked(e -> addCoursePane.setVisible(true));
        goBackCourseBtn.setOnMouseClicked(e -> addCoursePane.setVisible(false));
        chooseCourseImgBtn.setOnMouseClicked(e -> {
            FileChooser imageCourseChooser = new FileChooser();
            imageCourseChooser.setTitle("Choose your course image");
            FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                    "Image Files", "*.png", "*.jpg", "*.gif");
            imageCourseChooser.getExtensionFilters().add(imageFilter);
            File selectedFile = imageCourseChooser.showOpenDialog(null);
            if (selectedFile != null) {
                addCourseImgPath = selectedFile.getAbsolutePath();
                courseImgPreview.setImage(new Image("file:///" +addCourseImgPath.replace("\\","/")));
            }
        });
        goBackCourseDetBtn.setOnMouseClicked(e -> coursesDetailsContainer.setVisible(false));
    }
    @FXML
    void addNewCourse(MouseEvent event) {
        String title = titleInputAddCourse.getText();
        String description = addCourseDesc.getText();
        float price = Float.parseFloat(addCoursePrice.getText());
        String duration = addCourseDuration.getText();
        CourseService courseService = new CourseService();
        Course course = new Course(0,addCourseImgPath,title,description,1,duration,price,0,"",0);
        if(courseService.add(course) == 1){
            courses = courseService.getAll();
            coursesListContainer.getChildren().clear();
            try {
                for(Course c : courses) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/tutors/courseDashboardItem.fxml"));
                    Parent root = fxmlLoader.load();
                    CourseDashboardItemController itemController = fxmlLoader.getController();
                    itemController.setMainPageController(this);
                    itemController.setCourseItemData(c);
                    coursesListContainer.getChildren().add(root);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
            System.out.println("FAILED");
    }
    public void showCourseDetails(Course course) {
        coursesDetailsContainer.setVisible(true);
        courseDetailsImgContainer.setFill(new ImagePattern(
                new Image("file:///" +course.getImage().replace("\\","/"))
        ));
        titleLabelDetail.setText(course.getTitle());
        idLabelDetail.setText(String.valueOf(course.getId()));
        tutorLabelDetail.setText(String.valueOf(course.getTutor()));
        priceLabelDetail.setText(String.valueOf(course.getPrice()));
        nbrSecLabelDetail.setText(String.valueOf(course.getNbrSection()));
        nbrRegistredLabelDetail.setText(String.valueOf(course.getNbrRegistred()));
        descriptionLabelDetail.setText(course.getDescription());
        durationLabelDetail.setText(course.getDuration());
        postedDateLabelDetail.setText(course.getPostedDate());
    }
}
