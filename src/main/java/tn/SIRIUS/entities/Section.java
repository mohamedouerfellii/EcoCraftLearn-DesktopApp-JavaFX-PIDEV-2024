package tn.SIRIUS.entities;

public class Section {
    private int idSection;
    private int course;
    private String title;
    private String description;
    private String attachment;
    private String postedDate;
    public Section(){}
    public Section(int idSection, int course, String title, String description, String attachment, String postedDate) {
        this.idSection = idSection;
        this.course = course;
        this.title = title;
        this.description = description;
        this.attachment = attachment;
        this.postedDate = postedDate;
    }
    public int getIdSection() {
        return idSection;
    }
    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }
    public int getCourse() {
        return course;
    }
    public void setCourse(int course) {
        this.course = course;
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
    public String getAttachment() {
        return attachment;
    }
    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
    public String getPostedDate(){
        return this.postedDate;
    }
    public void setPostedDate(String postedDate){
        this.postedDate = postedDate;
    }
}
