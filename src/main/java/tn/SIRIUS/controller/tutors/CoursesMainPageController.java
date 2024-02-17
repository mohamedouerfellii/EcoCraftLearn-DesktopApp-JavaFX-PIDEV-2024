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
import javafx.scene.media.MediaView;
import tn.SIRIUS.entities.Section;
import tn.SIRIUS.services.CourseService;
import tn.SIRIUS.services.SectionService;
import tn.SIRIUS.services.UserService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
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
        Course course = new Course(0,addCourseImgPath,title,description,1,duration,price,0,"",0);
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
            if (courseService.delete(Integer.parseInt(idLabelDetail.getText().replace("#","")))){
                passwordConfirmDelete.clear();
                warningDeleteCourseContainer.setVisible(false);
                coursesDetailsContainer.setVisible(false);
                successOperationContainer.setVisible(true);
                showCoursesList();
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> successOperationContainer.setVisible(false)));
                timeline.setCycleCount(1);
                timeline.play();
            }
        } else {
            passwordConfirmDelete.clear();
            warningDeleteCourseContainer.setVisible(false);
            failedOperationEditContainer.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationEditContainer.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();
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
        mediaPlayer.pause();
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
        Course course = new Course(id,addCourseImgPath,title,description,1,duration,price,0,"",0);
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
    public void showCourseSection(){
        int count = 0;
        coursesViewSectionContainer.setVisible(true);
        // get and display all sections
        sectionsViewListContainer.getChildren().clear();
        SectionService sectionService = new SectionService();
        sections = sectionService.getAll();
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
        showCourseSection();
    }
    public void setSectionPlay(Section section){
        if(mediaPlayer != null) mediaPlayer.pause();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/SectionDetailsItemDescription.fxml"));
            Parent root = fxmlLoader.load();
            SectionDetailsItemDescriptionController itemController = fxmlLoader.getController();
            itemController.setCoursesMainPageController(this);
            itemController.setData(section);
            mediaSecVid = new Media(section.getAttachment());
            mediaPlayer = new MediaPlayer(mediaSecVid);
            mediaViewerSectionsCourse.setMediaPlayer(mediaPlayer);
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
            playVidCounter++;
        } else{
            playVidSecBtnImg.setImage(new Image(getClass().getResourceAsStream("/icons/light/play-fill.png")));
            mediaPlayer.pause();
            playVidCounter++;
        }
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
    public void sectionEditFormBtnClicked(MouseEvent event){
        if( !titleInputEditSection.getText().isEmpty() &&
                ( !sectionEditDescInput.getText().isEmpty() && sectionEditDescInput.getText().length() >= 20 ) &&
                !vidSectionPath.isEmpty() ){
            int courseID = Integer.parseInt(idLabelDetail.getText().replace("#",""));
            int idSection = Integer.parseInt(idInputEditSection.getText());
            System.out.println(idSection);
            Section section = new Section(
                    idSection,courseID,
                    titleInputEditSection.getText(),
                    sectionEditDescInput.getText(),
                    vidSectionPath, ""
            );
            SectionService sectionService = new SectionService();
            if(sectionService.update(section)){
                    showCourseSection();
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
            showCourseSection();
            notifySuccess();
        } else {
            notifyFailed();
        }
    }
    // Quiz Start
    public void setAddQuizPage(int idSection){
        idSectionAddQuiz.setText(String.valueOf(idSection));
        quizAddFormPageContainer.setVisible(true);
        nbrQuestionQuizAdd.setText("Question Number : 0");
        ObservableList<String> items = FXCollections.observableArrayList(
                "Choice 1",
                "Choice 2",
                "Choice 3",
                "Choice 4"
        );
        comboBoxQuizAdd.setItems(items);
    }
    @FXML
    public void goBackSectionFromQuizAdd(MouseEvent event){
        quizAddFormPageContainer.setVisible(false);
    }
    // resources clean
    public void cleanup(){
        mediaSecVid = null;
        if (sections != null)
            sections.clear();
        if (courses != null)
            courses.clear();
    }
}
