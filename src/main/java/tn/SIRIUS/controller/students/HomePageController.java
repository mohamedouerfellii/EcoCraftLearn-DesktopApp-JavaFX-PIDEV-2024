package tn.SIRIUS.controller.students;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.mortbay.jetty.security.Password;
import tn.SIRIUS.controller.SignUpController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import tn.SIRIUS.controller.tutors.DashboardAdminHomePageController;
import tn.SIRIUS.entities.Session;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.services.GRUDService;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

public class HomePageController implements Initializable {

    @FXML
    private Text emailLabelDetail;

    @FXML
    private Button eventsBtn;

    @FXML
    private ImageView eventsBtnImg;

    @FXML
    private Text firstNameLabelDetail;

    @FXML
    private Button forumBtn;

    @FXML
    private ImageView forumBtnImg;

    @FXML
    private Text genderLabelDetail;

    @FXML
    private HBox hboxitem;

    @FXML
    private AnchorPane homeAnchorPane;

    @FXML
    private Button homeBtn;

    @FXML
    private ImageView homeBtnImg;

    @FXML
    private Text lastNameLabelDetail;

    @FXML
    private Button logoutBtn;

    @FXML
    private ImageView logoutBtnImg;

    @FXML
    private Button message;

    @FXML
    private Label messageCunt;

    @FXML
    private Button mostPopularCoursesBtn;

    @FXML
    private Button mostProgressedCourseBtn;

    @FXML
    private Circle mostProgressedCourseImgContainer;

    @FXML
    private Arc mostProgressedCoursePrc;

    @FXML
    private Label mostProgressedCoursePrcVal;

    @FXML
    private Label mostProgressedCourseTitle;

    @FXML
    private Label mostProgressedCourseTutorName;

    @FXML
    private HBox msgCount;

    @FXML
    private Text nbrPtCollectLabelDetail;

    @FXML
    private Button newestCoursesBtn;

    @FXML
    private HBox notifCount;

    @FXML
    private Text numberLabelDetail;

    @FXML
    private Pane paneitem;

    @FXML
    private Button productsBtn;

    @FXML
    private ImageView productsBtnImg;

    @FXML
    private Circle profilImgContainer;

    @FXML
    private VBox vboxitem;

    @FXML
    private Label welcomeMsg;

    @FXML
    private Button coursesBtn;
    @FXML
    private ImageView coursesBtnImg;


    @FXML
    private Button collectsBtn;
    @FXML
    private ImageView collectsBtnImg;

    @FXML
    private VBox messagContainer;
    @FXML
    private ScrollPane coursesScrollPane;
    @FXML
    private VBox vboxMessage;
    @FXML
    private Pane blurPane;
    @FXML
    private Rectangle messageRectangle;

@FXML
private Button  arrow;
    @FXML
    private Button sendBtn;
    private BoxBlur blurEffect = new BoxBlur();
    private static DatagramSocket socket;
    private int count;

    static {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private static InetAddress address;

    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    ;
    @FXML
    private TextField textFieldMessage;
    @FXML
    private AnchorPane userCont;
    @FXML
    private ImageView userImage;
@FXML
private ScrollPane scrollPaneMessage;

    private static final int SERVER_PORT = 12345;
User loggingUser=Session.getUser();
@FXML
private void userContainer(ActionEvent event) {
    userCont.setVisible(!userCont.isVisible());
}
@FXML
private Pane detailsPane;
    @FXML
    public void closeDetailsPane(ActionEvent event){
        detailsPane.setVisible(false);
        blurEffect.setWidth(0);
        blurEffect.setHeight(0);
        // Create a timeline to animate the blur effect
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(0.5),
                        new KeyValue(blurEffect.widthProperty(), 0),
                        new KeyValue(blurEffect.heightProperty(), 0)
                )
        );
        paneitem.setEffect(blurEffect);
        vboxitem.setEffect(blurEffect);
        hboxitem.setEffect(blurEffect);

        // Play the timeline
        timeline.play();
    }
