package tn.SIRIUS.entities;

public class User {
    private int idUser;

    public User(int id,String firstName, String lastName, String image) {
        this.idUser = id;
        this.FirstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    private String FirstName;
    private String lastName;
    private String email;
    private String password;
    private String numTel;
    private String role;
    private String gender;
    private int isActive;
    private int nbrPtsCollects;
    private String image;

    public User(int idUser, String firstName, String lastName, String email, String password, String numTel, String role, String gender, int isActive, int nbrPtsCollects, String image) {

        this.idUser= idUser;
        FirstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
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

    public User() {
    }

    public void setImage(String image) {
        this.image = image;
    }
}
