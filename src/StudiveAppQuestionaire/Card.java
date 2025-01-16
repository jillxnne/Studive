package StudiveAppQuestionaire;
import processing.core.PApplet;

public class Card {
    String question;
    String answer;

    Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    void displayQuestion(PApplet app, float x, float y) {
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(20);
        app.fill(0);
        app.text("Q: " + question, x, y);
    }

    void displayAnswer(PApplet app, float x, float y) {
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(20);
        app.fill(0);
        app.text("A: " + answer, x, y);
    }
}