@FXML
private void closeChat(ActionEvent event) {
    blurPane.setVisible(false);
    editPane.setVisible(false);
    blurEffect.setWidth(0);
    blurEffect.setHeight(0);
    // Create a timeline to animate the blur effect
    Timeline timeline = new Timeline(
            new KeyFrame(javafx.util.Duration.seconds(0.5),
                    new KeyValue(blurEffect.widthProperty(), 0),
                    new KeyValue(blurEffect.heightProperty(), 0)
            )
    );
    paneitem.setEffect(blurEffect);
    vboxitem.setEffect(blurEffect);
    hboxitem.setEffect(blurEffect);

    // Play the timeline
    timeline.play();
    count = 0;
    messageCunt.setText(String.valueOf(count));
}
@FXML
private Circle imgCircle;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        User loggedInUser = Session.getUser();
        editPane.setVisible(false);
        detailsPane.setVisible(false);
        count = 0;
        profilImgContainer.setFill(new ImagePattern(new Image(loggedInUser.getImage())));
        welcomeMsg.setText("Welcome "+loggedInUser.getFirstName()+"!");
       vboxMessage.setStyle("-fx-background-color: #D5FFDC;");
        lastNameLabelDetail.setText(loggedInUser.getLastName());
        firstNameLabelDetail.setText(loggedInUser.getFirstName());
        emailLabelDetail.setText(loggedInUser.getEmail());
        numberLabelDetail.setText(String.valueOf(loggedInUser.getNumber()));
        genderLabelDetail.setText(loggedInUser.getGender());
        nbrPtCollectLabelDetail.setText(String.valueOf(loggedInUser.getNbrPtsCollects()));
        imgCircle.setFill(new ImagePattern(new Image(loggedInUser.getImage())));
        byte[] uuid = ("init;" + loggedInUser.getFirstName()+";").getBytes();
        DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
        try {
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

sendBtn.setOnAction(event1 -> {
    String temp =loggedInUser.getFirstName() + ": " +textFieldMessage.getText() + "\n"; // message to send
    HBox hbox = new HBox();
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setPadding(new Insets(5, 5, 5, 5));

    Label label = new Label(temp);
    label.setStyle("-fx-background-color: #D5FFDC;-fx-border-radius: 15 15 15 15; -fx-background-radius: 15 15 15 15;-fx-effect: dropshadow(gaussian, rgba(0.4,0,0,0.4), 10, 0, 0, 2); -fx-text-fill: black; -fx-font-family: 'Jost Medium'; -fx-font-size: 16px;");
    label.setWrapText(true);
    label.setPadding(new Insets(5, 10, 5, 10));
    vboxMessage.setSpacing(7);
    hbox.setPadding(new Insets(5, 10, 5, 10));
    hbox.getChildren().add(label);
    vboxMessage.getChildren().add(hbox);

    byte[] msg = temp.getBytes(); // convert to bytes
    textFieldMessage.clear(); // remove text from input box
    // create a packet & send
    DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);

    try {
        socket.send(send);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


});
        textFieldMessage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String temp = loggedInUser.getFirstName() + ": " + textFieldMessage.getText() + "\n"; // message to send
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER_RIGHT);
                hbox.setPadding(new Insets(5, 5, 5, 5));

                Label label = new Label(temp);
                label.setStyle("-fx-background-color: #D5FFDC;-fx-border-radius: 15 15 15 15; -fx-background-radius: 15 15 15 15;-fx-effect: dropshadow(gaussian, rgba(0.4,0,0,0.4), 10, 0, 0, 2); -fx-text-fill: black; -fx-font-family: 'Jost Medium'; -fx-font-size: 16px;");
                label.setWrapText(true); // Allow text to wrap to multiple lines if needed
                label.setPadding(new Insets(5, 10, 5, 10));
                vboxMessage.setSpacing(7);
                hbox.setPadding(new Insets(5, 10, 5, 10));
                hbox.getChildren().add(label);
                vboxMessage.getChildren().add(hbox);

                byte[] msg = temp.getBytes(); // convert to bytes
                textFieldMessage.clear(); // remove text from input box
                // create a packet & send
                DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);

                    try {
                        socket.send(send);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

            }

        });
        new Thread(new ClientThread()).start();
        }
    private class ClientThread implements Runnable {
        private final byte[] incoming = new byte[256];

        @Override
        public void run() {
            System.out.println("Starting thread");

            while (true) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length);

                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                String message = new String(packet.getData(), 0, packet.getLength());

                // Extract sender's name and message content
                String[] parts = message.split(":");
                String senderName = parts[0].trim();
                String actualMessage = parts[1].trim();

                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.setPadding(new Insets(5, 5, 5, 10));
                Label label = new Label(senderName + ": " + actualMessage);
                label.setStyle("-fx-background-color: #39932C;-fx-border-radius: 15 15 15 15; -fx-background-radius: 15 15 15 15;-fx-effect: dropshadow(gaussian, rgba(0.4,0,0,0.4), 10, 0, 0, 2); -fx-text-fill: black; -fx-font-family: 'Jost Medium'; -fx-font-size: 16px;");
                label.setPadding(new Insets(5, 10, 5, 10));
                label.setWrapText(true);
                hbox.getChildren().add(label);

                Platform.runLater(() -> {
                    vboxMessage.setSpacing(7);
                    vboxMessage.getChildren().add(hbox);

                    count++;
                    System.out.println(count);
                    messageCunt.setText(String.valueOf(count));
                });
            }
        }
    }

