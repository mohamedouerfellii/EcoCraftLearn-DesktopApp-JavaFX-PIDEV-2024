package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tn.SIRIUS.entities.Commandes;

public class ItemCommandetutor {

    @FXML
    private Label AddresseCommande;

    @FXML
    private Label DateCommande;

    @FXML
    private HBox HboxItemCommande;

    @FXML
    private Label StatusCommande;

    @FXML
    private Label TotalCommande;

    ProductPagetutor productPage = new ProductPagetutor();

    public void setDataCommande(Commandes commandes) {
        AddresseCommande.setText(commandes.getCity());
        DateCommande.setText(commandes.getCommandeDate());
        TotalCommande.setText(commandes.getTotal() + " DT");
        StatusCommande.setText(commandes.getStatus());

    }

    public void setProductPage(ProductPagetutor productpage) {
        this.productPage= productpage;
    }

}

