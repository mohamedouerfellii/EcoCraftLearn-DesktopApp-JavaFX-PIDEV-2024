package tn.SIRIUS.controller.admins;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import tn.SIRIUS.entities.Commandes;
import tn.SIRIUS.entities.Product;
import tn.SIRIUS.services.CommandesService;
import tn.SIRIUS.services.ProductService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ProduitPageController implements Initializable {

    @FXML
    private Rectangle RectangleDetailProduct;


    @FXML
    private ImageView ImageQrcode;

    @FXML
    private Pane paneqrCode;

    @FXML
    private Button BtnOpenUpdate;
    @FXML
    private TextArea InputDescUpdate;

    @FXML
    private ImageView InputImageUpdate;

    @FXML
    private TextField InputNameUpdate;

    @FXML
    private TextField InputPriceUpdate;

    @FXML
    private TextField InputQuantiteupdate;

    @FXML
    private AnchorPane FormUpdateAdmin;

    @FXML
    private AnchorPane AnchorPanedetailsProduct;

    @FXML
    private AnchorPane AnchorPaneProductBack;

    @FXML
    private ImageView ImageProductDetails;
    @FXML
    private Label IdProductdetails;


    @FXML
    private Text NameProductdetails;


    @FXML
    private Text QuantityProductdetail;

    @FXML
    private Text PriceProductDetails;

    @FXML
    private Label DescProductdetails;

    @FXML
    private Text dateProductDetails;

    @FXML
    private VBox VboxTableProduct;
    @FXML
    private Button GoBackBtn;

    @FXML
    private TextField InputSearchProduct;

   @FXML
    private VBox VboxCommandeAdmin;

       @FXML
      private AnchorPane AnchorPaneCommande;

    @FXML
    private AnchorPane AnchorPaneStatestique;

    public List<Product> recentlyAdded;
    public List<Commandes> ListCommandes;

    @FXML
    private Button Statestiqueproduct;


    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    private Button TrieBtnProduct;

    public List<Product> TrieProduct;

    @FXML
    private AnchorPane SuccesAddProduct;

    @FXML
    private AnchorPane failedAddProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recentlyAdded = new ArrayList<>(recentlyAdded());
        try {
            for (int i=0; i<recentlyAdded.size(); i++)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/TableItemProduct.fxml"));
                HBox BoxTableProduct = fxmlLoader.load();
                TableItemProduct tableItemProduct = fxmlLoader.getController();
                tableItemProduct.setProduitPageController(this);
                tableItemProduct.setData(recentlyAdded.get(i));
                VboxTableProduct.getChildren().add(BoxTableProduct);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        AnchorPaneProductBack.setVisible(true);
        AnchorPanedetailsProduct.setVisible(false);


        Statestiqueproduct.setOnAction(e -> {
            AfficherStat();
            AnchorPaneStatestique.setVisible(true);

        });

        TrieBtnProduct.setOnAction(e -> {
            AfficherTriPrice();
        });

        refresh();

    }

public void AfficherTriPrice()
{
    ProductService productService = new ProductService();
    TrieProduct = productService.TrieParPrice();
    VboxTableProduct.getChildren().clear();

    try {
        for (Product product : TrieProduct)
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/admins/TableItemProduct.fxml"));
            HBox BoxTableProduct = fxmlLoader.load();
            TableItemProduct tableItemProduct = fxmlLoader.getController();
            tableItemProduct.setProduitPageController(this);
            tableItemProduct.setData(product);
            VboxTableProduct.getChildren().add(BoxTableProduct);
        }
    }
    catch (IOException e) {
        throw new RuntimeException(e);
    }
}

    public void refresh()
    {
          ProductService productService = new ProductService();
          recentlyAdded = productService.getAll();
          VboxTableProduct.getChildren().clear();

        try {
            for (Product product : recentlyAdded)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/TableItemProduct.fxml"));
                HBox BoxTableProduct = fxmlLoader.load();
                TableItemProduct tableItemProduct = fxmlLoader.getController();
                tableItemProduct.setProduitPageController(this);
                tableItemProduct.setData(product);
                VboxTableProduct.getChildren().add(BoxTableProduct);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> recentlyAdded()
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

    public void ShowProductListsearch(List<Product> products) {
        VboxTableProduct.getChildren().clear();

        try {
            for (Product product : recentlyAdded)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/TableItemProduct.fxml"));
                HBox BoxTableProduct = fxmlLoader.load();
                TableItemProduct tableItemProduct = fxmlLoader.getController();
                tableItemProduct.setProduitPageController(this);
                tableItemProduct.setData(product);
                VboxTableProduct.getChildren().add(BoxTableProduct);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void showAllProducts(String searchText) {
        ProductService productService = new ProductService();
        recentlyAdded = productService.searchProducts(searchText);
        ShowProductListsearch(recentlyAdded);
    }

    @FXML
    private void handlesearch() {
        String searchText = InputSearchProduct.getText().trim();
        VboxTableProduct.getChildren().clear();
        showAllProducts(searchText);
    }

    public void showProductDetails(Product product) {

        AnchorPaneProductBack.setVisible(false);
        AnchorPanedetailsProduct.setVisible(true);

        RectangleDetailProduct.setFill(new ImagePattern(new Image("file:///" +product.getImage().replace("\\","/"))));
        IdProductdetails.setText(String.valueOf(product.getIdProduct()));
        NameProductdetails.setText(product.getName());
        DescProductdetails.setText(product.getDescription());
        dateProductDetails.setText(product.getAddDate());
        QuantityProductdetail.setText(String.valueOf(product.getQuantite()));
        float price = product.getPrice();
        PriceProductDetails.setText(price + "DT");
    }


    @FXML
    void GoBackBtn(ActionEvent event) {
        AnchorPaneProductBack.setVisible(true);
        AnchorPanedetailsProduct.setVisible(false);
    }

    @FXML
    void handleDeleteBtnProduct(ActionEvent event) {

        ProductService productService = new ProductService();
        if (productService.delete(Integer.valueOf(IdProductdetails.getText())))
        {    NotifSuccess();
            refresh();
            AnchorPaneProductBack.setVisible(true);
            AnchorPanedetailsProduct.setVisible(false);
        }

    }

    @FXML
    void handleOnBtnChooserImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null)
        {
            Image image = new Image(selectedFile.toURI().toString());
            InputImageUpdate.setImage(image);
        }
    }

    @FXML
    void handleOnBtnOpenUpdate(ActionEvent event) {

        FormUpdateAdmin.setVisible(true);

        InputNameUpdate.setText(NameProductdetails.getText());
        InputDescUpdate.setText(DescProductdetails.getText());
        InputPriceUpdate.setText(PriceProductDetails.getText().replace("DT", ""));

        RectangleDetailProduct.setFill(new ImagePattern(InputImageUpdate.getImage()));

        InputQuantiteupdate.setText(QuantityProductdetail.getText());

    }

    @FXML
    void handleOnUpdateBtn(ActionEvent event) {


        try {

            int id = Integer.valueOf(IdProductdetails.getText());
            String name = InputNameUpdate.getText();
            String description = InputDescUpdate.getText();

            float price = Float.parseFloat(InputPriceUpdate.getText());
            int quantite = Integer.parseInt(InputQuantiteupdate.getText());
            String image = InputImageUpdate.getImage().getUrl().toString();
            if (image.startsWith("file:/")) {

                image = image.substring("file:/".length());
            }
            System.out.println(image);

            ProductService productService = new ProductService();
            Product product = new Product(id, name, description, image, price, 1, "",quantite);

            if (productService.update(product)) {
                InputNameUpdate.clear();
                InputDescUpdate.clear();
                InputPriceUpdate.clear();
                InputQuantiteupdate.clear();
                //InputImageUpdate.setImage(null);
                NotifSuccess();

                refresh();
                showProductDetails(product);
            } else {
                NotifFailed();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

    }
    @FXML
    void handleOnCancelBtn(ActionEvent event) {
        FormUpdateAdmin.setVisible(false);
    }

public void showQrCode(Product product) {

    try {
        paneqrCode.setVisible(false);
        String content = "Product: " + product.getName()+ "\nPrice: " + product.getPrice() + "\nDescription: " + product.getDescription() + "\nImage: " + product.getImage() + "\nAddDate: " + product.getAddDate() + "\nOwner: " + product.getOwner() + "\nQuantite: " + product.getQuantite();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 250, 250, hints);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        byte[] qrCodeBytes = outputStream.toByteArray();
        javafx.scene.image.Image qrCodeImage = new javafx.scene.image.Image(new ByteArrayInputStream(qrCodeBytes));
        ImageQrcode.setImage(qrCodeImage);
        paneqrCode.setVisible(true);
    } catch (Exception e) {
        e.printStackTrace();
    }

}

    @FXML
    void handleCloseQrcode(ActionEvent event) {
        paneqrCode.setVisible(false);
    }

    public List<Commandes> recentlyAddedCommande()
    {
        List<Commandes> ListCommandes = new ArrayList<>();
       CommandesService commandesService = new CommandesService();
        ListCommandes = commandesService.getAll();
        return ListCommandes;

    }


    @FXML
    void handleOpenOrder(ActionEvent event) {

        AnchorPaneCommande.setVisible(true);
        AnchorPaneProductBack.setVisible(false);

        CommandesService commandesService = new CommandesService();
        ListCommandes = commandesService.getAll();
        VboxCommandeAdmin.getChildren().clear();
        try {
            for (Commandes commandes : ListCommandes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/ItemCommandeAdmin.fxml"));

                HBox HboxItemCommande = fxmlLoader.load();

                ItemCommandeAdmin itemCommandeAdmin = fxmlLoader.getController();
                itemCommandeAdmin.setProduitPageController(this);
                itemCommandeAdmin.setDataCommande(commandes);

                VboxCommandeAdmin.getChildren().add(HboxItemCommande);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public void refreshCommande()
    {
        CommandesService commandesService = new CommandesService();
        ListCommandes = commandesService.getAll();
        VboxCommandeAdmin.getChildren().clear();
        try {
            for (Commandes commandes : ListCommandes)
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/ItemCommandeAdmin.fxml"));

                HBox HboxItemCommande = fxmlLoader.load();

                ItemCommandeAdmin itemCommandeAdmin = fxmlLoader.getController();
                itemCommandeAdmin.setProduitPageController(this);
                itemCommandeAdmin.setDataCommande(commandes);

                VboxCommandeAdmin.getChildren().add(HboxItemCommande);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

@FXML
void GoBackBtnCommande(ActionEvent event) {

    AnchorPaneCommande.setVisible(false);
    AnchorPaneProductBack.setVisible(true);

}
public  void updateStat(Commandes commandes)
{
    CommandesService commandesService = new CommandesService();
    commandesService.updateStatus(commandes);
    refreshCommande();

}



    @FXML
    void handleCloseStat(ActionEvent event) {
        AnchorPaneStatestique.setVisible(false);
    }





    void AfficherStat(){

        AnchorPaneStatestique.setVisible(true);
        ProductService productService = new ProductService();
        List<Float> prices = productService.getAllPrices();

        int countPriceLessThan100 = 0;
        int countPriceBetween100And500 = 0;
        int countPriceBetween500And1000 = 0;
        int countPriceGreaterThan1000 = 0;

        for (Float price : prices) {
            if (price < 100) {
                countPriceLessThan100++;
            } else if (price <= 500) {
                countPriceBetween100And500++;
            } else if (price <= 1000) {
                countPriceBetween500And1000++;
            } else {
                countPriceGreaterThan1000++;
            }
        }

        barChart.getData().clear();
        xAxis.setLabel("Price Range");
        xAxis.setCategories(FXCollections.observableArrayList("price < 100", "100 - 500", "500 - 1000", "price > 1000"));
        yAxis.setLabel("Number of Products");
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("price < 100", countPriceLessThan100));
        series.getData().add(new XYChart.Data<>("100 - 500", countPriceBetween100And500));
        series.getData().add(new XYChart.Data<>("500 - 1000", countPriceBetween500And1000));
        series.getData().add(new XYChart.Data<>("price > 1000", countPriceGreaterThan1000));
        barChart.getData().add(series);
    }








}
