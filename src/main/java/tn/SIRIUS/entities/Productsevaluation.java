package tn.SIRIUS.entities;

public class Productsevaluation {
   private int idEvaluation;
   private double rate;

   private  String evaluationDate;

   private int product;
   private int evaluator;



    public Productsevaluation() {
    }

    public Productsevaluation(int idEvaluation, double rate, String evaluationDate, int product, int evaluator) {
        this.idEvaluation = idEvaluation;
        this.rate = rate;
        this.evaluationDate = evaluationDate;
        this.product = product;
        this.evaluator = evaluator;

    }

    public int getIdEvaluation() {
        return idEvaluation;
    }

    public void setIdEvaluation(int idEvaluation) {
        this.idEvaluation = idEvaluation;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getEvaluationDate() {
        return evaluationDate;
    }

    public void setEvaluationDate(String evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(int evaluator) {
        this.evaluator = evaluator;
    }



    @Override
    public String toString() {
        return "Productsevaluation{" +
                "idEvaluation=" + idEvaluation +
                ", rate=" + rate +
                ", evaluationDate='" + evaluationDate + '\'' +
                ", product=" + product +
                ", evaluator=" + evaluator +
                '}';
    }
}
