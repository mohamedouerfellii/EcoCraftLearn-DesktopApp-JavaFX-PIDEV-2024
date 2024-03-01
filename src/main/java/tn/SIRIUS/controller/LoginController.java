package tn.SIRIUS.controller;

import com.twilio.twiml.voice.Sms;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;
import org.w3c.dom.events.MouseEvent;
import tn.SIRIUS.api.SMS;
import tn.SIRIUS.controller.tutors.DashboardAdminHomePageController;
import tn.SIRIUS.controller.tutors.OperationAdminController;
import tn.SIRIUS.entities.Session;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GURDService;
import tn.SIRIUS.services.UserService;
import tn.SIRIUS.utils.MyDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private Connection con;
    public LoginController(){
        con = MyDB.getInstance().getCon();
    }

    @FXML
    private Stage stage;
    @FXML
    private Label CheckDetail;
    @FXML
    private AnchorPane successOperationContainer;
    @FXML
    private AnchorPane confirmationAnchor;
    @FXML
    private AnchorPane forgetPasswordAnchor;
    @FXML
    private CheckBox CheckPassword;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField Show;
    @FXML
    private Pane blurPane;

    @FXML
    private Button SignInButton;
@FXML
private TextField answerQuestion;
    @FXML
    private PasswordField passwordLabel;
    @FXML
    private Label questionText;

    @FXML
    private TextField userNameLabel;
    @FXML
    private ImageView Background;
    @FXML
    private Rectangle rectangle;



    @FXML
   public void handelVisibility(ActionEvent event) {
        if(CheckPassword.isSelected())
        {
            Show.setText(passwordLabel.getText());
            Show.setVisible(true);
            passwordLabel.setVisible(false);
            Show.requestFocus();
            Show.positionCaret(Show.getLength());
            return;
        }
        else{
            passwordLabel.setText(Show.getText());
            passwordLabel.setVisible(true);
            Show.setVisible(false);
            passwordLabel.requestFocus();
            passwordLabel.positionCaret(passwordLabel.getLength());
        }
    }
    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        UserService u = new UserService();

        Show.setText(passwordLabel.getText());
        String name = userNameLabel.getText();
        String password = passwordLabel.getText();
        String passwordSeen = Show.getText();

        String role = u.getUserRole(name);
        User user=new User(1,name,password,role);

        if ((name.isBlank() && password.isBlank()) || passwordSeen.isBlank()) {
            CheckDetail.setText("Username or Password fields are blank!");
        } else {
            System.out.println("Role retrieved from the database: " + role);

            if (u.isPasswordMatch(user)!=0) {
                int userId = u.isPasswordMatch(user);
                User loggedInUser = u.getUserById(userId);
                Session.setUser(loggedInUser);

                if (role != null) {
                    try {
                        FXMLLoader fxmlLoader;

                        if ("student".equals(role)) {
                            fxmlLoader = new FXMLLoader(OperationAdminController.class.getResource("/gui/students/homePage.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if ("admin".equals(role)) {
                            fxmlLoader = new FXMLLoader(getClass().getResource("/gui/tutors/dashboardAdminHomePage.fxml"));
                            Parent root = fxmlLoader.load();

                            Scene scene = new Scene(root, 1350, 720);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if ("teacher".equals(role)) {
                            fxmlLoader = new FXMLLoader(OperationAdminController.class.getResource("/gui/students/homePage.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                CheckDetail.setText("Password does not match");
            }
        }



    }
    private BoxBlur blurEffect = new BoxBlur();
    private void applyBlurEffect() {

        blurEffect.setWidth(100);
        blurEffect.setHeight(100);
        // Create a timeline to animate the blur effect
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(blurEffect.widthProperty(), 1000),
                        new KeyValue(blurEffect.heightProperty(),1000 )
                )
        );
        blurPane.setEffect(blurEffect);


        // Play the timeline
        timeline.play();
    }

    @FXML
    public void signUpForum(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("/gui/SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void forgetPassword(ActionEvent event) throws SQLException {
        UserService u = new UserService();
        forgetPasswordAnchor.setVisible(false);
        successOperationContainer.setVisible(false);
        confirmationAnchor.setVisible(true);
        questionText.setText(u.getquestion(userNameLabel.getText()));

    }
    @FXML
    private Label timerLabel;
    @FXML
    private PasswordField codeLabel;
    private int secondsElapsed = 30;
    @FXML
    private AnchorPane changePassword;
@FXML
public void closeForgetPassword(ActionEvent event) {
    forgetPasswordAnchor.setVisible(false);
    successOperationContainer.setVisible(false);
    confirmationAnchor.setVisible(false);
}

        @FXML
        private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;
    @FXML
    public void UpdatePassword(ActionEvent event) {
        if(newPassword.getText().equals(confirmNewPassword.getText())){
            UserService u = new UserService();
           GURDService g = new GURDService();
            String password = String.valueOf(newPassword.getText().hashCode());
            User user1 = u.getUserByFirstName(userNameLabel.getText());

            if(user1 != null){
                User user= new User(user1.getId(),user1.getFirstName(),user1.getLastName(),user1.getNumber(),user1.getEmail(),user1.getGender(),password,user1.getImage());
                g.update(user);
                changePassword.setVisible(false);
                successOperationContainer.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> successOperationContainer.setVisible(false)));
                timeline.setCycleCount(1);
                timeline.play();
                confirmationAnchor.setVisible(false);
                forgetPasswordAnchor.setVisible(false);
                changePassword.setVisible(false);
            }

        }
    }
    @FXML
    public void confirmCode(ActionEvent event) throws SQLException {

        if(resetCode.equals(codeLabel.getText())){
            changePassword.setVisible(true);

    }

    }
    private String resetCode = generateRandomNumber();
    @FXML
    public void confirmAnswer(ActionEvent event) throws SQLException {
        UserService u = new UserService();
         // Implement this method to generate a unique code

        if(u.confirmAnswer(questionText.getText(),answerQuestion.getText())) {
            try {
                SMS.setAccountSID("ACd3443809359cb57f1af6d42fe97e5ef5");
                SMS.setAuthToken("ad6f55c5405874b91ea4847008b89db5");
                String recipientPhoneNumber = "+21656330810";
                String senderPhoneNumber = "+16364890351";
                String message = "Use this code to reset your password.\n Your code is: " + resetCode + "\n Remember this code is valid for 30 seconds.";
                SMS.sendSMS(recipientPhoneNumber, senderPhoneNumber, message);
                forgetPasswordAnchor.setVisible(true);
                confirmationAnchor.setVisible(false);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event1 -> {
                    secondsElapsed--;
                    timerLabel.setText(secondsElapsed + " seconds");
                    if(secondsElapsed == 0){
                        forgetPasswordAnchor.setVisible(false);
                        confirmationAnchor.setVisible(false);
                    }
                }));
                timeline.setCycleCount(30);
                timeline.play();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }






    public String generateRandomNumber() {
        Random random = new Random();
        int rand=1000000+random.nextInt(99999999);
        String numberString = String.valueOf(rand);
        StringBuilder formattedNumber = new StringBuilder();
        for (int i = 0; i < numberString.length(); i++) {
            formattedNumber.append(numberString.charAt(i));
            if ((i + 1) % 2 == 0 && i < numberString.length() - 1) {
                formattedNumber.append("-");
            }
        }
       String randString = formattedNumber.toString();
        return randString;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      forgetPasswordAnchor.setVisible(false);
      confirmationAnchor.setVisible(false);
        confirmationAnchor.setVisible(false);
        blurPane.setVisible(true);
        applyBlurEffect();
        changePassword.setVisible(false);



    }



}