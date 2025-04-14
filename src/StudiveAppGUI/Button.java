package StudiveAppGUI;
import processing.core.PApplet;

public class Button {
    float x, y, w, h;
    int fillColor, strokeColor;
    int fillColorOver, fillColorDisabled;
    String textBoto;
    boolean enabled;

    // Constructor
    public Button(PApplet p5, String text, float x, float y, float w, float h){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColor = p5.color(170,111,115);
        this.fillColorOver = p5.color(238,169,144);
        this.fillColorDisabled = p5.color(170,111,115);
        this.strokeColor = p5.color(0);
    }

    // Setters

    public void setEnabled(boolean b){
        this.enabled = b;
    }

    public void setTextBoto(String t){ this.textBoto = t; }

    public void setColors(int cFill, int cStroke, int cOver, int cDisabled){
        this.fillColor = cFill;
        this.strokeColor = cStroke;
        this.fillColorOver = cOver;
        this.fillColorDisabled = cDisabled;
    }

    // Getters
    public boolean isEnabled(){
        return  this.enabled;
    }

    // Dibuixa el botó
    public void display(PApplet p5){
        p5.pushStyle();
        if(!enabled){
            p5.fill(fillColorDisabled);
        }
        else if(mouseOverButton(p5)){
            p5.fill(fillColorOver);
        }
        else{
            p5.fill(fillColor);
        }
        p5.stroke(strokeColor); p5.strokeWeight(0);
        p5.rect(this.x, this.y, this.w, this.h, 10);

        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);
        p5.popStyle();
    }

    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }
    boolean isPressed(float px, float py) {
        return px > x && px < x + w && py > y && py < y + h;
    }

    void setLabel(String newText) {
        this.textBoto = newText;
    }
    public boolean checkClick(PApplet app) {
        return mouseOverButton(app) && app.mousePressed;
    }

    public void setPosition(int i, int i1) {
        this.x = i;
        this.y = i1;
    }
}
