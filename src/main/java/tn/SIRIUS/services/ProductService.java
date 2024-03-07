package tn.SIRIUS.services;

import tn.SIRIUS.entities.Product;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

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

        String query = "INSERT INTO products (name,description,image,price,owner,quantite) VALUES (?,?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2,product.getDescription());
            statement.setString(3,product.getImage());
            statement.setFloat(4,product.getPrice());

            statement.setInt(5, 1);
            statement.setInt(6,product.getQuantite());
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
             ResultSet rs = stm.executeQuery(query))
                       {
            while (rs.next()) {
                        Product product = new Product(
                        rs.getInt("idProduct"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("owner"),
                        rs.getString("addDate"),
                        rs.getInt("quantite")
                );
                productList.add(product);
            }

        } catch (SQLException ex) {
            System.out.println("Error fetching products: " + ex.getMessage());

        }
        return productList;
    }


    @Override
    public boolean update(Product product) {
        String query = "UPDATE PRODUCTS SET name = ? , description = ? , image = ? , price = ?, quantite = ? WHERE idProduct = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getImage());
            statement.setFloat(4, product.getPrice());
            statement.setInt(5, product.getQuantite());
            statement.setInt(6, product.getIdProduct());

            if (statement.executeUpdate() == 1) {
                statement.close();
                return true;
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean delete(int idProduct) {
        String qry = "DELETE FROM PRODUCTS WHERE idProduct = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,idProduct);
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

    public int getProductQuantityById(int idProduct) {
        String query = "SELECT quantite FROM PRODUCTS WHERE idProduct = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idProduct);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantite");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching product quantity: " + ex.getMessage());
        }
        return -1;
    }


    public List<Product> searchProducts(String search) {
        List<Product> productList = new ArrayList<>();
        try {
            String query = "SELECT * FROM PRODUCTS WHERE (name LIKE ? OR description LIKE ? OR quantite LIKE ? OR price LIKE ? OR addDate LIKE ?) AND image IS NOT NULL";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            String searchQuery = "%" + search + "%";
            String searchQuery2 = "%" + search + "%";
            String searchQuery3 = "%" + search + "%";
            String searchQuery4 = "%" + search + "%";
            String searchQuery5 = "%" + search + "%";

            preparedStatement.setString(1, searchQuery);
            preparedStatement.setString(2, searchQuery2);
            preparedStatement.setString(3, searchQuery3);
            preparedStatement.setString(4, searchQuery4);
            preparedStatement.setString(5, searchQuery5);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                product.setQuantite(resultSet.getInt("quantite"));
                product.setAddDate(resultSet.getString("addDate"));

                product.setImage(resultSet.getString("image"));


                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> TrieTopRatedProduts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT P.*, MAX(PE.rate) AS max_rate " +
                "FROM PRODUCTS P " +
                "JOIN productsevaluations PE ON P.idProduct  = PE.product  " +
                "GROUP BY P.idProduct " +
                "ORDER BY max_rate DESC LIMIT 10";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("idProduct"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        0,
                        rs.getString("addDate"),
                        rs.getInt("quantite")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> WinnerProducts() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT p.* " +
                "FROM products p " +
                "JOIN souscarts s ON p.idProduct = s.id_product " +
                "GROUP BY p.idProduct " +
                "ORDER BY COUNT(s.id_product) DESC";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("idProduct"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        0,
                        rs.getString("addDate"),
                        rs.getInt("quantite")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public double SommeRateofEvent(int idProduct){
        double rate = 0;
        String query = "SELECT sum(rate) FROM productsevaluations WHERE product = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idProduct);
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

    public int countNBrOfRate(int idProduct) {
        int rate = 0;
        String query = "SELECT count(rate) FROM productsevaluations WHERE product = ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, idProduct);
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

    public List<Float> getAllPrices() {
        List<Float> prices = new ArrayList<>();
        String query = "SELECT price FROM products";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                float price = resultSet.getFloat("price");
                prices.add(price);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prices;
    }

    public List<Product> TrieParPrice() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products ORDER BY price DESC";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getFloat("price"));
                product.setQuantite(resultSet.getInt("quantite"));
                product.setAddDate(resultSet.getString("addDate"));
                product.setImage(resultSet.getString("image"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;

    }

    public List<String> GetImageNameLastNameUser(int owner) {
        List<String> userData = new ArrayList<>();
        String query = "SELECT image, firstName, lastName FROM users WHERE idUser = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, owner);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String image = resultSet.getString("image");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                userData.add(image);
                userData.add(firstName);
                userData.add(lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userData;
    }







}
