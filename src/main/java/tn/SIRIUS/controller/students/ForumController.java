package tn.SIRIUS.controller.students;

import tn.SIRIUS.controller.tutors.CourseDashboardItemController;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.Post;
import com.sun.javafx.collections.MappingChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.PostService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ForumController implements Initializable {
    @FXML
    private VBox forum;
    @FXML
    private Button NewAttachment;

    @FXML
    private TextArea NewPostText;

    @FXML
    private Button New_PostBnt;

    @FXML
    private Circle User_image;

    @FXML
    private Label User_name;
    @FXML
    private Rectangle newImage;

    @FXML
    private BorderPane createPostContaner;
    @FXML
    private TextField SearchPost;
    @FXML
    private Button allPosts;

    @FXML
    private Button personalPosts;

    @FXML
    private VBox PopularpeopleConatainer;

    @FXML
    private AnchorPane postsMainContent;

    Map<Post, User> acuill = new HashMap<>();
    String attachmentPath;

    private boolean showAllPosts = true;
    public AnchorPane getPostsMainContent() {
        return postsMainContent;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image userimg = new Image(getClass().getResourceAsStream("/img/img.png"));
        User_image.setFill(new ImagePattern(userimg));
        User_name.setText("Jarray abdelmonam");
        PostService postService = new PostService();
        acuill = postService.getAll();
       showPosts();
      //  ShowPopularPeople();
        NewAttachment.setOnAction(event -> {
            newImage.setVisible(true);
             attachmentPath = openAttachmentWindow();

        });
        PostItem postItem = new PostItem();


    }



    public void  addNewPost(){
        String username = "Jarray abdelmonam";
        String img = "img/img.png";
        int idUser=2;
        LocalDateTime currentDateTime = LocalDateTime.now();
        String newPostText = NewPostText.getText();
        Post post = new Post(1,newPostText,attachmentPath,idUser,currentDateTime);
        PostService postService = new PostService();
        if(postService.add(post) == 1){
            acuill = postService.getAll();
            showPosts();

        }
        else
            System.out.println("FAILED");

    //ShowPopularPeople();
    attachmentPath=null;
    NewPostText.clear();
    newImage.setHeight(0);
    newImage.setVisible(false);

    }




    public void showPosts() {
        if (forum.getChildren().size() > 1) {
            forum.getChildren().remove(3, forum.getChildren().size()); // Remove all children except the first one
        }

        try {
            for (Map.Entry<Post, User> entry : acuill.entrySet()) {
                Post a = entry.getKey();
                User user = entry.getValue();
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/Post.fxml"));
                Parent p = fxmlLoader.load();
                PostItem postItem = fxmlLoader.getController();
                postItem.setForumController(this);
                postItem.setData(a, user);


                if (showAllPosts) {
                    postItem.LikePressed();

                    forum.getChildren().add(p);
                } else if (user.getIdUser() == 1) { // Assuming getId() returns the user ID
                    forum.getChildren().add(p);

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private String openAttachmentWindow() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image  img = new Image("file:/"+selectedFile.getAbsolutePath());
            newImage.setFill(new ImagePattern((img)));
            newImage.setVisible(true);
            newImage.setHeight(img.getHeight()*50/100);
            newImage.setWidth(img.getWidth()*50/100);
            Text text = new Text(NewPostText.getText());
            double textHeight = text.getBoundsInLocal().getHeight();
            NewPostText.setPrefWidth(600);
           NewPostText.setPrefHeight(textHeight);
           return attachmentPath = "file:/"+selectedFile.getAbsolutePath();
        }
        newImage.setVisible(false);
        return null;

    }


    @FXML
    void TopBarBtnClicked(ActionEvent event) {
        if (event.getSource() == allPosts) {
            personalPosts.getStyleClass().remove("top-bar-button-selected");
            allPosts.getStyleClass().add("top-bar-button-selected");
            forum.getChildren().removeAll();
            showAllPosts = true;
            showPosts();
        } else if (event.getSource() == personalPosts) {
            allPosts.getStyleClass().remove("top-bar-button-selected");
            personalPosts.getStyleClass().add("top-bar-button-selected");
            showAllPosts = false;
            showPosts();
        }





        }


    void updatePressed(Post post) throws IOException {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/updatePost.fxml"));
                Parent root = fxmlLoader.load();
               postsMainContent.getChildren().clear();
                postsMainContent.getChildren().add(root);


            System.out.println(post.getIdPost());
        }


    }



//***************************************************************
   /* public void ShowPopularPeople() {

        for (int i = 0; i < acuill.size(); i++) {
            User user = new User(acuill.get(i).getUsername(), acuill.get(i).getImage());
            int nblike = acuill.get(i).getNbLikes();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FirstTry/View/popularPeopleItem.fxml"));
                HBox hBox = fxmlLoader.load();
                PopularPeopleItemController popularPeopleItemController = fxmlLoader.getController();
                popularPeopleItemController.SetPopularData(nblike, user);
                PopularpeopleConatainer.getChildren().add(hBox);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
*/

/*------------------------------------------------*/




      /*  List<Post> sortedPosts = acuill.stream()
                .sorted(Comparator.comparingInt(Post::getNbLikes).reversed())
                .collect(Collectors.toList());

        // Create a Map with nbLikes as key and User as value
        Map<Integer, User> popularPeopleMap = sortedPosts.stream()
                .collect(Collectors.toMap(Post::getNbLikes, Post::getUsername, (existing, replacement) -> existing, LinkedHashMap::new));

        // Print or process the popularPeopleMap
        popularPeopleMap.forEach((nbLikes, user) -> {
            System.out.println("Likes: " + nbLikes);
            System.out.println("User: " + user.getUsername());
            System.out.println("Image: " + user.getImage());
            // Add more processing as needed
        });*/

     //   }


    //}





