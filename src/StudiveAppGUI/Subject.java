package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;

public class Subject {
    String title;  // Solo mantenemos el título
    PImage img;    // Imagen para la tarjeta

    // Dimensiones de la tarjeta
    float x, y, w, h, b;

    // Constructor
    public Subject(String title) {
        this.title = title;
    }

    public void setDimensions(float x, float y, float w, float h, float b) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.b = b;
    }

    // Método para asignar la imagen a la tarjeta
    public void setImage(PImage img) {
        this.img = img;
    }

    // Método para mostrar la tarjeta
    public void display(PApplet p5) {
        p5.pushStyle();

        // Asignar color dependiendo del título
        if (title.equals("info")) {
            p5.fill(0, 255, 0);  // Verde para "info"
        } else if (title.equals("mates")) {
            p5.fill(0, 0, 255);  // Azul para "mates"
        } else if (title.equals("historia")) {
            p5.fill(255, 0, 0);  // Rojo para "historia"
        } else {
            p5.fill(220);  // Color por defecto (gris)
        }

        // Dibuja la tarjeta con el fondo colorido
        p5.rect(x, y, w, h, b / 2);

        if (img != null) {
            float imgW = w / 2; // La imagen ocupa la mitad del ancho de la tarjeta
            float imgH = h - 2 * b; // La imagen ocupa la mitad de la altura de la tarjeta
            float imgX = x + (w - imgW) / 2; // Centrar la imagen en el eje X
            float imgY = y + (h - imgH) / 2; // Centrar la imagen en el eje Y
            p5.image(img, imgX, imgY, imgW, imgH); // Dibuja la imagen centrada

            // Muestra el título centrado sobre la imagen
            p5.fill(255);  // Blanco para el texto (para que destaque sobre la imagen)
            p5.textSize(48); // Título más grande
            p5.textAlign(p5.CENTER, p5.CENTER); // Centrar el texto en X e Y
            // El texto se centra en la imagen, ajustando las coordenadas para estar sobre ella
            p5.text(title, imgX + imgW / 2, imgY + imgH / 2); // Centrado sobre la imagen
        }

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}
