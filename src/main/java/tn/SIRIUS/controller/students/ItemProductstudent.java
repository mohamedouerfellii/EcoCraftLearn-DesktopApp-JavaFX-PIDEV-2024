package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.Rating;
import tn.SIRIUS.entities.Product;
import tn.SIRIUS.services.ProductService;

public class ItemProductstudent {

    private int valueQuantite=0;

    @FXML
    private Button AddToCartBtn;


    @FXML
    private VBox BoxProduct;

    @FXML
    private Button BtnAcheter;

    @FXML
    private Label QuantityProduct;

    @FXML
    private AnchorPane ItemProductPane;

    @FXML
    private Label NameProduct;

    @FXML
    private Label Priceproduct;

    @FXML
    private Rating RateProduct;

    @FXML
    private Label nbrrating;

    @FXML
    private Rectangle RectangleImageProduct;
    ProductPagestudent productPage = new ProductPagestudent();

    public void setData(Product product) {



        RectangleImageProduct.setFill(new ImagePattern(new Image("file:///" +product.getImage().replace("\\","/"))));
        NameProduct.setText(product.getName());
        int q = product.getQuantite();
        QuantityProduct.setText("Quantity available : "+q);
        float price = product.getPrice();
        Priceproduct.setText(price + "  DT");
        RectangleImageProduct.setOnMouseClicked( e -> productPage.showProductDetailsClient(product));
        AddToCartBtn.setOnMouseClicked(e -> {
            productPage.remplireCartProduct(product);
            productPage.checkQuantity(product);
        });

        ProductService productService = new ProductService();
        double somme =  productService.SommeRateofEvent(product.getIdProduct());
        int nbr = productService.countNBrOfRate(product.getIdProduct());
        double moyen = somme/nbr;
        if (moyen == 0) {
            nbrrating.setText("("+nbr+")");

        } else {
            RateProduct.setRating(moyen);
            nbrrating.setText("("+nbr+")");
        }

    }

    public void setProductPage(ProductPagestudent productpage) {
       this.productPage= productpage;
    }




}