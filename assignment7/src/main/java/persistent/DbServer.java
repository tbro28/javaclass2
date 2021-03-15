package persistent;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.domain.TimeCard;

import java.sql.*;
import java.time.Month;
import java.util.List;

/**
 * Provides a programmatic interface to store and access objects in the database.
 *
 */
public class DbServer {

    List<ClientAccount> clientAccountList;
    List<Consultant> consultantList;


    private static String url;
    private static String userName;
    private static String password;
    private static String QUERY_STRING = "SELECT * from skills";
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public DbServer(String dbUrl, String username, String password) {
        this.url = dbUrl;
        this.userName = username;
        this.password = password;




        try {
            connection = DriverManager.getConnection(
                    this.url, this.userName, this.password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QUERY_STRING);


            System.out.println("Query successful.");
            while(resultSet.next())
                System.out.println(resultSet.getObject("NAME"));



        } catch (SQLException throwables) {
            //connection.close();
            throwables.printStackTrace();
        }


    }

    public void addClient(ClientAccount client) throws SQLException {



    }








    public void addConsultant(Consultant consultant) {

    }


    /**
     * Inserting a new TimeCard in to the database involves four steps:
     * 1.	Obtain the consultant id, for the consultant (using the name fields)
     * 2.	Insert a timecard record providing the consultant id and start date
     * 3.	Obtain the timecard id of the just inserted time card
     * 4.	Insert billable and non-billable hours record for the just created timecard,
     * using the timecard id to identify the timecard the hours are associated with
     *
     *
     * @param timecard
     */
    public void addTimeCard(TimeCard timecard) {

    }






    public List<ClientAccount> getClients() throws SQLException {

        while(resultSet.next())
            System.out.println(resultSet.getObject("NAME"));

        return clientAccountList;
    }



    public List<Consultant> getConsultants() {

        return consultantList;
    }


//Query
    public Invoice getInvoice(ClientAccount client, int month, int year) {

        return new Invoice(client, Month.of(month), year);
    }




}
