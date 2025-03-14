package StudiveAppScreens;

import com.jogamp.common.util.IntObjectHashMap;

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
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return info;
    }
}
