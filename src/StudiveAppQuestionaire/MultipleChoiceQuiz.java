package StudiveAppQuestionaire;
import StudiveAppGUI.Button;
import StudiveAppGUI.TextArea;
import processing.core.PApplet;

public class MultipleChoiceQuiz {
    Question[] questions;
    int currentQuestionIndex;
    int currentQuestionIndexToShow;
    boolean awaitingNumQuestions;
    boolean enteringQuestions;
    boolean enteringAnswers;
    boolean enteringAnswersCorrectIndex;
    boolean doneTest;
    boolean isEditionMode;
    boolean TestDone;
    int numQuestions;
    int currentInputAnswerIndex;
    int correctAnswers;
    int incorrectAnswers;
    String currentQuestion;
    String[] currentAnswers;
    int correctAnswerIndex;
    Button saveButton;
    Button nextButton;
    Button checkButton;
    Button finishButton;
    Button[] answerButtons;
    int selectedAnswerIndex = -1;
    TextArea numOfQuestions, question, answers, answerCorrectIndex;
    int cardX = 450, cardY = 250, cardW = 1000, cardH = 600;

    public MultipleChoiceQuiz(PApplet p5, boolean isEditionMode) {
        this.isEditionMode = isEditionMode;

        questions = new Question[0];
        currentQuestionIndex = 0;
        currentQuestionIndexToShow = 0;
        awaitingNumQuestions = true;
        enteringQuestions = false;
        enteringAnswers = false;
        enteringAnswersCorrectIndex = false;
        doneTest = false;
        TestDone = false;
        numQuestions = 0;
        currentInputAnswerIndex = 0;
        correctAnswers = 0;
        incorrectAnswers = 0;
        currentQuestion = "";
        currentAnswers = new String[3];
        for (int i = 0; i < currentAnswers.length; i++) {
            currentAnswers[i] = "";
        }
        correctAnswerIndex = -1;

        numOfQuestions = new TextArea(p5, 900, 550, 100, 40, 40, 23);
        question = new TextArea(p5, 670, 550, 550, 40, 40, 23);
        answers = new TextArea(p5, 670, 550, 550, 40, 40, 23);
        answerCorrectIndex = new TextArea(p5, 900, 550, 100, 40, 40, 23);

        saveButton = new Button(p5, "Guardar", cardX + cardW / 2 - 50, 700, 100, 50);
        nextButton = new Button(p5, "Siguiente", cardX + cardW - 200, 700, 100, 50);
        checkButton = new Button(p5, "Comprobar", cardX + cardW / 2 - 50, 700, 100, 50);
        finishButton = new Button(p5, "Finalizar", cardX + cardW / 2 - 50, 700, 100, 50); // Added "Finalizar" button

        answerButtons = new Button[3];
        for (int i = 0; i < 3; i++) {
            answerButtons[i] = new Button(p5, "", cardX + 200 + (i * 200), cardY + 350, 150, 50);
        }
    }

    public MultipleChoiceQuiz(PApplet p5, String[] questions, String[] answers, int[] correctIndexes) {
        this(p5, false); // view mode off by default
        if (questions != null && answers != null && correctIndexes != null) {
            this.questions = buildQuestionsFromDB(questions, answers, correctIndexes);
            isEditionMode = false; // Switch to view mode
        }
    }

    public static Question[] buildQuestionsFromDB(String[] questions, String[] answers, int[] correctIndexes) {
        int numberOfQuestions = questions.length;
        Question[] questionObjects = new Question[numberOfQuestions];

        for (int i = 0; i < numberOfQuestions; i++) {
            // Each question has 3 answers (flat array like in FlashCard)
            String[] options = new String[] {
                    answers[i * 3],
                    answers[i * 3 + 1],
                    answers[i * 3 + 2]
            };
            questionObjects[i] = new Question(questions[i], options, correctIndexes[i]);
        }

        return questionObjects;
    }


    public void keyPressed(char key, int keyCode) {
        if (awaitingNumQuestions) {
            numOfQuestions.keyPressed(key, keyCode);
        } else if (enteringQuestions) {
            question.keyPressed(key, keyCode);
        } else if (enteringAnswers) {
            answers.keyPressed(key, keyCode);
        } else if (enteringAnswersCorrectIndex) {
            answerCorrectIndex.keyPressed(key, keyCode);
        }
    }

