package tn.SIRIUS.EcoCraftLearning;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.Objects;

public class EcoCraftLearning extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/admins/ProduitPageController.fxml")));
        stage.setScene(new Scene(root,1350,720));
        stage.setTitle("ECO CRAFT");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}