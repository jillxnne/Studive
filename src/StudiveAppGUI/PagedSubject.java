package StudiveAppGUI;

import StudiveAppGUI.Subject;
import processing.core.PApplet;
import processing.core.PImage;

public class PagedSubject {
    String[][] subjectsData;
    Subject[] subjects;
    int numSubjectsPage;
    int numPage;
    int numTotalPages;
    float x, y, w, h;
    int selectedCard = -1;


    public PagedSubject(int nsp) {
        this.numSubjectsPage = nsp;
        this.numPage = 0;
    }

    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void setData(String[][] d) {
        this.subjectsData = d;
        this.numTotalPages = d.length / this.numSubjectsPage;
    }

    public void setSubjects(PImage img) {
        subjects = new Subject[this.subjectsData.length];
        for (int np = 0; np <= numTotalPages; np++) {
            int firstSubjectPage = numSubjectsPage * np;
            int lastSubjectPage = numSubjectsPage * (np + 1) - 1;
            float hSubject = h / (float) numSubjectsPage;
            float ySubject = y;
            float b = 10;
            for (int i = firstSubjectPage; i <= lastSubjectPage; i++) {
                if (i < subjects.length) {
                    subjects[i] = new Subject(subjectsData[i][0], subjectsData[i][1]);
                    subjects[i].setDimensions(x, ySubject, w, hSubject, b);

                    subjects[i].setImage(img);

                    ySubject += hSubject + b;
                }
            }
        }
    }

    public void nextPage() {
        if (this.numPage < this.numTotalPages) {
            this.numPage++;
        }
    }

    public void prevPage() {
        if (this.numPage > 0) {
            this.numPage--;
        }
    }

    public void display(PApplet p5) {
        p5.pushStyle();
        int firstSubjectPage = numSubjectsPage * numPage;
        int lastSubjectPage = numSubjectsPage * (numPage + 1) - 1;
        for (int i = firstSubjectPage; i <= lastSubjectPage; i++) {
            if (i < subjects.length && subjects[i] != null) {
                subjects[i].display(p5);
            }
        }
        p5.popStyle();
    }
    public void checkSubjectSelection(PApplet p5) {
        boolean selected = false;
        int firstSubjectPage = numSubjectsPage * numPage;
        int lastSubjectPage = numSubjectsPage * (numPage + 1) - 1;
        for (int i = firstSubjectPage; i <= lastSubjectPage; i++) {
            if (i < subjects.length && subjects[i] != null && subjects[i].mouseOver(p5)) {
                selectedCard = i;
                selected = true;
                break;
            }
        }
        if (!selected) {
            selectedCard = -1;
        }
    }
    public String getSelectedSubjectTitle() {
        if(selectedCard >= 0 && selectedCard < subjects.length) {
            if(subjects[selectedCard].title != null){
                return subjects[selectedCard].title;
            }
        }
        return null;
    }

    public boolean checkMouseOver(PApplet p5) {
        int firstSubjectPage = numSubjectsPage * numPage;
        int lastSubjectPage = numSubjectsPage * (numPage + 1) - 1;
        for (int i = firstSubjectPage; i <= lastSubjectPage; i++) {
            if (i < subjects.length && subjects[i] != null && subjects[i].mouseOver(p5)) {
                return true;
            }
        }
        return false;
    }
}
