package StudiveAppScreens;

import java.sql.*;

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

    // ----------------------------------------- * CONNECTION * ------------------------------------------ //
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

    // ------------------------------------------ * SELECTS * ------------------------------------------- //


    // --------------------------------------- * VERIFICATIONS * --------------------------------------- //
    public boolean Login(String username, String password){
        String q = "SELECT COUNT(*) AS n FROM usuario "+
                   " WHERE ID='"+username+"' AND PASSWORD='"+password+"' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("n")==1;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    // --------------------------------------- * INSERTS * --------------------------------------- //
    public void insertSubject (String subject, String color){
        String q = "INSERT INTO asignatura (ID, COLOR) " +
                   " VALUES ('"+subject+"', '"+color+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertDocument (String title, String description, String URL, String subjectName, String docType){
        try{
            String subjectId = getSubjectId(subjectName);
            String docTypeId = getTypeOfDocId(docType);

            if(subjectId == null || docTypeId == null) {
                System.out.println("not found");
            }
            String q = "INSERT INTO documento (ID, DESCRIPCION, URL, ASIGNATURA_ID, TIPODOCUMENTO_ID)" +
                    " VALUES ('"+title+"', '"+description+"','"+URL+"', '"+subjectId+"', '"+docTypeId+"')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void insertTest (String title, String description, String subjectName, String testType){
        try{
            String subjectId = getSubjectId(subjectName);
            String testTypeId = getTypeOfTestId(testType);

            if(subjectId == null || testTypeId == null) {
                System.out.println("not found");
            }
            String q = "INSERT INTO prueba (ID, DESCRIPCION, DONE, ASIGNATURA_ID, TIPODOCUMENTO_ID)" +
                    " VALUES ('"+title+"', '"+description+"','0','"+subjectId+"', '"+testTypeId+"')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    // --------------------------------------- * GET INFO * --------------------------------------- //
    public String[][] getSubjectsInfo() {
        int numRows = getNumRows("asignatura");
        String [][] subjectInfo = new String [numRows][2];
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
    public String getTypeOfDocId(String typeOfDoc) {
        String q = "SELECT ID FROM tipodocumento WHERE ID = '" + typeOfDoc + "'";
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
    public String getTypeOfTestId(String typeOfTest) {
        String q = "SELECT ID FROM tipoprueba WHERE ID = '" + typeOfTest + "'";
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

    public String[][] getSpecificInfoAboutTestOrDoc(String typeOfDoc, String title){
        int numRows = 0;
        String q = null;

        String docID = getTypeOfDocId(typeOfDoc);
        if (docID == null){
            numRows = getNumRows("prueba");
            q = "SELECT ID, TIPOPRUEBA_ID AS DOCTYPE, DESCRIPCION FROM prueba WHERE ID = '"+title+"' ";
        }
        else {
            numRows = getNumRows("documento");
            q = "SELECT ID, TIPODOCUMENTO_ID AS DOCTYPE, DESCRIPCION FROM documento WHERE ID = '"+title+"' ";
        }
        String[][] Info = new String[numRows][];

        try{
            ResultSet rs = query.executeQuery(q);
            int rows = 0;
            while (rs.next()) {
                Info[rows][0] = rs.getString("ID");
                Info[rows][1] = rs.getString("DOCTYPE");
                Info[rows][2] = rs.getString("DESCRIPCION");
                rows++;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return Info;
    }

    public String getDescription (String typeofDoc, String titleOfDoc){
        String q = "SELECT DESCRIPCION FROM '"+typeofDoc+"' WHERE ID = '"+titleOfDoc+"'";
        try {
            ResultSet rs = query.executeQuery(q);
            if (rs.next()) {
                return rs.getString("DESCRIPCION");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public String getURL (String typeofDoc, String titleOfDoc){
        String q = "SELECT URL FROM '"+typeofDoc+"' WHERE ID = '"+titleOfDoc+"'";
        try {
            ResultSet rs = query.executeQuery(q);
            if (rs.next()) {
                return rs.getString("URL");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public String getTitle (String typeofDoc, String titleOfDoc){
        String q = "SELECT ID FROM '"+typeofDoc+"' WHERE ID = '"+titleOfDoc+"'";
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
    public String getFecha (String typeofDoc, String titleOfDoc){
        String q = "SELECT FECHA FROM '"+typeofDoc+"' WHERE ID = '"+titleOfDoc+"'";
        try {
            ResultSet rs = query.executeQuery(q);
            if (rs.next()) {
                return rs.getString("FECHA");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public String[][] getTotalOfDoneTests (String doneOrNotDone){
        String stateOfTest = " ";
        if (doneOrNotDone == "done"){
            stateOfTest = "1";
        }
        String order = "SELECT ID, TIPOPRUEBA_ID, DESCRIPCION AS num FROM prueba WHERE DONE = '"+stateOfTest+"' ";
        int numFilesThatMatchQuery = getNumFilesThatMatchQuery(order);
        String[][] totalOfSaidState = new String[numFilesThatMatchQuery][3];
        String q = " SELECT ID, TIPOPRUEBA_ID, DESCRIPCION FROM prueba WHERE DONE = '"+stateOfTest+"' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                totalOfSaidState[n][0] = rs.getString("ID");
                totalOfSaidState[n][1] = rs.getString("TIPOPRUEBA_ID");
                totalOfSaidState[n][2] = String.valueOf(rs.getInt("DESCRIPCION"));
                n++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return totalOfSaidState;
    }


    // ----------------------------------------- * UPDATES * ----------------------------------------- //
    public void updateDone(String typeOfDoc, String title){
        String docType = getTypeOfDocId(typeOfDoc);
        if (docType != null){
            docType = "prueba";
            String date = getFecha(docType,title);
            if (date != null){
                try{
                    String q = "UPDATE '"+docType+"' SET DONE = '1' WHERE ID = '"+title+"' ";
                    query.executeQuery(q);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }
    }

    public void updateInfo(String typeOfDoc, String title, String description, String fechaOrURL){
        String q = null;
        String docType = getTypeOfDocId(typeOfDoc);

        if (docType != null){
            docType = "documento";
            String oldTitle = getTitle(docType, title);
            String oldDescription = getDescription(docType, title);
            String oldURL = getURL(docType, title);

            try {
                if (title != null && description == null && fechaOrURL == null) {
                    q = "UPDATE documento SET ID = '" + title + "' WHERE ID = '" + oldTitle + "' ";
                } else if (title == null && description != null && fechaOrURL == null) {
                    q = "UPDATE documento SET DESCRIPCION = '" + description + "' WHERE DESCRIPCION = '" + oldDescription + "'";
                } else if (title == null && description == null && fechaOrURL != null) {
                    q = "UPDATE documento SET URL = '"+fechaOrURL+"' WHERE URL = '"+oldURL+"'";
                }
                else {
                    q = "UPDATE documento SET ID = '"+title+"' WHERE ID ='"+oldTitle+"', " +
                        "SET DESCRIPCION = '"+description+"' WHERE DESCRIPCION = '"+oldDescription+"', " +
                        "SET URL = '"+fechaOrURL+"' WHERE URL = '"+oldURL+"'";
                }
                query.execute(q);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            docType = "prueba";
            String oldTitle = getTitle(docType, title);
            String oldDescription = getDescription(docType, title);
            String oldFecha = getFecha(docType, title);

            try {
                if (title != null && description == null && fechaOrURL == null) {
                    q = "UPDATE prueba SET ID = '" + title + "' WHERE ID = '" + oldTitle + "' ";
                } else if (title == null && description != null && fechaOrURL == null) {
                    q = "UPDATE prueba SET DESCRIPCION = '" + description + "' WHERE DESCRIPCION = '" + oldDescription + "'";
                } else if (title == null && description == null && fechaOrURL != null) {
                    q = "UPDATE prueba SET FECHA = '"+fechaOrURL+"' WHERE FECHA = '"+oldFecha+"'";
                }
                else {
                    q = "UPDATE prueba SET ID = '"+title+"' WHERE ID ='"+oldTitle+"', " +
                        "SET DESCRIPCION = '"+description+"' WHERE DESCRIPCION = '"+oldDescription+"', " +
                        "SET FECHA  = '"+fechaOrURL+"' WHERE FECHA = '"+oldFecha+"'";
                }
                query.execute(q);
            } catch (Exception e) {
                System.out.println(e);
            }
            }
        }

    // ------------------------------------- * GEN. FUNCTIONS * ------------------------------------- //
    public int getNumRows(String tableName){
        String q = "SELECT COUNT(*) AS num FROM "+ tableName;
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public int getNumFilesThatMatchQuery(String q){
        try{
            ResultSet rs = query.executeQuery(q);
            rs.next();
            return rs.getInt("num");
        } catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    // --------------------------------------- * OTHERS * --------------------------------------- //
}
