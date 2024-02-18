package tn.SIRIUS.services;

import tn.SIRIUS.entities.Event;
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

}
