package StudiveAppLayout;

import processing.core.PApplet;

import static StudiveAppLayout.Layout.*;

public class Studive extends PApplet {

    public static void main(String[] args) {
        PApplet.main("StudiveAppLayout.Studive", args);
    }

    public void settings() {
        //fullScreen();                       // Pantalla completa
        size(1920, 1080);        // Pantalla HD
        smooth(10);
    }

    public void setup() {
        noStroke();                         // Sense bordes
        textAlign(CENTER);
        textSize(18);   // Alineació i mida del text
    }

    public void draw() {
        background(0);

        // Main zone
        fill(102,84,94);
        rect(marginH, marginV, width - marginH * 2, height - marginV * 2);

        // Logo
        fill(163,145,147);
        rect(marginH, marginV, logoWidth, logoHeight);
        fill(0);
        text("LOGO", marginH + logoWidth / 2, marginV + logoHeight / 2);

        // Sidebar
        fill(170,111,115);
        rect(marginH, 2 * marginV + logoHeight, sidebarWidth, sidebarHeight);
        fill(0);
        text("SIDEBAR", marginH + sidebarWidth / 2, marginV + logoHeight + sidebarHeight / 2);

        // Banner
        fill(238,169,144);
        rect(2 * marginH + logoWidth, marginV, bannerWidth, bannerHeight);
        fill(0);
        text("BANNER", marginH + logoWidth + bannerWidth / 2, marginV + bannerHeight / 2);


        // Columns
        fill(246,224,181);
        rect(2 * marginH + sidebarWidth, 2 * marginV + bannerHeight, columnWidth, columnHeight);
        fill(0);
        text("COLUMN 1", 2 * marginH + sidebarWidth + columnWidth / 2, 2 * marginV + bannerHeight + columnHeight / 2);

        fill(246,224,181);
        rect(3 * marginH + sidebarWidth + columnWidth, 2 * marginV + bannerHeight, columnWidth, columnHeight);
        fill(0);
        text("COLUMN 2", 3 * marginH + sidebarWidth + columnWidth + columnWidth / 2, 2 * marginV + bannerHeight + columnHeight / 2);

        fill(246,224,181);
        rect(4 * marginH + sidebarWidth + 2 * columnWidth, 2 * marginV + bannerHeight, columnWidth, columnHeight);
        fill(0);
        text("COLUMN 3", 4 * marginH + sidebarWidth + 2 * columnWidth + columnWidth / 2, 2 * marginV + bannerHeight + columnHeight / 2);

    }
}
