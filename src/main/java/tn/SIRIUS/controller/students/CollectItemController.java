package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.SIRIUS.entities.Collect;

public class CollectItemController {

    @FXML
    private Label collectDate;

    @FXML
    private Label collectMaterialType;

    @FXML
    private Label collectQuantity;

    @FXML
    private Button deleteCollectBtn;

    @FXML
    private Button updateCollectBtn;
    private  PtsCollectMainPageController ptsCollectMainPageController;
    public void setPtsCollectMainPageController(PtsCollectMainPageController ptsCollectMainPageController) {
        this.ptsCollectMainPageController = ptsCollectMainPageController;
    }






    public void setCollectItemData(Collect Collect) {

        collectDate.setText(Collect.getCollectDate().toString());
        collectMaterialType.setText(Collect.getMaterialType());
        collectQuantity.setText(Double.toString(Collect.getQuantity()));

        deleteCollectBtn.setOnAction(e->{
            ptsCollectMainPageController.deleteCollect(Collect);
            ptsCollectMainPageController.showCollections(Collect.getCollectionPoint());
        });


    }



}
