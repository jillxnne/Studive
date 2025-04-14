package StudiveAppScreens;
import StudiveAppGUI.*;
import processing.core.PApplet;
import static StudiveAppLayout.Layout.*;
import static StudiveAppFonts.Sizes.*;
import StudiveAppQuestionaire.FlashCard;
import StudiveAppQuestionaire.MultipleChoiceQuiz;
import StudiveAppColors.Colors;
import StudiveAppFonts.Fonts;
import processing.core.PImage;

public class GUI extends PApplet {
    public enum SCREENS {SIGNINPAGE, LOGINPAGE, HOMEPAGE, SUBJECTPAGE, MAINLESSONS, ADDSUBJECT,LINKSPAGE, PDFPAGE,
        CARDSPAGE, QUIZPAGE, ADDLINK, ADDPDF, ADDQUIZ, ADDCARD, DONELESSONS,
        NOTDONELESSONS, GENERALSTATISTICS, MAINSTATISTICS, FLASHCARDTEST, FLASHCARDCREATION, TEST, TESTCREATION};
    public SCREENS Default;
    ImageButtons home, foldermainbar, plus, statistic, doneLections, notDoneLections, editName, edit, back, subjectback,
            bigback1, share, mainlessonback, statisticback,addDocTypeBackButton, addTestTypeBackButton;
    PImage homeIcon,homepressedIcon, homefolderIcon, homefolderpressedIcon, plusIcon, pdfIcon, testIcon, linkIcon,
            flashcardIcon, pluspressedIcon, statisticIcon, statisticpressedIcon, mainfolderIcon, editIcon,
            editpressedIcon, backIcon, backpressedIcon, shareIcon, sharepressedIcon, loginIcon;
    TextArea Lection, namechange, titleDoc,descriptionDoc,titleTest,descriptionTest, subjecttitle, username,
            password, newUsername, newPassword;
    Checkbox done;
    PagedCard HomePageCard, DonePageCard, NotDonePageCard, mainPageStat, pagePDF, pageFlashCards, pageLink,pageTest, mainPage;
    PagedSubject Subjects;
    Button PreviousSubject, NextSubject, statNext, statPrev, newTest, accessResults, PDFfiles, Flashcardsfiles,
            Quizfiles, Linksfiles,addFile,saveName, create, add0File, addlink, stateOfLessonsNext, stateOfLessonsPrev,
            login, signIn, createDoc, createTest,
            goToSignIn;
    BarsDiagram genDiagram, mainDiagram;
    SelectButton typeOfLection;
    Colors colorss;
    Fonts fonts;
    FlashCard flashCards;
    MultipleChoiceQuiz questionaire;
    String[][] subjectsInfo,
            doneTests, notDoneTests, doneDocs, notDoneDocs, totalDone, totalNotDone,
            pdfInfo, linkInfo, testInfo, flashcardsInfo, specificLessonInfo;
    String lectionTitle, lectionTypeOfDoc, subjectName;
    String currentPage = "PDF";
    public String lastSubjectLoaded = "";
    MultipleChoiceQuiz visualiveTestUI, createTestUI;
    FlashCard createFlashCardUI;
    FlashCard visualizeFlashCardUI;
    boolean inCreateMode = true;
    String testId = "";
    String flashCardId,questionaireID = "";
    String[] questions, answers;
    int[] correctIndex;

    // ----------------------------------- * TABLE INFORMATION * ----------------------------------- //
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
    String[] text = {"CAS", "TOK", "MN"};
    float[] values = {200, 450, 375};
    int[] colors = {color(0), color(255,255,255), color(100,100,100)};
    String[] lectionType = {"PDF", "TEST","FLASHCARDS","LINK"};

