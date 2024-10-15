
import processing.core.PApplet;

public class Studive extends PApplet {
    Colors AppColors;
    Cercle c1,c2,c3,c4;

    public static void main(String[] args) {
        PApplet.main("Studive", args);
    }

    public void settings(){
        size(800, 800, P2D);
        smooth(10);
    }

    public void setup(){
        AppColors = new Colors(this);
        c1 = new Cercle(width/4, height/2, 50);
        c1.setColor(AppColors.getFirstColor());  // Color primari

        c2 = new Cercle(width/2, height/2, 50);
        c2.setColor(AppColors.getSecondColor());  // Color secundari

        c3 = new Cercle(3*width/4, height/2, 50);
        c3.setColor(AppColors.getThirdColor());
    }

    public void draw(){
        background(255);

        background(255);

        c1.display(this);
        c2.display(this);
        c3.display(this);

        fill(AppColors.getColorAt(4)); noStroke();
        rect(0, 3*height/4, width, height/4);

        AppColors.displayColors(this, 100,100,width-200);
    }

}
