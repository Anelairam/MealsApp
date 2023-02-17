import javax.swing.*;
import java.sql.*;

public class DBfunctions {

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
            System.out.println("Done!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }

    //Insert new meal in the database
    static void insertNewMeal (int idMeal, String strMeal, String strArea, String strCategory, String strInstructions){
        try{
            Connection connection = connect();
            String insertSQL = "Insert into MEAL (IDMEAL, STRMEAL, STRCATEGORY, STRAREA, STRINSTRUCTIONS) values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, idMeal);
            preparedStatement.setString(2,strMeal);
            preparedStatement.setString(3,strArea);
            preparedStatement.setString(4,strCategory);
            preparedStatement.setString(5,strInstructions);

            int count = preparedStatement.executeUpdate();
            if (count>0) {
                System.out.println(count+" record inserted");
                JOptionPane.showMessageDialog(null,"record inserted");
            }else{
                System.out.println("Something went wrong. Check the exception");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Done!");
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
                System.out.println(count+" record deleted");
                JOptionPane.showMessageDialog(null,"record deleted");
            }else{
                System.out.println("Something went wrong. Check the exception");
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Done!");
        } catch (SQLException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
    }

    //Selection query
    static void selectAll(){
        try{
            Connection connection = connect();
            Statement statement = connection.createStatement();
            String selectSQL = "Select * from MEAL";
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                System.out.println(rs.getInt("IDMEAL")+","+rs.getString("STRMEAL")+","+rs.getString("STRCATEGORY")+","+rs.getString("STRAREA"));
            }
            statement.close();
            connection.close();
            System.out.println("Done!");
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
}
