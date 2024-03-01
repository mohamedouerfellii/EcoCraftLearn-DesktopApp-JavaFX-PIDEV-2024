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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.SIRIUS.entities.Comment;
import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CommentService;
import  javafx.scene.control.MenuItem;
import tn.SIRIUS.services.PostService;

import java.awt.*;
import java.io.File;

public class CommentItemController {

    public MenuButton getABoutCommentBtn() {
        return ABoutCommentBtn;
    }

    @FXML
    private MenuButton ABoutCommentBtn;

    @FXML
    private VBox commentContainer;

    @FXML
    private VBox contentConatainer;

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
   private String attachment ;



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

        updateCommentmenuBtn.setOnAction(e->{
recoverComment(comment);
        });

    }

public void recoverComment(Comment comment) {
    ABoutCommentBtn.setVisible(false);
    String content = commentContent.getText();
    double textHeight = commentContent.getLayoutBounds().getHeight();
    attachment= comment.getAttachment();
    System.out.println( attachment);
    contentConatainer.getChildren().remove(commentContent);
    TextArea newContent = new TextArea();
    newContent.setMinHeight(textHeight);
    newContent.setText(content);
    newContent.setStyle("-fx-control-inner-background: lightgrey; -fx-text-fill: black ; -fx-font: 12px Jost medium");
    newContent.setWrapText(true);
    contentConatainer.setPrefHeight(textHeight + 60);
    commentContainer.setMinHeight(contentConatainer.getPrefHeight()+25);//commentContainer.setPrefHeight( + 50);
    HBox hBox = new HBox();
    hBox.setPrefHeight(35.2);
    hBox.setPrefWidth(590.0);
    VBox.setMargin(hBox, new Insets(5.0, 15.0, 0.0, 15.0));

    Button newCommentAttachmentBtn = new Button();
    newCommentAttachmentBtn.setMinHeight(34.0);
    newCommentAttachmentBtn.setMinWidth(34.0);
    newCommentAttachmentBtn.setPrefHeight(34.0);
    newCommentAttachmentBtn.setPrefWidth(34.0);
    ImageView attachmentImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/dark/icons8-trombone-50.png")));
    attachmentImageView.setFitHeight(20.0);
    attachmentImageView.setFitWidth(20.0);
    newCommentAttachmentBtn.setGraphic(attachmentImageView);

    Button updateCommentContentBtn = new Button();
    updateCommentContentBtn.setMinWidth(34.0);
    updateCommentContentBtn.setPrefHeight(34.0);
    updateCommentContentBtn.setPrefWidth(34.0);

    ImageView sendImageView = new ImageView(new Image(getClass().getResourceAsStream("/img/dark/icons8-envoyé-24.png")));
    sendImageView.setFitHeight(20.0);
    sendImageView.setFitWidth(20.0);
    updateCommentContentBtn.setGraphic(sendImageView);

    ImageView imageView = new ImageView();
    hBox.getChildren().addAll(newContent, newCommentAttachmentBtn, updateCommentContentBtn);
    contentConatainer.getChildren().add(hBox);
if (attachment != null && !attachment.isEmpty()) {
  Image img = new Image(attachment);
  imageView.setImage(img);
  imageView.setFitHeight(img.getHeight()*0.3);
  imageView.setFitWidth(img.getWidth()*0.3);
  commentContainer.getChildren().add(imageView);
  commentContainer.setMinHeight(imageView.getFitHeight()  +contentConatainer.getHeight()+40);

}



    newContent.textProperty().addListener((obs, oldText, newText) -> {
        int numLines = (int) Math.ceil(newText.split("\n").length);
        double lineHeight = 20;
        double textHeight1 = numLines * lineHeight;
        newContent.setMinHeight(textHeight1);
        double minHeight = 80;
        double contentHeight = 35.2 + newContent.getMinHeight() + 30;
        if (attachment != null && !attachment.isEmpty()) {
            Image img = new Image(attachment);

            commentContainer.setMinHeight(img.getHeight()*0.3  +contentHeight );
        }else {
            commentContainer.setMinHeight(Math.max(minHeight, contentHeight));
        }
        contentConatainer.setMinHeight(textHeight1 + 50);
        if (textHeight1 < 28) {
            contentConatainer.setMinHeight(73.6);

            }
    });

    newCommentAttachmentBtn.setOnAction(e->{
        attachment = newCommentAttachmentWindow(imageView);
        if (attachment != null) {
            commentContainer.getChildren().remove(1, commentContainer.getChildren().size());
            commentContainer.getChildren().add(imageView);
            commentContainer.setMinHeight(imageView.getFitHeight()  + contentConatainer.getHeight());
            //////// adjust the comment container hight according to the image height

        }
    });

    updateCommentContentBtn.setOnAction(e->{
        Comment updateComment = new Comment(comment.getIdComment(), comment.getPost(),comment.getOwner(),newContent.getText(), attachment);
        CommentService commentService = new CommentService();
        if (commentService.update(updateComment) == 1) {
            System.out.println("updated");
        }else System.out.println("error");
        attachment=null;

        PostService postService = new PostService();
        Post post = postService.getPostById(comment.getPost().getIdPost());
        forumController.showPostComments(post);
    });


}
    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }

    private String newCommentAttachmentWindow(ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            // Remove the old image
            commentContainer.getChildren().remove(imageView);

            // Add the new image
            Image img = new Image(selectedFile.toURI().toString());
            imageView.setImage(new Image(selectedFile.toURI().toString()));
            imageView.setFitHeight(img.getHeight() * 0.3);
            imageView.setFitWidth(img.getWidth() * 0.3);
            commentContainer.getChildren().add(imageView);

            return "file:/" + selectedFile.getAbsolutePath();
        }
        return null;
    }

}
