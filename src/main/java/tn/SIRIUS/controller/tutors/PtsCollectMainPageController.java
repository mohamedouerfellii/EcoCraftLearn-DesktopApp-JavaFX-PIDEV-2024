package tn.SIRIUS.controller.tutors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;
import tn.SIRIUS.entities.CollectionPoint;
import tn.SIRIUS.services.CollectionPtService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PtsCollectMainPageController implements Initializable {

    public AnchorPane getUpdateCollectionPtsContainer() {
        return UpdateCollectionPtsContainer;
    }

    @FXML
    private AnchorPane UpdateCollectionPtsContainer;

    @FXML
    private AnchorPane addNewCollectionPtsContainer;

    @FXML
    private Button addNewCollectionptsBtn;

    @FXML
    private Button cancelAddingNewCollectionPts;

    @FXML
    private Button cancelUpdatingCollectionPts;

    @FXML
    private TextArea newCollectionPtsAdress;

    @FXML
    private TextField newCollectionPtsCapacity;

    @FXML
    private TextField newCollectionptsName;

    @FXML
    private Button openNewCollectionPts;

    @FXML
    private VBox ptsCollectMainContainer;

    @FXML
    private AnchorPane ptsCollectPage;

    public TextArea getUpdateCollectionPtsAdress() {
        return updateCollectionPtsAdress;
    }

    @FXML
    private TextArea updateCollectionPtsAdress;

    public TextField getUpdateCollectionPtsCapacity() {
        return updateCollectionPtsCapacity;
    }

    @FXML
    private TextField updateCollectionPtsCapacity;

    public Button getUpdateCollectionptsBtn() {
        return updateCollectionptsBtn;
    }

    @FXML
    private Button updateCollectionptsBtn;

    public TextField getUpdateCollectionptsName() {
        return updateCollectionptsName;
    }

    @FXML
    private TextField updateCollectionptsName;


    @FXML
    private Button deleteCollectionptsBtn;
    @FXML
    private Button cancelDeleteCollectionPts;

    public AnchorPane getConfirmDeleteCollectionPtContainer() {
        return confirmDeleteCollectionPtContainer;
    }

    @FXML
    private AnchorPane confirmDeleteCollectionPtContainer;


    private List<CollectionPoint> points = new ArrayList<>();

    public int getIdtodelete() {
        return Idtodelete;
    }

    public void setIdtodelete(int idtodelete) {
        Idtodelete = idtodelete;
    }

    private int Idtodelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addNewCollectionPtsContainer.setVisible(false);
        UpdateCollectionPtsContainer.setVisible(false);
        confirmDeleteCollectionPtContainer.setVisible(false);
        openNewCollectionPts.setOnAction(e->{
        addNewCollectionPtsContainer.setVisible(true);
        });
        cancelAddingNewCollectionPts.setOnAction(e->{
            addNewCollectionPtsContainer.setVisible(false);
            newCollectionptsName.clear();
            newCollectionPtsAdress.clear();
            newCollectionPtsCapacity.clear();
        });
        addNewCollectionptsBtn.setOnAction(e->{
            addNewCollectionpt();
        });
        deleteCollectionptsBtn.setOnAction(e->{
           CollectionPtService collectionPtService = new CollectionPtService();
           CollectionPoint collectionPoint = new CollectionPoint(Idtodelete,"","", 0.0F);
            if (collectionPtService.delete(collectionPoint)==1){
                confirmDeleteCollectionPtContainer.setVisible(false);
                ptsCollectMainContainer.getChildren().clear();
                showAllCollectionPts();
            }else System.out.println("error");
        });
        cancelDeleteCollectionPts.setOnAction(e->{
            confirmDeleteCollectionPtContainer.setVisible(false);
        });
        CollectionPtService collectionPtService = new CollectionPtService();
        points= collectionPtService.getAll();
        showAllCollectionPts();
        TextFormatter<Double> textFormatter = new TextFormatter<>(new DoubleStringConverter(), 0.0);
        newCollectionPtsCapacity.setTextFormatter(textFormatter);
        cancelUpdatingCollectionPts.setOnAction(event -> {
                    UpdateCollectionPtsContainer.setVisible(false);
                    updateCollectionptsName.clear();
                    updateCollectionPtsAdress.clear();
                    updateCollectionPtsCapacity.clear();
                }
                );
    }
    public void showAllCollectionPts(){

        CollectionPtService collectionPtService = new CollectionPtService();
        points= collectionPtService.getAll();

        for (CollectionPoint collectionPoint : points){

            try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/collectionPtsItem.fxml"));
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


    public void addNewCollectionpt(){
      String name= newCollectionptsName.getText();
      String adress = newCollectionPtsAdress.getText();
      float capacity = Float.parseFloat(newCollectionPtsCapacity.getText());
      CollectionPoint collectionPoint = new CollectionPoint(1,name,adress,capacity);
      CollectionPtService collectionPtService = new CollectionPtService();
      if (collectionPtService.add(collectionPoint)==1){
          addNewCollectionPtsContainer.setVisible(false);
          newCollectionptsName.clear();
          newCollectionPtsAdress.clear();
          newCollectionPtsCapacity.clear();
          ptsCollectMainContainer.getChildren().clear();
          showAllCollectionPts();
      }
      else System.out.println("error");
    }


    void recover(CollectionPoint collectionPoint ) throws IOException {
        updateCollectionptsName.setText(collectionPoint.getNameCollectionPoint());
        updateCollectionPtsAdress.setText(collectionPoint.getAdressCollectionPoint());
        updateCollectionPtsCapacity.setText(collectionPoint.getCapacity().toString());
        UpdateCollectionPtsContainer.setVisible(true);

        updateCollectionptsBtn.setOnAction(e->{UpdateCollectionpt(collectionPoint);
            System.out.println(collectionPoint.getIdcollectionPoint());
        });

    }

    public void UpdateCollectionpt(CollectionPoint entity) {
        int id = entity.getIdcollectionPoint();
        String name =updateCollectionptsName.getText();
        String adress = updateCollectionPtsAdress.getText();
        float capacity = Float.parseFloat(updateCollectionPtsCapacity.getText());
        CollectionPoint updatedentity = new CollectionPoint(id,name, adress, capacity);
        CollectionPtService collectionPtService = new CollectionPtService();
        if (collectionPtService.update(updatedentity) == 1) {
            System.out.println("done");
            UpdateCollectionPtsContainer.setVisible(false);
            ptsCollectMainContainer.getChildren().clear();
            points = collectionPtService.getAll();
            showAllCollectionPts();
        } else System.out.println("error");

    }




}
