package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase que representa un botón con imagen que cambia de estado al pasar el mouse sobre él.
 * Este botón tiene dos imágenes: una normal y otra que se muestra cuando el mouse pasa por encima.
 */
public class ImageButtons {

    /** Coordenada X de la posición del botón */
    float x, y;

    /** Radio del botón */
    float r;

    /** Imagen mostrada cuando el botón no está seleccionado (estado normal) */
    PImage imgNormal;

    /** Imagen mostrada cuando el mouse está sobre el botón (estado hover) */
    PImage imgHover;

    /**
     * Constructor que inicializa un botón con las imágenes y posición especificadas.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @param imgNormal Imagen para el estado normal del botón.
     * @param imgHover Imagen para el estado hover del botón (cuando el mouse está sobre él).
     * @param x Coordenada X de la posición del botón.
     * @param y Coordenada Y de la posición del botón.
     * @param r Radio del botón (tamaño).
     */
    public ImageButtons(PApplet p5, PImage imgNormal, PImage imgHover, float x, float y, float r){
        this.imgNormal = imgNormal;
        this.imgHover = imgHover;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    /**
     * Dibuja el botón en la pantalla. Cambia la imagen según el estado (normal o hover).
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
    public void display(PApplet p5){
        p5.pushStyle();

        // Cambia la imagen si el mouse está sobre el botón
        if(mouseOverButton(p5)){
            p5.imageMode(p5.CENTER);
            p5.image(this.imgHover, this.x, this.y, 2*this.r, 2*this.r);  // Imagen en estado hover
        }
        else {
            p5.imageMode(p5.CENTER);
            p5.image(this.imgNormal, this.x, this.y, 2*this.r, 2*this.r);  // Imagen en estado normal
        }

        p5.popStyle();
    }

    /**
     * Método que verifica si el mouse está sobre el botón.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @return true si el mouse está dentro del área del botón, false si no lo está.
     */
    public boolean mouseOverButton(PApplet p5){
        return p5.dist(p5.mouseX, p5.mouseY, this.x, this.y) <= this.r;  // Verifica si el mouse está dentro del radio del botón
    }
}
