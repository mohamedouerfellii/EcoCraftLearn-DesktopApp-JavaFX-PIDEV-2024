package tn.SIRIUS.controller.admins;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import tn.SIRIUS.entities.Product;
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




    public List<Product> recentlyAdded;
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
    }



    public void refresh()
    {   ProductService productService = new ProductService();
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




    public void showProductDetails(Product product) {

        AnchorPaneProductBack.setVisible(false);
        AnchorPanedetailsProduct.setVisible(true);

        String imageUrl = product.getImage();
        String formattedUrl = imageUrl.substring(imageUrl.indexOf("/images"));
        Image img1 = new Image(getClass().getResourceAsStream(formattedUrl));
        RectangleDetailProduct.setFill(new ImagePattern(img1));
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
        {
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

            ProductService productService = new ProductService();
            Product product = new Product(id, name, description, image, price, 1, "",quantite);

            if (productService.update(product)) {
                System.out.println("success");
                refresh();
                showProductDetails(product);
            } else {
                System.out.println("FAILED");
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
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
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


}