    public GUI(PApplet p5, DataBase db){
        // ----------------------------------- SUBJECT INFO
        subjectsInfo = db.getSubjectsInfo();

        // ----------------------------------- STATE OF LESSONS INFO
        doneTests = db.getTotalOfStateOfTests("done");
        notDoneTests = db.getTotalOfStateOfTests(" ");
        doneDocs = db.getTotalOfStateOfDocs("done");
        notDoneDocs = db.getTotalOfStateOfDocs(" ");
        totalDone = db.getTotalOfTestsAndDocsState(doneTests, doneDocs);
        totalNotDone = db.getTotalOfTestsAndDocsState(notDoneTests, notDoneDocs);

        // ----------------------------------- SPECIFIC INFO ABOUT A LESSON
        // specificLessonInfo = db.getSpecificInfoAboutTestOrDoc();

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
        setColorss(p5);

        setLoginAttributes(p5);
        setHomePageAttributes(p5);
        setStateOfLessonsPageButtons();
        setDoneLessonsComponents();
        setNotDoneLessonsComponents();
        setSubjectComponents(p5);
        setAddSubjectComponents(p5);
        setDocOrTestPageButtons();
        setAddDocOrTestPageButtons(p5);
        setFlashCards(p5);
        setTests(p5);
        fonts = new Fonts(p5);
    }
    public void setColorss(PApplet p5){
        colorss = new Colors(p5);
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
    public void setPageButtons(PApplet p5){
        statNext = new Button(this, "NEXT", 1450, 860, 60, 60);
        statPrev = new Button(this, "PREV", 1350, 860, 60, 60);
    }
    public void setAccessButtons(PApplet p5){
        accessResults = new Button(p5, "ACCESS RESULTS",360, 680,500,50);
        newTest = new Button(p5, "MAKE NEW TEST", 340, 780, 400, 50);
        saveName = new Button(p5, "SAVE", 900,520,100,50);
    }
    public void setPagedCards(){
        mainPageStat = new PagedCard(5);
        mainPageStat.setDimensions(900,180, RecentLecturewidth+120, 625);
        mainPageStat.setData(info);
        mainPageStat.setCards();
        mainPageStat.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);
    }

    public void setCheckBoxs(PApplet p5){
        done = new Checkbox(p5, PanelBoardwidth+320,PanelBoardheight+25,30);
    }
    public void setTextFields(PApplet p5){
        Lection = new TextArea(p5, (int)PanelBoardwidth+313, (int)PanelBoardheight-320, 500,290, 45,13);
        namechange = new TextArea(p5,750,450,400,50,40,13);
    }

    public void setImageButtons(PApplet p5){
        home = new ImageButtons(p5, homeIcon,homepressedIcon, 650,990,30);
        foldermainbar = new ImageButtons(p5, homefolderIcon,homefolderpressedIcon,950, 990,30);
        statistic = new ImageButtons(p5,statisticIcon, statisticpressedIcon,1250,990,30);
        edit = new ImageButtons(p5,editIcon,editpressedIcon,1470,250,20);
        share = new ImageButtons(p5,shareIcon,sharepressedIcon,1470,750,20);
        back = new ImageButtons(p5,backIcon,backpressedIcon,650,350,25);
        bigback1 = new ImageButtons(p5,backIcon,backpressedIcon,650,250,25);
        statisticback = new ImageButtons(p5,backIcon,backpressedIcon,150,110,25);
        mainlessonback = new ImageButtons(p5,backIcon,backpressedIcon,150,130,25);

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
        loginIcon = p5.loadImage("data/foldermain.png");
    }

    // ----------------------------------- * LOGIN PAGE * ----------------------------------- //
    public void drawLoginPage(PApplet p5){
        p5.background(246,224,181);
        p5.noFill();
        p5.strokeWeight(5);
        p5.rect(610,180,670,700,20);
        p5.image(loginIcon, 795,200,300,300);
        p5.textSize(MidTitleSize);
        p5.text("Usuario", 670, 530);
        username.display(p5);
        p5.textSize(MidTitleSize);
        p5.text("Contraseña", 670, 615);
        password.display(p5);
        login.display(p5);
        p5.textSize(SubtitleSize);
        p5.text("¿No tiene cuenta?", 835,790);
        goToSignIn.display(p5);
    }

    public void drawSignInPage(PApplet p5){
        p5.background(246,224,181);
        p5.noFill();
        p5.strokeWeight(5);
        p5.rect(610,180,670,600,20);
        p5.image(loginIcon, 795,200,300,300);
        p5.textSize(MidTitleSize);
        p5.text("Introduce tu usuario", 670, 530);
        username.display(p5);
        p5.textSize(MidTitleSize);
        p5.text("Introduce tu contraseña", 670, 615);
        password.display(p5);
        signIn.display(p5);
    }

    public void setLoginAttributes(PApplet p5){
        newUsername = new TextArea(p5, 670, 540, 550,40,40,23);
        newPassword = new TextArea(p5, 670,630,550,40,40,23);
        username = new TextArea(p5, 670, 540, 550,40,40,23);
        password = new TextArea(p5, 670,630,550,40,40,23);
        login = new Button(p5, "INICIAR SESIÓN", 830, 690,200,50);
        signIn = new Button(p5, "REGISTRARSE", 830,690,200,50);
        goToSignIn = new Button (p5,"CREAR CUENTA", 830,800,200,50);
    }

