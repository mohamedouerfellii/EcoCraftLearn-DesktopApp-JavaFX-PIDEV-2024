package tn.SIRIUS.entities;

public class User {
          private int  idUser;
          private String  firstName;
          private String  lastName;
          private String email;
          private int numTel;
          private String   role;
          private String gender;
          private int isActive;
          private  int nbrPtsCollects;
          private String image;

    public User(int idUser, String firstName, String lastName, String email, int numTel, String role, String gender, int isActive, int nbrPtsCollects, String image) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numTel = numTel;
        this.role = role;
        this.gender = gender;
        this.isActive = isActive;
        this.nbrPtsCollects = nbrPtsCollects;
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getNbrPtsCollects() {
        return nbrPtsCollects;
    }

    public void setNbrPtsCollects(int nbrPtsCollects) {
        this.nbrPtsCollects = nbrPtsCollects;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
