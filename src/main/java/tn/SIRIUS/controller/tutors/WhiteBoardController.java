package tn.SIRIUS.controller.tutors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

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
    private Button eraser;
    private String[] colors;
    private Button[] chalks;
    private int currentColorIndex;
    private GraphicsContext gc;
    private static DatagramSocket socket;
    private static InetAddress address;
    private static final String IDENTIFIER = "Abdallah";
    private static final int SERVER_PORT = 1235;
    @Override
    public void initialize(URL url, ResourceBundle rs){
        currentColorIndex = 0;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");

            byte[] uuid = ("init;" + IDENTIFIER).getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        colors = new String[]{"FFFFFF","ffff00","ff0000","047904"};
        chalks = new Button[]{chalkWhite,chalkYellow,chalkRed,chalkGreen};
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#086c3a"));
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        canvas.setOnMouseDragged(e -> {
            gc.fillOval(e.getX(),e.getY(),10,10);
            double x = e.getX();
            double y = e.getY();
            int h = 10;
            int w = 10;
            String serializedData = x + ";" + y + ";"+h+";"+w+";" + currentColorIndex+ ";";
            byte[] msg = serializedData.getBytes();
            DatagramPacket send = new DatagramPacket(msg, msg.length, address, SERVER_PORT);
            try {
                socket.send(send);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        });
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
}
