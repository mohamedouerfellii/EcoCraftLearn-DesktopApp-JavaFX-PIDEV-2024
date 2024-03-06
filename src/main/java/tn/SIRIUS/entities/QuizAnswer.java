package tn.SIRIUS.entities;

public class QuizAnswer {
    private int idAnswer;
    private User student;
    private Quiz quiz;
    private float result;
    private String answerDate;
    public  QuizAnswer(){idAnswer = 0;}

    public QuizAnswer(int idAnswer, User student, Quiz quiz, float result, String answerDate) {
        this.idAnswer = idAnswer;
        this.student = student;
        this.quiz = quiz;
        this.result = result;
        this.answerDate = answerDate;
    }

    public int getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(int idAnswer) {
        this.idAnswer = idAnswer;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }
}
