package tn.SIRIUS.entities;

public class Commandes {
    private int idCommande;
    private int owner;
    private String commandeDate;
   private int cart ;

   private String email;

   private String city;
   private int phone ;

    private String status;

    private double latitude;

    private  double longitude;

    private float total;

    public Commandes() {
    }

    public Commandes(int idCommande, int owner, String commandeDate, int cart, String email, String city, int phone ,String status,double latitude,double longitude,float total) {
        this.idCommande = idCommande;
        this.owner = owner;
        this.commandeDate = commandeDate;
        this.cart = cart;
        this.email = email;
        this.city = city;
        this.phone = phone;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.total = total;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getCommandeDate() {
        return commandeDate;
    }

    public void setCommandeDate(String commandeDate) {
        this.commandeDate = commandeDate;
    }

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Commandes{" +
                "idCommande=" + idCommande +
                ", owner=" + owner +
                ", commandeDate='" + commandeDate + '\'' +
                ", cart=" + cart +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", phone=" + phone +
                ", status='" + status + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", total=" + total +
                '}';

    }



}
