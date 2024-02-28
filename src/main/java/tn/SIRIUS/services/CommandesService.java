package tn.SIRIUS.services;

import tn.SIRIUS.entities.Commandes;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandesService implements ICRUD<Commandes> {
    private Connection con;
    public CommandesService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public int add(Commandes entity) {
        String query = "INSERT INTO commandes (owner,cart,email,city,phone,status,latitude,longitude,total) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, entity.getOwner());
            ps.setInt(2, entity.getCart());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getCity());
            ps.setInt(5, entity.getPhone());
            ps.setString(6,"in progress");
            ps.setDouble(7,entity.getLatitude());
            ps.setDouble(8,entity.getLongitude());
            ps.setFloat(9,entity.getTotal());

            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Commandes> getAll() {
        String query = "SELECT * FROM commandes";
        List<Commandes> CommandesList = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commandes c = new Commandes();
                c.setIdCommande(rs.getInt("idCommande"));
                c.setOwner(rs.getInt("owner"));
                c.setCart(rs.getInt("cart"));
                c.setCommandeDate(rs.getString("commandeDate"));
                c.setEmail(rs.getString("email"));
                c.setCity(rs.getString("city"));
                c.setPhone(rs.getInt("phone"));
                c.setStatus(rs.getString("status"));
                c.setLatitude(rs.getDouble("latitude"));
                c.setLongitude(rs.getDouble("longitude"));
                c.setTotal(rs.getFloat("total"));
                CommandesList.add(c);
            }
            return CommandesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Commandes entity) {
        return false;
    }
    public boolean updateStatus(Commandes commandes) {
        String query = "UPDATE commandes SET status = ? WHERE idCommande = ? ";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, "Delivred");
            statement.setInt(2, commandes.getIdCommande());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
