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
    }
    
    public void mousePressed() {
        if (gui.Default == SCREENS.HOMEPAGE) {
            if (gui.home.mouseOverButton(this)) {
                gui.Default = SCREENS.HOMEPAGE;
            }

            if (gui.plus.mouseOverButton(this)){
                gui.plusFunctions.display(this);
            }

            if (gui.statistic.mouseOverButton(this)) {
                gui.Default = SCREENS.GENERALSTATISTICS;
            }

            if (gui.foldermainbar.mouseOverButton(this)){
                gui.Default = SCREENS.GENERALLESSONS;
            }

            if (gui.mainfolder1.mouseOverButton(this) || gui.mainforlder2.mouseOverButton(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.mainPageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.mainPageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

        } else if (gui.Default == SCREENS.GENERALLESSONS) {
            if (gui.home.mouseOverButton(this)) {
                gui.Default = SCREENS.HOMEPAGE;
            }

            if (gui.statistic.mouseOverButton(this)) {
                gui.Default = SCREENS.GENERALSTATISTICS;
            }

            if (gui.foldermainbar.mouseOverButton(this)){
                gui.Default = SCREENS.GENERALLESSONS;
            }

            if (gui.mainPageLection.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.lectNext.mouseOverButton(this) && gui.lectNext.isEnabled()) {
                gui.mainPageLection.nextPage();
            } else if (gui.lectPrev.mouseOverButton(this) && gui.lectPrev.isEnabled()) {
                gui.mainPageLection.prevPage();
            } else {
                gui.mainPageLection.checkCardSelection(this);
            }

        } else if (gui.Default == SCREENS.MAINLESSONS) {
            if (gui.home.mouseOverButton(this)) {
                gui.Default = SCREENS.HOMEPAGE;
            }

            if (gui.statistic.mouseOverButton(this)) {
                gui.Default = SCREENS.GENERALSTATISTICS;
            }

            if (gui.foldermainbar.mouseOverButton(this)){
                gui.Default = SCREENS.GENERALLESSONS;
            }

            if (gui.typeOfLection.mouseOverSelect(this) && gui.typeOfLection.isEnabled()) {
                if (!gui.typeOfLection.isCollapsed()) {
                    gui.typeOfLection.update(this);
                }
                gui.typeOfLection.toggle();
            }
            gui.Lection.isPressed(this);


            // if one is pressed, other one can't be pressed
            if (gui.done.onMouseOver(this)) {
                gui.done.toggle();
            } else if (gui.notDone.onMouseOver(this)) {
                gui.notDone.toggle();
            }

            if (gui.accessResults.mouseOverButton(this)) {
                gui.Default = SCREENS.MAINSTATISTICS;
            }

        } else if (gui.Default == SCREENS.GENERALSTATISTICS) {
            if (gui.home.mouseOverButton(this)) {
                gui.Default = SCREENS.HOMEPAGE;
            }

            if (gui.statistic.mouseOverButton(this)) {
                gui.Default = SCREENS.GENERALSTATISTICS;
            }

            if (gui.foldermainbar.mouseOverButton(this)){
                gui.Default = SCREENS.GENERALLESSONS;
            }

            if (gui.mainPageStat.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINSTATISTICS;
            }

        } else if (gui.Default == SCREENS.MAINSTATISTICS) ;
        if (gui.home.mouseOverButton(this)) {
            gui.Default = SCREENS.HOMEPAGE;
        }

        if (gui.statistic.mouseOverButton(this)) {
            gui.Default = SCREENS.GENERALSTATISTICS;
        }

        if (gui.foldermainbar.mouseOverButton(this)){
            gui.Default = SCREENS.GENERALLESSONS;
        }
    }
}
