package tn.SIRIUS.services;

import tn.SIRIUS.entities.Product;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;
import java.util.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements ICRUD<Product> {
    private Connection con;
    public ProductService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(Product product) {

        String query = "INSERT INTO products (name,description,image,price,owner) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2,product.getDescription());
            statement.setString(3,product.getImage());
            statement.setFloat(4,product.getPrice());
            statement.setInt(5, 1);
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
    public List<Product> getAll() {
        String query = "SELECT * FROM PRODUCTS";
        List<Product> productList = new ArrayList<>();

        try (Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("idProduct"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        0,
                        rs.getString("addDate")
                );
                productList.add(product);
            }

        } catch (SQLException ex) {
            System.out.println("Error fetching products: " + ex.getMessage());

        }

        return productList;
    }


    @Override
    public int update(Product product) {
        return 0;
    }

    @Override
    public int delete(Product product) {
        return 0;
    }
}
