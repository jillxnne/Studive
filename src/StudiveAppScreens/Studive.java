package StudiveAppScreens;
import StudiveAppGUI.PagedCard;
import StudiveAppGUI.PagedSubject;
import StudiveAppQuestionaire.Card;
import StudiveAppQuestionaire.FlashCard;
import processing.core.PApplet;
import static StudiveAppLayout.Layout.IDwidth;
import static StudiveAppLayout.Layout.RecentLecturewidth;
import static StudiveAppScreens.GUI.*;
/**
 * Clase principal de la aplicación Studive.
 *
 * Esta clase extiende `PApplet` y actúa como controlador principal de la interfaz gráfica de usuario y
 * de la lógica de navegación entre pantallas. Coordina la interacción entre el usuario, la base de datos
 * y la interfaz proporcionada por la clase `GUI`.
 */
public class Studive extends PApplet {
    /**
     * Instancia de la clase GUI encargada de gestionar y dibujar la interfaz gráfica.
     */
    GUI gui;

    /**
     * Instancia de la clase DataBase utilizada para acceder y manipular la base de datos de usuarios, materias y tarjetas.
     */
    DataBase db;

    /**
     * Método principal que inicia la aplicación.
     * @param args Argumentos pasados por consola (no utilizados).
     */
    public static void main(String[] args) {
        PApplet.main("StudiveAppScreens.Studive", args);
    }

    /**
     * Configura el tamaño de la ventana y la calidad del renderizado.
     */
    public void settings() {
        size(1920, 1080);
        smooth(10);
    }

    /**
     * Inicializa los componentes principales: la conexión a la base de datos y la GUI.
     */
    public void setup() {
        background(255);
        db = new DataBase("admin", "12345", "studive");
        db.connect();
        gui = new GUI(this, db);
    }

