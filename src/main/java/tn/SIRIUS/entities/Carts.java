package tn.SIRIUS.entities;

public class Carts {
    private int idCarts;

    private float totalPrice;

    private int isConfirmed;
    private int owner;

    public Carts() {
    }

    public Carts(int idCarts, float totalPrice, int isConfirmed, int owner) {
        this.idCarts = idCarts;
        this.totalPrice = totalPrice;
        this.isConfirmed = isConfirmed;
        this.owner = owner;
    }

    public int getIdCarts() {
        return idCarts;
    }

    public void setIdCarts(int idCarts) {
        this.idCarts = idCarts;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Carts{" +
                "idCarts=" + idCarts +
                ", totalPrice=" + totalPrice +
                ", isConfirmed=" + isConfirmed +
                ", owner=" + owner +
                '}';
    }
}
