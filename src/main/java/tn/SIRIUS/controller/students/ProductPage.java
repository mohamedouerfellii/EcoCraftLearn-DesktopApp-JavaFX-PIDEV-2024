package tn.SIRIUS.controller.students;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import tn.SIRIUS.entities.*;
import tn.SIRIUS.services.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductPage implements Initializable{


    @FXML
    private Line lineMiniDetailsBtn;

    @FXML
    private Line lineMiniDetailsBtn1;
    @FXML
    private Button miniDetailsBtn;

    @FXML
    private Button miniDescriptionBtn;

    @FXML
    private AnchorPane AnchorPanedetailProduct;


    @FXML
    private AnchorPane productDescriptionLeft;

    @FXML
    private Button OrderMapBtn;


    @FXML
    private WebView WebViewMapOrder;


    @FXML
    private Pane mapContainer;

    @FXML
    private AnchorPane AnchorQuantitéInsuffisante;

    @FXML
    private Button VideCartBtn;
    private int valueQuantite =1;

    @FXML
    private AnchorPane AnchorPaneQuantite;

    @FXML
    private AnchorPane AnchorPaneFormCommande;

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
    private Text DescProductdetailsClient;


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
    private TextArea InputDescription;

    @FXML
    private TextField InputName;

    @FXML
    private TextField InputPrice;

    @FXML
    private TextField InputQuantite;

    @FXML
    private Label Labelvalue;

    @FXML
    private VBox MenuVbox;

    @FXML
    private VBox cartItemsContainer;

    @FXML
    private Text NameProductdetailsClient;
    @FXML
    private Text QuantiteProductDetailclient;
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
    private Text PriceProductDetailsClient;

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
    private Text dateProductDetailsClient;

    @FXML
    private Button decrementValue;

    @FXML
    private Button incrementValue;
    private List<Product> recentlyAdded;
    @FXML
    private Button openCart;
    @FXML
    private AnchorPane cartAnchorepaneContainer;

    @FXML
    private AnchorPane CarteVide;

    @FXML
    private TextField InputPhoneOrder;

    @FXML
    private TextField InputEmailOrder;

    @FXML
    private TextField InputCityOrder;

    @FXML
    private Button PasserOrderBtn;
    private float initialPrice;

    private WebView webView;
    private WebEngine webEngine;
    @FXML
    private TextField InputSearchProduct;


    @FXML
    private AnchorPane AnchorPanerating;

    @FXML
    private TextField InputReview;

    @FXML
    private Rating InputRatingStar;
    @FXML
    private Button Passerrating;


@FXML
private VBox Containeritemreview;
    private List <Productsevaluation> Listreview;

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
        AnchorPaneFormCommande.setVisible(false);

        CartAnchorPane.setVisible(false);
        cartAnchorepaneContainer.setVisible(false);
        openCart.setOnAction(event -> showSousCarts());
        hideCartBtn.setOnAction(event -> cartAnchorepaneContainer.setVisible(false));


        passerCommandeBtn.setOnAction(event -> {
            AnchorPaneFormCommande.setVisible(true);

        });

        PasserOrderBtn.setOnAction(event -> {

                if (PasserCommande()) {
                    CartsService cartsservice = new CartsService();
                    Carts carts = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
                    cartsservice.updateIsConfirmed(carts.getIdCarts());
                    AnchorPaneFormCommande.setVisible(false);
                }
        });


        VideCartBtn.setOnAction(event -> {

            CartsService cartsservice = new CartsService();
            Carts carts = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
            cartsservice.delete(carts.getIdCarts());
            cartAnchorepaneContainer.setVisible(false);

        });







        OrderMapBtn.setOnAction(event -> {

          /*  WebEngine webEngine = webView.getEngine();

            webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue == Worker.State.SUCCEEDED) {
                    webEngine.executeScript("mapboxgl.accessToken = 'pk.eyJ1IjoiemllZGdhY2hpdGEiLCJhIjoiY2x0MG4yNWgwMTNrYTJqbzIxY2I1ZHZsNCJ9.V9zXjftAvkRjP5PZ9-a25g';" +
                            "var map = new mapboxgl.Map({" +
                            "container: 'map', " +
                            "style: 'mapbox://styles/mapbox/streets-v11', " +
                            "center: [-74.5, 40], " +
                            "zoom: 9 " +
                            "});");
                }
            });

            webEngine.loadContent("<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<script src='https://api.mapbox.com/mapbox-gl-js/v2.6.1/mapbox-gl.js'></script>" +
                    "<link href='https://api.mapbox.com/mapbox-gl-js/v2.6.1/mapbox-gl.css' rel='stylesheet' />" +
                    "</head>" +
                    "<body>" +
                    "<div id='map' style='width: 100%; height: 100%;'></div>" +
                    "</body>" +
                    "</html>");

            mapContainer.getChildren().add(webView);*/


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
               //itemProduct.setProductPage(this);
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


    public void ShowProductListsearch(List<Product> products) {
        GridContainerProduct.getChildren().clear();

        int column = 0;
        int row = 1;

        for (Product product : products) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProduct.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProduct itemProduct = fxmlLoader.getController();
                //itemProduct.setProductPage(this);
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
            int quantite= Integer.parseInt(InputQuantite.getText());

            String image = PictureChooser.getImage().getUrl().toString();

            ProductService productService = new ProductService();

            Product product = new Product(0, name, description, image, price, 1, "",quantite);
            int result = productService.add(product);

            if (result == 1) {
                Notifications.create()
                        .title("Product Added")
                        .text("Product :" + product.getName() + " has been added successfully.")
                        .showInformation();
                         ShowProductList();
            } else {
                Notifications.create()
                        .text("Product :" + product.getName() + "Failed to add the product.")
                        .showInformation();;
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


       AnchorPanedetailProduct.setVisible(true);
       lineMiniDetailsBtn.setVisible(true);
        AnchorPaneDetailsProctClient.setVisible(true);
        PaneGroupProduct.setVisible(false);
        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));



      //IdProductdetailsClient.setText(String.valueOf(product.getIdProduct()));
       RectangleDetailProduct.setFill(new ImagePattern(img1));
       NameProductdetailsClient.setText(product.getName());
       DescProductdetailsClient.setText(product.getDescription());
       dateProductDetailsClient.setText(product.getAddDate());

       QuantiteProductDetailclient.setText(String.valueOf(product.getQuantite()));
       float price = product.getPrice();
       PriceProductDetailsClient.setText(price + "  TND");



       Passerrating.setOnAction(e -> {

           int productid = product.getIdProduct();
           String review = InputReview.getText();
           double rating =InputRatingStar.getRating();
           ProductevaluationService productevaluationService = new ProductevaluationService();
           Productsevaluation productsevaluation = new Productsevaluation(0,rating, review, "", productid, user.getIdUser(),0);
           productevaluationService.add(productsevaluation);

       });



       ProductevaluationService productevaluationService = new ProductevaluationService();
       Listreview = productevaluationService.getAllByIdProductNotConfirmed(product.getIdProduct());
       Containeritemreview.getChildren().clear();
       try {
           for (Productsevaluation productsevaluation : Listreview)
           {
               FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemReviewProduct.fxml"));
               HBox HboxContainerReview = fxmlLoader.load();
               ItemReviewProduct itemReviewProduct = fxmlLoader.getController();

               itemReviewProduct.setData(productsevaluation);
               Containeritemreview.getChildren().add(HboxContainerReview);
           }
       }
       catch (IOException e) {
           throw new RuntimeException(e);
       }





    }


    public void remplireCartProduct(Product product) {
        CartAnchorPane.setVisible(true);
        NumeroProductLabel.setText(String.valueOf(product.getIdProduct()));
        NomProductLabel.setText(product.getName());
        initialPrice = product.getPrice();
        TotalPriceLabel.setText(String.valueOf(initialPrice));
    }

    @FXML
    void decrementValue(ActionEvent event) {
        if (valueQuantite > 1) {
            valueQuantite--;
            updateValueLabel();
            updateTotalPriceLabel(false);
        }
    }

    public void updateTotalPriceLabel(boolean increase) {
        float totalPrice = Float.parseFloat(TotalPriceLabel.getText());


        if (increase) {
            totalPrice += initialPrice;
        } else {
            totalPrice -= initialPrice;
        }
        TotalPriceLabel.setText(String.valueOf(totalPrice));
    }

    @FXML
    void incrementValue(ActionEvent event) {
        valueQuantite++;
        updateValueLabel();
        updateTotalPriceLabel(true);
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

            ProductService productService = new ProductService();
            int availableQuantity = productService.getProductQuantityById(id_produit);

            if (availableQuantity != -1 && quantite <= availableQuantity) {
                CartsService cartsservice = new CartsService();
                Carts cart = new Carts();
                SousCart sousCart = new SousCart();
                SousCartService sousCartService = new SousCartService();
                if (cartsservice.getByIdIfNotConfirmed(user.getIdUser()) != null) {
                    cart = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
                    sousCart = new SousCart(0, cart.getIdCarts(), id_produit, quantite);
                    sousCartService.add(sousCart);
                    cartsservice.updateTotalPrice(cart.getIdCarts(), cart.getTotalPrice() + totalPrice);
                } else {
                    cart = new Carts(1, 0, 0, user.getIdUser());
                    if (cartsservice.add(cart) == 1) {
                        cart = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
                        sousCart = new SousCart(0, cart.getIdCarts(), id_produit, quantite);
                        sousCartService.add(sousCart);
                        cartsservice.updateTotalPrice(cart.getIdCarts(), cart.getTotalPrice() + totalPrice);
                    }
                }
            } else {
                AnchorQuantitéInsuffisante.setVisible(true);
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> AnchorQuantitéInsuffisante.setVisible(false)));
                timeline.setCycleCount(1);
                timeline.play();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        valueQuantite = 1;
        updateValueLabel();
        CartAnchorPane.setVisible(false);
    }


    @FXML
    void handleOnCloseCartAction(ActionEvent event) {

        CartAnchorPane.setVisible(false);
        PaneGroupProduct.setVisible(true);

    }
    @FXML
    void handleCloserorder(ActionEvent event) {

        AnchorPaneFormCommande.setVisible(false);
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
        }else {
           CarteVide.setVisible(true);
           Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> CarteVide.setVisible(false)));
           timeline.setCycleCount(1);
           timeline.play();
        }
    }

    public boolean PasserCommande() {

        try {
            String email = InputEmailOrder.getText();
            String city = InputCityOrder.getText();
            String phoneInput = InputPhoneOrder.getText();
            if (phoneInput.length() != 8 || !phoneInput.matches("\\d{8}")) {
                System.out.println("Invalid phone number ");
                return false;
            }
            int phone = Integer.parseInt(phoneInput);
            CartsService cartsservice = new CartsService();
            Carts carts = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
            int cartId = carts.getIdCarts();
            CommandesService commandesService = new CommandesService();
            Commandes commandes = new Commandes(0, user.getIdUser(), "", cartId, email, city, phone,"",0,0);
            int result = commandesService.add(commandes);
            if (result == 1) {
                System.out.println("SUCCESS");
                CarteVide.setVisible(false);
                return true;
            } else {
                System.out.println("FAILED");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("An error occurred while placing the order: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }


  public void deleteSouscarte(SousCart sousCart) {

          SousCartService sousCartService = new SousCartService();
          sousCartService.deleteSousCarts(sousCart.getId_SousCarts(), sousCart.getIdCart());

          CartsService cartsservice = new CartsService();
          Carts cart = new Carts();

          cart = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
          float totalPrice = cart.getTotalPrice() - sousCart.getQuantiteProduct() * sousCart.getProduct().getPrice();
          cartsservice.updateTotalPrice(cart.getIdCarts(), totalPrice);
          showSousCarts();


  }


    public void showAllProducts(String searchText) {
        ProductService productService = new ProductService();
        recentlyAdded = productService.searchProducts(searchText);
        ShowProductListsearch(recentlyAdded);
    }

    @FXML
    private void handlesearch() {
        String searchText = InputSearchProduct.getText().trim();
        GridContainerProduct.getChildren().clear();
        showAllProducts(searchText);
    }
   @FXML
    private void handlecloserating(ActionEvent event) {
       AnchorPanerating.setVisible(false);

    }

    @FXML
    private void handleopenrating(ActionEvent event) {
        AnchorPanerating.setVisible(true);

    }

    @FXML
    private void handleDetailsBtnright(ActionEvent event) {
        AnchorPanedetailProduct.setVisible(true);
        lineMiniDetailsBtn.setVisible(true);
         lineMiniDetailsBtn1.setVisible(false);
        productDescriptionLeft.setVisible(false);
    }

    @FXML
    private void handleDetailsBtnleft(ActionEvent event) {
        productDescriptionLeft.setVisible(true);
        AnchorPanedetailProduct.setVisible(false);
        lineMiniDetailsBtn.setVisible(false);
        lineMiniDetailsBtn1.setVisible(true);

    }


}
