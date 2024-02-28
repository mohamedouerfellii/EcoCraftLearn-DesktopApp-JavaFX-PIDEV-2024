package tn.SIRIUS.controller.admins;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import tn.SIRIUS.entities.Commandes;

public class ItemCommandeAdmin {

    @FXML
    private Label AddresseCommande;

    @FXML
    private Button BtnDelivred;

    @FXML
    private Label DateCommande;

    @FXML
    private Label StatusCommande;

    @FXML
    private Label TotalCommande;
    @FXML
    private HBox HboxItemCommande;
    ProduitPageController produitPageController = new ProduitPageController();
    public void setDataCommande(Commandes commandes) {
        AddresseCommande.setText(commandes.getCity());
        DateCommande.setText(commandes.getCommandeDate());
        TotalCommande.setText(commandes.getTotal() + " DT");
        StatusCommande.setText(commandes.getStatus());

        BtnDelivred.setOnAction(event -> {
            produitPageController.updateStat(commandes);
        });

    }

    public void setProduitPageController(ProduitPageController produitPageController) {
        this.produitPageController = produitPageController;
    }

}