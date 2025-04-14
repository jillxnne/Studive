package StudiveAppQuestionaire;

import processing.core.PApplet;

public class Try extends PApplet {
    public static void main(String[] args) {
        PApplet.main("StudiveAppQuestionaire.Try", args);
    }
    public void settings(){
        fullScreen();
        smooth(10);
    }
    MultipleChoiceQuiz quiz;
    Question[] preloadedQuestions;

    public void setup() {
        preloadedQuestions = new Question[3];
        preloadedQuestions[0] = new Question("What is 2 + 2?", new String[]{"3", "4", "5"}, 1);
        preloadedQuestions[1] = new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris"}, 2);
        preloadedQuestions[2] = new Question("What is the color of the sky?", new String[]{"Blue", "Green", "Red"}, 0);
        // quiz = new MultipleChoiceQuiz(this,preloadedQuestions);
    }

    public void draw() {
        background(0);
        quiz.display(this);
    }

    public void mousePressed() {
        quiz.mousePressed(this);
    }
    public void keyPressed() {
        quiz.keyPressed(key, keyCode);
    }
}
