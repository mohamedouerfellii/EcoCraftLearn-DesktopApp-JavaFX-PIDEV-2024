package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import tn.SIRIUS.entities.SousCart;

public class SousCartItem {




    @FXML
    private Button DeleteBtnSousCart;

    @FXML
    private Rectangle sousCartProductImg;

    @FXML
    private Label sousCartProductName;

    @FXML
    private Label sousCartProductQuantite;

    @FXML
    private Label sousCartProductprice;

    public void setProductPage(ProductPage productPage) {
        this.productPage = productPage;
    }

    private ProductPage productPage;


    public void setSousCartProduct(SousCart sousCart) {


        if (sousCart.getProduct() != null) {

        sousCartProductImg.setFill(new ImagePattern(new Image("file:///" +sousCart.getProduct().getImage().replace("\\","/"))));

        float price = sousCart.getProduct().getPrice();

        sousCartProductprice.setText(price + " DT");
        sousCartProductName.setText(sousCart.getProduct().getName());
        int quantite = sousCart.getQuantiteProduct();

        sousCartProductQuantite.setText("Quantity : "+quantite);

            DeleteBtnSousCart.setOnMouseClicked(e -> {
                productPage.deleteSouscarte(sousCart);
            });

    }


    }



}
