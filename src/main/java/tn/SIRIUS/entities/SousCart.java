package tn.SIRIUS.entities;

public class SousCart {

    private int id_SousCarts;
    private int idCart;
    private  int id_product;
    private  int QuantiteProduct;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;

    public SousCart() {
    }

    public SousCart(int id_SousCarts, int idCart, int id_product, int quantiteProduct) {
        this.id_SousCarts = id_SousCarts;
        this.idCart = idCart;
        this.id_product = id_product;
        QuantiteProduct = quantiteProduct;
    }
    public SousCart(int id_SousCarts, int idCart, int id_product, int quantiteProduct,Product p) {
        this.id_SousCarts = id_SousCarts;
        this.idCart = idCart;
        this.id_product = id_product;
        QuantiteProduct = quantiteProduct;
        this.product = p;
    }
    public int getId_SousCarts() {
        return id_SousCarts;
    }

    public void setId_SousCarts(int id_SousCarts) {
        this.id_SousCarts = id_SousCarts;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantiteProduct() {
        return QuantiteProduct;
    }

    public void setQuantiteProduct(int quantiteProduct) {
        QuantiteProduct = quantiteProduct;
    }

    @Override
    public String toString() {
        return "SousCart{" +
                "id_SousCarts=" + id_SousCarts +
                ", idCart=" + idCart +
                ", id_product=" + id_product +
                ", QuantiteProduct=" + QuantiteProduct +
                '}';
    }
}
