package StudiveAppScreens;

import com.jogamp.common.util.IntObjectHashMap;

import javax.swing.text.Style;
import javax.xml.crypto.Data;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBase {
    Connection c;
    Statement query;
    String user, password, databaseName;
    boolean connectat = false;

    public DataBase(String user, String password, String databaseName){
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
    }

    public void connect(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, user, password);
            query = c.createStatement();
            System.out.println("Connectat a la BBDD!");
            connectat = true;
        } catch (Exception e){
            System.out.println(e);
        }
    }

    // retorna la informació d'una casella
    public String getInfo(String nomColumna, String nomTaula, String key, String id){
        try{
            String q =  "SELECT "+ nomColumna +
                        " FROM " + nomTaula +
                        " WHERE "+key+" = '"+id+"' ";
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getString(nomColumna);
        } catch(Exception e){
            System.out.println(e);
        }
        return "";
    }

    // retorna el número total de files d'una taula
    public int getNumFilesTaula(String nomTaula){
        String q = "SELECT COUNT(*) AS num FROM "+ nomTaula;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        } catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // retorna totes les caselles d'una columna
    public String[] getInfoArray(String nomTaula, String nomColumna){
        int n = getNumFilesTaula(nomTaula);
        String[] info = new String[n];
        String q = "SELECT "+ nomColumna +
                   " FROM " + nomTaula ;
               //    " ORDER BY " + nomColumna + "ASC"; opcional si en ordre alfabètic
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()){ // fa getNext(), hasNext(), resetNext()...
                info[f] = rs.getString(1);
                    f++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return info;
    }

    // retorna totes les caselles (files i columnes) d'una taula
    public String[][] getInfoArray2D(){
        int nf = getNumFilesTaula("asignatura");
        String[][] info = new String[nf][2];
        String q = "SELECT * FROM asignatura ORDER BY ID ASC"; //* = ID, COLOR
        try{
            ResultSet rs = query.executeQuery(q);
            int f = 0;
            while (rs.next()){
                info[f][0] = rs.getString("ID"); //si és número String.valueof(); ** mirar si date tmb ho és
                info[f][1] = rs.getString("COLOR");
                f++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return info;
    }

    public int getNumFilesMatchQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        } catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // retorn de filtratge de una taula
    public String [][] getInfoPregunta(){
        String qNF = "SELECT COUNT(*) AS num FROM opcion WHERE CORRECTO = 'no' ";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][4];
        String q = " SELECT * FROM opcion WHERE CORRECTO = 'no' "; // * perquè s'ha de seleccionar tot 
        System.out.println(q);
        try{
           ResultSet rs = query.executeQuery(q);
           int n = 0;
           while (rs.next()){
               info[n][0] = rs.getString("ID");
               info[n][1] = rs.getString("CORRECTO");
               info[n][2] = String.valueOf(rs.getInt("NUMERO"));
               info[n][3] = rs.getString("PREGUNTA_ID");
               n++;
           }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return info;
    }

    public String[][] getInfoOfTwoRelatedTables(){
        String qNF = "SELECT COUNT(*) AS num FROM opcion o, pregunta p WHERE p.ID = o.PREGUNTA_ID";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];
        String q = " SELECT p.ENUNCIADO, o.ID FROM opcion o, pregunta p WHERE p.ID = o.PREGUNTA_ID ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                info[n][0] = rs.getString("p.ENUNCIADO");
                info[n][1] = rs.getString("o.ID");
                n++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return info;
    }

    public int getCalculationForSomething(String nomUsuari){
        String q = "SELECT XX AS XX FROM SSS WHERE XXX = '" + nomUsuari+" ' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("XX");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // ------------------------------------------ * SELECTS * ------------------------------------------- //

    public String getSubjectForLesson (String subject){
        String q = "SELECT ID from asignatura WHERE ID = '"+subject+"' ";
    }


    // --------------------------------------- * VERIFICATIONS * --------------------------------------- //
    public boolean Login(String username, String password){
        String q = "SELECT COUNT(*) AS n FROM usuario " +
                   " WHERE ID= '"+username+"' AND PASSWORD = '"+password+"'";
        System.out.println(q);
        try {
            ResultSet rs = query.executeQuery(q);
            rs.next();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    // execute si no ha de retornar, executeQuery si ha de retornar

    // --------------------------------------- * INSERTS * --------------------------------------- //
    public void insertSubject (String subject, String color){
        String q = "INSERT INTO asignatura (ID, COLOR) VALUES ('"+subject+"', '"+color+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertDocument (String ID, String descripcion, String URL, String Subject, String TypeOfDocs){
        String q = "INSERT INTO documento (ID, DESCRIPCION, URL, ASIGNATURA_ID, TIPODOCUMENTO_ID VALUES" +
                " ()";
    }

    public void insertSomething(String username, String password){
        String q = "INSERT INTO usuario (ID, PASSWORD) VALUES ('"+username+"', '"+password+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // delete
    public void deleteSomething (String username){
        String q = "DELETE FROM usuario WHERE SUBSTR(ID, 1,1) = '"+username+"' ";
        // DELETE FROM usuario --> delete everything, DELETE FROM usuario WHERE ID = .. --> una fila
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // update
    public void updateSomething (String username, String oldUsername){
        String q = "UPDATE usuario SET ID = '"+username+"' WHERE ID ='"+oldUsername+"' ";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // Search : SELECT usuario FROM  xx WHERE xx LIKE
    public String[][] preguntesCercador(String clauCerca){

        String qNF = "SELECT COUNT(*) AS num FROM usuario WHERE ID LIKE '%"+ clauCerca+"%'";
        int nf = getNumFilesMatchQuery(qNF);
        String[][] info = new String[nf][2];
        String q = "SELECT ID, PASSWORD FROM usuario WHERE ID LIKE '%"+ clauCerca+"%'";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n=0;
            while(rs.next()){
                info[n][0] = rs.getString("ID");
                info[n][1] = rs.getString("PASSWORD");
                n++;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return info;
    }
}
