package tn.SIRIUS.controller.students;
import tn.SIRIUS.entities.Product;
import tn.SIRIUS.controller.students.ProductPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.SIRIUS.services.ProductService;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductPage implements Initializable{

    @FXML
    private Button AddNewProduct;

    @FXML
    private Button AddProductBtn;

    @FXML
    private AnchorPane AnchorFormProduct;

    @FXML
    private Button GoBackBtn;

    @FXML
    private AnchorPane HomeAnchorPane;

    @FXML
    private ChoiceBox<?> InputCategory;

    @FXML
    private TextField InputDescription;

    @FXML
    private TextField InputName;

    @FXML
    private TextField InputPrice;

    @FXML
    private VBox MenuVbox;

    @FXML
    private Pane PaneGroupProduct;

    @FXML
    private Button PictureBtn;

    @FXML
    private ImageView PictureChooser;

    @FXML
    private AnchorPane ProductAnchorPane;

    @FXML
    private AnchorPane ContainerAnchorpaneProduct;

    @FXML
    private GridPane GridContainerProduct;
    @FXML
    private ScrollPane ScrolPaneProduct;


    private List<Product> recentlyAdded;


    @FXML
    private void handleAddProductBtn(ActionEvent event) {
        AnchorFormProduct.setVisible(true);
        ContainerAnchorpaneProduct.setVisible(false);
    }

    @FXML
    private void handleGoBackBtn(ActionEvent event) {
        ContainerAnchorpaneProduct.setVisible(true);
        AnchorFormProduct.setVisible(false);

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        int column = 0;
        int row = 1;
        for (Product product : recentlyAdded) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProduct.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProduct itemProduct = fxmlLoader.getController();
                itemProduct.setData(product);

                if (column == 3) {
                    column = 0;
                    ++row;
                }
                GridContainerProduct.add(boxProduct, column++, row);
                GridContainerProduct.setMargin(boxProduct, new Insets(10));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
    }


    @FXML
    private void handlePictureBtn(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            PictureChooser.setImage(image);
        }
    }

    private List<Product> recentlyAdded()
    {
        List<Product> productList = new ArrayList<>();
        ProductService productService = new ProductService();
        productList = productService.getAll();
        return productList;
    }

    @FXML
    private void AddNewProduct(ActionEvent event) {
        try {
            String name = InputName.getText();
            String description = InputDescription.getText();
            float price = Float.parseFloat(InputPrice.getText());

            String image = PictureChooser.getImage().getUrl().toString();

            ProductService productService = new ProductService();
            Product product = new Product(0, name, description, image, price, 1, "");

            int result = productService.add(product);

            if (result == 1) {
                System.out.println("Product added successfully.");
            } else {

                System.out.println("Failed to add the product.");
            }
        } catch (NumberFormatException ex) {

            System.out.println("Invalid price format. Please enter a valid number.");
        } catch (Exception ex) {

            System.out.println("An error occurred while adding the product: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
