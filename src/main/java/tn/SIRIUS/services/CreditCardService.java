package tn.SIRIUS.services;

import tn.SIRIUS.entities.CreditCard;
import tn.SIRIUS.utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardService {
    private Connection con;
    public CreditCardService(){
        con = MyDB.getInstance().getCon();
    }
    public boolean addCard(CreditCard creditCard){
        String qry = "INSERT INTO CREDITCARDS (owner,cardNumber,ccv,expireDate) VALUES (?,SHA2(?,256),SHA2(?,256),SHA2(?,256))";
        try{
            PreparedStatement stm = con.prepareStatement(qry);
            stm.setInt(1,creditCard.getOwner().getId());
            stm.setString(2,creditCard.getCardNumber());
            stm.setString(3,creditCard.getCcv());
            stm.setString(4,creditCard.getExpiredDate());
            if (stm.executeUpdate() == 1){
                stm.close();
                return true;
            }
            stm.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean isUserHadCreditCard(int idUser){
        String query = "SELECT owner FROM CREDITCARDS WHERE owner = ?";
        try{
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,idUser);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                stm.close();
                return true;
            }
            return false;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
