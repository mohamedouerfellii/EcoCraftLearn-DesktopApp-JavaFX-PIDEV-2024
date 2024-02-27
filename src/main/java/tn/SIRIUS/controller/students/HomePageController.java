package tn.SIRIUS.controller.students;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    private VBox coursesVboxHomePage;
    @FXML
    private Pane blurPane;
    @FXML
    private Rectangle messageRectangle;
    @FXML
    private TextArea messageArea;
    private BoxBlur blurEffect = new BoxBlur();
    private static DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private GraphicsContext gc;
    private String[] colors;
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

    private static final String IDENTIFIER = "Jarray Abdo";
    private static final int SERVER_PORT = 12345;
User loggingUser=Session.getUser();
    @Override
    public void initialize(URL url, ResourceBundle rb){
        User loggedInUser = Session.getUser();

        profilImgContainer.setFill(new ImagePattern(new Image(loggedInUser.getImage())));
        welcomeMsg.setText("Welcome "+loggedInUser.getFirstName()+"!");
// send initialization message to the server
        byte[] uuid = ("init;" + loggedInUser.getFirstName()+";").getBytes();
        DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
        try {
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Thread(new ClientThread()).start();

        textFieldMessage.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String temp = loggingUser.getFirstName() + ":" + textFieldMessage.getText(); // message to send
                String current = messageArea.getText();
                messageArea.setText(current + textFieldMessage.getText() + "\n"); // update messages on screen
                byte[] msg = temp.getBytes(); // convert to bytes
                textFieldMessage.setText(""); // remove text from input box

                // create a packet & send
                DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
                try {
                    socket.send(send);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        }


    @FXML
    public void logOut(ActionEvent event)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignUpController.class.getResource("/gui/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1350, 720);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();  // Get the current stage
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void bulring(ActionEvent event) throws IOException {
        blurPane.setVisible(true);
        applyBlurEffect();
    }


    private class ClientThread implements Runnable {
        private final byte[] incoming = new byte[256];

        @Override
        public void run() {
            System.out.println("starting thread");
            while (true) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String message = new String(packet.getData(), 0, packet.getLength()) + "\n";
                String current = messageArea.getText();
                messageArea.setText(current+ message);
            }
        }
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

