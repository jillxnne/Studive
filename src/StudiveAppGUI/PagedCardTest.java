package StudiveAppGUI;
import StudiveAppGUI.PagedCard;
import processing.core.PApplet;
import processing.core.PImage;
public class PagedCardTest extends PApplet {
    PagedCard pc;
    float cardsW = 800, cardsH = 700;

    // Número de cards per pàgina
    int numCardsPage = 4;

    String[][] info = {
            {"Títol 1", "Lloc 1", "Data 1", "Secció 1", "Descripció 1"},
            {"Títol 2", "Lloc 2", "Data 2", "Secció 2", "Descripció 2"},
            {"Títol 3", "Lloc 3", "Data 3", "Secció 1", "Descripció 3"},
            {"Títol 4", "Lloc 4", "Data 4", "Secció 1", "Descripció 4"},
            {"Títol 5", "Lloc 5", "Data 5", "Secció 2", "Descripció 5"},
            {"Títol 6", "Lloc 6", "Data 6", "Secció 2", "Descripció 6"},
            {"Títol 7", "Lloc 7", "Data 7", "Secció 1", "Descripció 7"},
            {"Títol 8", "Lloc 8", "Data 8", "Secció 8", "Descripció 8"},
            {"Títol 9", "Lloc 9", "Data 9", "Secció 9", "Descripció 9"},
            {"Títol 0", "Lloc 0", "Data 0", "Secció 0", "Descripció 0"},
    };

    PImage img1;

    public static void main(String[] args) {
        PApplet.main("StudiveAppGUI.PagedCardTest", args);
    }

    public void settings(){
        size(1200, 800);     // Dimensions de la Pantalla
        smooth(10);
    }

    public void setup(){
        // Imatges de les Categories
        img1 = loadImage("folder.png");

        // Creació de la taula
        pc = new PagedCard(numCardsPage);
        pc.setDimensions(50, 50, cardsW, cardsH);
        pc.setData(info);
        pc.setCards();
        pc.setImages(img1);

    }

    public void draw(){

        background(255);
        pc.display(this);

    }

    public void mousePressed(){
            pc.checkCardSelection(this);
    }

}
