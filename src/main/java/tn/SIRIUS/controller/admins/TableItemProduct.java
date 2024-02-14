package tn.SIRIUS.controller.admins;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tn.SIRIUS.entities.Product;

import java.util.List;


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
    @FXML
    private Label DateProduct;


    @FXML
    private Button DetailsBtnProduct;

    public List<Product> recentlyAdded;

    ProduitPageController produitPageController = new ProduitPageController();

    public void setData(Product product) {

       // BoxTableProduct.setUserData(product);

        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));

        
        ImageProduct.setImage(img1);
        NameProduct.setText(product.getName());
        DescProduct.setText(product.getDescription());
        DateProduct.setText(product.getAddDate());

        float price = product.getPrice();
        PriceProduct.setText(price + "  DT");

       DetailsBtnProduct.setOnMouseClicked(e ->  produitPageController.showProductDetails(product));

    }
/*
    @FXML
    void handeDeleteBtnProduct(ActionEvent event) {

        Product product = (Product) BoxTableProduct.getUserData();

        if (product != null) {
            int productId = product.getIdProduct();
            ProductService productService = new ProductService();
            boolean success = productService.delete(productId);
            if (success) {
                System.out.println("Le produit a été supprimé avec succès.");

            } else {
                System.out.println("Échec de la suppression du produit.");
            }
        } else {
            System.out.println("Aucun produit associé à cet élément.");
        }
    }

*/

    public void setProduitPageController(ProduitPageController produitPageController) {
        this.produitPageController = produitPageController;
    }



}






