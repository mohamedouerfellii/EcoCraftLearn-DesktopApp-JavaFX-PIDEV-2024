package tn.SIRIUS.controller.students;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    private Circle tutorImg;
    @FXML
    private Text tutorName;
    private GraphicsContext gc;
    private String[] colors;
    private static DatagramSocket socket;
    private static InetAddress address;
    private static final String IDENTIFIER = "Jarray Abdo";
    private static final int SERVER_PORT = 12345;
    private int courseId;
    @Override
    public void initialize(URL url, ResourceBundle rs){
        colors = new String[]{"FFFFFF","ffff00","ff0000","047904","086c3a"};
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#086c3a"));
        gc.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
    }
    public void setCourseId(int id){
        courseId = id;
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
            System.out.println(courseId);
            byte[] uuid = ("init;" + IDENTIFIER+";"+courseId+";").getBytes();
            DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
            socket.send(initialize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Thread(new ClientThread()).start();
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
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
