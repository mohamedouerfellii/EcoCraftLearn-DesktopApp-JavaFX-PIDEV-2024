package tn.SIRIUS.controller.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import tn.SIRIUS.entities.Carts;
import tn.SIRIUS.entities.Product;
import tn.SIRIUS.entities.SousCart;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CartsService;
import tn.SIRIUS.services.ProductService;
import tn.SIRIUS.services.SousCartService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductPage implements Initializable{

    private int valueQuantite =1;

    @FXML
    private AnchorPane AnchorPaneQuantite;


    @FXML
    private Button AddNewProduct;
    @FXML
    private Button passerCommandeBtn;
    @FXML
    private Button hideCartBtn;
    @FXML
    private Button AddProductBtn;

    @FXML
    private Button AddReview;

    @FXML
    private AnchorPane AnchorFormProduct;

    @FXML
    private AnchorPane AnchorPaneDetailsProctClient;

    @FXML
    private AnchorPane CartAnchorPane;

    @FXML
    private Button CloseCart;

    @FXML
    private Button ConfirmeCartBtn;

    @FXML
    private AnchorPane ContainerAnchorpaneProduct;

    @FXML
    private Label DescProductdetailsClient;

    @FXML
    private Button DetailGoBackBtn;

    @FXML
    private Button GoBackBtn;

    @FXML
    private GridPane GridContainerProduct;

    @FXML
    private AnchorPane HomeAnchorPane;

    @FXML
    private Button HomeBtn;

    @FXML
    private Button HomeBtn1;

    @FXML
    private Button HomeBtn2;

    @FXML
    private Button HomeBtn3;

    @FXML
    private Button HomeBtn4;

    @FXML
    private Button HomeBtn5;

    @FXML
    private Button HomeBtn6;

    @FXML
    private TextField InputDescription;

    @FXML
    private TextField InputName;

    @FXML
    private TextField InputPrice;

    @FXML
    private Label Labelvalue;

    @FXML
    private VBox MenuVbox;

    @FXML
    private VBox cartItemsContainer;

    @FXML
    private Label NameProductdetailsClient;

    @FXML
    private Label NomProductLabel;

    @FXML
    private Label NumeroProductLabel;

    @FXML
    private Pane PaneGroupProduct;

    @FXML
    private Button PictureBtn;

    @FXML
    private ImageView PictureChooser;

    @FXML
    private Label PriceProductDetailsClient;

    @FXML
    private Rectangle RectangleDetailProduct;

    @FXML
    private ScrollPane ScrolPaneProduct;

    @FXML
    private Label TotalPriceLabel;

    @FXML
    private Label totalPriceCartLabel;

    @FXML
    private VBox VboxContainerReview;

    @FXML
    private Label dateProductDetailsClient;

    @FXML
    private Button decrementValue;

    @FXML
    private Button incrementValue;
    private List<Product> recentlyAdded;
    @FXML
    private Button openCart;
    @FXML
    private AnchorPane cartAnchorepaneContainer;

    private float initialPrice;
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


    User user = new User(1,"ssss","mohamed","ssssss@",12345,"Admin","male",1,1,"");

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
                itemProduct.setProductPage(this);
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
        CartAnchorPane.setVisible(false);
        cartAnchorepaneContainer.setVisible(false);
        openCart.setOnAction(event -> showSousCarts());
        hideCartBtn.setOnAction(event -> cartAnchorepaneContainer.setVisible(false));
        passerCommandeBtn.setOnAction(event -> {
            CartsService cartsservice = new CartsService();
            Carts carts = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
            cartsservice.updateIsConfirmed(carts.getIdCarts());
        });
    }


    public void ShowProductList()
    {
        ProductService productService = new ProductService();
        recentlyAdded = productService.getAll();
        GridContainerProduct.getChildren().clear();

        recentlyAdded = new ArrayList<>(recentlyAdded());
        int column = 0;
        int row = 1;

        for (Product product : recentlyAdded) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProduct.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProduct itemProduct = fxmlLoader.getController();
               // itemProduct.setProductPage(this);
                itemProduct.setData(product);

                if (column == 2) {
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
        if (selectedFile != null)
        {
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
                ShowProductList();
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

    @FXML
    void handleOnDetailGoBackBtnClicked(ActionEvent event) {
        PaneGroupProduct.setVisible(true);
        AnchorPaneDetailsProctClient.setVisible(false);
    }
    public void showProductDetailsClient(Product product) {

        AnchorPaneDetailsProctClient.setVisible(true);
        PaneGroupProduct.setVisible(false);
        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));

      //IdProductdetailsClient.setText(String.valueOf(product.getIdProduct()));
        RectangleDetailProduct.setFill(new ImagePattern(img1));
        NameProductdetailsClient.setText(product.getName());
        DescProductdetailsClient.setText(product.getDescription());
       //dateProductDetailsClient.setText(product.getAddDate());
        float price = product.getPrice();
        PriceProductDetailsClient.setText(price + "  DT");



    }













    public void remplireCartProduct(Product product) {
        CartAnchorPane.setVisible(true);
        NumeroProductLabel.setText(String.valueOf(product.getIdProduct()));
        NomProductLabel.setText(product.getName());
        initialPrice = product.getPrice(); // Stockez le prix initial
        TotalPriceLabel.setText(String.valueOf(initialPrice)); // Affichez le prix initial dans le label du prix total
    }

    @FXML
    void decrementValue(ActionEvent event) {
        if (valueQuantite > 1) {
            valueQuantite--; // Décrémentez la quantité
            updateValueLabel(); // Mettez à jour l'affichage de la quantité
            updateTotalPriceLabel(false); // Mettez à jour le prix total
        }
    }

    public void updateTotalPriceLabel(boolean increase) {
        float totalPrice = Float.parseFloat(TotalPriceLabel.getText()); // Obtenez le prix total actuel

        // Mettez à jour le prix total en fonction de l'opération (augmentation ou diminution)
        if (increase) {
            totalPrice += initialPrice; // Si l'opération est une augmentation, ajoutez le prix initial au prix total
        } else {
            totalPrice -= initialPrice; // Si l'opération est une diminution, soustrayez le prix initial au prix total
        }
        TotalPriceLabel.setText(String.valueOf(totalPrice)); // Mettez à jour le label du prix total
    }

    @FXML
    void incrementValue(ActionEvent event) {
        valueQuantite++; // Incrémentez la quantité
        updateValueLabel(); // Mettez à jour l'affichage de la quantité
        updateTotalPriceLabel(true); // Mettez à jour le prix total
    }

    private void updateValueLabel() {
        Labelvalue.setText(String.valueOf(valueQuantite));
        if (valueQuantite == 0) {
            Labelvalue.setText("");
        }
    }




    @FXML
    void handleConfirmeCartClicked(ActionEvent event) {
        try {
            int id_produit = Integer.parseInt(NumeroProductLabel.getText());
            float totalPrice = Float.parseFloat(TotalPriceLabel.getText());
            int quantite = valueQuantite;
            CartsService cartsservice = new CartsService();
            Carts cart = new Carts();
            SousCart sousCart = new SousCart();
            SousCartService sousCartService = new SousCartService();
            if( cartsservice.getByIdIfNotConfirmed(user.getIdUser()) != null){
                cart = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
                sousCart = new SousCart(0, cart.getIdCarts(), id_produit, quantite);
                sousCartService.add(sousCart);
                cartsservice.updateTotalPrice(cart.getIdCarts(), cart.getTotalPrice() + totalPrice);


            }else {
                 cart = new Carts(1, 0, 0 , user.getIdUser());
                if (cartsservice.add(cart)==1) {
                cart = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
                sousCart = new SousCart(0, cart.getIdCarts(), id_produit, quantite);
                sousCartService.add(sousCart);
                cartsservice.updateTotalPrice(cart.getIdCarts(), cart.getTotalPrice() + totalPrice);

            }}
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        valueQuantite=1;
        updateValueLabel();
        CartAnchorPane.setVisible(false);

    }


    @FXML
    void handleOnCloseCartAction(ActionEvent event) {
        CartAnchorPane.setVisible(false);
        PaneGroupProduct.setVisible(true);
    }


    public  void showSousCarts(){
        cartItemsContainer.getChildren().clear();
        CartsService cartsService = new CartsService();
        Carts carts = cartsService.getByIdIfNotConfirmed(user.getIdUser());
        SousCartService sousCartService = new SousCartService();
       if(carts != null){ List<SousCart> sousCarts = sousCartService.getSousCartsWithProductInfo(carts.getIdCarts());
        for (SousCart sousCart : sousCarts) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/sousCartItem.fxml"));
                Parent p = fxmlLoader.load();
                SousCartItem sousCartItem = fxmlLoader.getController();
                sousCartItem.setProductPage(this);
                sousCartItem.setSousCartProduct(sousCart);
                cartItemsContainer.getChildren().add(p);
                cartAnchorepaneContainer.setVisible(true);
                totalPriceCartLabel.setText(String.valueOf(carts.getTotalPrice()) + " DT");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}
        } else System.out.println("vide");
    }


}
