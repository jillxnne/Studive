package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Question extends PApplet {
    String question;
    String[] answers;
    int correctAnswerIndex;

    public Question(String question, String[] answers, int correctAnswerIndex) {
        this.question = question;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }
    public String[] getAnswers() {
        return answers;
    }
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}