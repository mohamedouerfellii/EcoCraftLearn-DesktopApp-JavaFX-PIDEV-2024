package tn.SIRIUS.entities;

public class Eventsevaluation {

    private int idEvaluation ;
    private int event ;
    private int evaluator ;
    private double rate;

    public Eventsevaluation() {
    }

    public Eventsevaluation(int idEvaluation, int event, int evaluator, double rate) {
        this.idEvaluation = idEvaluation;
        this.event = event;
        this.evaluator = evaluator;
        this.rate = rate;
    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(int evaluator) {
        this.evaluator = evaluator;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Eventsevaluation{" +
                "idEvaluation=" + idEvaluation +
                ", event=" + event +
                ", evaluator=" + evaluator +
                ", rate=" + rate +
                '}';
    }


}
