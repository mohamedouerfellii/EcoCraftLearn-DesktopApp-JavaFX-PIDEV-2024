package tn.SIRIUS.entities;

import java.util.Date;

public class Collect {
    private int idCollect;

    public Collect() {
    }

    private String materialType;
    private double quantity;
    private Date collectDate;
    private CollectionPoint collectionPoint;
    private User user;

    public int getIdCollect() {
        return idCollect;
    }

    public void setIdCollect(int idCollect) {
        this.idCollect = idCollect;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double  quantity) {
        this.quantity = quantity;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    public CollectionPoint getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(CollectionPoint collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collect(int idCollect, String materialType, Double quantity, Date collectDate, CollectionPoint collectionPoint, User user) {
        this.idCollect = idCollect;
        this.materialType = materialType;
        this.quantity = quantity;
        this.collectDate = collectDate;
        this.collectionPoint = collectionPoint;
        this.user = user;
    }
}
