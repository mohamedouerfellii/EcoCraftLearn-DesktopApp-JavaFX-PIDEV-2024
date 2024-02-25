package tn.SIRIUS.EcoCraftLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tn.SIRIUS.controller.students.PostItem;

import java.io.IOException;

public class EcoCraftLearning extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/students/homePage.fxml"));
        Scene scene = new Scene(loader.load(), 1350,720);
        stage.setScene(scene);
        stage.setTitle("Eco Craft");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logoDark.png")));
        stage.setResizable(false);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
