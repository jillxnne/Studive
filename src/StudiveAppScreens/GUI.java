package StudiveAppScreens;
import StudiveAppGUI.Checkbox;
import StudiveAppGUI.PagedCard;
import StudiveAppGUI.Button;
import StudiveAppGUI.BarsDiagram;
import StudiveAppGUI.SelectButton;
import StudiveAppGUI.PopUp;
import StudiveAppGUI.PagedSubject;
import processing.core.PApplet;
import static StudiveAppLayout.Layout.*;
import static StudiveAppFonts.Sizes.*;
import StudiveAppGUI.ImageButtons;
import StudiveAppGUI.TextArea;
import StudiveAppColors.Colors;
import StudiveAppFonts.Fonts;
import processing.core.PApplet;
import processing.core.PImage;

public class GUI extends PApplet {
    public enum SCREENS {HOMEPAGE, GENERALLESSONS, MAINLESSONS, EDITNAME, ADDSUBJECT,LINKSPAGE, PDFPAGE, CARDSPAGE,
        QUIZPAGE, NOLINK, NOPDF, NOCARD, NOQUIZ, ADDLINK, ADDPDF, ADDQUIZ, ADDCARD, DONELESSONS, NOTDONELESSONS,
        GENERALSTATISTICS, MAINSTATISTICS};
    public SCREENS Default;
    ImageButtons home, foldermainbar, plus, statistic, mainfolder1, mainforlder2, editName, edit, back, bigback,
            bigback1, share, mainback, mainback1;
    PImage homeIcon,homepressedIcon, homefolderIcon, homefolderpressedIcon, plusIcon, pdfIcon, testIcon, linkIcon,
            flashcardIcon, pluspressedIcon, statisticIcon, statisticpressedIcon, mainfolderIcon, editIcon,
            editpressedIcon, backIcon, backpressedIcon, shareIcon, sharepressedIcon;
    TextArea Lection, namechange, title0,description0,title1,description1, subjecttitle;
    Checkbox done, notDone;
    PagedCard mainPageCard, mainPageStat, pagePDF, pageCard, pageLink,pageTest, mainPage;
    PagedSubject mainPageLection;
    Button lectNext, lectPrev, statNext, statPrev, newTest, accessResults, PDFfiles, Flashcardsfiles,
            Quizfiles, Linksfiles,addFile,saveName, create, add0File, addlink, mainNext, mainPrev;
    BarsDiagram genDiagram, mainDiagram;
    SelectButton typeOfLection;
    PopUp plusFunctions, lessonFunctions;
    Colors colorss;
    Fonts fonts;

    String[][] info = {
            {"Card Title 1", "cards", "This is the description for Card 1"},
            {"Card Title 2", "pdf", "This is the description for Card 2"},
            {"Card Title 3", "link", "This is the description for Card 3"},
            {"Card Title 4", "test", "This is the description for Card 4"},
            {"Card Title 1", "cards", "This is the description for Card 1"},
            {"Card Title 2", "pdf", "This is the description for Card 2"},
            {"Card Title 3", "link", "This is the description for Card 3"},
            {"Card Title 4", "test", "This is the description for Card 4"}
    };
    String[][] info1 = {
            {"info"},
            {"mates"},
            {"historia"},
            {"info"},
            {"mates"},
            {"historia"},
            {"info"},
            {"mates"},
            {"historia"}
    };
    String[][] cards = {
            {"Title 1", "cards", "Description"},
            {"Title 2", "cards", "Description"},
            {"Title 3", "cards", "Description"},
            {"Title 4", "cards", "Description"},
    };
    String[][] link = {
            {"Title 1", "link", "Description"},
            {"Title 2", "link", "Description"},
            {"Title 3", "link", "Description"},
            {"Title 4", "link", "Description"},
    };
    String[][] test = {
            {"Title 1", "test", "Description"},
            {"Title 2", "test", "Description"},
            {"Title 3", "test", "Description"},
            {"Title 4", "test", "Description"},
    };
    String[][] pdf = {
            {"Title 1", "pdf", "Description"},
            {"Title 2", "pdf", "Description"},
            {"Title 3", "pdf", "Description"},
            {"Title 4", "pdf", "Description"},
    };
    String[] text = {"CAS", "TOK", "MN"};
    float[] values = {200, 450, 375};
    int[] colors = {color(0), color(255,255,255), color(100,100,100)};
    String[] lectionType = {"PDF", "TEST","FLASHCARDS","LINK"};

