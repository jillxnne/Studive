package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase que representa un conjunto de tarjetas paginadas que pueden ser visualizadas y seleccionadas.
 * Esta clase permite mostrar un número limitado de tarjetas por página y navegar entre páginas.
 */
public class PagedCard {

    /** Datos de las tarjetas (título, sección, descripción) */
    String[][] cardsData;

    /** Arreglo de objetos de la clase Card que representan las tarjetas */
    Card[] cards;

    /** Número de tarjetas por página */
    int numCardsPage;

    /** Número de la página actual */
    int numPage;

    /** Número total de páginas */
    int numTotalPages;

    /** Coordenadas y dimensiones para posicionar las tarjetas en la pantalla */
    float x, y, w, h;

    /** Índice de la tarjeta seleccionada (-1 si ninguna está seleccionada) */
    int selectedCard = -1;

    /**
     * Constructor que inicializa la paginación de tarjetas.
     *
     * @param ncp Número de tarjetas por página.
     */
    public PagedCard(int ncp) {
        this.numCardsPage = ncp;
        this.numPage = 0;
    }

    /**
     * Establece las dimensiones de la zona donde se ubicarán las tarjetas.
     *
     * @param x Coordenada X de la zona donde se ubicarán las tarjetas.
     * @param y Coordenada Y de la zona donde se ubicarán las tarjetas.
     * @param w Ancho de la zona de las tarjetas.
     * @param h Alto de la zona de las tarjetas.
     */
    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Establece los datos de las tarjetas.
     *
     * @param d Datos de las tarjetas (título, sección, descripción).
     */
    public void setData(String[][] d) {
        this.cardsData = d;
        this.numTotalPages = d.length / this.numCardsPage;
    }

    /**
     * Crea y organiza las tarjetas en función de los datos y las dimensiones establecidas.
     */
    public void setCards() {
        cards = new Card[this.cardsData.length];
        for (int np = 0; np <= numTotalPages; np++) {
            int firstCardPage = numCardsPage * np;
            int lastCardPage = numCardsPage * (np + 1) - 1;
            float hCard = h / (float) numCardsPage;
            float yCard = y;
            float b = 10;
            for (int i = firstCardPage; i <= lastCardPage; i++) {
                if (i < cards.length) {
                    cards[i] = new Card(cardsData[i]);
                    cards[i].setDimensions(x, yCard, w, hCard, b);
                    yCard += hCard + b;
                }
            }
        }
    }

    /**
     * Asigna imágenes a las tarjetas que pertenecen a la sección "FLASHCARD".
     *
     * @param imgCard Imagen que se asignará a las tarjetas de tipo "FLASHCARD".
     */
    public void setImages(PImage imgCard) {
        PImage img = null;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                if (cards[i].section.equals("FLASHCARD")) {
                    img = imgCard;
                    cards[i].setImage(img); // Asignar la imagen al card
                }
            }
        }
    }

    /**
     * Cambia a la siguiente página de tarjetas.
     */
    public void nextPage() {
        if (this.numPage < this.numTotalPages) {
            this.numPage++;
        }
    }

    /**
     * Vuelve a la página anterior de tarjetas.
     */
    public void prevPage() {
        if (this.numPage > 0) {
            this.numPage--;
        }
    }

    /**
     * Muestra las tarjetas de la página actual.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
    public void display(PApplet p5) {
        p5.pushStyle();
        int firstCardPage = numCardsPage * numPage;
        int lastCardPage = numCardsPage * (numPage + 1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i < cards.length && cards[i] != null) {
                cards[i].display(p5);
            }
        }
        p5.popStyle();
    }

    /**
     * Verifica si el mouse está sobre alguna tarjeta y, en caso afirmativo, la selecciona.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
    public void checkCardSelection(PApplet p5) {
        boolean selected = false;
        int firstCardPage = numCardsPage * numPage;
        int lastCardPage = numCardsPage * (numPage + 1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i < cards.length && cards[i] != null && cards[i].mouseOver(p5)) {
                selectedCard = i;
                selected = true;
                break;
            }
        }
        if (!selected) {
            selectedCard = -1;
        }
    }

    /**
     * Devuelve el título de la tarjeta seleccionada.
     *
     * @return El título de la tarjeta seleccionada, o null si ninguna tarjeta está seleccionada.
     */
    public String getSelectedCardTitle() {
        if (selectedCard >= 0 && selectedCard < cards.length) {
            if (cards[selectedCard].title != null) {
                return cards[selectedCard].title;
            }
        }
        return null;
    }

    /**
     * Verifica si el mouse está sobre alguna tarjeta de la página actual.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @return true si el mouse está sobre alguna tarjeta, false en caso contrario.
     */
    public boolean checkMouseOver(PApplet p5) {
        int firstCardPage = numCardsPage * numPage;
        int lastCardPage = numCardsPage * (numPage + 1) - 1;
        for (int i = firstCardPage; i <= lastCardPage; i++) {
            if (i < cards.length && cards[i] != null && cards[i].mouseOver(p5)) {
                return true;
            }
        }
        return false;
    }
}
