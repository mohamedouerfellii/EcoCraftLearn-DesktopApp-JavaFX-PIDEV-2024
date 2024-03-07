package tn.SIRIUS.entities;

public class Course {
    private int id;
    private String image;
    private String title;
    private String description;
    private User tutor;
    private String duration;
    private float price;
    private int nbrSection;
    private String postedDate;
    private int nbrRegistred;
    private float rate;

    public int getNbrPersonRated() {
        return nbrPersonRated;
    }

    public void setNbrPersonRated(int nbrPersonRated) {
        this.nbrPersonRated = nbrPersonRated;
    }

    private int nbrPersonRated;
    public Course(){
        this.id = 0;
    }
    public Course(int id,String image,String title,String description,User tutor,String duration,float price,int nbrSection,String postedDate,int nbrRegistred,float rate,int nbrPersonRated){
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.tutor = tutor;
        this.duration = duration;
        this.price = price;
        this.nbrSection = nbrSection;
        this.postedDate = postedDate;
        this.nbrRegistred = nbrRegistred;
        this.rate = rate;
        this.nbrPersonRated = nbrPersonRated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public float getPrice() {
        return price;
    }

    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getNbrSection() {
        return nbrSection;
    }

    public void setNbrSection(int nbrSection) {
        this.nbrSection = nbrSection;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
    public void setNbrRegistred(int nbrRegistred){ this.nbrRegistred = nbrRegistred; }
    public int getNbrRegistred(){ return this.nbrRegistred; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tutor=" + tutor +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                ", nbrSection=" + nbrSection +
                ", postedDate='" + postedDate + '\'' +
                ", nbrRegistred=" + nbrRegistred +
                '}';
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj instanceof Course c)
            return c.getId() == this.getId();
        return false;
    }

}
