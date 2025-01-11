package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Question extends PApplet {
    String questionText;
    String[] options;  // Opciones de respuesta
    int correctAnswerIndex;  // Índice de la respuesta correcta

    Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    boolean checkAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }

    void displayQuestion(PApplet app, float x, float y, float w, float h) {
        app.fill(255);
        app.stroke(0);
        app.rect(x, y, w, h);  // Rectángulo para la pregunta

        app.fill(0);
        app.textAlign(PApplet.CENTER, PApplet.TOP);
        app.textSize(18);
        app.text(questionText, x + w / 2, y + 20);  // Pregunta dentro del rectángulo

        // Dibujar las opciones con círculos a la izquierda
        for (int i = 0; i < options.length; i++) {
            float optionY = y + 50 + i * 40;
            app.text(options[i], x + 100, optionY);  // Texto de la opción
            app.fill(0);  // Color de los círculos
            app.ellipse(x + 20, optionY, 20, 20);  // Círculo a la izquierda de la opción
        }
    }
}
