package tn.SIRIUS.entities;

public class Comment {
    private int idComment;
    private Post post;
    private User owner;
    private  String content;
    private String attachment;

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Comment(int idComment, Post post, User owner, String content, String attachment) {
        this.idComment = idComment;
        this.post = post;
        this.owner = owner;
        this.content = content;
        this.attachment = attachment;
    }
}