    public void display(PApplet p5) {
        p5.background(240);

        p5.fill(255);
        p5.stroke(0);
        p5.rect(cardX, cardY, cardW, cardH, 20);

        if (isEditionMode && !TestDone) {
            saveButton.display(p5);
            p5.fill(0);

            if (awaitingNumQuestions) {
                p5.textAlign(PApplet.CENTER, PApplet.CENTER);
                p5.textSize(20);
                p5.text("¿Cuántos tests quieres crear?", cardX + cardW / 2, 520);
                numOfQuestions.display(p5);
            } else if (enteringQuestions) {
                p5.textAlign(PApplet.CENTER, PApplet.CENTER);
                p5.textSize(20);
                p5.text("Introduce la pregunta para el test " + (currentQuestionIndex + 1), cardX + cardW / 2, 520);
                question.display(p5);
            } else if (enteringAnswers) {
                p5.textAlign(PApplet.CENTER, PApplet.CENTER);
                p5.textSize(20);
                p5.text("Introduce la respuesta " + (currentInputAnswerIndex + 1) + " para el test " + (currentQuestionIndex + 1), cardX + cardW / 2, 520);
                answers.display(p5);
            } else if (enteringAnswersCorrectIndex) {
                p5.textAlign(PApplet.CENTER, PApplet.CENTER);
                p5.textSize(20);
                p5.text("Selecciona el índice de la respuesta correcta (0-2):", cardX + cardW / 2, 520);
                answerCorrectIndex.display(p5);
            }

        } else if (!isEditionMode && !TestDone) {
            nextButton.display(p5);
            checkButton.display(p5);

            if (currentQuestionIndexToShow < questions.length) {
                p5.fill(0);
                p5.textAlign(PApplet.CENTER, PApplet.CENTER);
                p5.textSize(28);
                p5.text(correctAnswers + "/" + (correctAnswers + incorrectAnswers), cardX + cardW - 100, cardY + 40);

                p5.textAlign(PApplet.CENTER, PApplet.TOP);
                p5.textSize(18);
                Question currentQuestion = questions[currentQuestionIndexToShow];
                p5.text("Pregunta " + (currentQuestionIndexToShow + 1) + ": " + currentQuestion.getQuestion(), cardX + cardW / 2, cardY + cardH / 2 - 100);
                for (int i = 0; i < 3; i++) {
                    answerButtons[i].setTextBoto(currentQuestion.getAnswers()[i]);
                    answerButtons[i].display(p5);
                }
            }
        } else if (!isEditionMode && TestDone) {
            p5.textAlign(PApplet.CENTER, PApplet.CENTER);
            p5.fill(0);
            p5.textSize(22);
            p5.text("¡Test finalizado!", cardX + cardW / 2, cardY + 150);
            p5.text("Respuestas correctas: " + correctAnswers, cardX + cardW / 2, cardY + 200);
            p5.text("Respuestas incorrectas: " + incorrectAnswers, cardX + cardW / 2, cardY + 250);
            finishButton.display(p5);
        } else if (isEditionMode && TestDone) {
            p5.textAlign(PApplet.CENTER, PApplet.CENTER);
            p5.fill(0);
            p5.textSize(22);
            p5.text("¡Test finalizado!", cardX + cardW / 2, cardY + 150);
            finishButton.display(p5);
        }

    }

    public void mousePressed(PApplet p5) {
        numOfQuestions.isPressed(p5);
        question.isPressed(p5);
        answers.isPressed(p5);
        answerCorrectIndex.isPressed(p5);

        if (saveButton.checkClick(p5)) {
            if (awaitingNumQuestions) {
                try {
                    int num = Integer.parseInt(numOfQuestions.getText());
                    numOfQuestions.clear();
                    if (num > 0) {
                        questions = new Question[num];
                        awaitingNumQuestions = false;
                        enteringQuestions = true;
                    }
                } catch (Exception ignored) {}
            } else if (enteringQuestions) {
                currentQuestion = question.getText();
                question.clear();
                enteringQuestions = false;
                enteringAnswers = true;
                currentInputAnswerIndex = 0;
            } else if (enteringAnswers) {
                currentAnswers[currentInputAnswerIndex] = answers.getText();
                answers.clear();
                currentInputAnswerIndex++;
                if (currentInputAnswerIndex == 3) {
                    enteringAnswers = false;
                    enteringAnswersCorrectIndex = true;
                }
            } else if (enteringAnswersCorrectIndex) {
                try {
                    correctAnswerIndex = Integer.parseInt(answerCorrectIndex.getText());
                    answerCorrectIndex.clear();
                    questions[currentQuestionIndex] = new Question(currentQuestion, currentAnswers.clone(), correctAnswerIndex);
                    currentQuestionIndex++;
                    resetQuestionData();
                    if (currentQuestionIndex == questions.length) {
                        enteringAnswersCorrectIndex = false;
                        TestDone = true;
                    } else {
                        enteringQuestions = true;
                    }
                } catch (Exception ignored) {}
            }
        }

        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].checkClick(p5)) {
                selectedAnswerIndex = i;
                break;
            }
        }

        if (checkButton.checkClick(p5)) {
            if (selectedAnswerIndex != -1 && currentQuestionIndexToShow < questions.length) {
                Question currentQuestion = questions[currentQuestionIndexToShow];
                if (selectedAnswerIndex == currentQuestion.getCorrectAnswerIndex()) {
                    correctAnswers++;
                } else {
                    incorrectAnswers++;
                }
                if (currentQuestionIndexToShow == questions.length - 1) {
                    TestDone = true;
                }
            }
        }


        if (nextButton.checkClick(p5)) {
            if (currentQuestionIndexToShow < questions.length - 1) {
                currentQuestionIndexToShow++;
                selectedAnswerIndex = -1;
            }
        }

        if (finishButton.checkClick(p5)) {
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

    public String[] getQuestions() {
        String[] out = new String[questions.length];
        for (int i = 0; i < questions.length; i++) {
            out[i] = questions[i].getQuestion();
        }
        return out;
    }

    public String[] getAnswers() {
        String[] out = new String[questions.length * 3];
        for (int i = 0; i < questions.length; i++) {
            String[] options = questions[i].getAnswers();
            for (int j = 0; j < 3; j++) {
                out[i * 3 + j] = options[j];
            }
        }
        return out;
    }

    public int[] getCorrectIndexes() {
        int[] out = new int[questions.length];
        for (int i = 0; i < questions.length; i++) {
            out[i] = questions[i].getCorrectAnswerIndex();
        }
        return out;
    }

}
