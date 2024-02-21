package tn.SIRIUS.services;

import tn.SIRIUS.entities.Commandes;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CommandesService implements ICRUD<Commandes> {
    private Connection con;
    public CommandesService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public int add(Commandes entity) {
        String query = "INSERT INTO commandes (owner,cart,email,city,phone,status) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, entity.getOwner());
            ps.setInt(2, entity.getCart());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getCity());
            ps.setInt(5, entity.getPhone());
            ps.setString(6,"En cours");
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Commandes> getAll() {
        return null;
    }

    @Override
    public boolean update(Commandes entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
