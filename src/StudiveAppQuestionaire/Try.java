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
        quiz = new Quiz();
    }

    public void draw() {
        background(0);
        quiz.handleInput(this);
    }

    public void mousePressed() {
        quiz.mousePressed(this);
    }
    public void keyPressed() {
        quiz.keyPressed(this);
    }
}
