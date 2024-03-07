package tn.SIRIUS.controller.students;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tn.SIRIUS.entities.*;
import tn.SIRIUS.services.*;
import tn.SIRIUS.utils.SMS;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CoursesMainPageController implements Initializable {
    @FXML
    private GridPane coursesGridPane;
    private List<Course> courses;
    @FXML
    private Button allCoursesBtn;
    @FXML
    private Button newestCoursesBtn;
    @FXML
    private Button topRatedCoursesBtn;
    @FXML
    private Button mostPopularCoursesBtn;
    @FXML
    private Button inProgressBtn;
    @FXML
    private Button completedCoursesBtn;
    @FXML
    private AnchorPane registeredCoursePage;
    @FXML
    private VBox courseRegisteredVBox;
    @FXML
    private AnchorPane courseDetailsPage;
    @FXML
    private Rectangle courseDetailsImg;
    @FXML
    private Text courseDetailsTitle;
    @FXML
    private Circle tutorCourseDetailsImg;
    @FXML
    private Text tutorNameCourseDetailsText;
    @FXML
    private Text nbrPersonRatedText;
    @FXML
    private Rectangle starBarRate;
    @FXML
    private Button miniDetailsBtn;
    @FXML
    private Button miniDescriptionBtn;
    @FXML
    private Button enrollCourseBtn;
    @FXML
    private Line lineMiniDescBtn;
    @FXML
    private Line lineMiniDetailsBtn;
    @FXML
    private Line lineMiniReviewBtn;
    @FXML
    private Text miniPrice;
    @FXML
    private AnchorPane coursesDescriptionLeft;
    @FXML
    private Text miniTutor;
    @FXML
    private Text miniPostedDate;
    @FXML
    private Text miniNbrSection;
    @FXML
    private Text miniDurationText;
    @FXML
    private Text miniCertificate;
    @FXML
    private Text miniDescription;
    @FXML
    private AnchorPane paymentContainer;
    @FXML
    private TextField cardHolderName;
    @FXML
    private TextField cardNumber;
    @FXML
    private TextField ccvCard;
    @FXML
    private TextField expireDateCard;
    @FXML
    private Label cardNameError;
    @FXML
    private Label cardNumberError;
    @FXML
    private Label ccvExpireError;
    @FXML
    private AnchorPane successOperationContainer;
    @FXML
    private AnchorPane failedOperationContainer;
    @FXML
    private AnchorPane failedOperationPasswordContainer;
    @FXML
    private AnchorPane enrollCourseConfirmationContainer;
    @FXML
    private PasswordField passwordConfirmEnroll;
    @FXML
    private VBox sectionsViewDetailsContainer;
    @FXML
    private VBox sectionsViewListContainer;
    @FXML
    private MediaView mediaViewerSectionsCourse;
    @FXML
    private AnchorPane courseStudyPage;
    @FXML
    private Slider volumeBarSectionVid;
    @FXML
    private Slider slideBarTimeVidSectioin;
    @FXML
    private ImageView playVidSecBtnImg;
    @FXML
    private ImageView muteVolumeSectVid;
    @FXML
    private Label speedVidSec;
    @FXML
    private Text totalTimeVidSection;
    @FXML
    private Text timeVideoSectionPlay;
    @FXML
    private PasswordField passwordConfirmUnroll;
    @FXML
    private AnchorPane unrollCourseConfirmationContainer;
    @FXML
    private AnchorPane ratingCourseContainer;
    @FXML
    private TextArea feedbackText;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    @FXML
    private Button deleteFeedbackBtn;
    @FXML
    private AnchorPane coursesReviewLeft;
    @FXML
    private VBox listReviewsLeftContainer;
    @FXML
    private AnchorPane mainPageContainer;
    @FXML Button whiteBoardBtn;
    private int incorrectPasswordCounter;
    private CourseService courseService;
    private List<Course> registeredCourses;
    private String styleMiniBtnNormal;
    private String styleMiniBtnHover;
    private Course currentCourse;
    private List<Section> sections;
    private MediaPlayer mediaPlayer;
    private File fileVidSection;
    private Media mediaSecVid;
    private String vidSectionPath;
    private int playVidCounter;
    private int muteVidSecCounter;
    private double currentVolume;
    private int rateValue;
    private int userTest;
    private HomePageController homePageController;
    private CourseParticipationService courseParticipationService;
    private boolean isSectionCompleted;
    private CourseParticipation cp;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        registeredCoursePage.setVisible(false);
        courseStudyPage.setVisible(false);
        allCoursesBtn.setStyle("-fx-opacity: 1;");
        courseService = new CourseService();
        styleMiniBtnNormal = """
                     -fx-font-family: "Jost Medium";
                     -fx-font-size: 14;
                     -fx-background-color: #D5FFDC;
                     -fx-text-fill: #000000;
                     -fx-background-radius: 16;
                     -fx-border-color: #19BB1C;
                     -fx-border-width: 1;
                     -fx-border-radius: 16;
                     -fx-cursor: hand;""";
        styleMiniBtnHover = """
                    -fx-font-family: "Jost Medium";
                    -fx-font-size: 14;
                    -fx-background-color: #19BB1C;
                    -fx-text-fill: #D5FFDC;
                    -fx-background-radius: 16;
                    -fx-border-color: #19BB1C;
                    -fx-border-width: 1;
                    -fx-border-radius: 16;
                    -fx-cursor: hand""";
        volumeBarSectionVid.valueProperty().addListener(observable -> {
            if(volumeBarSectionVid.isPressed())
                mediaPlayer.setVolume(volumeBarSectionVid.getValue()/100);
        });
        rateValue = 0;
        incorrectPasswordCounter = 0;
        userTest = 4;
        courseParticipationService = new CourseParticipationService();
        isSectionCompleted = false;
    }
    public void setHomePageController(HomePageController controller){
        homePageController = controller;
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
        failedOperationPasswordContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedOperationPasswordContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void loadCoursesItem(int column,int row,Course course) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPageItem.fxml"));
        Parent root = fxmlLoader.load();
        CoursesMainPageItemController controller = fxmlLoader.getController();
        controller.setData(course,this);
        coursesGridPane.add(root,column,row);
    }
    public void loadRegisteredCoursesItem(Course course) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesRegisteredItem.fxml"));
        Parent root = fxmlLoader.load();
        CoursesRegisteredItemController controller = fxmlLoader.getController();
        controller.setData(course,this);
        courseRegisteredVBox.getChildren().add(root);
    }
    public void getCourses(List<Course> courses){
        this.courses = courses;
        int column = 0;
        int row = 1;
        coursesGridPane.getChildren().clear();
        for(Course course : this.courses){
            try{
                if(column == 2){
                    column = 0;
                    row++;
                }
                loadCoursesItem(column++,row,course);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    @FXML
    public void allCoursesBtnClicked(MouseEvent event){
        allCoursesBtn.setStyle("-fx-opacity: 1;");
        newestCoursesBtn.setStyle("-fx-opacity: 0.6;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 0.6;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 0.6;");
        getCourses(courses);
    }
    @FXML
    public void newestCoursesBtnClicked(MouseEvent event) {
        allCoursesBtn.setStyle("-fx-opacity: 0.6;");
        newestCoursesBtn.setStyle("-fx-opacity: 1;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 0.6;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 0.6;");
        getCourses(courses.stream().sorted((c1, c2) -> c2.getPostedDate().compareTo(c1.getPostedDate())).collect(Collectors.toList()));
    }
    @FXML
    public void topRatedCoursesBtnClicked(MouseEvent event){
        allCoursesBtn.setStyle("-fx-opacity: 0.6;");
        newestCoursesBtn.setStyle("-fx-opacity: 0.6;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 1;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 0.6;");
        getCourses(courses.stream().sorted((c1, c2) -> (int) (c2.getRate() - c1.getRate())).collect(Collectors.toList()));
    }
    @FXML
    public void mostPopularCoursesBtnClicked(MouseEvent event){
        allCoursesBtn.setStyle("-fx-opacity: 0.6;");
        newestCoursesBtn.setStyle("-fx-opacity: 0.6;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 0.6;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 1;");
        getCourses(courses.stream().sorted((c1, c2) -> (int) (c2.getNbrRegistred() - c1.getNbrRegistred())).collect(Collectors.toList()));
    }
    @FXML
    public void registeredCoursesBtnClicked(MouseEvent event){
        courseRegisteredVBox.getChildren().clear();
        inProgressBtn.setStyle("-fx-opacity: 1");
        registeredCourses = courseService.getCourseRegistered(userTest);
        for (Course course : registeredCourses){
            try {
                loadRegisteredCoursesItem(course);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        registeredCoursePage.setVisible(true);
    }
    @FXML
    public void completedCoursesBtnClicked(MouseEvent event){
        inProgressBtn.setStyle("-fx-opacity: 0.6");
        completedCoursesBtn.setStyle("-fx-opacity: 1");
    }
    @FXML
    public void inProgressBtnClicked(MouseEvent event){
        inProgressBtn.setStyle("-fx-opacity: 1");
        completedCoursesBtn.setStyle("-fx-opacity: 0.6");
    }
    @FXML
    public void goBackRegisteredCoursesClicked(MouseEvent event){
        registeredCoursePage.setVisible(false);
    }
    public void setUpCourseDetails(Course course){
        courseDetailsImg.setFill(new ImagePattern(new Image(
                "file:/"+course.getImage().replace("\\","/"))));
        courseDetailsTitle.setText(course.getTitle());
        tutorCourseDetailsImg.setFill(new ImagePattern(
                new Image("file:/"+course.getTutor().getImage().replace("\\","/"))));
        tutorNameCourseDetailsText.setText(course.getTutor().getLastName()+" "+course.getTutor().getFirstName());
        nbrPersonRatedText.setText("( "+course.getNbrPersonRated()+" )");
        starBarRate.setWidth((course.getRate() / 5)*100);
        courseDetailsPage.setVisible(true);
        miniTutor.setText(course.getTutor().getLastName()+" "+course.getTutor().getFirstName());
        miniPostedDate.setText(course.getPostedDate());
        miniNbrSection.setText(String.valueOf(course.getNbrSection()));
        miniDurationText.setText(course.getDuration());
        //miniCertificate;
        miniDescription.setText("\" "+course.getDescription()+" \"");
        miniPrice.setText(course.getPrice()+" TND");
        FeedbackService fbService = new FeedbackService();
        List<Feedback> feedbacks = fbService.getFeedbacksCourse(course.getId());
        listReviewsLeftContainer.getChildren().clear();
        for (Feedback feedback : feedbacks){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/reviewItemCourse.fxml"));
                Parent root = fxmlLoader.load();
                ReviewItemCourseController controller = fxmlLoader.getController();
                controller.setData(feedback);
                listReviewsLeftContainer.getChildren().add(root);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        currentCourse = course;
    }
    @FXML
    public void goBackCoursesDetailsClicked(MouseEvent event){
        courseDetailsPage.setVisible(false);
    }
    @FXML
    public void miniDescBtnClicked(MouseEvent event){
        lineMiniDescBtn.setVisible(true);
        lineMiniDetailsBtn.setVisible(false);
        lineMiniReviewBtn.setVisible(false);
        coursesDescriptionLeft.setVisible(true);
        coursesReviewLeft.setVisible(false);
    }
    @FXML
    public  void miniDetailsBtnClicked(MouseEvent event){
        lineMiniDetailsBtn.setVisible(true);
        lineMiniDescBtn.setVisible(false);
        lineMiniReviewBtn.setVisible(false);
        coursesDescriptionLeft.setVisible(false);
        coursesReviewLeft.setVisible(false);
    }
    @FXML
    public void miniReviewBtnClicked(MouseEvent event){
        lineMiniDetailsBtn.setVisible(false);
        lineMiniDescBtn.setVisible(false);
        lineMiniReviewBtn.setVisible(true);
        coursesReviewLeft.setVisible(true);
    }
    // Enroll Course
    @FXML
    public void enrollCourseBtnClicked(MouseEvent event){
        CreditCardService ccService = new CreditCardService();
        if(ccService.isUserHadCreditCard(userTest)){
            enrollCourseConfirmationContainer.setVisible(true);
        }
        else paymentContainer.setVisible(true);
    }
    public void enrollFromItem(Course course){
        currentCourse = course;
        CreditCardService ccService = new CreditCardService();
        if(ccService.isUserHadCreditCard(userTest)){
            enrollCourseConfirmationContainer.setVisible(true);
        }
        else paymentContainer.setVisible(true);
    }
    @FXML
    public void closePaymentClicked(MouseEvent event){
        paymentContainer.setVisible(false);
        cardHolderName.clear();
        cardNumber.clear();
        ccvCard.clear();
        expireDateCard.clear();
        cardNameError.setVisible(false);
        cardNumberError.setVisible(false);
        ccvExpireError.setVisible(false);
    }
    @FXML
    public void addCardEnrollCourseClicked(MouseEvent event){
        String cardholderNameInputText = cardHolderName.getText();
        String cardNumberText = cardNumber.getText();
        String ccvCardText = ccvCard.getText();
        String expireDateText = expireDateCard.getText();
        if( cardNumberText.matches("[0-9]+") && cardNumberText.length() == 16
            && !cardholderNameInputText.isEmpty() && ccvCardText.matches("[0-9]+") && ccvCardText.length() == 3 && !expireDateText.isEmpty()){
            CreditCardService creditCardService = new CreditCardService();
            User user = new User(userTest,"","","","");
            if(creditCardService.addCard(new CreditCard(
                    0,user,cardNumberText,cardholderNameInputText,ccvCardText,expireDateText
            ))){
                CourseParticipationService cpService = new CourseParticipationService();
                if(cpService.enrollNewCourse(userTest,currentCourse.getId())){
                    if(courseService.updateNbrRegistered(currentCourse.getId(),currentCourse.getNbrRegistred()+1)){
                        closePaymentClicked(null);
                        registeredCoursesBtnClicked(null);
                        courses.remove(currentCourse);
                        getCourses(courses);
                        courseDetailsPage.setVisible(false);
                        notifySuccess();
                    } else {
                        closePaymentClicked(null);
                        notifyFailed();
                    }
                } else {
                    closePaymentClicked(null);
                    notifyFailed();
                }
            } else {
                closePaymentClicked(null);
                notifyFailed();
            }
        }
        if(!cardNumberText.matches("[0-9]+") || cardNumberText.length() != 16){
            cardNumberError.setVisible(true);
        }
        if(cardholderNameInputText.isEmpty()){
            cardNameError.setVisible(true);
        }
        if(!ccvCardText.matches("[0-9]+") || ccvCardText.length() != 3 || expireDateText.isEmpty()){
            ccvExpireError.setVisible(true);
        }
    }
    @FXML
    public void closeEnrollConfirmation(MouseEvent event){
        enrollCourseConfirmationContainer.setVisible(false);
        passwordConfirmEnroll.clear();
    }
    @FXML
    public void confirmEnrollClicked(MouseEvent event){
        UserService userService = new UserService();
        if(userService.isPasswordMatch(userTest,passwordConfirmEnroll.getText())){
            CourseParticipationService cpService = new CourseParticipationService();
            if(cpService.enrollNewCourse(userTest,currentCourse.getId())){
                if(courseService.updateNbrRegistered(currentCourse.getId(),currentCourse.getNbrRegistred()+1)){
                    closeEnrollConfirmation(null);
                    registeredCoursesBtnClicked(null);
                    courses.remove(currentCourse);
                    getCourses(courses);
                    courseDetailsPage.setVisible(false);
                    notifySuccess();
                } else {
                    closeEnrollConfirmation(null);
                    notifyFailed();
                }
            } else {
                closeEnrollConfirmation(null);
                notifyFailed();
            }
        } else {
            closeEnrollConfirmation(null);
            sendSecuritySms();
            notifyFailedPassword();
        }
    }
    // Learning Page
    public void setUpLearningPage(Course course,boolean isOnRefresh){
        int count = 0;
        int countForCompleted = 1;
        currentCourse = course;
        sectionsViewListContainer.getChildren().clear();
        cp = courseParticipationService.getCourseParticipationById(userTest,course.getId());
        SectionService sectionService = new SectionService();
        sections = sectionService.getAll(course.getId());
        try{
            for(Section section : sections) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/sectionItemPlay.fxml"));
                Parent root = fxmlLoader.load();
                SectionItemPlayController controller = fxmlLoader.getController();
                if(cp.getSectionDone() == 0){
                    if(!isOnRefresh){
                        if(count < 1){
                            setSectionPlay(section,false);
                            count++;
                        }
                    }
                    if(countForCompleted == 1){
                        controller.setData(section,this,false,true);
                    }
                    else if(countForCompleted == cp.getSectionDone()+1){
                        controller.setData(section,this,false,true);
                    } else if(countForCompleted <= cp.getSectionDone() && countForCompleted != 0){
                        controller.setData(section,this,true,false);
                    } else
                        controller.setData(section,this,false,false);
                } else {
                    if(!isOnRefresh){
                        if(count < 1){
                            setSectionPlay(section,true);
                            count++;
                        }
                    }
                    if(countForCompleted == cp.getSectionDone()+1){
                        controller.setData(section,this,false,true);
                    } else if(countForCompleted <= cp.getSectionDone() && countForCompleted != 0){
                        controller.setData(section,this,true,false);
                    } else
                        controller.setData(section,this,false,false);
                }
                countForCompleted++;
                sectionsViewListContainer.getChildren().add(root);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        courseStudyPage.setVisible(true);
    }
    public void setSectionPlay(Section section,boolean isFinished){
        isSectionCompleted = isFinished;
        if (mediaPlayer != null) mediaPlayer.dispose();
        mediaSecVid = new Media(section.getAttachment());
        mediaPlayer = new MediaPlayer(mediaSecVid);
        mediaViewerSectionsCourse.setMediaPlayer(mediaPlayer);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/SectionDetailItem.fxml"));
            Parent root = fxmlLoader.load();
            SectionDetailItemController controller = fxmlLoader.getController();
            controller.setData(section,this);
            sectionsViewDetailsContainer.getChildren().clear();
            sectionsViewDetailsContainer.getChildren().add(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        initPlayVidCounter();
        updateTimeLabelVideoSection();
    }
    // Start Video Play
    public void updateTimeLabelVideoSection(){
        timeVideoSectionPlay.textProperty().bind(Bindings.createStringBinding(() -> { /* Callable<String> ( call() )*/
            try{
                float progress = (float) (mediaPlayer.getCurrentTime().toSeconds() / mediaPlayer.getMedia().getDuration().toSeconds());
                if(progress >= 0.9 && !isSectionCompleted){
                    isSectionCompleted = true;
                    if(courseParticipationService.updateCourseParticipationDone(cp))
                        setUpLearningPage(currentCourse,true);
                }
                return getVidTime(mediaPlayer.getCurrentTime()) + " / ";
            }catch (Exception e){
                System.out.println(e.getMessage());
                return "";
            }
        },mediaPlayer.currentTimeProperty()));
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
    @FXML
    public void updateSecVidSpeed(MouseEvent event){
        if (speedVidSec.getText().equals("x1")){
            speedVidSec.setText("x1.5");
            mediaPlayer.setRate(1.5);
        } else {
            speedVidSec.setText("x1");
            mediaPlayer.setRate(1.0);
        }
    }
    public void initPlayVidCounter(){
        playVidCounter = 0;
        muteVidSecCounter = 0;
        playVidSecBtnImg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/play-fill.png"))));
        muteVolumeSectVid.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/volume-up-line.png"))));
        volumeBarSectionVid.setValue(100);
        slideBarTimeVidSectioin.setValue(0);
        speedVidSec.setText("x1");
        mediaPlayer.setRate(1.0);
        mediaPlayer.totalDurationProperty().addListener((obs,oldDuration,newDuration) -> {
            totalTimeVidSection.setText(getVidTime(newDuration));
        });
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
    public void restartEndVideo(){
        playVidCounter = 0;
        playVidSecBtnImg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/light/play-fill.png"))));
        mediaPlayer.seek(new Duration(0));
        mediaPlayer.pause();
        slideBarTimeVidSectioin.setValue(0);
    }
    public void updateProgressBarSecVideo(){
        Platform.runLater(() -> slideBarTimeVidSectioin.setValue(mediaPlayer.getCurrentTime().toMillis()/mediaPlayer.getTotalDuration().toMillis()*100) );
    }
    // End video play
    // Unroll
    public void showConfirmationUnroll(Course course){
        currentCourse = course;
        unrollCourseConfirmationContainer.setVisible(true);
    }
    @FXML
    public void closeUnrollConfirmation(MouseEvent event){
        unrollCourseConfirmationContainer.setVisible(false);
        passwordConfirmUnroll.clear();
    }
    @FXML
    public void confirmUnrollClicked(MouseEvent event){
        UserService userService = new UserService();
        if(userService.isPasswordMatch(userTest,passwordConfirmUnroll.getText())){
            CourseParticipationService cpService = new CourseParticipationService();
            if(cpService.unrollCourse(currentCourse.getId(),userTest)){
                if(courseService.updateNbrRegistered(currentCourse.getId(),currentCourse.getNbrRegistred()-1)){
                    courses.add(currentCourse);
                    registeredCourses.remove(currentCourse);
                    getCourses(courses);
                    registeredCoursesBtnClicked(null);
                    notifySuccess();
                } else
                    notifyFailed();
            }else
                notifyFailed();
        } else{
            sendSecuritySms();
            notifyFailedPassword();
        }
        closeUnrollConfirmation(null);
    }
    public void sendSecuritySms(){
        incorrectPasswordCounter++;
        if(incorrectPasswordCounter == 5){
            SMS.sendSms();
            incorrectPasswordCounter = 0;
        }
    }
    // Rate
    @FXML
    public void closeRating(MouseEvent event){
        ratingCourseContainer.setVisible(false);
        deleteFeedbackBtn.setVisible(false);
        rateValue = 0;
        star1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star4.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star5.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        feedbackText.clear();
    }
    public void showRatingForm(Course course){
        FeedbackService fbService = new FeedbackService();
        Feedback feedback = fbService.getFeedback(userTest,course.getId());
        if(feedback.getId() != 0){
            switch (feedback.getRate()){
                case 1 -> starOneClicked(null);
                case 2 -> starTwoClicked(null);
                case 3 -> starThreeClicked(null);
                case 4 -> starFourClicked(null);
                case 5 -> starFiveClicked(null);
                default -> System.out.println("Error !");
            };
            feedbackText.setText(feedback.getContent());
            deleteFeedbackBtn.setVisible(true);
            deleteFeedbackBtn.setOnMouseClicked(e -> {
                    if(fbService.deleteFeedback(feedback)){
                        closeRating(null);
                    }
            });
        }
        currentCourse = course;
        ratingCourseContainer.setVisible(true);
    }
    @FXML
    public void submitFeedback(MouseEvent event){
        FeedbackService fbService = new FeedbackService();
        User owner = new User(userTest,"","","","");
        String content = feedbackText.getText();
        Feedback feedback = new Feedback(0,owner,"",content,rateValue,currentCourse.getId());
        if(!deleteFeedbackBtn.isVisible()){
            if(fbService.addFeedback(feedback)) notifySuccess();
            else notifyFailed();
        } else {
            if(fbService.updateFeedback(feedback)) notifySuccess();
            else notifyFailed();
        }
        closeRating(null);
    }
    @FXML
    public void starOneClicked(MouseEvent event){
        star1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star4.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star5.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        rateValue = 1;
    }
    @FXML
    public void starTwoClicked(MouseEvent event){
        star1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star4.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star5.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        rateValue = 2;
    }
    @FXML
    public void starThreeClicked(MouseEvent event){
        star1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star4.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        star5.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        rateValue = 3;
    }
    @FXML
    public void starFourClicked(MouseEvent event){
        star1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star4.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star5.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStarEmpty.png"))));
        rateValue = 4;
    }
    @FXML
    public void starFiveClicked(MouseEvent event){
        star1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star4.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        star5.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/oneStar.png"))));
        rateValue = 5;
    }
    @FXML
    public void goBackCoursesStudyClicked(MouseEvent event){
        if (mediaPlayer != null)
            mediaPlayer.dispose();
        registeredCoursesBtnClicked(null);
        courseStudyPage.setVisible(false);
    }
    @FXML
    public void whiteboardBtnClicked(MouseEvent event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/whiteboard.fxml"));
            Parent root = fxmlLoader.load();
            WhiteBoardController controller = fxmlLoader.getController();
            controller.setCourseId(currentCourse.getId(),homePageController);
            mainPageContainer.getChildren().clear();
            mainPageContainer.getChildren().add(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // Quiz play
    public void setUpQuizPlay(Quiz quiz){
        homePageController.setUpQuizPlay(quiz);
    }
    public void goBackFromQuiz(List<Course> notRegistered,List<Course> registeredCourse,Course currentCourse){
        setUpLearningPage(currentCourse,false);
        this.courses = notRegistered;
        this.registeredCourses = registeredCourse;
    }
}
