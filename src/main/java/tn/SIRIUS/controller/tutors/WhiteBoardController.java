package tn.SIRIUS.controller.tutors;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tn.SIRIUS.entities.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WhiteBoardController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private Button chalkGreen;
    @FXML
    private Button chalkRed;
    @FXML
    private Button chalkWhite;
    @FXML
    private Button chalkYellow;
    @FXML
    private VBox connectedContainer;
    @FXML
    private AnchorPane leftUserContainer;
    @FXML
    private Label leftUserTxt;
    @FXML
    private AnchorPane joinedStudentContainer;
    @FXML
    private Label joinedTxt;
    private String[] colors;
    private Button[] chalks;
    private int currentColorIndex;
    private GraphicsContext gc;
    private static DatagramSocket socket;
    private static InetAddress address;
    private static final int SERVER_PORT = 12345;
    private int courseId;
    private User tutor;
    private List<String> usersConnected;
    private DashboardTutorHomePageController dashboardTutorHomePageController;
    @Override
    public void initialize(URL url, ResourceBundle rs){
        currentColorIndex = 0;
        colors = new String[]{"FFFFFF","ffff00","ff0000","047904"};
        chalks = new Button[]{chalkWhite,chalkYellow,chalkRed,chalkGreen};
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#086c3a"));
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

    }
    public void setCourseId(int id,DashboardTutorHomePageController controller){
        dashboardTutorHomePageController = controller;
        tutor = new User(1,"Mohamed","Ouerfelli","mohamedouerfelli3@gmail.com","file:/C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/images/profilePictures/12.jpg");
        usersConnected = new ArrayList<>();
        courseId = id;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println(courseId);
            byte[] uuid = ("initRoom;"+courseId+";"+tutor.getLastName()+";"+tutor.getFirstName()+";"+tutor.getImage()+";").getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        canvas.setOnMouseDragged(e -> {
            gc.fillOval(e.getX(),e.getY(),10,10);
            double x = e.getX();
            double y = e.getY();
            int h = 10;
            int w = 10;
            String serializedData = x + ";" + y + ";"+h+";"+w+";" + currentColorIndex+ ";"+courseId+";";
            byte[] msg = serializedData.getBytes();
            DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
            try {
                socket.send(send);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
        new  Thread(new ClientThread()).start();
    }
    @FXML
    public void chalkClicked(MouseEvent event){
        for(int i = 0;i < chalks.length;i++){
            if(chalks[i].getId().equals(((Button)event.getSource()).getId())){
                System.out.println(colors[i]);
                gc.setFill(Color.web(colors[i]));
                currentColorIndex = i;
                return;
            }
        }
    }
    @FXML
    public void eraserClicked(MouseEvent event){
        gc.setFill(Color.web("#086c3a"));
        currentColorIndex = 4;
    }
    public void studentLeftNotify(String identifier){
        leftUserTxt.setText(identifier);
        leftUserContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> leftUserContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    public void studentJoinNotify(String identifier){
        joinedTxt.setText(identifier);
        joinedStudentContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> joinedStudentContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    @FXML
    public void disconnect(MouseEvent event){
        String serializedData = "roomEnd;"+courseId+";";
        byte[] msg = serializedData.getBytes();
        DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
        try {
            socket.send(send);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        dashboardTutorHomePageController.backToCoursesPage();
    }
    private class ClientThread implements Runnable {
        @Override
        public void run() {
            byte[] incoming = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
                try {
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    if (!msg.isEmpty()) {
                        // Case When Student Joined
                        if(msg.contains("studentConnect;")){
                            String[] parts = msg.split(";");
                            String userConnected = parts[1];
                            usersConnected.add(userConnected);
                            Platform.runLater(() -> {
                                Text userText = new Text(userConnected);
                                userText.setStyle("-fx-font-family: \"Jost\";" +
                                        "    -fx-font-size: 16;\n" +
                                        "    -fx-fill: #000000;");
                                connectedContainer.getChildren().add(userText);
                                studentJoinNotify(userConnected);
                            });
                        }
                        // Case When Student Disconnect
                        else if(msg.contains("studentDisconnect;")){
                            String[] parts = msg.split(";");
                            String userDisonnected = parts[1];
                            usersConnected.remove(userDisonnected);
                            Platform.runLater(() -> {
                                connectedContainer.getChildren().clear();
                                for(String data : usersConnected){
                                    Text userText = new Text(data);
                                    userText.setStyle("-fx-font-family: \"Jost\";" +
                                            "    -fx-font-size: 16;\n" +
                                            "    -fx-fill: #000000;");
                                    connectedContainer.getChildren().add(userText);
                                }
                                studentLeftNotify(userDisonnected);
                            });
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
