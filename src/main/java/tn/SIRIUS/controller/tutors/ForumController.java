package tn.SIRIUS.controller.tutors;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import tn.SIRIUS.entities.Comment;
import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.Report;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.CommentService;
import tn.SIRIUS.services.PostService;
import tn.SIRIUS.services.ReportService;
import tn.SIRIUS.services.UserService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;

public class ForumController implements Initializable {
    @FXML
    private Button NewAttachment;

    @FXML
    private TextArea NewPostText;


    @FXML
    private VBox PopularpeopleConatainer;

    @FXML
    private Circle User_image;

    @FXML
    private Label User_name;

    @FXML
    private Label aboutReportLabel;

    @FXML
    private Button abuseBtn;

    @FXML
    private Button addCommentBtn;

    @FXML
    private VBox allCommentsContainer;

    @FXML
    private Button allPosts;

    @FXML
    private Button cancelDeletePostBtn;

    @FXML
    private Button cancelUpadteBtn;

    @FXML
    private Button closeCommentsConatainerBtn;

    @FXML
    private Button closeReportPostContainer;

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
    private Button copyrightBtn;

    @FXML
    private BorderPane createPostContainer;

    @FXML
    private AnchorPane deletePostContainer;

    @FXML
    private VBox forum;

    @FXML
    private Button harassmentBtn;

    @FXML
    private Button hateBtn;

    @FXML
    private Button impersonationBtn;

    @FXML
    private Button intimateMediaBtn;

    @FXML
    private Rectangle newCommentAttachment;

    @FXML
    private Button newCommentAttachmentBtn;

    @FXML
    private VBox newCommentContainer;

    @FXML
    private TextArea newCommentInput;

    @FXML
    private Circle newCommentUserImg;

    @FXML
    private Rectangle newImage;

    @FXML
    private Button personalPosts;


    @FXML
    private Button prohibitedBtn;


    @FXML
    private AnchorPane reportPostConatainer;

    @FXML
    private Label reportTypeLabel;

    @FXML
    private Button savedPosts;


    @FXML
    private TextField searchPost;

    @FXML
    private Button selfHarmBtn;

    @FXML
    private Button sharePersonalInfoBtn;

    @FXML
    private AnchorPane showCommentsConatainer;

    @FXML
    private Button spamBtn;

    public Button getSubmitReportBtn() {
        return submitReportBtn;
    }

    @FXML
    private Button submitReportBtn;


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

    @FXML
    private Button violenceBtn;
    public AnchorPane getReportPostConatainer() {
        return reportPostConatainer;
    }
    public Button getCloseCommentsConatainerBtn() {
        return closeCommentsConatainerBtn;
    }
    public VBox getAllCommentsContainer() {
        return allCommentsContainer;
    }
    public AnchorPane getShowCommentsConatainer() {
        return showCommentsConatainer;
    }
    public Button getAddCommentBtn() {
        return addCommentBtn;
    }

    private List<Comment> comments = new ArrayList<>();
   List<Post> acuill = new ArrayList<>();
    String attachmentPath;
    String newattachmentPath;
    int idposttodelete;
    String newCommentAttachmentPath;
    private boolean showAllPosts = true;

    public boolean isSavedPosts() {
        return isSavedPosts;
    }

    private boolean isSavedPosts = false;
    public AnchorPane getDeletePostContainer() {return deletePostContainer;}

    public void setIdposttodelete(int idposttodelete) {this.idposttodelete = idposttodelete;}


    UserService userService = new UserService();

    public User getUser() {
        return user;
    }

    public User user=userService.getPostById(1);

    private static final String NEUTRINO_API_ENDPOINT = "https://neutrinoapi.com/bad-word-filter";
    private static final String API_USER_ID = "ahmeeeed";
    private static final String API_KEY = "h0TYS2ew9NwC03UJnSFd9zTDCPCr3FZsJuJAG7qjvEs81k0y";


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
            ShowPopularPeople();

        });
        cancelDeletePostBtn.setOnAction(e->{
            deletePostContainer.setVisible(false);
            idposttodelete= Integer.parseInt(null);
        });

        updatePostContainer.setVisible(false);
        deletePostContainer.setVisible(false);
        showCommentsConatainer.setVisible(false);
        reportPostConatainer.setVisible(false);



        newCommentAttachmentBtn.setOnAction(e-> newCommentAttachmentPath = newCommentAttachmentWindow());
        personalPosts.getStyleClass().remove("top-bar-button-selected");
        savedPosts.getStyleClass().remove("top-bar-button-selected");
        allPosts.getStyleClass().add("top-bar-button-selected");

