package StudiveAppQuestionaire;
import processing.core.PApplet;

/**
 * La clase {@code Card} representa una tarjeta que contiene una pregunta y su respuesta.
 * La clase proporciona métodos para mostrar tanto la pregunta como la respuesta en una
 * interfaz gráfica utilizando el objeto {@code PApplet} de la biblioteca Processing.
 */
public class Card {

    /** La pregunta contenida en la tarjeta */
    String question;

    /** La respuesta correspondiente a la pregunta */
    public String answer;

    /**
     * Constructor de la clase {@code Card}.
     * Inicializa una nueva tarjeta con una pregunta y su respuesta asociada.
     *
     * @param question La pregunta que se almacenará en la tarjeta.
     * @param answer La respuesta correspondiente a la pregunta.
     */
    public Card(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Muestra la pregunta en la pantalla en las coordenadas especificadas.
     * Utiliza el objeto {@code PApplet} para configurar el tamaño del texto, el color
     * y la alineación antes de dibujar el texto de la pregunta en la ubicación indicada.
     *
     * @param app El objeto {@code PApplet} que representa la ventana de la aplicación.
     * @param x La coordenada X donde se mostrará el texto.
     * @param y La coordenada Y donde se mostrará el texto.
     */
    void displayQuestion(PApplet app, float x, float y) {
        app.textAlign(PApplet.CENTER, PApplet.CENTER);  // Alineación del texto
        app.textSize(20);  // Tamaño del texto
        app.fill(0);  // Color negro para el texto
        app.text("Q: " + question, x, y);  // Muestra la pregunta en el área definida
    }

    /**
     * Muestra la respuesta en la pantalla en las coordenadas especificadas.
     * Similar a {@code displayQuestion}, pero muestra la respuesta en lugar de la pregunta.
     *
     * @param app El objeto {@code PApplet} que representa la ventana de la aplicación.
     * @param x La coordenada X donde se mostrará el texto.
     * @param y La coordenada Y donde se mostrará el texto.
     */
    void displayAnswer(PApplet app, float x, float y) {
        app.textAlign(PApplet.CENTER, PApplet.CENTER);  // Alineación del texto
        app.textSize(20);  // Tamaño del texto
        app.fill(0);  // Color negro para el texto
        app.text("A: " + answer, x, y);  // Muestra la respuesta en el área definida
    }
}
