package StudiveAppScreens;

import java.sql.*;

/**
 * La clase {@code DataBase} proporciona una interfaz para interactuar con una base de datos MySQL.
 * Incluye métodos para conectar a la base de datos, realizar inserciones, actualizaciones y consultas
 * relacionadas con usuarios, asignaturas y flashcards.
 */
public class DataBase {

    /** Conexión activa a la base de datos. */
    Connection c;

    /** Objeto para ejecutar consultas SQL. */
    Statement query;

    /** Nombre de usuario para acceder a la base de datos. */
    String user;

    /** Contraseña del usuario para la base de datos. */
    String password;

    /** Nombre de la base de datos a la que se conecta. */
    String databaseName;

    /** Indica si la conexión con la base de datos fue exitosa. */
    boolean connectat = false;

    /**
     * Constructor que inicializa las credenciales de conexión.
     *
     * @param user         Nombre de usuario.
     * @param password     Contraseña del usuario.
     * @param databaseName Nombre de la base de datos.
     */
    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    /**
     * Establece la conexión con la base de datos usando JDBC.
     */
    public void connect(){
        try{
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD!");
            connectat = true;
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Verifica si el usuario existe en la base de datos.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return {@code true} si las credenciales son válidas, {@code false} en caso contrario.
     */
    public boolean Login(String username, String password){
        String q = "SELECT COUNT(*) AS n FROM usuario WHERE ID='" + username + "' AND PASSWORD='" + password + "'";
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n") == 1;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     */
    public void insertNewUser(String username, String password){
        String q = "INSERT INTO usuario (ID, PASSWORD) VALUES ('" + username + "','" + password + "')";
        try{
            query.execute(q);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Inserta una nueva asignatura.
     *
     * @param subject Nombre de la asignatura.
     * @param color   Color asociado a la asignatura.
     */
    public void insertSubject(String subject, String color){
        String q = "INSERT INTO asignatura (ID, COLOR) VALUES ('" + subject + "', '" + color + "')";
        try{
            query.execute(q);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Inserta los datos generales de una flashcard en la tabla {@code prueba}.
     *
     * @param title       Título de la flashcard.
     * @param description Descripción de la flashcard.
     * @param subjectName Nombre de la asignatura a la que pertenece.
     */
    public void insertFlashCardGenInfo(String title, String description, String subjectName){
        String typeTest = "FLASHCARD";
        try{
            String subjectId = getSubjectId(subjectName);
            String q = "INSERT INTO prueba (ID, DESCRIPCION, HECHO, ASIGNATURA_ID, TIPOPRUEBA_ID)" +
                    " VALUES ('" + title + "', '" + description + "', '0', '" + subjectId + "', '" + typeTest + "')";
            query.execute(q);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Inserta preguntas y respuestas asociadas a una flashcard.
     *
     * @param questions Arreglo de preguntas.
     * @param answers   Arreglo de respuestas correspondientes.
     * @param testId    ID de la flashcard.
     */
    public void insertFlashCardInfo(String[] questions, String[] answers, String testId) {
        String q = "INSERT INTO pregunta (ID, PRUEBA_ID) VALUES ";
        String s = "INSERT INTO opcion (ID, PREGUNTA_ID) VALUES ";
        try {
            for (int i = 0; i < questions.length; i++) {
                q += "('" + questions[i] + "', '" + testId + "')";
                if (i < questions.length - 1) q += ", ";
            }
            query.execute(q);
            for (int i = 0; i < answers.length; i++) {
                s += "('" + answers[i] + "', '" + questions[i] + "')";
                if (i < answers.length - 1) s += ", ";
            }
            query.execute(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Obtiene información de todas las asignaturas almacenadas.
     *
     * @return Arreglo bidimensional con ID y color de cada asignatura.
     */
    public String[][] getSubjectsInfo() {
        int numRows = getNumRows("asignatura");
        String[][] subjectInfo = new String[numRows][2];
        String q = "SELECT * FROM asignatura";
        try {
            ResultSet rs = query.executeQuery(q);
            int rows = 0;
            while (rs.next()) {
                subjectInfo[rows][0] = rs.getString("ID");
                subjectInfo[rows][1] = rs.getString("COLOR");
                rows++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return subjectInfo;
    }

    /**
     * Devuelve todas las pruebas con su estado (hechas o no).
     *
     * @param doneOrNotDone 0 si no están hechas, 1 si están hechas.
     * @return Arreglo con ID, tipo y descripción de cada prueba.
     */
    public String[][] getTotalOfStateOfTests(int doneOrNotDone){
        String qNum = "SELECT COUNT(*) AS num FROM prueba WHERE HECHO ='" + doneOrNotDone + "'";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[][] totalOfSaidState = new String[numRowsOfTest][3];
        String q = "SELECT ID, TIPOPRUEBA_ID, DESCRIPCION FROM prueba WHERE HECHO = '" + doneOrNotDone + "'";
        try {
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()) {
                totalOfSaidState[n][0] = rs.getString("ID");
                totalOfSaidState[n][1] = rs.getString("TIPOPRUEBA_ID");
                totalOfSaidState[n][2] = rs.getString("DESCRIPCION");
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalOfSaidState;
    }

    /**
     * Devuelve todas las pruebas de un tipo para una asignatura específica.
     *
     * @param subject ID de la asignatura.
     * @return Arreglo con ID, tipo de prueba y descripción.
     */
    public String[][] getTotalOfTypeOfLessonOfASubject(String subject){
        String qNum = "SELECT COUNT(*) AS num FROM prueba WHERE ASIGNATURA_ID ='" + subject + "'";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[][] totalOfSaidState = new String[numRowsOfTest][3];
        String q = "SELECT ID, TIPOPRUEBA_ID, DESCRIPCION FROM prueba WHERE ASIGNATURA_ID = '" + subject + "'";
        try {
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()) {
                totalOfSaidState[n][0] = rs.getString("ID");
                totalOfSaidState[n][1] = rs.getString("TIPOPRUEBA_ID");
                totalOfSaidState[n][2] = rs.getString("DESCRIPCION");
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalOfSaidState;
    }

    /**
     * Obtiene las preguntas asociadas a una flashcard.
     *
     * @param testId ID de la flashcard.
     * @return Arreglo de preguntas.
     */
    public String[] getQuestionsOfAFlashCard(String testId){
        String qNum = "SELECT COUNT(*) AS num FROM pregunta WHERE PRUEBA_ID ='" + testId + "'";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[] totalOfQuestions = new String[numRowsOfTest];
        String q = "SELECT ID FROM pregunta WHERE PRUEBA_ID = '" + testId + "'";
        try {
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()) {
                totalOfQuestions[n] = rs.getString("ID");
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalOfQuestions;
    }

    /**
     * Obtiene las respuestas correspondientes a un conjunto de preguntas.
     *
     * @param questions Arreglo de preguntas.
     * @return Arreglo de respuestas.
     */
    public String[] getAnswersOfAFlashCard(String[] questions) {
        String[] answers = new String[questions.length];
        for (int i = 0; i < questions.length; i++) {
            String q = "SELECT ID FROM opcion WHERE PREGUNTA_ID = '" + questions[i] + "'";
            try {
                ResultSet rs = query.executeQuery(q);
                if (rs.next()) {
                    answers[i] = rs.getString("ID");
                } else {
                    answers[i] = "";
                }
            } catch (Exception e) {
                System.out.println("Error fetching answer for question: " + questions[i]);
                e.printStackTrace();
                answers[i] = "";
            }
        }
        return answers;
    }

    /**
     * Marca una prueba como hecha.
     *
     * @param title ID de la prueba.
     */
    public void updateDone(String title) {
        try {
            String q = "UPDATE prueba SET HECHO = '1' WHERE ID = '" + title + "'";
            query.execute(q);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Actualiza la fecha de una prueba con la hora actual del sistema.
     *
     * @param cardID ID de la prueba.
     */
    public void updateFechaOfTest(String cardID){
        String q = "UPDATE prueba SET FECHA = CURRENT_TIMESTAMP WHERE ID ='" + cardID + "'";
        try {
            query.execute(q);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Devuelve el número de filas de una tabla.
     *
     * @param tableName Nombre de la tabla.
     * @return Número de filas.
     */
    public int getNumRows(String tableName){
        String q = "SELECT COUNT(*) AS num FROM " + tableName;
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        } catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    /**
     * Ejecuta una consulta COUNT personalizada y devuelve el número de filas.
     *
     * @param q Consulta SQL que retorna un COUNT con alias 'num'.
     * @return Número de resultados.
     */
    public int getNumFilesThatMatchQuery(String q){
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        } catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    /**
     * Obtiene el ID de una asignatura por su nombre.
     *
     * @param subjectName Nombre de la asignatura.
     * @return ID de la asignatura o {@code null} si no se encuentra.
     */
    public String getSubjectId(String subjectName) {
        String q = "SELECT ID FROM asignatura WHERE ID = '" + subjectName + "'";
        try {
            ResultSet rs = query.executeQuery(q);
            if (rs.next()) {
                return rs.getString("ID");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
