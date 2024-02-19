package tn.SIRIUS.controller.students;

import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tn.SIRIUS.entities.Comment;
import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.User;

import java.awt.*;

public class CommentItemController {

    @FXML
    private VBox commentContainer;

    @FXML
    private Text commentContent;

    @FXML
    private Circle commentUserImg;

    @FXML
    private Text commentUserName;



    void setCommentItemdata(Comment comment){
        commentContent.setText(comment.getContent());
        commentUserName.setText(comment.getOwner().getFirstName() +" "+comment.getOwner().getLastName());
        Image img = new Image(comment.getOwner().getImage());
        commentUserImg.setFill(new ImagePattern(img));
        String imageUrl = comment.getAttachment();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            ImageView imageView = new ImageView(image);
            imageView.setStyle("-fx-background-radius: 10;");
            imageView.setFitWidth(image.getWidth() * 0.3);
            imageView.setFitHeight(image.getHeight() * 0.3);
            VBox.setMargin(imageView, new Insets(10, 0, 0, 0));
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), imageView);
            scaleIn.setToX(5);
            scaleIn.setToY(5);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), imageView);
            scaleOut.setToX(1);
            scaleOut.setToY(1);
            javafx.scene.control.Button returnButton = new javafx.scene.control.Button("Hide");
            returnButton.setOnAction(event -> {
                scaleOut.play();
                commentContainer.setPrefHeight(image.getHeight()*0.3 + 50);
                commentContainer.getChildren().remove(returnButton);
            });
            imageView.setOnMouseClicked(event -> {scaleIn.play();commentContainer.setPrefHeight(image.getHeight() + 50); commentContainer.getChildren().add(returnButton);});
            commentContainer.getChildren().add(imageView);
            commentContainer.setPrefHeight(image.getHeight()*0.3 + 50);
        }
    
    }
}
