package tn.SIRIUS.services;

import tn.SIRIUS.entities.Course;
import tn.SIRIUS.entities.Event;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService implements ICRUD<Event> {
    private Connection con;
    public EventService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean add(Event event) {
        String query = "INSERT INTO EVENTS (title,description,startDate,endDate,attachment,owner,eventType,place,placeNbr,price) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);


            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setString(3, event.getStartDate());
            statement.setString(4, event.getEndDate());
            statement.setString(5, event.getAttachment());
            statement.setInt(6,1);
            statement.setString(7,event.getEventType());
            statement.setString(8,event.getPlace());
            statement.setInt(9, event.getPlaceNbr());
            statement.setFloat(10, event.getPrice());


            if(statement.executeUpdate() == 1){
                statement.close();
                return true;
            }
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Event> getAll() {
        String query = "SELECT * FROM EVENTS ";
        List<Event> eventList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                eventList.add(new Event(
                        rs.getInt("idEvent"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("startDate").toString(),
                        rs.getDate("endDate").toString(),
                        rs.getString("attachment"),
                        rs.getInt("owner"),
                        rs.getString("eventType"),
                        rs.getString("place"),
                        rs.getInt("placeNbr"),
                        rs.getInt("price")
                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return eventList;
    }
    @Override
    public boolean update(Event event) {
        String query = "UPDATE EVENTS SET title = ? , description = ? , startDate = ? , endDate = ? , attachment = ? , owner = ? , eventType = ? , place = ? , placeNbr = ? , price = ? WHERE idEvent = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, event.getTitle());
            statement.setString(2, event.getDescription());
            statement.setString(3, event.getStartDate());
            statement.setString(4, event.getEndDate());
            statement.setString(5, event.getAttachment());
            statement.setInt(6, event.getOwner());
            statement.setString(7, event.getEventType());
            statement.setString(8, event.getPlace());
            statement.setInt(9, event.getPlaceNbr());
            statement.setFloat(10, event.getPrice());
            statement.setInt(11, event.getIdEvent());
            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated == 1) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean delete(int idEvent) {

        String qry = "DELETE FROM events WHERE idEvent = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idEvent);
            if(stm.executeUpdate() == 1){
                stm.close();

                return true;
            }
            stm.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public List<Event> getEventRegistered(int idUser){
        List<Event> eventsRegistered = new ArrayList<>();
        String query = "SELECT * FROM EVENTS E JOIN EVENTSPARTICIPATIONS EP ON E.idEvent = EP.event WHERE EP.participant = ?";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idUser);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                eventsRegistered.add(new Event(
                        rs.getInt("idEvent"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("startDate").toString(),
                        rs.getDate("endDate").toString(),
                        rs.getString("attachment"),
                        rs.getInt("owner"),
                        rs.getString("eventType"),
                        rs.getString("place"),
                        rs.getInt("placeNbr"),
                        rs.getInt("price")
                        ));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return eventsRegistered;
    }


    public List<Event> getEventUser(int idUser){
        List<Event> eventsUser = new ArrayList<>();
        String query = "SELECT * FROM EVENTS E JOIN EVENTSPARTICIPATIONS EP ON E.idEvent = EP.event WHERE EP.participant = ?";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idUser);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                eventsUser.add(new Event(
                        rs.getInt("idEvent"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("startDate").toString(),
                        rs.getDate("endDate").toString(),
                        rs.getString("attachment"),
                        rs.getInt("owner"),
                        rs.getString("eventType"),
                        rs.getString("place"),
                        rs.getInt("placeNbr"),
                        rs.getInt("price")
                ));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return eventsUser;
    }

    public boolean deleteParticipation(int event, int idUser) {
        String qry = "DELETE FROM eventsparticipations WHERE event = ? AND participant = ?";
        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1, event);
            stm.setInt(2, idUser);
            if (stm.executeUpdate() == 1) {
                stm.close();
                return true;
            }
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void decrementPlaceNbr(int idEvent) {
        String query = "UPDATE events SET placeNbr = placeNbr - 1 WHERE idEvent = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idEvent);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void incrementPlaceNbr(int idEvent) {
        String query = "UPDATE events SET placeNbr = placeNbr + 1 WHERE idEvent = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idEvent);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public double SommeRateofEvent(int idEvent){
        double rate = 0;
        String query = "SELECT sum(rate) FROM eventsevaluations WHERE event = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idEvent);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rate = resultSet.getDouble("sum(rate)");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rate;
    }

    public int countNBrOfRate(int idEvent){
        int rate = 0;
        String query = "SELECT count(rate) FROM eventsevaluations WHERE event = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idEvent);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rate = resultSet.getInt("count(rate)");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rate;
    }

}
