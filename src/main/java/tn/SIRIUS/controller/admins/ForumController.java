package tn.SIRIUS.controller.admins;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.Report;
import tn.SIRIUS.services.PostService;
import tn.SIRIUS.services.ReportService;
import java.io.IOException;

import java.net.URL;

import java.util.*;

public class ForumController implements Initializable {

    @FXML
    private Button cancelDeletePostBtn;

    public Button getConfirmDeletePostBtn() {
        return confirmDeletePostBtn;
    }

    @FXML
    private Button confirmDeletePostBtn;


    @FXML
    private AnchorPane deletePostContainer;

    @FXML
    private VBox forum;

    public AnchorPane getDeletePostContainer() {
        return deletePostContainer;
    }

    List<Report> acuill = new ArrayList<>();
    ReportService serviceReport = new ReportService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        acuill = serviceReport.getAllReports();
        showPosts();
        cancelDeletePostBtn.setOnAction(e->{
            deletePostContainer.setVisible(false);

        });

        deletePostContainer.setVisible(false);


    }



    public void showPosts() {
       forum.getChildren().clear();
       PostService service = new PostService();
       Post post = new Post();
        acuill = serviceReport.getAllReports();
        Collections.sort(acuill, (c1, c2) -> c2.getNbrReports()-(c1.getNbrReports()));
        try {
            for (Report report : acuill) {
                post = service.getPostById(report.getPost().getIdPost());
                report.setPost(post);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/admins/Post.fxml"));
                Parent p = fxmlLoader.load();
                PostItem postItem = fxmlLoader.getController();
                postItem.setForumController(this);
                postItem.setData(report);
                forum.getChildren().add(p);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteReport(Report report){
        ReportService service = new ReportService();
        service.deleteReport(report);
        deletePostContainer.setVisible(false);
        showPosts();
    }



}









