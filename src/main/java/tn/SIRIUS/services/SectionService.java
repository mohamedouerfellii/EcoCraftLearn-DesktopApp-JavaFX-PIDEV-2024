package tn.SIRIUS.services;

import tn.SIRIUS.entities.Section;
import tn.SIRIUS.iservices.ICRUD;
import tn.SIRIUS.utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionService implements ICRUD<Section> {
    private Connection con;
    public SectionService(){
        con = MyDB.getInstance().getCon();
    }
    @Override
    public boolean add(Section section){
        String query = "INSERT INTO SECTIONS (course,title,description,attachment) VALUES (?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, section.getCourse());
            statement.setString(2,section.getTitle());
            statement.setString(3,section.getDescription());
            statement.setString(4, section.getAttachment());
            if(statement.executeUpdate() == 1){
                statement.close();
                return true;
            }
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public List<Section> getAll(){
        String query = "SELECT * FROM Sections ORDER BY postedDate ASC";
        List<Section> sectionList = new ArrayList<>();
        try(Statement stm = con.createStatement()){
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                sectionList.add(new Section(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return sectionList;
    }
    @Override
    public boolean update(Section section){
        String query = "UPDATE SECTIONS SET title = ? , description = ? , attachment = ? WHERE idSection = ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, section.getTitle());
            statement.setString(2,section.getDescription());
            statement.setString(3, section.getAttachment());
            statement.setFloat(4,section.getIdSection());
            if(statement.executeUpdate() == 1){
                statement.close();
                return true;
            }
            statement.close();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean delete(int sectionId){
        String qry = "DELETE FROM SECTIONS WHERE idSection = ?";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,sectionId);
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
}
