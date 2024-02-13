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
    private Button NewAttachment;

    @FXML
    private TextArea NewPostText;

    @FXML
    private Button New_PostBnt;

    @FXML
    private VBox PopularpeopleConatainer;

    @FXML
    private Circle User_image;

    @FXML
    private Label User_name;

    @FXML
    private Button allPosts;

    @FXML
    private VBox forum;

    @FXML
    private Rectangle newImage;

    @FXML
    private Button personalPosts;

    @FXML
    private AnchorPane postsMainContent;

    @FXML
    private Button updatePostBtn;

    @FXML
    private AnchorPane updatePostContainer;

    @FXML
    private Rectangle updatePostImg;

    @FXML
    private TextArea updatePostText;

    @FXML
    private Label updatePostUsername;

    @FXML
    private Button updatePostattachment;

    @FXML
    private Circle updtePostuserimg;


    Map<Post, User> acuill = new HashMap<>();
    String attachmentPath;
    String newattachmentPath;

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
        updatePostattachment.setOnAction(e->{
            newattachmentPath = UpdateopenAttachmentWindow();
        });
        updatePostContainer.setVisible(false);



    }


    public void addNewPost() {
        String username = "Jarray abdelmonam";
        String img = "img/img.png";
        int idUser = 2;
        LocalDateTime currentDateTime = LocalDateTime.now();
        String newPostText = NewPostText.getText();
        Post post = new Post(1, newPostText, attachmentPath, idUser, currentDateTime);
        PostService postService = new PostService();
        if (postService.add(post) == 1) {
            acuill = postService.getAll();
            showPosts();

        } else
            System.out.println("FAILED");

        //ShowPopularPeople();
        attachmentPath = null;
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
            Image img = new Image("file:/" + selectedFile.getAbsolutePath());
            newImage.setFill(new ImagePattern((img)));
            newImage.setVisible(true);
            newImage.setHeight(img.getHeight() * 50 / 100);
            newImage.setWidth(img.getWidth() * 50 / 100);
            Text text = new Text(NewPostText.getText());
            double textHeight = text.getBoundsInLocal().getHeight();
            NewPostText.setPrefWidth(600);
            NewPostText.setPrefHeight(textHeight);
            return attachmentPath = "file:/" + selectedFile.getAbsolutePath();
        }
        newImage.setVisible(false);
        return null;

    }


    private String UpdateopenAttachmentWindow() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image img = new Image("file:/" + selectedFile.getAbsolutePath());
            updatePostImg.setFill(new ImagePattern((img)));
            updatePostImg.setVisible(true);
            updatePostImg.setHeight(img.getHeight() * 50 / 100);
            updatePostImg.setWidth(img.getWidth() * 50 / 100);
            return newattachmentPath = "file:/" + selectedFile.getAbsolutePath();
        }
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


    void recover(Post post) throws IOException {


        Image img = new Image(getClass().getResourceAsStream("/img/img.png"));
       updtePostuserimg.setFill(new ImagePattern(img));
        updatePostText.setWrapText(true);
       updatePostText.setText(post.getContent());
       if (post.getAttachment()!=null && !post.getAttachment().isEmpty()){
       Image img1 = new Image(post.getAttachment());

       updatePostImg.setWidth(img1.getWidth()*30/100);
       updatePostImg.setHeight(img1.getHeight()*30/100);
       updatePostImg.setFill(new ImagePattern(img));
           updatePostImg.setVisible(true);}
        updatePostContainer.setVisible(true);
       updatePostBtn.setOnAction(e->{UpdatePost(post);});

    }


    public void UpdatePost(Post post) {
        if(newattachmentPath==null){
            newattachmentPath= post.getAttachment();
        }
        String username = "Jarray abdelmonam";
        String img = "img/img.png";
        int idUser = 2;
        LocalDateTime currentDateTime = LocalDateTime.now();
        Post updatedpost = new Post(post.getIdPost(), updatePostText.getText(), newattachmentPath, idUser, post.getPostedDate());
        PostService postService = new PostService();
        if (postService.update(updatedpost) == 1) {
            System.out.println("done");
        } else System.out.println("error");
        attachmentPath=null;
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





