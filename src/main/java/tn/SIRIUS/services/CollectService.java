package tn.SIRIUS.services;

import tn.SIRIUS.entities.Collect;
import tn.SIRIUS.entities.CollectionPoint;
import tn.SIRIUS.entities.User;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectService implements ICRUD<Collect> {
    private final Connection con;
    public CollectService(){
        con = MyDB.getInstance().getCon();
    }

    @Override
    public int add(Collect entity) {
        String query = "INSERT INTO collects (materialType,quantity,collectsPt,collector) VALUES (?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, entity.getMaterialType());
            statement.setDouble(2,entity.getQuantity());
            statement.setDouble(3,entity.getCollectionPoint().getIdcollectionPoint());
            statement.setDouble(4,entity.getUser().getIdUser());
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
    public List<Collect> getAll() {
        String query = "SELECT * FROM collects";
        List<Collect> list = new ArrayList<>();
        try (Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                User user=new User(rs.getInt("collector"), "", "", "","","","","",2,2,"");
                CollectionPoint collectionPoint = new CollectionPoint(rs.getInt("collectsPt"),"","",0.0f);
                Collect collect = new Collect(rs.getInt("idCollect"), rs.getString("materialType"),rs.getDouble("quantity"), rs.getDate("collectDate"), collectionPoint, user);
                list.add(collect);
            }} catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }

    @Override
    public int update(Collect entity) {

        String query = "UPDATE collects SET materialType = ? , quantity = ?  WHERE idCollect = ? ";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, entity.getMaterialType());
            statement.setDouble(2,entity.getQuantity());
            statement.setInt(3,entity.getIdCollect());
            if(statement.executeUpdate() == 1){
                statement.close();
                return 1;
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int delete(Collect entity) {
String query = "DELETE FROM collects WHERE idCollect = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, entity.getIdCollect());
            if(statement.executeUpdate() == 1){
                statement.close();
                return 1;
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


}
