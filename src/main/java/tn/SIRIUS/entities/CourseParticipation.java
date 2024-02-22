package tn.SIRIUS.entities;

public class CourseParticipation {
    private int idCP;
    private int idParticipant;
    private int idCourse;
    private String participationDate;

    public int getIdCP() {
        return idCP;
    }

    public void setIdCP(int idCP) {
        this.idCP = idCP;
    }

    public int getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(int idParticipant) {
        this.idParticipant = idParticipant;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(String participationDate) {
        this.participationDate = participationDate;
    }

    public CourseParticipation(int idCP, int idParticipant, int idCourse, String participationDate) {
        this.idCP = idCP;
        this.idParticipant = idParticipant;
        this.idCourse = idCourse;
        this.participationDate = participationDate;
    }
    public CourseParticipation(){}
}
