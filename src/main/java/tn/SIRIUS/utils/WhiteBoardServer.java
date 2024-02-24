package tn.SIRIUS.utils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class WhiteBoardServer {
    private static byte[] incoming = new byte[256];
    private static final int PORT = 1235;
    private static DatagramSocket socket;
    static{
        try{
            socket = new DatagramSocket(PORT);
        }catch (SocketException e){
            System.out.println(e.getMessage());
        }
    }
    private static ArrayList<Integer> usersIDs = new ArrayList<>();
    private static InetAddress address;
    static {
        try{
            address = InetAddress.getByName("localhost");
        }catch (UnknownHostException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){
        while (true){
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length); // prepare packet
            try{
                socket.receive(packet);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            String msg = new String(packet.getData(),0, packet.getLength());
            System.out.println("Server received : "+msg);
            if(msg.contains("init;")){
                usersIDs.add(packet.getPort());
            }
            else {
                int userPort = packet.getPort();
                byte[] byteMessage = msg.getBytes();
                for(int forward_port : usersIDs){
                    if(forward_port!=userPort){
                        DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,forward_port);
                        try{
                            socket.send(forward);
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
