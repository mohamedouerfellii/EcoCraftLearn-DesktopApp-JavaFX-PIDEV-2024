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
    private ToggleButton likePostBtn;

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
    private  ForumController forumController = new ForumController();

    public MenuButton getAboutPostBtn() {return AboutPostBtn;}


    @FXML
    private MenuButton AboutPostBtn;

    @FXML
    private MenuItem updatePostBtn;
    @FXML
    private MenuItem deletePostBtn;

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
        likePostBtn.setStyle("    -fx-font-family: \"Jost Medium\";\n" +
                "    -fx-font-size: 16;\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-text-fill: #939393;\n" +
                "    -fx-background-radius: 8px;");
        Image image1 = new Image(getClass().getResourceAsStream("/img/dark/icons8-aime-rempli-24.png"));
        LikeIcon.setImage(image1);

//        likePostBtn.setOnMouseEntered(e->{
//            Image image = new Image("/Resources/img/dark/icons8-aimer-50.png");
//            LikeIcon.setImage(image);
//            likePostBtn.setStyle(" -fx-font-family: \"Jost Medium\";\n" +
//                    "    -fx-font-size: 16;\n" +
//                    "    -fx-background-color: #7dc145;\n" +
//                    "    -fx-text-fill: white;\n" +
//                    "    -fx-opacity: 0.6;\n" +
//                    "    -fx-background-radius: 8px;");
//        });
//        likePostBtn.setOnMouseExited(e->{
//            Image image = new Image("/Resources/img/dark/icons8-aime-rempli-24.png");
//            LikeIcon.setImage(image);
//            likePostBtn.setStyle("  -fx-font-family: \"Jost Medium\";\n" +
//                    "    -fx-font-size: 16;\n" +
//                    "    -fx-background-color: transparent;\n" +
//                    "    -fx-text-fill: #939393;\n" +
//                    "    -fx-background-radius: 8px;");
//        });
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

        likePostBtn.setOnAction(event -> {
            LikePressed();
        });
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


    @FXML
    void LikePressed() {


        System.out.println(likePostBtn.isSelected());
        if (likePostBtn.isSelected()) {

            likePostBtn.setStyle("-fx-background-color: white;\n" +
                    " -fx-text-fill: #939393 ;\n" +
                    "        -fx-border-radius: 5;\n " +
                    "       -fx-padding: 8 ;");
            Image icon = new Image(getClass().getResourceAsStream("/img/dark/icons8-aime-rempli-26.png"));
            LikeIcon.setImage(icon);

        } else {

            likePostBtn.setStyle("  -fx-background-color: #ffffff; /* Facebook blue */\n" +
                    "    -fx-text-fill: #939393;\n" +
                    "    -fx-border-radius: 5;\n" +
                    "    -fx-padding: 8 ;");

            Image icon = new Image(getClass().getResourceAsStream("/img/dark/icons8-aime-rempli-24.png"));

            LikeIcon.setImage(icon);
        }



    }
    public ToggleButton getLike() {
        return likePostBtn;
    }

    public void setLike(ToggleButton like) {
        likePostBtn = like;
    }



    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }


}


