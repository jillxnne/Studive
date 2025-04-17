package StudiveAppGUI;

import processing.core.PApplet;
import static java.lang.Math.min;
import static processing.core.PApplet.constrain;
import static processing.core.PApplet.println;
import static processing.core.PConstants.BACKSPACE;
import static StudiveAppFonts.Sizes.*;

/**
 * La clase {@code TextArea} representa un área de texto editable en una interfaz gráfica.
 * Permite a los usuarios escribir texto, borrar, y seleccionar el área de texto. Además,
 * permite la visualización del texto en un número limitado de filas y columnas, y ofrece
 * opciones para manejar la interacción con el ratón y el teclado.
 *
 * El área de texto tiene un fondo, borde y color de texto configurables, y su comportamiento
 * cambia según si está o no seleccionada.
 */
public class TextArea {

    /** Coordenada X de la esquina superior izquierda del área de texto */
    int x, y;

    /** Altura y ancho del área de texto */
    int h, w;

    /** Número de columnas y filas que el área de texto puede contener */
    int numCols, numRows;

    /** Colores del fondo, texto, área seleccionada y borde */
    int bgColor, fgColor, selectedColor, borderColor;

    /** Grosor del borde del área de texto */
    int borderWeight = 1;

    /** Texto contenido en el área de texto */
    public String text = "";

    /** Arreglo de líneas que se visualizan en el área de texto */
    String[] lines;

    /** Tamaño de la fuente para el texto */
    float sizeText = SubtitleSize;

    /** Indica si el área de texto está seleccionada o no */
    boolean selected = false;

    /**
     * Constructor de la clase {@code TextArea}.
     * Inicializa las propiedades del área de texto, como su posición, tamaño,
     * número de filas y columnas, así como los colores predeterminados.
     *
     * @param p5 El objeto {@code PApplet} que representa la ventana de la aplicación.
     * @param x Coordenada X de la esquina superior izquierda del área de texto.
     * @param y Coordenada Y de la esquina superior izquierda del área de texto.
     * @param w Ancho del área de texto.
     * @param h Alto del área de texto.
     * @param nc Número de columnas (letras por línea) que el área de texto puede contener.
     * @param nr Número de filas (líneas de texto) que el área de texto puede contener.
     */
    public TextArea(PApplet p5, int x, int y, int w, int h, int nc, int nr) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.numCols = nc;
        this.numRows = nr;
        this.lines = new String[nr];

        this.bgColor = p5.color(140, 140, 140);
        this.fgColor = p5.color(0, 0, 0);
        this.selectedColor = p5.color(190, 190, 60);
        this.borderColor = p5.color(30, 30, 30);
    }

    /**
     * Obtiene el texto actual del área de texto.
     *
     * @return El texto almacenado en el área de texto.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Dibuja el área de texto en la pantalla, incluyendo su borde, fondo y texto.
     * Si el área está seleccionada, se utiliza un color diferente para el fondo.
     *
     * @param p5 El objeto {@code PApplet} que representa la ventana de la aplicación.
     */
    public void display(PApplet p5) {
        p5.pushStyle();
        p5.strokeWeight(borderWeight);
        p5.stroke(borderColor);
        p5.fill(selected ? selectedColor : bgColor);
        p5.rectMode(p5.CORNER);
        p5.rect(x, y, w, h, 5);

        p5.fill(fgColor);
        p5.textSize(sizeText);
        p5.textAlign(p5.LEFT);
        float lineHeight = sizeText + 5;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null) {
                p5.text(lines[i], x + 10, y + lineHeight * (i + 1));
            }
        }
        p5.popStyle();
    }

    /**
     * Actualiza el contenido de las líneas del área de texto, dividiendo el texto
     * en líneas según el número de columnas disponibles. Si el texto excede la
     * capacidad de líneas disponibles, se ajusta para caber dentro del área visible.
     */
    public void updateLines() {
        if (text.length() > 0) {
            int numLines = constrain(text.length() / numCols, 0, this.numRows - 1);
            for (int i = 0; i <= numLines; i++) {
                int start = i * numCols;
                int end = min(text.length(), (i + 1) * numCols);
                lines[i] = text.substring(start, end);
            }
        } else {
            for (int i = 0; i < lines.length; i++) {
                lines[i] = "";
            }
        }
    }

    /**
     * Maneja la entrada de texto cuando se presiona una tecla. Si el área de texto
     * está seleccionada y se presiona una tecla válida, el texto se agrega o se elimina
     * dependiendo de la tecla presionada.
     *
     * @param key El carácter ingresado.
     * @param keyCode El código de la tecla presionada.
     */
    public void keyPressed(char key, int keyCode) {
        if (selected) {
            if (keyCode == (int)BACKSPACE) {
                removeText();
            } else if (keyCode == 32) {
                addText(' '); // Agregar espacio
            } else {
                addText(key);
            }
        }
    }

    /**
     * Agrega un carácter al texto en el área de texto, siempre que no se haya superado
     * el límite de filas y columnas disponibles.
     *
     * @param c El carácter a agregar al texto.
     */
    public void addText(char c) {
        println(c);
        if (this.text.length() < this.numCols * this.numRows) {
            this.text += c;
        }
        updateLines();
    }

    /**
     * Elimina el último carácter del texto en el área de texto.
     */
    public void removeText() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        updateLines();
    }

    /**
     * Verifica si el ratón está sobre el área de texto.
     *
     * @param p5 El objeto {@code PApplet} que representa la ventana de la aplicación.
     * @return {@code true} si el ratón está sobre el área de texto, {@code false} de lo contrario.
     */
    public boolean mouseOverTextField(PApplet p5) {
        return (p5.mouseX >= this.x && p5.mouseX <= this.x + this.w && p5.mouseY >= this.y && p5.mouseY <= this.y + this.h);
    }

    /**
     * Cambia el estado de selección del área de texto dependiendo de si el ratón está
     * dentro del área de texto o no.
     *
     * @param p5 El objeto {@code PApplet} que representa la ventana de la aplicación.
     */
    public void isPressed(PApplet p5) {
        if (mouseOverTextField(p5)) {
            selected = true;
        } else {
            selected = false;
        }
    }

    /**
     * Limpia todo el texto en el área de texto.
     */
    public void clear() {
        this.text = "";
        updateLines();
    }
}
