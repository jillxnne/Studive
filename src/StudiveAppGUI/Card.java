package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase que representa una tarjeta (Card) que contiene información visual y textual.
 * La tarjeta puede contener una imagen, un título, una sección y una descripción.
 * Además, es interactiva, permitiendo detectar si el mouse está sobre ella.
 */
public class Card {

    /** Imagen asociada a la tarjeta */
    PImage img;

    /** Título de la tarjeta */
    String title;

    /** Sección o tipo de documento asociado a la tarjeta */
    String section;

    /** Descripción breve sobre el contenido de la tarjeta */
    String description;

    /** Coordenada X de la posición de la tarjeta */
    float x;

    /** Coordenada Y de la posición de la tarjeta */
    float y;

    /** Ancho de la tarjeta */
    float w;

    /** Alto de la tarjeta */
    float h;

    /** Radio de las esquinas redondeadas de la tarjeta */
    float b;

    /**
     * Constructor que inicializa una tarjeta con la información proporcionada.
     *
     * @param info Arreglo de Strings donde el primer elemento es el título, el segundo la sección,
     *             y el tercero la descripción de la tarjeta.
     */
    public Card(String[] info){
        this.title = info[0];
        this.section = info[1];
        this.description = info[2];
    }

    /**
     * Establece las dimensiones y la posición de la tarjeta.
     *
     * @param x Coordenada X de la tarjeta.
     * @param y Coordenada Y de la tarjeta.
     * @param w Ancho de la tarjeta.
     * @param h Alto de la tarjeta.
     * @param b Radio de las esquinas redondeadas de la tarjeta.
     */
    public void setDimensions(float x, float y, float w, float h, float b){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.b = b;
    }

    /**
     * Establece la imagen de la tarjeta.
     *
     * @param img Imagen que se desea mostrar en la tarjeta.
     */
    public void setImage(PImage img){
        this.img = img;
    }

    /**
     * Dibuja la tarjeta en la pantalla, incluyendo la imagen, el título, la sección y la descripción.
     * Si el mouse está sobre la tarjeta, cambia su color de fondo.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
    public void display(PApplet p5){

        p5.pushStyle();

        // Cambia el color de la tarjeta si el mouse está sobre ella
        if(this.mouseOver(p5)){
            p5.fill(200);  // Color de fondo cuando el mouse está sobre la tarjeta
        }
        else {
            p5.fill(220);  // Color de fondo por defecto
        }

        p5.rect(x, y, w, h, b/2);  // Dibuja el contorno de la tarjeta con esquinas redondeadas

        // Dibuja la imagen si está presente
        float imgW = (w/3) - 2*b;
        float imgH = h - 2*b;
        if(img != null){
            p5.image(img, x + b, y + b, imgW, imgH);  // Dibuja la imagen en la tarjeta
            p5.noFill();
        }
        else {
            p5.fill(50);  // Si no hay imagen, utiliza un color gris.
        }

        // Dibuja el título en la parte superior derecha de la tarjeta
        p5.fill(0);
        p5.textSize(24);
        p5.textAlign(p5.CENTER);
        p5.text(title, x + 2 * w / 3, y + h / 5);

        // Dibuja la sección o tipo de documento debajo del título
        p5.fill(0);
        p5.textSize(18);
        p5.textAlign(p5.CENTER);
        p5.text("Tipo de documento: " + section, x + 2 * w / 3, y + h / 3);

        // Dibuja la descripción de la tarjeta en la parte inferior
        p5.fill(0);
        p5.textSize(14);
        p5.textAlign(p5.LEFT);
        p5.text(description, x + w / 3 + b, y + 2 * h / 3 - b, 2 * w / 3 - b * 2, h / 4);

        p5.popStyle();
    }

    /**
     * Método que verifica si el mouse está sobre la tarjeta.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @return true si el mouse está sobre la tarjeta, false en caso contrario.
     */
    public boolean mouseOver(PApplet p5){
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}
