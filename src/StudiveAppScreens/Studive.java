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
        switch (gui.Default) {
            case HOMEPAGE:
                gui.drawHomePage(this);
                break;
            case GENERALLESSONS:
                gui.drawGeneralLessons(this);
                break;
            case MAINLESSONS:
                gui.drawMainLessons(this);
                break;
            case GENERALSTATISTICS:
                gui.drawGeneralStatistics(this);
                break;
            case MAINSTATISTICS:
                gui.drawMainStatistics(this);
                break;
        }
    }

    public void keyPressed() {
        gui.Lection.keyPressed(key, keyCode);

        if (key == '0') {
            gui.Default = SCREENS.HOMEPAGE;
        } else if (key == '1') {
            gui.Default = SCREENS.GENERALLESSONS;
        } else if (key == '2') {
            gui.Default = SCREENS.MAINLESSONS;
        } else if (key == '3') {
            gui.Default = SCREENS.GENERALSTATISTICS;
        } else if (key == '4') {
            gui.Default = SCREENS.MAINSTATISTICS;
        }
    }

    public void mousePressed() {
        if (gui.home.mouseOverButton(this)){
            gui.Default = SCREENS.HOMEPAGE;
        } else if (gui.foldermainbar.mouseOverButton(this)){
            gui.Default = SCREENS.GENERALLESSONS;
        } else if (gui.statistic.mouseOverButton(this)){
            gui.Default = SCREENS.GENERALSTATISTICS;
        }


        if (gui.done.onMouseOver(this)) {
            gui.done.toggle();
        } else if (gui.notDone.onMouseOver(this)) {
            gui.notDone.toggle();
        }

        gui.Lection.isPressed(this);

        if (gui.mainNext.mouseOverButton(this) && gui.mainNext.isEnabled()) {
            gui.mainPageCard.nextPage();
        } else if (gui.mainPrev.mouseOverButton(this) && gui.mainPrev.isEnabled()) {
            gui.mainPageCard.prevPage();
        } else {
            gui.mainPageCard.checkCardSelection(this);
        }

        if (gui.lectNext.mouseOverButton(this) && gui.lectNext.isEnabled()) {
            gui.mainPageLection.nextPage();
        } else if (gui.lectPrev.mouseOverButton(this) && gui.lectPrev.isEnabled()) {
            gui.mainPageLection.prevPage();
        } else {
            gui.mainPageLection.checkCardSelection(this);
        }

        if (gui.statNext.mouseOverButton(this) && gui.statNext.isEnabled()) {
            gui.mainPageStat.nextPage();
        } else if (gui.statPrev.mouseOverButton(this) && gui.statPrev.isEnabled()) {
            gui.mainPageStat.prevPage();
        } else {
            gui.mainPageStat.checkCardSelection(this);
        }
    }
}
