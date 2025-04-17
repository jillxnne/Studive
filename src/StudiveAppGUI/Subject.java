package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase que representa una asignatura. Cada asignatura tiene un título, un color,
 * una imagen y una posición en la pantalla.
 */
public class Subject {

    /** Título de la asignatura */
    String title;

    /** Color de la asignatura, usado para personalizar su apariencia */
    String color;

    /** Imagen de la asignatura (opcional) */
    PImage img;

    /** Coordenadas y dimensiones para la visualización en la pantalla */
    float x, y, w, h, b;

    /**
     * Constructor que inicializa el título y color de la asignatura.
     *
     * @param title Título de la asignatura.
     * @param color Color asignado a la asignatura.
     */
    public Subject(String title, String color) {
        this.title = title;
        this.color = color;
    }

    /**
     * Establece las dimensiones y la posición de la asignatura en la pantalla.
     *
     * @param x Coordenada X de la asignatura.
     * @param y Coordenada Y de la asignatura.
     * @param w Ancho de la asignatura.
     * @param h Alto de la asignatura.
     * @param b Espaciado alrededor de la asignatura.
     */
    public void setDimensions(float x, float y, float w, float h, float b) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.b = b;
    }

    /**
     * Establece la imagen que representa a la asignatura.
     *
     * @param img Imagen que se asignará a la asignatura.
     */
    public void setImage(PImage img) {
        this.img = img;
    }

    /**
     * Dibuja la asignatura en la pantalla, mostrando su color y su imagen (si está presente),
     * así como el título de la asignatura.
     *
     * @param p5 Objeto de la clase PApplet para interactuar con Processing.
     */
    public void display(PApplet p5) {
        p5.pushStyle();

        // Establece el color de fondo basado en el valor de "color"
        switch (color) {
            case "one":
                p5.fill(102, 84, 94); // Color 1
                break;
            case "two":
                p5.fill(163, 145, 147); // Color 2
                break;
            case "three":
                p5.fill(170, 111, 115); // Color 3
                break;
            case "four":
                p5.fill(238, 169, 144); // Color 4
                break;
            case "five":
                p5.fill(246, 224, 181); // Color 5
                break;
            case " ":
                p5.fill(220); // Color predeterminado
                break;
        }

        // Dibuja el rectángulo de la asignatura
        p5.rect(x, y, w, h, b / 2);

        // Si hay imagen, dibújala
        if (img != null) {
            float imgW = w / 2;
            float imgH = h - 2 * b;
            float imgX = x + (w - imgW) / 2;
            float imgY = y + (h - imgH) / 2;
            p5.image(img, imgX, imgY, imgW, imgH);

            // Dibuja el título en el centro de la imagen
            p5.fill(0);
            p5.textSize(48);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(title, imgX + imgW / 2, imgY + imgH / 2);
        }

        p5.popStyle();
    }

    /**
     * Verifica si el mouse está sobre la asignatura.
     *
     * @param p5 Objeto de la clase PApplet para interactuar con Processing.
     * @return true si el mouse está sobre la asignatura, false en caso contrario.
     */
    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}
