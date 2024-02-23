package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import tn.SIRIUS.entities.Product;

public class ItemProduct {

    private int valueQuantite=0;

    @FXML
    private Button AddToCartBtn;


    @FXML
    private VBox BoxProduct;

    @FXML
    private Button BtnAcheter;

    @FXML
    private Label DescProduct;

    @FXML
    private AnchorPane ItemProductPane;

    @FXML
    private Label NameProduct;

    @FXML
    private Label Priceproduct;

    @FXML
    private Rectangle RectangleImageProduct;
    ProductPage productPage = new ProductPage();

    public void setData(Product product) {


        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));

        RectangleImageProduct.setFill(new ImagePattern(img1));
        NameProduct.setText(product.getName());
        DescProduct.setText(product.getDescription());
        float price = product.getPrice();
        Priceproduct.setText(price + "  DT");


        RectangleImageProduct.setOnMouseClicked( e -> productPage.showProductDetailsClient(product));
        AddToCartBtn.setOnMouseClicked(e -> {
            productPage.remplireCartProduct(product);
        });
    }

    public void setProductPage(ProductPage productpage) {
       this.productPage= productpage;
    }





}