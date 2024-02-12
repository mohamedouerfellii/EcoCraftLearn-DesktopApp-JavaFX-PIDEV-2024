package tn.SIRIUS.controller.students;

import tn.SIRIUS.entities.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.util.ResourceBundle;

public class ItemProduct {

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
    private ImageView ImgProduct;
    @FXML
    private Rectangle TriangleImageProduct;

    public void setData(Product product) {
        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));
        ImgProduct.setImage(img1);
        NameProduct.setText(product.getName());
        DescProduct.setText(product.getDescription());
        float price = product.getPrice();
        Priceproduct.setText(price + "  DT");
    }


}