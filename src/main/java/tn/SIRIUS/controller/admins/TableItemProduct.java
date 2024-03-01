package tn.SIRIUS.controller.admins;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import tn.SIRIUS.entities.Product;

import java.util.List;


public class TableItemProduct {

    @FXML
    private Rectangle RectangleImage;
    @FXML
    private HBox BoxTableProduct;

    @FXML
    private Label DescProduct;

    @FXML
    private Label NameProduct;

    @FXML
    private Label PriceProduct;
    @FXML
    private Label DateProduct;


    @FXML
    private Button DetailsBtnProduct;

    @FXML
    private Button qrcodeBtn;

    public List<Product> recentlyAdded;

    ProduitPageController produitPageController = new ProduitPageController();

    public void setData(Product product) {


        RectangleImage.setFill(new ImagePattern(new Image("file:///" +product.getImage().replace("\\","/"))));
        NameProduct.setText(product.getName());
      //  DescProduct.setText(product.getDescription());
        DateProduct.setText(product.getAddDate());
        float price = product.getPrice();
        PriceProduct.setText(price + "  DT");

        DetailsBtnProduct.setOnMouseClicked(e ->  produitPageController.showProductDetails(product));

        qrcodeBtn.setOnAction(e -> produitPageController.showQrCode(product));


    }

    public void setProduitPageController(ProduitPageController produitPageController) {
        this.produitPageController = produitPageController;
    }



}






