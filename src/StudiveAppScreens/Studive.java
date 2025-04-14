package StudiveAppScreens;
import StudiveAppGUI.PagedCard;
import StudiveAppGUI.PagedSubject;
import StudiveAppQuestionaire.FlashCard;
import StudiveAppQuestionaire.MultipleChoiceQuiz;
import processing.core.PApplet;

import javax.xml.crypto.Data;

import static StudiveAppLayout.Layout.IDwidth;
import static StudiveAppLayout.Layout.RecentLecturewidth;
import static StudiveAppScreens.GUI.*;

public class Studive extends PApplet {
    GUI gui;
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
        db = new DataBase("admin", "12345", "studive");
        db.connect();

        gui = new GUI(this, db);
    }

    public void draw() {
        switch (gui.Default) {
            case SIGNINPAGE:
                gui.drawSignInPage(this);
                break;
            case LOGINPAGE:
                gui.drawLoginPage(this);
                break;
            case HOMEPAGE:
                gui.drawHomePage(this);
                break;
            case SUBJECTPAGE:
                gui.drawSubjectPage(this);
                break;
            case ADDSUBJECT:
                gui.drawAddSubject(this);
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
            case FLASHCARDCREATION:
                gui.drawFlashCardCreatePage(this);
                break;
            case FLASHCARDTEST:
                gui.drawFlashCardVisualizePage(this);
                break;
            case TESTCREATION:
                gui.drawTestCreatePage(this);
                break;
            case TEST:
                gui.drawTestVisualizePage(this);
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
                loadTypeOfDocOrTestPage();
                gui.drawPDF(this);
                break;
            case LINKSPAGE:
                loadTypeOfDocOrTestPage();
                gui.drawLink(this);
                break;
            case QUIZPAGE:
                loadTypeOfDocOrTestPage();
                gui.drawTest(this);
                break;
            case CARDSPAGE:
                loadTypeOfDocOrTestPage();
                gui.drawFlashcard(this);
                break;
        }
    }
    public void keyPressed() {
        gui.Lection.keyPressed(key, keyCode);
        gui.namechange.keyPressed(key, keyCode);
        gui.subjecttitle.keyPressed(key, keyCode);
        gui.titleTest.keyPressed(key, keyCode);
        gui.descriptionTest.keyPressed(key, keyCode);
        gui.titleDoc.keyPressed(key, keyCode);
        gui.descriptionDoc.keyPressed(key, keyCode);
        gui.username.keyPressed(key,keyCode);
        gui.password.keyPressed(key,keyCode);
        if(gui.createFlashCardUI!=null) {
            gui.createFlashCardUI.keyPressed(key, keyCode);
        }
        if(gui.createTestUI!=null) {
            gui.createTestUI.keyPressed(key, keyCode);
        }
        if(gui.visualiveTestUI!=null){
            gui.visualiveTestUI.keyPressed(key, keyCode);
        }
        if(gui.visualiveTestUI!=null){
            gui.visualiveTestUI.keyPressed(key,keyCode);
        }
        if (key =='0'){
            gui.Default = SCREENS.LOGINPAGE;
        }
    }

    public void mousePressed() {
        // --------------------------------------- * ACCESS PAGE * --------------------------------------- //
        if (gui.Default == SCREENS.LOGINPAGE){
            gui.password.isPressed(this);
            gui.username.isPressed(this);

            String username = gui.username.getText();
            String password = gui.password.getText();

            if (gui.login.mouseOverButton(this) && db.Login(username, password)){
                gui.Default = SCREENS.HOMEPAGE;
                gui.username.clear();
                gui.password.clear();
            }

            if (gui.goToSignIn.mouseOverButton(this)){
                gui.Default = SCREENS.SIGNINPAGE;
            }
        }
        else if (gui.Default == SCREENS.SIGNINPAGE) {
            gui.username.isPressed(this);
            gui.password.isPressed(this);
            String username = gui.username.getText();
            String password = gui.password.getText();
            if (gui.signIn.mouseOverButton(this)){
                db.insertNewUser(username, password);
                gui.username.clear();
                gui.password.clear();
                gui.Default = SCREENS.LOGINPAGE;
            }
        }

        // --------------------------------------- * HOME PAGE * --------------------------------------- //
        else if (gui.Default == SCREENS.HOMEPAGE) {
            mainBarFunctions(this);
            if (gui.doneLections.mouseOverButton(this)) {
                gui.Default = SCREENS.NOTDONELESSONS;
            }
            if (gui.notDoneLections.mouseOverButton(this)){
                gui.Default = SCREENS.DONELESSONS;
            }
            if (gui.HomePageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            // ------------------------------------- * SUBJECT PAGE * ------------------------------------- //
        } else if (gui.Default == SCREENS.SUBJECTPAGE) {
            mainBarFunctions(this);
            if (gui.plus.mouseOverButton(this)){
                gui.Default = SCREENS.ADDSUBJECT;
            }
            if (gui.Subjects.checkMouseOver(this)) {
                gui.Default = SCREENS.PDFPAGE;
            }
            if (gui.NextSubject.mouseOverButton(this) && gui.NextSubject.isEnabled()) {
                gui.Subjects.nextPage();
            } else if (gui.PreviousSubject.mouseOverButton(this) && gui.PreviousSubject.isEnabled()) {
                gui.Subjects.prevPage();
            } else {
                gui.Subjects.checkSubjectSelection(this);
            }

        } else if (gui.Default == SCREENS.ADDSUBJECT){
            gui.subjecttitle.isPressed(this);
            gui.colorss.checkMouseOver(this,800,520,300);
            String titleSubject = gui.subjecttitle.getText();
            String color = gui.colorss.getSelectedColorAsString(this);

            if (gui.create.mouseOverButton(this) && (!titleSubject.equals("") && !color.equals(""))){
                db.insertSubject(titleSubject,color);
                loadSubjectPage();
                gui.Default=SCREENS.SUBJECTPAGE;
            }
            if (gui.subjectback.mouseOverButton(this)){
                gui.Default=SCREENS.SUBJECTPAGE;
            }

            // ----------------------------------- * DOCS / TEST PAGE * ----------------------------------- //
        } else if (gui.Default == SCREENS.PDFPAGE){
            upperBarFunctions(this);
            if (gui.pagePDF != null && gui.pagePDF.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }
            if ((gui.addFile != null && gui.addFile.mouseOverButton(this)) ||
                    (gui.add0File != null && gui.add0File.mouseOverButton(this))) {
                gui.Default = SCREENS.ADDPDF;
            }

        } else if (gui.Default == SCREENS.QUIZPAGE){
            upperBarFunctions(this);
            gui.pageTest.checkCardSelection(this);
            if (gui.pageTest != null && gui.pageTest.checkMouseOver(this)) {
                loadVisualizeTest();
                gui.Default = SCREENS.TEST;
            }
            if ((gui.addFile != null && gui.addFile.mouseOverButton(this)) ||
                    (gui.add0File != null && gui.add0File.mouseOverButton(this))) {
                gui.Default = SCREENS.ADDQUIZ;
            }

        } else if (gui.Default == SCREENS.LINKSPAGE){
            upperBarFunctions(this);
            if (gui.pageLink != null && gui.pageLink.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }
            if ((gui.addFile != null && gui.addFile.mouseOverButton(this)) ||
                    (gui.add0File != null && gui.add0File.mouseOverButton(this))) {
                gui.Default = SCREENS.ADDLINK;
            }

        } else if (gui.Default == SCREENS.CARDSPAGE){
            upperBarFunctions(this);
            gui.pageFlashCards.checkCardSelection(this);
            if (gui.pageFlashCards != null && gui.pageFlashCards.checkMouseOver(this)) {
                loadVisualizeFlashcard();
                gui.Default = SCREENS.FLASHCARDTEST;
            }
            if ((gui.addFile != null && gui.addFile.mouseOverButton(this)) ||
                    (gui.add0File != null && gui.add0File.mouseOverButton(this))) {
                gui.Default = SCREENS.ADDCARD;
            }

        } else if (gui.Default == SCREENS.ADDPDF){
            String subjectName = gui.Subjects.getSelectedSubjectTitle();
            gui.titleDoc.isPressed(this);
            String title = gui.titleDoc.getText();
            gui.descriptionDoc.isPressed(this);
            String description = gui.descriptionDoc.getText();
            String URL = " ";
            upperBarFunctions(this);
            if (gui.createDoc.mouseOverButton(this)){
                db.insertDocument(title,description,URL,subjectName,"PDF");
                gui.titleDoc.clear();
                gui.descriptionDoc.clear();
            }
            if (gui.addDocTypeBackButton.mouseOverButton(this)){
                gui.Default=SCREENS.PDFPAGE;
            }

        } else if (gui.Default == SCREENS.ADDQUIZ){
            String subjectName = gui.Subjects.getSelectedSubjectTitle();
            gui.titleTest.isPressed(this);
            String title = gui.titleTest.getText();
            gui.testId = gui.titleTest.getText();
            gui.descriptionTest.isPressed(this);
            String description = gui.descriptionTest.getText();
            upperBarFunctions(this);
            if (gui.createTest.mouseOverButton(this)){
                db.insertTestOrFlashCardGenInfo(title,description,subjectName,"TEST");
                gui.titleTest.clear();
                gui.descriptionTest.clear();
                gui.Default=SCREENS.TESTCREATION;
            }
            if (gui.addTestTypeBackButton.mouseOverButton(this)){
                gui.Default=SCREENS.QUIZPAGE;
            }

        } else if (gui.Default == SCREENS.ADDLINK){
            String subjectName = gui.Subjects.getSelectedSubjectTitle();
            gui.titleDoc.isPressed(this);
            String title = gui.titleDoc.getText();
            gui.descriptionDoc.isPressed(this);
            String description = gui.descriptionDoc.getText();
            String URL = " ";
            upperBarFunctions(this);
            if (gui.createDoc.mouseOverButton(this)){
                db.insertDocument(title, description,URL, subjectName, "LINK");
                gui.titleDoc.clear();
                gui.descriptionDoc.clear();
            }
            if (gui.addDocTypeBackButton.mouseOverButton(this)){
                gui.Default=SCREENS.LINKSPAGE;
            }

        } else if (gui.Default == SCREENS.ADDCARD) {
            String subjectName = gui.Subjects.getSelectedSubjectTitle();
            gui.titleTest.isPressed(this);
            String title = gui.titleTest.getText();
            gui.testId = gui.titleTest.getText();
            gui.descriptionTest.isPressed(this);
            String description = gui.descriptionTest.getText();
            if (gui.createTest.mouseOverButton(this)) {
                db.insertTestOrFlashCardGenInfo(title, description, subjectName, "FLASHCARD");
                gui.titleTest.clear();
                gui.descriptionTest.clear();
                gui.Default = SCREENS.FLASHCARDCREATION;
            }
            if (gui.addTestTypeBackButton.mouseOverButton(this)) {
                gui.Default = SCREENS.CARDSPAGE;
            }
            // ----------------------------------- * TEST / CARD PAGE * ----------------------------------- //
        } else if (gui.Default == SCREENS.FLASHCARDCREATION) {
            gui.createFlashCardUI.mousePressed(this);
            gui.titleTest.isPressed(this);
                if (gui.createFlashCardUI.isCompleted && !gui.flashcardsInserted) {
                    String[] questions = gui.createFlashCardUI.getQuestions();
                    String[] answers = gui.createFlashCardUI.getAnswers();
                    db.insertFlashCardInfo(questions, answers, gui.testId);
                    gui.flashcardsInserted = true;
                }
            if (gui.createFlashCardUI.doneButton.mouseOverButton(this)){
                gui.Default = SCREENS.CARDSPAGE;
            }

        } else if (gui.Default == SCREENS.FLASHCARDTEST) {
            gui.visualizeFlashCardUI.mousePressed(this);
            if (gui.visualizeFlashCardUI.doneButton.mouseOverButton(this)){
                gui.Default = SCREENS.CARDSPAGE;
            }

        } else if (gui.Default == SCREENS.TESTCREATION) {
            gui.createTestUI.mousePressed(this);
            gui.titleTest.isPressed(this);
            if (gui.createTestUI.TestDone && !gui.testInserted) {
                String[] questions = gui.createTestUI.getQuestions();
                String[] answers = gui.createTestUI.getAnswers();
                int[] indexes = gui.createTestUI.getCorrectIndexes();
                db.insertTestData(gui.testId, questions, answers, indexes);
                gui.testInserted = true;
            }

            if (gui.createTestUI.TestDone && gui.createTestUI.finishButton.mouseOverButton(this)) {
                gui.Default = SCREENS.QUIZPAGE;
            }
        }
        else if (gui.Default == SCREENS.TEST){
            gui.visualiveTestUI.mousePressed(this);
            if (gui.visualiveTestUI.finishButton.mouseOverButton(this)){
                gui.Default = SCREENS.QUIZPAGE;
            }

            // ----------------------------------- * (NOT) DONE PAGE * ----------------------------------- //
        } else if (gui.Default == SCREENS.NOTDONELESSONS){
            mainBarFunctions(this);
            if (gui.NotDonePageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }

            if (gui.stateOfLessonsNext.mouseOverButton(this) && gui.stateOfLessonsNext.isEnabled()) {
                gui.NotDonePageCard.nextPage();
            } else if (gui.stateOfLessonsPrev.mouseOverButton(this) && gui.stateOfLessonsPrev.isEnabled()) {
                gui.NotDonePageCard.prevPage();
            } else {
                gui.NotDonePageCard.checkCardSelection(this);
            }

        } else if (gui.Default == SCREENS.DONELESSONS){
            mainBarFunctions(this);
            if (gui.DonePageCard.checkMouseOver(this)) {
                gui.Default = SCREENS.MAINLESSONS;
            }
            if (gui.stateOfLessonsNext.mouseOverButton(this) && gui.stateOfLessonsNext.isEnabled()) {
                gui.DonePageCard.nextPage();
            } else if (gui.stateOfLessonsPrev.mouseOverButton(this) && gui.stateOfLessonsPrev.isEnabled()) {
                gui.DonePageCard.prevPage();
            } else {
                gui.DonePageCard.checkCardSelection(this);
            }

            // ----------------------------- * SPECIFIC LECTION INFO PAGE * ----------------------------- //
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
                if (gui.done.isChecked()){
                    // db.updateDone();
                }
            }
            if (gui.accessResults.mouseOverButton(this)) {
                gui.Default = SCREENS.MAINSTATISTICS;
            }
            if (gui.mainlessonback.mouseOverButton(this)) {
                gui.Default=SCREENS.PDFPAGE;
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
        if (gui.statisticback.mouseOverButton(this)){
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
            gui.Default = SCREENS.SUBJECTPAGE;
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
    public void loadSubjectPage(){
        gui.subjectsInfo = db.getSubjectsInfo();
        gui.Subjects = new PagedSubject(4);
        gui.Subjects.setDimensions(535,160, RecentLecturewidth+300, 700);
        gui.subjectsInfo = db.getSubjectsInfo();
        gui.Subjects.setData(gui.subjectsInfo);
        gui.Subjects.setSubjects(gui.mainfolderIcon);
    }
    public void loadTypeOfDocOrTestPage() {
        String selectedSubject = gui.Subjects.getSelectedSubjectTitle();

        if (!selectedSubject.equals(gui.lastSubjectLoaded)) {
            gui.lastSubjectLoaded = selectedSubject;
            gui.subjectName = selectedSubject;

            gui.pdfInfo = db.getTotalOfTypeOfLessonOfASubject("PDF", selectedSubject);
            gui.pagePDF = new PagedCard(4);
            gui.pagePDF.setDimensions(IDwidth - 400, 250, RecentLecturewidth + 100, 620);
            gui.pagePDF.setData(gui.pdfInfo);
            gui.pagePDF.setCards();
            gui.pagePDF.setImages(gui.flashcardIcon, gui.pdfIcon, gui.linkIcon, gui.testIcon);

            gui.flashcardsInfo = db.getTotalOfTypeOfLessonOfASubject("FLASHCARD", selectedSubject);
            gui.pageFlashCards = new PagedCard(4);
            gui.pageFlashCards.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
            gui.pageFlashCards.setData(gui.flashcardsInfo);
            gui.pageFlashCards.setCards();
            gui.pageFlashCards.setImages(gui.flashcardIcon, gui.pdfIcon, gui.linkIcon,gui.testIcon);

            gui.linkInfo = db.getTotalOfTypeOfLessonOfASubject("LINK", selectedSubject);
            gui.pageLink = new PagedCard(4);
            gui.pageLink.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
            gui.pageLink.setData(gui.linkInfo);
            gui.pageLink.setCards();
            gui.pageLink.setImages(gui.flashcardIcon, gui.pdfIcon, gui.linkIcon,gui.testIcon);

            gui.testInfo = db.getTotalOfTypeOfLessonOfASubject("TEST", selectedSubject);
            gui.pageTest = new PagedCard(4);
            gui.pageTest.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
            gui.pageTest.setData(gui.testInfo);
            gui.pageTest.setCards();
            gui.pageTest.setImages(gui.flashcardIcon, gui.pdfIcon, gui.linkIcon, gui.testIcon);
        }
    }

    public void loadVisualizeFlashcard(){
        gui.flashCardId = gui.pageFlashCards.getSelectedCardTitle();
        gui.questions = db.getQuestionsOfAFlashCard(gui.flashCardId);
        gui.answers = db.getAnswersOfAFlashCard(gui.questions);
        gui.visualizeFlashCardUI = new FlashCard(this, gui.questions, gui.answers);
    }

    public void loadVisualizeTest(){
        gui.questionaireID = gui.pageTest.getSelectedCardTitle();
        gui.questions = db.getQuestionsOfMultipleChoiceTest(gui.flashCardId);
        gui.answers = db.getAnswersOfMultipleChoiceTest(gui.questions);
        gui.correctIndex = db.getCorrectAnswerIndexesOfMultipleChoiceTest(gui.questions);
        gui.visualiveTestUI = new MultipleChoiceQuiz(this,gui.questions,gui.questions,gui.correctIndex);
    }

}
