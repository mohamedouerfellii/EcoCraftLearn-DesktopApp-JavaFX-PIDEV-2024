package tn.SIRIUS.controller.admins;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.SIRIUS.entities.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProduitPageController implements Initializable {

    @FXML
    private Button BtnCollects;

    @FXML
    private Button BtnCourses;

    @FXML
    private Button BtnDashboard;

    @FXML
    private Button BtnEvents;

    @FXML
    private Button BtnForum;

    @FXML
    private Button BtnLogout;

    @FXML
    private Button BtnProducts;

    @FXML
    private VBox VboxTableProduct;

    @FXML
    private ImageView iconCourses;

    @FXML
    private ImageView iconProduct;

    @FXML
    private ImageView iconcollect;

    @FXML
    private ImageView iconevent;

    @FXML
    private ImageView iconforum;

    @FXML
    private ImageView iconlogout;

    @FXML
    private ImageView iconsdash;

    private List<Product> recentlyAdded;
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
                tableItemProduct.setData(recentlyAdded.get(i));
                VboxTableProduct.getChildren().add(BoxTableProduct);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Product> recentlyAdded()
    {
        List<Product> ls = new ArrayList<>();
        Product product = new Product();
        product.setImage("/images/Img1.jpg");
        product.setName("Product Number  1");
        product.setDescription("ABBBABABABABABABABABAABABABA");
        product.setPrice(15);
        ls.add(product);

       product = new Product();
        product.setImage("/images/Img1.jpg");
        product.setName("Product Number  1");
        product.setDescription("ABBBABABABABABABABABAABABABA");
        product.setPrice(15);
        ls.add(product);

       product = new Product();
        product.setImage("/images/Img1.jpg");
        product.setName("Product Number  1");
        product.setDescription("ABBBABABABABABABABABAABABABA");
        product.setPrice(15);
        ls.add(product);

         product = new Product();
        product.setImage("/images/Img1.jpg");
        product.setName("Product Number  1");
        product.setDescription("ABBBABABABABABABABABAABABABA");
        product.setPrice(15);
        ls.add(product);


        product = new Product();
        product.setImage("/images/Img1.jpg");
        product.setName("Product Number  1");
        product.setDescription("ABBBABABABABABABABABAABABABA");
        product.setPrice(15);
        ls.add(product);


        return ls;
    }
}
