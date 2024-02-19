package tn.SIRIUS.controller.students;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import tn.SIRIUS.entities.Post;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.PostService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class PostItem  {

    @FXML
    private Button commentPostBtn;

    @FXML
    private ImageView CommentIcon;

    @FXML
    private Button likePostBtn;
    @FXML
    private Button disLikePostBtn;

    @FXML
    private ImageView LikeIcon;

    @FXML
    private Text Post_Text;

    @FXML
    private Label Posted_Date;

    @FXML
    private Circle Profil_img;


    @FXML
    private Button sharePostBtn;

    @FXML
    private Label UserName;

    @FXML
    private VBox container;

    @FXML
    private BorderPane postItem;

    @FXML
    private Separator separator;

    @FXML
    private ImageView shareIcon;
    @FXML
    private ImageView disLikePostIcon;
    private  ForumController forumController = new ForumController();

    public MenuButton getAboutPostBtn() {return AboutPostBtn;}


    @FXML
    private MenuButton AboutPostBtn;

    @FXML
    private MenuItem updatePostBtn;
    @FXML
    private MenuItem deletePostBtn;

    int nbvote=0;

    public void setData(Post p , User user )
    {
        Image img = new Image(user.getImage());
        Profil_img.setFill(new ImagePattern(img));
        String usernamelastname = user.getFirstName()+ " " + user.getLastName();
        UserName.setText(usernamelastname);
        Posted_Date.setText(p.getPostedDate().toString());
        Post_Text.setText(p.getContent());
        Text text = new Text(p.getContent());
        double textHeight = text.getBoundsInLocal().getHeight();
        Post_Text.setWrappingWidth(565);
        container.setPrefHeight(textHeight+150);
        String imageUrl = p.getAttachment();
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
        sharePostBtn.setOnMouseEntered(e->{
            Image image = new Image(getClass().getResourceAsStream("/img/dark/icons8-transférer-50.png"));
            shareIcon.setImage(image);
        });
        commentPostBtn.setOnMouseEntered(e->{
            Image image = new Image(getClass().getResourceAsStream("/img/dark/icons8-paroles-24 (1).png"));
            CommentIcon.setImage(image);
        });
        sharePostBtn.setOnMouseExited(e->{
            Image image = new Image(getClass().getResourceAsStream("/img/dark/icons8-transférer-50 (1).png"));
            shareIcon.setImage(image);
        });
        commentPostBtn.setOnMouseExited(e->{
            Image image = new Image(getClass().getResourceAsStream("/img/dark/icons8-paroles-24.png"));
            CommentIcon.setImage(image);

        });
        commentPostBtn.setOnMouseClicked(e->{
            forumController.getShowCommentsConatainer().setVisible(true);
            forumController.setPostCommentData(p,user);

        });






        String styleBtnClicked = "-fx-background-color: white;\n" +
                " -fx-text-fill: #939393 ;\n" +
                "        -fx-border-radius: 5;\n " +
                "       -fx-padding: 8 ";

        String styleBtnNormal = "    -fx-font-family: \"Jost Medium\";\n" +
                "    -fx-font-size: 16;\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-text-fill: #939393;\n" +
                "    -fx-background-radius: 8px";

        likePostBtn.setStyle(styleBtnNormal);
        disLikePostBtn.setStyle(styleBtnNormal);

        likePostBtn.setOnAction(event -> {
            if (likePostBtn.getStyle().equals(styleBtnNormal)) {
                likePostBtn.setStyle(styleBtnClicked);
                LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-épaisse-pointant-vers-le-haut-48.png")));
nbvote++;
                System.out.println(nbvote);
                // Deselect the other button
                disLikePostBtn.setStyle(styleBtnNormal);
                disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-épaisse-pointant-vers-le-bas-32.png")));
            } else {
                likePostBtn.setStyle(styleBtnNormal);
                LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-épaisse-pointant-vers-le-haut-32.png")));
                nbvote--;
                System.out.println(nbvote);
            }
        });

        disLikePostBtn.setOnAction(event -> {
            if (disLikePostBtn.getStyle().equals(styleBtnNormal)) {
                disLikePostBtn.setStyle(styleBtnClicked);
                disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-épaisse-pointant-vers-le-bas-32 (1).png")));
                nbvote--;
                System.out.println(nbvote);
                // Deselect the other button
                likePostBtn.setStyle(styleBtnNormal);
                LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-épaisse-pointant-vers-le-haut-32.png")));
            } else {
                disLikePostBtn.setStyle(styleBtnNormal);
                disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-épaisse-pointant-vers-le-bas-32.png")));
                nbvote++;
                System.out.println(nbvote);
            }
        });





//        disLikePostBtn.setOnAction(event -> {
//            ReactionPressed(disLikePostBtn, disLikePostIcon, "/img/dark/icons8-flèche-épaisse-pointant-vers-le-bas-32.png", "/img/dark/icons8-flèche-épaisse-pointant-vers-le-bas-32 (1).png", likePostBtn, LikeIcon, "/img/dark/icons8-flèche-épaisse-pointant-vers-le-haut-48.png", "/img/dark/icons8-flèche-épaisse-pointant-vers-le-haut-32.png");
//        });


        updatePostBtn.setOnAction(e->{
            try {
                forumController.recover(p);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        deletePostBtn.setOnAction(e->{
            forumController.getDeletePostContainer().setVisible(true);
            forumController.setIdposttodelete(p.getIdPost());
            System.out.println(p.getIdPost());

        });


    }


//    private void ReactionPressed(ToggleButton selectedButton, ImageView selectedIcon, String selectedIconPath, String deselectedIconPath, ToggleButton otherButton, ImageView otherIcon, String otherIconPath, String otherDeselectedIconPath) {
//        String styleBtnClicked = "-fx-background-color: white;\n" +
//                " -fx-text-fill: #939393 ;\n" +
//                "        -fx-border-radius: 5;\n " +
//                "       -fx-padding: 8 ";
//
//        String styleBtnNormal = "    -fx-font-family: \"Jost Medium\";\n" +
//                "    -fx-font-size: 16;\n" +
//                "    -fx-background-color: transparent;\n" +
//                "    -fx-text-fill: #939393;\n" +
//                "    -fx-background-radius: 8px";
//
//        if (selectedButton.isSelected()) {
//            selectedButton.setStyle(styleBtnClicked);
//            selectedIcon.setImage(new Image(getClass().getResourceAsStream(selectedIconPath)));
//
//        } else {
//            selectedButton.setStyle(styleBtnNormal);
//            selectedIcon.setImage(new Image(getClass().getResourceAsStream(deselectedIconPath)));
//
//        }
//
//        if (otherButton.isSelected()) {
//            otherButton.setSelected(false);  // Deselect the other button
//            otherButton.setStyle(styleBtnNormal);
//            otherIcon.setImage(new Image(getClass().getResourceAsStream(otherDeselectedIconPath)));
//        }
//        System.out.println("this"+selectedButton.isSelected());
//        System.out.println("other"+otherButton.isSelected());
//    }






    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }


}


