package StudiveAppQuestionaire;
import processing.core.PApplet;

public class Button {
    float x, y, w, h;
    String label;

    public Button(float x, float y, float w, float h, String label) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.label = label;
    }

    public void display(PApplet app) {
        app.fill(100);
        app.rect(x, y, w, h, 10);
        app.fill(255);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(16);
        app.text(label, x + w / 2, y + h / 2);
    }

    boolean isMouseOver(PApplet app) {
        return app.mouseX > x && app.mouseX < x + w && app.mouseY > y && app.mouseY < y + h;
    }

    public boolean checkClick(PApplet app) {
        return isMouseOver(app) && app.mousePressed;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}