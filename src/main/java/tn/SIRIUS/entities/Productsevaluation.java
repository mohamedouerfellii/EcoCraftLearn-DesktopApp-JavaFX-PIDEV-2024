package tn.SIRIUS.entities;

public class Productsevaluation {
   private int idEvaluation;
   private int rate;

   private String review;

   private  String evaluationDate;

   private int product;
   private int evaluator;

    public Productsevaluation() {
    }

    public Productsevaluation(int idEvaluation, int rate, String review, String evaluationDate, int product, int evaluator) {
        this.idEvaluation = idEvaluation;
        this.rate = rate;
        this.review = review;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
                ", review='" + review + '\'' +
                ", evaluationDate='" + evaluationDate + '\'' +
                ", product=" + product +
                ", evaluator=" + evaluator +
                '}';
    }

}
