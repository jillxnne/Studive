package StudiveAppGUI;
import processing.core.PApplet;
import processing.core.PImage;

public class ImageButtons {

    float x, y, r;
    PImage imgNormal;
    PImage imgHover;
    boolean enabled;

    public ImageButtons(PApplet p5, PImage imgNormal, PImage imgHover, float x, float y, float r){
        this.imgNormal = imgNormal;
        this.imgHover = imgHover;
        this.x = x;
        this.y = y;
        this.r = r;

    }
    public void display(PApplet p5){
        p5.pushStyle();
        if(mouseOverButton(p5)){
            p5.imageMode(p5.CENTER);
            p5.image(this.imgHover, this.x, this.y, 2*this.r, 2*this.r);
        }
        else {
            p5.imageMode(p5.CENTER);
            p5.image(this.imgNormal, this.x, this.y, 2*this.r, 2*this.r);
        }
        p5.popStyle();
    }
    public boolean mouseOverButton(PApplet p5){
        return p5.dist(p5.mouseX, p5.mouseY, this.x, this.y)<= this.r;
    }


}
