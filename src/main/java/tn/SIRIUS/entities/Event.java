package tn.SIRIUS.entities;

public class Event {

    private int idEvent;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String attachment;

    private int owner;

    private String eventType;
    private  String place;
     private int placeNbr;
      private  int price;

    public Event() {
    }

    public Event(int idEvent, String title, String description, String startDate, String endDate, String attachment, int owner, String eventType, String place, int placeNbr, int price) {
        this.idEvent = idEvent;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attachment = attachment;
        this.owner = owner;
        this.eventType = eventType;
        this.place = place;
        this.placeNbr = placeNbr;
        this.price = price;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPlaceNbr() {
        return placeNbr;
    }

    public void setPlaceNbr(int placeNbr) {
        this.placeNbr = placeNbr;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Event{" +
                "idEvent=" + idEvent +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", attachment='" + attachment + '\'' +
                ", owner=" + owner +
                ", eventType='" + eventType + '\'' +
                ", place='" + place + '\'' +
                ", placeNbr=" + placeNbr +
                ", price=" + price +
                '}';
    }



}
