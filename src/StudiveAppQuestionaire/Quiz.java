package StudiveAppQuestionaire;
import processing.core.PApplet;
import StudiveAppGUI.TextArea;
import StudiveAppGUI.Button;

public class Quiz {
    Question[] questions;
    int currentQuestionIndex;
    boolean awaitingNumQuestions;
    boolean enteringQuestions;
    boolean enteringAnswers;
    boolean enteringCorrectIndex;
    int numQuestions;
    int currentAnswerIndex;
    int correctAnswers;
    int incorrectAnswers;
    String currentQuestion;
    String[] currentAnswers;
    int correctAnswerIndex;
    int selectedAnswerIndex = -1;

    TextArea inputNumQuestions, inputQuestion, inputAnswer, inputCorrectIndex;
    Button saveButton, nextButton, checkButton;
    Button[] answerButtons;

    int cardX = 300, cardY = 200, cardW = 1350, cardH = 700;

    // Constructor
    public Quiz(PApplet app) {
        questions = null;
        currentQuestionIndex = 0;
        awaitingNumQuestions = true;
        enteringQuestions = false;
        enteringAnswers = false;
        enteringCorrectIndex = false;
        numQuestions = 0;
        currentAnswerIndex = 0;
        correctAnswers = 0;
        incorrectAnswers = 0;
        currentQuestion = "";
        currentAnswers = new String[3];
        for (int i = 0; i < 3; i++) currentAnswers[i] = "";
        correctAnswerIndex = -1;

        inputNumQuestions = new TextArea(app, cardX + 475, cardY + 120, 400, 60, 20, 1);
        inputQuestion = new TextArea(app, cardX + 275, cardY + 120, 800, 80, 40, 2);
        inputAnswer = new TextArea(app, cardX + 275, cardY + 120, 800, 80, 40, 2);
        inputCorrectIndex = new TextArea(app, cardX + 475, cardY + 120, 400, 60, 20, 1);

        saveButton = new Button(app, "Guardar", cardX + cardW / 2 - 75, cardY + 250, 150, 50);
        nextButton = new Button(app, "Siguiente", cardX + cardW / 2 - 75, cardY + 310, 150, 50);
        checkButton = new Button(app, "Comprobar", cardX + cardW / 2 - 75, cardY + 370, 150, 50);

        answerButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            answerButtons[i] = new Button(app, "", cardX + 400 + (i * 200), cardY + 350, 150, 50);
        }
    }

    public void display(PApplet app) {
        app.background(240);

        // Card Rect
        app.fill(255);
        app.stroke(0);
        app.rect(cardX, cardY, cardW, cardH, 20);

        // Correct/Incorrect display
        app.fill(0);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(28);
        app.text(correctAnswers + "/" + (correctAnswers + incorrectAnswers), cardX, cardY - 50);

        app.textSize(22);

        // Display inputs based on current state
        if (awaitingNumQuestions) {
            app.text("Insertar número de tests", cardX + cardW / 2, cardY + 60);
            inputNumQuestions.display(app);
            saveButton.display(app);
        } else if (enteringQuestions) {
            app.text("Insertar la pregunta número " + (currentQuestionIndex + 1), cardX + cardW / 2, cardY + 60);
            inputQuestion.display(app);
            saveButton.display(app);
        } else if (enteringAnswers) {
            app.text("Insertar la respuesta número " + (currentAnswerIndex + 1) + " del test " + (currentQuestionIndex + 1), cardX + cardW / 2, cardY + 60);
            inputAnswer.display(app);
            saveButton.display(app);
        } else if (enteringCorrectIndex) {
            app.text("Insertar el índice correcto (0-2) del test " + (currentQuestionIndex + 1), cardX + cardW / 2, cardY + 60);
            inputCorrectIndex.display(app);
            saveButton.display(app);
        } else if (currentAnswerIndex < questions.length) {
            Question q = questions[currentAnswerIndex];
            app.text("Pregunta " + (currentAnswerIndex + 1) + ": " + q.getQuestion(), cardX + cardW / 2, cardY + 100);
            for (int i = 0; i < 3; i++) {
                answerButtons[i].setTextBoto(q.getAnswers()[i]);
                answerButtons[i].display(app);
            }
            checkButton.display(app);
            nextButton.display(app);
        } else {
            app.text("Ya se ha finalizado el test", cardX + cardW / 2, cardY + 150);
            app.text("Correctas: " + correctAnswers + ", Incorrectas: " + incorrectAnswers, cardX + cardW / 2, cardY + 200);
        }
    }

    public void keyPressed(PApplet app) {
        if (awaitingNumQuestions) inputNumQuestions.keyPressed(app.key, app.keyCode);
        else if (enteringQuestions) inputQuestion.keyPressed(app.key, app.keyCode);
        else if (enteringAnswers) inputAnswer.keyPressed(app.key, app.keyCode);
        else if (enteringCorrectIndex) inputCorrectIndex.keyPressed(app.key, app.keyCode);
    }

    public void mousePressed(PApplet app) {
        inputNumQuestions.isPressed(app);
        inputQuestion.isPressed(app);
        inputAnswer.isPressed(app);
        inputCorrectIndex.isPressed(app);

        if (saveButton.checkClick(app)) {
            if (awaitingNumQuestions) {
                try {
                    numQuestions = Integer.parseInt(inputNumQuestions.getText());
                    questions = new Question[numQuestions];
                    awaitingNumQuestions = false;
                    enteringQuestions = true;
                    inputNumQuestions.clear();
                } catch (Exception ignored) {}
            } else if (enteringQuestions) {
                currentQuestion = inputQuestion.getText();
                inputQuestion.clear();
                enteringQuestions = false;
                enteringAnswers = true;
                currentAnswerIndex = 0;
            } else if (enteringAnswers) {
                currentAnswers[currentAnswerIndex] = inputAnswer.getText();
                inputAnswer.clear();  // Clear after entering each answer
                currentAnswerIndex++;
                if (currentAnswerIndex == 3) {
                    enteringAnswers = false;
                    enteringCorrectIndex = true;
                }
            } else if (enteringCorrectIndex) {
                try {
                    correctAnswerIndex = Integer.parseInt(inputCorrectIndex.getText());
                    inputCorrectIndex.clear();
                    questions[currentQuestionIndex] = new Question(currentQuestion, currentAnswers.clone(), correctAnswerIndex);
                    currentQuestionIndex++;
                    resetQuestionData();
                    if (currentQuestionIndex == questions.length) {
                        enteringCorrectIndex = false;
                    } else {
                        enteringQuestions = true;
                    }
                } catch (Exception ignored) {}
            }
        }

        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].checkClick(app)) {
                selectedAnswerIndex = i;
            }
        }

        if (checkButton.checkClick(app)) {
            if (selectedAnswerIndex != -1) {
                Question q = questions[currentAnswerIndex];
                if (selectedAnswerIndex == q.getCorrectAnswerIndex()) {
                    correctAnswers++;
                } else {
                    incorrectAnswers++;
                }
            }
        }

        if (nextButton.checkClick(app)) {
            if (currentAnswerIndex < questions.length - 1) {
                currentAnswerIndex++;
                selectedAnswerIndex = -1;
            }
        }
    }

    private void resetQuestionData() {
        currentQuestion = "";
        currentAnswers = new String[3];
        for (int i = 0; i < 3; i++) currentAnswers[i] = "";
        correctAnswerIndex = -1;
    }
}
