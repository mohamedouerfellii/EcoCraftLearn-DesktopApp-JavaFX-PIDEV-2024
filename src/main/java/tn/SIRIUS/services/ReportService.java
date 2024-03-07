package tn.SIRIUS.services;

import tn.SIRIUS.entities.Post;
import tn.SIRIUS.entities.Report;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportService {
    private Connection con;

    public ReportService() {
        con = MyDB.getInstance().getCon();
    }


    public void reportPost(Report report) {
        String qry="insert into reportedPosts (idPost,type,reason) values(?,?,?)";
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, report.getPost().getIdPost());
            statement.setString(2, report.getType());
            statement.setString(3, report.getReason());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReport(Report report){
        String qry="delete from reportedPosts where idPost=? and type=? ";
        String qry2="delete from Posts where idPost=? ";
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, report.getPost().getIdPost());
            statement.setString(2, report.getType());
         if (  statement.executeUpdate()!=0){
             PreparedStatement statement2 = con.prepareStatement(qry2);
             statement2.setInt(1, report.getPost().getIdPost());
             statement2.executeUpdate();
         }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Report> getAllReports(){
        String qry = "select * from reportedposts " +
                "inner join posts on posts.idPost = reportedposts.idPost";

        List<Report> list = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Post post = new Post(rs.getInt("idPost"),rs.getString("content"),rs.getString("attachment"),rs.getInt("owner"),rs.getTimestamp("postedDate").toLocalDateTime());
                Report report = new Report(rs.getInt("idReport"),post,rs.getString("type"),rs.getString("reason"),rs.getInt("nbrReports"));
                list.add(report);
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    public void updateNbrReports(int id){
        String qry="update reportedPosts set nbrReports=nbrReports+1 where idPost=?";
        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int ReportExist(Report report){
        String qry="select * from reportedPosts where idPost= ? and type= ?";

        try {
            PreparedStatement statement = con.prepareStatement(qry);
            statement.setInt(1, report.getPost().getIdPost());
            statement.setString(2, report.getType());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("idReport");
            }
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;

    }


}
