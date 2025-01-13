package StudiveAppGUI;
import processing.core.PApplet;
import processing.core.PImage;

public class PagedCard {
    String[][] cardsData;    // Dades de les Cards
    Card[] cards;            // Cards
    int numCards;            // Número total de Cards
    int numCardsPage;        // Número de Cards en 1 Pàgina
    int numPage;
    int numTotalPages;
    float x, y, w, h;
    int selectedCard = -1;
    public PagedCard(int ncp) {
        this.numCardsPage = ncp;
        this.numPage = 0;
    }
    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void setData(String[][] d) {
        this.cardsData = d;
        this.numTotalPages = d.length / this.numCardsPage;
    }
    public void setCards() {
        cards = new Card[this.cardsData.length];
        for (int np=0; np<=numTotalPages; np++) {
            int firstCardPage = numCardsPage*np;
            int lastCardPage  = numCardsPage*(np+1) - 1;
            float hCard = h / (float) numCardsPage;
            float yCard = y;
            float b = 10;

            for (int i = firstCardPage; i <= lastCardPage; i++) {
                if (i<cards.length) {
                    cards[i] = new Card(cardsData[i]);
                    cards[i].setDimensions(x, yCard, w, hCard, b);
                    yCard += hCard + b;
                }
            }
        }
    }
    public void setImages(PImage img1) {
        PImage img = null;
        for (int i=0; i<cards.length; i++) {
            if (cards[i]!=null) {
                if (cards[i].section=="Secció 1") {
                    img = img1;
                }
                cards[i].setImage(img);
            }
        }
    }
    public void nextPage() {
        if (this.numPage<this.numTotalPages) {
            this.numPage++;
        }
    }
    public void prevPage() {
        if (this.numPage>0) {
            this.numPage--;
        }
    }
    public void display(PApplet p5) {
        p5.pushStyle();
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null) {
                cards[i].display(p5);
            }
        }
        p5.popStyle();
    }
    public void checkCardSelection(PApplet p5){
        boolean selected = false;
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null && cards[i].mouseOver(p5)) {
                selectedCard = i;
                selected = true;
                break;
            }
        }
        if(!selected){
            selectedCard = -1;
        }
    }
    public boolean checkMouseOver(PApplet p5){
        int firstCardPage = numCardsPage*numPage;
        int lastCardPage  = numCardsPage*(numPage+1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i<cards.length && cards[i]!=null && cards[i].mouseOver(p5)) {
                return true;
            }
        }
        return false;
    }
}