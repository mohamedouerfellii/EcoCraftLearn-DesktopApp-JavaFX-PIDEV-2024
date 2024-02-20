package tn.SIRIUS.services;

import tn.SIRIUS.entities.Carts;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartsService implements ICRUD<Carts> {
    private Connection con;

    public CartsService() {
        con = MyDB.getInstance().getCon();
    }


    @Override
    public int add(Carts entity) {
        String query = "INSERT INTO carts (totalPrice,isConfirmed,owner ) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, entity.getTotalPrice());
            ps.setInt(2, entity.getIsConfirmed());
            ps.setInt(3, entity.getOwner());
            if (ps.executeUpdate() == 1) {
                ps.close();
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Carts> getAll() {
        List<Carts> cartsList = new ArrayList<>();
        String query = "SELECT * FROM carts";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carts carts = new Carts(rs.getInt("idCarts"),
                        rs.getFloat("totalPrice"),
                        rs.getInt("isConfirmed"),
                        rs.getInt("owner"));
                cartsList.add(carts);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartsList;
    }

    @Override
    public boolean update(Carts entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }


    public Carts getByIdIfNotConfirmed(int id) {
        String query = "SELECT * FROM carts WHERE owner = ? AND isConfirmed = 0";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Carts(rs.getInt("idCarts"),
                        rs.getFloat("totalPrice"),
                        rs.getInt("isConfirmed"),
                        rs.getInt("owner"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public int updateIsConfirmed(int id) {
        String query = "UPDATE carts SET isConfirmed = ? WHERE idCarts = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public int updateTotalPrice(int id, double newTotalPrice) {
        String query = "UPDATE carts SET totalPrice = ? WHERE idCarts = ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, newTotalPrice);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
