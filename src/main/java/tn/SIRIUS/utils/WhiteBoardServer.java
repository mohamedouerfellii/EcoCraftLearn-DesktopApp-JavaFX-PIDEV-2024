package tn.SIRIUS.utils;

import tn.SIRIUS.services.CourseParticipationService;

import java.io.IOException;
import java.net.*;
import java.util.*;

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
    private static ArrayList<Integer> tutorsIDs = new ArrayList<>();
    private static Map<Integer,ArrayList<Integer>> rooms = new TreeMap<>();
    private static Map<Integer,ArrayList<String>> roomStudentConnected = new TreeMap<>();
    private static Map<Integer,String> roomTutorsConnected = new TreeMap<>();
    private static Map<Integer,Integer> studentsJoined = new TreeMap<>();
    private static Map<String,Integer> tutorsConnected = new HashMap<>();
    private static Map<Integer,ArrayList<Integer>> tutorsInvitedRoom = new HashMap<>();
    private static InetAddress address;
    private static boolean running;
    private static CourseParticipationService courseParticipationService = new CourseParticipationService();
    static {
        try{
            address = InetAddress.getByName("localhost");
        }catch (UnknownHostException e){
            System.out.println(e.getMessage());
        }
    }
    public static int searchForRoom(int courseID){
        for (int tutorID : tutorsIDs) {
            int roomKey = tutorID + courseID;
            if (rooms.containsKey(roomKey)) return roomKey;
        }
        return 0;
    }
    public static void sendData(String type,String data,int port){
        byte[] byteMessage = (type + data).getBytes();
        DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,port);
        try{
            socket.send(forward);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){
        running = true;
        while (running){
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length); // prepare packet
            try{
                socket.receive(packet);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
            int userPort = packet.getPort();
            String msg = new String(packet.getData(),0, packet.getLength());
            // Tutor joined the server
            if(msg.contains("tutorJoinServer;")){
                String[] allData = msg.split(";");
                tutorsConnected.remove(allData[2]);
                tutorsConnected.put(allData[2],userPort); // add tutor to the list of tutor connected
            }
            // tutor invite to be joined
            else if(msg.contains("inviteTutor;")){
                String[] allData = msg.split(";");
                String tutorId = allData[2];
                String email = allData[3];
                int courseID = Integer.parseInt(allData[1]);
                if(tutorsConnected.containsKey(email)){ // tutor invited is connected
                    int tutorInvitedPort = tutorsConnected.get(email);
                    sendData("courseInvite;",courseID+";"+tutorId+";",tutorInvitedPort);
                }
            }
            // Tutor accept invite
            else if(msg.contains("tutorJoinInvite;")){
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[1]);
                String email = allData[2];
                int roomKey = searchForRoom(courseID);
                if(roomKey != 0){
                    int tutorPort = roomKey - courseID;
                    tutorsInvitedRoom.get(roomKey).add(userPort); // add tutor
                    sendData("tutorInvitedJoinedAccept;",email+" joined",tutorPort);
                }
            }
            // Tutor invited disconnect
            else if(msg.contains("tutorInvitedDisconnect;")){
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[1]);
                String email = allData[2];
                int roomKey = searchForRoom(courseID);
                if(roomKey != 0){
                    int tutorPort = roomKey - courseID;
                    tutorsInvitedRoom.get(roomKey).remove((Integer) userPort);// remove tutor from invited list room
                    sendData("tutorInvitedJoinedLeft;",email+" left",tutorPort);
                }
            }
            // Create room
            else if(msg.contains("initRoom;")){
                String[] allData = msg.split(";");
                int courseId = Integer.parseInt(allData[1]);
                int roomKey = courseId + userPort;
                rooms.put(roomKey,new ArrayList<>());
                roomStudentConnected.put(roomKey,new ArrayList<>());
                tutorsInvitedRoom.put(roomKey,new ArrayList<>());
                roomTutorsConnected.put(roomKey,msg.replace("initRoom;",""));
                tutorsIDs.add(userPort);
                // sending alert to student
                List<Integer> particiants = courseParticipationService.getEnrolledUsers(courseId);
                System.out.println(particiants);
                System.out.println("dsdf");
                System.out.println(studentsJoined);
                for(int idStudent : particiants){
                    if(studentsJoined.containsKey(idStudent)){
                        int forward_port = studentsJoined.get(idStudent);
                        String alert = "courseAlert;" + courseId + ";";
                        System.out.println("alert : "+alert);
                        byte[] byteMessage = alert.getBytes();
                        DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,forward_port);
                        try{
                            socket.send(forward);
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            // Student Joining Server
            else if(msg.contains("studentJoinServer;")){
                String[] allData = msg.split(";");
                int studentId = Integer.parseInt(allData[1]);
                if(studentsJoined.containsKey(studentId))
                    studentsJoined.remove(studentId);
                studentsJoined.put(studentId,userPort);
            }
            // Room end
            else if(msg.contains("roomEnd;")){
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[1]);
                userPort = packet.getPort();
                int roomKey = courseID + userPort;
                if(rooms.containsKey(roomKey)){
                    for(int forward_port : rooms.get(roomKey)){
                        sendData("roomEnd;","Finished",forward_port);
                    }
                    for(int forward_port : tutorsInvitedRoom.get(roomKey)){
                        sendData("roomEnd;","Finished",forward_port);
                    }
                }
                rooms.remove((Integer) roomKey);
                roomTutorsConnected.remove((Integer) roomKey);
                roomStudentConnected.remove((Integer) roomKey);
                tutorsInvitedRoom.remove((Integer) roomKey);
            }
            // Join student
            else if(msg.contains("studentConnect;")){
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[2]);
                int roomKey = searchForRoom(courseID);
                if(roomKey != 0){
                    int tutorPort = roomKey - courseID;
                    System.out.println(rooms.containsKey(roomKey));
                    rooms.get(roomKey).add(userPort);
                    roomStudentConnected.get(roomKey).add(allData[1]);
                    sendData("studentConnect;",allData[1],tutorPort);
                    sendData("tutor;",roomTutorsConnected.get(roomKey),userPort);
                }
            }
            // Student disconnect
            else if(msg.contains("studentDisconnect;")){
                String[] allData = msg.split(";");
                int courseID = Integer.parseInt(allData[2]);
                int roomKey = searchForRoom(courseID);
                if(roomKey != 0){
                    int tutorPort = roomKey - courseID;
                    rooms.get(roomKey).remove((Integer) userPort);
                    roomStudentConnected.get(roomKey).remove(allData[1]);
                    sendData("studentDisconnect;",allData[1],tutorPort);
                }
            }
            // Sending data
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
                    for (int forward_port : tutorsInvitedRoom.get(roomID)){
                        DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,forward_port);
                        try{
                            socket.send(forward);
                        }catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    int roomKey = searchForRoom(courseID);
                    if(rooms.containsKey(roomKey)){
                        byte[] byteMessage = msg.getBytes();
                        for(int forward_port : rooms.get(roomKey)){
                            DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,forward_port);
                            try{
                                socket.send(forward);
                            }catch (IOException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        DatagramPacket forward = new DatagramPacket(byteMessage,byteMessage.length,address,roomKey - courseID);
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
