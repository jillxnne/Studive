package StudiveAppScreens;
import StudiveAppGUI.*;
import processing.core.PApplet;
import static StudiveAppLayout.Layout.*;
import static StudiveAppFonts.Sizes.*;
import StudiveAppQuestionaire.FlashCard;
import StudiveAppColors.Colors;
import StudiveAppFonts.Fonts;
import processing.core.PImage;

public class GUI extends PApplet {
    public enum SCREENS {SIGNINPAGE, LOGINPAGE, HOMEPAGE, SUBJECTPAGE, ADDSUBJECT, CARDSPAGE, ADDCARD, DONELESSONS,
        NOTDONELESSONS, FLASHCARDTEST, FLASHCARDCREATION};
    public SCREENS Default;
    ImageButtons home, foldermainbar, plus, doneLections, notDoneLections, subjectback, stateOfLessonBackButton,
             flashCardBackButton, addTestTypeBackButton;
    Button PreviousSubject, NextSubject, CardsNext, CardsPrev, addFile, create, add0File, stateOfLessonsNext,
            stateOfLessonsPrev, login, signIn, createTest, goToSignIn;
    PImage homeIcon,homepressedIcon, homefolderIcon, homefolderpressedIcon, plusIcon, flashcardIcon,
            pluspressedIcon, mainfolderIcon, backIcon, backpressedIcon,  loginIcon;
    TextArea titleTest,descriptionTest, subjecttitle, username,  password, newUsername, newPassword;
    PagedCard HomePageCard, DonePageCard, NotDonePageCard, pageFlashCards;
    PagedSubject Subjects;
    Colors colorss;
    Fonts fonts;
    String[][] subjectsInfo, doneTests, notDoneTests, flashcardsInfo;
    String subjectName;
    FlashCard createFlashCardUI, visualizeFlashCardUI;
    String testId, flashCardId = "";
    String[] questions, answers;
    public boolean flashcardsInserted = false;

    // ----------------------------------- * TABLE INFORMATION * ----------------------------------- //
    public GUI(PApplet p5, DataBase db){
        // ----------------------------------- SUBJECT INFO
        subjectsInfo = db.getSubjectsInfo();

        // ----------------------------------- STATE OF LESSONS INFO
        doneTests = db.getTotalOfStateOfTests(1);
        notDoneTests = db.getTotalOfStateOfTests(0);

        // ----------------------------------- OTHERS
        Default  = SCREENS.LOGINPAGE;
        this.setImage(p5);
        setMainBarButtons(p5);
        setLoginAttributes(p5);
        setHomePageAttributes(p5);
        setStateOfLessonsPageButtons();
        setDoneLessonsComponents();
        setNotDoneLessonsComponents();
        setSubjectComponents(p5);
        setAddSubjectComponents(p5);
        setPageFlashCardsComponents();
        setAddFlashcardPageButtons(p5);
        setFlashCards(p5);
        setColorss(p5);
        fonts = new Fonts(p5);
    }

    public void setColorss(PApplet p5){
        colorss = new Colors(p5);
    }

    public void setImage(PApplet p5){
        homeIcon = p5.loadImage("data/home.png");
        homepressedIcon = p5.loadImage("data/homepressed.png");
        homefolderIcon = p5.loadImage("data/folder.png");
        homefolderpressedIcon = p5.loadImage("data/folderpressed.png");
        plusIcon = p5.loadImage("data/pluspressed.png");
        pluspressedIcon = p5.loadImage("data/plus.png");
        mainfolderIcon = p5.loadImage("data/foldermain.png");
        flashcardIcon = p5.loadImage("data/flashcard.png");
        backIcon = p5.loadImage("data/back.png");
        backpressedIcon = p5.loadImage("data/backpressed.png");
        loginIcon = p5.loadImage("data/foldermain.png");
    }

    // ----------------------------------- * LOGIN PAGE * ----------------------------------- //
    public void drawLoginPage(PApplet p5){
        p5.background(246,224,181);
        p5.noFill();
        p5.strokeWeight(5);
        p5.rect(610,180,670,700,20);
        p5.strokeWeight(0);
        p5.image(loginIcon, 795,200,300,300);
        p5.textFont(fonts.getThirdFont());
        p5.text("Usuario", 670, 530);
        username.display(p5);
        p5.text("Contraseña", 670, 615);
        password.display(p5);
        login.display(p5);
        p5.textSize(SubtitleSize);
        p5.text("¿No tiene cuenta?", 810,790);
        goToSignIn.display(p5);
    }

