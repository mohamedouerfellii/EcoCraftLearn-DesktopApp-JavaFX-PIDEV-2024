package tn.SIRIUS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.SIRIUS.controller.tutors.OperationAdminController;
import tn.SIRIUS.services.UserService;
import tn.SIRIUS.utils.MyDB;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
    private CheckBox CheckPassword;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField Show;

    @FXML
    private Button SignInButton;

    @FXML
    private PasswordField passwordLabel;

    @FXML
    private TextField userNameLabel;

    @FXML
    void handelVisibility(ActionEvent event) {
        if(CheckPassword.isSelected())
        {
            Show.setText(passwordLabel.getText());
            Show.setVisible(true);
            passwordLabel.setVisible(false);
            return;
        }
        else{
            passwordLabel.setText(Show.getText());
            passwordLabel.setVisible(true);
            Show.setVisible(false);
        }
    }

    @FXML
    public void onLoginButtonClick(ActionEvent event) {
        UserService u = new UserService();
        String name = userNameLabel.getText();
        String password = passwordLabel.getText();
        String role = u.getUserRole(name);

        if (name.isBlank() || password.isBlank()) {
            CheckDetail.setText("Username or Password fields are blank!");
        } else {
            System.out.println("Role retrieved from the database: " + role);

            if (u.isPasswordMatch(name, password)) {
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
                            fxmlLoader = new FXMLLoader(OperationAdminController.class.getResource("/gui/tutors/dashboardAdminHomePage.fxml"));
                            Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
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

    @FXML
    public void signUpForum(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("/gui/SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
