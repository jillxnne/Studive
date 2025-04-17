package StudiveAppGUI;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Clase que representa un conjunto de asignaturas paginadas que pueden ser visualizadas y seleccionadas.
 * Esta clase permite mostrar un número limitado de asignaturas por página y navegar entre páginas.
 */
public class PagedSubject {

    /** Datos de las asignaturas (título, sección) */
    String[][] subjectsData;

    /** Arreglo de objetos de la clase Subject que representan las asignaturas */
    Subject[] subjects;

    /** Número de asignaturas por página */
    int numSubjectsPage;

    /** Número de la página actual */
    int numPage;

    /** Número total de páginas */
    int numTotalPages;

    /** Coordenadas y dimensiones para posicionar las asignaturas en la pantalla */
    float x, y, w, h;

    /** Índice de la asignatura seleccionada (-1 si ninguna está seleccionada) */
    int selectedCard = -1;

    /**
     * Constructor que inicializa la paginación de asignaturas.
     *
     * @param nsp Número de asignaturas por página.
     */
    public PagedSubject(int nsp) {
        this.numSubjectsPage = nsp;
        this.numPage = 0;
    }

    /**
     * Establece las dimensiones de la zona donde se ubicarán las asignaturas.
     *
     * @param x Coordenada X de la zona donde se ubicarán las asignaturas.
     * @param y Coordenada Y de la zona donde se ubicarán las asignaturas.
     * @param w Ancho de la zona de las asignaturas.
     * @param h Alto de la zona de las asignaturas.
     */
    public void setDimensions(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Establece los datos de las asignaturas.
     *
     * @param d Datos de las asignaturas (título, sección).
     */
    public void setData(String[][] d) {
        this.subjectsData = d;
        this.numTotalPages = d.length / this.numSubjectsPage;
    }

    /**
     * Crea y organiza las asignaturas en función de los datos y las dimensiones establecidas.
     *
     * @param img Imagen que se asignará a todas las asignaturas.
     */
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

                    // Asigna la imagen a la asignatura
                    subjects[i].setImage(img);

                    ySubject += hSubject + b;
                }
            }
        }
    }

    /**
     * Cambia a la siguiente página de asignaturas.
     */
    public void nextPage() {
        if (this.numPage < this.numTotalPages) {
            this.numPage++;
        }
    }

    /**
     * Vuelve a la página anterior de asignaturas.
     */
    public void prevPage() {
        if (this.numPage > 0) {
            this.numPage--;
        }
    }

    /**
     * Muestra las asignaturas de la página actual.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
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

    /**
     * Verifica si el mouse está sobre alguna asignatura y, en caso afirmativo, la selecciona.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
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

    /**
     * Devuelve el título de la asignatura seleccionada.
     *
     * @return El título de la asignatura seleccionada, o null si ninguna asignatura está seleccionada.
     */
    public String getSelectedSubjectTitle() {
        if (selectedCard >= 0 && selectedCard < subjects.length) {
            if (subjects[selectedCard].title != null) {
                return subjects[selectedCard].title;
            }
        }
        return null;
    }

    /**
     * Verifica si el mouse está sobre alguna asignatura de la página actual.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @return true si el mouse está sobre alguna asignatura, false en caso contrario.
     */
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
