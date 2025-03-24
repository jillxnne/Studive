package StudiveAppGUI;
import processing.core.PApplet;
import static java.lang.Math.min;
import static processing.core.PApplet.constrain;
import static processing.core.PConstants.BACKSPACE;
import static StudiveAppFonts.Sizes.*;

public class TextArea {

    int x, y, h, w;
    int numCols, numRows;
    int bgColor, fgColor, selectedColor, borderColor;
    int borderWeight = 1;
    String text = "";
    String[] lines;
    float sizeText = SubtitleSize;
    boolean selected = false;

    public TextArea(PApplet p5, int x, int y, int w, int h, int nc, int nr) {
        this.x = x; this.y = y; this.w = w; this.h = h;
        this.numCols = nc; this.numRows = nr;
        this.lines = new String[nr];

        this.bgColor = p5.color(140, 140, 140);
        this.fgColor = p5.color(0, 0, 0);
        this.selectedColor = p5.color(190, 190, 60);
        this.borderColor = p5.color(30, 30, 30);
    }

    public String getText(){ return this.text; }
    public void display(PApplet p5) {
        p5.pushStyle();
        if (selected) {
            p5.fill(selectedColor);
        } else {
            p5.fill(bgColor);
        }

        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(sizeText);
        for(int i=0; i<lines.length; i++){
            if(lines[i]!=null){
                p5.text(lines[i], x + 5, y + (i+1)* sizeText);
            }
        }
        p5.popStyle();
    }

    public void updateLines(){
        if(text.length()>0){
            int numLines = constrain(text.length() / numCols, 0, this.numRows-1);
            for(int i=0; i<=numLines; i++){
                int start = i*numCols;
                int end = min(text.length(), (i+1)*numCols);
                lines[i] = text.substring(start, end);
            }
        }
        else {
            for(int i=0; i<lines.length; i++){
                lines[i] ="";
            }
        }
    }
    public void keyPressed(char key, int keyCode) {
        if (selected) {
            if (keyCode == (int)BACKSPACE) {
                removeText();
            } else if (keyCode == 32) {
                addText(' '); // SPACE
            } else {
                addText(key);
            }
        }
    }
    public void addText(char c) {
        if (this.text.length() < this.numCols*this.numRows) {
            this.text += c;
        }
        updateLines();
    }
    public void removeText() {
        if (text.length()> 0) {
            text = text.substring(0, text.length()-1);
        }
        updateLines();
    }
    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }
    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            selected = true;
        } else {
            selected = false;
        }
    }
}