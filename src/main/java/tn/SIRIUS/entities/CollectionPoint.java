package tn.SIRIUS.entities;

public class CollectionPoint {
    private int idcollectionPoint;
    private String nameCollectionPoint;
    private  String adressCollectionPoint;
    private  Float capacity;

    public CollectionPoint(int idcollectionPoint, String nameCollectionPoint, String adressCollectionPoint, Float capacity) {
        this.idcollectionPoint = idcollectionPoint;
        this.nameCollectionPoint = nameCollectionPoint;
        this.adressCollectionPoint = adressCollectionPoint;
        this.capacity = capacity;
    }

    public int getIdcollectionPoint() {
        return idcollectionPoint;
    }

    public void setIdcollectionPoint(int idcollectionPoint) {
        this.idcollectionPoint = idcollectionPoint;
    }

    public String getNameCollectionPoint() {
        return nameCollectionPoint;
    }

    public void setNameCollectionPoint(String nameCollectionPoint) {
        this.nameCollectionPoint = nameCollectionPoint;
    }

    public String getAdressCollectionPoint() {
        return adressCollectionPoint;
    }

    public void setAdressCollectionPoint(String adressCollectionPoint) {
        this.adressCollectionPoint = adressCollectionPoint;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }
}
