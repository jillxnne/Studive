package StudiveAppGUI;

import processing.core.PApplet;

/**
 * Clase que representa un botón interactivo en la interfaz gráfica de la aplicación.
 * Los botones tienen un texto, un color de fondo, un borde y responden a la interacción del usuario,
 * como el paso del mouse por encima o la presión del mouse.
 */
public class Button {

    /** Coordenada X de la posición del botón */
    float x;

    /** Coordenada Y de la posición del botón */
    float y;

    /** Ancho del botón */
    float w;

    /** Alto del botón */
    float h;

    /** Color de relleno del botón cuando está habilitado */
    int fillColor;

    /** Color del borde del botón */
    int strokeColor;

    /** Color de relleno del botón cuando el mouse está sobre él */
    int fillColorOver;

    /** Color de relleno del botón cuando está deshabilitado */
    int fillColorDisabled;

    /** Texto que aparece en el botón */
    String textBoto;

    /** Indica si el botón está habilitado o no */
    boolean enabled;

    /**
     * Constructor que inicializa un botón con las propiedades especificadas.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @param text Texto que se mostrará en el botón.
     * @param x Coordenada X de la posición del botón.
     * @param y Coordenada Y de la posición del botón.
     * @param w Ancho del botón.
     * @param h Alto del botón.
     */
    public Button(PApplet p5, String text, float x, float y, float w, float h){
        this.textBoto = text;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.enabled = true;
        this.fillColor = p5.color(170,111,115);  // Color predeterminado del botón.
        this.fillColorOver = p5.color(238,169,144);  // Color cuando el mouse está sobre el botón.
        this.fillColorDisabled = p5.color(170,111,115);  // Color cuando el botón está deshabilitado.
        this.strokeColor = p5.color(0);  // Color del borde del botón.
    }

    /**
     * Método que verifica si el botón está habilitado.
     *
     * @return true si el botón está habilitado, false si está deshabilitado.
     */
    public boolean isEnabled(){
        return  this.enabled;
    }

    /**
     * Método que dibuja el botón en la pantalla.
     * Cambia el color del botón dependiendo de su estado (habilitado, deshabilitado, mouse sobre él).
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     */
    public void display(PApplet p5){
        p5.pushStyle();
        if(!enabled){
            p5.fill(fillColorDisabled);  // Si está deshabilitado, usa un color de relleno específico.
        }
        else if(mouseOverButton(p5)){
            p5.fill(fillColorOver);  // Si el mouse está sobre el botón, cambia el color.
        }
        else{
            p5.fill(fillColor);  // Color predeterminado.
        }
        p5.stroke(strokeColor); p5.strokeWeight(0);
        p5.rect(this.x, this.y, this.w, this.h, 10);  // Dibuja el botón como un rectángulo con bordes redondeados.

        p5.fill(0); p5.textAlign(p5.CENTER); p5.textSize(20);  // Establece el estilo de texto.
        p5.text(textBoto, this.x + this.w/2, this.y + this.h/2 + 10);  // Dibuja el texto en el centro del botón.
        p5.popStyle();
    }

    /**
     * Método que verifica si el mouse está sobre el área del botón.
     *
     * @param p5 Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @return true si el mouse está sobre el botón, false en caso contrario.
     */
    public boolean mouseOverButton(PApplet p5){
        return (p5.mouseX >= this.x) && (p5.mouseX <= this.x + this.w) &&
                (p5.mouseY >= this.y) && (p5.mouseY <= this.y + this.h);
    }

    /**
     * Método que verifica si el botón ha sido clicado.
     *
     * @param app Objeto de la clase PApplet para acceder a las funcionalidades de Processing.
     * @return true si el botón ha sido clicado, false en caso contrario.
     */
    public boolean checkClick(PApplet app) {
        return mouseOverButton(app) && app.mousePressed;  // Devuelve true si el botón está presionado y el mouse está sobre él.
    }
}
