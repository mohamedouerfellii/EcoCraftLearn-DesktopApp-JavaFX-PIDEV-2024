package tn.SIRIUS.controller.admins;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.SIRIUS.entities.Product;

public class TableItemProduct {
    @FXML
    private ImageView ImageProduct;
    @FXML
    private HBox BoxTableProduct;



    @FXML
    private Label DescProduct;

    @FXML
    private Label NameProduct;

    @FXML
    private Label PriceProduct;

    public void setData(Product product) {

        String imageUrl = product.getImage();
        Image img1 = new Image(getClass().getResourceAsStream(imageUrl));
        ImageProduct.setImage(img1);
        NameProduct.setText(product.getName());
        DescProduct.setText(product.getDescription());
        float price = product.getPrice();
        PriceProduct.setText(price + "  DT");
    }

}