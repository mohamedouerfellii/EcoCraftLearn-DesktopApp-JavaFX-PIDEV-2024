package tn.SIRIUS.entities;

public class CreditCard {
    private int idCard;
    private User owner;
    private String cardNumber;
    private String cardholderName;
    private String ccv;
    private String expiredDate;
    public CreditCard(){}

    public CreditCard(int idCard, User owner, String cardNumber, String cardholderName, String ccv, String expiredDate) {
        this.idCard = idCard;
        this.owner = owner;
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.ccv = ccv;
        this.expiredDate = expiredDate;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }
}
