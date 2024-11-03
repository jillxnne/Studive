package StudiveAppScreens;
import processing.core.PApplet;

import static StudiveAppScreens.GUI.*;

public class Studive extends PApplet {

    // Interfície Gràfica (Pantalles i components)
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("StudiveAppScreens.Studive", args);
    }

    public void settings() {
        size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup() {
        background(255); // Fondo negro
        gui = new GUI();                   // Constructor de la GUI

    }

    public void draw() {
        switch(gui.Default){
            case HOMEPAGE:   gui.drawHomePage(this);
                break;
            case GENERALLESSONS:     gui.drawGeneralLessons(this);
                break;
            case MAINLESSONS:   gui.drawMainLessons(this);
                break;
            case GENERALSTATISTICS:   gui.drawGeneralStatistics(this);
                break;
            case MAINSTATISTICS:   gui.drawMainStatistics(this);
                break;
        }

    }
    public void keyPressed(){
        if(key=='0'){
            gui.Default = SCREENS.HOMEPAGE;
        }
        else if(key=='1'){
            gui.Default = SCREENS.GENERALLESSONS;
        }
        else if(key=='2'){
            gui.Default = SCREENS.MAINLESSONS;
        }

    }
}