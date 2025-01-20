package StudiveAppGUI;
import processing.core.PApplet;
import processing.core.PImage;

public class Card {
    PImage img;
    String title;
    String section;
    String description;

    float x, y, w, h, b;

    public Card(String title, String section, String description){
        this.title = title;
        this.section = section;
        this.description = description;
    }

    public Card(String[] info){
        this.title = info[0];
        this.section = info[1];
        this.description = info[2];
    }
    public void setDimensions(float x, float y, float w, float h, float b){
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.b = b;
    }
    public void setImage(PImage img){
        this.img = img;
    }

    public void display(PApplet p5){

        p5.pushStyle();
        if(this.mouseOver(p5)){
            p5.fill(200);
        }
        else {
            p5.fill(220);
        }
        p5.rect(x, y, w, h, b/2);

        float imgW = (w/3) - 2*b;
        float imgH = h - 2*b;
        if(img!=null){
            p5.image(img, x + b, y + b, imgW, imgH);
            p5.noFill();
        }
        else {
            p5.fill(50);
        }

        p5.fill(0); p5.textSize(24); p5.textAlign(p5.CENTER);
        p5.text(title, x + 2 * w / 3, y + h / 5);

        // Tipo de documento (section)
        p5.fill(0); p5.textSize(18); p5.textAlign(p5.CENTER);
        p5.text("Tipo de documento: " + section, x + 2 * w / 3, y + h / 3);

        // Descripción
        p5.fill(0); p5.textSize(14); p5.textAlign(p5.LEFT);
        p5.text(description, x + w / 3 + b, y + 2 * h / 3 - b, 2 * w / 3 - b * 2, h / 4);

        p5.popStyle();
    }
    public boolean mouseOver(PApplet p5){
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}