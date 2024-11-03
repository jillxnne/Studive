package StudiveAppLayout;

import processing.core.PApplet;

import static StudiveAppLayout.Layout.*;

public class Studive extends PApplet {

    public static void main(String[] args) {
        PApplet.main("StudiveAppLayout.Studive", args);
    }

    public void settings() {
        //fullScreen();                       // Pantalla completa
        size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup() {
        noStroke();                         // Sense bordes
        textAlign(CENTER);
        textSize(18);   // Alineació i mida del text
    }

    public void draw() {
        background(0);

    }
}

