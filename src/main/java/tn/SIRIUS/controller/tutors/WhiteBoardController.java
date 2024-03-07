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
import javafx.scene.control.TextField;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @FXML
    private TextField inviteInput;
    @FXML
    private Button endRoomBtn;
    @FXML private AnchorPane listStdContainer;
    @FXML private AnchorPane inviteContainer;
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
    private final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    @Override
    public void initialize(URL url, ResourceBundle rs){
        usersConnected = new ArrayList<>();
        currentColorIndex = 0;
        colors = new String[]{"FFFFFF","ffff00","ff0000","047904","086c3a"};
        chalks = new Button[]{chalkWhite,chalkYellow,chalkRed,chalkGreen};
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#086c3a"));
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

    }
    public void setCourseId(int id,DashboardTutorHomePageController controller,boolean isInvited,int idInvited){
        courseId = id;
        if(!isInvited){
            dashboardTutorHomePageController = controller;
            tutor = new User(1,"Mohamed","Ouerfelli","mohamedouerfelli3@gmail.com","file:/C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/images/profilePictures/12.jpg");
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
                String serializedData = x + ";" + y + ";"+h+";"+w+";" + currentColorIndex+ ";"+courseId+";"+tutor.getEmail()+";";
                byte[] msg = serializedData.getBytes();
                DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
                try {
                    socket.send(send);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        } else{
            dashboardTutorHomePageController = controller;
            tutor = new User(1,"Mohamed","Ouerfelli","mohamedouerfelli2@gmail.com","file:/C:/Users/ouerfelli mohamed/Desktop/EcoCraftLearning/src/main/resources/images/profilePictures/12.jpg");
            courseId = id;
            try {
                socket = new DatagramSocket();
                address = InetAddress.getByName("localhost");
                byte[] uuid = ("tutorJoinInvite;"+courseId+";"+tutor.getEmail()+";"+idInvited+";").getBytes();
                DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
                socket.send(initialize);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // make control invisible
            inviteContainer.setVisible(false);
            listStdContainer.setVisible(false);
            endRoomBtn.setText("Disconnect");
            canvas.setOnMouseDragged(e -> {
                gc.fillOval(e.getX(),e.getY(),10,10);
                double x = e.getX();
                double y = e.getY();
                int h = 10;
                int w = 10;
                String serializedData = x + ";" + y + ";"+h+";"+w+";" + currentColorIndex+ ";"+courseId+";"+tutor.getEmail()+";";
                byte[] msg = serializedData.getBytes();
                DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
                try {
                    socket.send(send);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
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
    public void notifyMailIncorrect(){
        leftUserTxt.setText("Email incorrect !");
        leftUserContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1500), e -> leftUserContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
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
        if(!endRoomBtn.getText().equals("Disconnect")){
            String serializedData = "roomEnd;"+courseId+";";
            byte[] msg = serializedData.getBytes();
            DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
            try {
                socket.send(send);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            String serializedData = "tutorInvitedDisconnect;"+courseId+";"+tutor.getEmail()+";";
            byte[] msg = serializedData.getBytes();
            DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
            try {
                socket.send(send);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
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
                        // Case when tutor accept invite
                        else if(msg.contains("tutorInvitedJoinedAccept;")){
                            String[] parts = msg.split(";");
                            String tutorData = parts[1];
                            Platform.runLater(() -> studentJoinNotify(tutorData) );
                        }
                        // Case when tutor invited disconnect
                        else if(msg.contains("tutorInvitedJoinedLeft;")){
                            String[] parts = msg.split(";");
                            String tutorData = parts[1];
                            Platform.runLater(() -> studentLeftNotify(tutorData) );
                        }
                        // Case when organizer end the room
                        else if(msg.contains("roomEnd;")){
                            dashboardTutorHomePageController.backToCoursesPage();
                        }
                        // Case when other tutor write
                        else {
                            String[] parts = msg.split(";");
                            double x = Double.parseDouble(parts[0]);
                            double y = Double.parseDouble(parts[1]);
                            double h = Double.parseDouble(parts[2]);
                            double w = Double.parseDouble(parts[3]);
                            int currentColorIndex = Integer.parseInt(parts[4]);
                            Platform.runLater(() -> {
                                gc.setFill(Color.web(colors[currentColorIndex]));
                                gc.fillOval(x,y,h,w);
                            });
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    // invite other tutor's
    @FXML public void inviteTutorToJoin(){
        String email = inviteInput.getText();
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            String serializedData = "inviteTutor;"+courseId+";"+tutor.getId()+";"+email+";";
            byte[] msg = serializedData.getBytes();
            DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
            try {
                socket.send(send);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            inviteInput.clear();
        } else if(email.equals(tutor.getEmail())){
                studentLeftNotify("You can't invite yourself");
        }else{
            inviteInput.clear();
            notifyMailIncorrect();
        }
    }
}
