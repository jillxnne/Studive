package StudiveAppLayout;

/**
 * Clase {@code Layout} que define constantes estáticas relacionadas con el diseño
 * visual y la disposición de elementos en la interfaz gráfica de la aplicación Studive.
 *
 * Esta clase sirve como referencia global para establecer dimensiones y proporciones
 * usadas en diferentes partes del diseño, como el alto de la pantalla, el tamaño de la barra principal
 * o la anchura reservada para ciertas secciones de contenido.
 *
 * No contiene métodos, únicamente variables públicas estáticas que actúan como constantes de diseño.
 */
public class Layout {

    /**
     * Altura total de la pantalla en píxeles.
     * Se utiliza para establecer el alto general de la interfaz.
     */
    public static float FullScreenheight = 1080;

    /**
     * Altura de la barra inferior o barra principal (en píxeles).
     * Forma parte del diseño fijo de la interfaz.
     */
    public static float MainBarheight = 200;

    /**
     * Anchura total de la barra principal o barra inferior (en píxeles).
     */
    public static float MainBarwidth = 1920;

    /**
     * Anchura utilizada para identificar elementos o secciones específicas en pantalla,
     * como áreas reservadas para información del usuario o contenido destacado.
     */
    public static float IDwidth = 1000;

    /**
     * Anchura estándar usada para mostrar listas de lecciones recientes u otros elementos similares.
     */
    public static float RecentLecturewidth = 500;
}
