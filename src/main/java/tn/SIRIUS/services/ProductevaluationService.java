package tn.SIRIUS.services;

import tn.SIRIUS.entities.Product;
import tn.SIRIUS.entities.Productsevaluation;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductevaluationService implements ICRUD<Productsevaluation> {

    private Connection con;
    public ProductevaluationService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(Productsevaluation productsevaluation) {
        String query = "INSERT INTO productsevaluations (rate,product,evaluator) VALUES (?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDouble(1, productsevaluation.getRate());
            statement.setInt(2,productsevaluation.getProduct());
            statement.setInt(3,productsevaluation.getEvaluator());


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

        return null;
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
