package tn.SIRIUS.controller.tutors;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
    private Button chalkGreen;
    @FXML
    private Button chalkRed;
    @FXML
    private Button chalkWhite;
    @FXML
    private Button chalkYellow;
    @FXML
    private VBox connectedContainer;
    private String[] colors;
    private Button[] chalks;
    private int currentColorIndex;
    private GraphicsContext gc;
    private static DatagramSocket socket;
    private static InetAddress address;
    private static final String IDENTIFIER = "Abdallah";
    private static final int SERVER_PORT = 12345;
    private int courseId;
    @Override
    public void initialize(URL url, ResourceBundle rs){
        currentColorIndex = 0;
        colors = new String[]{"FFFFFF","ffff00","ff0000","047904"};
        chalks = new Button[]{chalkWhite,chalkYellow,chalkRed,chalkGreen};
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#086c3a"));
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

    }
    public void setCourseId(int id){
        courseId = id;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println(courseId);
            byte[] uuid = ("initRoom;"+courseId+";").getBytes();
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
                        if(msg.contains("userConnected;")){
                            String[] parts = msg.split(";");
                            String userConnected = parts[1];
                            Platform.runLater(() -> {
                                Text userText = new Text(userConnected);
                                userText.setStyle("-fx-font-family: \"Jost\";" +
                                        "    -fx-font-size: 18;\n" +
                                        "    -fx-fill: #000000;");
                                connectedContainer.getChildren().add(userText); });
                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
