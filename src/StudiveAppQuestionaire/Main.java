package StudiveAppQuestionaire;
import processing.core.PApplet;

public class Main extends PApplet {
    Flashcards flashcards;

    public static void main(String[] args) {
        PApplet.main("StudiveAppQuestionaire.Main");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        flashcards = new Flashcards();
    }

    public void draw() {
        background(200);
        flashcards.display(this);
    }
    public void keyPressed() {
        flashcards.keyPressed(this);
    }

    public void mousePressed() {
        flashcards.mousePressed(this);
    }
}