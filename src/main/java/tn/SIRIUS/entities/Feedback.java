package tn.SIRIUS.entities;

public class Feedback {
    private int id;
    private User owner;
    private String date;
    private String content;
    private int rate;
    private int course;

    public Feedback(){this.id = 0;}
    public Feedback(int id, User owner, String date, String content, int rate,int course){
        this.id = id;
        this.owner = owner;
        this.date = date;
        this.content = content;
        this.rate = rate;
        this.course = course;
    }
    public int getId() {
        return id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setId(int id) {
        this.id = id;
    }
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj instanceof Feedback fb)
            return fb.getRate() == this.getRate() && fb.getContent().equals(this.getContent());
        return false;
    }
}
