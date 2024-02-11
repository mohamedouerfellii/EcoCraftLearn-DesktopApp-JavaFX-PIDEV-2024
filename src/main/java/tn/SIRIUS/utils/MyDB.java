package tn.SIRIUS.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {
    final String url = "jdbc:mysql://localhost:3306/EcoCraftLearning";
    final String username = "root";
    final String pwd = "";
    private Connection con;
    public static MyDB instance;
    public static MyDB getInstance(){
        if (instance == null)
            instance = new MyDB();
        return instance;
    }
    private MyDB(){
        try{
            con = DriverManager.getConnection(url,username,pwd);
            System.out.println("DataBase connected!");
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    public Connection getCon(){ return this.con;}
}
