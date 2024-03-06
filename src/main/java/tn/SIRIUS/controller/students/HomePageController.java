package tn.SIRIUS.controller.students;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.SIRIUS.entities.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import tn.SIRIUS.services.CourseService;
import tn.SIRIUS.services.QuizAnswerService;
import tn.SIRIUS.services.SectionService;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HomePageController implements Initializable {
    @FXML
    private Circle profilImgContainer;
    @FXML
    private HBox notifCount;
    @FXML
    private HBox msgCount;
    @FXML
    private Label welcomeMsg;
    @FXML
    private Circle mostProgressedCourseImgContainer;
    @FXML
    private Arc mostProgressedCoursePrc;
    @FXML
    private Label mostProgressedCoursePrcVal;
    @FXML
    private Button homeBtn;
    @FXML
    private ImageView homeBtnImg;
    @FXML
    private Button coursesBtn;
    @FXML
    private ImageView coursesBtnImg;
    @FXML
    private Button eventsBtn;
    @FXML
    private ImageView eventsBtnImg;
    @FXML
    private Button forumBtn;
    @FXML
    private ImageView forumBtnImg;
    @FXML
    private Button productsBtn;
    @FXML
    private ImageView productsBtnImg;
    @FXML
    private Button collectsBtn;
    @FXML
    private ImageView collectsBtnImg;
    @FXML
    private VBox coursesVboxHomePage;
    @FXML
    private Pane mainPageContainer;
    @FXML
    private TextField searchInput;
    @FXML
    private AnchorPane demandeJoinWBContainer;
    @FXML
    private Circle demandeJoinWBImg;
    @FXML
    private Label courseWBJTitle;
    @FXML private AnchorPane quizContainer;
    @FXML private Text courseTitleQuiz;
    @FXML private Text sectionTitleQuiz;
    @FXML private Text quizQuestionPlay;
    @FXML private Text quizQuestionRep1;
    @FXML private Text quizQuestionRep2;
    @FXML private Text quizQuestionRep3;
    @FXML private Text quizQuestionRep4;
    @FXML private AnchorPane quizCountReadyContainer;
    @FXML private Text countForReady;
    @FXML private Text timerQuiz;
    @FXML private VBox questionAnswersContainer;
    private Timeline countDownTimeline;
    private MediaPlayer whiteboardPlayer;
    private MediaPlayer clappingSound;
    private MediaPlayer timeOutSound;
    private CoursesMainPageController coursesMainPageController;
    private String styleMenuBtnClicked;
    private String styleMenuBtnNormal;
    private List<Course> courses;
    private CourseService courseService;
    private Course courseWB;
    private String currentPage;
    private int userTest;
    private boolean isWaiting;
    private static DatagramSocket socket;
    private static InetAddress address;
    private static final int SERVER_PORT = 12345;
    private Thread clientThread;
    private Quiz quizToPlay;
    private int currentQuestionIndex;
    private int totQuestionNbr;
    private Map<QuizQuestion,String> answerQuestionsMap = new HashMap<>();
    private SectionService sectionService;
    private QuizAnswerService quizAnswerService;
    private int idSectionComingFrom;
    private Course currentCourseQuiz;
    private boolean isThreadRunning;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        isThreadRunning = true;
        sectionService = new SectionService();
        quizAnswerService = new QuizAnswerService();
        quizContainer.setVisible(false);
        quizCountReadyContainer.setVisible(false);
        userTest = 4;
        //Profile Image
        Image profileImg = new Image("/images/profilePictures/12.jpg");
        profilImgContainer.setFill(new ImagePattern(profileImg));
        //Msg and Notifications
        Label nbrNotif = new Label();
        Label nbrMsg = new Label();
        nbrNotif.setText("5");
        nbrMsg.setText("2");
        nbrNotif.setStyle("-fx-font-family: \"Jost\";-fx-font-weight: bold;-fx-font-size: 16;-fx-text-fill: white;");
        nbrMsg.setStyle("-fx-font-family: \"Jost\";-fx-font-weight: bold;-fx-font-size: 16;-fx-text-fill: white;");
        notifCount.getChildren().add(nbrNotif);
        msgCount.getChildren().add(nbrMsg);
        //Welcome Msg
        String welcomeText = "Welcome Abdallah!";
        welcomeMsg.setText(welcomeText);
        //Most progressed courses
        Image mostProgressedCourseImg = new Image("/images/coursesImg/Finished-Basket-3-cropped.jpg");
        mostProgressedCourseImgContainer.setFill(new ImagePattern(mostProgressedCourseImg));
        double prcProg = 99.0;
        double progressCourseValue = (prcProg / 100.0) * 360;
        mostProgressedCoursePrc.setLength(progressCourseValue);
        mostProgressedCoursePrcVal.setText(String.valueOf(prcProg)+"%");
        //Menu
        styleMenuBtnClicked =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: #D5FFDC;
                -fx-background-radius: 24px;
                -fx-text-fill: #1D6611;
                -fx-cursor: hand;""";
        styleMenuBtnNormal =
                """
                -fx-font-family: "Jost Medium";
                -fx-font-size: 16;
                -fx-background-color: transparent;
                -fx-text-fill: #D5FFDC;
                -fx-cursor: hand;""";
        homeBtn.setStyle(styleMenuBtnClicked);
        homeBtnImg.setImage(new Image("/icons/dark/home-4-fill.png"));
        // Services
        courseService = new CourseService();
        // Init Page
        currentPage = "home";
        showAllCourses();
        // Sending connection packet to the Whiteboard server
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            byte[] uuid = ("studentJoinServer;" + userTest+ ";").getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isWaiting = true;
        clientThread = new Thread(new HomePageController.ClientThread());
        clientThread.start();
    }
    // Menu
    @FXML
    public void homeBtnClicked(MouseEvent event){
        homeBtn.setStyle(styleMenuBtnClicked);
        homeBtnImg.setImage(new Image("/icons/dark/home-4-fill.png"));
        if(coursesBtn.getStyle().equals(styleMenuBtnClicked)){
            coursesBtn.setStyle(styleMenuBtnNormal);
            coursesBtnImg.setImage(new Image("/icons/light/graduation-cap-fill.png"));
        } else if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
            eventsBtn.setStyle(styleMenuBtnNormal);
            eventsBtnImg.setImage(new Image("/icons/light/calendar-event-line.png"));
        } else if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
            forumBtn.setStyle(styleMenuBtnNormal);
            forumBtnImg.setImage(new Image("/icons/light/group-fill.png"));
        } else if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
            productsBtn.setStyle(styleMenuBtnNormal);
            productsBtnImg.setImage(new Image("/icons/light/shopping-bag-3-fill.png"));
        } else if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
            collectsBtn.setStyle(styleMenuBtnNormal);
            collectsBtnImg.setImage(new Image("/icons/light/recycle-fill.png"));
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/homePage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root,1350,720);
            Stage stage = (Stage) mainPageContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            currentPage = "home";
            coursesMainPageController = null;
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void coursesBtnClicked(MouseEvent event){
        coursesBtn.setStyle(styleMenuBtnClicked);
        coursesBtnImg.setImage(new Image("/icons/dark/graduation-cap-fill.png"));
        if(homeBtn.getStyle().equals(styleMenuBtnClicked)){
            homeBtn.setStyle(styleMenuBtnNormal);
            homeBtnImg.setImage(new Image("/icons/light/home-4-fill.png"));
        } else if(eventsBtn.getStyle().equals(styleMenuBtnClicked)){
            eventsBtn.setStyle(styleMenuBtnNormal);
            eventsBtnImg.setImage(new Image("/icons/light/calendar-event-line.png"));
        } else if(forumBtn.getStyle().equals(styleMenuBtnClicked)){
            forumBtn.setStyle(styleMenuBtnNormal);
            forumBtnImg.setImage(new Image("/icons/light/group-fill.png"));
        } else if(productsBtn.getStyle().equals(styleMenuBtnClicked)){
            productsBtn.setStyle(styleMenuBtnNormal);
            productsBtnImg.setImage(new Image("/icons/light/shopping-bag-3-fill.png"));
        } else if(collectsBtn.getStyle().equals(styleMenuBtnClicked)){
            collectsBtn.setStyle(styleMenuBtnNormal);
            collectsBtnImg.setImage(new Image("/icons/light/recycle-fill.png"));
        }
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPage.fxml"));
            Parent root = fxmlLoader.load();
            coursesMainPageController = fxmlLoader.getController();
            coursesMainPageController.getCourses(this.courses);
            coursesMainPageController.setHomePageController(this);
            mainPageContainer.getChildren().clear();
            mainPageContainer.getChildren().add(root);
            currentPage = "courses";
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // Home Page Start
    public void showAllCourses(){
        courses = courseService.getCoursesNotRegistered(userTest);
        for (Course course : courses){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesHomePageItem.fxml"));
                Parent root = fxmlLoader.load();
                CoursesHomePageItemController itemController = fxmlLoader.getController();
                itemController.setCourseData(course);
                coursesVboxHomePage.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @FXML
    public void searchForSomething(KeyEvent search){
        TextInputControl textInputControl = (TextInputControl) search.getSource();
        String searchInput = textInputControl.getText();
        if (!searchInput.isEmpty()){
            if(currentPage.equals("courses")){
                coursesMainPageController.getCourses(courses.stream().filter(
                        c -> ( c.getTitle().contains(searchInput) || c.getTutor().getFirstName().contains(searchInput) || c.getTutor().getLastName().contains(searchInput))
                ).collect(Collectors.toList()));
            }
        } else
            coursesMainPageController.getCourses(courses);
    }
    public void closeClientThread(){
        isThreadRunning = false;
        socket.close();
    }
    private class ClientThread implements Runnable{
        @Override
        public void run(){
            System.out.println("thread start");
            byte[] incoming = new byte[256];
            while (isThreadRunning) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
                try {
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    if (msg.contains("courseAlert;")) {
                        String[] allData = msg.split(";");
                        int courseId = Integer.parseInt(allData[1]);
                        courseWB = courseService.getOne(courseId);
                        System.out.println(msg);
                        Platform.runLater(() -> {
                            demandeJoinWBImg.setFill(new ImagePattern(new Image(
                                    "file:/"+courseWB.getImage().replace("\\","/")
                            )));
                            courseWBJTitle.setText(courseWB.getTitle());
                            demandeJoinWBContainer.setVisible(true);
                            String musicFile = "C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/Microsoft Teams Call Sound.mp3";
                            Media sound = new Media(new File(musicFile).toURI().toString());
                            whiteboardPlayer = new MediaPlayer(sound);
                            whiteboardPlayer.play();
                            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(41000), e -> {
                                whiteboardPlayer.dispose();
                                demandeJoinWBContainer.setVisible(false);
                            }));
                            timeline.setCycleCount(1);
                            timeline.play();
                        });
                    }
                }catch (IOException e){
                    System.out.println(e.getMessage());
                }

            }
        }
    }
    @FXML
    public void joWBMeeting(MouseEvent event){
        whiteboardPlayer.dispose();
        demandeJoinWBContainer.setVisible(false);
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/students/whiteboard.fxml"));
            Parent root = fxmlLoader.load();
            WhiteBoardController controller = fxmlLoader.getController();
            controller.setCourseId(courseWB.getId(),this);
            System.out.println("iddd"+courseWB.getId());
            mainPageContainer.getChildren().clear();
            mainPageContainer.getChildren().add(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    public void declineWBMeeting(MouseEvent event){
        whiteboardPlayer.dispose();
        demandeJoinWBContainer.setVisible(false);
    }
    // Quiz play
    @FXML
    public void cardAnswer1Clicked(MouseEvent event){
        countDownTimeline.stop(); // Stop the timer
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2), questionAnswersContainer);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setOnFinished(e -> questionAnswersContainer.setDisable(false));
        fadeOutTransition.play();
        answerQuestionsMap.put(quizToPlay.getQuestions().get(currentQuestionIndex),"Choice 1");
        answerQuestionSkip(quizToPlay.getQuestions().get(currentQuestionIndex));
    }
    @FXML
    public void cardAnswer2Clicked(MouseEvent event){
        countDownTimeline.stop(); // Stop the timer
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2), questionAnswersContainer);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setOnFinished(e -> questionAnswersContainer.setDisable(false));
        fadeOutTransition.play();
        answerQuestionsMap.put(quizToPlay.getQuestions().get(currentQuestionIndex),"Choice 2");
        answerQuestionSkip(quizToPlay.getQuestions().get(currentQuestionIndex));
    }
    @FXML
    public void cardAnswer3Clicked(MouseEvent event){
        countDownTimeline.stop(); // Stop the timer
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2), questionAnswersContainer);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setOnFinished(e -> questionAnswersContainer.setDisable(false));
        fadeOutTransition.play();
        answerQuestionsMap.put(quizToPlay.getQuestions().get(currentQuestionIndex),"Choice 3");
        answerQuestionSkip(quizToPlay.getQuestions().get(currentQuestionIndex));
    }
    @FXML
    public void cardAnswer4Clicked(MouseEvent event){
        countDownTimeline.stop(); // Stop the timer
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2), questionAnswersContainer);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setOnFinished(e -> questionAnswersContainer.setDisable(false));
        fadeOutTransition.play();
        answerQuestionsMap.put(quizToPlay.getQuestions().get(currentQuestionIndex),"Choice 4");
        answerQuestionSkip(quizToPlay.getQuestions().get(currentQuestionIndex));
    }
    public void setUpQuizPlay(Quiz quiz){
        quizToPlay = quiz;
        Section section = sectionService.getSectionById(quizToPlay.getSection());
        idSectionComingFrom = section.getIdSection();
        currentCourseQuiz = courseService.getOne(section.getCourse());
        courseTitleQuiz.setText(currentCourseQuiz.getTitle());
        sectionTitleQuiz.setText(section.getTitle());
        String musicFile = "C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/Simple 10 second countdown 4K with beep.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        timeOutSound = new MediaPlayer(sound);
        // Animation start
        quizCountReadyContainer.setOpacity(0);
        quizCountReadyContainer.setVisible(true);
        Timeline timeline = new Timeline();
        // Add keyframes for countdown from 3 to 0
        for (int i = 3; i >= 0; i--) {
            final int countValue = i;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(3 - i), e -> {
                countForReady.setText(Integer.toString(countValue));
                countForReady.setOpacity(1.0);
            });
            timeline.getKeyFrames().add(keyFrame);
        }
        // Fade in Counter begin
        KeyFrame fadeInCountKeyFrame = new KeyFrame(Duration.seconds(0.25), e -> {
            FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(0.25), quizCountReadyContainer);
            fadeInTransition.setFromValue(0.0);
            fadeInTransition.setToValue(1.0);
            fadeInTransition.play();
        });
        timeline.getKeyFrames().add(fadeInCountKeyFrame);
        // Fade out Counter begin
        // Show quiz play page
        KeyFrame showPlayKeyFrame = new KeyFrame(Duration.seconds(3.5), e -> {
            quizCountReadyContainer.setVisible(false);
            currentQuestionIndex = 0;
            totQuestionNbr = quizToPlay.getQuestions().size();
            quizContainer.setVisible(true);
            questionPlay(quizToPlay.getQuestions().get(0));
        });
        timeline.getKeyFrames().add(showPlayKeyFrame);
        timeline.play();
    }
    public void questionPlay(QuizQuestion quizQuestion){
        timerQuiz.setStyle("""
                -fx-font-family: "Jost Medium";
                    -fx-font-size: 32;
                    -fx-fill: #000000;
                """);
        quizQuestionPlay.setText(quizQuestion.getQuestion());
        quizQuestionRep1.setText(quizQuestion.getChoice_1());
        quizQuestionRep2.setText(quizQuestion.getChoice_2());
        quizQuestionRep3.setText(quizQuestion.getChoice_3());
        quizQuestionRep4.setText(quizQuestion.getChoice_4());
        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(2), questionAnswersContainer);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1.0);
        fadeInTransition.play();
        AtomicInteger remainingTime = new AtomicInteger(15);
        timerQuiz.setText(Integer.toString(remainingTime.get()));
        countDownTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    int timeLeft = remainingTime.decrementAndGet();
                    timerQuiz.setText(Integer.toString(timeLeft));
                    if(timeLeft == 10){
                        timerQuiz.setStyle("""
                -fx-font-family: "Jost Medium";
                    -fx-font-size: 36;
                    -fx-fill: #ff0101;
                """);
                        timeOutSound.play();
                    }
                    if (timeLeft == 0) {
                        countDownTimeline.stop(); // Stop the timer
                        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(2), questionAnswersContainer);
                        fadeOutTransition.setFromValue(1.0);
                        fadeOutTransition.setToValue(0.0);
                        fadeOutTransition.play();
                        answerQuestiontimeOut(quizQuestion); // Time out
                    }
                })
        );
        countDownTimeline.setCycleCount(15);
        countDownTimeline.play();
    }
    public void answerQuestiontimeOut(QuizQuestion quizQuestion){
        if(currentQuestionIndex < totQuestionNbr-1){
            timeOutSound.stop();
            timeOutSound.seek(Duration.millis(0));
            answerQuestionsMap.put(quizQuestion,"Time End");
            currentQuestionIndex++;
            questionPlay(quizToPlay.getQuestions().get(currentQuestionIndex));
        } else {
            timeOutSound.stop();
            timeOutSound.seek(Duration.millis(0));
            answerQuestionsMap.put(quizQuestion,"Time End");
            calculateScoreQuiz(answerQuestionsMap);
        }
    }
    public void answerQuestionSkip(QuizQuestion quizQuestion){
        if(currentQuestionIndex < totQuestionNbr-1){
            timeOutSound.stop();
            timeOutSound.seek(Duration.millis(0));
            currentQuestionIndex++;
            questionPlay(quizToPlay.getQuestions().get(currentQuestionIndex));
        } else {
            timeOutSound.stop();
            timeOutSound.seek(Duration.millis(0));
            calculateScoreQuiz(answerQuestionsMap);
        }
    }
    public void calculateScoreQuiz(Map<QuizQuestion,String> answers){
        int nbrCorrect = 0;
        for(Map.Entry<QuizQuestion,String> entry : answers.entrySet()){
            if(entry.getKey().getCorrect_choice().equals(entry.getValue()))
                nbrCorrect++;
        }
        float result = ((float) nbrCorrect/totQuestionNbr)*100;
        System.out.println("result :"+result);
        if(result < 50){
            quizCountReadyContainer.setStyle("-fx-background-color: #ff001e");
            countForReady.setStyle("""
                    -fx-font-family: "Jost SemiBold";
                        -fx-font-size: 50;
                        -fx-fill: #ffffff;""");
            countForReady.setText("Hard Luck you got "+result+"% !");
            quizCountReadyContainer.setVisible(true);
            FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(2), quizCountReadyContainer);
            fadeInTransition.setFromValue(0);
            fadeInTransition.setToValue(1.0);
            fadeInTransition.play();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
                quizCountReadyContainer.setVisible(true);
            }));
            timeline.setCycleCount(1);
            timeline.setOnFinished(e ->{
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPage.fxml"));
                    Parent root = fxmlLoader.load();
                    coursesMainPageController = fxmlLoader.getController();
                    coursesMainPageController.getCourses(this.courses);
                    coursesMainPageController.setHomePageController(this);
                    mainPageContainer.getChildren().clear();
                    coursesMainPageController.goBackFromQuiz(courses,courseService.getCourseRegistered(userTest),currentCourseQuiz);
                    mainPageContainer.getChildren().add(root);
                    currentPage = "courses";
                }catch (IOException ee){
                    System.out.println(ee.getMessage());
                }
                quizContainer.setVisible(false);
                quizCountReadyContainer.setVisible(false);
                quizCountReadyContainer.setStyle("-fx-background-color: #000000");
            });
            timeline.play();
            answers.clear();
            countForReady.setStyle("""
                    -fx-font-family: "Jost SemiBold";
                        -fx-font-size: 100;
                        -fx-fill: #ffffff;""");
        }else {
            User student = new User(userTest,"","","","");
            //quizAnswerService.addAnswer(
            //                    new QuizAnswer(0,student,quizToPlay,result,"")
            //            )
            boolean test = true;
            if(test){
                String musicFile = "C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/Audience Clapping Sound Effects (no copyright).mp3";
                Media sound = new Media(new File(musicFile).toURI().toString());
                clappingSound = new MediaPlayer(sound);
                clappingSound.play();
                quizCountReadyContainer.setStyle("-fx-background-color: #19BB1C");
                countForReady.setStyle("""
                        -fx-font-family: "Jost SemiBold";
                            -fx-font-size: 50;
                            -fx-fill: #ffffff;""");
                countForReady.setText("Congratulations you got "+result+"% !");
                quizCountReadyContainer.setVisible(true);
                FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(2), quizCountReadyContainer);
                fadeInTransition.setFromValue(0);
                fadeInTransition.setToValue(1.0);
                fadeInTransition.play();
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
                    quizCountReadyContainer.setVisible(true);
                }));
                timeline.setCycleCount(1);
                timeline.setOnFinished(e ->{
                    try{
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPage.fxml"));
                        Parent root = fxmlLoader.load();
                        coursesMainPageController = fxmlLoader.getController();
                        coursesMainPageController.getCourses(this.courses);
                        coursesMainPageController.setHomePageController(this);
                        mainPageContainer.getChildren().clear();
                        coursesMainPageController.goBackFromQuiz(courses,courseService.getCourseRegistered(userTest),currentCourseQuiz);
                        mainPageContainer.getChildren().add(root);
                        currentPage = "courses";
                    }catch (IOException ee){
                        System.out.println(ee.getMessage());
                    }
                    quizContainer.setVisible(false);
                    quizCountReadyContainer.setVisible(false);
                    quizCountReadyContainer.setStyle("-fx-background-color: #000000");
                });
                timeline.play();
                answers.clear();
                countForReady.setStyle("""
                        -fx-font-family: "Jost SemiBold";
                            -fx-font-size: 100;
                            -fx-fill: #ffffff;""");
            }
        }
    }
}
