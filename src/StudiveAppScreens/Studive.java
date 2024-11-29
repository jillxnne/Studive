package StudiveAppScreens;
import processing.core.PApplet;

import static StudiveAppScreens.GUI.*;

public class Studive extends PApplet {
    GUI gui;

    public static void main(String[] args) {
        PApplet.main("StudiveAppScreens.Studive", args);
    }

    public void settings() {
        size(1920, 1080);
        smooth(10);
    }

    public void setup() {
        background(255);
        gui = new GUI(this);

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
        gui.Lection.keyPressed(key,keyCode);

        if(key=='0'){
            gui.Default = SCREENS.HOMEPAGE;
        }
        else if(key=='1'){
            gui.Default = SCREENS.GENERALLESSONS;
        }
        else if(key=='2'){
            gui.Default = SCREENS.MAINLESSONS;
        }
        else if(key=='3'){
            gui.Default = SCREENS.GENERALSTATISTICS;
        }
        else if(key=='4'){
            gui.Default = SCREENS.MAINSTATISTICS;
        }
    }

    public void mousePressed(){
        if(gui.c1.onMouseOver(this)){
            gui.c1.toggle();
        }

        gui.Lection.isPressed(this);
        gui.mainPageCard.checkCardSelection(this);
    }


}