    // ----------------------------------- * HOME PAGE * ----------------------------------- //
    public void drawHomePage(PApplet p5){
        p5.background(246,224,181);
        p5.textSize(TitleSize);
        p5.text("Hola, " + username.getText(),125,FullScreenheight-980);
        p5.textSize(MidTitleSize);
        p5.text("Lecciones hechas", 450, 180);
        p5.textSize(MidTitleSize);
        p5.text("Lecciones a hacer", 450, 550);
        drawRecentLecturesList(p5);
        doneLections.display(p5);
        notDoneLections.display(p5);
        drawmainBar(p5);
    }
    // ----------------------------------- COMPONENTS
    public void drawRecentLecturesList(PApplet p5) {
        p5.textFont(fonts.getThirdFont());
        p5.textSize(MidTitleSize);
        p5.text("Lecciones recientes", IDwidth-70, 180);
        HomePageCard.display(p5);
    }
    public void setHomePageAttributes(PApplet p5){
        // buttons to access the (not) done lessons
        doneLections = new ImageButtons(p5, mainfolderIcon, mainfolderIcon, 600, 350,180);
        notDoneLections = new ImageButtons(p5, mainfolderIcon, mainfolderIcon, 600, 720, 180);

        // overview of the 4 most recent lessons
        HomePageCard = new PagedCard(4);
        HomePageCard.setDimensions(IDwidth-70,200, RecentLecturewidth+100, 625);
        HomePageCard.setData(totalDone);
        HomePageCard.setCards();
        HomePageCard.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);
    }
    // -------------------------------- * (NOT) DONE LESSONS * -------------------------------- //
    public void drawDoneLessons(PApplet p5){
        p5.background(246,224,181);
        DonePageCard.display(p5);
        stateOfLessonsNext.display(p5);
        stateOfLessonsPrev.display(p5);
        drawmainBar(p5);
    }
    public void drawNotDoneLessons(PApplet p5){
        p5.background(246,224,181);
        NotDonePageCard.display(p5);
        stateOfLessonsNext.display(p5);
        stateOfLessonsPrev.display(p5);
        drawmainBar(p5);
    }
    // ----------------------------------- COMPONENTS
    public void setStateOfLessonsPageButtons(){
        stateOfLessonsPrev = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        stateOfLessonsNext = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);
    }
    public void setDoneLessonsComponents(){
        DonePageCard = new PagedCard(5);
        DonePageCard.setDimensions(535,160, RecentLecturewidth+300, 700);
        DonePageCard.setData(totalDone);
        DonePageCard.setCards();
        DonePageCard.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);
    }
    public void setNotDoneLessonsComponents(){
        NotDonePageCard = new PagedCard(5);
        NotDonePageCard.setDimensions(535,160, RecentLecturewidth+300, 700);
        NotDonePageCard.setData(totalNotDone);
        NotDonePageCard.setCards();
        NotDonePageCard.setImages(flashcardIcon, pdfIcon, linkIcon,testIcon);
    }

    // ----------------------------------- * SUBJECT PAGE  * ----------------------------------- //
    public void drawSubjectPage(PApplet p5){
        p5.background(246,224,181);
        p5.fill(0);
        p5.textSize(TitleSize);
        p5.text("Asignaturas", 210, 120);
        Subjects.display(p5);
        NextSubject.display(p5);
        PreviousSubject.display(p5);
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
        p5.textSize(SubtitleSize);
        p5.text("Name of subject: ",650,620);
        subjecttitle.display(p5);
        create.display(p5);
        colorss.displayColors(p5, 800,520,300);
        subjectback.display(p5);
    }

    // ----------------------------------- COMPONENTS
    public void setSubjectComponents(PApplet p5){
        Subjects = new PagedSubject(4);
        Subjects.setDimensions(535,160, RecentLecturewidth+300, 700);
        Subjects.setData(subjectsInfo);
        Subjects.setSubjects(mainfolderIcon);

        // buttons next and back for Subject Card
        PreviousSubject = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        NextSubject = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);

        // button to add new subject
        plus = new ImageButtons(p5,plusIcon, pluspressedIcon,1600,850,40);
    }

    public void setAddSubjectComponents(PApplet p5){
        // text areas & buttons to go back or create
        subjecttitle = new TextArea(p5,650,630,550,40,40,23);
        create = new Button(p5, "CREATE", 900,700,100,50);
        subjectback = new ImageButtons(p5,backIcon,backpressedIcon,650,300,25);
    }

    // ---------------------------------- * DOCS AND TESTS * ---------------------------------- //
    // ----------------------------------- MAIN PAGE
    public void drawSelectDocOrTestPage(PApplet p5){
        p5.fill(255);
        p5.rect(210,170,1500,50,10);
        PDFfiles.display(p5);
        Flashcardsfiles.display(p5);
        Quizfiles.display(p5);
        Linksfiles.display(p5);
    }

    // ----------------------------------- PDF
    public void drawPDF(PApplet p5) {
        p5.background(246,224,181);
        drawSelectDocOrTestPage(p5);
        if (pdfInfo != null && !EmptyOrNot(pdfInfo)) {
            pagePDF.display(p5);
            if (addFile != null) addFile.display(p5);
        } else {
            p5.image(pdfIcon, 820, 400, 230, 230);
            p5.fill(0);
            p5.textSize(MidTitleSize);
            p5.text("No hay PDFS", 860, 680);
            if (add0File != null) add0File.display(p5);
        }
        drawmainBar(p5);
    }

    public void drawAddPDF(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,120,700,800,15);
        p5.image(pdfIcon, 820,150, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Título: ",650,400);
        titleDoc.display(p5);
        p5.text("Descripcón: ",650,485);
        p5.text("Enlace: ", 650, 760);
        descriptionDoc.display(p5);
        addlink.display(p5);
        createDoc.display(p5);
        addDocTypeBackButton.display(p5);
    }

    // ----------------------------------- FLASHCARD
    public void drawFlashcard(PApplet p5){
        p5.background(246,224,181);
        drawSelectDocOrTestPage(p5);
        if (flashcardsInfo != null && !EmptyOrNot(flashcardsInfo)) {
            pageFlashCards.display(p5);
            if (addFile != null) addFile.display(p5);
        } else {
            p5.image(flashcardIcon, 820, 400, 230, 230);
            p5.fill(0);
            p5.textSize(MidTitleSize);
            p5.text("No hay flashcards", 860, 680);
            if (add0File != null) add0File.display(p5);
        }
        drawmainBar(p5);
    }
    public void drawAddCards(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,170,700,710,15);
        p5.image(flashcardIcon, 820,230, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Título: ",650,490);
        titleTest.display(p5);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Descripcion: ",650,570);
        descriptionTest.display(p5);
        createTest.display(p5);
        addTestTypeBackButton.display(p5); // CHANGEEEEEEEEE
    }

    // ----------------------------------- LINK
    public void drawLink(PApplet p5){
        p5.background(246,224,181);
        drawSelectDocOrTestPage(p5);
        if (linkInfo != null && !EmptyOrNot(linkInfo)) {
            pageLink.display(p5);
            if (addFile != null) addFile.display(p5);
        } else {
            p5.image(linkIcon, 820, 400, 230, 230);
            p5.fill(0);
            p5.textSize(MidTitleSize);
            p5.text("No hay enlaces", 860, 680);
            if (add0File != null) add0File.display(p5);
        }
        drawmainBar(p5);
    }
    public void drawAddLink(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,120,700,800,15);
        p5.image(linkIcon, 820,150, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Título: ",650,400);
        titleDoc.display(p5);
        p5.text("Descripcón: ",650,485);
        p5.text("Enlace: ", 650, 760);
        descriptionDoc.display(p5);
        addlink.display(p5);
        createDoc.display(p5);
        addDocTypeBackButton.display(p5);
    }

    // ----------------------------------- TEST
    public void drawTest(PApplet p5){
        p5.background(246,224,181);
        drawSelectDocOrTestPage(p5);
        if (testInfo != null && !EmptyOrNot(testInfo)) {
            pageTest.display(p5);
            if (addFile != null) addFile.display(p5);
        } else {
            p5.image(testIcon, 820, 400, 230, 230);
            p5.fill(0);
            p5.textSize(MidTitleSize);
            p5.text("No hay tests", 860, 680);
            if (add0File != null) add0File.display(p5);
        }
        drawmainBar(p5);
    }
    public void drawAddTest(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,170,700,710,15);
        p5.image(testIcon, 820,230, 230,230);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Título: ",650,490);
        titleTest.display(p5);
        p5.fill(0);
        p5.textSize(SubtitleSize);
        p5.text("Descripcion: ",650,570);
        descriptionTest.display(p5);
        createTest.display(p5);
        addTestTypeBackButton.display(p5); // CHANGEEEEEEEEE
    }

    // ----------------------------------- COMPONENTS
    public boolean EmptyOrNot(String[][] array){
        if (array == null) {
            return true;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] != null && !array[i][j].isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public void setDocOrTestPageButtons(){
        // Top bar buttons to access type of doc and test
        PDFfiles = new Button(this,"PDF",210,170,375,50);
        Flashcardsfiles = new Button(this,"FLASHCARDS",585,170,375,50);
        Quizfiles = new Button(this,"QUIZ",960,170,375,50);
        Linksfiles = new Button(this,"LINKS",1335,170,375,50);

        // redo to add button of description
        // buttons to add new files when it's (not) empty
        addFile = new Button(this,"ADD FILE", 1350,300,150,50);
        add0File = new Button (this, "ADD FILE", 900,700,100,50);

        // button to add
        addlink = new Button (this, "ADD LINK", 650,770,600,50);
    }
    public void setAddDocOrTestPageButtons(PApplet p5){
        titleDoc = new TextArea(p5,650,410,600,40,51,1);
        descriptionDoc = new TextArea(p5,650,500,600,220,51,8);
        createDoc = new Button(this, "CREAR",900,840,100,50);
        addDocTypeBackButton = new ImageButtons(p5,backIcon,backpressedIcon,650,230,25);

        titleTest = new TextArea(p5,650,500,600,40,51,1);
        descriptionTest = new TextArea(p5,650,580,600,210,51,8);
        createTest = new Button(this, "CREAR",900,810,100,50);
        addTestTypeBackButton = new ImageButtons(p5,backIcon,backpressedIcon,650,250,25);
    }
    // ---------------------------------- * FLASHCARDS PAGE * ---------------------------------- //
    public void drawFlashCardVisualizePage(PApplet p5){
        p5.background(246,224,181);
        visualizeFlashCardUI.display(p5);
    }
    public boolean flashcardsInserted = false;

    public void drawFlashCardCreatePage(PApplet p5){
        p5.background(246,224,181);
        createFlashCardUI.display(p5);
    }

    public void setFlashCards(PApplet p5){
        createFlashCardUI = new FlashCard(p5, true);
        visualizeFlashCardUI = new FlashCard(p5,questions, answers);
    }

    // ------------------------------------- * TEST PAGE * ------------------------------------ //
    public void drawTestVisualizePage(PApplet p5){
        p5.background(246,224,181);
        visualiveTestUI.display(p5);
    }
    public boolean testInserted = false;

    public void drawTestCreatePage(PApplet p5){
        p5.background(246,224,181);
        createTestUI.display(p5);
    }
    public void setTests(PApplet p5){
        createTestUI = new MultipleChoiceQuiz(p5, true);
        visualiveTestUI = new MultipleChoiceQuiz(p5,questions, answers, correctIndex);
    }

    // --------------------------------- * MAIN LESSON INFO * --------------------------------- //
    public void drawMainLessons(PApplet p5) {
        p5.background(246, 224, 181);
        drawLectureInfo(p5);
        done.display(p5);
        /*Lection.display(p5);
        typeOfLection.display(p5);
        if (typeOfLection.getSelectedValue() == "TEST") {
            accessResults.display(p5);
            p5.image(testIcon, 380, 280, 380, 380);
        } else if (typeOfLection.getSelectedValue() == "PDF") {
            p5.image(pdfIcon, 360, 280, 400, 400);
        } else if (typeOfLection.getSelectedValue() == "FLASHCARDS") {
            p5.image(flashcardIcon, 360, 280, 400, 400);
        } else if (typeOfLection.getSelectedValue() == "LINK") {
            p5.image(linkIcon, 360, 280, 380, 380);
        }
         */
        drawmainBar(p5);
    }

    // ----------------------------------- COMPONENTS
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
        // edit.display(p5);
        //  share.display(p5);
        mainlessonback.display(p5);
    }

    // ----------------------------------- * STATISTICS * ----------------------------------- //
    public void drawGeneralStatistics(PApplet p5){
        p5.background(246,224,181);
        drawStatisticGenInfo(p5);
        statNext.display(p5);
        statPrev.display(p5);
        genDiagram.display(p5);
        mainPageStat.display(p5);
        drawmainBar(p5);
    }
    public void drawMainStatistics(PApplet p5){
        p5.background(246,224,181);
        drawStatisticMainInfo(p5);
        mainDiagram.display(p5);
        newTest.display(p5);
        drawmainBar(p5);
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
        statisticback.display(p5);
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

    // ------------------------------------ * OTHERS * ------------------------------------- //
    public void drawmainBar(PApplet p5){
        p5.fill(102,84,94);
        p5.rect(0,FullScreenheight-150,MainBarwidth,MainBarheight);
        home.display(p5);
        foldermainbar.display(p5);
        statistic.display(p5);
    }
}