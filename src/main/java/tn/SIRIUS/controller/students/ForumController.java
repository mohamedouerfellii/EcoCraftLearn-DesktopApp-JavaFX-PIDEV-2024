package tn.SIRIUS.controller.students;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import tn.SIRIUS.controller.tutors.CourseDashboardItemController;
import tn.SIRIUS.entities.Comment;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.Post;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CommentService;
import tn.SIRIUS.services.PostService;
import tn.SIRIUS.services.UserService;

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
    private Button New_PostBtn;

    @FXML
    private VBox PopularpeopleConatainer;

    @FXML
    private Circle User_image;

    @FXML
    private Label User_name;
    public Button getAddCommentBtn() {
        return addCommentBtn;
    }

    @FXML
    private Button addCommentBtn;

    public VBox getAllCommentsContainer() {
        return allCommentsContainer;
    }

    public void setAllCommentsContainer(VBox allCommentsContainer) {
        this.allCommentsContainer = allCommentsContainer;
    }

    @FXML
    private VBox allCommentsContainer;


    @FXML
    private ScrollPane scrollPaneAllcomments;


    @FXML
    private Button allPosts;

    @FXML
    private Button cancelDeletePostBtn;

    @FXML
    private Button cancelUpadteBtn;

    @FXML
    private VBox commentPostContentContainer;

    @FXML
    private Text commentPostContentText;

    @FXML
    private Label commentPostPostedDate;

    @FXML
    private Circle commentPostUserImg;

    @FXML
    private Label commentPostUserName;

    @FXML
    private Button confirmDeletePostBtn;

    @FXML
    private BorderPane createPostContainer;

    @FXML
    private AnchorPane deletePostContainer;

    @FXML
    private VBox forum;

    @FXML
    private Button newCommentAttachmentBtn;

    @FXML
    private VBox newCommentContainer;

    @FXML
    private TextArea newCommentIput;

    @FXML
    private Rectangle newImage;

    @FXML
    private Button personalPosts;

    @FXML
    private AnchorPane postsMainContent;

    @FXML
    private AnchorPane showCommentsConatainer;

    @FXML
    private BorderPane updatePostBorderPane;

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

    public Button getCloseCommentsConatainerBtn() {
        return closeCommentsConatainerBtn;
    }

    @FXML
    private Button closeCommentsConatainerBtn;

    @FXML
    private Rectangle newCommentAttachment;




    public AnchorPane getShowCommentsConatainer() {
        return showCommentsConatainer;
    }
    private List<Comment> comments = new ArrayList<>();
   List<Post> acuill = new ArrayList<>();
    String attachmentPath;
    String newattachmentPath;
    int idposttodelete;
    String newCommentAttachmentPath;
    private boolean showAllPosts = true;


    public Button getConfirmDeletePostBtn() {return confirmDeletePostBtn;}
    public AnchorPane getPostsMainContent() {
        return postsMainContent;
    }
    public AnchorPane getDeletePostContainer() {return deletePostContainer;}
    public int getIdposttodelete() {return idposttodelete;}

    public void setIdposttodelete(int idposttodelete) {this.idposttodelete = idposttodelete;}


    UserService userService = new UserService();

    public User getUser() {
        return user;
    }

    public User user=userService.getPostById(1);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image userimg = new Image(user.getImage());
        User_image.setFill(new ImagePattern(userimg));
        User_name.setText(user.getFirstName()+ " "+user.getLastName());
        PostService postService = new PostService();
        acuill = postService.getAll();
        showPosts();
        ShowPopularPeople();
        NewAttachment.setOnAction(event -> {
            newImage.setVisible(true);
            attachmentPath = openAttachmentWindow();

        });
        updatePostattachment.setOnAction(e->{
            newattachmentPath = UpdateopenAttachmentWindow();
        });
        cancelUpadteBtn.setOnAction(e->{
            updatePostContainer.setVisible(false);
            newattachmentPath=null;
        });

        confirmDeletePostBtn.setOnAction(e->{
            LocalDateTime d = null;
          Post deletepost= new Post(idposttodelete,"","",1,d);
            if (postService.delete(deletepost)==1) {
                acuill = postService.getAll();
                showPosts();
                deletePostContainer.setVisible(false);

            }

        });
        cancelDeletePostBtn.setOnAction(e->{
            deletePostContainer.setVisible(false);
            idposttodelete= Integer.parseInt(null);
        });

        updatePostContainer.setVisible(false);
        deletePostContainer.setVisible(false);
        showCommentsConatainer.setVisible(false);



        newCommentAttachmentBtn.setOnAction(e-> newCommentAttachmentPath = newCommentAttachmentWindow());



    }


    public void addNewPost() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String newPostText = NewPostText.getText();
        Post post = new Post(1, newPostText, attachmentPath, user.getIdUser(), currentDateTime);
        PostService postService = new PostService();
        if (postService.add(post) == 1) {
            acuill = postService.getAll();
            showPosts();

        }


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
        Collections.sort(acuill, (c1, c2) -> c2.getPostedDate().compareTo(c1.getPostedDate()));
        try {
            for (Post post : acuill) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/Post.fxml"));
                Parent p = fxmlLoader.load();
                PostItem postItem = fxmlLoader.getController();
                postItem.setForumController(this);
                postItem.setData(post);


                if (showAllPosts) {
//                    postItem.ReactionPressed();
                    forum.getChildren().add(p);
                    postItem.getAboutPostBtn().setVisible(false);
                } else if (post.getUser().getIdUser() == user.getIdUser()) { // Assuming getId() returns the user ID
                    forum.getChildren().add(p);
                    postItem.getAboutPostBtn().setVisible(true);

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
       updatePostImg.setFill(new ImagePattern(img1));
       updatePostImg.setVisible(true);
       }
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

            updatePostContainer.setVisible(false);
            acuill = postService.getAll();
            showPosts();
        } else System.out.println("error");
        newattachmentPath=null;
    }








    public void ShowPopularPeople() {
        PopularpeopleConatainer.getChildren().clear();
        PostService postService = new PostService();
        Map<User, Integer> popularUsers = postService.PopularUser();
        for (Map.Entry<User, Integer> entry : popularUsers.entrySet()) {
            User usr = entry.getKey();
            int nblike = entry.getValue();
            System.out.println(1);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/students/popularPeopleItem.fxml"));
                HBox hBox = fxmlLoader.load();
                PopularPeopleItemController popularPeopleItemController = fxmlLoader.getController();
                popularPeopleItemController.SetPopularData(nblike, usr);
                PopularpeopleConatainer.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }





public void showPostComments(Post post) {
    if (allCommentsContainer.getChildren().size() > 0) {
        allCommentsContainer.getChildren().remove(1, allCommentsContainer.getChildren().size());
        commentPostContentContainer.getChildren().remove(1, commentPostContentContainer.getChildren().size());
        commentPostContentContainer.setPrefHeight(50);}
        Image img = new Image(post.getUser().getImage());
        commentPostUserImg.setFill(new ImagePattern(img));
        commentPostUserName.setText(post.getUser().getFirstName() + post.getUser().getLastName());
        commentPostPostedDate.setText(post.getPostedDate().toString());
        commentPostContentText.setText(post.getContent());
        String imageUrl = post.getAttachment();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Image image = new Image(imageUrl);
            StackPane stackPane = new StackPane();
            stackPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            ImageView imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            stackPane.setPrefHeight(image.getHeight() * 0.3);
            stackPane.setPrefWidth(image.getWidth() * 0.3);
            stackPane.setMaxWidth(600);
            imageView.setFitWidth(image.getWidth() * 30 / 100);
            imageView.setFitHeight(image.getHeight() * 30 / 100);
            DoubleBinding scaledWidth = Bindings.createDoubleBinding(
                    () -> stackPane.widthProperty().get() * 0.5,
                    stackPane.widthProperty()
            );
            imageView.fitWidthProperty().bind(scaledWidth);
            imageView.fitHeightProperty().bind(stackPane.heightProperty());
            stackPane.getChildren().add(imageView);
            commentPostContentContainer.getChildren().add(stackPane);
            commentPostContentContainer.setPrefHeight(image.getHeight() + 125 + 50);
        }
        CommentService commentService = new CommentService();
        comments = commentService.getAll();
        for (Comment comment : comments) {
            if (comment.getPost().getIdPost() == post.getIdPost()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/students/commentItem.fxml"));
                    Parent root = fxmlLoader.load();
                    CommentItemController item = fxmlLoader.getController();
                    item.setForumController(this);
                    item.setCommentItemdata(comment);
                    if (comment.getOwner().getIdUser() == user.getIdUser()) {
                       item.getABoutCommentBtn().setVisible(true);
                    }else {
                        item.getABoutCommentBtn().setVisible(false);
                    }

                    allCommentsContainer.getChildren().add(root);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        }
        int lastIndex = allCommentsContainer.getChildren().size() - 1;
        if (lastIndex >= 1) {
            Parent lastRoot = (Parent) allCommentsContainer.getChildren().get(lastIndex);
            lastRoot.setStyle("-fx-background-color: white;" +
                    "        -fx-border-color: none;" +
                    "        -fx-border-radius: 20 20 0  0;" +
                    "        -fx-background-radius: 0 0 20 20;");
        }

        closeCommentsConatainerBtn.setOnAction(e ->
        {
            showCommentsConatainer.setVisible(false);


        });



}




public  void addNewComment(Post p){

        if (newCommentIput.getText() != null && !newCommentIput.getText().isEmpty()) {
            Comment comment = new Comment(0, p , user, newCommentIput.getText(), newCommentAttachmentPath);
            CommentService commentService = new CommentService();
            if (commentService.add(comment) == 1)
                System.out.println("true");
            else System.out.println("error");
        }
        showPostComments(p);
        newCommentContainer.setMaxHeight(67);
        newCommentAttachment.setHeight(0);
        newCommentAttachment.setVisible(false);
        newCommentAttachmentPath = null;
        newCommentIput.clear();
}


    private String newCommentAttachmentWindow() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            Image img = new Image("file:/" + selectedFile.getAbsolutePath());
            newCommentAttachment.setFill(new ImagePattern((img)));
            newCommentAttachment.setVisible(true);
            newCommentAttachment.setHeight(img.getHeight() * 0.3);
            newCommentAttachment.setWidth(img.getWidth() * 0.3);
            return newCommentAttachmentPath = "file:/" + selectedFile.getAbsolutePath();
        }
        return null;

    }



public void  deleteComment(Comment comment) {


        PostService postService = new PostService();
    Post post = postService.getPostById(comment.getPost().getIdPost());
    CommentService commentService = new CommentService();
    if (commentService.delete(comment) == 1) {
        System.out.println("true");
        showPostComments(post);
    } else System.out.println("error");


}






}


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







