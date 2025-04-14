package StudiveAppScreens;

import java.awt.desktop.SystemEventListener;
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
    public void insertNewUser(String username, String password){
        String q = "INSERT INTO usuario (ID, PASSWORD) " +
                   " VALUES ('"+username+"','"+password+"')";
        System.out.println(q);
        try{
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
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
    public void insertTestOrFlashCardGenInfo (String title, String description, String subjectName, String testType){
        try{
            String subjectId = getSubjectId(subjectName);
            String testTypeId = getTypeOfTestId(testType);

            if(subjectId == null || testTypeId == null) {
                System.out.println("not found");
            }
            String q = "INSERT INTO prueba (ID, DESCRIPCION, DONE, ASIGNATURA_ID, TIPOPRUEBA_ID)" +
                    " VALUES ('"+title+"', '"+description+"','0','"+subjectId+"', '"+testTypeId+"')";
            System.out.println(q);
            query.execute(q);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void insertFlashCardInfo(String[] questions, String[] answers, String testId) {
        String q = "INSERT INTO pregunta (ID, PRUEBA_ID) VALUES ";
        String s = "INSERT INTO opcion (ID, PREGUNTA_ID) VALUES ";

        try {
            for (int i = 0; i < questions.length; i++) {
                q += "('" + questions[i] + "', '" + testId + "')";
                if (i < questions.length - 1) q += ", ";
            }
            System.out.println(q);
            query.execute(q);

            for (int i = 0; i < answers.length; i++) {
                s += "('" + answers[i] + "', '" + questions[i] + "')";
                if (i < answers.length - 1) s += ", ";
            }
            System.out.println(s);
            query.execute(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void insertTestData(String testId, String[] questions, String[] answers, int[] correctAnswerIndexes) {
        String insertQuestionsQuery = "INSERT INTO pregunta (ID, PRUEBA_ID) VALUES ";
        String insertAnswersQuery = "INSERT INTO opcion (PREGUNTA_ID, ID, CORRECTO, NUMERO) VALUES ";

        try {
            for (int i = 0; i < questions.length; i++) {
                insertQuestionsQuery += "('" + questions[i] +"', '" + testId + "')";
                if (i < questions.length - 1) insertQuestionsQuery += ", ";

                System.out.println(insertQuestionsQuery);
                query.execute(insertQuestionsQuery);

                for (int j = 0; j < 3; j++) {
                    int answerIndex = i * 3 + j;
                    insertAnswersQuery += "('" + questions[i] + "', '" + answers[answerIndex] + "', '" + correctAnswerIndexes[answerIndex] + "', '" + j + "')";
                    if (answerIndex < answers.length - 1) insertAnswersQuery += ", ";
                }
                System.out.println(insertAnswersQuery);
                query.execute(insertAnswersQuery);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ----------------------------- * GET INFO FOR STRINGS[][] * ----------------------------- //
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

    public String[][] getSpecificInfoAboutATestOrDoc(String typeOfDoc, String title){
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

    public String[][] getTotalOfStateOfTests (String doneOrNotDone){
        String stateOfTest = " ";
        if (doneOrNotDone == "done"){
            stateOfTest = "1";
        }
        String qNum = "SELECT COUNT(*) AS num FROM prueba WHERE DONE ='"+stateOfTest+"' ";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[][] totalOfSaidState = new String[numRowsOfTest][3];
        String q = " SELECT ID, TIPOPRUEBA_ID, DESCRIPCION FROM prueba WHERE DONE = '"+stateOfTest+"' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                totalOfSaidState[n][0] = rs.getString("ID");
                totalOfSaidState[n][1] = rs.getString("TIPOPRUEBA_ID");
                totalOfSaidState[n][2] = rs.getString("DESCRIPCION");
                n++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return totalOfSaidState;
    }

    public String[][] getTotalOfStateOfDocs (String doneOrNotDone){
        String stateOfTest = " ";
        if (doneOrNotDone == "done"){
            stateOfTest = "1";
        }
        String qNum = "SELECT COUNT(*) AS num FROM documento WHERE DONE ='"+stateOfTest+"' ";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[][] totalOfSaidState = new String[numRowsOfTest][3];
        String q = " SELECT ID, TIPODOCUMENTO_ID, DESCRIPCION FROM documento WHERE DONE = '"+stateOfTest+"' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                totalOfSaidState[n][0] = rs.getString("ID");
                totalOfSaidState[n][1] = rs.getString("TIPODOCUMENTO_ID");
                totalOfSaidState[n][2] = rs.getString("DESCRIPCION");
                n++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return totalOfSaidState;
    }

    public String[][] getTotalOfTestsAndDocsState(String[][] infoTest, String[][] infoDocs) {
        int totalRows = infoTest.length + infoDocs.length;
        String testsAndDocsState[][] = new String[totalRows][3];
        for (int i = 0; i < infoTest.length; i++) {
            System.arraycopy(infoTest[i], 0, testsAndDocsState[i], 0, 3);
        }
        for (int i = 0; i < infoDocs.length; i++) {
            System.arraycopy(infoDocs[i], 0, testsAndDocsState[i + infoTest.length], 0, 3);
        }
        return testsAndDocsState;
    }
    public String[][] getTotalOfTypeOfLessonOfASubject(String docType, String subject){
        String typeOfDocOrTest = getTypeOfDocId(docType);
        String parameter = " ";
        if (typeOfDocOrTest == null){
            typeOfDocOrTest = "prueba";
            parameter = "TIPOPRUEBA_ID";
        }
        else {
            typeOfDocOrTest = "documento";
            parameter = "TIPODOCUMENTO_ID";
        }
        String qNum = "SELECT COUNT(*) AS num FROM "+typeOfDocOrTest+" " +
                      " WHERE ASIGNATURA_ID ='"+subject+"' AND "+parameter+" = '"+docType+"' ";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[][] totalOfSaidState = new String[numRowsOfTest][3];
        String q = " SELECT ID, "+parameter+", DESCRIPCION FROM "+typeOfDocOrTest+" " +
                   " WHERE ASIGNATURA_ID = '"+subject+"' AND "+parameter+" = '"+docType+"' ";
        System.out.println(q);
        try{
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                totalOfSaidState[n][0] = rs.getString("ID");
                totalOfSaidState[n][1] = rs.getString(parameter);
                totalOfSaidState[n][2] = rs.getString("DESCRIPCION");
                n++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return totalOfSaidState;
    }

    public String[] getQuestionsOfAFlashCard(String testId){
        String qNum = "SELECT COUNT(*) AS num FROM pregunta " +
                      " WHERE PRUEBA_ID ='"+testId+"' ";
        int numRowsOfTest = getNumFilesThatMatchQuery(qNum);
        String[] totalOfQuestions = new String[numRowsOfTest];
        String q = " SELECT ID FROM pregunta " +
                   " WHERE PRUEBA_ID = '"+testId+"' ";
        System.out.println(q);
        try{
            System.out.println(q);
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()){
                totalOfQuestions[n] = rs.getString("ID");
                n++;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return totalOfQuestions;
    }

    public String[] getAnswersOfAFlashCard(String[] questions) {
        String[] answers = new String[questions.length];
        for (int i = 0; i < questions.length; i++) {
            String q = "SELECT ID FROM opcion WHERE PREGUNTA_ID = '" + questions[i] + "'";
            System.out.println("Query for question " + questions[i] + ": " + q);
            try {
                System.out.println(q);
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

    public String[] getQuestionsOfMultipleChoiceTest(String testId) {
        String qNum = "SELECT COUNT(*) AS num FROM pregunta WHERE PRUEBA_ID = '" + testId + "'";
        int numRows = getNumFilesThatMatchQuery(qNum);
        String[] questionTexts = new String[numRows];

        String q = "SELECT ID FROM pregunta WHERE PRUEBA_ID = '" + testId + "'";
        System.out.println(q);

        try {
            ResultSet rs = query.executeQuery(q);
            int n = 0;
            while (rs.next()) {
                questionTexts[n] = rs.getString("ID");
                n++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return questionTexts;
    }

    public String[] getAnswersOfMultipleChoiceTest(String[] testIds) {
        String[] answers = new String[testIds.length * 3];

        for (int i = 0; i < testIds.length; i++) {
            String q = "SELECT ID FROM opcion WHERE PREGUNTA_ID = '" + testIds[i] + "'";
            System.out.println(q);

            try {
                System.out.println(q);
                ResultSet rs = query.executeQuery(q);
                int j = 0;
                while (rs.next() && j < 3) {
                    answers[i * 3 + j] = rs.getString("ID");
                    j++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return answers;
    }

    public int[] getCorrectAnswerIndexesOfMultipleChoiceTest(String[] testIds) {
        int[] correctIndexes = new int[testIds.length];

        for (int i = 0; i < testIds.length; i++) {
            String q = "SELECT CORRECTO FROM opcion WHERE PREGUNTA_ID = '" + testIds[i] + "'";
            System.out.println(q);

            try {
                System.out.println(q);
                ResultSet rs = query.executeQuery(q);
                if (rs.next()) {
                    correctIndexes[i] = rs.getInt("CORRECTO");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return correctIndexes;
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

    public void updateInfoOfDocOrTest(String typeOfDoc, String title, String description, String fechaOrURL){
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
    // --------------------------------------- * GET IDS * --------------------------------------- //
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
    // ------------------------ * GET SPECIFIC INFO ABOUT DOCS OR TESTS * ------------------------ //
    public String getDescription (String typeofDoc, String titleOfDoc){
        String docType = getTypeOfDocId(typeofDoc);
        if (docType!=null){
            docType = "documento";
        }
        else{
            docType = "prueba";
        }
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
        String docType = getTypeOfDocId(typeofDoc);
        if (docType!=null){
            docType = "documento";
        }
        String q = "SELECT URL FROM '"+docType+"' WHERE ID = '"+titleOfDoc+"'";
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
        String docType = getTypeOfDocId(typeofDoc);
        if (docType!=null){
            docType = "documento";
        }
        else{
            docType = "prueba";
        }
        String q = "SELECT ID FROM '"+docType+"' WHERE ID = '"+titleOfDoc+"'";
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
        String docType = getTypeOfDocId(typeofDoc);
        if (docType!=null){

        }
        else{
            docType = "prueba";
        }
        String q = "SELECT FECHA FROM '"+docType+"' WHERE ID = '"+titleOfDoc+"'";
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

    // --------------------------------------- * OTHERS * --------------------------------------- //

}
