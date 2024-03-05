package tn.SIRIUS.services;

import tn.SIRIUS.entities.Product;
import tn.SIRIUS.entities.SousCart;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SousCartService implements ICRUD<SousCart> {
    private Connection con;
    public SousCartService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(SousCart entity) {
        String query = "INSERT INTO souscarts (idCart,id_product,QuantiteProduct) VALUES (?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, entity.getIdCart());
            statement.setInt(2,entity.getId_product());
            statement.setInt(3,entity.getQuantiteProduct());
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
    public List<SousCart> getAll() {
        return null;
    }

    public List<SousCart> getSousCartsWithProductInfo(int cartId) {
        List<SousCart> result = new ArrayList<>();
        String query = "SELECT sc.id_SousCarts , sc.idCart, sc.id_product, sc.QuantiteProduct, " +
                "p.name, p.image, p.price, p.idProduct " +
                "FROM souscarts sc " +
                "JOIN products p ON sc.id_product = p.idProduct " +
                "WHERE sc.idCart = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("p.idProduct");
                Product info = new Product(
                        productId,rs.getString("p.name"),
                        rs.getString("p.image"),
                        rs.getFloat("p.price")
                );
                SousCart sousCart = new SousCart(
                        rs.getInt("sc.id_SousCarts"),
                        rs.getInt("sc.idCart"),
                        rs.getInt("sc.id_product"),
                        rs.getInt("sc.QuantiteProduct"),
                        info
                );
                result.add(sousCart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;}
    @Override
    public boolean update(SousCart entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    public boolean deleteSousCarts(int idSousCarts, int idCart) {
        String qry = "DELETE FROM souscarts WHERE id_SousCarts = ? AND idCart = ?";
        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1, idSousCarts);
            stm.setInt(2, idCart);
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

    public void decrementAllQuantities(int idCart) {
        String qry = "UPDATE products p " +
                "INNER JOIN (SELECT id_product, SUM(QuantiteProduct) AS total_quantity " +
                "FROM souscarts " +
                "WHERE idCart = ? " +
                "GROUP BY id_product) s " +
                "ON p.idProduct = s.id_product " +
                "SET p.quantite = p.quantite - s.total_quantity";

        try {
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1, idCart);
            stm.executeUpdate();
            stm.close();
            System.out.println("Quantities decremented successfully for all products in cart " + idCart);
        } catch (SQLException e) {
            System.out.println("Error while decrementing quantities: " + e.getMessage());
        }
    }


   public int countNbrOfsouscarts(int idCart){
       int nbr = 0;
       String query = "SELECT count(id_SousCarts) FROM souscarts WHERE idCart = ? ";
       try {
           PreparedStatement statement = con.prepareStatement(query);
           statement.setInt(1, idCart);
           ResultSet resultSet = statement.executeQuery();
           if (resultSet.next()) {
               nbr = resultSet.getInt("count(id_SousCarts)");
           }
           resultSet.close();
           statement.close();
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
       return nbr;
   }







}
