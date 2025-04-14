/*package StudiveAppQuestionaire;

import StudiveAppGUI.TextArea;
import StudiveAppGUI.Button;
import processing.core.PApplet;

public class Test {
    private final PApplet p;
    // private TestQuestion[] questions;
    private int currentQuestionIndex = 0; // Para la navegación en el test
    private int questionCreationIndex = 0; // Para la creación de preguntas
    private boolean awaitingNumQuestions = true;
    private boolean enteringQuestion = false;
    private boolean enteringAnswers = false;
    private boolean enteringCorrectIndex = false;
    private int numQuestions;
    private int currentAnswerIndex = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;
    private String currentQuestion = "";
    private String[] currentAnswers = new String[3];
    private int correctAnswerIndex;
    private int selectedAnswerIndex = -1;

    private final TextArea inputNumQuestions;
    private final TextArea inputQuestion;
    private final TextArea inputAnswer;
    private final TextArea inputCorrectIndex;

    private final Button saveButton;
    private final Button nextButton;
    private final Button checkButton;
    private final Button[] answerButtons;

    private final int cardX = 300, cardY = 200, cardW = 1350, cardH = 700;

    public Test(PApplet p) {
        this.p = p;
        for (int i = 0; i < 3; i++) currentAnswers[i] = "";

        inputNumQuestions = new TextArea(p, cardX + 475, cardY + 120, 400, 60, 20, 1);
        inputQuestion = new TextArea(p, cardX + 275, cardY + 120, 800, 80, 40, 2);
        inputAnswer = new TextArea(p, cardX + 275, cardY + 120, 800, 80, 40, 2);
        inputCorrectIndex = new TextArea(p, cardX + 475, cardY + 120, 400, 60, 20, 1);

        saveButton = new Button(p, "Guardar", cardX + cardW / 2 - 75, cardY + 250, 150, 50);
        nextButton = new Button(p, "Siguiente", cardX + cardW / 2 - 75, cardY + 310, 150, 50);
        checkButton = new Button(p, "Comprobar", cardX + cardW / 2 - 75, cardY + 370, 150, 50);

        answerButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            answerButtons[i] = new Button(p, "", cardX + 400 + (i * 200), cardY + 350, 150, 50);
        }
    }

    public void display(PApplet app) {
        app.background(240);

        // Dibuja el rectángulo del test (tarjeta)
        app.fill(255);
        app.stroke(0);
        app.rect(cardX, cardY, cardW, cardH, 20);

        // Muestra el resultado de las respuestas correctas/incorrectas en la esquina superior derecha
        app.fill(0);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(28);
        app.text(correctAnswers + "/" + (correctAnswers + incorrectAnswers), cardX + cardW - 100, cardY + 40);

        // Define el tamaño de fuente para el texto
        app.textSize(22);

        // Flujo de la pantalla dependiendo del estado
        if (awaitingNumQuestions) {
            // Pide el número de preguntas
            app.text("Insertar número de tests", cardX + cardW / 2, cardY + 60);
            inputNumQuestions.display(app);
            saveButton.display(app);
        } else if (enteringQuestion) {
            // Pide la pregunta
            app.text("Insertar la pregunta número " + (currentQuestionIndex + 1), cardX + cardW / 2, cardY + 60);
            inputQuestion.display(app);
            saveButton.display(app);
        } else if (enteringAnswers) {
            // Pide las respuestas
            app.text("Insertar la respuesta número " + (currentAnswerIndex + 1), cardX + cardW / 2, cardY + 60);
            inputAnswer.display(app);
            saveButton.display(app);
        } else if (enteringCorrectIndex) {
            // Pide el índice correcto de la respuesta
            app.text("Índice correcto (0-2)", cardX + cardW / 2, cardY + 60);
            inputCorrectIndex.display(app);
            saveButton.display(app);
        } else if (currentQuestionIndex < questions.length) {
            // Muestra la pregunta y las respuestas con los botones
            TestQuestion q = questions[currentQuestionIndex];
            app.text("Pregunta " + (currentQuestionIndex + 1) + ": " + q.getQuestion(), cardX + cardW / 2, cardY + 100);

            // Muestra las respuestas como botones en una fila
            for (int i = 0; i < 3; i++) {
                answerButtons[i].setTextBoto(q.getAnswers()[i]);
                answerButtons[i].setPosition(cardX + 400 + (i * 200), cardY + 350); // Establecer las posiciones de los botones
                answerButtons[i].display(app);
            }
            // Muestra los botones para comprobar la respuesta y pasar a la siguiente pregunta
            checkButton.display(app);
            nextButton.display(app);

        } else {
            // Cuando todas las preguntas han sido completadas, mostrar el mensaje de finalización
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(28);
            app.text("Test finalizado", cardX + cardW / 2, cardY + 150);

            // Muestra los resultados
            app.textSize(22);
            app.text("Respuestas correctas: " + correctAnswers, cardX + cardW / 2, cardY + 200);
            app.text("Respuestas incorrectas: " + incorrectAnswers, cardX + cardW / 2, cardY + 250);
        }
    }


    public void keyPressed(char key, int keyCode) {
        if (awaitingNumQuestions) inputNumQuestions.keyPressed(key, keyCode);
        else if (enteringQuestion) inputQuestion.keyPressed(key, keyCode);
        else if (enteringAnswers) inputAnswer.keyPressed(key, keyCode);
        else if (enteringCorrectIndex) inputCorrectIndex.keyPressed(key, keyCode);
    }

    public void mousePressed() {
        inputNumQuestions.isPressed(p);
        inputQuestion.isPressed(p);
        inputAnswer.isPressed(p);
        inputCorrectIndex.isPressed(p);

        if (saveButton.checkClick(p)) {
            if (awaitingNumQuestions) {
                try {
                    numQuestions = Integer.parseInt(inputNumQuestions.getText());
                    questions = new TestQuestion[numQuestions];
                    awaitingNumQuestions = false;
                    enteringQuestion = true;
                    inputNumQuestions.clear();
                } catch (Exception ignored) {}
            } else if (enteringQuestion) {
                currentQuestion = inputQuestion.getText();
                inputQuestion.clear();
                enteringQuestion = false;
                enteringAnswers = true;
                currentAnswerIndex = 0;
            } else if (enteringAnswers) {
                currentAnswers[currentAnswerIndex] = inputAnswer.getText();
                inputAnswer.clear();
                currentAnswerIndex++;
                if (currentAnswerIndex == 3) {
                    enteringAnswers = false;
                    enteringCorrectIndex = true;
                }
            } else if (enteringCorrectIndex) {
                try {
                    correctAnswerIndex = Integer.parseInt(inputCorrectIndex.getText());
                    inputCorrectIndex.clear();
                    questions[questionCreationIndex] = new TestQuestion(currentQuestion, currentAnswers.clone(), correctAnswerIndex);
                    questionCreationIndex++; // Avanzamos el índice de creación
                    resetQuestionData();
                    if (questionCreationIndex == questions.length) {
                        enteringCorrectIndex = false;
                        currentQuestionIndex = 0; // Iniciamos la visualización del test desde la primera pregunta
                    } else {
                        enteringQuestion = true;
                    }
                } catch (Exception ignored) {}
            }
        }

        // Botones de respuesta
        for (int i = 0; i < 3; i++) {
            if (answerButtons[i].checkClick(p)) {
                selectedAnswerIndex = i;
            }
        }

        if (checkButton.checkClick(p)) {
            if (selectedAnswerIndex != -1 && currentQuestionIndex < questions.length) {
                TestQuestion q = questions[currentQuestionIndex];
                if (selectedAnswerIndex == q.getCorrectIndex()) correctAnswers++;
                else incorrectAnswers++;
            }
        }

        if (nextButton.checkClick(p)) {
            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
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

 */
