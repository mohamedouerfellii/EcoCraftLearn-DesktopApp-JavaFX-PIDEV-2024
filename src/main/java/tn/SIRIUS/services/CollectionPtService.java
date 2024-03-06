package tn.SIRIUS.services;

import tn.SIRIUS.entities.CollectionPoint;
import tn.SIRIUS.entities.Course;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionPtService implements ICRUD<CollectionPoint> {

    private final Connection con;
    public CollectionPtService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public int add(CollectionPoint entity) {
        String query = "INSERT INTO collectspts (name,address,capacity) VALUES (?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, entity.getNameCollectionPoint());
            statement.setString(2,entity.getAdressCollectionPoint());
            statement.setFloat(3,entity.getCapacity());

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
    public List<CollectionPoint> getAll() {
        String query = "SELECT * FROM collectspts ";
        List<CollectionPoint> collectionptsList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                collectionptsList.add(new CollectionPoint(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getFloat(4)

                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return collectionptsList;
    }

    @Override
    public int update(CollectionPoint entity) {
        String query = "UPDATE collectspts SET name = ? , address =? , capacity =? WHERE idCollectsPts  = ? ";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, entity.getNameCollectionPoint());
            statement.setString(2,entity.getAdressCollectionPoint());
            statement.setFloat(3,entity.getCapacity());
            statement.setInt(4,entity.getIdcollectionPoint());
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
    public int delete(CollectionPoint entity) {
        String query = "delete from collectspts  WHERE idCollectsPts  = ? ";
        try{

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,entity.getIdcollectionPoint());
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
}
