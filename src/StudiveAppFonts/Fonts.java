package StudiveAppFonts;

import processing.core.PApplet;
import processing.core.PFont;

import static StudiveAppFonts.Sizes.*;

/**
 * Clase que gestiona las fuentes tipográficas utilizadas en la aplicación.
 * Permite almacenar y acceder a diferentes estilos de fuentes, así como visualizarlas en pantalla.
 */
public class Fonts {

    // Array que almacena las fuentes utilizadas en la aplicación
    PFont[] fonts;

    /**
     * Constructor de la clase Fonts. Inicializa las fuentes predefinidas.
     * @param p5 Instancia de PApplet, necesaria para crear y gestionar las fuentes tipográficas.
     */
    public Fonts(PApplet p5) {
        this.setFonts(p5);
    }

    /**
     * Define las fuentes preestablecidas en el array 'fonts'.
     * Cada fuente se asocia a un tamaño específico definido en la clase `Sizes`.
     * @param p5 Instancia de PApplet, utilizada para crear las fuentes a partir de los archivos.
     */
    void setFonts(PApplet p5) {
        this.fonts = new PFont[3];
        // Se asignan las fuentes con sus respectivos tamaños
        this.fonts[0] = p5.createFont("data/Griffiths.otf", TitleSize); // Fuente para títulos
        this.fonts[1] = p5.createFont("data/NostalgicWhispers.ttf", MidTitleSize); // Fuente para títulos secundarios
        this.fonts[2] = p5.createFont("data/SimpleDay.otf", SubtitleSize); // Fuente para subtítulos
    }

    /**
     * Devuelve la primera fuente (para títulos).
     * @return La primera fuente en el array 'fonts'.
     */
    public PFont getFirstFont() {
        return this.fonts[0];
    }

    /**
     * Devuelve la segunda fuente (para subtítulos).
     * @return La segunda fuente en el array 'fonts'.
     */
    public PFont getSecondFont() {
        return this.fonts[1];
    }

    /**
     * Devuelve la tercera fuente (para párrafos).
     * @return La tercera fuente en el array 'fonts'.
     */
    public PFont getThirdFont() {
        return this.fonts[2];
    }
}
