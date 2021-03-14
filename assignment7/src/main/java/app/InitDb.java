package app;

import java.sql.*;

public class InitDb {

        /*
      Driver class: org.apache.derby.jdbc.ClientDriver (usage optional)
      URL:          jdbc:derby://localhost:1527/memory:scgDb
      Username:     student
      Password:     student
     */

    private static final String URL = "jdbc:derby://localhost:1527/memory:scgDb";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "student";
    private static final String QUERY_STRING = "SELECT * from skills";

/*    public InitDb() throws SQLException {
    }*/

    //try with resources to auto-close connections to the DB.

    static Connection connection;

    public static void main(String args[]){
        try {
            connection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_STRING);


            System.out.println("Query successful.");
            while(resultSet.next())
                System.out.println(resultSet.getObject("NAME"));



        } catch (SQLException throwables) {
            //connection.close();
            throwables.printStackTrace();
        }
    }









/*    catch (SQLException e) {

        e.printStackTrace();

    }*/



}
