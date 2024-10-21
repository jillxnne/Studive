package StudiveAppColors;
import processing.core.PApplet;

public class Studive extends PApplet{
    Colors appColors;
    public static void main(String[] args) {
        PApplet.main("StudiveAppColors.Studive", args);
    }

    public void settings(){
        size(800, 800, P2D);
        smooth(10);
    }

    public void setup() {
        appColors = new Colors(this);
    }

    public void draw(){
        background(255);

        fill(appColors.getColorAt(4)); noStroke();
        rect(0, 3*height/4, width, height/4);

        appColors.displayColors(this, 100,100,width-200);
    }


}
