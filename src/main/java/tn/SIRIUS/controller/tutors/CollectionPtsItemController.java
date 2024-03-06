package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.SIRIUS.entities.CollectionPoint;
import tn.SIRIUS.services.CollectionPtService;

import java.io.IOException;

public class CollectionPtsItemController {

    @FXML
    private Label collectionPtsAdress;

    @FXML
    private Label collectionPtsCapacity;

    @FXML
    private Label collectionPtsName;

    @FXML
    private Button deleteCollectionPtsBtn;

    @FXML
    private Button showUpdateCollectionPtsBtn;

    @FXML
    private Button viewCollectionsBtn;



    private PtsCollectMainPageController ptsCollectMainPageController;

    public void setPtsCollectMainPageController(PtsCollectMainPageController ptsCollectMainPageController) {this.ptsCollectMainPageController = ptsCollectMainPageController;}

    public void setCollectionItemData(CollectionPoint collectionPoint){
        collectionPtsName.setText(collectionPoint.getNameCollectionPoint());
        collectionPtsAdress.setText(collectionPoint.getAdressCollectionPoint());
        collectionPtsCapacity.setText(collectionPoint.getCapacity().toString()+ " KG" );
        deleteCollectionPtsBtn.setOnAction(e->{
            ptsCollectMainPageController.getConfirmDeleteCollectionPtContainer().setVisible(true);
            ptsCollectMainPageController.setIdtodelete(collectionPoint.getIdcollectionPoint());
        });
        showUpdateCollectionPtsBtn.setOnAction(event -> {
            try {
                ptsCollectMainPageController.recover(collectionPoint);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

     }

}
