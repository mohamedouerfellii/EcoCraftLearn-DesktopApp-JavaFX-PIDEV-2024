package tn.SIRIUS.controller.students;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import tn.SIRIUS.controller.students.CollectionPtsItemController;
import tn.SIRIUS.entities.Collect;
import tn.SIRIUS.entities.CollectionPoint;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CollectService;
import tn.SIRIUS.services.CollectionPtService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class PtsCollectMainPageController implements Initializable {

    @FXML
    private VBox CollectsMainContainer;

    @FXML
    private Button addCollectBtn;

    @FXML
    private ComboBox<String> addCollectMaterialType;

    @FXML
    private TextField addCollectQuantity;

    @FXML
    private AnchorPane addNewCollectContainer;

    @FXML
    private Button cancelAddingCollectBtn;

    @FXML
    private AnchorPane collectsPage;

    @FXML
    private Button openNewCollect;

    @FXML
    private VBox ptsCollectMainContainer;

    @FXML
    private AnchorPane ptsCollectPage;

    private List<CollectionPoint> points = new ArrayList<>();
    private List<Collect>   collectsList = new ArrayList<>();

    public int getIdPtCollect() {
        return idPtCollect;
    }

    public void setIdPtCollect(int idPtCollect) {
        this.idPtCollect = idPtCollect;
    }
    public AnchorPane getCollectsPage() {
        return collectsPage;
    }
    int idPtCollect;
    User user = new User(1,"","","","","","","",1,1,"");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collectsPage.setVisible(false);
        addNewCollectContainer.setVisible(false);
        CollectionPtService collectionPtService = new CollectionPtService();
        points = collectionPtService.getAll();
        showAllCollectionPts();
        openNewCollect.setOnMouseClicked(e->{
            addNewCollectContainer.setVisible(true);

        });
        ObservableList<String> materialTypes = FXCollections.observableArrayList("Plastique", "Papier", "Carton", "Metalique", "Autre");
        addCollectMaterialType.setItems(materialTypes);
    }
    public void showAllCollectionPts(){

        CollectionPtService collectionPtService = new CollectionPtService();
        points= collectionPtService.getAll();
        for (CollectionPoint collectionPoint : points){

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/collectionPtsItem.fxml"));
                Parent root = fxmlLoader.load();
                CollectionPtsItemController itemCollection = fxmlLoader.getController();
                itemCollection.setPtsCollectMainPageController(this);
                itemCollection.setCollectionItemData(collectionPoint);
                ptsCollectMainContainer.getChildren().add(root);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }






    public void showCollections(CollectionPoint collectionPt){
        CollectsMainContainer.getChildren().clear();
        CollectService collectService = new CollectService();
  collectsList= collectService.getAll();
        for (Collect collect : collectsList){
if(collect.getCollectionPoint().getIdcollectionPoint()==collectionPt.getIdcollectionPoint()){
    System.out.println(collect.getIdCollect());
    try {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/students/collectItem.fxml"));
        Parent root = fxmlLoader.load();
        CollectItemController item = fxmlLoader.getController();
        item.setPtsCollectMainPageController(this);
        item.setCollectItemData(collect);

        CollectsMainContainer.getChildren().add(root);

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
        }}

        addCollectBtn.setOnAction(e->{
             addCollect(collectionPt);
            addNewCollectContainer.setVisible(false);

        });
    }



    public void addCollect(CollectionPoint collectionPt) {
        Collect collect;
        collect = new Collect(1, addCollectMaterialType.getSelectionModel().getSelectedItem(), Double.parseDouble(addCollectQuantity.getText()), null, collectionPt, user);
        CollectService collectService = new CollectService();
       if (collectService.add(collect)==1)
       {
           showCollections(collectionPt);
           System.out.println(true);
       }else
           System.out.println(false);

    }

    public void deleteCollect(Collect collect) {
        CollectService collectService = new CollectService();
        if (collectService.delete(collect) == 1)
            System.out.println(true);
        else System.out.println(false);

    }
}
