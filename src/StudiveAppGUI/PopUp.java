package StudiveAppGUI;

import processing.core.PApplet;
import StudiveAppFonts.Fonts;
import static StudiveAppFonts.Sizes.*;

public class PopUp {
    Fonts fonts;
    float x, y, w, h;
    public Button b1;
    float buttonH = 80;
    boolean visible = true;

    public PopUp(PApplet p5, float x, float y, float w, float h){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.b1 = new Button(p5, "Text", x + 120, y+90, 140, 40);
    }

    //Setters
    public void setTextButtons(String txt1){
        this.b1.textBoto = txt1;
    }
    public void setVisible(boolean b){
        this.visible = b;
        if(!this.visible){
            this.b1.setEnabled(false);
        }
        else {
            this.b1.setEnabled(true);
        }
    }
    public void display(PApplet p5){
        if(this.visible){
            p5.pushStyle();
            p5.stroke(0);
            p5.strokeWeight(2);
            p5.fill(200, 200, 100);
            p5.rect(x, y, w/2, h/2, 15);
            b1.display(p5);
            p5.fill(0);
            p5.strokeWeight(2);
            p5.textSize(MidTitleSize);
            p5.text("CREATE ACCOUNT", x+80,y+70);
            p5.popStyle();
        }
    }
}
