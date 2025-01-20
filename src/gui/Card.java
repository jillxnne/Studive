package gui;
import processing.core.PApplet;
import processing.core.PImage;
public class Card {
    String title;  // Solo mantenemos el título
    PImage img;    // Imagen para la tarjeta

    // Dimensiones de la tarjeta
    float x, y, w, h, b;

    // Constructor
    public Card(String title) {
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

        // Muestra la imagen en la tarjeta
        if (img != null) {
            float imgW = w / 3; // La imagen ocupa un tercio de la tarjeta
            float imgH = h - 2 * b; // La imagen ocupa toda la altura, menos los bordes
            p5.image(img, x + b, y + b, imgW, imgH);
        }


        // Títol: solo mostramos el título de la tarjeta (info, mates, historia)
        p5.fill(0);
        p5.textSize(24);
        p5.textAlign(p5.CENTER);
        p5.text(title, x + w / 2, y + h / 2);

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}

