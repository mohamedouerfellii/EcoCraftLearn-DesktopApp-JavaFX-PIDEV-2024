package tn.SIRIUS.EcoCraftLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class EcoCraftLearning extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EcoCraftLearning.class.getResource("/gui/students/homePage.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(EcoCraftLearning.class.getResource("/gui/tutors/dashboardTutorHomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1350,720);
        stage.setScene(scene);
        stage.setTitle("EcoCraft Learning");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logoDark.png")));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