@FXML
public void showDetails(ActionEvent event){
    detailsPane.setVisible(true);
    applyBlurEffect();
    userCont.setVisible(false);

}
@FXML
private TextField editfirstnameUser;
    @FXML
    private TextField editlastnameuser;
    @FXML
    private TextField editemailuser;
    @FXML
    private TextField editgenderuser;
    @FXML
    private TextField editnumberuser;
    @FXML
    private TextField editpassworduser;
   private  String img=loggingUser.getImage();

    private  Image detailImg=new Image(img);

    @FXML
    private ImageView editimguser;
    @FXML
    private Pane editPane;
    @FXML
    public void editUserBtnClicked(ActionEvent event) throws IOException {
        detailsPane.setVisible(false);
        editPane.setVisible(true);
        editfirstnameUser.setText(firstNameLabelDetail.getText());
        editlastnameuser.setText(lastNameLabelDetail.getText());
        editemailuser.setText(emailLabelDetail.getText());
        editgenderuser.setText(genderLabelDetail.getText());
        editnumberuser.setText(String.valueOf(numberLabelDetail.getText()));
        editpassworduser.setText("");
        editimguser.setImage(detailImg);


    }
    @FXML
    public void deleteCourseBtnClicked(ActionEvent event) throws IOException {
        GRUDService userService=new GRUDService();
        userService.delete(loggingUser.getId());
        Session.logout();
        FXMLLoader fxmlLoader = new FXMLLoader(DashboardAdminHomePageController.class.getResource("/gui/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editUserButton(ActionEvent event){
        String Password;
        int id=loggingUser.getId();
        String firstName=editfirstnameUser.getText();
        String lastName=editlastnameuser.getText();
        String Email=editemailuser.getText();
        int number=Integer.parseInt(editnumberuser.getText());
        String gender=editgenderuser.getText();
        if(editpassworduser.getText().isEmpty()){
            Password=String.valueOf(loggingUser.getPassword());
        }else
         Password = String.valueOf(editpassworduser.getText().hashCode());

        GRUDService serv=new GRUDService();

        User user=new User(id, firstName, lastName, number, Email, gender, Password,img);


        if (serv.update(user))
        {
            profilImgContainer.setFill(new ImagePattern(new Image(user.getImage())));
            imgCircle.setFill(new ImagePattern(new Image(user.getImage())));
            welcomeMsg.setText("Welcome "+user.getFirstName()+"!");
            editPane.setVisible(false);
            detailsPane.setVisible(false);
            blurEffect.setWidth(0);
            blurEffect.setHeight(0);
            // Create a timeline to animate the blur effect
            Timeline timeline = new Timeline(
                    new KeyFrame(javafx.util.Duration.seconds(0.5),
                            new KeyValue(blurEffect.widthProperty(), 0),
                            new KeyValue(blurEffect.heightProperty(), 0)
                    )
            );
            paneitem.setEffect(blurEffect);
            vboxitem.setEffect(blurEffect);
            hboxitem.setEffect(blurEffect);

            // Play the timeline
            timeline.play();
        }


    }
    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            img = selectedFile.toURI().toString();
            Image image = new Image(selectedFile.toURI().toString());
            editimguser.setImage(image);
        }
    }
    @FXML
    public void logOut(ActionEvent event)  throws IOException {
        Session.logout();
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("/gui/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void bulring(ActionEvent event){
        blurPane.setVisible(true);
        applyBlurEffect();
        count = 0;
        messageCunt.setText(String.valueOf(count));
    }



        private void applyBlurEffect() {

            blurEffect.setWidth(0);
            blurEffect.setHeight(0);
            // Create a timeline to animate the blur effect
            Timeline timeline = new Timeline(
                    new KeyFrame(javafx.util.Duration.seconds(0.5),
                            new KeyValue(blurEffect.widthProperty(), 10),
                            new KeyValue(blurEffect.heightProperty(), 10)
                    )
            );
            paneitem.setEffect(blurEffect);
            vboxitem.setEffect(blurEffect);
            hboxitem.setEffect(blurEffect);

            // Play the timeline
            timeline.play();
        }

    }

