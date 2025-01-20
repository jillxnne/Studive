package gui;
import gui.PagedCard;
import processing.core.PApplet;
import processing.core.PImage;


public class PagedCardTest extends PApplet {
    PagedCard pc;
    float cardsW = 800, cardsH = 700;
    int numCardsPage = 3;
    String[][] info = {
            {"info"},
            {"mates"},
            {"historia"}
    };
    PImage img1;
    public static void main(String[] args) {
        PApplet.main("gui.PagedCardTest", args);
    }
    public void settings(){
        size(1200, 800);
        smooth(10);
    }
    public void setup(){
        img1 = loadImage("data/foldermain.png");


        pc = new PagedCard(numCardsPage);
        pc.setDimensions(50, 50, cardsW, cardsH);
        pc.setData(info);
        pc.setCards(img1);
    }
    public void draw(){
        background(255);
        pc.display(this);
    }
}





