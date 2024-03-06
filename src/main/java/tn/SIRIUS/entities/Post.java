package tn.SIRIUS.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Post {
        private  int idPost;
        private  String content;
        private  String attachment;
        private  int owner;
    private LocalDateTime postedDate;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Post(int idPost, String content, String attachment, int owner, LocalDateTime postedDate, User user) {
        this.idPost = idPost;
        this.content = content;
        this.attachment = attachment;
        this.owner = owner;
        this.postedDate = postedDate;
        this.user = user;
    }



    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
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

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return idPost == post.idPost ;
    }



    public Post(int idPost , String content, String attachment, int owner, LocalDateTime postedDate) {
        this.idPost=idPost;
        this.content = content;
        this.attachment = attachment;
        this.owner = owner;
        this.postedDate = postedDate;
    }
}
