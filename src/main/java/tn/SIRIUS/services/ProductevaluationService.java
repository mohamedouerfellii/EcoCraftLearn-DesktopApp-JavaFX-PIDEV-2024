package tn.SIRIUS.services;

import tn.SIRIUS.entities.Productsevaluation;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductevaluationService implements ICRUD<Productsevaluation> {

    private Connection con;
    public ProductevaluationService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(Productsevaluation productsevaluation) {
        String query = "INSERT INTO Productsevaluation (rate,review ,product,evaluator) VALUES (?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, productsevaluation.getRate());
            statement.setString(2,productsevaluation.getReview());
            statement.setInt(3,productsevaluation.getProduct());
            statement.setInt(4,productsevaluation.getEvaluator());
            if(statement.executeUpdate() == 1){
                statement.close();
                return 1;
            }
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<Productsevaluation> getAll() {
        String query = "SELECT * FROM productsevaluation";
        List<Productsevaluation> productsevaluationList = new ArrayList<>();
        try (Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                Productsevaluation productsevaluation = new Productsevaluation(
                        rs.getInt("idEvaluation"),
                        rs.getInt("rate"),
                        rs.getString("review"),
                        rs.getString("evaluationDate"),
                        rs.getInt("product"),
                        rs.getInt("evaluator")

                );
                productsevaluationList.add(productsevaluation);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return productsevaluationList;
    }

    @Override
    public boolean update(Productsevaluation productsevaluation) {
        String query = "UPDATE productsevaluation SET rate = ? , review = ? WHERE idEvaluation = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, productsevaluation.getRate());
            statement.setString(2,productsevaluation.getReview());
            statement.setInt(3,productsevaluation.getIdEvaluation());

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
    public boolean delete(int idEvaluation) {
        String qry = "DELETE FROM productsevaluation WHERE idEvaluation = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idEvaluation);
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
