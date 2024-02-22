package tn.SIRIUS.controller.students;

import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
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
import tn.SIRIUS.services.CommentService;
import  javafx.scene.control.MenuItem;

import java.awt.*;

public class CommentItemController {

    public MenuButton getABoutCommentBtn() {
        return ABoutCommentBtn;
    }

    @FXML
    private MenuButton ABoutCommentBtn;

    @FXML
    private VBox commentContainer;

    @FXML
    private Text commentContent;

    @FXML
    private Circle commentUserImg;

    @FXML
    private Text commentUserName;

    @FXML
    private MenuItem deleteCommentmenuBtn;

    @FXML
    private MenuItem updateCommentmenuBtn;


    private ForumController forumController;




    void setCommentItemdata(Comment comment){
        commentContent.setText(comment.getContent());
        commentUserName.setText(comment.getOwner().getFirstName() +" "+comment.getOwner().getLastName());
        Image img = new Image(comment.getOwner().getImage());
        commentUserImg.setFill(new ImagePattern(img));
        double textHeight = commentContent.getLayoutBounds().getHeight();
        commentContainer.setPrefHeight(textHeight + 50);
        String imageUrl = comment.getAttachment();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            ImageView imageView = new ImageView(image);
            imageView.setStyle("-fx-background-radius: 10;");
            imageView.setFitWidth(image.getWidth() * 0.3);
            imageView.setFitHeight(image.getHeight() * 0.3);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(imageView);
            stackPane.setAlignment(Pos.BOTTOM_CENTER);
            commentContainer.getChildren().add(stackPane);
            commentContainer.setPrefHeight(image.getHeight() * 0.3+ textHeight +25 );
            ScaleTransition scaleIn = new ScaleTransition(Duration.millis(200), stackPane);
            scaleIn.setToX(1.5);
            scaleIn.setToY(1.5);
            ScaleTransition scaleOut = new ScaleTransition(Duration.millis(200), stackPane);
            scaleOut.setToX(1);
            scaleOut.setToY(1);
            ImageView closephotoicon = new ImageView();
            Image closephoto = new Image(getClass().getResourceAsStream("/img/light/icons8-multiplier-50.png"));
            closephotoicon.setImage(closephoto);
            closephotoicon.setFitHeight(30);
            closephotoicon.setFitWidth(30);
            Button returnButton = new Button();
            returnButton.setGraphic(closephotoicon);
            returnButton.setOnAction(event -> {
                scaleOut.play();
                scaleOut.setOnFinished(e -> {
                    forumController.getShowCommentsConatainer().getChildren().remove(stackPane);
                    forumController.getShowCommentsConatainer().getChildren().remove(returnButton);
                    forumController.getAllCommentsContainer().setVisible(true);
                    forumController.getCloseCommentsConatainerBtn().setVisible(true);
                    commentContainer.getChildren().add(stackPane);
                    commentContainer.setPrefHeight(imageView.getFitHeight()  +textHeight+25);
                });

            });
            imageView.setOnMouseClicked(event -> {
                scaleIn.play();
                scaleIn.setOnFinished(e -> {
                    forumController.getShowCommentsConatainer().getChildren().add(stackPane);
                    forumController.getShowCommentsConatainer().setTopAnchor(stackPane, imageView.getFitWidth()/2);
                    forumController.getShowCommentsConatainer().setLeftAnchor(stackPane, (forumController.getShowCommentsConatainer().getWidth() - imageView.getFitWidth())/2 );
                    forumController.getShowCommentsConatainer().getChildren().add(returnButton);
                    forumController.getShowCommentsConatainer().setTopAnchor(returnButton, 50.0);
                    forumController.getShowCommentsConatainer().setRightAnchor(returnButton, 50.0);
                    forumController.getCloseCommentsConatainerBtn().setVisible(false);
                    forumController.getAllCommentsContainer().setVisible(false);
                });

            });
        }
deleteCommentmenuBtn.setOnAction(e->{


    forumController.deleteComment(comment);


});

    }




    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }


}
