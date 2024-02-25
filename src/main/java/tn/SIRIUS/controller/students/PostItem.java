package tn.SIRIUS.controller.students;

import com.sun.javafx.tk.TKScene;
import com.sun.javafx.tk.TKStage;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import tn.SIRIUS.EcoCraftLearning.EcoCraftLearning;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.PostService;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PostItem  {


    @FXML
    private MenuButton AboutPostBtn;

    @FXML
    private ImageView CommentIcon;

    @FXML
    private ImageView LikeIcon;

    @FXML
    private Text Post_Text;

    @FXML
    private Label Posted_Date;

    @FXML
    private Circle Profil_img;

    @FXML
    private Label UserName;

    @FXML
    private Button commentPostBtn;

    @FXML
    private VBox container;

    @FXML
    private MenuItem deletePostBtn;

    @FXML
    private Button disLikePostBtn;

    @FXML
    private ImageView disLikePostIcon;

    @FXML
    private Button likePostBtn;

    @FXML
    private Label nbLikesPost;

    @FXML
    private BorderPane postItem;

    @FXML
    private Button rapportPostBtn;

    @FXML
    private Button savePostBtn;



    @FXML
    private ImageView shareIcon;

    @FXML
    private Button sharePostBtn;

    @FXML
    private MenuItem updatePostBtn;
 
    private  ForumController forumController = new ForumController();

    public MenuButton getAboutPostBtn() {return AboutPostBtn;}



    int nbvote=0;

    public void setData(Post p)
    {
        Image img = new Image(p.getUser().getImage());
        Profil_img.setFill(new ImagePattern(img));
        String usernamelastname = p.getUser().getFirstName()+ " " + p.getUser().getLastName();
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

        String styleBtnClicked = "-fx-background-color: transparent;" +
                " -fx-text-fill: white;" +
                "        -fx-border-radius: 5; " ;

        String styleBtnNormal = "    -fx-font-family: \"Jost Medium\";" +

                "    -fx-background-color: transparent;" +
                "    -fx-text-fill: #939393;" +
                "    -fx-cursor: hand;";
        likePostBtn.setStyle(styleBtnNormal);
        disLikePostBtn.setStyle(styleBtnNormal);
        PostService postService = new PostService();

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
            forumController.showPostComments(p);
            forumController.getAddCommentBtn().setOnAction(event-> forumController.addNewComment(p));

        });
   likePostBtn.setOnAction(event -> {
            if (likePostBtn.getStyle().equals(styleBtnNormal)) {
                likePostBtn.setStyle(styleBtnClicked);
                LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-haut-32 (2).png")));
                int idlike = postService.likeExist(p,forumController.getUser());
            if (idlike==0) {
                    postService.addLike(p,forumController.getUser(),1);
                    nbLikesPost.setText(postService.getLikes(p)+"");
            }else {
             postService.updateNbLikes(idlike,1);
             nbLikesPost.setText(postService.getLikes(p)+"");
             }
                disLikePostBtn.setStyle(styleBtnNormal);
                disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-haut-32 (3).png")));
              } else {
                likePostBtn.setStyle(styleBtnNormal);
                LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/upvote.png")));
                int idlike = postService.likeExist(p,forumController.getUser());
                if (idlike!=0) postService.deleteLike(idlike);
                nbLikesPost.setText(postService.getLikes(p)+"");

        }
       forumController.ShowPopularPeople();
        });
   disLikePostBtn.setOnAction(event -> {
            if (disLikePostBtn.getStyle().equals(styleBtnNormal)) {
                disLikePostBtn.setStyle(styleBtnClicked);
                disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-haut-32 (1).png")));

                likePostBtn.setStyle(styleBtnNormal);
                LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/upvote.png")));
                int idlike = postService.likeExist(p,forumController.getUser());
                if (idlike==0) {
                    postService.addLike(p,forumController.getUser(),-1);
                    nbLikesPost.setText(postService.getLikes(p)+"");
                }else {
                    postService.updateNbLikes(idlike,-1);
                    nbLikesPost.setText(postService.getLikes(p)+"");
                }
            } else {
                disLikePostBtn.setStyle(styleBtnNormal);
                disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-haut-32 (3).png")));
                int idlike = postService.likeExist(p,forumController.getUser());
                if (idlike!=0) postService.deleteLike(idlike);
                nbLikesPost.setText(postService.getLikes(p)+"");
             }
            forumController.ShowPopularPeople();
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

        });


   sharePostBtn.setOnMouseClicked(e -> {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();

            webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    webEngine.executeScript("updatePostField('" + p.getContent() + "')");
                }
            });
            webView.getEngine().load(getClass().getResource("/Api/Share.html").toExternalForm());
        });
   savePostBtn.setOnAction(e->{
            System.out.println( postService.getLikes(p));

        });



              setReactions(p,forumController.getUser());
              nbLikesPost.setText(postService.getLikes(p)+"");


    }





    public void setReactions(Post post,User user) {

        String styleBtnClicked = "-fx-background-color: transparent;" +
                " -fx-text-fill: white;" +
                "        -fx-border-radius: 5; " ;

        PostService postService = new PostService();
        int reaction = postService.liketype(post,user);
        if(reaction!=0){

            switch (reaction){
                case 1:
                    likePostBtn.setStyle(styleBtnClicked);
                    LikeIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-haut-32 (2).png")));
                    break;
                case -1:
                    disLikePostBtn.setStyle(styleBtnClicked);
                    disLikePostIcon.setImage(new Image(getClass().getResourceAsStream("/img/dark/icons8-flèche-haut-32 (1).png")));
                    break;
            }

        }
    }






    public void setForumController(ForumController forumController) {
        this.forumController = forumController;
    }


}


