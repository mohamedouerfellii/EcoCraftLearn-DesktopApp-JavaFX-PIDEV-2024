package tn.SIRIUS.controller.admins;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.SIRIUS.entities.Product;
import tn.SIRIUS.services.ProductService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProduitPageController implements Initializable {



    @FXML
    private AnchorPane AnchorPanedetailsProduct;

    @FXML
    private AnchorPane AnchorPaneProductBack;

    @FXML
    private ImageView ImageProductDetails;
    @FXML
    private Label IdProductdetails;


    @FXML
    private Label NameProductdetails;

    @FXML
    private Label PriceProductDetails;
    @FXML
    private Label DescProductdetails;

    @FXML
    private Label dateProductDetails;

    @FXML
    private VBox VboxTableProduct;
    @FXML
    private Button GoBackBtn;

    public List<Product> recentlyAdded;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
            for (int i=0; i<recentlyAdded.size(); i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/TableItemProduct.fxml"));
                HBox BoxTableProduct = fxmlLoader.load();
                TableItemProduct tableItemProduct = fxmlLoader.getController();
                tableItemProduct.setProduitPageController(this);
                tableItemProduct.setData(recentlyAdded.get(i));
                VboxTableProduct.getChildren().add(BoxTableProduct);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnchorPaneProductBack.setVisible(true);
        AnchorPanedetailsProduct.setVisible(false);
    }



    public void refresh()
    {   ProductService productService = new ProductService();
          recentlyAdded = productService.getAll();
        VboxTableProduct.getChildren().clear();

        try {
            for (Product product : recentlyAdded)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/TableItemProduct.fxml"));
                HBox BoxTableProduct = fxmlLoader.load();
                TableItemProduct tableItemProduct = fxmlLoader.getController();
                tableItemProduct.setProduitPageController(this);
                tableItemProduct.setData(product);
                VboxTableProduct.getChildren().add(BoxTableProduct);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> recentlyAdded()
    {
        List<Product> productList = new ArrayList<>();
        ProductService productService = new ProductService();
        productList = productService.getAll();
        return productList;

    }




    public void showProductDetails(Product product) {

        AnchorPaneProductBack.setVisible(false);
        AnchorPanedetailsProduct.setVisible(true);
        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));

        IdProductdetails.setText(String.valueOf(product.getIdProduct()));
        ImageProductDetails.setImage(img1);
        NameProductdetails.setText(product.getName());
        DescProductdetails.setText(product.getDescription());
        dateProductDetails.setText(product.getAddDate());
        float price = product.getPrice();
        PriceProductDetails.setText(price + "  DT");

    }


    @FXML
    void GoBackBtn(ActionEvent event) {
        AnchorPaneProductBack.setVisible(true);
        AnchorPanedetailsProduct.setVisible(false);
    }



    @FXML
    void handleDeleteBtnProduct(ActionEvent event) {

        ProductService productService = new ProductService();
        if (productService.delete(Integer.valueOf(IdProductdetails.getText())))
        {
            refresh();
            AnchorPaneProductBack.setVisible(true);
            AnchorPanedetailsProduct.setVisible(false);
        }

    }



}
