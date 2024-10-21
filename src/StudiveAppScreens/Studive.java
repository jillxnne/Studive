package StudiveAppScreens;

import processing.core.PApplet;

import static StudiveAppLayout.Layout.*;

public class Studive extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    Screens gui;

    public static void main(String[] args) {
        PApplet.main("StudiveAppScreens.Studive", args);
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
        gui = new Screens();                   // Constructor de la GUI
    }

    public void draw() {

        // Dibuixa la pantalla corresponent
        switch (gui.pantallaActual) {
            case INICIAL:
                gui.dibuixaPantallaInicial(this);
                break;

            case ABOUT:
                gui.dibuixaPantallaAbout(this);
                break;

            case DETALLS:
                gui.dibuixaPantallaDetalls(this);
                break;
        }

    }
}