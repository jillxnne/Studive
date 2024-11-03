
import StudiveAppScreens.GUI;
import processing.core.PApplet;

public class Studive extends PApplet {
    public static void main(String[] args) {
        PApplet.main("Studive",args);
    }

    public void settings() {
        size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup() {
        background(255); // Fondo negro
                          // Constructor de la GUI

    }

    public void draw() {
        fill(0); // Color de relleno del rectángulo (azul)
        rect(0, height - 100, width, height);
    }
}
