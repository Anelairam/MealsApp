
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class DBfunctions {

    private static int idMeal, viewStats;
    private static String strMeal, strCategory, strArea, strInstructions;

    private static boolean fnd;
    static final int numcol = 4;
    static ArrayList<ArrayList<String>> result = new ArrayList<>(numcol);

    //Create db table
    static void CreateTableAndData(){
        try{
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String createSQL = "CREATE TABLE Meal" +
                    "(idMeal integer NOT NULL PRIMARY KEY," +
                    "strMeal varchar(255)," +
                    "strCategory varchar(255)," +
                    "strArea varchar(255)," +
                    "strInstructions varchar(1000)," +
                    "Stats integer)";
            statement.executeUpdate(createSQL);
            //String insertSQLv1 = "INSERT INTO D_USER VALUES(1,'bob','b12345')";
            //statement.executeUpdate(insertSQLv1);
            statement.close();
            connection.close();
            System.out.println("Done Create!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }

    //Insert new meal in the database
    static void insertNewMeal (int idMeal, String strMeal, String strArea, String strCategory, String strInstructions){
        try{
            Connection connection = connect();
            String insertSQL = "Insert into MEAL (IDMEAL, STRMEAL, STRCATEGORY, STRAREA, STRINSTRUCTIONS, VIEWSTATS) values(?,?,?,?,?,0)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, idMeal);
            preparedStatement.setString(2,strMeal);
            preparedStatement.setString(3,strArea);
            preparedStatement.setString(4,strCategory);
            preparedStatement.setString(5,strInstructions);

            int count = preparedStatement.executeUpdate();
            if (count>0) {
                //System.out.println(count+" record inserted");
                JOptionPane.showMessageDialog(null,"record inserted");
            }else{
                System.out.println("Something went wrong. Check the exception");
                JOptionPane.showMessageDialog(null,"Something went wrong. ");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Done Insert!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }

    //update meal
    static void UpdateMeal (int idMeal, String strMeal, String strArea, String strCategory, String strInstructions){
        try{
            Connection connection = connect();
            String updateSQL = "Update MEAL set STRMEAL = ?, STRCATEGORY = ?, STRAREA = ?, STRINSTRUCTIONS = ? where IDMEAL = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1,strMeal);
            preparedStatement.setString(2,strArea);
            preparedStatement.setString(3,strCategory);
            preparedStatement.setString(4,strInstructions);
            preparedStatement.setInt(5, idMeal);

            int count = preparedStatement.executeUpdate();
            if (count>0) {
                //System.out.println(count+" record updated");
                JOptionPane.showMessageDialog(null,"record updated");
            }else{
                System.out.println("Something went wrong. Check the exception");
                JOptionPane.showMessageDialog(null,"Something went wrong.");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Done Update!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }


    //update viewstats (increment by 1)
    static void UpdateViewStats (int idMeal){
        try{
            Connection connection = connect();
            String updateSQL = "Update MEAL set VIEWSTATS = VIEWSTATS + 1  where IDMEAL = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, idMeal);

            int count = preparedStatement.executeUpdate();
            if (count>0) {
                System.out.println(count+" record updated view counter+1");
                //JOptionPane.showMessageDialog(null,"record updated - counter +1");
            }else{
                System.out.println("Something went wrong. Check the exception");
                //JOptionPane.showMessageDialog(null,"record updated");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Done Update!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }

    //Delete meal from the database
    static void DeleteNewMeal (int idMeal){
        try{
            Connection connection = connect();
            String deleteSQL = "Delete FROM MEAL where IDMEAL = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idMeal);
            int count = preparedStatement.executeUpdate();

            if (count>0) {
                //System.out.println(count+"DB - record deleted");
                JOptionPane.showMessageDialog(null,"DB - record deleted");
            }else{
                //System.out.println("DB - record not found");
                JOptionPane.showMessageDialog(null,"DB - record not found");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Done Delete!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }

    //Edit recordset

    //Selection query all data
    static void selectAll(){
        try{
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String selectSQL = "Select * from MEAL order by VIEWSTATS DESC";
            ResultSet rs = statement.executeQuery(selectSQL);

            int counter = 0;
            result.clear();

            while (rs.next()) {
                result.add(new ArrayList<>());
                result.get(counter).add(rs.getString("IDMEAL"));
                result.get(counter).add(rs.getString("STRMEAL"));
                result.get(counter).add(rs.getString("STRCATEGORY"));
                result.get(counter).add(rs.getString("STRAREA"));
                result.get(counter).add(rs.getString("VIEWSTATS"));
                counter++;
                //      names.addAll((Collection<? extends String>) rs);
                System.out.println( rs.getString("IDMEAL") + "\t|" +
                        rs.getString("STRMEAL") + "\t\t|" +
                        rs.getString("STRCATEGORY") + "\t|" +
                        rs.getString("STRAREA") + "\t|" +
                        rs.getString("VIEWSTATS")

                );

            }
            //System.out.println("result list:" + counter + " " +result);
            statement.close();
            connection.close();
            System.out.println("Done Select All!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }

    }

    //Selection query specific meal
    //Αναζήτηση του γεύματος που επιλέχθηκε απο λίστα
    static void selectMeal(int idMeal, boolean found){
        DBfunctions.idMeal = idMeal;

        try{
            Connection connection = connect();
            String selectSQL = "Select * FROM MEAL where IDMEAL = ?";
            PreparedStatement stmt = connection.prepareStatement(selectSQL);
            stmt.setInt(1, idMeal);
          //  int count = preparedStatement.execute();
            ResultSet rs =  stmt.executeQuery();
            strMeal = "";
            strCategory = "";
            strArea = "";
            strInstructions = "";

            if (!rs.next()) {
                //System.out.println("No Data found in the Database");
                JOptionPane.showMessageDialog(null,"No Data found in the Database");
                fnd = false;
            } else {
                //System.out.println("Data found to edit");
                //JOptionPane.showMessageDialog(null,"Data found in the Database to edit");
                fnd = true;

                //variables used by getters
                strMeal = rs.getString("STRMEAL");
                strCategory = rs.getString("STRCATEGORY");
                strArea = rs.getString("STRAREA");
                strInstructions = rs.getString("STRINSTRUCTIONS");
            }

            stmt.close();
            connection.close();
            System.out.println("Done Select Meal!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }

    }

    static void selectAll2(){

        try{
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String selectSQL = "Select * from MEAL order by VIEWSTATS DESC";
            ResultSet rs = statement.executeQuery(selectSQL);

            int counter = 0;
            result.clear();

            while (rs.next()) {
                result.add(new ArrayList<>());
                result.get(counter).add(rs.getString("IDMEAL"));
                result.get(counter).add(rs.getString("STRMEAL"));
                result.get(counter).add(rs.getString("STRCATEGORY"));
                result.get(counter).add(rs.getString("STRAREA"));
                result.get(counter).add(rs.getString("VIEWSTATS"));
                counter++;
                //      names.addAll((Collection<? extends String>) rs);

            }
            System.out.println("result list:" + counter + " " +result);
            statement.close();
            connection.close();
            System.out.println("Done Select All!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }
    //ppep underconstruction
    static void checkdbifExist(){
        try{
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String selectSQL = "Select count(*) from MEAL";
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                System.out.println(rs.getInt("IDMEAL")+","+rs.getString("STRMEAL")+","+rs.getString("STRCATEGORY"));
            }
            statement.close();
            connection.close();
            System.out.println("Done!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }
    private static Connection connect(){
        String connectionString = "jdbc:derby:meals_db;create=true;";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static int getIdMeal(){
        return idMeal;
    }

    public static String getStrMeal () {
        return strMeal;
    }

    public static String getStrArea(){
        return strArea;
    }

    public static String getStrCategory() {
        return strCategory;
    }
    public static String getStrInstructions() {
        return strInstructions;
    }

    public static int getViewStats(){return viewStats; }

    public static boolean getfound(){ return fnd; }

    private ArrayList<ArrayList<String>> getResults(){
        return result;
    }
}

