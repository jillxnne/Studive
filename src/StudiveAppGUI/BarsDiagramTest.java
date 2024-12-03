package StudiveAppGUI;

import StudiveAppGUI.BarsDiagram;
import processing.core.PApplet;

public class BarsDiagramTest extends PApplet {

    // Elements de la Interfície Gràfica (BarsDiagram)
    // Diagrama de Barres
    BarsDiagram s;

    // Dades del Diagrama (textos, valors i colors)
    String[] textos = {"WATER", "AIR", "FIRE", "EARTH"};
    float[] values = {400, 600, 100, 300};
    int[] colors = {color(0, 0, 255), color(50, 50, 200), color(255, 0, 0), color(0, 255, 0)};


    public static void main(String[] args) {
        PApplet.main("StudiveAppGUI.BarsDiagramTest", args);
    }

    public void settings() {
        size(1200, 800);
        smooth(10);
    }

    public void setup() {
        // Creació del Diagrama de Barres
        s = new BarsDiagram(50, 50, width / 1.2f, height / 1.2f);

        // Configuració de Dades (textos, valors, colors)
        s.setTexts(textos);
        s.setValues(values);
        s.setColors(colors);
    }

    public void draw() {
        // Fons de la finestra
        background(255);

        // Dibuix del Diagrama de Barres
        s.display(this);
    }
}
