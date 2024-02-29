package tn.SIRIUS.services;

import tn.SIRIUS.entities.Eventsevaluation;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EventevaluationService implements ICRUD<Eventsevaluation> {
    private Connection con;
    public EventevaluationService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public boolean add(Eventsevaluation evaluattion) {
        String query = "INSERT INTO eventsevaluations (event,evaluator,rate) VALUES (?,?,?)";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, evaluattion.getEvent());
            statement.setInt(2, evaluattion.getEvaluator());
            statement.setDouble(3, evaluattion.getRate());
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


    public int getEvaluatorValue(int event) {
        int EvaluatorValue = 0;
        String query = "SELECT evaluator FROM eventsevaluations WHERE event = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, event);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EvaluatorValue = resultSet.getInt("evaluator");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return EvaluatorValue;
    }

    @Override
    public List<Eventsevaluation> getAll() {
        return null;
    }

    @Override
    public boolean update(Eventsevaluation entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
