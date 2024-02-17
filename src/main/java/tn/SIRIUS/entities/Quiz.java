package tn.SIRIUS.entities;


import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private int idQuiz;
    private int section;
    private List<QuizQuestion> questions;

    public Quiz(){
        this.idQuiz =0;
        questions = new ArrayList<>();
    }
    public Quiz(int idQuiz, int section) {
        this.idQuiz = idQuiz;
        this.section = section;
        this.questions = new ArrayList<>();
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }
}
