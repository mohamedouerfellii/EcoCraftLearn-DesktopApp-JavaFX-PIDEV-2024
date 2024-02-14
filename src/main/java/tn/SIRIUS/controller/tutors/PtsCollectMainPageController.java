package tn.SIRIUS.controller.tutors;

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

    @FXML
    private TextArea updateCollectionPtsAdress;

    @FXML
    private TextField updateCollectionPtsCapacity;

    @FXML
    private Button updateCollectionptsBtn;

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



}
