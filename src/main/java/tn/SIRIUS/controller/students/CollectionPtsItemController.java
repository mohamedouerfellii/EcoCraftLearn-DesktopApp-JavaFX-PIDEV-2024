package tn.SIRIUS.controller.students;

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
    private Button viewCollectionsBtn;



    private PtsCollectMainPageController ptsCollectMainPageController;

    public void setPtsCollectMainPageController(PtsCollectMainPageController ptsCollectMainPageController) {this.ptsCollectMainPageController = ptsCollectMainPageController;}

    public void setCollectionItemData(CollectionPoint collectionPoint){
        collectionPtsName.setText(collectionPoint.getNameCollectionPoint());
        collectionPtsAdress.setText(collectionPoint.getAdressCollectionPoint());
        collectionPtsCapacity.setText(collectionPoint.getCapacity().toString()+ " KG" );
        viewCollectionsBtn.setOnMouseClicked(e->{
            ptsCollectMainPageController.getCollectsPage().setVisible(true);
            ptsCollectMainPageController.showCollections(collectionPoint);
        });

    }





}
