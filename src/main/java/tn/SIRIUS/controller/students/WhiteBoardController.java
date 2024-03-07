package tn.SIRIUS.controller.students;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tn.SIRIUS.entities.User;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;

public class WhiteBoardController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private Circle tutorImg;
    @FXML
    private Text tutorName;
    @FXML private AnchorPane joinedStudentContainer;
    @FXML private Label joinedTxt;
    private GraphicsContext gc;
    private String[] colors;
    private static DatagramSocket socket;
    private static InetAddress address;
    private User student;
    private User tutor;
    private static final int SERVER_PORT = 12345;
    private int courseId;
    private HomePageController homePageController;
    @Override
    public void initialize(URL url, ResourceBundle rs){
        colors = new String[]{"FFFFFF","ffff00","ff0000","047904","086c3a"};
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#086c3a"));
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
    public void setCourseId(int id,HomePageController controller){
        homePageController = controller;
        student = new User(4,"Mohamed","Ouerfelli","mohamedouerfelli3@gmail.com","");
        courseId = id;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println(courseId);
            byte[] uuid = ("studentConnect;" + "#"+student.getId()+" "+student.getFirstName()+" "+student.getFirstName()+";"+courseId+";").getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new  Thread(new WhiteBoardController.ClientThread()).start();
    }
    @FXML
    public void disconnect(MouseEvent event){
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println(courseId);
            byte[] uuid = ("studentDisconnect;" + "#"+student.getId()+" "+student.getFirstName()+" "+student.getFirstName()+";"+courseId+";").getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
            homePageController.coursesBtnClicked(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void notifyPresented(String identifier){
        joinedTxt.setText(identifier);
        joinedStudentContainer.setVisible(true);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> joinedStudentContainer.setVisible(false)));
        timeline.setCycleCount(1);
        timeline.play();
    }
    private class ClientThread implements Runnable {
        @Override
        public void run() {
            System.out.println("Starting thread");
            byte[] incoming = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(incoming, incoming.length);
                try {
                    socket.receive(packet);
                    String msg = new String(packet.getData(), 0, packet.getLength());
                    if (!msg.isEmpty()) {
                        if(msg.contains("tutor;")){
                            String[] allData = msg.split(";");
                            tutor = new User(Integer.parseInt(allData[1]),allData[3],allData[2],"",allData[4]);
                            Platform.runLater(() -> {
                                tutorImg.setFill(new ImagePattern(new Image(tutor.getImage())));
                                tutorName.setText(tutor.getLastName()+" "+tutor.getFirstName());
                            });
                        }
                        else if(msg.contains("roomEnd;")){
                            Platform.runLater(() -> homePageController.coursesBtnClicked(null));
                        }
                        else {
                            String[] parts = msg.split(";");
                            double x = Double.parseDouble(parts[0]);
                            double y = Double.parseDouble(parts[1]);
                            double h = Double.parseDouble(parts[2]);
                            double w = Double.parseDouble(parts[3]);
                            int currentColorIndex = Integer.parseInt(parts[4]);
                            Platform.runLater(() -> {
                                notifyPresented(parts[6]+" Writing ...");
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
}
