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

    public void setImage(PImage img) {
        this.img = img;
    }

    public void display(PApplet p5) {
        p5.pushStyle();

        if (title.equals("info")) {
            p5.fill(102, 84, 94);
        } else if (title.equals("mates")) {
            p5.fill(238, 169, 144);
        } else if (title.equals("historia")) {
            p5.fill(170, 111, 115);
        } else {
            p5.fill(220);
        }

        p5.rect(x, y, w, h, b / 2);

        if (img != null) {
            float imgW = w / 2;
            float imgH = h - 2 * b;
            float imgX = x + (w - imgW) / 2;
            float imgY = y + (h - imgH) / 2;
            p5.image(img, imgX, imgY, imgW, imgH);

            p5.fill(0);
            p5.textSize(48);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(title, imgX + imgW / 2, imgY + imgH / 2);
        }

        p5.popStyle();
    }

    public boolean mouseOver(PApplet p5) {
        return this.x < p5.mouseX && p5.mouseX < this.x + this.w &&
                this.y < p5.mouseY && p5.mouseY < this.y + this.h;
    }
}
