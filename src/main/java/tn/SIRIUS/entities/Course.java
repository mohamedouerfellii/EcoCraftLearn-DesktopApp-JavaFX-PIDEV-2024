package tn.SIRIUS.entities;

public class Course {
    private int id;
    private String image;
    private String title;
    private String description;
    private int tutor;
    private String duration;
    private float price;
    private int nbrSection;
    private String postedDate;
    private int nbrRegistred;
    public Course(){
    }
    public Course(int id,String image,String title,String description,int tutor,String duration,float price,int nbrSection,String postedDate,int nbrRegistred){
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

    public int getTutor() {
        return tutor;
    }

    public void setTutor(int tutor) {
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
}
