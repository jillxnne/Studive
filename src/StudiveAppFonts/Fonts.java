package StudiveAppFonts;

import processing.core.PApplet;
import processing.core.PFont;

import static StudiveAppFonts.Sizes.*;

public class Fonts {

    PFont[] fonts;

    public Fonts(PApplet p5){
        this.setFonts(p5);
    }

    // Estableix les fonts de l'App
    void setFonts(PApplet p5){
        this.fonts = new PFont[3];
        this.fonts[0] = p5.createFont("data/Griffiths.otf", TitleSize);
        this.fonts[1] = p5.createFont("data/NostalgicWhispers.ttf", SubtitleSize);
        this.fonts[2] = p5.createFont("data/Stalysta.ttf", ParagraphSize);
    }
    int getNumFonts(){
        return this.fonts.length;
    }
    PFont getFirstFont(){
        return  this.fonts[0];
    }
    PFont getSecondFont(){
        return  this.fonts[1];
    }
    PFont getThirdFont(){
        return  this.fonts[2];
    }
    PFont getFontAt(int i){
        return this.fonts[i];
    }
    void displayFonts(PApplet p5, float x, float y, float h){
        p5.pushStyle();
        for(int i=0; i<getNumFonts(); i++){
            p5.fill(0); p5.stroke(0); p5.strokeWeight(3);
            p5.textFont(getFontAt(i));
            p5.text("Tipography "+i, x, y + i*h);
        }
        p5.popStyle();
    }
}