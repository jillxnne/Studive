package StudiveAppQuestionaire;

import StudiveAppGUI.Button;
import StudiveAppGUI.TextArea;
import processing.core.PApplet;

/**
 * La clase {@code FlashCard} representa un conjunto de tarjetas educativas interactivas.
 * Permite crear, editar y visualizar tarjetas de preguntas y respuestas (flashcards) mediante una interfaz gráfica.
 * Puede funcionar en modo de edición o revisión.
 */
public class FlashCard {

    /** Arreglo de objetos {@link Card} que representan las tarjetas creadas. */
    public Card[] cards;

    /** Índice actual de la tarjeta mostrada. */
    int currentIndex;

    /** Indica si se debe mostrar la respuesta en lugar de la pregunta. */
    boolean showAnswer;

    /** Indica si el usuario aún debe introducir el número de tarjetas a crear. */
    public boolean awaitingNumCards;

    /** Indica si el usuario está introduciendo las preguntas. */
    public boolean enteringQuestions;

    /** Indica si el usuario está introduciendo las respuestas. */
    public boolean enteringAnswers;

    /** Indica si el sistema está en modo de edición (creación de tarjetas). */
    public boolean isEditionMode;

    /** Indica si el proceso de edición ha sido completado. */
    public boolean isCompleted;

    /** Indica si se han revisado todas las tarjetas. */
    boolean hasFinishedViewing;

    /** Campo de texto para la pregunta actual. */
    public TextArea question;

    /** Campo de texto para la respuesta actual. */
    public TextArea answer;

    /** Campo de texto para ingresar la cantidad de tarjetas a crear. */
    public TextArea numOfCards;

    /** Índice de la tarjeta que se está editando actualmente. */
    public int cardIndex;

    /** Botones para navegar, alternar, confirmar y finalizar. */
    public Button nextButton, prevButton, toggleButton, confirmButton, doneButton;

    /** Coordenadas y dimensiones del área donde se muestra la tarjeta. */
    int cardX = 450, cardY = 250, cardW = 1000, cardH = 600;

    /**
     * Constructor principal. Inicializa los campos visuales y lógicos.
     *
     * @param p5         Instancia de la aplicación Processing.
     * @param createOnly Indica si se entra en modo edición (true) o modo revisión (false).
     */
    public FlashCard(PApplet p5, boolean createOnly) {
        cards = new Card[0];
        currentIndex = 0;
        showAnswer = false;
        awaitingNumCards = true;
        enteringQuestions = false;
        enteringAnswers = false;
        hasFinishedViewing = false;
        isEditionMode = createOnly;
        isCompleted = false;
        cardIndex = 0;

        confirmButton = new Button(p5, "Confirmar", cardX + cardW / 2 - 50, 700, 100, 50);
        nextButton = new Button(p5, "Siguiente", cardX + cardW - 200, 700, 100, 50);
        prevButton = new Button(p5, "Anterior", cardX + 100, 700, 100, 40);
        toggleButton = new Button(p5, "Mostrar", cardX + cardW / 2 - 50, 700, 100, 50);
        doneButton = new Button(p5, "Finalizar", cardX + cardW / 2 - 50, 600, 100, 50);

        question = new TextArea(p5, 670, 550, 550, 40, 40, 23);
        answer = new TextArea(p5, 670, 550, 550, 40, 40, 23);
        numOfCards = new TextArea(p5, 900, 550, 100, 40, 40, 23);
    }

    /**
     * Constructor alternativo que permite crear tarjetas desde arreglos de preguntas y respuestas.
     *
     * @param p5        Instancia de Processing.
     * @param questions Arreglo de preguntas.
     * @param answers   Arreglo de respuestas correspondientes.
     */
    public FlashCard(PApplet p5, String[] questions, String[] answers) {
        this(p5, false);
        if (questions != null && questions.length > 0) {
            this.cards = new Card[questions.length];
            for (int i = 0; i < questions.length; i++) {
                cards[i] = new Card(questions[i], answers[i]);
            }
            isEditionMode = false;
        }
    }

    /**
     * Avanza a la siguiente tarjeta. Si es la última y ya se mostró la respuesta, se marca como finalizada la revisión.
     */
    void nextCard() {
        if (cards.length > 0) {
            if (currentIndex == cards.length - 1 && showAnswer) {
                hasFinishedViewing = true;
            } else {
                currentIndex = (currentIndex + 1) % cards.length;
                showAnswer = false;
            }
        }
    }

