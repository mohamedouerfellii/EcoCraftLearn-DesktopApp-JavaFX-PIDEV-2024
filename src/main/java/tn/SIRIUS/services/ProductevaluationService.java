package tn.SIRIUS.services;

import tn.SIRIUS.entities.Product;
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
        String query = "INSERT INTO productsevaluations (rate,review ,product,evaluator,isConfirmed) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDouble(1, productsevaluation.getRate());
            statement.setString(2,productsevaluation.getReview());
            statement.setInt(3,productsevaluation.getProduct());
            statement.setInt(4,productsevaluation.getEvaluator());
            statement.setInt(5,productsevaluation.getIsConfirmed());

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
        String query = "SELECT * FROM productsevaluations";
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
                        rs.getInt("evaluator"),
                        rs.getInt("isConfirmed")
                );
                productsevaluationList.add(productsevaluation);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return productsevaluationList;
    }

    public List<Productsevaluation> getAllByIdProductNotConfirmed(int idProduct) {
        String query = "SELECT * FROM productsevaluations WHERE product = ? AND isConfirmed = 0";
        List<Productsevaluation> productsevaluationList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, idProduct);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Productsevaluation productsevaluation = new Productsevaluation(
                            rs.getInt("idEvaluation"),
                            rs.getInt("rate"),
                            rs.getString("review"),
                            rs.getString("evaluationDate"),
                            rs.getInt("product"),
                            rs.getInt("evaluator"),
                            rs.getInt("isConfirmed")
                    );
                    productsevaluationList.add(productsevaluation);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productsevaluationList;
    }

    @Override
    public boolean update(Productsevaluation productsevaluation) {

        return false;
    }

    @Override
    public boolean delete(int idEvaluation) {

        return false;
    }

       public int isUserrated(Product product) {
       int evaluatorValue = 0 ;
       String query = "SELECT evaluator FROM productsevaluations WHERE product = ?";
       try{
           PreparedStatement statement = con.prepareStatement(query);
           statement.setInt(1, product.getIdProduct());
           ResultSet rs = statement.executeQuery();
           while(rs.next()){
               evaluatorValue = rs.getInt("evaluator");
           }
           statement.close();
       }catch (SQLException ex){
           System.out.println(ex.getMessage());
       }
       return evaluatorValue;
       }


}