closeReportPostContainer.setOnAction(e->{
    reportPostConatainer.setVisible(false);
    reportTypeLabel.setText("");
    aboutReportLabel.setText("");
});

        abuseBtn.setOnAction(e->{
            setReportData("Abuse","Sharing or soliciting content involving abuse, neglect, or sexualization of minors or any predatory or inappropriate behavior towards minors.");
        });
        copyrightBtn.setOnAction(e->{
            setReportData("Copyright","Sharing or soliciting content involving copyright violations, or any other content that infringes on the rights of others.");
        });
        harassmentBtn.setOnAction(e->{
            setReportData("Harassment","Sharing or soliciting content involving harassment, intimidation, or any other forms of offensive behavior.");
        });
        hateBtn.setOnAction(e->{
            setReportData("Hate speech","Sharing or soliciting content involving hate speech, or any other content that depicts or depicts hate, prejudice, or discrimination.");
        });
        impersonationBtn.setOnAction(e->{
            setReportData("Impersonation","Sharing or soliciting content involving impersonation, or any other content that depicts or depicts an impersonation or pretending to be someone else.");
        });
        intimateMediaBtn.setOnAction(e->{
            setReportData("Non-consensual intimate media","Sharing, threatening to share, or soliciting intimate or sexually-explicit content of someone without their consent (including fake or lookalike pornography).");
        });
        prohibitedBtn.setOnAction(e->{
            setReportData("Prohibited content","Sharing or soliciting content that is prohibited by law or regulations.");
        });
        selfHarmBtn.setOnAction(e->{
            setReportData("Self-harm","Sharing or soliciting content involving self-harm, suicide, or any other forms of inappropriate behavior towards others.");
        });
        sharePersonalInfoBtn.setOnAction(e->{
            setReportData("Sharing personal information","Sharing or soliciting personal information, such as names, email addresses, or phone numbers.");
        });
        spamBtn.setOnAction(e->{
            setReportData("Spam","Sharing or soliciting content that is not relevant to the topic or content of the forum.");
        });
        violenceBtn.setOnAction(e->{
            setReportData("Violence","Sharing or soliciting content involving violence, sexual or otherwise inappropriate behavior, or any other forms of offensive or harmful behavior.");
        });


    }


    public void addNewPost() {
        if(!NewPostText.getText().isEmpty() || attachmentPath != null) {


            LocalDateTime currentDateTime = LocalDateTime.now();
            String newPostText = NewPostText.getText();
            newPostText = containsBadWords(newPostText) ? convertToCensoredText(newPostText) : newPostText;
            Post post = new Post(1, newPostText, attachmentPath, user.getIdUser(), currentDateTime);
            PostService postService = new PostService();
            if (postService.add(post) == 1) {
                acuill = postService.getAll();
                showPosts();

            }

        }

        ShowPopularPeople();
        attachmentPath = null;
        NewPostText.clear();
        newImage.setHeight(0);
        newImage.setVisible(false);

    }


    public void showPosts() {
        if (forum.getChildren().size() > 1) {
            forum.getChildren().remove(2, forum.getChildren().size()); // Remove all children except the first one
            forum.getChildren().add(createPostContainer);
        }
        if (isSavedPosts) {
            showSavedPosts();
        } else{

        Collections.sort(acuill, (c1, c2) -> c2.getPostedDate().compareTo(c1.getPostedDate()));
        try {
            for (Post post : acuill) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/tutors/Post.fxml"));
                Parent p = fxmlLoader.load();
                PostItem postItem = fxmlLoader.getController();
                postItem.setForumController(this);
                postItem.setData(post);
                if (showAllPosts) {
                    forum.getChildren().add(p);
                    postItem.getAboutPostBtn().setVisible(false);
                } else if (post.getUser().getIdUser() == user.getIdUser()) {
                    forum.getChildren().add(p);
                    postItem.getAboutPostBtn().setVisible(true);

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }}

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
        PostService postService = new PostService();
        acuill = postService.getAll();

        if (event.getSource() == allPosts) {
            personalPosts.getStyleClass().remove("top-bar-button-selected");
            savedPosts.getStyleClass().remove("top-bar-button-selected");
            allPosts.getStyleClass().add("top-bar-button-selected");
            forum.getChildren().removeAll();
            showAllPosts = true;
            isSavedPosts = false;
            showPosts();
        } else if (event.getSource() == personalPosts) {
            allPosts.getStyleClass().remove("top-bar-button-selected");
            savedPosts.getStyleClass().remove("top-bar-button-selected");
            personalPosts.getStyleClass().add("top-bar-button-selected");
            showAllPosts = false;
            isSavedPosts = false;
            showPosts();
        }else if (event.getSource()==savedPosts){
            allPosts.getStyleClass().remove("top-bar-button-selected");
            savedPosts.getStyleClass().add("top-bar-button-selected");
            personalPosts.getStyleClass().remove("top-bar-button-selected");

            isSavedPosts = true;
            showAllPosts = false;
            showPosts();
        }


    }


    void recover(Post post) throws IOException {


        Image img = new Image(user.getImage());
       updtePostuserimg.setFill(new ImagePattern(img));
       updatePostUsername.setText(user.getFirstName()+" "+user.getLastName());
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

        LocalDateTime currentDateTime = LocalDateTime.now();
        updatePostText.setText(convertToCensoredText(updatePostText.getText()));
        String updatedcentent = updatePostText.getText();
        updatedcentent = containsBadWords(updatedcentent) ? convertToCensoredText(updatedcentent) : updatedcentent;
        Post updatedpost = new Post(post.getIdPost(), updatedcentent, newattachmentPath, user.getIdUser(), post.getPostedDate());
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/tutors/popularPeopleItem.fxml"));
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
        newCommentUserImg.setFill(new ImagePattern(new Image(user.getImage())));
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
                    fxmlLoader.setLocation(getClass().getResource("/gui/tutors/commentItem.fxml"));
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

        if (newCommentInput.getText() != null && !newCommentInput.getText().isEmpty()) {
            newCommentInput.setText(convertToCensoredText(newCommentInput.getText()));
            String newCommentText = newCommentInput.getText();
            newCommentText = containsBadWords(newCommentText) ? convertToCensoredText(newCommentText) : newCommentText;
            Comment comment = new Comment(0, p , user, newCommentText, newCommentAttachmentPath);
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
        newCommentInput.clear();
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





    @FXML
    void search(KeyEvent event) {
        String searchText = searchPost.getText();
        List<Post> acuillF = new ArrayList<>();
        if (!searchText.isEmpty()) {
            acuillF = acuill.stream()
                    .filter(item -> item.getContent().contains(searchText)).toList();
            if (forum.getChildren().size() > 1) {
                forum.getChildren().remove(3, forum.getChildren().size());
            }
            try {
                for (Post post : acuillF) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/tutors/Post.fxml"));
                    Parent p = fxmlLoader.load();
                    PostItem postItem = fxmlLoader.getController();
                    postItem.setForumController(this);
                    postItem.setData(post);
                    if (showAllPosts) {
                        forum.getChildren().add(p);
                        postItem.getAboutPostBtn().setVisible(false);
                    } else if (post.getUser().getIdUser() == user.getIdUser()) {
                        forum.getChildren().add(p);
                        postItem.getAboutPostBtn().setVisible(true);

                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            showPosts();
        }

    }


public void showSavedPosts(){
    if (forum.getChildren().size() > 1) {
        forum.getChildren().remove(2, forum.getChildren().size());
    }
    List<Post> savedPosts = new ArrayList<>();
    PostService postService = new PostService();
    savedPosts = postService.getSavedPosts(user);
    try {
        for (Post post : savedPosts) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/gui/tutors/Post.fxml"));
            Parent p = fxmlLoader.load();
            PostItem postItem = fxmlLoader.getController();
            postItem.setForumController(this);
            postItem.getAboutPostBtn().setVisible(false);
            postItem.setData(post);
            forum.getChildren().add(p);
        }
} catch (IOException e) {
        throw new RuntimeException(e);
    }
    }


    public static boolean containsBadWords(String content) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            String encodedContent = URLEncoder.encode(content, "UTF-8");
            HttpGet request = new HttpGet(NEUTRINO_API_ENDPOINT + "?content=" + encodedContent);
            request.addHeader("User-ID", API_USER_ID);
            request.addHeader("API-Key", API_KEY);
            HttpResponse response = httpClient.execute(request);

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            JSONObject jsonResponse = new JSONObject(result.toString());
            System.out.println("JSON Response: " + result.toString());

            if (jsonResponse.has("is-bad")) {
                return jsonResponse.getBoolean("is-bad");
            } else {
                // Assume no bad words if "is-bad" field is not present
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Assume no bad words in case of an error
        }
    }

    public static String convertToCensoredText(String text) {
        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (containsBadWords(words[i])) {
                words[i] = censorProfanity(words[i]);
            }
        }
        return String.join(" ", words);
    }

    private static String censorProfanity(String text) {
        return "*".repeat(text.length());
    }



    public void setReportData(String type,String Content){
        reportTypeLabel.setText(type);
        aboutReportLabel.setText(Content);
    }
    public void reportPost(Post post){
        ReportService service = new ReportService();
        Report report = new Report( 1,post,reportTypeLabel.getText(),aboutReportLabel.getText(),1);
       int id = service.ReportExist(report);
        if(reportTypeLabel.getText()!="" && aboutReportLabel.getText()!=""){
            if (id!=0){
                service.updateNbrReports(report.getPost().getIdPost());
            } else {
                service.reportPost(report);
            }
            reportPostConatainer.setVisible(false);
            reportTypeLabel.setText("");
            aboutReportLabel.setText("");
        }else System.out.println("error report");

    }
}









