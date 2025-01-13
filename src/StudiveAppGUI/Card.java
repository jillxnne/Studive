package StudiveAppGUI;
import processing.core.PApplet;
import processing.core.PImage;

public class Card {
    PImage img;
    String title, section;
    float x, y, w, h, b;

    public Card(String[] info){
        this.title = info[0];
        this.section = info[1];
    }

    public void setDimensions(float x, float y, float width, float height, float b){
        this.x = x; this.y = y;
        this.w = width; this.h = height;
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
        p5.text(title, x + 2*w/3, y + h/5);

        // Lloc i data
        p5.fill(0); p5.textSize(18); p5.textAlign(p5.CENTER);
        p5.text(section, x + w/3 + w/6, y + 2*h/5);

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5){
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}