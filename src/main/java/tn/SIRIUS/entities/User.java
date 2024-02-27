package tn.SIRIUS.entities;

import java.util.Objects;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int number;
    private String Email;
    private String gender;
    private String Password;
    private String question;
    private String answer;
    private String roles;
    private String Image;
    private boolean isActive;
    private int nbrPtsCollects;
    public User(){

    }

    public User(int id,String firstName, String lastName, int number, String Email, String gender, String Password, String roles,String image,boolean isActive,int nbrPtsCollects) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.Email = Email;
        this.gender = gender;
        this.Password = Password;
        this.roles= roles;
        this.Image=image;
        this.isActive=isActive;
        this.nbrPtsCollects=nbrPtsCollects;
    }

    public User(int id, String firstName, String password, String roles) {
        this.id = id;
        this.firstName = firstName;
        Password = password;
        this.roles = roles;
    }

    public User(String firstName, String lastName, int number, String Email, String gender, String Password, String roles, String image, String answer, String question) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.Email = Email;
        this.gender = gender;
        this.Password = Password;
        this.roles= roles;
        this.Image=image;
        this.answer=answer;
        this.question=question;

    }
    public User(int id,String firstName, String lastName, int number, String Email, String gender, String Password,String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.Email = Email;
        this.gender = gender;
        this.Password = Password;

        this.Image=image;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return Password;
    }


    public void setPassword(String password) {
        Password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getNbrPtsCollects() {
        return nbrPtsCollects;
    }

    public void setNbrPtsCollects(int nbrPtsCollects) {
        this.nbrPtsCollects = nbrPtsCollects;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + number +
                ", Email='" + Email + '\'' +
                ", gender='" + gender + '\'' +
                ", Password='" + Password + '\'' +
                ", roles='" + roles + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }

}
