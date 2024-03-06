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
import tn.SIRIUS.EcoCraftLearning.Gmail;
import tn.SIRIUS.controller.students.CollectionPtsItemController;
import tn.SIRIUS.entities.Collect;
import tn.SIRIUS.entities.CollectionPoint;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CollectService;
import tn.SIRIUS.services.CollectionPtService;
import tn.SIRIUS.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Button cancelUpdatingCollect;

    @FXML
    private AnchorPane collectsPage;

    @FXML
    private Button openNewCollect;

    @FXML
    private VBox ptsCollectMainContainer;

    @FXML
    private AnchorPane ptsCollectPage;



    @FXML
    private Button updateCollectBtn;


    @FXML
    private AnchorPane updateCollectContainer;

    @FXML
    private ComboBox<String> updateCollectMaterialType;

    @FXML
    private TextField updateCollectQuantity;

    private List<CollectionPoint> points = new ArrayList<>();
    private List<Collect>   collectsList = new ArrayList<>();
   public List<String> adminEmails = new ArrayList<>();

    public int getIdPtCollect() {
        return idPtCollect;
    }
    public Button getUpdateCollectBtn() {
        return updateCollectBtn;
    }
    public void setIdPtCollect(int idPtCollect) {
        this.idPtCollect = idPtCollect;
    }
    public AnchorPane getCollectsPage() {
        return collectsPage;
    }
    public AnchorPane getUpdateCollectContainer() {
        return updateCollectContainer;
    }

    int idPtCollect;
    User user = new User(1,"","","","","","","",1,1,"");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        collectsPage.setVisible(false);
        addNewCollectContainer.setVisible(false);
        updateCollectContainer.setVisible(false);
        CollectionPtService collectionPtService = new CollectionPtService();
        points = collectionPtService.getAll();
        showAllCollectionPts();
        openNewCollect.setOnMouseClicked(e->{
            addNewCollectContainer.setVisible(true);

        });
        cancelAddingCollectBtn.setOnAction(e->{
            addNewCollectContainer.setVisible(false);
            addCollectMaterialType.setValue("");
            addCollectQuantity.clear();
        });
        cancelUpdatingCollect.setOnAction(e->{
            updateCollectContainer.setVisible(false);
        });
        ObservableList<String> materialTypes = FXCollections.observableArrayList("Plastique", "Papier", "Carton", "Metalique", "Autre");
        addCollectMaterialType.setItems(materialTypes);
        updateCollectMaterialType.setItems(materialTypes);

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
            addCollectMaterialType.setValue("");
            addCollectQuantity.clear();

        });
    }



    public void addCollect(CollectionPoint collectionPt) {
        if (addCollectMaterialType.getValue() == null || addCollectQuantity.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            Collect collect;
            String material = addCollectMaterialType.getSelectionModel().getSelectedItem();
            double quantity = Double.parseDouble(addCollectQuantity.getText());
            Date date = new Date(); // Assuming you want the current date

            collect = new Collect(1, material, quantity, date, collectionPt, user);
            CollectService collectService = new CollectService();

            if (collectService.add(collect) == 1) {
                showCollections(collectionPt);
                System.out.println("Collect added successfully");

                UserService userService = new UserService();
                String notif = "A new collect has been added  The material is " + material +
                        ", the quantity is " + quantity + ", and it was added at " + date;

                List<String> adminEmails = userService.getEmailsAdmin();

                if (adminEmails != null) {
                    for (String adminEmail : adminEmails) {
                        Gmail.EmailTest(adminEmail, notif);
                        System.out.println("Email sent successfully to: " + adminEmail);
                    }
                } else {
                    System.out.println("No admin emails found.");
                }
            } else {
                System.out.println("Failed to add collect.");
            }
        }
    }

    public void deleteCollect(Collect collect) {
        CollectService collectService = new CollectService();

        if (collectService.delete(collect) == 1) {
            System.out.println("Collect deleted successfully");

            // Assuming you have a method like showCollections for displaying updated data
            showCollections(collect.getCollectionPoint());

            UserService userService = new UserService();
            String notif = "You have deleted a collect. The material was " + collect.getMaterialType() +
                    ", the quantity was " + collect.getQuantity() + ", and it was collected on " + collect.getCollectDate();

            List<String> adminEmails = userService.getEmailsAdmin();

            if (adminEmails != null) {
                for (String adminEmail : adminEmails) {
                    // Assuming you have an EmailTest method in your Gmail class
                    Gmail.EmailTest(adminEmail, notif);
                    System.out.println("Email sent successfully to: " + adminEmail);
                }
            } else {
                System.out.println("No admin emails found.");
            }
        } else {
            System.out.println("Failed to delete collect.");
        }
    }


    public void recoverCollect(Collect collect){
        updateCollectMaterialType.setValue(collect.getMaterialType());
        updateCollectQuantity.setText(Double.toString(collect.getQuantity()));
    }

    public void updateCollect(Collect collect) {
        if (updateCollectMaterialType.getValue() == null || updateCollectQuantity.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        } else {
            CollectService collectService = new CollectService();

            String material = updateCollectMaterialType.getValue();
            double quantity = Double.parseDouble(updateCollectQuantity.getText());

            Collect updatedCollect = new Collect(collect.getIdCollect(), material, quantity, collect.getCollectDate(), collect.getCollectionPoint(), user);

            if (collectService.update(updatedCollect) == 1) {
                System.out.println("Collect updated successfully");

                // Send email notification
                sendCollectNotification(updatedCollect);

                // Assuming you have a method like showCollections for displaying updated data
                updateCollectContainer.setVisible(false);
                showCollections(collect.getCollectionPoint());
            } else {
                System.out.println("Failed to update collect.");
            }
        }
    }

    private void sendCollectNotification(Collect updatedCollect) {
        UserService userService = new UserService();
        String notif = "You have updated a collect. The material is " + updatedCollect.getMaterialType() +
                ", the new quantity is " + updatedCollect.getQuantity() + ", and it was updated at " + updatedCollect.getCollectDate();

        List<String> adminEmails = userService.getEmailsAdmin();

        if (adminEmails != null) {
            for (String adminEmail : adminEmails) {
                // Assuming you have an EmailTest method in your Gmail class
                Gmail.EmailTest(adminEmail, notif);
                System.out.println("Email sent successfully to: " + adminEmail);
            }
        } else {
            System.out.println("No admin emails found.");
        }
    }

}
