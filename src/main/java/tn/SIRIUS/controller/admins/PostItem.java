package tn.SIRIUS.controller.admins;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.Report;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.PostService;

import java.io.IOException;

public class PostItem  {



    @FXML
    private Text Post_Text;

    @FXML
    private Label Posted_Date;

    @FXML
    private Circle Profil_img;

    @FXML
    private Label UserName;

    @FXML
    private Label aboutReport;

    @FXML
    private VBox container;

    @FXML
    private Button deletePostBtn;

    @FXML
    private Label nbReports;


    @FXML
    private Label reportType;
    private ForumController forumController = new ForumController();




    public void setData(Report p)
    {
        Image img = new Image(p.getPost().getUser().getImage());
        Profil_img.setFill(new ImagePattern(img));
        String usernamelastname = p.getPost().getUser().getFirstName()+ " " + p.getPost().getUser().getLastName();
        UserName.setText(usernamelastname);
        Posted_Date.setText(p.getPost().getPostedDate().toString());
        Post_Text.setText(p.getPost().getContent());
        nbReports.setText("Reports : "+String.valueOf(p.getNbrReports()));
        aboutReport.setText(p.getReason());
        reportType.setText(p.getType());
        Text text = new Text(p.getPost().getContent());
        double textHeight = text.getBoundsInLocal().getHeight();
        Post_Text.setWrappingWidth(565);
        container.setPrefHeight(textHeight+150);
        String imageUrl = p.getPost().getAttachment();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            StackPane stackPane = new StackPane();
            stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            stackPane.setPrefHeight(image.getHeight()*0.3);
            stackPane.setPrefWidth(image.getWidth()*0.3);
            stackPane.setMaxWidth(600);
            imageView.setFitWidth(image.getWidth()*30/100);
            imageView.setFitHeight(image.getHeight()*30/100);
            DoubleBinding scaledWidth = Bindings.createDoubleBinding(
                    () -> stackPane.widthProperty().get() * 0.5,
                    stackPane.widthProperty()
            );
            imageView.fitWidthProperty().bind(scaledWidth);
            imageView.fitHeightProperty().bind(stackPane.heightProperty());
            stackPane.getChildren().add(imageView);
            container.getChildren().add(stackPane);
            container.setPrefHeight(textHeight+image.getHeight()+125+50);// Show the ImageView
        }


        System.out.println(p.getPost().getUser().getIdUser());
   deletePostBtn.setOnAction(e->{
            forumController.getDeletePostContainer().setVisible(true);
           forumController.getConfirmDeletePostBtn().setOnAction(event -> {
               forumController.deleteReport(p);
               forumController.getDeletePostContainer().setVisible(false);
           });

        });












    }





    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }


}


