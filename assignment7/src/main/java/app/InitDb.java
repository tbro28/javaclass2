package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
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



        Address address = new Address( "streetNumberDiff", "city", StateCode.WA,"postalCode");
        String name = "TimBiz1";
        String name2 = "TimBiz2";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("Brown22222222", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);
        ClientAccount clientAccount2 = new ClientAccount(name2, personalName2, address);

        Consultant consultant = new Consultant(personalName);

        DbServer dbServer = new DbServer(URL, USERNAME, PASSWORD);

        try {

            dbServer.addConsultant(consultant);
            dbServer.getConsultants();

/*            dbServer.addClient(clientAccount);
            dbServer.addClient(clientAccount2);*/
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
