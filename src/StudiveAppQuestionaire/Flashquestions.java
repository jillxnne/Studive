package StudiveAppQuestionaire;

import StudiveAppGUI.PagedCard;
import processing.core.PApplet;

public class Flashquestions {
    String statement;
    String[] options;
    int correctOption;
    int answer;

    Flashquestions(PApplet p5, String statement, String[] options, int correctOption, int answer){
        this.statement = statement;
        options = new String[3];
        this.correctOption = correctOption;
        this.answer = answer;
    }


}
