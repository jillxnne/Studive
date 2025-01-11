package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Try extends PApplet {
    public static void main(String[] args) {
        PApplet.main("StudiveAppQuestionaire.Try", args);
    }
    public void settings(){
        size(1200, 600);
        smooth(10);
    }
    Quiz quiz;
    public void setup() {
        quiz = new Quiz(100, 100, 400, 200,3);
        String[] options1 = {"Paris", "London", "Berlin"};
        quiz.addQuestion("What is the capital of France?", options1, 0, 0);
        String[] options2 = {"2", "4", "6"};
        quiz.addQuestion("What is 2 + 2?", options2, 1, 1);
        String[] options3 = {"Harper Lee", "J.K. Rowling", "Ernest Hemingway"};
        quiz.addQuestion("Who wrote 'To Kill a Mockingbird'?", options3, 0, 2);
    }

    public void draw() {
        background(200, 230, 255);
        quiz.display(this);
    }

    public void mousePressed() {
        quiz.checkClick(mouseX, mouseY);
    }

    public void keyPressed() {
        if (keyCode == RIGHT) {
            quiz.nextQuestion();
        } else if (keyCode == LEFT) {
            quiz.previousQuestion();
        }
    }
}
