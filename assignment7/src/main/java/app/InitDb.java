package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import persistent.DbServer;

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

            DbServer dbServer = new DbServer(URL, USERNAME, PASSWORD);

        try {
            dbServer.getClients();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        System.out.println("Query successful.");





    }









/*    catch (SQLException e) {

        e.printStackTrace();

    }*/



}
