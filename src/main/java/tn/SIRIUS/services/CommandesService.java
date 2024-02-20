package tn.SIRIUS.services;

import tn.SIRIUS.entities.Commandes;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandesService implements ICRUD<Commandes> {
    private Connection con;
    public CommandesService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(Commandes entity) {
         String query = "INSERT INTO commandes (owner ,commandeDate,cart ,address) VALUES (?,?,?,?)";
        con = MyDB.getInstance().getCon();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, entity.getOwner());
            ps.setString(2, entity.getCommandeDate());
            ps.setInt(3, entity.getCart());
            ps.setString(4, entity.getAddress());
            if(ps.executeUpdate() == 1){
                ps.close();
                return 1;
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<Commandes> getAll() {
      String query = "SELECT * FROM commandes";
      con = MyDB.getInstance().getCon();
      List<Commandes> list = new ArrayList<>();
      try {
          PreparedStatement ps = con.prepareStatement(query);
          ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              Commandes commandes = new Commandes(rs.getInt("idCommandes"),
                      rs.getInt("owner"),
                      rs.getString("commandeDate"),
                      rs.getInt("cart"),
                      rs.getString("address"));
              list.add(commandes);
          }
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      return list;
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
