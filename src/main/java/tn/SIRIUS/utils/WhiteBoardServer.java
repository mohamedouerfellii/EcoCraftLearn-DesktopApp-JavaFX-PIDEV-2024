package tn.SIRIUS.utils;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WhiteBoardServer {
    private static byte[] incoming = new byte[256];
    private static final int PORT = 12345;
    private static DatagramSocket socket;
    static{
        try{
            socket = new DatagramSocket(PORT);
        }catch (SocketException e){
            System.out.println(e.getMessage());
        }
    }
    private static ArrayList<Integer> usersIDs = new ArrayList<>();
    private static ArrayList<Integer> tutorsIDs = new ArrayList<>();
    private static Map<Integer,ArrayList<Integer>> rooms = new TreeMap<>();
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
            int userPort = packet.getPort();
            String msg = new String(packet.getData(),0, packet.getLength());
            if(msg.contains("initRoom;")){
                String[] allData = msg.split(";");
                int courseId = Integer.parseInt(allData[1]);
                rooms.put(courseId+userPort,new ArrayList<Integer>());
                System.out.println("tutor connected"+courseId);
                if(!tutorsIDs.contains(userPort)) tutorsIDs.add(userPort);
                System.out.println(rooms.containsKey(courseId+userPort));
            }
            else if(msg.contains("init;")){
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[2]);
                for (int tutorID : tutorsIDs){
                    int roomID = tutorID+courseID;
                    if(rooms.containsKey(roomID)){
                        System.out.println("room exist");
                        rooms.get(roomID).add(userPort);
                        byte[] byteMessage = ("userConnected;"+allData[1]).getBytes();
                        DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,tutorID);
                        try{
                            socket.send(forward);
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                }
            }
            else {
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[5]);
                userPort = packet.getPort();
                int roomID = courseID + userPort;
                if(rooms.containsKey(roomID)){
                    byte[] byteMessage = msg.getBytes();
                    for(int forward_port : rooms.get(roomID)){
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
