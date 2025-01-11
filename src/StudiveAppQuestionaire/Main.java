package StudiveAppQuestionaire;
import processing.core.PApplet;
import java.util.Scanner;
public class Main extends PApplet {
    public static void main(String[] args) {
        PApplet.main("StudiveAppQuestionaire.Main", args);
    }

    Flashcards flashcards;
    public void settings(){
        size(1200, 600);
        smooth(10);
    }
    public void setup() {
        flashcards = new Flashcards(100,100,400,200);

        // Agregar algunas flashcards de prueba
        flashcards.addCard("What is the capital of France?", "Paris", 0);
        flashcards.addCard("What is 2 + 2?", "4", 1);
        flashcards.addCard("Who wrote 'To Kill a Mockingbird'?", "Harper Lee", 2);

    }

    public void draw() {
        background(200, 230, 255);

        flashcards.display(this);
    }

    public void keyPressed() {
        if (key == ' ') {
            flashcards.toggleAnswer();
        } else if (keyCode == RIGHT) {
            flashcards.nextCard();
        } else if (keyCode == LEFT) {
            flashcards.previousCard();
        }
    }

}