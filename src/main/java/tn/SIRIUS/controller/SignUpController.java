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
import tn.SIRIUS.controller.tutors.OperationAdminController;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GURDService;

public class SignUpController implements Initializable {
    @FXML
    private Button Cancel;

    @FXML
    private Label CheckDetails1;
    @FXML
    private Label done;

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
    private TextField Show1;

    @FXML
    private AnchorPane SignUpAnchorPane;
    @FXML
    private AnchorPane successOperationContainer;

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
    private String addUserImgPath;

    @FXML
    private void handelVisibility1(ActionEvent event) {
        if(CheckPassword1.isSelected())
        {
            Show1.setText(passwordLabel1.getText());
            Show1.setVisible(true);
            passwordLabel1.setVisible(false);
            return;
        }
        else{
            passwordLabel1.setText(Show1.getText());
            passwordLabel1.setVisible(true);
            Show1.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
        String roles = RoleChoise.getValue();
        String password = passwordLabel1.getText();

        String gender = GenderChoise.getValue();
        int number = Integer.parseInt(PhoneNumber.getText());

        GURDService u = new GURDService();
        User user = new User(firstname,lastname,number,email,gender,password,roles,addUserImgPath);
        u.add(user);
        FXMLLoader fxmlLoader = new FXMLLoader(OperationAdminController.class.getResource("/gui/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
