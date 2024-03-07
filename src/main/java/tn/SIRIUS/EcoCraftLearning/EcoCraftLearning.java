package tn.SIRIUS.EcoCraftLearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;


public class EcoCraftLearning extends Application {
    //HomePageController homePageController;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EcoCraftLearning.class.getResource("/gui/students/homePage.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(EcoCraftLearning.class.getResource("/gui/tutors/dashboardTutorHomePage.fxml"));
        Parent root = fxmlLoader.load();
        //homePageController = fxmlLoader.getController();
        Scene scene = new Scene(root,1350,720);
        stage.setScene(scene);
        stage.setTitle("EcoCraft Learning");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/logoDark.png")));
        stage.setResizable(false);
        /*stage.setOnCloseRequest(e -> {
            if(homePageController != null) homePageController.closeClientThread();
            WhiteBoardServer.closeWhiteBoardServer();
        });*/
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
