package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import tn.SIRIUS.entities.*;
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
import javafx.scene.media.MediaView;
import tn.SIRIUS.services.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private AnchorPane failedOperationEditContainer;
    @FXML
    private AnchorPane failedOperationContainer;
    @FXML
    private Button showCourseSectionsBtn;
    @FXML
    private MediaView mediaAddSectionViewer;
    @FXML
    private AnchorPane coursesAddSectionContainer;
    @FXML
    private TextField titleInputAddSection;
    @FXML
    private TextArea sectionDescInput;
    @FXML
    private Label errorAttAddSection;
    @FXML
    private Label errorDescAddSection;
    @FXML
    private Label errorTitleAddSection;
    @FXML
    private AnchorPane coursesViewSectionContainer;
    @FXML
    private VBox sectionsViewListContainer;
    @FXML
    private MediaView mediaViewerSectionsCourse;
    @FXML
    private VBox sectionsViewDetailsContainer;
    @FXML
    private Label courseTitleSectionDisplay;
    @FXML
    private Slider volumeBarSectionVid;
    @FXML
    private Slider slideBarTimeVidSectioin;
    @FXML
    private Button playSectionVidBtn;
    @FXML
    private ImageView playVidSecBtnImg;
    @FXML
    private Label speedVidSec;
    @FXML
    private ImageView muteVolumeSectVid;
    @FXML
    private HBox volumeSecPartContainer;
    @FXML
    private ImageView fullScreenSecVid;
    @FXML
    private Text timeVideoSectioPlay;
    @FXML
    private Text totalTimeVidSection;
    @FXML
    private MediaView mediaEditSectionViewer;
    @FXML
    private TextField titleInputEditSection;
    @FXML
    private TextArea sectionEditDescInput;
    @FXML
    private AnchorPane coursesEditSectionContainer;
    @FXML
    private Label errorTitleEditSection;
    @FXML
    private Label errorDescEditSection;
    @FXML
    private Label errorAttEditSection;
    @FXML
    private TextField idInputEditSection;
    @FXML
    private AnchorPane quizAddFormPageContainer;
    @FXML
    private Label idSectionAddQuiz;
    @FXML
    private Label nbrQuestionQuizAdd;
    @FXML
    private TextField questionQuizAddInput;
    @FXML
    private TextField choice1QuizAddInput;
    @FXML
    private TextField choice2QuizAddInput;
    @FXML
    private TextField choice3QuizAddInput;
    @FXML
    private TextField choice4QuizAddInput;
    @FXML
    private ComboBox<String> comboBoxQuizAdd;
    @FXML
    private VBox questionQuizFormContainer;
    @FXML
    private Label errorQuestionAdd;
    @FXML
    private Label choice1QuizError;
    @FXML
    private Label choice2QuizError;
    @FXML
    private Label choice3QuizError;
    @FXML
    private Label choice4QuizError;
    @FXML
    private AnchorPane failedOperationQuizAddContainer;
    @FXML
    private AnchorPane quizEditFormPageContainer;
    @FXML
    private VBox questionQuizEditFormContainer;
    @FXML
    private ComboBox<String> comboBoxQuizEdit;
    @FXML
    private TextField questionQuizEditInput;
    @FXML
    private TextField choice1QuizEditInput;
    @FXML
    private TextField choice2QuizEditInput;
    @FXML
    private TextField choice3QuizEditInput;
    @FXML
    private TextField choice4QuizEditInput;
    @FXML
    private Label idQuestionEdit;
    @FXML
    private Label errorQuestionEdit;
    @FXML
    private Label choice1QuizErrorEdit;
    @FXML
    private Label choice2QuizErrorEdit;
    @FXML
    private Button editQuestionQuizFormBtn;
    @FXML
    private Label choice4QuizErrorEdit;
    @FXML
    private Label choice3QuizErrorEdit;
    @FXML
    private AnchorPane failedOperationQuizEditContainer;
    @FXML
    private Text msgConfirmDelete;
    @FXML
    private Label nbrQuestionQuizEdit;
    private Quiz quizToEdit;
    private List<Section> sections;
    private String addCourseImgPath;
    private Image detailImg;
    List<Course> courses;
    private MediaPlayer mediaPlayer;
    private File fileVidSection;
    private Media mediaSecVid;
    private String vidSectionPath;
    private int playVidCounter;
    private int muteVidSecCounter;
    private double currentVolume;
    private List<QuizQuestion> quizQuestions;
    private Section sectionQuiz;
    private int idQuizDelete;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Init visibilities and variables
        addCoursePane.setVisible(false);
        coursesDetailsContainer.setVisible(false);
        warningDeleteCourseContainer.setVisible(false);
        successOperationContainer.setVisible(false);
        coursesEditContainer.setVisible(false);
        failedOperationContainer.setVisible(false);
        failedOperationEditContainer.setVisible(false);
        coursesAddSectionContainer.setVisible(false);
        coursesViewSectionContainer.setVisible(false);
        coursesEditSectionContainer.setVisible(false);
        quizAddFormPageContainer.setVisible(false);
        quizEditFormPageContainer.setVisible(false);
        volumeBarSectionVid.setValue(100);
        slideBarTimeVidSectioin.setValue(0);
        vidSectionPath = "";
        addCourseImgPath = "";
        //timeVideoSectioPlay.setText("0");
        // End
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
        // video for section part
        volumeBarSectionVid.valueProperty().addListener(observable -> {
            if(volumeBarSectionVid.isPressed())
                mediaPlayer.setVolume(volumeBarSectionVid.getValue()/100);
        });
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
        Course course = new Course(0,addCourseImgPath,title,description,new User(1,"","","",""),duration,price,0,"",0,0,0);
        if(courseService.add(course)){
            showCoursesList();
            addCoursePane.setVisible(false);
            successOperationContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> successOperationContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else {
            addCoursePane.setVisible(false);
            failedOperationContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
    public void showCourseDetails(Course course) {
        addCourseImgPath = course.getImage();
        detailImg = new Image("file:///" +addCourseImgPath.replace("\\","/"));
        coursesDetailsContainer.setVisible(true);
        courseDetailsImgContainer.setFill(new ImagePattern(detailImg));
        titleLabelDetail.setText(course.getTitle());
        idLabelDetail.setText("#" + course.getId());
        tutorLabelDetail.setText(String.valueOf(course.getTutor()));
        priceLabelDetail.setText(String.valueOf(course.getPrice()));
        nbrSecLabelDetail.setText(String.valueOf(course.getNbrSection()));
        nbrRegistredLabelDetail.setText(String.valueOf(course.getNbrRegistred()));
        descriptionLabelDetail.setText(course.getDescription());
        durationLabelDetail.setText(course.getDuration());
        postedDateLabelDetail.setText(course.getPostedDate());
        showCourseSectionsBtn.setVisible(course.getNbrSection() >= 1);
        // for sections part
        courseTitleSectionDisplay.setText(course.getTitle());

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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/add-circle-lineee.png"))
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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/add-circle-linee.png"))
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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/edit-box-line.png"))
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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/edit-box-line.png"))
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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/close-circle-filll.png"))
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
                Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/close-circle-filll.png"))
        ));
    }
    public void warningDeleteSetUp(String deleteCase){
        switch (deleteCase){
            case "Delete Course" -> msgConfirmDelete.setText("Are you sure you want to delete this course ?");
            case "Delete Quiz" -> msgConfirmDelete.setText("Are you sure you want to delete this quiz ?");
            case "Delete Section" -> msgConfirmDelete.setText("Are you sure you want to delete this section ?");
            default -> System.out.println("Wrong case !");
        }
        warningDeleteCourseContainer.setVisible(true);
    }
   @FXML
    public void deleteCourseBtnClicked(MouseEvent event){
       warningDeleteSetUp("Delete Course");
    }
    @FXML
    public void closeWarningDelete(MouseEvent event){
        passwordConfirmDelete.clear();
        warningDeleteCourseContainer.setVisible(false);
    }
    @FXML
    public void confirmDelete(MouseEvent event){
       String password = passwordConfirmDelete.getText();
        UserService userService = new UserService();
        if(userService.isPasswordMatch(1,password)){
            switch (msgConfirmDelete.getText()){
                case "Are you sure you want to delete this course ?" -> {
                    CourseService courseService = new CourseService();
                    if (courseService.delete(Integer.parseInt(idLabelDetail.getText().replace("#","")))){
                        closeWarningDelete(null);
                        coursesDetailsContainer.setVisible(false);
                        successOperationContainer.setVisible(true);
                        showCoursesList();
                        notifySuccess();
                    } else {
                        closeWarningDelete(null);
                        notifyFailed();
                    }
                }
                case "Are you sure you want to delete this quiz ?" -> {
                    QuizService quizService = new QuizService();
                    if(quizService.delete(idQuizDelete)){
                        closeWarningDelete(null);
                        setSectionPlay(sectionQuiz);
                        notifySuccess();
                    } else {
                        closeWarningDelete(null);
                        notifyFailed();
                    }
                }
                case "Are you sure you want to delete this section ?" -> {

                }
                default -> System.out.println("Wrong Case !");
            }
        } else {
            passwordConfirmDelete.clear();
            warningDeleteCourseContainer.setVisible(false);
            notifyFailedPassword();
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
            coursesAddSectionContainer.setVisible(true);
    }
    @FXML
    public void goBackCoursesDetails(MouseEvent event){
        // set visibilty and clear input for edit course
        coursesEditContainer.setVisible(false);
        // set visibilty and clear input for section
        coursesAddSectionContainer.setVisible(false);
        errorTitleAddSection.setVisible(false);
        errorDescAddSection.setVisible(false);
        errorAttAddSection.setVisible(false);
        titleInputAddSection.clear();
        sectionDescInput.clear();
        mediaPlayer.dispose();
        mediaAddSectionViewer.setMediaPlayer(null);
        coursesViewSectionContainer.setVisible(false);
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
        int id = Integer.parseInt(idLabelDetail.getText().replace("#",""));
        String title = editTitleInputAddCourse.getText();
        String description = editCourseDesc.getText();
        float price = Float.parseFloat(editCoursePrice.getText());
        String duration = editCourseDuration.getText();
        CourseService courseService = new CourseService();
        Course course = new Course(id,addCourseImgPath,title,description,new User(1,"","","","S"),duration,price,0,"",0,0.0f,0);
        if(courseService.update(course)){
            showCoursesList();
            coursesEditContainer.setVisible(false);
            showCourseDetails(course);
            successOperationContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> successOperationContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
        else {
            coursesEditContainer.setVisible(false);
            addCoursePane.setVisible(false);
            showCourseDetails(course);
            failedOperationEditContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationEditContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
    // Section part
    @FXML
    public void playAddSectionVid(MouseEvent event){
        mediaPlayer.play();
    }
    @FXML
    public void pauseAddSectionVid(MouseEvent event){ mediaPlayer.pause(); }
    @FXML
    public void chooseSectionAttBtnClicked(MouseEvent event){
        FileChooser videoChooser = new FileChooser();
        videoChooser.setTitle("Choose your course Video");
        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter(
                "Videos Files", "*.mp4", "*.avi", "*.mov", "*.mkv", "*.wmv", "*.flv");
        videoChooser.getExtensionFilters().add(videoFilter);
        fileVidSection = videoChooser.showOpenDialog(null);
        if(fileVidSection != null){
            vidSectionPath = fileVidSection.toURI().toString();
            mediaSecVid = new Media(vidSectionPath);
            mediaPlayer = new MediaPlayer(mediaSecVid);
            mediaAddSectionViewer.setMediaPlayer(mediaPlayer);
        }
    }
    @FXML
    public void addSectionFormBtnClicked(){
        if( !titleInputAddSection.getText().isEmpty() &&
            ( !sectionDescInput.getText().isEmpty() && sectionDescInput.getText().length() >= 20 ) &&
            !vidSectionPath.isEmpty() ){
            int courseID = Integer.parseInt(idLabelDetail.getText().replace("#",""));
            Section section = new Section(
                    0,courseID,
                    titleInputAddSection.getText(),
                    sectionDescInput.getText(),
                    vidSectionPath, ""
            );
            SectionService sectionService = new SectionService();
            if(sectionService.add(section)){
                CourseService courseService = new CourseService();
                int nbrSection = Integer.parseInt(nbrSecLabelDetail.getText()) + 1;
                if(courseService.updateNbrSection(courseID,nbrSection)){
                    showCoursesList();
                    showCourseDetails(courseService.getOne(courseID));
                    goBackCoursesDetails(null);
                    notifySuccess();
                } else {
                    coursesAddSectionContainer.setVisible(false);
                    notifyFailed();
                }
            } else {
                coursesAddSectionContainer.setVisible(false);
                notifyFailed();
            }
        } else {
            if( titleInputAddSection.getText().isEmpty() )
                errorTitleAddSection.setVisible(true);
            if( sectionDescInput.getText().isEmpty() || sectionDescInput.getText().length() < 20 )
                errorDescAddSection.setVisible(true);
            if( vidSectionPath.isEmpty() )
                errorAttAddSection.setVisible(true);
        }
    }
    public void notifySuccess(){
        successOperationContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> successOperationContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void notifyFailed(){
        failedOperationContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void notifyFailedPassword(){
        failedOperationEditContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationEditContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void showCourseSection(int idCourse){
        int count = 0;
        coursesViewSectionContainer.setVisible(true);
        // get and display all sections
        sectionsViewListContainer.getChildren().clear();
        SectionService sectionService = new SectionService();
        sections = sectionService.getAll(idCourse);
        try {
            for(Section section : sections) {
                if(count < 1){
                    setSectionPlay(section);
                    count++;
                }
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/sectionsDetailsTitleItem.fxml"));
                Parent root = fxmlLoader.load();
                SectionsDetailsTitleItemController itemController = fxmlLoader.getController();
                itemController.setCoursesMainPageController(this);
                itemController.setData(section);
                sectionsViewListContainer.getChildren().add(root);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void showCourseSectionsBtnClicked(MouseEvent event){
        showCourseSection(Integer.parseInt(idLabelDetail.getText().replace("#","")));
    }
    public void setSectionPlay(Section section){
        if(mediaPlayer != null) mediaPlayer.dispose();
        mediaSecVid = new Media(section.getAttachment());
        mediaPlayer = new MediaPlayer(mediaSecVid);
        mediaViewerSectionsCourse.setMediaPlayer(mediaPlayer);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/SectionDetailsItemDescription.fxml"));
            Parent root = fxmlLoader.load();
            SectionDetailsItemDescriptionController itemController = fxmlLoader.getController();
            itemController.setData(section,false,this);
            sectionsViewDetailsContainer.getChildren().clear();
            sectionsViewDetailsContainer.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initPlayVidCounter();
        updateTimeLabelVideoSection();
    }
    @FXML
    public void playSectionVid(MouseEvent event){
        if(playVidCounter == 0 || ( playVidCounter%2 == 0 )){
            mediaPlayer.play();
            mediaPlayer.currentTimeProperty().addListener(observable -> updateProgressBarSecVideo());
            slideBarTimeVidSectioin.valueProperty().addListener(observable -> {
                if(slideBarTimeVidSectioin.isPressed())
                    mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(slideBarTimeVidSectioin.getValue()/100));
            });
            playVidSecBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/pause-line.png")));
        } else{
            playVidSecBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/play-fill.png")));
            mediaPlayer.pause();
        }
        playVidCounter++;
    }
    public void updateProgressBarSecVideo(){
        Platform.runLater(() -> slideBarTimeVidSectioin.setValue(mediaPlayer.getCurrentTime().toMillis()/mediaPlayer.getTotalDuration().toMillis()*100) );
    }
    public void initPlayVidCounter(){
        playVidCounter = 0;
        muteVidSecCounter = 0;
        playVidSecBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/play-fill.png")));
        muteVolumeSectVid.setImage(new Image(getClass().getResourceAsStream("/icons/dark/volume-up-line.png")));
        volumeBarSectionVid.setValue(100);
        slideBarTimeVidSectioin.setValue(0);
        speedVidSec.setText("x1");
        mediaPlayer.setRate(1.0);
        mediaPlayer.totalDurationProperty().addListener((obs,oldDuration,newDuration) -> {
            totalTimeVidSection.setText(getVidTime(newDuration));
        });

    }
    @FXML
    public void updateSecVidSpeed(){
        if (speedVidSec.getText().equals("x1")){
            speedVidSec.setText("x1.5");
            mediaPlayer.setRate(1.5);
        } else {
            speedVidSec.setText("x1");
            mediaPlayer.setRate(1.0);
        }
    }
    @FXML
    public void muteUnmuteVidSecPart(MouseEvent event){
        if((muteVidSecCounter%2 == 0)){
            muteVolumeSectVid.setImage(new Image(getClass().getResourceAsStream("/icons/dark/volume-mute-line.png")));
            currentVolume = (volumeBarSectionVid.getValue()/100);
            mediaPlayer.setVolume(0.0);
            volumeBarSectionVid.setValue(0.0);
        } else{
            muteVolumeSectVid.setImage(new Image(getClass().getResourceAsStream("/icons/dark/volume-up-line.png")));
            mediaPlayer.setVolume(currentVolume);
            volumeBarSectionVid.setValue(currentVolume*100);
        }
        muteVidSecCounter++;
    }
    @FXML
    public void showVolumeSlideBar(MouseEvent event){
        volumeBarSectionVid.setVisible(true);
    }
    @FXML
    public void hideVolumeSlideBar(MouseEvent event){
        volumeBarSectionVid.setVisible(false);
    }
    // update time video section details part
    public void restartEndVideo(){
        playVidCounter = 0;
        playVidSecBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/play-fill.png")));
        mediaPlayer.seek(new Duration(0));
        mediaPlayer.pause();
        slideBarTimeVidSectioin.setValue(0);
    }
    public void updateTimeLabelVideoSection(){
        timeVideoSectioPlay.textProperty().bind(Bindings.createStringBinding(() -> { /* Callable<String> ( call() )*/
            try{
                return getVidTime(mediaPlayer.getCurrentTime()) + " / ";
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "";
            }
        },mediaPlayer.currentTimeProperty()));
    }
    public String getVidTime(Duration time){
        int secondes = (int) time.toSeconds();
        int minutes = (int) time.toMinutes();
        int hours = (int) time.toHours();
        if(secondes > 59 ) secondes = secondes % 60;
        if(minutes > 59 ) minutes = minutes % 60;
        if(hours > 59 ) hours = hours % 60;
        if(hours > 0) {
            String duration =  String.format("%d:%02d:%02d",hours,minutes,secondes);
            if(totalTimeVidSection.getText().equals(duration))
                restartEndVideo();
            return duration;
        }
        else {
            String duration =  String.format("%02d:%02d",minutes,secondes);
            if(totalTimeVidSection.getText().equals(duration))
                restartEndVideo();
            return duration;
        }
    }
    // Edit Section
    public void setUpEditSectionPage(Section section){
        titleInputEditSection.setText(section.getTitle());
        sectionEditDescInput.setText(section.getDescription());
        titleInputAddSection.setText(String.valueOf(section.getIdSection()));
        vidSectionPath = section.getAttachment();
        mediaSecVid = new Media(vidSectionPath);
        mediaPlayer = new MediaPlayer(mediaSecVid);
        mediaEditSectionViewer.setMediaPlayer(mediaPlayer);
        idInputEditSection.setText(String.valueOf(section.getIdSection()));
        coursesEditSectionContainer.setVisible(true);
    }
    @FXML
    public void chooseSectionEditAttBtnClicked(MouseEvent event){
        FileChooser videoChooser = new FileChooser();
        videoChooser.setTitle("Choose your course Video");
        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter(
                "Videos Files", "*.mp4", "*.avi", "*.mov", "*.mkv", "*.wmv", "*.flv");
        videoChooser.getExtensionFilters().add(videoFilter);
        fileVidSection = videoChooser.showOpenDialog(null);
        if(fileVidSection != null){
            vidSectionPath = fileVidSection.toURI().toString();
            mediaSecVid = new Media(vidSectionPath);
            mediaPlayer = new MediaPlayer(mediaSecVid);
            mediaEditSectionViewer.setMediaPlayer(mediaPlayer);
        }
    }
    @FXML
    public void playEditSectionVid(MouseEvent event){
        mediaPlayer.play();
    }
    @FXML
    public void pauseEditSectionVid(MouseEvent event){
        mediaPlayer.pause();
    }
    @FXML
    public void goBackSectionDetailsBtnClicked(MouseEvent event){
        coursesEditSectionContainer.setVisible(false);
    }
    @FXML
    public void sectionEditFormBtnClicked(MouseEvent event){
        if( !titleInputEditSection.getText().isEmpty() &&
                ( !sectionEditDescInput.getText().isEmpty() && sectionEditDescInput.getText().length() >= 20 ) &&
                !vidSectionPath.isEmpty() ){
            int courseID = Integer.parseInt(idLabelDetail.getText().replace("#",""));
            int idSection = Integer.parseInt(idInputEditSection.getText());
            Section section = new Section(
                    idSection,courseID,
                    titleInputEditSection.getText(),
                    sectionEditDescInput.getText(),
                    vidSectionPath, ""
            );
            SectionService sectionService = new SectionService();
            if(sectionService.update(section)){
                    showCourseSection(courseID);
                    setSectionPlay(section);
                    coursesEditSectionContainer.setVisible(false);
                    notifySuccess();
            } else {
                    coursesEditSectionContainer.setVisible(false);
                    notifyFailed();
            }
        } else {
            if( titleInputEditSection.getText().isEmpty() )
                errorAttEditSection.setVisible(true);
            if( sectionEditDescInput.getText().isEmpty() || sectionEditDescInput.getText().length() < 20 )
                errorDescEditSection.setVisible(true);
            if( vidSectionPath.isEmpty() )
                errorAttEditSection.setVisible(true);
        }
    }
    public void deleteSection(Section section){
        SectionService sectionService = new SectionService();
        if(sectionService.delete(section.getIdSection())){
            CourseService courseService = new CourseService();
            int courseID = Integer.parseInt(idLabelDetail.getText().replace("#",""));
            int newNbrSection = Integer.parseInt(nbrSecLabelDetail.getText()) - 1;
            courseService.updateNbrSection(courseID,newNbrSection );
            showCourseSection(courseID);
            notifySuccess();
        } else {
            notifyFailed();
        }
    }
    // Quiz Start
    public void setAddQuizPage(Section section){
        idSectionAddQuiz.setText(String.valueOf(section.getIdSection()));
        quizAddFormPageContainer.setVisible(true);
        nbrQuestionQuizAdd.setText("Question Number : 0");
        ObservableList<String> items = FXCollections.observableArrayList(
                "Choice 1",
                "Choice 2",
                "Choice 3",
                "Choice 4"
        );
        comboBoxQuizAdd.setItems(items);
        comboBoxQuizAdd.setValue("Choice 1");
        quizQuestions = new ArrayList<>();
        sectionQuiz = section;
    }
    @FXML
    public void goBackSectionFromQuizAdd(MouseEvent event){
        questionQuizEditFormContainer.getChildren().clear();
        quizAddFormPageContainer.setVisible(false);
        quizEditFormPageContainer.setVisible(false);
        setSectionPlay(sectionQuiz);
        clearEditQuizInputs();
        clearAddQuizInputs();
    }
    @FXML
    public void addQuestionToQuiz(MouseEvent event) throws IOException {
        String question = questionQuizAddInput.getText();
        String choice1 = choice1QuizAddInput.getText();
        String choice2 = choice2QuizAddInput.getText();
        String choice3 = choice3QuizAddInput.getText();
        String choice4 = choice4QuizAddInput.getText();
        String correctChoice = comboBoxQuizAdd.getValue();
        if(!choice1.isEmpty() && !choice2.isEmpty() && !choice3.isEmpty()
                && !choice4.isEmpty() && !question.isEmpty()){
            QuizQuestion qq = new QuizQuestion(
                    0,0,question,choice1,choice2,choice3,choice4,correctChoice
            );
            quizQuestions.add(qq);
            clearAddQuizInputs();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/QuizItemTutor.fxml"));
            Parent root = fxmlLoader.load();
            QuizItemTutorController controller = fxmlLoader.getController();
            controller.setData(qq,false,this);
            questionQuizFormContainer.getChildren().add(root);
            nbrQuestionQuizAdd.setText("Question Number : "+quizQuestions.size());
            notifySuccess();
        } else {
            if(question.isEmpty())
                errorQuestionAdd.setVisible(true);
            if(choice1.isEmpty())
                choice1QuizError.setVisible(true);
            if(choice2.isEmpty())
                choice2QuizError.setVisible(true);
            if(choice3.isEmpty())
                choice3QuizError.setVisible(true);
            if(choice4.isEmpty())
                choice4QuizError.setVisible(true);
        }
    }
    @FXML
    public void addQuiz(MouseEvent event){
        if(quizQuestions.size() < 3){
            failedOperationQuizAddContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationQuizAddContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        } else {
            QuizService quizService = new QuizService();
            int idSection = Integer.parseInt(idSectionAddQuiz.getText());
            if(quizService.add(new Quiz(0,idSection))){
                Quiz quiz = quizService.getQuizBySection(idSection);
                if(quiz.getIdQuiz() != 0){
                    int count = 0;
                    QuizQuestionService qqService = new QuizQuestionService();
                    for(QuizQuestion qq : quizQuestions){
                        qq.setQuiz(quiz.getIdQuiz());
                        if(qqService.add(qq))
                            count++;
                    }
                    if (count == quizQuestions.size()){
                        setSectionPlay(sectionQuiz);
                        clearAddQuizInputs();
                        quizAddFormPageContainer.setVisible(false);
                        notifySuccess();
                    } else
                        notifyFailed();
                }
            }
            else
                notifyFailed();
        }
    }
    public void deleteQuiz(Section section, int id){
        idQuizDelete = id;
        sectionQuiz = section;
        warningDeleteSetUp("Delete Quiz");
    }
    public void setEditQuizPage(Quiz quiz,Section section){
        quizToEdit = quiz;
        sectionQuiz = section;
        quizEditFormPageContainer.setVisible(true);
        for(QuizQuestion qq : quiz.getQuestions()){
            FXMLLoader fxmlLoader = new FXMLLoader();
            try {
                fxmlLoader.setLocation((getClass().getResource("/gui/tutors/QuizItemTutor.fxml")));
                Parent root = fxmlLoader.load();
                QuizItemTutorController controller = fxmlLoader.getController();
                controller.setData(qq,true,this);
                questionQuizEditFormContainer.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ObservableList<String> items = FXCollections.observableArrayList(
                "Choice 1",
                "Choice 2",
                "Choice 3",
                "Choice 4"
        );
        comboBoxQuizEdit.setItems(items);
        comboBoxQuizEdit.setValue("Choice 1");
        nbrQuestionQuizEdit.setText("Question Number : "+quizToEdit.getQuestions().size());
    }
    public void clearAddQuizInputs(){
        questionQuizAddInput.clear();
        choice1QuizAddInput.clear();
        choice2QuizAddInput.clear();
        choice3QuizAddInput.clear();
        choice4QuizAddInput.clear();
        errorQuestionAdd.setVisible(false);
        choice1QuizError.setVisible(false);
        choice2QuizError.setVisible(false);
        choice3QuizError.setVisible(false);
        choice4QuizError.setVisible(false);
    }
    public void clearEditQuizInputs(){
        questionQuizEditInput.clear();
        choice1QuizEditInput.clear();
        choice2QuizEditInput.clear();
        choice3QuizEditInput.clear();
        choice4QuizEditInput.clear();
        errorQuestionEdit.setVisible(false);
        choice1QuizErrorEdit.setVisible(false);
        choice2QuizErrorEdit.setVisible(false);
        choice3QuizErrorEdit.setVisible(false);
        choice4QuizErrorEdit.setVisible(false);
        editQuestionQuizFormBtn.setText("Add Question");
    }
    public void setQuestionEditForm(QuizQuestion qq){
        questionQuizEditInput.setText(qq.getQuestion());
        choice1QuizEditInput.setText(qq.getChoice_1());
        choice2QuizEditInput.setText(qq.getChoice_2());
        choice3QuizEditInput.setText(qq.getChoice_3());
        choice4QuizEditInput.setText(qq.getChoice_4());
        comboBoxQuizEdit.setValue(qq.getCorrect_choice());
        idQuestionEdit.setText(String.valueOf(qq.getIdQuestion()));
        editQuestionQuizFormBtn.setText("Edit Question");
    }
    public void loadQuestionsEditPage(QuizQuestion qq) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/tutors/QuizItemTutor.fxml"));
        Parent root = fxmlLoader.load();
        QuizItemTutorController controller = fxmlLoader.getController();
        controller.setData(qq,true,this);
        questionQuizEditFormContainer.getChildren().add(root);
    }
    @FXML
    public void editQuestionQuizFormBtnClicked(MouseEvent event) throws IOException {
        String question = questionQuizEditInput.getText();
        String choice1 = choice1QuizEditInput.getText();
        String choice2 = choice2QuizEditInput.getText();
        String choice3 = choice3QuizEditInput.getText();
        String choice4 = choice4QuizEditInput.getText();
        String correctChoice = comboBoxQuizEdit.getValue();
        if(!choice1.isEmpty() && !choice2.isEmpty() && !choice3.isEmpty()
                && !choice4.isEmpty() && !question.isEmpty()){
            if(editQuestionQuizFormBtn.getText().equals("Edit Question")){
                int idQuestion = Integer.parseInt(idQuestionEdit.getText());
                QuizQuestion qq = new QuizQuestion(idQuestion,quizToEdit.getIdQuiz(),question,
                        choice1,choice2,choice3,choice4,correctChoice);
                QuizQuestionService qqService = new QuizQuestionService();
                if(qqService.update(qq)){
                    questionQuizEditFormContainer.getChildren().clear();
                    for(QuizQuestion q : quizToEdit.getQuestions()){
                        if(q.getIdQuestion() == qq.getIdQuestion())
                            q = qq;
                        try {
                            loadQuestionsEditPage(q);
                        }catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    notifySuccess();
                }
                editQuestionQuizFormBtn.setText("Add Question");
            } else{
                QuizQuestionService qqService = new QuizQuestionService();
                int idAvailable = qqService.getNextIdAvailable();
                if(idAvailable != 0){
                    QuizQuestion qq = new QuizQuestion(idAvailable,quizToEdit.getIdQuiz(),question,
                            choice1,choice2,choice3,choice4,correctChoice);
                    if(qqService.add(qq)){
                        questionQuizEditFormContainer.getChildren().clear();
                        quizToEdit.getQuestions().add(qq);
                        for(QuizQuestion q : quizToEdit.getQuestions()){
                            try {
                                loadQuestionsEditPage(q);
                            }catch(IOException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        nbrQuestionQuizEdit.setText("Question Number : "+quizToEdit.getQuestions().size());
                        notifySuccess();
                    }
                }
            }
            clearEditQuizInputs();
        } else {
            if(question.isEmpty())
                errorQuestionEdit.setVisible(true);
            if(choice1.isEmpty())
                choice1QuizErrorEdit.setVisible(true);
            if(choice2.isEmpty())
                choice2QuizErrorEdit.setVisible(true);
            if(choice3.isEmpty())
                choice3QuizErrorEdit.setVisible(true);
            if(choice4.isEmpty())
                choice4QuizErrorEdit.setVisible(true);
        }
    }
    public void deleteQuestionQuiz(QuizQuestion qq){
        QuizQuestionService qqService = new QuizQuestionService();
        if(quizToEdit.getQuestions().size() > 3){
            if(qqService.delete(qq.getIdQuestion())){
                if(quizToEdit.getQuestions().remove(qq)){
                    questionQuizEditFormContainer.getChildren().clear();
                    for(QuizQuestion q : quizToEdit.getQuestions()){
                        try {
                            loadQuestionsEditPage(q);
                        }catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    nbrQuestionQuizEdit.setText("Question Number : "+quizToEdit.getQuestions().size());
                    notifySuccess();
                } else
                    notifyFailed();
            } else
                notifyFailed();
        } else {
            failedOperationQuizEditContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500), e -> failedOperationQuizEditContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }
    // resources clean
    public void cleanup(){
        mediaPlayer.dispose();
        if (sections != null)
            sections.clear();
        if (courses != null)
            courses.clear();
    }
}
