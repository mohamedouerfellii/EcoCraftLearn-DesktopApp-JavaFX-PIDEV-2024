package tn.SIRIUS.entities;

public class Eventsparticipations {
    private int idParticipation;
    private  Event event;
    private  User participant ;

    public Eventsparticipations(int idParticipation, Event event, User participant) {
        this.idParticipation = idParticipation;
        this.event = event;
        this.participant = participant;
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getParticipant() {
        return participant;
    }

    public void setParticipant(User participant) {
        this.participant = participant;
    }
}
