package tn.SIRIUS.entities;

public class Feedback {
    private int id;
    private String authorName;
    private String date;
    private String content;
    public Feedback(int id,String authorName,String date,String content){
        this.id = id;
        this.authorName = authorName;
        this.date = date;
        this.content = content;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
}
