package tn.SIRIUS.entities;

public class Product {

    private int idProduct;
 private String name;
 private String description;
 private String image;
 private float price;
 private int owner;
 private String addDate;

    public Product() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public Product(int idProduct ,String name, String description, String image, float price, int owner, String addDate) {
       this.idProduct = idProduct;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.owner = owner;
        this.addDate = addDate;
    }

    public Product(int idProduct, String name, String image, float price) {
        this.idProduct = idProduct;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", owner=" + owner +
                ", addDate='" + addDate + '\'' +
                '}';


    }



}