package tn.SIRIUS.controller.students;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import tn.SIRIUS.controller.SignUpController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import tn.SIRIUS.entities.Session;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.utils.ChatServer;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.application.Application.launch;

public class HomePageController implements Initializable {

    @FXML
    private Circle profilImgContainer;
    @FXML
    private AnchorPane homeAnchorPane;
    @FXML
    private Pane paneitem;
    @FXML
    private VBox vboxitem;
    @FXML
    private HBox hboxitem;
    @FXML
    private HBox notifCount;

    @FXML
    private HBox msgCount;
    @FXML
    private Label welcomeMsg;
    @FXML
    private Circle mostProgressedCourseImgContainer;
    @FXML
    private Arc mostProgressedCoursePrc;
    @FXML
    private Label mostProgressedCoursePrcVal;
    @FXML
    private Button homeBtn;
    @FXML
    private ImageView homeBtnImg;
    @FXML
    private Button coursesBtn;
    @FXML
    private ImageView coursesBtnImg;
    @FXML
    private Button eventsBtn;
    @FXML
    private ImageView eventsBtnImg;
    @FXML
    private Button forumBtn;
    @FXML
    private ImageView forumBtnImg;
    @FXML
    private Button productsBtn;
    @FXML
    private ImageView productsBtnImg;
    @FXML
    private Button collectsBtn;
    @FXML
    private ImageView collectsBtnImg;
    @FXML
    private Button logoutBtn;
    @FXML
    private ImageView logoutBtnImg;
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
private ScrollPane scrollPaneMessage;
@FXML
private Label messageCunt;
    private static final int SERVER_PORT = 12345;
User loggingUser=Session.getUser();
@FXML
private void userContainer(ActionEvent event) {
    userCont.setVisible(true);

}
@FXML
private void closeChat(ActionEvent event) {
    blurPane.setVisible(false);
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
    @Override
    public void initialize(URL url, ResourceBundle rb){
        User loggedInUser = Session.getUser();
        count = 0;
        profilImgContainer.setFill(new ImagePattern(new Image(loggedInUser.getImage())));
        welcomeMsg.setText("Welcome "+loggedInUser.getFirstName()+"!");
       vboxMessage.setStyle("-fx-background-color: #D5FFDC;");

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
    label.setStyle("-fx-background-color: #7BE41B; -fx-background-radius: 20px; -fx-text-fill: black; -fx-font-family: 'Jost Medium'; -fx-font-size: 16px;");
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


});
        textFieldMessage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String temp = loggedInUser.getFirstName() + ": " + textFieldMessage.getText() + "\n"; // message to send
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER_RIGHT);
                hbox.setPadding(new Insets(5, 5, 5, 5));

                Label label = new Label(temp);
                label.setStyle("-fx-background-color: #7BE41B; -fx-background-radius: 20px; -fx-text-fill: black; -fx-font-family: 'Jost Medium'; -fx-font-size: 16px;");
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
                label.setStyle("-fx-background-color: grey ; -fx-background-radius: 20px; -fx-text-fill: black; -fx-font-family: 'Jost Medium'; -fx-font-size: 16px;");
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

