package tn.SIRIUS.services;

import tn.SIRIUS.entities.Event;
import tn.SIRIUS.entities.Eventsparticipations;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventparticipationsService implements ICRUD<Eventsparticipations> {


    private Connection con;
    public EventparticipationsService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public boolean add(Eventsparticipations entity) {
        String query = "INSERT INTO eventsparticipations (event, participant) VALUES (?,?)";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, entity.getEvent().getIdEvent());
            statement.setInt(2, entity.getParticipant().getIdUser());
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
    public List<Eventsparticipations> getAll() {
      List<Eventsparticipations> list = new ArrayList();
        String query = "SELECT eventsparticipations.*, u.idUser, u.firstName, u.lastName, u.email, u.image, e.title, e.idEvent " +
                "FROM eventsparticipations " +
                "JOIN users u ON eventsparticipations.participant = u.idUser " +
                "JOIN events e ON eventsparticipations.event = e.idEvent";
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                User user = new User();
                user.setIdUser(rs.getInt("idUser"));
                user.setLastName(rs.getString("lastName"));
                user.setFirstName(rs.getString("firstName"));
                user.setEmail(rs.getString("email"));
                user.setImage(rs.getString("image"));
                Event event = new Event();
                event.setIdEvent(rs.getInt("idEvent"));
                event.setTitle(rs.getString("title"));
                Eventsparticipations eventsparticipations = new Eventsparticipations(rs.getInt("idParticipation"), event, user);
                list.add(eventsparticipations);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public boolean update(Eventsparticipations entity) {
        return false;
    }


    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM eventsparticipations WHERE idParticipation = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, id);
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

    public int getParticipantValue(int event) {
        int participantValue = 0;
        String query = "SELECT participant FROM eventsparticipations WHERE event = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, event);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                participantValue = resultSet.getInt("participant");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return participantValue;
    }








}
