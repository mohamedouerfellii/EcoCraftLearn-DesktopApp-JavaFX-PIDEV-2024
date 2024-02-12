package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.util.Duration;
import tn.SIRIUS.entities.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import tn.SIRIUS.services.UserService;
import tn.SIRIUS.utils.MyDB;

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
    private Button addSectionCourseBtn;
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
    @FXML
    private ImageView deleteCourseBtnImg;
    @FXML
    private ImageView editCourseBtnImg;
    @FXML
    private ImageView addSectionCourseBtnImg;
    @FXML
    private AnchorPane warningDeleteCourseContainer;
    @FXML
    private PasswordField passwordConfirmDelete;
    @FXML
    private AnchorPane successOperationContainer;
    @FXML
    private AnchorPane coursesEditContainer;
    @FXML
    private TextField editCoursePrice;
    @FXML
    private TextField editTitleInputAddCourse;
    @FXML
    private TextArea editCourseDesc;
    @FXML
    private TextField editCourseDuration;
    @FXML
    private Button editCourseFormBtn;
    @FXML
    private ImageView courseImgEditPreview;
    @FXML
    private AnchorPane doneEditCourse;
    private String addCourseImgPath;
    private Image detailImg;
    List<Course> courses;
    private int idC;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        addCoursePane.setVisible(false);
        coursesDetailsContainer.setVisible(false);
        warningDeleteCourseContainer.setVisible(false);
        successOperationContainer.setVisible(false);
        coursesEditContainer.setVisible(false);
        doneEditCourse.setVisible(false);
        showCoursesList();
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
    public void showCoursesList(){
        CourseService courseService = new CourseService();
        courses = courseService.getAll();
        coursesListContainer.getChildren().clear();
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
            showCoursesList();
            addCoursePane.setVisible(false);
            successOperationContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> {
                successOperationContainer.setVisible(false);
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else
            System.out.println("FAILED");
    }
    public void showCourseDetails(Course course) {
        addCourseImgPath = course.getImage();
        detailImg = new Image("file:///" +addCourseImgPath.replace("\\","/"));
        coursesDetailsContainer.setVisible(true);
        courseDetailsImgContainer.setFill(new ImagePattern(detailImg));
        titleLabelDetail.setText(course.getTitle());
        idLabelDetail.setText(String.valueOf("#"+course.getId()));
        tutorLabelDetail.setText(String.valueOf(course.getTutor()));
        priceLabelDetail.setText(String.valueOf(course.getPrice()));
        nbrSecLabelDetail.setText(String.valueOf(course.getNbrSection()));
        nbrRegistredLabelDetail.setText(String.valueOf(course.getNbrRegistred()));
        descriptionLabelDetail.setText(course.getDescription());
        durationLabelDetail.setText(course.getDuration());
        postedDateLabelDetail.setText(course.getPostedDate());
    }
    @FXML
    public void addSectionCourseBtnHover(MouseEvent event){
        addSectionCourseBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 20;" +
                "    -fx-background-color: #1D6611;" +
                "    -fx-text-fill: #fff;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;"+
                "    -fx-border-width: 2px;");
        addSectionCourseBtnImg.setImage(new Image(
                getClass().getResourceAsStream("/icons/light/add-circle-lineee.png")
        ));
    }
    @FXML
    public void addSectionCourseBtnNormal(MouseEvent event){
        addSectionCourseBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 20;" +
                "    -fx-background-color: #fff;" +
                "    -fx-text-fill: #1D6611;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-border-color: #1D6611;" +
                "    -fx-border-width: 2px;");
        addSectionCourseBtnImg.setImage(new Image(
                getClass().getResourceAsStream("/icons/dark/add-circle-linee.png")
        ));
    }
    @FXML
    public void editCourseBtnHover(MouseEvent event){
        editCourseBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 20;" +
                "    -fx-background-color: #BDA91A;" +
                "    -fx-text-fill: #fff;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;");
        editCourseBtnImg.setImage(new Image(
                getClass().getResourceAsStream("/icons/light/edit-box-line.png")
        ));
    }
    @FXML
    public void editCourseBtnNormal(MouseEvent event){
        editCourseBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 20;" +
                "    -fx-background-color: #fff;" +
                "    -fx-text-fill: #BDA91A;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-border-color: #BDA91A;" +
                "    -fx-border-width: 2px;");
        editCourseBtnImg.setImage(new Image(
                getClass().getResourceAsStream("/icons/dark/edit-box-line.png")
        ));
    }
    @FXML
    public void deleteCourseBtnHover(MouseEvent event){
        deleteCourseBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 20;" +
                "    -fx-background-color: red;" +
                "    -fx-text-fill: #fff;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;");
        deleteCourseBtnImg.setImage(new Image(
                getClass().getResourceAsStream("/icons/light/close-circle-filll.png")
        ));
    }
    @FXML
    public void deleteCourseBtnNormal(MouseEvent event){
        deleteCourseBtn.setStyle("-fx-font-family: \"Jost Medium\";" +
                "    -fx-font-size: 20;" +
                "    -fx-background-color: #fff;" +
                "    -fx-text-fill: red;" +
                "    -fx-background-radius: 8px;" +
                "    -fx-border-radius: 8px;" +
                "    -fx-border-color: red;" +
                "    -fx-border-width: 2px;");
        deleteCourseBtnImg.setImage(new Image(
                getClass().getResourceAsStream("/icons/dark/close-circle-filll.png")
        ));
    }
   @FXML
    public void deleteCourseBtnClicked(MouseEvent event){
       warningDeleteCourseContainer.setVisible(true);
    }
    @FXML
    public void closeWarningDeleteCourse(MouseEvent event){
        warningDeleteCourseContainer.setVisible(false);
    }
    @FXML
    public void confirmDeleteCourse(MouseEvent event){
       String password = passwordConfirmDelete.getText();
        UserService userService = new UserService();
        if(userService.isPasswordMatch(1,password)){
            CourseService courseService = new CourseService();
            if (courseService.delete(Integer.valueOf(idLabelDetail.getText().replace("#","")))){
                passwordConfirmDelete.clear();
                warningDeleteCourseContainer.setVisible(false);
                coursesDetailsContainer.setVisible(false);
                successOperationContainer.setVisible(true);
                showCoursesList();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> {
                    successOperationContainer.setVisible(false);
                }));
                timeline.setCycleCount(1);
                timeline.play();
            }
        }
    }
    @FXML
    public void editCourseBtnClicked(MouseEvent event){
        coursesEditContainer.setVisible(true);
        editTitleInputAddCourse.setText(titleLabelDetail.getText());
        editCourseDesc.setText(descriptionLabelDetail.getText());
        editCoursePrice.setText(priceLabelDetail.getText());
        editCourseDuration.setText(durationLabelDetail.getText());
        courseImgEditPreview.setImage(detailImg);
    }
    @FXML
    public void addSectionCourseBtnClicked(MouseEvent event){

    }
    @FXML
    public void goBackCoursesDetails(MouseEvent event){
        coursesEditContainer.setVisible(false);
    }
    @FXML
    public void chooseImageEditCourse(MouseEvent event){
        FileChooser imageCourseChooser = new FileChooser();
        imageCourseChooser.setTitle("Choose your course image");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files", "*.png", "*.jpg", "*.gif");
        imageCourseChooser.getExtensionFilters().add(imageFilter);
        File selectedFile = imageCourseChooser.showOpenDialog(null);
        if (selectedFile != null) {
            addCourseImgPath = selectedFile.getAbsolutePath();
            courseImgEditPreview.setImage(new Image("file:///" +addCourseImgPath.replace("\\","/")));
        }
    }
    @FXML
    public void editCourse(MouseEvent event){
        int id = Integer.valueOf(idLabelDetail.getText().replace("#",""));
        String title = editTitleInputAddCourse.getText();
        String description = editCourseDesc.getText();
        float price = Float.parseFloat(editCoursePrice.getText());
        String duration = editCourseDuration.getText();
        CourseService courseService = new CourseService();
        Course course = new Course(id,addCourseImgPath,title,description,1,duration,price,0,"",0);
        if(courseService.update(course)){
            showCoursesList();
            coursesEditContainer.setVisible(false);
            addCoursePane.setVisible(false);
            showCourseDetails(course);
            doneEditCourse.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> {
                doneEditCourse.setVisible(false);
            }));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else
            System.out.println("FAILED");
    }
}
