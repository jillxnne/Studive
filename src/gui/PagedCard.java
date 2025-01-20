package gui;
import processing.core.PApplet;
import processing.core.PImage;
public class PagedCard {
    String[][] cardsData;
    Card[] cards;
    int numCardsPage;
    int numPage;
    int numTotalPages;
    float x, y, w, h;

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

    public void setCards(PImage img) {
        cards = new Card[this.cardsData.length];
        for (int np = 0; np <= numTotalPages; np++) {
            int firstCardPage = numCardsPage * np;
            int lastCardPage = numCardsPage * (np + 1) - 1;
            float hCard = h / (float) numCardsPage;
            float yCard = y;
            float b = 10;
            for (int i = firstCardPage; i <= lastCardPage; i++) {
                if (i < cards.length) {
                    // Crear la tarjeta con el título
                    cards[i] = new Card(cardsData[i][0]);
                    cards[i].setDimensions(x, yCard, w, hCard, b);

                    // Asignar la misma imagen a todas las tarjetas
                    cards[i].setImage(img);

                    yCard += hCard + b;
                }
            }
        }
    }

    public void nextPage() {
        if (this.numPage < this.numTotalPages) {
            this.numPage++;
        }
    }

    public void prevPage() {
        if (this.numPage > 0) {
            this.numPage--;
        }
    }

    public void display(PApplet p5) {
        p5.pushStyle();
        int firstCardPage = numCardsPage * numPage;
        int lastCardPage = numCardsPage * (numPage + 1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i < cards.length && cards[i] != null) {
                cards[i].display(p5);
            }
        }
        p5.fill(0);
        p5.text("Pag: " + (this.numPage + 1) + " / " + (this.numTotalPages + 1), x + w + 50, y + 10);
        p5.popStyle();
    }
}