    public GUI(PApplet p5){
        Default  = SCREENS.HOMEPAGE;

        this.setImage(p5);
        setImageButtons(p5);
        setTextFields(p5);
        setCheckBoxs(p5);
        setPageButtons(p5);
        setPagedCards();
        setBarsDiagrams();
        setAccessButtons(p5);
        setSelectButtons();
        setPopUps(p5);
        setLessonTypeButtons(p5);
        setPagedSubjects();
        setColorss(p5);
        fonts = new Fonts(p5);
    }
    public void setColorss(PApplet p5){
        colorss = new Colors(p5);
    }

    public void setPopUps(PApplet p5){
        plusFunctions = new PopUp(p5, 100, 100, 800, 340);
        plusFunctions.setTextButtons("PDF", "FLASHCARDS", "TEST");

        lessonFunctions = new PopUp(p5,100,100,800,340);
        lessonFunctions.setTextButtons("SHARE", "EDIT", "CHANGE LOCATION");
    }

    public void setSelectButtons(){
        typeOfLection = new SelectButton(lectionType,PanelBoardwidth+310, PanelBoardheight-400,400,30);
    }
    public void setBarsDiagrams(){
        genDiagram = new BarsDiagram(300,100, 500,400);
        genDiagram.setColors(colors);
        genDiagram.setValues(values);
        genDiagram.setTexts(text);

        mainDiagram = new BarsDiagram(300,170,500,500);
        mainDiagram.setColors(colors);
        mainDiagram.setValues(values);
        mainDiagram.setTexts(text);
    }
    public void setLessonTypeButtons(PApplet p5){
        PDFfiles = new Button(this,"PDF",210,170,375,50);
        Flashcardsfiles = new Button(this,"FLASHCARDS",585,170,375,50);
        Quizfiles = new Button(this,"QUIZ",960,170,375,50);
        Linksfiles = new Button(this,"LINKS",1335,170,375,50);
        addFile = new Button(this,"ADD FILE", 1350,300,150,50);
        add0File = new Button (this, "ADD FILE", 900,700,100,50);
        addlink = new Button (this, "ADD LINK", 800,630,300,50);
    }
    public void setPageButtons(PApplet p5){
        lectPrev = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        lectNext = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);
        mainPrev = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        mainNext = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);
        statNext = new Button(this, "NEXT", 1450, 860, 60, 60);
        statPrev = new Button(this, "PREV", 1350, 860, 60, 60);
    }
    public void setAccessButtons(PApplet p5){
        accessResults = new Button(p5, "ACCESS RESULTS",360, 680,500,50);
        newTest = new Button(p5, "MAKE NEW TEST", 340, 780, 400, 50);
        saveName = new Button(p5, "SAVE", 900,520,100,50);
        create = new Button(p5, "CREATE", 900,700,100,50);
    }
    public void setPagedCards(){
        mainPageCard = new PagedCard(3);
        mainPageCard.setDimensions(IDwidth-70,200, RecentLecturewidth+100, 625);
        mainPageCard.setData(info);
        mainPageCard.setCards();
        mainPageCard.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

        mainPage = new PagedCard(3);
        mainPage.setDimensions(535,160, RecentLecturewidth+300, 700);
        mainPage.setData(info);
        mainPage.setCards();
        mainPage.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

        mainPageStat = new PagedCard(5);
        mainPageStat.setDimensions(900,180, RecentLecturewidth+120, 625);
        mainPageStat.setData(info);
        mainPageStat.setCards();
        mainPageStat.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

        pagePDF = new PagedCard(4);
        pagePDF.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
        pagePDF.setData(pdf);
        pagePDF.setCards();
        pagePDF.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

        pageCard = new PagedCard(4);
        pageCard.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
        pageCard.setData(cards);
        pageCard.setCards();
        pageCard.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

        pageLink = new PagedCard(4);
        pageLink.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
        pageLink.setData(link);
        pageLink.setCards();
        pageLink.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

        pageTest = new PagedCard(4);
        pageTest.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
        pageTest.setData(test);
        pageTest.setCards();
        pageTest.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);

    }
    public void setPagedSubjects(){
        mainPageLection = new PagedSubject(4);
        mainPageLection.setDimensions(535,160, RecentLecturewidth+300, 700);
        mainPageLection.setData(info1);
        mainPageLection.setSubjects(mainfolderIcon);
    }
    public void setCheckBoxs(PApplet p5){
        done = new Checkbox(p5, PanelBoardwidth+320,PanelBoardheight+25,30);
        notDone = new Checkbox(p5, PanelBoardwidth+320,PanelBoardheight+75,30);
    }
    public void setTextFields(PApplet p5){
        Lection = new TextArea(p5, (int)PanelBoardwidth+313, (int)PanelBoardheight-320, 500,290, 45,13);
        namechange = new TextArea(p5,750,450,400,50,40,13);
        subjecttitle = new TextArea(p5,650,630,550,40,40,23);

        title0 = new TextArea(p5,650,460,550,40,40,23);
        description0 = new TextArea(p5,650,550,550,60,40,23);

        title1 = new TextArea(p5,650,510,550,40,40,23);
        description1 = new TextArea(p5,650,630,550,40,40,23);
    }
    public void setImageButtons(PApplet p5){
        home = new ImageButtons(p5, homeIcon,homepressedIcon, 650,990,30);
        foldermainbar = new ImageButtons(p5, homefolderIcon,homefolderpressedIcon,950, 990,30);
        plus = new ImageButtons(p5,plusIcon, pluspressedIcon,1600,850,40);
        statistic = new ImageButtons(p5,statisticIcon, statisticpressedIcon,1250,990,30);
        editName = new ImageButtons(p5,editIcon,editpressedIcon,320,82,18);
        edit = new ImageButtons(p5,editIcon,editpressedIcon,1470,250,20);
        share = new ImageButtons(p5,shareIcon,sharepressedIcon,1470,750,20);
        back = new ImageButtons(p5,backIcon,backpressedIcon,650,350,25);
        bigback = new ImageButtons(p5,backIcon,backpressedIcon,650,300,25);
        bigback1 = new ImageButtons(p5,backIcon,backpressedIcon,650,250,25);
        mainback = new ImageButtons(p5,backIcon,backpressedIcon,150,110,25);
        mainback1 = new ImageButtons(p5,backIcon,backpressedIcon,150,110,25);

        mainfolder1 = new ImageButtons(p5, mainfolderIcon, mainfolderIcon, 600, 350,180);
        mainforlder2 = new ImageButtons(p5, mainfolderIcon, mainfolderIcon, 600, 720, 180);
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
        mainfolderIcon = p5.loadImage("data/foldermain.png");
        editIcon = p5.loadImage("data/edit.png");
        editpressedIcon = p5.loadImage("data/editpressed.png");
        linkIcon = p5.loadImage("data/link.png");
        flashcardIcon = p5.loadImage("data/flashcard.png");
        pdfIcon = p5.loadImage("data/pdf.png");
        testIcon = p5.loadImage("data/test.png");
        backIcon = p5.loadImage("data/back.png");
        backpressedIcon = p5.loadImage("data/backpressed.png");
        shareIcon = p5.loadImage("data/share.png");
        sharepressedIcon = p5.loadImage("data/sharepressed.png");
    }

    public void drawHomePage(PApplet p5){
        p5.background(246,224,181);
        drawGreetings(p5);
        editName.display(p5);
        p5.textSize(MidTitleSize);
        p5.text("Lecciones hechas", 450, 180);
        p5.textSize(MidTitleSize);
        p5.text("Lecciones a hacer", 450, 550);
        drawRecentLecturesList(p5);
        mainfolder1.display(p5);
        mainforlder2.display(p5);
        drawmainBar(p5);
    }
    public void drawEditName(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,290,700,400,15);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("Nombre: ", 750, 420);
        namechange.display(p5);
        saveName.display(p5);
        back.display(p5);
    }
    public void drawGeneralLessons(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        mainPageLection.display(p5);
        lectNext.display(p5);
        lectPrev.display(p5);
        plus.display(p5);
        drawmainBar(p5);
    }

    public void drawAddSubject(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,220,700,600,15);
        p5.image(mainfolderIcon, 820,250, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Colors: ",650,500);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Name of subject: ",650,620);
        subjecttitle.display(p5);
        create.display(p5);
        colorss.displayColors(p5, 800,520,300);
        bigback.display(p5);
    }
    public void drawNoPDF(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawSubjectBar(p5);
        p5.image(pdfIcon,820,400,230,230);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("No files found",860,680);
        add0File.display(p5);
        drawmainBar(p5);
    }
    public void drawNoLink(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawSubjectBar(p5);
        p5.image(linkIcon,820,400,230,230);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("No files found",860,680);
        add0File.display(p5);
        drawmainBar(p5);
    }
    public void drawNoQuiz(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawSubjectBar(p5);
        p5.image(testIcon,820,400,230,230);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("No files found",860,680);
        add0File.display(p5);
        drawmainBar(p5);
    }
    public void drawNoCard(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawSubjectBar(p5);
        p5.image(flashcardIcon,820,400,230,230);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("No files found",860,680);
        add0File.display(p5);
        drawmainBar(p5);
    }
    public void drawGreetings(PApplet p5){
        p5.textSize(TitleSize);
        p5.text("Hola, X",125,FullScreenheight-980);
    }

    public void drawMainLessons(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawLectureInfo(p5);
        Lection.display(p5);
        done.display(p5);
        typeOfLection.display(p5);
        if (typeOfLection.getSelectedValue() == "TEST"){
            accessResults.display(p5);
            p5.image(testIcon,380,280,380,380);
        }  else if (typeOfLection.getSelectedValue() == "PDF"){
            p5.image(pdfIcon,360,280,400,400);
        } else if (typeOfLection.getSelectedValue() == "FLASHCARDS"){
            p5.image(flashcardIcon,360,280,400,400);
        } else if (typeOfLection.getSelectedValue() == "LINK"){
            p5.image(linkIcon,360,280,380,380);
        }
        drawmainBar(p5);
    }
    public void drawPDFPage(PApplet p5){
        p5.background(246,224,181);
        drawSubjectBar(p5);
        drawUpperSign(p5);
        pagePDF.display(p5);
        addFile.display(p5);
        drawmainBar(p5);
    }

    public void drawAddLink(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,180,700,600,15);
        p5.image(linkIcon, 820,210, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Title: ",650,450);
        title0.display(p5);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Description: ",650,530);
        description0.display(p5);
        addlink.display(p5);
        create.display(p5);
        bigback1.display(p5);
    }
    public void drawAddPDF(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,180,700,600,15);
        p5.image(pdfIcon, 820,210, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Title: ",650,450);
        title0.display(p5);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Description: ",650,530);
        description0.display(p5);
        addlink.display(p5);
        create.display(p5);
        bigback1.display(p5);
    }
    public void drawAddTest(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,220,700,550,15);
        p5.image(testIcon, 820,250, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Title: ",650,500);
        title1.display(p5);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Description: ",650,620);
        description1.display(p5);
        create.display(p5);
        bigback.display(p5);
    }
    public void drawAddCards(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,220,700,550,15);
        p5.image(flashcardIcon, 820,250, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Title: ",650,500);
        title1.display(p5);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Description: ",650,620);
        description1.display(p5);
        create.display(p5);
        bigback.display(p5);
    }

    public void drawCardsPage(PApplet p5){
        p5.background(246,224,181);
        drawSubjectBar(p5);
        drawUpperSign(p5);
        pageCard.display(p5);
        addFile.display(p5);
        drawmainBar(p5);
    }

    public void drawQuizPage(PApplet p5){
        p5.background(246,224,181);
        drawSubjectBar(p5);
        drawUpperSign(p5);
        pageTest.display(p5);
        addFile.display(p5);
        drawmainBar(p5);
    }

    public void drawLinkPage(PApplet p5){
        p5.background(246,224,181);
        drawSubjectBar(p5);
        drawUpperSign(p5);
        pageLink.display(p5);
        addFile.display(p5);
        drawmainBar(p5);
    }
    public void drawDoneLessons(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        mainPage.display(p5);
        mainNext.display(p5);
        mainPrev.display(p5);
        drawmainBar(p5);
    }

    public void drawNotDoneLessons(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        mainPage.display(p5);
        mainNext.display(p5);
        mainPrev.display(p5);
        drawmainBar(p5);
    }

    public void drawGeneralStatistics(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawStatisticGenInfo(p5);
        statNext.display(p5);
        statPrev.display(p5);
        genDiagram.display(p5);
        mainPageStat.display(p5);
        drawmainBar(p5);
    }

    public void drawMainStatistics(PApplet p5){
        p5.background(246,224,181);
        drawUpperSign(p5);
        drawStatisticMainInfo(p5);
        mainDiagram.display(p5);
        newTest.display(p5);
        drawmainBar(p5);
    }

    public void drawmainBar(PApplet p5){
        p5.fill(102,84,94);
        p5.rect(0,FullScreenheight-150,MainBarwidth,MainBarheight);

        home.display(p5);
        foldermainbar.display(p5);
        statistic.display(p5);
    }

    public void drawRecentLecturesList(PApplet p5) {
        p5.textFont(fonts.getThirdFont());
        p5.textSize(MidTitleSize);
        p5.text("Lecciones recientes", IDwidth-70, 180);
        mainPageCard.display(p5);
    }

    public void drawSubjectBar(PApplet p5){
        p5.fill(255);
        p5.rect(210,170,1500,50,10);
        PDFfiles.display(p5);
        Flashcardsfiles.display(p5);
        Quizfiles.display(p5);
        Linksfiles.display(p5);
    }

    public void drawUpperSign(PApplet p5){
        p5.fill(0);
        p5.textSize(TitleSize);
        p5.text("Lección/Subject", 210, 120);
    }

    public void drawLectureInfo(PApplet p5){
        p5.textSize(MidTitleSize);
        p5.text("Title", 500, 260);

        p5.fill(238,169,144);
        p5.rect(900, 160, PanelBoardwidth,PanelBoardheight-50,25);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("DETALLES DEL LECCIÓN", PanelBoardwidth+310,PanelBoardheight-460);
        p5.textSize(SubtitleSize);
        p5.text("TIPO: ", PanelBoardwidth+310,PanelBoardheight-420);
        p5.textSize(SubtitleSize);

        p5.text("DESCRIPCIÓN: ", PanelBoardwidth+310,PanelBoardheight-330);
        p5.textSize(SubtitleSize);

        p5.text("MARCAR COMO: ", PanelBoardwidth+310,PanelBoardheight);
        p5.textSize(SubtitleSize);
        p5.text("HECHO ", PanelBoardwidth+360,PanelBoardheight+50);

        edit.display(p5);
        share.display(p5);
        mainback.display(p5);
    }

    public void drawStatisticMainInfo(PApplet p5){
        // NAME OF LECTURE
        p5.fill(255);
        p5.rect(300,200,500, 100);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("NOMBRE XXX", 500, 260);

        // INFO
        p5.fill(238,169,144);
        p5.rect(1000, 200, StatisticMainBoardwidth,StatisticMainBoardheight,25);
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("ERRORES: ", StatisticMainBoardwidth+480,StatisticMainBoardheight-360);
        p5.fill(255);
        p5.rect(StatisticMainBoardwidth+480,StatisticMainBoardheight-340,400,200);
        // afegir boto Errores
        p5.fill(0);
        p5.textSize(MidTitleSize);
        p5.text("RECOMENDACIONES: ", StatisticMainBoardwidth+480,StatisticMainBoardheight-110);
        p5.fill(255);
        p5.rect(StatisticMainBoardwidth+480,StatisticMainBoardheight-90,400,200);
        //afegir boto Recomendaciones
        mainback1.display(p5);
    }

    public void drawStatisticGenInfo(PApplet p5) {
        // INFO
        p5.fill(238, 169, 144);
        p5.rect(250, 580, StatisticGenBoardwidth, StatisticGenBoardheight, 25);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("RESULTADOS: ", StatisticGenBoardwidth - 300, StatisticGenBoardheight + 330);
        p5.fill(255);
        p5.rect(StatisticGenBoardwidth-300, StatisticGenBoardheight+340, 500,80);
        // afegir boto Errores
        p5.textSize(SubtitleSize);
        p5.fill(0);
        p5.text("RECOMENDACIONES: ", StatisticGenBoardwidth - 300, StatisticGenBoardheight + 460);
        p5.fill(255);
        p5.rect(StatisticGenBoardwidth - 300, StatisticGenBoardheight + 480, 500,70);
        //afegir boto Recomendaciones és un text field
    }
}