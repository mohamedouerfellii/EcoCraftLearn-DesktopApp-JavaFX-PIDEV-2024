package tn.SIRIUS.EcoCraftLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EcoCraftLearning extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EcoCraftLearning.class.getResource("/gui/tutors/dashboardTutorHomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),1350,720);
        stage.setTitle("First Try");
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
