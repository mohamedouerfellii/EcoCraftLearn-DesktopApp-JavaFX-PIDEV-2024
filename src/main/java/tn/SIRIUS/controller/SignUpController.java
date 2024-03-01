package tn.SIRIUS.controller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mindrot.jbcrypt.BCrypt;
import tn.SIRIUS.controller.tutors.OperationAdminController;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GURDService;

public class SignUpController implements Initializable {
    @FXML
    private Button Cancel;

    @FXML
    private AnchorPane fieldEmptyAnchor;
    @FXML
    private Label done;
    @FXML
    private AnchorPane successOperationContainer;

    @FXML
    private CheckBox CheckPassword1;

    @FXML
    private TextField Email;

    @FXML
    private TextField FirtsName;

    @FXML
    private ChoiceBox<String> GenderChoise;

    @FXML
    private TextField LastName;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private ChoiceBox<String> RoleChoise;
    @FXML
    private ChoiceBox<String> questionChoise;

    @FXML
    private TextField AnswerLabel;

    @FXML
    private TextField Show1;

    @FXML
    private AnchorPane SignUpAnchorPane;

    @FXML
    private Button SignUpButton;

    @FXML
    private Button chooseImage;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField passwordLabel1;

    @FXML
    private ImageView userImageView;
    @FXML
    private Label passwordNotConfirmed;
    private String addUserImgPath;

    @FXML
    private void handelVisibility1(ActionEvent event) {
        if(CheckPassword1.isSelected())
        {
            Show1.setText(passwordLabel1.getText());
            Show1.setVisible(true);
            passwordLabel1.setVisible(false);
            Show1.requestFocus();
            Show1.positionCaret(Show1.getLength());
            return;
        }
        else{
            passwordLabel1.setText(Show1.getText());
            passwordLabel1.setVisible(true);
            Show1.setVisible(false);
            passwordLabel1.requestFocus();
            passwordLabel1.positionCaret(passwordLabel1.getLength());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldEmptyAnchor.setVisible(false);
        passwordNotConfirmed.setVisible(false);
        RoleChoise.setItems(FXCollections.observableArrayList("teacher", "student"));

        // Set a default choice
        RoleChoise.setValue("");

        // Event handler for choice selection
        RoleChoise.setOnAction(event -> {
            String selectedOption = RoleChoise.getValue();

        });
        GenderChoise.setItems(FXCollections.observableArrayList("Male", "Female"));

        // Set a default choice
        GenderChoise.setValue("");

        // Event handler for choice selection
        GenderChoise.setOnAction(event -> {
            String selectedOption =GenderChoise.getValue();

        });
        questionChoise.setItems(FXCollections.observableArrayList("What is your primary school name?", "What is your mother's name", "Who is your best teacher"));

        // Set a default choice
        questionChoise.setValue("");

        // Event handler for choice selection
        questionChoise.setOnAction(event -> {
            String selectedOption =questionChoise.getValue();

        });

    }
    @FXML
    public void cancelButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/gui/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            addUserImgPath=selectedFile.toURI().toString();
            Image image = new Image(addUserImgPath);
            userImageView.setImage(image);
        }
    }
    @FXML
    void addNewUser(ActionEvent event) throws IOException {
        String firstname = FirtsName.getText();
        String lastname = LastName.getText();
        String email = Email.getText();
        String password = String.valueOf(passwordLabel1.getText().hashCode());
        String question = questionChoise.getValue();
        String answer = AnswerLabel.getText();
        String roles = RoleChoise.getValue();
        String gender = GenderChoise.getValue();
        Show1.setText(passwordLabel1.getText());
        int number;
        if(confirmPassword.getText().isEmpty()|| !confirmPassword.getText().equals(passwordLabel1.getText()) || !confirmPassword.getText().equals(Show1.getText()))
        {

            passwordNotConfirmed.setVisible(true);
            passwordNotConfirmed.setText("Password Not Confirmed");
            Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(2000), e -> passwordNotConfirmed.setVisible(false)));
            timeline1.setCycleCount(1);
            timeline1.play();
        }
        if(PhoneNumber.getText().isEmpty())
        {
            number = 0;
        }
        else
            number=Integer.parseInt(PhoneNumber.getText());
        if(firstname.isEmpty()||lastname.isEmpty()||email.isEmpty()||password.isEmpty()||gender.isEmpty()||roles.isEmpty())
        {

            fieldEmptyAnchor.setVisible(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> fieldEmptyAnchor.setVisible(false)));
            timeline.setCycleCount(1);
            timeline.play();

        }
        else {
            GURDService u = new GURDService();

            User user = new User(firstname, lastname, number, email, gender, password, roles, addUserImgPath, answer, question);
            u.add(user);
            FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/gui/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }
}
