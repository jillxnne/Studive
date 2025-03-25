package StudiveAppColors;

import processing.core.PApplet;

public class Colors {
    String colorname = " ";
    int[] colors;
    int selectedColor = -1;  // Variable para almacenar el color seleccionado

    public Colors(PApplet p5) {
        this.setColors(p5);
    }

    void setColors(PApplet p5) {
        this.colors = new int[5];
        this.colors[0] = p5.color(102, 84, 94); // one
        this.colors[1] = p5.color(163, 145, 147); // two
        this.colors[2] = p5.color(170, 111, 115); // three
        this.colors[3] = p5.color(238, 169, 144); // four
        this.colors[4] = p5.color(246, 224, 181); // five

    }

    int getNumColors() {
        return this.colors.length;
    }

    int getColorAt(int i) {
        return this.colors[i];
    }

    public void displayColors(PApplet p5, float x, float y, float w) {
        p5.pushStyle();
        p5.fill(0);
        p5.textAlign(p5.LEFT);
        p5.textSize(36);

        float wc = w / getNumColors();
        for (int i = 0; i < getNumColors(); i++) {
            float cx = x + i * wc + wc / 2;  // Coordenada x del centro del círculo
            float cy = y + wc / 2;  // Coordenada y del centro del círculo
            float radius = wc / 2;  // Radio del círculo

            if (i == selectedColor) {
                p5.stroke(0);
                p5.strokeWeight(5);
            } else {
                p5.noStroke();
                p5.fill(getColorAt(i));
            }

            p5.ellipse(cx, cy, radius * 2, radius * 2);
        }
        p5.popStyle();
    }

    public void checkMouseOver(PApplet p5, float x, float y, float w) {
        float wc = w / getNumColors();
        for (int i = 0; i < getNumColors(); i++) {
            float cx = x + i * wc + wc / 2;
            float cy = y + wc / 2;
            float radius = wc / 2;

            if (p5.dist(p5.mouseX, p5.mouseY, cx, cy) < radius) {
                if (p5.mousePressed) {
                    selectedColor = i;
                }
                break;
            }
        }
    }
    public String getSelectedColorAsString(PApplet p5) {
        switch (selectedColor) {
            case -1:
                colorname = "";
                break;
            case 1:
                colorname = "one";
                break;
            case 2:
                colorname = "two";
                break;
            case 3:
                colorname = "three";
                break;
            case 4:
                colorname = "four";
                break;
            case 5:
                colorname = "five";
                break;
        }
        return colorname;
    }

}