    /**
     * Retrocede a la tarjeta anterior.
     */
    void previousCard() {
        if (cards.length > 0) {
            currentIndex = (currentIndex - 1 + cards.length) % cards.length;
            showAnswer = false;
        }
    }

    /**
     * Alterna entre mostrar la pregunta y la respuesta.
     */
    void toggleAnswer() {
        showAnswer = !showAnswer;
    }

    /**
     * Muestra visualmente la tarjeta actual, ya sea en modo edición o revisión.
     *
     * @param p5 Instancia de Processing usada para dibujar en la pantalla.
     */
    public void display(PApplet p5) {
        p5.fill(255);
        p5.stroke(0);
        p5.rect(cardX, cardY, cardW, cardH, 20);

        p5.textAlign(PApplet.CENTER, PApplet.CENTER);
        p5.fill(0);
        p5.textSize(28);

        if (isEditionMode && !isCompleted) {
            confirmButton.display(p5);
            if (awaitingNumCards) {
                p5.textSize(20);
                p5.text("¿Cuántas tarjetas quieres crear?", cardX + cardW / 2, 520);
                numOfCards.display(p5);

            } else if (enteringQuestions) {
                p5.textSize(20);
                p5.text("Introduce la pregunta para la tarjeta " + (cardIndex + 1), cardX + cardW / 2, 520);
                question.display(p5);

            } else if (enteringAnswers) {
                p5.textSize(20);
                p5.text("Introduce la respuesta para la tarjeta " + (cardIndex + 1), cardX + cardW / 2, 520);
                answer.display(p5);
            }

        } else if (isCompleted) {
            p5.textSize(20);
            p5.text("¡Tarjetas creadas con éxito!", cardX + cardW / 2, 520);
            doneButton.display(p5);

        } else {
            if (!hasFinishedViewing) {
                nextButton.display(p5);
                prevButton.display(p5);
                toggleButton.display(p5);

                p5.textSize(18);
                p5.fill(255);
                if (cards.length > 0) {
                    if (showAnswer) {
                        cards[currentIndex].displayQuestion(p5, cardX + cardW / 2, cardY + cardH / 2);
                    } else {
                        cards[currentIndex].displayAnswer(p5, cardX + cardW / 2, cardY + cardH / 2);
                    }
                }

            } else {
                p5.textSize(20);
                p5.fill(0);
                p5.text("Has completado todas las flashcards.", cardX + cardW / 2, 520);
                doneButton.display(p5);
            }
        }
    }

    /**
     * Gestiona la entrada de teclado y la asigna al campo correspondiente.
     *
     * @param key     Tecla presionada.
     * @param keyCode Código de la tecla presionada.
     */
    public void keyPressed(char key, int keyCode) {
        if (awaitingNumCards) {
            numOfCards.keyPressed(key, keyCode);
        } else if (enteringQuestions) {
            question.keyPressed(key, keyCode);
        } else if (enteringAnswers) {
            answer.keyPressed(key, keyCode);
        }
    }

    /**
     * Maneja los clics del ratón sobre las áreas interactivas.
     *
     * @param p5 Instancia de Processing usada para detectar clics.
     */
    public void mousePressed(PApplet p5) {
        numOfCards.isPressed(p5);
        question.isPressed(p5);
        answer.isPressed(p5);
        confirmButton.checkClick(p5);
        doneButton.mouseOverButton(p5);

        if (!isEditionMode && cards.length > 0) {
            if (nextButton.checkClick(p5)) {
                nextCard();
                showAnswer = true;
            } else if (prevButton.checkClick(p5)) {
                previousCard();
                showAnswer = true;
            } else if (toggleButton.checkClick(p5)) {
                toggleAnswer();
            }
        }
    }

    /**
     * Devuelve un arreglo con las preguntas de todas las tarjetas creadas.
     *
     * @return Arreglo de strings con las preguntas.
     */
    public String[] getQuestions() {
        String[] questions = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            questions[i] = cards[i].question;
        }
        return questions;
    }

    /**
     * Devuelve un arreglo con las respuestas de todas las tarjetas creadas.
     *
     * @return Arreglo de strings con las respuestas.
     */
    public String[] getAnswers() {
        String[] answers = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            answers[i] = cards[i].answer;
        }
        return answers;
    }
}
