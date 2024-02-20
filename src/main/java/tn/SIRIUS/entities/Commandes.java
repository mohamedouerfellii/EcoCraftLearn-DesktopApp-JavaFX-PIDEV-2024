package tn.SIRIUS.entities;

public class Commandes {
    private int idCommande;
    private int owner;
    private String commandeDate;
   private int cart ;

   private String address;

    public Commandes() {
    }

    public Commandes(int idCommande, int owner, String commandeDate, int cart, String address) {
        this.idCommande = idCommande;
        this.owner = owner;
        this.commandeDate = commandeDate;
        this.cart = cart;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Commandes{" +
                "idCommande=" + idCommande +
                ", owner=" + owner +
                ", commandeDate='" + commandeDate + '\'' +
                ", cart=" + cart +
                ", address='" + address + '\'' +
                '}';
    }


}
