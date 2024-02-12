package tn.SIRIUS.controller.students;
import tn.SIRIUS.entities.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;


import java.io.File;
import java.io.IOException;

public class FormProduct {

    private Pane PaneGroupProduct;

    @FXML
    private AnchorPane AnchorForm;

    @FXML
    private Button AddBtn;

    @FXML
    private Button GoBackBtn;

    @FXML
    private TextField InputDescription;

    @FXML
    private TextField InputName;

    @FXML
    private TextField InputPrice;

    @FXML
    private Button PictureBtn;

    @FXML
    private ImageView PictureChooser;


    @FXML
    private ChoiceBox<String> InputCategory;



}
