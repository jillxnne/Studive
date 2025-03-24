package StudiveAppScreens;
import processing.core.PApplet;
import static StudiveAppScreens.GUI.*;

public class Studive extends PApplet {
    GUI gui;
    String username, password = " ";
    String currentPage = "PDF";
    DataBase db;
    public static void main(String[] args) {
        PApplet.main("StudiveAppScreens.Studive", args);
    }

    public void settings() {
        size(1920, 1080);
        smooth(10);
    }

    public void setup() {
        background(255);
        gui = new GUI(this);
        db = new DataBase("admin", "12345", "studive");
        db.connect();
        /*
        db.getInfo("COLOR", "asignatura", "ID", "Mates");

        String[] infoColumna = db.getInfoArray("documento", "ID");
        printArray(infoColumna);

        String[][] infoTaula = db.getInfoArray2D();
        println("TAULA: ");
        for (int i=0; i<infoTaula.length; i++) {
            print(i+" ");
            for (int j = 0; j < infoTaula[i].length; j++) {
                System.out.print(infoTaula[i][j]+"\t");
            }
            println();
        }

        String[][] infoQuery = db.getInfoPregunta();
        println("QUERY: ");
        for (int i=0; i<infoQuery.length; i++) {
            print(i+" ");
            for (int j = 0; j < infoQuery[i].length; j++) {
                System.out.print(infoQuery[i][j]+"\t");
            }
            println();
        }

        String[][] infoJoin = db.getInfoOfTwoRelatedTables();
        println("JOIN: ");
        for (int i=0; i<infoJoin.length; i++) {
            print(i+" ");
            for (int j = 0; j < infoJoin[i].length; j++) {
                System.out.print(infoJoin[i][j]+"\t");
            }
            println();
        }

        String[][] search = db.preguntesCercador("ee");
        println("SEARCH: ");
        for (int i=0; i<search.length; i++) {
            print(i+" ");
            for (int j = 0; j < search[i].length; j++) {
                System.out.print(search[i][j]+"\t");
            }
            println();
        }
        /* could be max, min, sum, avg, count,
        int Max = db.getCalculationForSomething("xx");
        System.out.println("SSS : " + Max);
         */

      /*  boolean nini = db.isUserOk("nini", "12345");
        System.out.println(nini);

        boolean lali = db.isUserOk("lali", "12345");
        System.out.println(lali);

        db.insertSomething("cling", "boom");
        db.updateSomething("cla", "kree", "cling");
        // db.deleteSomething("c");
       */
    }

    public void draw() {
        switch (gui.Default) {
            case LOGINPAGE:
                gui.drawLoginPage(this);
                break;
            case HOMEPAGE:
                gui.drawHomePage(this);
                break;
            case GENERALLESSONS:
                gui.drawGeneralLessons(this);
                break;
            case ADDSUBJECT:
                gui.drawAddSubject(this);
                break;
            case NOPDF:
                gui.drawNoPDF(this);
                break;
            case NOCARD:
                gui.drawNoCard(this);
                break;
            case NOLINK:
                gui.drawNoLink(this);
                break;
            case NOQUIZ:
                gui.drawNoQuiz(this);
                break;
            case ADDPDF:
                gui.drawAddPDF(this);
                break;
            case ADDCARD:
                gui.drawAddCards(this);
                break;
            case ADDLINK:
                gui.drawAddLink(this);
                break;
            case ADDQUIZ:
                gui.drawAddTest(this);
                break;
            case DONELESSONS:
                gui.drawDoneLessons(this);
                break;
            case NOTDONELESSONS:
                gui.drawNotDoneLessons(this);
                break;
            case MAINLESSONS:
                gui.drawMainLessons(this);
                break;
            case GENERALSTATISTICS:
                gui.drawGeneralStatistics(this);
                break;
            case MAINSTATISTICS:
                gui.drawMainStatistics(this);
                break;
            case PDFPAGE:
                gui.drawPage(this,currentPage);
                break;
            case LINKSPAGE:
                gui.drawPage(this,currentPage);
                break;
            case QUIZPAGE:
                gui.drawPage(this,currentPage);
                break;
            case CARDSPAGE:
                gui.drawPage(this,currentPage);
                break;
        }
    }
    public void keyPressed() {
        gui.Lection.keyPressed(key, keyCode);
        gui.namechange.keyPressed(key, keyCode);
        gui.subjecttitle.keyPressed(key, keyCode);
        gui.title1.keyPressed(key, keyCode);
        gui.description1.keyPressed(key, keyCode);
        gui.title0.keyPressed(key, keyCode);
        gui.description0.keyPressed(key, keyCode);
        gui.username.keyPressed(key,keyCode);
        gui.password.keyPressed(key,keyCode);

        if (key =='6'){
            gui.Default = SCREENS.NOPDF;
        }
        if (key =='7'){
            gui.Default = SCREENS.NOCARD;
        }
        if (key =='8'){
            gui.Default = SCREENS.NOQUIZ;
        }
        if (key =='9'){
            gui.Default = SCREENS.NOLINK;
        }
        if (key =='5'){
            gui.Default = SCREENS.LOGINPAGE;
        }
    }
    
