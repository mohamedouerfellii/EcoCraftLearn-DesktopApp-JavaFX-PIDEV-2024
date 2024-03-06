package tn.SIRIUS.controller.students;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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
import netscape.javascript.JSObject;
import org.controlsfx.control.Rating;
import tn.SIRIUS.entities.*;
import tn.SIRIUS.services.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductPagestudent implements Initializable{


    @FXML
    private AnchorPane SoldeOut;

   @FXML
   private Button BtnMycommande;


    @FXML
    private VBox VboxCommandeClient;

    @FXML
    private  AnchorPane AnchorPaneMyorders;


    @FXML
    private  AnchorPane quantitevide;

    @FXML
    private TextField Langitudde;

    @FXML
    private TextField Latitude;

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
    private Pane mapContainer;



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
    private TextField InputLongitude;

    @FXML
    private TextField InputLatitude;

    @FXML
    private Button PasserOrderBtn;
    private float initialPrice;




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

    public List<Commandes> ListOrderClient;

    private List<Product> TopRated;

    @FXML
    private Button TopRatedProduct;


    @FXML
    private Button AllProductBtn;


    @FXML
    private Button WinnerProductBtn;

    private List<Product> Winner;

    @FXML
    private WebView mapWebView;
    @FXML
    private Label nbrSourCart;

    @FXML
    private AnchorPane SuccesAddProduct;

    @FXML
    private AnchorPane failedAddProduct;

    @FXML
    private AnchorPane failedAddToCart;

    @FXML
    private AnchorPane insufficientquantity;
    @FXML
    private AnchorPane FailedRate;


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


    User user = new User(1,"Zied","mohamed","ssssss@",12345,"Admin","male",1,1,"");
    User user1 = new User(2,"aaa","aa","ssssss@",12345,"Admin","male",1,1,"");

    public void initialize(URL url, ResourceBundle resourceBundle) {


        recentlyAdded = new ArrayList<>(recentlyAdded());
        int column = 0;
        int row = 1;
        for (Product product : recentlyAdded) {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProductstudent.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProductstudent itemProduct = fxmlLoader.getController();
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


        BtnMycommande.setOnAction(event -> {
            AnchorPaneMyorders.setVisible(true);
            showMyOrders();

        });


        TopRatedProduct.setOnAction(event -> Toprated() );
        AllProductBtn.setOnAction(event -> ShowProductList());


        WinnerProductBtn.setOnAction(event ->  Winnerproduct () );

        lineMiniDetailsBtn.setVisible(true);

    }


    public void Toprated () {
        ProductService productService = new ProductService();
        TopRated = productService.TrieTopRatedProduts();
        GridContainerProduct.getChildren().clear();
        TopRated = new ArrayList<>(TopRated);
        int column = 0;
        int row = 1;

        for (Product product : TopRated) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProductstudent.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProductstudent itemProduct = fxmlLoader.getController();
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

    }

    public void Winnerproduct () {
        ProductService productService = new ProductService();
        Winner = productService.WinnerProducts();
        GridContainerProduct.getChildren().clear();
        Winner = new ArrayList<>(Winner);
        int column = 0;
        int row = 1;

        for (Product product : Winner) {

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProductstudent.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProductstudent itemProduct = fxmlLoader.getController();
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
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProductstudent.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProductstudent itemProduct = fxmlLoader.getController();
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
    }


    public void ShowProductListsearch(List<Product> products) {
        GridContainerProduct.getChildren().clear();

        int column = 0;
        int row = 1;

        for (Product product : products) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemProductstudent.fxml"));
                VBox boxProduct = fxmlLoader.load();

                ItemProductstudent itemProduct = fxmlLoader.getController();
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


 public void NotifSuccess()
 {
     SuccesAddProduct.setVisible(true);
     Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> SuccesAddProduct.setVisible(false)));
     timeline.setCycleCount(1);
     timeline.play();

 }

 public void NotifFailed()
 {
     failedAddProduct.setVisible(true);
     Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedAddProduct.setVisible(false)));
     timeline.setCycleCount(1);
     timeline.play();
 }

 public void NotifFailedAddToCart()
 {

     failedAddToCart.setVisible(true);
     Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> failedAddToCart.setVisible(false)));
     timeline.setCycleCount(1);
     timeline.play();

 }

 public void Notifinsuffisante()
 {

     insufficientquantity.setVisible(true);
     Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> insufficientquantity.setVisible(false)));
     timeline.setCycleCount(1);
     timeline.play();
 }
 public void NotifSoldeOut()
 {
     SoldeOut.setVisible(true);
     Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> SoldeOut.setVisible(false)));
     timeline.setCycleCount(1);
     timeline.play();
 }

    public void NotifFailedRate()
    {
        FailedRate.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> FailedRate.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }


    @FXML
    private void AddNewProduct(ActionEvent event) {
        try {

            String name = InputName.getText();
            String description = InputDescription.getText();
            float price = Float.parseFloat(InputPrice.getText());
            int quantite= Integer.parseInt(InputQuantite.getText());

            ProductService productService = new ProductService();
            String image = PictureChooser.getImage().getUrl().toString();

            if (image.startsWith("file:/")) {
                image = image.substring("file:/".length());
            }


            if (name.isEmpty() || description.isEmpty() || image.isEmpty()) {
                NotifFailed();
                return;
            }

            Product product = new Product(0, name, description, image, price, 1, "",quantite);
            int result = productService.add(product);

            if (result == 1) {
                         clearInputsproduct();
                         NotifSuccess();
                         ShowProductList();

            } else {
                NotifFailed();
            }
        } catch (NumberFormatException ex) {
            NotifFailed();
        } catch (Exception ex) {
            System.out.println("An error occurred while adding the product: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void clearInputsproduct() {

        InputName.clear();
        InputDescription.clear();
        InputPrice.clear();
        InputQuantite.clear();
        //PictureChooser.setImage(null);

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

      //IdProductdetailsClient.setText(String.valueOf(product.getIdProduct()));
       RectangleDetailProduct.setFill(new ImagePattern(new Image("file:///" +product.getImage().replace("\\","/"))));
       NameProductdetailsClient.setText(product.getName());
       DescProductdetailsClient.setText(product.getDescription());
       dateProductDetailsClient.setText(product.getAddDate());

       QuantiteProductDetailclient.setText(String.valueOf(product.getQuantite()));
       float price = product.getPrice();
       PriceProductDetailsClient.setText(price + " DT");

           Passerrating.setOnAction(e -> {
               ProductevaluationService productevaluationService = new ProductevaluationService();
            int id= productevaluationService.isUserrated(product);
            if (id != user1.getIdUser())
            {

                int productid = product.getIdProduct();
                double rating =InputRatingStar.getRating();
                Productsevaluation productsevaluation = new Productsevaluation(0,rating, "", productid, user1.getIdUser());
                productevaluationService.add(productsevaluation);
                ShowProductList();
                NotifSuccess();
                AnchorPanerating.setVisible(true);
            }
            else {
                AnchorPanerating.setVisible(false);
                NotifFailedRate();
            }

       });

    }




    public void remplireCartProduct(Product product) {
        CartAnchorPane.setVisible(true);
        NumeroProductLabel.setText(String.valueOf(product.getIdProduct()));
      //NomProductLabel.setText(product.getName());
        initialPrice = product.getPrice();
        TotalPriceLabel.setText(String.valueOf(initialPrice));
    }

    public void checkQuantity(Product product) {
        ProductService productService = new ProductService();
        int TestQuantity = productService.getProductQuantityById(product.getIdProduct());
        if (TestQuantity ==0) {
            CartAnchorPane.setVisible(false);
            NotifSoldeOut();
        }
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

                   int nbr = sousCartService.countNbrOfsouscarts(cart.getIdCarts());
                    nbrSourCart.setText(String.valueOf(nbr)); //nbrSourCart
                } else {
                    cart = new Carts(1, 0, 0, user.getIdUser());
                    if (cartsservice.add(cart) == 1) {
                        cart = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
                        sousCart = new SousCart(0, cart.getIdCarts(), id_produit, quantite);
                        sousCartService.add(sousCart);
                        cartsservice.updateTotalPrice(cart.getIdCarts(), cart.getTotalPrice() + totalPrice);
                        int nbr = sousCartService.countNbrOfsouscarts(cart.getIdCarts());
                        nbrSourCart.setText(String.valueOf(nbr)); //nbrSourCart
                    }
                }
            } else {
                Notifinsuffisante();
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
                fxmlLoader.setLocation(getClass().getResource("/gui/students/sousCartItemStudent.fxml"));
                Parent p = fxmlLoader.load();
                SousCartItemStudent sousCartItem = fxmlLoader.getController();
                sousCartItem.setProductPage(this);
                sousCartItem.setSousCartProduct(sousCart);
                cartItemsContainer.getChildren().add(p);
                cartAnchorepaneContainer.setVisible(true);
                totalPriceCartLabel.setText(String.valueOf(carts.getTotalPrice()) + " DT");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}
        }else {
           NotifFailedAddToCart();


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

    @FXML
    void handleCloseMap(ActionEvent event)
    {
        mapContainer.setVisible(false);
    }



    @FXML
    void handleOrderMap(ActionEvent event) {

        mapContainer.setVisible(true);
        WebEngine we = mapWebView.getEngine();
        String pathMap = "file:///C:/projet Pidev/EcoCraftLearning/src/main/resources/Api/map.html";
        we.load(pathMap);
        we.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            JSObject window = (JSObject) we.executeScript("window");
            window.setMember("javaConnector", this);

        });

    }
    public void updateCoordinatesAndName(String latitude, String longitude, String cityName) {
        Platform.runLater(() -> {
            InputLatitude.setText(latitude);
            InputLongitude.setText(longitude);

            String[] parts = cityName.split("\\s|,", 2);
            if (parts.length > 1) {
                String addressAfterSpaceOrComma = parts[1];
                InputCityOrder.setText(addressAfterSpaceOrComma);
            } else {
                InputCityOrder.setText(cityName);
            }
        });
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(cityName);
    }




    public boolean PasserCommande() {

        try {
           double longitude = Double.parseDouble(InputLongitude.getText());
            double latitude = Double.parseDouble(InputLatitude.getText());
            String email = InputEmailOrder.getText();
            String city = InputCityOrder.getText();
            String phoneInput = InputPhoneOrder.getText();
            float total = Float.parseFloat(totalPriceCartLabel.getText().replace("DT", ""));

            if (phoneInput.length() != 8 || !phoneInput.matches("\\d{8}") || !email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail\\.com$")) {
                NotifFailed();
                return false;
            }

            int phone = Integer.parseInt(phoneInput);
            CartsService cartsservice = new CartsService();
            Carts carts = cartsservice.getByIdIfNotConfirmed(user.getIdUser());
            int cartId = carts.getIdCarts();
            CommandesService commandesService = new CommandesService();
            Commandes commandes = new Commandes(0, user.getIdUser(), "", cartId, email, city, phone,"",latitude,longitude,total);

            int result = commandesService.add(commandes);
            if (result == 1) {

                NotifSuccess();
                SousCartService sousCartService = new SousCartService();
                sousCartService.decrementAllQuantities(cartId);

                clearInputOrder();

                 //EmailSender emailSender = new EmailSender();
                // emailSender.sendConfirmationEmail(email, user.getFirstName(), city, total);

                cartAnchorepaneContainer.setVisible(false);
                return true;
            } else {
                NotifFailed();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("An error occurred while placing the order: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public void clearInputOrder() {

        InputLongitude.clear();
        InputLatitude.clear();
        InputEmailOrder.clear();
        InputCityOrder.clear();
        InputPhoneOrder.clear();

    }


    @FXML
    void GobackOrder(ActionEvent event) {

        AnchorPaneMyorders.setVisible(false);
        PaneGroupProduct.setVisible(true);

    }



   public void showMyOrders() {

       CommandesService commandesService = new CommandesService();
       ListOrderClient = commandesService.getAllByIdUser(user.getIdUser());
       VboxCommandeClient.getChildren().clear();
       try {
           for (Commandes commandes : ListOrderClient)
           {
               FXMLLoader fxmlLoader = new FXMLLoader();
               fxmlLoader.setLocation(getClass().getResource("/gui/students/ItemCommandeStudent.fxml"));

               HBox HboxItemCommande = fxmlLoader.load();

               ItemCommandeStudent itemCommandeStudent = fxmlLoader.getController();


               itemCommandeStudent.setProductPage(this);

               itemCommandeStudent.setDataCommande(commandes);

               VboxCommandeClient.getChildren().add(HboxItemCommande);
           }
       }
       catch (IOException e) {
           throw new RuntimeException(e);
       }

   }


}
