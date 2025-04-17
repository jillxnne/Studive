package StudiveAppColors;

import processing.core.PApplet;

/**
 * Clase que gestiona la paleta de colores utilizada en la aplicación.
 * Permite almacenar, mostrar y seleccionar colores desde una lista de opciones.
 */
public class Colors {
    // Nombre del color seleccionado
    String colorname = " ";

    // Array que almacena los colores definidos
    int[] colors;

    // Índice del color actualmente seleccionado
    int selectedColor = -1;

    /**
     * Constructor de la clase Colors. Inicializa los colores predefinidos.
     * @param p5 Instancia de PApplet, necesaria para trabajar con las funcionalidades gráficas.
     */
    public Colors(PApplet p5) {
        this.setColors(p5);
    }

    /**
     * Define los colores preestablecidos en el array 'colors'.
     * @param p5 Instancia de PApplet, necesaria para generar los colores.
     */
    void setColors(PApplet p5) {
        this.colors = new int[5];
        // Definición de los colores utilizando el método color de PApplet
        this.colors[0] = p5.color(102, 84, 94); // uno
        this.colors[1] = p5.color(163, 145, 147); // dos
        this.colors[2] = p5.color(170, 111, 115); // tres
        this.colors[3] = p5.color(238, 169, 144); // cuatro
        this.colors[4] = p5.color(246, 224, 181); // cinco
    }

    /**
     * Devuelve el número total de colores definidos.
     * @return El número de colores en el array 'colors'.
     */
    int getNumColors() {
        return this.colors.length;
    }

    /**
     * Devuelve el color en una posición específica del array 'colors'.
     * @param i Índice del color que se desea obtener.
     * @return El color correspondiente al índice proporcionado.
     */
    int getColorAt(int i) {
        return this.colors[i];
    }

    /**
     * Muestra los colores en pantalla como círculos interactivos.
     * Los colores se presentan alineados en la pantalla y, si un color es seleccionado, se resalta.
     * @param p5 Instancia de PApplet, necesaria para realizar las operaciones gráficas.
     * @param x Posición X inicial para la visualización.
     * @param y Posición Y inicial para la visualización.
     * @param w Ancho total disponible para la visualización de los colores.
     */
    public void displayColors(PApplet p5, float x, float y, float w) {
        p5.pushStyle();
        p5.fill(0);
        p5.textAlign(p5.LEFT);
        p5.textSize(36);
        float wc = w / getNumColors(); // Ancho individual de cada color
        // Dibuja los colores como círculos
        for (int i = 0; i < getNumColors(); i++) {
            float cx = x + i * wc + wc / 2;  // Posición X del círculo
            float cy = y + wc / 2;  // Posición Y del círculo
            float radius = wc / 2;  // Radio del círculo

            if (i == selectedColor) {
                p5.stroke(0);
                p5.strokeWeight(5);  // Resalta el color seleccionado
            } else {
                p5.noStroke();
                p5.fill(getColorAt(i)); // Rellena con el color correspondiente
            }

            p5.ellipse(cx, cy, radius * 2, radius * 2);  // Dibuja el círculo
        }
        p5.popStyle();
    }

    /**
     * Verifica si el ratón está sobre uno de los colores y selecciona el color si se hace clic.
     * @param p5 Instancia de PApplet, necesaria para trabajar con la interacción del ratón.
     * @param x Posición X inicial para la interacción.
     * @param y Posición Y inicial para la interacción.
     * @param w Ancho total disponible para la selección de colores.
     */
    public void checkMouseOver(PApplet p5, float x, float y, float w) {
        float wc = w / getNumColors(); // Ancho individual de cada color
        // Verifica si el ratón está sobre alguno de los colores
        for (int i = 0; i < getNumColors(); i++) {
            float cx = x + i * wc + wc / 2;
            float cy = y + wc / 2;
            float radius = wc / 2;

            if (p5.dist(p5.mouseX, p5.mouseY, cx, cy) < radius) {
                if (p5.mousePressed) {
                    selectedColor = i;  // Selecciona el color si se hace clic
                }
                break;
            }
        }
    }

    /**
     * Devuelve el nombre del color seleccionado como una cadena de texto.
     * El nombre corresponde al índice del color seleccionado.
     * @param p5 Instancia de PApplet, necesaria para la interacción con la interfaz.
     * @return El nombre del color seleccionado como una cadena de texto.
     */
    public String getSelectedColorAsString(PApplet p5) {
        switch (selectedColor) {
            case -1:
                colorname = ""; // No hay color seleccionado
                break;
            case 0:
                colorname = "one"; // Primer color
                break;
            case 1:
                colorname = "two"; // Segundo color
                break;
            case 2:
                colorname = "three"; // Tercer color
                break;
            case 3:
                colorname = "four"; // Cuarto color
                break;
            case 4:
                colorname = "five"; // Quinto color
                break;
        }
        return colorname; // Retorna el nombre del color seleccionado
    }
}
