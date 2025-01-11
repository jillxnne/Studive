package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Quiz extends PApplet {
    Question[] questions;
    int currentQuestionIndex;
    int num;
    int selectedOptionIndex = -1;
    boolean[] answers;
    boolean confirmed = false;
    float x, y, w, h;

    Quiz(float x, float y, float w, float h, int num) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.num = num;
        questions = new Question[num];
        currentQuestionIndex = 0;
        answers = new boolean[questions.length];
    }

    void addQuestion(String questionText, String[] options, int correctAnswerIndex, int index) {
        questions[index] = new Question(questionText, options, correctAnswerIndex);
    }

    void nextQuestion() {
        currentQuestionIndex = (currentQuestionIndex + 1) % questions.length;
        selectedOptionIndex = -1;
        confirmed = false;
    }

    void previousQuestion() {
        currentQuestionIndex = (currentQuestionIndex - 1 + questions.length) % questions.length;
        selectedOptionIndex = -1;
        confirmed = false;
    }

    void checkAnswer() {
        if (selectedOptionIndex != -1) {
            answers[currentQuestionIndex] = questions[currentQuestionIndex].checkAnswer(selectedOptionIndex);
        }
    }

    void display(PApplet app) {
        Question currentQuestion = questions[currentQuestionIndex];
        currentQuestion.displayQuestion(app, x, y, w, h);

        if (selectedOptionIndex != -1) {
            app.fill(255, 0, 0);
            float optionY = y + 50 + selectedOptionIndex * 40;
            app.ellipse(x + 20, optionY, 20, 20);
        }

        if (confirmed) {
            app.fill(0);
            if (answers[currentQuestionIndex]) {
                app.text("Correct!", x + w / 2, y + h + 20);
            } else {
                app.text("Incorrect!", x + w / 2, y + h + 20);
            }
        }

        app.fill(0, 255, 0);
        app.rect(x + w / 2 - 50, y + h + 80, 100, 30);
        app.fill(0);
        app.textAlign(PApplet.CENTER, PApplet.CENTER);
        app.textSize(16);
        app.text("Confirm", x + w / 2, y + h + 95);
    }
    void checkClick(float mouseX, float mouseY) {
        Question currentQuestion = questions[currentQuestionIndex];

        for (int i = 0; i < currentQuestion.options.length; i++) {
            float optionY = y + 50 + i * 40;
            if (mouseX > x + 10 && mouseX < x + 30 && mouseY > optionY - 10 && mouseY < optionY + 10) {
                selectedOptionIndex = i;
            }
        }
        if (mouseX > x + w / 2 - 50 && mouseX < x + w / 2 + 50 && mouseY > y + h + 80 && mouseY < y + h + 110) {
            confirmed = true;
            checkAnswer();
        }
    }
}
