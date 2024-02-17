package tn.SIRIUS.entities;

public class QuizQuestion {
    private int idQuestion;
    private int quiz;
    String question;
    String choice_1;
    String choice_2;
    String choice_3;
    String choice_4;
    String correct_choice;

    public QuizQuestion(){}
    public QuizQuestion(int idQuestion, int quiz, String question, String choice_1, String choice_2, String choice_3, String choice_4, String correct_choice) {
        this.idQuestion = idQuestion;
        this.quiz = quiz;
        this.question = question;
        this.choice_1 = choice_1;
        this.choice_2 = choice_2;
        this.choice_3 = choice_3;
        this.choice_4 = choice_4;
        this.correct_choice = correct_choice;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice_1() {
        return choice_1;
    }

    public void setChoice_1(String choice_1) {
        this.choice_1 = choice_1;
    }

    public String getChoice_2() {
        return choice_2;
    }

    public void setChoice_2(String choice_2) {
        this.choice_2 = choice_2;
    }

    public String getChoice_3() {
        return choice_3;
    }

    public void setChoice_3(String choice_3) {
        this.choice_3 = choice_3;
    }

    public String getChoice_4() {
        return choice_4;
    }

    public void setChoice_4(String choice_4) {
        this.choice_4 = choice_4;
    }

    public String getCorrect_choice() {
        return correct_choice;
    }

    public void setCorrect_choice(String correct_choice) {
        this.correct_choice = correct_choice;
    }
}