    public void mousePressed() {

        if (gui.PDFfiles.mouseOverButton(this)) {
            currentPage = "PDF";  // Cambiar a la página PDF
        } else if (gui.Flashcardsfiles.mouseOverButton(this)) {
            currentPage = "FLASHCARDS";  // Cambiar a la página de tarjetas
        } else if (gui.Quizfiles.mouseOverButton(this)) {
            currentPage = "QUIZ";  // Cambiar a la página de quiz
        } else if (gui.Linksfiles.mouseOverButton(this)) {
            currentPage = "LINKS";  // Cambiar a la página de enlaces
        }
        if (gui.Default == SCREENS.LOGINPAGE){
            gui.password.isPressed(this);
            gui.username.isPressed(this);

            String username = gui.username.getText();
            String password = gui.password.getText();
            if (gui.Login.mouseOverButton(this) && db.isUserOk(username, password)){
                gui.Default = SCREENS.HOMEPAGE;
            }
        }

        else if (gui.Default == SCREENS.HOMEPAGE) {
            mainBarFunctions(this);
            if (gui.mainfolder1.mouseOverButton(this)) {
                gui.Default = SCREENS.NOTDONELESSONS;
            }
            if (gui.mainforlder2.mouseOverButton(this)){
                gui.Default = SCREENS.DONELESSONS;
            }
            if (gui.mainPageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

        } else if (gui.Default == SCREENS.GENERALLESSONS) {
            mainBarFunctions(this);
            if (gui.plus.mouseOverButton(this)){
                gui.Default = SCREENS.ADDSUBJECT;
            }
            if (gui.mainPageLection.checkMouseOver(this)) {
                gui.Default = SCREENS.PDFPAGE;
            }
            if (gui.lectNext.mouseOverButton(this) && gui.lectNext.isEnabled()) {
                gui.mainPageLection.nextPage();
            } else if (gui.lectPrev.mouseOverButton(this) && gui.lectPrev.isEnabled()) {
                gui.mainPageLection.prevPage();
            } else {
                gui.mainPageLection.checkSubjectSelection(this);
            }

        } else if (gui.Default == SCREENS.ADDSUBJECT){
            gui.subjecttitle.isPressed(this);
            String subjectitle = gui.subjecttitle.getText();
            gui.colorss.checkMouseOver(this,800,520,300);
            String color = gui.colorss.getSelectedColorAsString(this);
            if (gui.bigback.mouseOverButton(this)){
                gui.Default=SCREENS.GENERALLESSONS;
            }

        } else if (gui.Default == SCREENS.PDFPAGE){
            upperBarFunctions(this);
            if (gui.pagePDF.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }
            if (gui.addFile.mouseOverButton(this)){
                gui.Default=SCREENS.ADDPDF;
            }

        } else if (gui.Default == SCREENS.QUIZPAGE){
            upperBarFunctions(this);
            if (gui.pageTest.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.addFile.mouseOverButton(this)){
                gui.Default=SCREENS.ADDQUIZ;
            }

        } else if (gui.Default == SCREENS.LINKSPAGE){
            upperBarFunctions(this);
            if (gui.pageLink.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.addFile.mouseOverButton(this)){
                gui.Default=SCREENS.ADDLINK;
            }

        } else if (gui.Default == SCREENS.CARDSPAGE){
            upperBarFunctions(this);
            if (gui.pageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.addFile.mouseOverButton(this)){
                gui.Default=SCREENS.ADDCARD;
            }

        } else if (gui.Default == SCREENS.NOPDF){
            upperBarFunctions(this);
            if (gui.add0File.mouseOverButton(this)){
                gui.Default=SCREENS.ADDPDF;
            }

        } else if (gui.Default == SCREENS.NOQUIZ){
            upperBarFunctions(this);
            if (gui.add0File.mouseOverButton(this)){
                gui.Default=SCREENS.ADDQUIZ;
            }

        } else if (gui.Default == SCREENS.NOLINK){
            upperBarFunctions(this);
            if (gui.add0File.mouseOverButton(this)){
                gui.Default=SCREENS.ADDLINK;
            }

        } else if (gui.Default == SCREENS.NOCARD){
            upperBarFunctions(this);
            if (gui.add0File.mouseOverButton(this)){
                gui.Default=SCREENS.ADDCARD;
            }

        } else if (gui.Default == SCREENS.ADDPDF){
            gui.title0.isPressed(this);
            gui.description0.isPressed(this);
            upperBarFunctions(this);
        if (gui.bigback1.mouseOverButton(this)){
            gui.Default=SCREENS.PDFPAGE;
        }

        } else if (gui.Default == SCREENS.ADDQUIZ){
            gui.title1.isPressed(this);
            gui.description1.isPressed(this);
        upperBarFunctions(this);
        if (gui.bigback.mouseOverButton(this)){
            gui.Default=SCREENS.QUIZPAGE;
        }

        } else if (gui.Default == SCREENS.ADDLINK){
            gui.title0.isPressed(this);
            gui.description0.isPressed(this);
        if (gui.bigback1.mouseOverButton(this)){
            gui.Default=SCREENS.LINKSPAGE;
        }

        } else if (gui.Default == SCREENS.ADDCARD){
            gui.title1.isPressed(this);
            gui.description1.isPressed(this);
        if (gui.bigback.mouseOverButton(this)){
            gui.Default=SCREENS.CARDSPAGE;
        }

        } else if (gui.Default == SCREENS.NOTDONELESSONS){
            mainBarFunctions(this);
            if (gui.mainPageLection.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.lectNext.mouseOverButton(this) && gui.lectNext.isEnabled()) {
                gui.mainPageLection.nextPage();
            } else if (gui.lectPrev.mouseOverButton(this) && gui.lectPrev.isEnabled()) {
                gui.mainPageLection.prevPage();
            } else {
                gui.mainPageLection.checkSubjectSelection(this);
            }

        } else if (gui.Default == SCREENS.DONELESSONS){
            mainBarFunctions(this);
            if (gui.mainPageLection.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }
            if (gui.lectNext.mouseOverButton(this) && gui.lectNext.isEnabled()) {
                gui.mainPageLection.nextPage();
            } else if (gui.lectPrev.mouseOverButton(this) && gui.lectPrev.isEnabled()) {
                gui.mainPageLection.prevPage();
            } else {
                gui.mainPageLection.checkSubjectSelection(this);
            }

        } else if (gui.Default == SCREENS.MAINLESSONS) {
            mainBarFunctions(this);
           if (gui.typeOfLection.mouseOverSelect(this) && gui.typeOfLection.isEnabled()) {
                if (!gui.typeOfLection.isCollapsed()) {
                    gui.typeOfLection.update(this);
                }
                gui.typeOfLection.toggle();
            } else {
                gui.Lection.isPressed(this);
            }
            if (gui.done.onMouseOver(this)) {
                gui.done.toggle();
            }
            if (gui.accessResults.mouseOverButton(this)) {
                gui.Default = SCREENS.MAINSTATISTICS;
            }
            if (gui.mainback.mouseOverButton(this)) {
                gui.Default=SCREENS.DONELESSONS;
            }

        } else if (gui.Default == SCREENS.GENERALSTATISTICS) {
            mainBarFunctions(this);
            if (gui.mainPageStat.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINSTATISTICS;
            }
            if (gui.statNext.mouseOverButton(this) && gui.statNext.isEnabled()) {
                gui.mainPageStat.nextPage();
            } else if (gui.statPrev.mouseOverButton(this) && gui.statPrev.isEnabled()) {
                gui.mainPageStat.prevPage();
            } else {
                gui.mainPageStat.checkCardSelection(this);
            }

        } else if (gui.Default == SCREENS.MAINSTATISTICS) ;
            mainBarFunctions(this);
            if (gui.mainback1.mouseOverButton(this)){
                gui.Default=SCREENS.GENERALSTATISTICS;
            }
    }
    public void mainBarFunctions(PApplet p5){
        if (gui.home.mouseOverButton(this)) {
            gui.Default = SCREENS.HOMEPAGE;
        }

        if (gui.statistic.mouseOverButton(this)) {
            gui.Default = SCREENS.GENERALSTATISTICS;
        }

        if (gui.foldermainbar.mouseOverButton(this)){
            gui.Default = SCREENS.GENERALLESSONS;
        }
    }
    public void upperBarFunctions(PApplet p5){
        if (gui.PDFfiles.mouseOverButton(this)){
            gui.Default = SCREENS.PDFPAGE;
        }
        if (gui.Flashcardsfiles.mouseOverButton(this)){
            gui.Default = SCREENS.CARDSPAGE;
        }
        if (gui.Linksfiles.mouseOverButton(this)){
            gui.Default = SCREENS.LINKSPAGE;
        }
        if (gui.Quizfiles.mouseOverButton(this)){
            gui.Default = SCREENS.QUIZPAGE;
        }
    }
}
