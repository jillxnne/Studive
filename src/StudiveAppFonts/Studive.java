package StudiveAppFonts;

import processing.core.PApplet;

public class Studive extends PApplet {
    Fonts fontsApp;

    public static void main(String[] args) {
        PApplet.main("StudiveAppFonts.Studive", args);
    }

    public void settings() {
        size(800, 800, P2D);
        smooth(10);
    }

    public void setup() {
        fontsApp = new Fonts(this);
    }

    public void draw() {
        background(255);

        textFont(fontsApp.getFirstFont());
        text("Title of the app", 50, 200);

        fill(50);
        textFont(fontsApp.getSecondFont());
        text("Subtitle of the app", 50, 250);

        fill(55, 0, 0);
        textFont(fontsApp.getThirdFont());
        text("Paragraph of the app", 50, 300);

        fontsApp.displayFonts(this, 100, 400, 50);


    }
}