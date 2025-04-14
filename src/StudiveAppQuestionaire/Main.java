package StudiveAppQuestionaire;
import processing.core.PApplet;

public class Main extends PApplet {
    FlashCard flashCardCreator;  // For creating flashcards
    FlashCard flashCardViewer;   // For viewing preloaded flashcards
    Card[] iniciales = new Card[] {
            new Card("¿Capital de Francia?", "París"),
            new Card("¿Resultado de 2 + 2?", "4"),
            new Card("¿Color del cielo?", "Azul")
    };

    public static void main(String[] args) {
        PApplet.main("StudiveAppQuestionaire.Main");
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        flashCardCreator = new FlashCard(this, true);  // true means "create mode"

        //flashCardViewer = new FlashCard(this, iniciales);  // false means "view mode"
    }

    public void draw() {
        background(200);
        flashCardCreator.display(this);
    }
    public void keyPressed() {
        flashCardCreator.keyPressed(key, keyCode);
    }

    public void mousePressed() {
        flashCardCreator.mousePressed(this);
    }
}