package tn.SIRIUS.controller.students;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tn.SIRIUS.entities.Post;

public class UpdatePostController {


    @FXML
    private BorderPane createPostContaner;

    @FXML
    private Button updatePostBtn;

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
    void openAttachmentWindow(ActionEvent event) {

    }

   public void setUpdatePostData(Post post){


   }


}
