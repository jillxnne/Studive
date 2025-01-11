package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Flashcards extends PApplet{
    Card[] cards;
    int currentIndex;
    boolean showAnswer;
    float x, y, w, h;

    Flashcards(float x, float y, float w, float h) {
        cards = new Card[10]; // Máximo de 10 tarjetas
        currentIndex = 0;
        showAnswer = false;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void addCard(String question, String answer, int index) {
        if (index < cards.length) {
            cards[index] = new Card(question, answer);
        }
    }

    void nextCard() {
        currentIndex = (currentIndex + 1) % cards.length;
        showAnswer = false; // Reset to question when moving to the next card
    }

    void previousCard() {
        currentIndex = (currentIndex - 1 + cards.length) % cards.length;
        showAnswer = false; // Reset to question when moving to the previous card
    }

    void toggleAnswer() {
        showAnswer = !showAnswer;
    }

    void display(PApplet app) {
        app.fill(255);
        app.stroke(0);
        app.rect(x, y, w, h,20);

        if (cards[currentIndex] == null) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.fill(0);
            app.text("No hay más", x + w / 2, y + h / 2);
            return;
        }

        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(18);
        app.fill(0);

        if (showAnswer) {
            cards[currentIndex].displayAnswer(app, x + w / 2, y + h / 2);
        } else {
            cards[currentIndex].displayQuestion(app, x + w / 2, y + h / 2);
        }
    }
}
