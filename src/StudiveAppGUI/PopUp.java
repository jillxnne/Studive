package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;


public class PopUp {

    // Dimensions
    float x, y, w, h;

    // Propietats
    String title;
    String message;
    public Button b1, b2, b3;
    float buttonH = 80;
    boolean visible = true;

    public PopUp(PApplet p5, float x, float y, float w, float h){
        this.x = x; this.y = y;
        this.w = w; this.h = h;

        float m = 50;
        float wButton = (w - 4*m)/3f;
        this.b1 = new Button(p5, "Opcio 1", x + 160, y +h - buttonH*3.5f, 200, 70);
        this.b2 = new Button(p5, "Opcio2", x + 160, y + h - buttonH*2.5f, 200, 70);
        this.b3 = new Button(p5, "Opcio2", x + 160, y + h - buttonH*1.5f, 200, 70);

        // x + m
    }

    //Setters
    public void setTextButtons(String txt1, String txt2, String txt3){
        this.b1.textBoto = txt1;
        this.b2.textBoto = txt2;
        this.b3.textBoto = txt3;
    }
    public void setVisible(boolean b){
        this.visible = b;
        if(!this.visible){
            this.b1.setEnabled(false);
            this.b2.setEnabled(false);
            this.b3.setEnabled(false);
        }
        else {
            this.b1.setEnabled(true);
            this.b2.setEnabled(true);
            this.b3.setEnabled(true);
        }
    }
    // Dibuixa el Confirm
    public void display(PApplet p5){
        if(this.visible){
            float b = 40;
            p5.pushStyle();
            // Rectangle
            p5.stroke(0); p5.strokeWeight(5); p5.fill(200, 200, 100);
            p5.rect(x, y, w/2, h, b/2);

            // Botons d'opcions
            b1.display(p5);
            b2.display(p5);
            b3.display(p5);
            p5.popStyle();
        }
    }
}