    public void drawSignInPage(PApplet p5){
        p5.background(246,224,181);
        p5.noFill();
        p5.strokeWeight(5);
        p5.rect(610,180,670,600,20);
        p5.strokeWeight(0);
        p5.image(loginIcon, 795,200,300,300);
        p5.textFont(fonts.getThirdFont());
        p5.text("Introduce tu usuario", 670, 530);
        p5.text("Introduce tu contraseña", 670, 615);
        username.display(p5);
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
        p5.textFont(fonts.getFirstFont());
        p5.text("Hola, " +username.getText(),125,FullScreenheight-980);
        p5.textFont(fonts.getThirdFont());
        p5.text("Flashcards a hacer", 450, 180);
        p5.text("Flashcards hechas", 450, 550);
        p5.text("Flashcards recientes", IDwidth-70, 180);
        p5.textFont(fonts.getSecondFont());
        HomePageCard.display(p5);
        doneLections.display(p5);
        notDoneLections.display(p5);
        drawmainBar(p5);
    }
    // ----------------------------------- COMPONENTS
    public void setHomePageAttributes(PApplet p5){
        doneLections = new ImageButtons(p5, mainfolderIcon, mainfolderIcon, 600, 350,180);
        notDoneLections = new ImageButtons(p5, mainfolderIcon, mainfolderIcon, 600, 720, 180);

        HomePageCard = new PagedCard(4);
        HomePageCard.setDimensions(IDwidth-70,200, RecentLecturewidth+100, 625);
        HomePageCard.setData(doneTests);
        HomePageCard.setCards();
        HomePageCard.setImages(flashcardIcon);
    }
    // -------------------------------- * (NOT) DONE LESSONS * -------------------------------- //
    public void drawDoneLessons(PApplet p5){
        p5.background(246,224,181);
        p5.textFont(fonts.getFirstFont());
        p5.text("Flashcards hechas", 800, 120);
        p5.textFont(fonts.getSecondFont());
        DonePageCard.display(p5);
        stateOfLessonsNext.display(p5);
        p5.textFont(fonts.getThirdFont());
        stateOfLessonsPrev.display(p5);
        stateOfLessonBackButton.display(p5);
        drawmainBar(p5);
    }
    public void drawNotDoneLessons(PApplet p5){
        p5.background(246,224,181);
        p5.textFont(fonts.getFirstFont());
        p5.text("Flashcards a hacer", 800, 120);
        p5.textFont(fonts.getSecondFont());
        NotDonePageCard.display(p5);
        stateOfLessonsNext.display(p5);
        p5.textFont(fonts.getThirdFont());
        stateOfLessonsPrev.display(p5);
        stateOfLessonBackButton.display(p5);
        drawmainBar(p5);
    }
    // ----------------------------------- COMPONENTS
    public void setStateOfLessonsPageButtons(){
        stateOfLessonsPrev = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        stateOfLessonsNext = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);
        stateOfLessonBackButton = new ImageButtons(this,backIcon,backpressedIcon,150,130,25);
    }
    public void setDoneLessonsComponents(){
        DonePageCard = new PagedCard(5);
        DonePageCard.setDimensions(535,160, RecentLecturewidth+300, 700);
        DonePageCard.setData(doneTests);
        DonePageCard.setCards();
        DonePageCard.setImages(flashcardIcon);
    }
    public void setNotDoneLessonsComponents(){
        NotDonePageCard = new PagedCard(5);
        NotDonePageCard.setDimensions(535,160, RecentLecturewidth+300, 700);
        NotDonePageCard.setData(notDoneTests);
        NotDonePageCard.setCards();
        NotDonePageCard.setImages(flashcardIcon);
    }

    // ----------------------------------- * SUBJECT PAGE  * ----------------------------------- //
    public void drawSubjectPage(PApplet p5){
        p5.background(246,224,181);
        p5.fill(0);
        p5.textFont(fonts.getFirstFont());
        p5.text("Asignaturas", 210, 120);
        p5.textFont(fonts.getSecondFont());
        Subjects.display(p5);
        p5.textFont(fonts.getThirdFont());
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
        p5.textFont(fonts.getThirdFont());
        p5.text("Colores: ",650,500);
        p5.text("Nombre de la asignatura: ",650,620);
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

        PreviousSubject = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        NextSubject = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);

        plus = new ImageButtons(p5,plusIcon, pluspressedIcon,1600,850,40);
    }

    public void setAddSubjectComponents(PApplet p5){
        subjecttitle = new TextArea(p5,650,630,550,40,40,23);
        create = new Button(p5, "CREATE", 900,700,100,50);
        subjectback = new ImageButtons(p5,backIcon,backpressedIcon,650,300,25);
    }

    // ---------------------------------- * DOCS AND TESTS * ---------------------------------- //
    // ----------------------------------- MAIN PAGE
    public void drawFlashcard(PApplet p5){
        p5.background(246,224,181);
        p5.textFont(fonts.getFirstFont());
        p5.text("Flashcards de esta asignatura ", 720, 200);
        if (flashcardsInfo != null && !EmptyOrNot(flashcardsInfo)) {
            p5.textFont(fonts.getSecondFont());
            pageFlashCards.display(p5);
            p5.textFont(fonts.getThirdFont());
            if (addFile != null) addFile.display(p5);
        } else {
            p5.image(flashcardIcon, 820, 320, 230, 230);
            p5.fill(0);
            p5.textFont(fonts.getSecondFont());
            p5.text("No hay flashcards", 840, 600);
            p5.textFont(fonts.getThirdFont());
            if (add0File != null) add0File.display(p5);
        }
        drawmainBar(p5);
        flashCardBackButton.display(p5);
    }
    public void drawAddCards(PApplet p5){
        p5.background(246,224,181);
        p5.fill(255);
        p5.rect(600,170,700,710,15);
        p5.image(flashcardIcon, 820,230, 230,230);
        p5.fill(0);
        p5.textFont(fonts.getThirdFont());
        p5.text("Título: ",650,490);
        titleTest.display(p5);
        p5.text("Descripcion: ",650,570);
        descriptionTest.display(p5);
        createTest.display(p5);
        addTestTypeBackButton.display(p5);
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
    public void setPageFlashCardsComponents(){
        addFile = new Button(this,"ADD FILE", 1350,300,150,50);
        add0File = new Button (this, "ADD FILE", 900,630,100,50);
        CardsPrev = new Button(this, "PREV", IDwidth+350, 165, 60, 60);
        CardsNext = new Button(this, "NEXT", IDwidth+350, 820, 60, 60);
        flashCardBackButton = new ImageButtons(this,backIcon,backpressedIcon,150,130,25);
    }
    public void setAddFlashcardPageButtons(PApplet p5){
        titleTest = new TextArea(p5,650,500,600,40,51,1);
        descriptionTest = new TextArea(p5,650,580,600,210,51,8);
        createTest = new Button(this, "CREAR",900,810,100,50);
        addTestTypeBackButton = new ImageButtons(p5,backIcon,backpressedIcon,650,250,25);
    }
    // ---------------------------------- * FLASHCARDS PAGE * ---------------------------------- //
    public void drawFlashCardVisualizePage(PApplet p5){
        p5.background(246,224,181);
        p5.textFont(fonts.getSecondFont());
        visualizeFlashCardUI.display(p5);
    }

    public void drawFlashCardCreatePage(PApplet p5){
        p5.background(246,224,181);
        p5.textFont(fonts.getSecondFont());
        createFlashCardUI.display(p5);
    }

    public void setFlashCards(PApplet p5){
        createFlashCardUI = new FlashCard(p5, true);
        visualizeFlashCardUI = new FlashCard(p5,questions, answers);
    }

    // ------------------------------------ * OTHERS * ------------------------------------- //
    public void drawmainBar(PApplet p5){
        p5.fill(102,84,94);
        p5.rect(0,FullScreenheight-150,MainBarwidth,MainBarheight);
        home.display(p5);
        foldermainbar.display(p5);
    }
    public void setMainBarButtons(PApplet p5){
        home = new ImageButtons(p5, homeIcon,homepressedIcon, 820,990,30);
        foldermainbar = new ImageButtons(p5, homefolderIcon,homefolderpressedIcon,1100, 990,30);
    }
}