package StudiveAppScreens;
import StudiveAppFonts.Fonts;
import StudiveAppGUI.Checkbox;
import StudiveAppGUI.PagedCard;
import processing.core.PApplet;
import static StudiveAppLayout.Layout.*;
import static StudiveAppFonts.Fonts.*;
import static StudiveAppFonts.Sizes.*;
import StudiveAppGUI.ImageButtons;
import StudiveAppGUI.TextArea;
import processing.core.PApplet;
import processing.core.PImage;
import static StudiveAppGUI.PagedCard.*;
import javax.swing.*;

public class GUI extends PApplet {
    public enum SCREENS {HOMEPAGE, GENERALLESSONS, MAINLESSONS, GENERALSTATISTICS, MAINSTATISTICS};
    public SCREENS Default;
    ImageButtons home, foldermainbar, plus, statistic, mainfolder;
    PImage homeIcon,homepressedIcon, homefolderIcon, homefolderpressedIcon, plusIcon,
            pluspressedIcon, statisticIcon, statisticpressedIcon, mainfolderIcon;
    TextArea Lection, Results, GenRecomendations, Errors, Recomendation;
    Checkbox c1, c2;
    PagedCard mainPageCard, mainPageLection;

    public GUI(PApplet p5){
       Default  = SCREENS.HOMEPAGE;

       this.setImage(p5);
       setButtons(p5);
       setTextFields(p5);
       setCheckBoxs(p5);
    }

    public void setPagedCards(PApplet p5){
        mainPageCard = new PagedCard();
        mainPageLection = new PagedCard();
    }

    public void setCheckBoxs(PApplet p5){
        c1 = new Checkbox(p5, 30,30,30);
        c2 = new Checkbox(p5, 20,20,20);
    }

    public void setTextFields(PApplet p5){
        Lection = new TextArea(p5, (int)PanelBoardwidth+313, (int)PanelBoardheight-320, 500,290, 45,13);
    }
    public void setButtons(PApplet p5){
        home = new ImageButtons(p5, homeIcon,homepressedIcon, 650,990,30);
        foldermainbar = new ImageButtons(p5, homefolderIcon,homefolderpressedIcon,850, 990,30);
        plus = new ImageButtons(p5,plusIcon, pluspressedIcon,1050,990,30);
        statistic = new ImageButtons(p5,statisticIcon, statisticpressedIcon,1250,990,30);
    }

    public void setImage(PApplet p5){
        homeIcon = p5.loadImage("data/home.png");
        homepressedIcon = p5.loadImage("data/homepressed.png");
        homefolderIcon = p5.loadImage("data/folder.png");
        homefolderpressedIcon = p5.loadImage("data/folderpressed.png");
        plusIcon = p5.loadImage("data/pluspressed.png");
        pluspressedIcon = p5.loadImage("data/plus.png");
        statisticIcon = p5.loadImage("data/statistics.png");
        statisticpressedIcon = p5.loadImage("data/statisticspressed.png");
    }

    public void drawHomePage(PApplet p5){
        p5.background(246,224,181);
        drawmainBar(p5);
        drawID(p5);
        drawRecentLecturesList(p5);
    }

    public void drawGeneralLessons(PApplet p5){
        p5.background(246,224,181);
        Lection.display(p5);
        drawmainBar(p5);
        drawLecturesList(p5);
        drawUpperSign(p5);
    }

    public void drawMainLessons(PApplet p5){
        p5.background(246,224,181);
        drawmainBar(p5);
        drawUpperSign(p5);
        drawLectureInfo(p5);
        Lection.display(p5);
        c1.display(p5);
    }

    public void drawGeneralStatistics(PApplet p5){
        p5.background(246,224,181);
        drawmainBar(p5);
        drawUpperSign(p5);
        drawStatisticGenInfo(p5);

    }

    public void drawMainStatistics(PApplet p5){
        p5.background(246,224,181);
        drawmainBar(p5);
        drawUpperSign(p5);
        drawStatisticMainInfo(p5);

    }

    public void drawmainBar(PApplet p5){
        p5.fill(102,84,94);
        p5.rect(0,FullScreenheight-150,MainBarwidth,MainBarheight);

       home.display(p5);
       foldermainbar.display(p5);
       plus.display(p5);
       statistic.display(p5);

    }

    public void drawID(PApplet p5){
        p5.fill(170,111,115);
        p5.rect(125,FullScreenheight-980,IDwidth,IDheight,25);
        p5.fill(0);
        p5.textSize(TitleSize);
        p5.text("STUDENT ID", IDwidth/2,IDheight-210);
        p5.textSize(SubtitleSize);
        p5.text("USUARIO", IDwidth/2, IDheight-145);
        p5.text("NUM DE LECCIONES", IDwidth-220, IDheight-145);
        p5.text("LECCIONES HECHOS", IDwidth/2, IDheight-30);
        p5.text("LECCIONES A HACER", IDwidth-220, IDheight-30);
    }

