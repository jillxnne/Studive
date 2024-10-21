package StudiveAppScreens;
import processing.core.PApplet;

import static StudiveAppLayout.Layout.*;

public class Screens {

    // Enumerat de les Pantalles de l'App
    public enum PANTALLA {INICIAL, DETALLS, ABOUT};

    // Pantalla Actual
    public PANTALLA pantallaActual;

    // Constructor de la GUI
    public Screens(){
        pantallaActual = PANTALLA.INICIAL;
    }


    // PANTALLES DE LA GUI

    public void dibuixaPantallaInicial(PApplet p5){

        p5.background(0);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        dibuixaBanner(p5);
        dibuixaColumnes123(p5);
    }

    public void dibuixaPantallaAbout(PApplet p5){
        p5.background(102,84,94);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        dibuixaBanner(p5);
        dibuixaColumna1(p5);
    }

    public void dibuixaPantallaDetalls(PApplet p5){
        p5.background(163,145,147);
        dibuixaLogo(p5);
        dibuixaSideBar(p5);
        dibuixaBanner(p5);
        dibuixaColumnes12(p5);
    }


    // ZONES DE LA GUI

    public void dibuixaLogo(PApplet p5){
        p5.fill(170,111,115);
        p5.rect(marginH, marginV, logoWidth, logoHeight);
        p5.fill(0);
        p5.text("LOGO", marginH + logoWidth/2, marginV + logoHeight/2);
    }

    public void dibuixaSideBar(PApplet p5){
        // Zona Sidebar ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(163,145,147);
        p5.rect(marginH, 2*marginV + logoHeight, sidebarWidth, sidebarHeight);
        p5.fill(0);
        p5.text("SIDEBAR", marginH + sidebarWidth/2, marginV + logoHeight + sidebarHeight/2);
    }

    public void dibuixaBanner(PApplet p5){
        p5.fill(238,169,144);
        p5.rect(2*marginH + logoWidth, marginV, bannerWidth, bannerHeight);
        p5.fill(0);
        p5.text("PANTALLA " +  pantallaActual + "("+pantallaActual.ordinal() +")", marginH + logoWidth + bannerWidth/2, marginV + bannerHeight/2);
    }

    public void dibuixaColumna1(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(200, 200, 50);
        p5.rect(2*marginH + sidebarWidth, 2*marginV + bannerHeight, 3*columnWidth + 2*marginH, columnHeight);
        p5.fill(0);
        p5.text("COLUMN 1", 2*marginH + sidebarWidth + 3*columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);
    }

    public void dibuixaColumnes12(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(200, 200, 50);
        p5.rect(2*marginH + sidebarWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0);
        p5.text("COLUMN 1", 2*marginH + sidebarWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);

        p5.fill(200, 200, 50);
        p5.rect(3*marginH + sidebarWidth + columnWidth, 2*marginV + bannerHeight, 2*columnWidth + marginH, columnHeight);
        p5.fill(0);
        p5.text("COLUMN 2", 3*marginH + sidebarWidth + 2*columnWidth, 2*marginV + bannerHeight + columnHeight/2);
    }

    public void dibuixaColumnes123(PApplet p5){
        // Zona Columnes 1, 2 i 3 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        p5.fill(246,224,181);
        p5.rect(2*marginH + sidebarWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0);
        p5.text("COLUMN 1", 2*marginH + sidebarWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);

        p5.fill(246,224,181);
        p5.rect(3*marginH + sidebarWidth + columnWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0);
        p5.text("COLUMN 2", 3*marginH + sidebarWidth + columnWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);

        p5.fill(246,224,181);
        p5.rect(4*marginH + sidebarWidth + 2*columnWidth, 2*marginV + bannerHeight, columnWidth, columnHeight);
        p5.fill(0);
        p5.text("COLUMN 3", 4*marginH + sidebarWidth + 2*columnWidth +columnWidth/2, 2*marginV + bannerHeight + columnHeight/2);
    }
}