    /**
     * Dibuja la pantalla correspondiente al estado actual de la interfaz.
     * Cada caso del switch representa una pantalla diferente.
     */
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
            case ADDCARD:
                gui.drawAddCards(this);
                break;
            case FLASHCARDCREATION:
                gui.drawFlashCardCreatePage(this);
                break;
            case FLASHCARDTEST:
                gui.drawFlashCardVisualizePage(this);
                break;
            case DONELESSONS:
                loadStateOfCardPage();
                gui.drawDoneLessons(this);
                break;
            case NOTDONELESSONS:
                loadStateOfCardPage();
                gui.drawNotDoneLessons(this);
                break;
            case CARDSPAGE:
                loadFlashCardPage();
                gui.drawFlashcard(this);
                break;
        }
    }

    /**
     * Maneja los eventos de teclado y los reenvía a los campos de texto activos.
     */
    public void keyPressed() {
        gui.subjecttitle.keyPressed(key, keyCode);
        gui.titleTest.keyPressed(key, keyCode);
        gui.descriptionTest.keyPressed(key, keyCode);
        gui.username.keyPressed(key,keyCode);
        gui.password.keyPressed(key,keyCode);
        if(gui.createFlashCardUI!=null) {
            gui.createFlashCardUI.keyPressed(key, keyCode);
        }
        if(gui.visualizeFlashCardUI!=null){
            gui.visualizeFlashCardUI.keyPressed(key, keyCode);
        }
    }

    /**
     * Método extenso que gestiona los clics del ratón en función de la pantalla actual.
     * Llama a las funciones adecuadas según la lógica del flujo de la aplicación:
     * iniciar sesión, registrarse, seleccionar materias, crear tarjetas, visualizar flashcards, etc.
     */
    public void mousePressed() {
        // Lógica detallada omitida aquí por extensión, ya está bien estructurada en bloques por pantalla.
        // --------------------------------------- * ACCESS PAGE * --------------------------------------- //
        if (gui.Default == SCREENS.LOGINPAGE){
            gui.password.isPressed(this);
            gui.username.isPressed(this);
            String username = gui.username.getText();
            String password = gui.password.getText();

            if (gui.login.mouseOverButton(this) && db.Login(username, password)){
                gui.Default = SCREENS.HOMEPAGE;
            } else if (gui.goToSignIn.mouseOverButton(this)){
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
            mainBarFunctions();
            gui.HomePageCard.checkCardSelection(this);
            if (gui.doneLections.mouseOverButton(this)) {
                gui.Default = SCREENS.NOTDONELESSONS;
            } else if (gui.notDoneLections.mouseOverButton(this)){
                gui.Default = SCREENS.DONELESSONS;
            } else if (gui.HomePageCard.checkMouseOver(this)) {
                gui.flashCardId = gui.HomePageCard.getSelectedCardTitle();
                gui.questions = db.getQuestionsOfAFlashCard(gui.flashCardId);
                gui.answers = db.getAnswersOfAFlashCard(gui.questions);
                gui.visualizeFlashCardUI = new FlashCard(this, gui.questions, gui.answers);
                db.updateFechaOfTest(gui.flashCardId);
                db.updateDone(gui.flashCardId);
                gui.Default = SCREENS.FLASHCARDTEST;
            }

        // ------------------------------------- * SUBJECT PAGE * ------------------------------------- //
        } else if (gui.Default == SCREENS.SUBJECTPAGE) {
            mainBarFunctions();
            gui.Subjects.checkSubjectSelection(this);
            if (gui.plus.mouseOverButton(this)){
                gui.Default = SCREENS.ADDSUBJECT;
            } else if (gui.Subjects.checkMouseOver(this)) {
                gui.Default = SCREENS.CARDSPAGE;
            } else if (gui.NextSubject.mouseOverButton(this) && gui.NextSubject.isEnabled()) {
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
                gui.subjecttitle.clear();
                gui.Default=SCREENS.SUBJECTPAGE;
            } else if (gui.subjectback.mouseOverButton(this)){
                gui.Default=SCREENS.SUBJECTPAGE;
            }

        // ------------------------------------- * CARD PAGE * ------------------------------------- //
        } else if (gui.Default == SCREENS.CARDSPAGE){
            mainBarFunctions();
            gui.pageFlashCards.checkCardSelection(this);
            if (gui.pageFlashCards != null && gui.pageFlashCards.checkMouseOver(this)) {
                gui.flashCardId = gui.pageFlashCards.getSelectedCardTitle();
                gui.questions = db.getQuestionsOfAFlashCard(gui.flashCardId);
                gui.answers = db.getAnswersOfAFlashCard(gui.questions);
                gui.visualizeFlashCardUI = new FlashCard(this, gui.questions, gui.answers);
                db.updateFechaOfTest(gui.flashCardId);
                db.updateDone(gui.flashCardId);
                gui.Default = SCREENS.FLASHCARDTEST;
            } else if ((gui.addFile != null && gui.addFile.mouseOverButton(this)) ||
                    (gui.add0File != null && gui.add0File.mouseOverButton(this))) {
                gui.Default = SCREENS.ADDCARD;
            } else if(gui.flashCardBackButton.mouseOverButton(this)){
                gui.Default = SCREENS.SUBJECTPAGE;
            }

        } else if (gui.Default == SCREENS.ADDCARD) {
            String subjectName = gui.Subjects.getSelectedSubjectTitle();
            gui.titleTest.isPressed(this);
            String title = gui.titleTest.getText();
            gui.testId = gui.titleTest.getText();
            gui.descriptionTest.isPressed(this);
            String description = gui.descriptionTest.getText();
            if (gui.createTest.mouseOverButton(this)) {
                db.insertFlashCardGenInfo(title, description, subjectName);
                gui.titleTest.clear();
                gui.descriptionTest.clear();
                gui.Default = SCREENS.FLASHCARDCREATION;
            } else if (gui.addTestTypeBackButton.mouseOverButton(this)) {
                gui.Default = SCREENS.CARDSPAGE;
            }

        // -------------------------------------- * CARD PAGE * -------------------------------------- //
        } else if (gui.Default == SCREENS.FLASHCARDCREATION) {
            gui.createFlashCardUI.mousePressed(this);
            gui.titleTest.isPressed(this);
            if (gui.createFlashCardUI.isCompleted && !gui.flashcardsInserted) {
                String[] questions = gui.createFlashCardUI.getQuestions();
                String[] answers = gui.createFlashCardUI.getAnswers();
                db.insertFlashCardInfo(questions, answers, gui.testId);
                gui.flashcardsInserted = true;
            } else if (gui.createFlashCardUI.confirmButton.checkClick(this)) {
                if (gui.createFlashCardUI.awaitingNumCards) {
                    try {
                        int num = Integer.parseInt(gui.createFlashCardUI.numOfCards.getText());
                        gui.createFlashCardUI.numOfCards.clear();
                        if (num > 0) {
                            gui.createFlashCardUI.cards = new Card[num];
                            gui.createFlashCardUI.awaitingNumCards = false;
                            gui.createFlashCardUI.enteringQuestions = true;
                        }
                    } catch (Exception ignored) {}
                } else if (gui.createFlashCardUI.enteringQuestions) {
                    String q = gui.createFlashCardUI.question.getText();
                    gui.createFlashCardUI.question.clear();
                    if (!q.isEmpty()) {
                        gui.createFlashCardUI.cards[gui.createFlashCardUI.cardIndex] = new Card(q, "");
                        gui.createFlashCardUI.enteringQuestions = false;
                        gui.createFlashCardUI.enteringAnswers = true;
                    }
                } else if (gui.createFlashCardUI.enteringAnswers) {
                    String a = gui.createFlashCardUI.answer.getText();
                    gui.createFlashCardUI.answer.clear();
                    if (!a.isEmpty()) {
                        gui.createFlashCardUI.cards[gui.createFlashCardUI.cardIndex].answer = a;
                        gui.createFlashCardUI.cardIndex++;
                        if (gui.createFlashCardUI.cardIndex >= gui.createFlashCardUI.cards.length) {
                            gui.createFlashCardUI.enteringAnswers = false;
                            gui.createFlashCardUI.isCompleted = true;
                            gui.createFlashCardUI.isEditionMode = false;
                            gui.createFlashCardUI.cardIndex = 0;
                        } else {
                            gui.createFlashCardUI.enteringQuestions = true;
                        }
                    }
                }
            } else if (gui.createFlashCardUI.doneButton.mouseOverButton(this) && gui.createFlashCardUI.isCompleted){
                gui.Default = SCREENS.CARDSPAGE;
            }
        } else if (gui.Default == SCREENS.FLASHCARDTEST) {
            gui.visualizeFlashCardUI.mousePressed(this);
            if (gui.visualizeFlashCardUI.doneButton.mouseOverButton(this)){
                gui.Default = SCREENS.CARDSPAGE;
            }

        // ----------------------------------- * (NOT) DONE PAGE * ----------------------------------- //
        } else if (gui.Default == SCREENS.NOTDONELESSONS){
            mainBarFunctions();
            gui.NotDonePageCard.checkCardSelection(this);
            if(gui.stateOfLessonBackButton.mouseOverButton(this)){
                gui.Default = SCREENS.HOMEPAGE;
            } else if (gui.NotDonePageCard.checkMouseOver(this)) {
                gui.flashCardId = gui.NotDonePageCard.getSelectedCardTitle();
                gui.questions = db.getQuestionsOfAFlashCard(gui.flashCardId);
                gui.answers = db.getAnswersOfAFlashCard(gui.questions);
                gui.visualizeFlashCardUI = new FlashCard(this, gui.questions, gui.answers);
                db.updateFechaOfTest(gui.flashCardId);
                db.updateDone(gui.flashCardId);
                gui.Default = SCREENS.FLASHCARDTEST;
            } else if (gui.stateOfLessonsNext.mouseOverButton(this) && gui.stateOfLessonsNext.isEnabled()) {
                gui.NotDonePageCard.nextPage();
            } else if (gui.stateOfLessonsPrev.mouseOverButton(this) && gui.stateOfLessonsPrev.isEnabled()) {
                gui.NotDonePageCard.prevPage();
            } else {
                gui.NotDonePageCard.checkCardSelection(this);
            }

        } else if (gui.Default == SCREENS.DONELESSONS){
            mainBarFunctions();
            gui.DonePageCard.checkCardSelection(this);
            if(gui.stateOfLessonBackButton.mouseOverButton(this)){
                gui.Default = SCREENS.HOMEPAGE;
            } else if (gui.DonePageCard.checkMouseOver(this)) {
                gui.flashCardId = gui.DonePageCard.getSelectedCardTitle();
                gui.questions = db.getQuestionsOfAFlashCard(gui.flashCardId);
                gui.answers = db.getAnswersOfAFlashCard(gui.questions);
                gui.visualizeFlashCardUI = new FlashCard(this, gui.questions, gui.answers);
                db.updateFechaOfTest(gui.flashCardId);
                db.updateDone(gui.flashCardId);
                gui.Default = SCREENS.FLASHCARDTEST;
            } else if (gui.stateOfLessonsNext.mouseOverButton(this) && gui.stateOfLessonsNext.isEnabled()) {
                gui.DonePageCard.nextPage();
            } else if (gui.stateOfLessonsPrev.mouseOverButton(this) && gui.stateOfLessonsPrev.isEnabled()) {
                gui.DonePageCard.prevPage();
            } else {
                gui.DonePageCard.checkCardSelection(this);
            }
        }
    }

    /**
     * Funciones de navegación accesibles desde la barra inferior común a varias pantallas.
     */
    public void mainBarFunctions(){
        if (gui.home.mouseOverButton(this)) {
            gui.Default = SCREENS.HOMEPAGE;
        } else if (gui.foldermainbar.mouseOverButton(this)){
            gui.Default = SCREENS.SUBJECTPAGE;
        }
    }

    /**
     * Carga la información de materias desde la base de datos y la asocia a la interfaz de materias.
     */
    public void loadSubjectPage(){
        gui.subjectsInfo = db.getSubjectsInfo();
        gui.Subjects = new PagedSubject(4);
        gui.Subjects.setDimensions(535,160, RecentLecturewidth+300, 700);
        gui.subjectsInfo = db.getSubjectsInfo();
        gui.Subjects.setData(gui.subjectsInfo);
        gui.Subjects.setSubjects(gui.mainfolderIcon);
    }

    /**
     * Carga la información de flashcards de la materia seleccionada y la asocia a la interfaz.
     */
    public void loadFlashCardPage() {
        gui.subjectName = gui.Subjects.getSelectedSubjectTitle();
        gui.flashcardsInfo = db.getTotalOfTypeOfLessonOfASubject(gui.subjectName);
        gui.pageFlashCards = new PagedCard(4);
        gui.pageFlashCards.setDimensions(IDwidth-400,250, RecentLecturewidth+100, 620);
        gui.flashcardsInfo = db.getTotalOfTypeOfLessonOfASubject(gui.subjectName);
        gui.pageFlashCards.setData(gui.flashcardsInfo);
        gui.pageFlashCards.setCards();gui.pageFlashCards.setImages(gui.flashcardIcon);
    }

    /**
     * Carga las flashcards categorizadas como hechas y no hechas desde la base de datos.
     * Asocia los datos cargados a los componentes gráficos correspondientes.
     */
    public void loadStateOfCardPage(){
        gui.doneTests = db.getTotalOfStateOfTests(1);
        gui.DonePageCard = new PagedCard(5);
        gui.DonePageCard.setDimensions(535,160, RecentLecturewidth+300, 700);
        gui.doneTests = db.getTotalOfStateOfTests(1);
        gui.DonePageCard.setData(gui.doneTests);
        gui.DonePageCard.setCards();
        gui.DonePageCard.setImages(gui.flashcardIcon);

        gui.notDoneTests = db.getTotalOfStateOfTests(0);
        gui.NotDonePageCard = new PagedCard(5);
        gui.NotDonePageCard.setDimensions(535,160, RecentLecturewidth+300, 700);
        gui.notDoneTests = db.getTotalOfStateOfTests(0);
        gui.NotDonePageCard.setData(gui.notDoneTests);
        gui.NotDonePageCard.setCards();
        gui.NotDonePageCard.setImages(gui.flashcardIcon);
    }
}
