package StudiveAppQuestionaire;
import processing.core.PApplet;

public class Flashcards {
    Card[] cards;
    int currentIndex;
    boolean showAnswer;
    boolean awaitingNumCards;
    boolean enteringQuestions;
    boolean enteringAnswers;
    String currentQuestion;
    String currentAnswer;
    int cardIndex;
    Button nextButton, prevButton, toggleButton, confirmButton;

    public Flashcards() {
        cards = new Card[0]; // Comenzamos con un array vacío
        currentIndex = 0;
        showAnswer = false;
        awaitingNumCards = true;
        enteringQuestions = false;
        enteringAnswers = false;
        currentQuestion = "";
        currentAnswer = "";
        cardIndex = 0;
        nextButton = new Button(300, 500, 100, 40, "Siguiente");
        prevButton = new Button(100, 500, 100, 40, "Anterior");
        toggleButton = new Button(500, 500, 100, 40, "Mostrar/Ocultar");
        confirmButton = new Button(350, 400, 100, 40, "Confirmar");
    }

    void addCard(String question, String answer) {
        if (cards.length == 0 || cardIndex < cards.length) {
            cards[cardIndex] = new Card(question, answer);
        } else {
            Card[] newCards = new Card[cards.length + 1];
            for (int i = 0; i < cards.length; i++) {
                newCards[i] = cards[i];
            }
            newCards[cards.length] = new Card(question, answer);
            cards = newCards;
        }
    }

    void nextCard() {
        if (cards.length > 0) {
            currentIndex = (currentIndex + 1) % cards.length;
            showAnswer = false;
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

    void handleInput(PApplet app) {
        if (awaitingNumCards) {
            if (app.key == PApplet.ENTER && !currentQuestion.isEmpty()) {
                try {
                    int numCards = Integer.parseInt(currentQuestion);
                    if (numCards > 0) {
                        cards = new Card[numCards];
                        awaitingNumCards = false;
                        enteringQuestions = true;
                    }
                } catch (NumberFormatException e) {
                }
            }
        } else if (enteringQuestions) {
            if (app.key == PApplet.ENTER && !currentQuestion.isEmpty()) {
                addCard(currentQuestion, "");
                currentAnswer = "";
                enteringQuestions = false;
                enteringAnswers = true;
                currentQuestion = "";
            }
        } else if (enteringAnswers) {
            if (app.key == PApplet.ENTER && !currentAnswer.isEmpty()) {
                cards[cardIndex].answer = currentAnswer;
                cardIndex++;

                if (cardIndex == cards.length) {
                    enteringAnswers = false;
                } else {
                    currentAnswer = "";
                    enteringQuestions = true;
                }
            }
        }
    }

    public void display(PApplet app) {
        if (awaitingNumCards || enteringQuestions || enteringAnswers) {
            confirmButton.display(app);
        } else {
            nextButton.display(app);
            prevButton.display(app);
            toggleButton.display(app);
        }

        if (awaitingNumCards) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("¿Cuántas tarjetas quieres crear?", app.width / 2, app.height / 2 - 50);
            app.textSize(18);
            app.text("Número de tarjetas: " + currentQuestion, app.width / 2, app.height / 2);
        } else if (enteringQuestions) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("Introduce la pregunta para la tarjeta " + (cardIndex + 1), app.width / 2, app.height / 2 - 50);
            app.textSize(18);
            app.text("Pregunta: " + currentQuestion, app.width / 2, app.height / 2);
        } else if (enteringAnswers) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("Introduce la respuesta para la tarjeta " + (cardIndex + 1), app.width / 2, app.height / 2 - 50);
            app.textSize(18);
            app.text("Respuesta: " + currentAnswer, app.width / 2, app.height / 2);
        } else {
            app.fill(255);
            app.stroke(0);
            app.rect(100, 150, 600, 300, 20);

            if (cards.length == 0) {
                app.textAlign(PApplet.CENTER, PApplet.CENTER);
                app.textSize(20);
                app.fill(0);
                app.text("No hay tarjetas creadas", 400, 250);
                return;
            }

            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(18);
            app.fill(0);

            if (showAnswer) {
                cards[currentIndex].displayAnswer(app, 400, 250);
            } else {
                cards[currentIndex].displayQuestion(app, 400, 250);
            }
        }
    }

    void keyPressed(PApplet app) {
        if (awaitingNumCards) {
            if (Character.isDigit(app.key)) {
                currentQuestion += app.key;
            } else if (app.key == PApplet.BACKSPACE && currentQuestion.length() > 0) {
                currentQuestion = currentQuestion.substring(0, currentQuestion.length() - 1);
            }
        } else if (enteringQuestions) {
            if (app.key != PApplet.ENTER) {
                if (currentQuestion.length() < 30 && app.key != PApplet.BACKSPACE) {
                    currentQuestion += app.key;
                } else if (app.key == PApplet.BACKSPACE && currentQuestion.length() > 0) {
                    currentQuestion = currentQuestion.substring(0, currentQuestion.length() - 1);
                }
            }
        } else if (enteringAnswers) {
            if (app.key != PApplet.ENTER) {
                if (currentAnswer.length() < 30 && app.key != PApplet.BACKSPACE) {
                    currentAnswer += app.key;
                } else if (app.key == PApplet.BACKSPACE && currentAnswer.length() > 0) {
                    currentAnswer = currentAnswer.substring(0, currentAnswer.length() - 1);
                }
            }
        }
    }

    void mousePressed(PApplet app) {
        if (confirmButton.checkClick(app)) {
            if (awaitingNumCards) {
                try {
                    int numCards = Integer.parseInt(currentQuestion);
                    if (numCards > 0) {
                        cards = new Card[numCards];
                        awaitingNumCards = false;
                        enteringQuestions = true;
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input
                }
            } else if (enteringQuestions) {
                addCard(currentQuestion, "");
                currentQuestion = "";
                enteringQuestions = false;
                enteringAnswers = true;
            } else if (enteringAnswers) {
                cards[cardIndex].answer = currentAnswer;
                cardIndex++;

                if (cardIndex == cards.length) {
                    enteringAnswers = false;
                } else {
                    currentAnswer = "";
                    enteringQuestions = true;
                }
            }
        } else if (nextButton.checkClick(app)) {
            nextCard();
        } else if (prevButton.checkClick(app)) {
            previousCard();
        } else if (toggleButton.checkClick(app)) {
            toggleAnswer();
        }
    }
}