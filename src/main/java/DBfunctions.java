import java.sql.*;

public class DBfunctions {

    public static void CreateTableAndData(){
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







    private static Connection connect(){
        String connectionString = "jdbc:derby:meals_db;create=true";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
