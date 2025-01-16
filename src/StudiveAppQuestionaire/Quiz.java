package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Quiz extends PApplet {
    public Question[] questions;
    public int currentQuestionIndex;
    public boolean awaitingNumQuestions;
    public boolean enteringQuestions;
    public boolean enteringAnswers;
    public boolean enteringCorrectIndex;
    public int numQuestions;
    public int currentAnswerIndex;
    public int correctAnswers;
    public int incorrectAnswers;
    public String currentQuestion;
    public String[] currentAnswers;
    public int correctAnswerIndex;
    public Button saveButton;
    public Button nextButton;
    public Button checkButton;
    public Button[] answerButtons;
    public int selectedAnswerIndex = -1;

    public Quiz() {
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
        for (int i = 0; i < currentAnswers.length; i++) {
            currentAnswers[i] = "";
        }
        correctAnswerIndex = -1;

        // Inicialización de botones
        saveButton = new Button(300, 400, 100, 40, "Guardar");
        nextButton = new Button(300, 500, 100, 40, "Siguiente");
        checkButton = new Button(300, 450, 100, 40, "Comprobar");

        // Botones de respuestas
        answerButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            answerButtons[i] = new Button(200 + (i * 150), 300, 100, 40, "");
        }
    }

    public void keyPressed(PApplet app) {
        if (awaitingNumQuestions && Character.isDigit(app.key)) {
            numQuestions = numQuestions * 10 + Character.getNumericValue(app.key);
        } else if (enteringQuestions) {
            if (app.key == PApplet.BACKSPACE && currentQuestion.length() > 0) {
                currentQuestion = currentQuestion.substring(0, currentQuestion.length() - 1);
            } else if (app.key != PApplet.BACKSPACE) {
                currentQuestion += app.key;
            }
        } else if (enteringAnswers) {
            if (app.key == PApplet.BACKSPACE && currentAnswers[currentAnswerIndex].length() > 0) {
                currentAnswers[currentAnswerIndex] = currentAnswers[currentAnswerIndex].substring(0, currentAnswers[currentAnswerIndex].length() - 1);
            } else if (app.key != PApplet.BACKSPACE) {
                currentAnswers[currentAnswerIndex] += app.key;
            }
        } else if (enteringCorrectIndex && Character.isDigit(app.key)) {
            int index = Character.getNumericValue(app.key);
            if (index >= 0 && index < currentAnswers.length) {
                correctAnswerIndex = index;
            }
        }
    }

    public void handleInput(PApplet app) {
        if (awaitingNumQuestions) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("¿Cuántos tests quieres crear?", app.width / 2, app.height / 2 - 50);
            app.text("Número de tests: " + numQuestions, app.width / 2, app.height / 2);
            saveButton.display(app); // Mostrar el botón de guardar
        }
        else if (enteringQuestions) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("Introduce la pregunta para el test " + (currentQuestionIndex + 1), app.width / 2, app.height / 2 - 50);
            app.text("Pregunta: " + currentQuestion, app.width / 2, app.height / 2);
            saveButton.display(app); // Mostrar el botón de guardar
        }
        else if (enteringAnswers) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("Introduce las respuestas para el test " + (currentQuestionIndex + 1), app.width / 2, app.height / 2 - 50);
            for (int i = 0; i < 3; i++) {
                app.text("Respuesta " + (i + 1) + ": " + currentAnswers[i], app.width / 2, app.height / 2 + (i * 30));
            }
            saveButton.display(app); // Mostrar el botón de guardar
        }
        else if (enteringCorrectIndex) {
            app.textAlign(PApplet.CENTER, PApplet.CENTER);
            app.textSize(20);
            app.text("Selecciona el índice de la respuesta correcta (0-2):", app.width / 2, app.height / 2 - 50);
            app.text("Índice correcto: " + correctAnswerIndex, app.width / 2, app.height / 2);
            saveButton.display(app); // Mostrar el botón de guardar
        }
        else if (currentAnswerIndex<questions.length){
            app.textAlign(PApplet.CENTER, PApplet.TOP);
            app.textSize(18);
            Question currentQuestion = questions[currentAnswerIndex];
            app.text("Pregunta " + (currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion(), app.width / 2, 50);
            for (int i = 0; i < 3; i++) {
                app.text("Respuesta " + (i + 1) + ": " + currentQuestion.getAnswers()[i], app.width / 2, 80 + (i * 30));
                answerButtons[i].setLabel(currentQuestion.getAnswers()[i]); // Establecer las respuestas en los botones
                answerButtons[i].display(app);
            }
            checkButton.display(app);  // Mostrar el botón "Comprobar"
            nextButton.display(app);  // Mostrar el botón "Siguiente"
            app.textSize(18);
            app.text("Incorrecta: " + incorrectAnswers, app.width / 2 + 100, 300);

            app.textSize(18);
            app.text("Correcta: " + correctAnswers, app.width / 2 +100, 350);
        } else {
            app.textSize(18);
            app.text("DONE", app.width/2,50);
        }
    }

    public void mousePressed(PApplet app) {
        if (saveButton.checkClick(app)) {
            if (awaitingNumQuestions && numQuestions > 0) {
                questions = new Question[numQuestions];
                awaitingNumQuestions = false;
                enteringQuestions = true;
            } else if (enteringQuestions) {
                enteringQuestions = false;
                enteringAnswers = true;
            } else if (enteringAnswers) {
                currentAnswerIndex++;
                if (currentAnswerIndex == currentAnswers.length) {
                    enteringAnswers = false;
                    enteringCorrectIndex = true;
                    currentAnswerIndex = 0;
                }
            } else if (enteringCorrectIndex) {
                questions[currentQuestionIndex] = new Question(currentQuestion, currentAnswers, correctAnswerIndex);
                currentQuestionIndex++;
                resetQuestionData();
                if (currentQuestionIndex == questions.length) {
                    enteringCorrectIndex = false;
                } else {
                    enteringQuestions = true;
                }
            }
        }

        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].checkClick(app)) {
                selectedAnswerIndex = i;
                System.out.println("Respuesta seleccionada: " + selectedAnswerIndex);
                break;
            }
        }

        if (checkButton.checkClick(app)) {
            if (selectedAnswerIndex != -1) {
                Question currentQuestion = questions[currentAnswerIndex];
                if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
                    correctAnswers++;
                    System.out.println("Respuesta correcta");
                } else {
                    incorrectAnswers++;
                    System.out.println("Respuesta incorrecta");
                }
            } else {
                System.out.println("No se ha seleccionado ninguna respuesta.");
            }
        }

        if (nextButton.checkClick(app)) {
            if (currentAnswerIndex < questions.length - 1) {
                currentAnswerIndex++;
                selectedAnswerIndex = -1;
            }
        }
    }

    public void resetQuestionData() {
        currentQuestion = "";
        currentAnswers = new String[3];
        for (int i = 0; i < 3; i++) {
            currentAnswers[i] = "";
        }
        correctAnswerIndex = -1;
    }
}
