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
            case DONELESSONS:
                gui.drawDoneLessons(this);
                break;
            case NOTDONELESSONS:
                gui.drawNotDoneLessons(this);
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
            mainBarFunctions(this);

            if (gui.mainfolder1.mouseOverButton(this)) {
                gui.Default = SCREENS.NOTDONELESSONS;
            }

            if (gui.mainforlder2.mouseOverButton(this)){
                gui.Default = SCREENS.DONELESSONS;
            }

            if (gui.mainPageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.mainPageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

        } else if (gui.Default == SCREENS.GENERALLESSONS) {
            mainBarFunctions(this);

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

        } else if (gui.Default == SCREENS.NOTDONELESSONS){
            mainBarFunctions(this);
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

        } else if (gui.Default == SCREENS.DONELESSONS){
            mainBarFunctions(this);
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
            mainBarFunctions(this);

            if (gui.typeOfLection.mouseOverSelect(this) && gui.typeOfLection.isEnabled()) {
                if (!gui.typeOfLection.isCollapsed()) {
                    gui.typeOfLection.update(this);
                }
                gui.typeOfLection.toggle();
            } else {
                gui.Lection.isPressed(this);
            }

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
            mainBarFunctions(this);

            if (gui.mainPageStat.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINSTATISTICS;
            }
            if (gui.statNext.mouseOverButton(this) && gui.statNext.isEnabled()) {
                gui.mainPageStat.nextPage();
            } else if (gui.statPrev.mouseOverButton(this) && gui.statPrev.isEnabled()) {
                gui.mainPageStat.prevPage();
            } else {
                gui.mainPageStat.checkCardSelection(this);
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
    public void mainBarFunctions(PApplet p5){
        if (gui.home.mouseOverButton(this)) {
            gui.Default = SCREENS.HOMEPAGE;
        }
        boolean plusVisible = false;
        if (gui.plus.mouseOverButton(this) && mousePressed) {
            plusVisible = true;
            System.out.println("A");
        }
        if (plusVisible) {
            gui.plusFunctions.display(this);
            if ((gui.plusFunctions.b1.mouseOverButton(this) && mousePressed && gui.plusFunctions.b1.isEnabled())
                    || (gui.plusFunctions.b2.mouseOverButton(this) && mousePressed && gui.plusFunctions.b2.isEnabled())
                    || (gui.plusFunctions.b3.mouseOverButton(this) && mousePressed && gui.plusFunctions.b3.isEnabled())) {
                if (gui.plusFunctions.b1.mouseOverButton(this)) {
                    System.out.println("B1");
                } else if (gui.plusFunctions.b2.mouseOverButton(this)) {
                    System.out.println("B2");
                } else if (gui.plusFunctions.b3.mouseOverButton(this)) {
                    System.out.println("B3");
                }
            }
        }

        if (gui.statistic.mouseOverButton(this)) {
            gui.Default = SCREENS.GENERALSTATISTICS;
        }

        if (gui.foldermainbar.mouseOverButton(this)){
            gui.Default = SCREENS.GENERALLESSONS;
        }
    }
}