    public void drawRecentLecturesList(PApplet p5) {
        int spacing = 20;
        p5.fill(0);
        p5.textSize(TitleSize);
        p5.text("LECCIONES RECIENTES", IDwidth + 250, 150);
        for (int i = 0; i < 5; i++) {
            float x = IDwidth +250; // Posición en x para alinearlos a la derecha con margen de 10
            float y = 180 + i * (125 + spacing); // Posición en y con espacio entre rectángulos
            p5.fill(170,111,115);
            p5.rect(x, y, RecentLecturewidth, RecentLectureheight,25);
        }
    }

    public void drawLecturesList(PApplet p5){
       int spacing = 20;
        for (int i = 0; i < 4; i++) {
            float x = 535; // Posición en x para alinearlos a la derecha con margen de 10
            float y = 160 + i * (170 + spacing); // Posición en y con espacio entre rectángulos
            p5.fill(170,111,115);
            p5.rect(x, y, RecentLecturewidth+300, RecentLectureheight+30,25);
        }
    }

    public void drawUpperSign(PApplet p5){
        p5.fill(255);
        p5.rect(50,50,500, 100);
        p5.fill(0);
        p5.textSize(TitleSize);
        p5.text("NOMBRE XXX", 230, 120);
    }

    public void drawLectureInfo(PApplet p5){
        p5.textSize(MidTitleSize);
        p5.text("NOMBRE XXX", 500, 260);

        //STATISTICS
        p5.fill(255);
        p5.rect(360,280,500,500);

        p5.fill(238,169,144);
        p5.rect(900, 160, PanelBoardwidth,PanelBoardheight,25);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("DETALLES DEL LECCIÓN", PanelBoardwidth+310,PanelBoardheight-460);
        p5.textSize(SubtitleSize);
        p5.text("TIPO: ", PanelBoardwidth+310,PanelBoardheight-420);
        p5.textSize(SubtitleSize);
        // afegir boto Tipo
        p5.text("INFORMACIÓN: ", PanelBoardwidth+310,PanelBoardheight-330);
        p5.textSize(SubtitleSize);

        //afegir boto Info + ACCEDER
        p5.text("MARCAR COMO: ", PanelBoardwidth+310,PanelBoardheight);
        p5.textSize(SubtitleSize);
        p5.text("HECHO ", PanelBoardwidth+360,PanelBoardheight+50);
        p5.textSize(SubtitleSize);
        p5.text("NO HECHO ", PanelBoardwidth+360,PanelBoardheight+100);
    }

    public void drawStatisticMainInfo(PApplet p5){
        // NAME OF LECTURE
        p5.fill(255);
        p5.rect(300,200,500, 100);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("NOMBRE XXX", 500, 260);

        //STATISTICS
        p5.fill(255);
        p5.rect(300,350,500,500);

        // INFO
        p5.fill(238,169,144);
        p5.rect(1000, 200, StatisticMainBoardwidth,StatisticMainBoardheight,25);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("ERRORES: ", StatisticMainBoardwidth+480,StatisticMainBoardheight-360);
        // afegir boto Errores
        p5.textSize(MidTitleSize);
        p5.text("RECOMENDACIONES: ", StatisticMainBoardwidth+480,StatisticMainBoardheight-110);
        //afegir boto Recomendaciones
    }

    public void drawStatisticGenInfo(PApplet p5) {

        //STATISTICS
        p5.fill(255);
        p5.rect(300, 170, 500, 400);

        // INFO
        p5.fill(238, 169, 144);
        p5.rect(250, 580, StatisticGenBoardwidth, StatisticGenBoardheight, 25);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("RESULTADOS: ", StatisticGenBoardwidth - 300, StatisticGenBoardheight + 330);
        // afegir boto Errores
        p5.textSize(SubtitleSize);
        p5.text("RECOMENDACIONES: ", StatisticGenBoardwidth - 300, StatisticGenBoardheight + 460);
        //afegir boto Recomendaciones és un text field

        int spacing = 20;
        for (int i = 0; i < 4; i++) {
            float x = 900; // Posición en x para alinearlos a la derecha con margen de 10
            float y = 160 + i * (170 + spacing); // Posición en y con espacio entre rectángulos
            p5.fill(170, 111, 115);
            p5.rect(x, y, RecentLecturewidth + 300, RecentLectureheight + 30, 25);
        }
    }

}
