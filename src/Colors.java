import processing.core.PApplet;
public class Colors {
    int[] colors;

    public Colors(PApplet p5){
        this.setColors(p5);
    }
    void setColors(PApplet p5){
        this.colors = new int[5];
        this.colors[0] = p5.color(102,84,94);
        this.colors[1] = p5.color(163,145,147);
        this.colors[2] = p5.color(170,111,115);
        this.colors[3] = p5.color(238,169,144);
        this.colors[4] = p5.color(246,224,181);
    }

    // Getter del número de colors
    int getNumColors(){
        return this.colors.length;
    }

    // Getter del color primari
    int getFirstColor(){
        return  this.colors[0];
    }

    // Getter del color secundari
    int getSecondColor(){
        return  this.colors[1];
    }

    // Getter del color terciari
    int getThirdColor(){
        return  this.colors[2];
    }

    // Getter del color i-èssim
    int getColorAt(int i){
        return this.colors[i];
    }


    // Dibuixa paleta de colors
    void displayColors(PApplet p5, float x, float y, float w){
        p5.pushStyle();
        //Llegenda
        p5.fill(0); p5.textAlign(p5.LEFT); p5.textSize(36);
        p5.text("Colors:", x, y-10);

        // Paleta de colors
        float wc = w / getNumColors();
        for(int i=0; i<getNumColors(); i++){
            p5.fill(getColorAt(i)); p5.stroke(0); p5.strokeWeight(3);
            p5.rect(x + i*wc, y, wc, wc);
        }
        p5.popStyle();
    }
}
