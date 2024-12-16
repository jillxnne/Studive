package StudiveAppGUI;

import StudiveAppGUI.PopUp;
import processing.core.PApplet;

public class PopUpTest extends PApplet {

    // Elements de la Interfície Gràfica (Tria)

    // Tria de 3 opcions
    PopUp c;





    // Color de fons
    int bgColor;

    public static void main(String[] args) {
        PApplet.main("StudiveAppGUI.PopUpTest", args);
    }

    public void settings(){
        size(1200, 600);
        smooth(10);
    }

    public void setup(){
        // Color de fons
        bgColor = color(255);
        // Creació del Tria
        c = new PopUp(this, 100, 100, 800, 340);
        c.setTextButtons("SHARE","EDIT TEST","CHANGE LOCATION");
    }

    public void draw() {

        // Fons de la finestra
        background(bgColor);

        // Dibuixa el Confirm
        c.display(this);

    }

    public void mousePressed(){

        if(c.b1.mouseOverButton(this) && c.b1.isEnabled()){
            c.setVisible(false);
            bgColor = color(255, 0, 0);
        }
        else if(c.b2.mouseOverButton(this) && c.b2.isEnabled()){
            c.setVisible(false);
            bgColor = color(0, 255, 0);
        }
        else if(c.b3.mouseOverButton(this) && c.b3.isEnabled()){
            c.setVisible(false);
            bgColor = color(0, 0, 255);
        }
        else {
            c.setVisible(true);
        }

    }


}