package StudiveAppQuestionaire;

import StudiveAppGUI.Button;
import StudiveAppGUI.TextArea;
import processing.core.PApplet;

public class FlashCard {
    Card[] cards;
    int currentIndex;
    boolean showAnswer;
    boolean awaitingNumCards;
    boolean enteringQuestions;
    boolean enteringAnswers;
    boolean isEditionMode;
    public boolean isCompleted;
    boolean hasFinishedViewing;
    public TextArea question, answer, numOfCards;
    int cardIndex;

    public Button nextButton, prevButton, toggleButton, confirmButton, doneButton;

    int cardX = 450, cardY = 250, cardW = 1000, cardH = 600;

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
        doneButton = new Button(p5, "Finalizar", cardX + cardW / 2 - 50, 700, 100, 50);

        question = new TextArea(p5, 670, 550, 550, 40, 40, 23);
        answer = new TextArea(p5, 670, 550, 550, 40, 40, 23);
        numOfCards = new TextArea(p5, 900, 550, 100, 40, 40, 23);
    }

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

    void previousCard() {
        if (cards.length > 0) {
            currentIndex = (currentIndex - 1 + cards.length) % cards.length;
            showAnswer = false;
        }
    }

    void toggleAnswer() {
        showAnswer = !showAnswer;
    }

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
            p5.text("¡Tarjetas creadas con éxito!", cardX + cardW / 2, cardY + cardH / 2);
            doneButton.display(p5);

        } else {
            if (!hasFinishedViewing) {
                nextButton.display(p5);
                prevButton.display(p5);
                toggleButton.display(p5);

                p5.textSize(18);
                p5.fill(255);
                if (cards.length > 0) {
                    if (!showAnswer) {
                        cards[currentIndex].displayQuestion(p5, cardX + cardW / 2, cardY + cardH / 2);
                    } else {
                        cards[currentIndex].displayAnswer(p5, cardX + cardW / 2, cardY + cardH / 2);
                    }
                }

            } else {
                p5.textSize(20);
                p5.fill(0);
                p5.text("Has completado todas las flashcards.", cardX + cardW / 2, cardY + 50);
                doneButton.display(p5);
            }
        }
    }

    public void keyPressed(char key, int keyCode) {
        if (awaitingNumCards) {
            numOfCards.keyPressed(key, keyCode);
        } else if (enteringQuestions) {
            question.keyPressed(key, keyCode);
        } else if (enteringAnswers) {
            answer.keyPressed(key, keyCode);
        }
    }

    public void mousePressed(PApplet p5) {
        numOfCards.isPressed(p5);
        question.isPressed(p5);
        answer.isPressed(p5);

        if (confirmButton.checkClick(p5)) {
            if (awaitingNumCards) {
                try {
                    int num = Integer.parseInt(numOfCards.getText());
                    numOfCards.clear();
                    if (num > 0) {
                        cards = new Card[num];
                        awaitingNumCards = false;
                        enteringQuestions = true;
                    }
                } catch (Exception ignored) {}
            } else if (enteringQuestions) {
                String q = question.getText();
                question.clear();
                if (!q.isEmpty()) {
                    cards[cardIndex] = new Card(q, "");
                    enteringQuestions = false;
                    enteringAnswers = true;
                }
            } else if (enteringAnswers) {
                String a = answer.getText();
                answer.clear();
                if (!a.isEmpty()) {
                    cards[cardIndex].answer = a;
                    cardIndex++;
                    if (cardIndex >= cards.length) {
                        enteringAnswers = false;
                        isCompleted = true;
                        isEditionMode = false;
                        cardIndex = 0;
                    } else {
                        enteringQuestions = true;
                    }
                }
            }
        }

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

    public String[] getQuestions() {
        String[] questions = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            questions[i] = cards[i].question;
        }
        return questions;
    }

    public String[] getAnswers() {
        String[] answers = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            answers[i] = cards[i].answer;
        }
        return answers;
    }
}
