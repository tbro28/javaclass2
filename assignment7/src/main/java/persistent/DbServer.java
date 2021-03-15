package persistent;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;

import java.sql.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

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


    /**
     * Constructor to setup the DB connection.
     *
     * @param dbUrl
     * @param username
     * @param password
     */
    public DbServer(String dbUrl, String username, String password) {
        this.url = dbUrl;
        this.userName = username;
        this.password = password;

        try {
            connection = DriverManager.getConnection(
                    this.url, this.userName, this.password);
            statement = connection.createStatement();

        } catch (SQLException throwables) {
            //connection.close();
            throwables.printStackTrace();
        }
    }


    /**
     * Add a client to the database, inserts one row in the clients table.
     *
     * @param client
     */
    public void addClient(ClientAccount client) {

        List<String> clientInfo = new ArrayList<>();
    /*    clientInfo.add(client.getName());
        clientInfo.add(client.getContact().getLastName());
        clientInfo.add(client.getContact().getFirstName());
        clientInfo.add(client.getContact().getMiddleName());
        clientInfo.add(client.toString());
        clientInfo.add(client.getAddress().getState().name());
*/
        for(String i : clientInfo) {
            System.out.println(i);
        }


        StringBuilder sb = new StringBuilder();
        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);
//        sb.append("'',").insert(1, client.getAddress().getStreetNumber());

        sb.append("INSERT INTO clients (name, street, city, state, postal_code, " +
                "contact_last_name, contact_first_name, contact_middle_name) VALUES (")
                .append("'', ").insert(sb.indexOf("'',")+1, client.getName())
                .append("'', ").insert(sb.indexOf("'',")+1, client.getAddress().getStreetNumber())
                .append("'', ").insert(sb.indexOf("'',")+1, client.getAddress().getCity())
                .append("'', ").insert(sb.indexOf("'',")+1, client.getAddress().getState().name())
                .append("'', ").insert(sb.indexOf("'',")+1, client.getAddress().getPostalCode())
                .append("'', ").insert(sb.indexOf("'',")+1, client.getContact().getLastName())
                .append("'', ").insert(sb.indexOf("'',")+1, client.getContact().getFirstName())
                .append("'')").insert(sb.indexOf("')"), client.getContact().getMiddleName());

        System.out.println(sb.toString());

        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
/*



                .format("'',")
                .insert(sdf)
                .format(client.getAddress().getStreetNumber())
                .format(client.getAddress().getCity())
                .format(client.getAddress().getState().name())
                .format(client.getAddress().getPostalCode())
                .format(client.getContact().getLastName())
                .format(client.getContact().getFirstName())
                .format(client.getContact().getMiddleName())
                .format(");");
*/



/*        INSERT INTO clients (name, street, city, state, postal_code,
                contact_last_name, contact_first_name, contact_middle_name)
        VALUES ('Acme Industries', '1616 Index Ct.', 'Redmond', 'WA', '98055',
                'Coyote', 'Wiley', 'E');*/


    }


    /**
     * Add a consultant to the database, inserts one row in the consultants table.
     *
     * @param consultant
     */
    public void addConsultant(Consultant consultant) {

/*        *//* Insert consultant *//*
        INSERT INTO consultants (last_name, first_name, middle_name)
        VALUES ('Architect', 'Ann', 'S.');*/

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO consultants (last_name, first_name, middle_name) ")
                .append("VALUES (")
                .append("'" + consultant.getName().getLastName() + "', ")
                .append("'" + consultant.getName().getFirstName() + "', ")
                .append("'" + consultant.getName().getMiddleName() + "')");

        System.out.println(sb);

        try {
            statement.executeUpdate(sb.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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


    /**
     * Helper method to validate if the StateCode in the DB was a valid
     * enum in StateCode.
     *
     * @param strState
     * @return
     */
    public static StateCode validState(String strState) {

        StateCode states[] = StateCode.values();
        for(StateCode s : states) {
            if(strState.equals(s.name()))
                return s;
                //stateCode = s;
            //System.out.println(s);
        }
        return null;

/*        for (MyEnum me : MyEnum.values()) {
            if (me.name().equalsIgnoreCase(str))
                return me;
        }
        */
    }



    /**
     * Get all of the clients in the database, selects all rows from the clients table.
     *
     * @return
     * @throws SQLException
     */
    public List<ClientAccount> getClients() throws SQLException {

        List<ClientAccount> clientAccountList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM clients");

        while(resultSet.next()) {

            String stateDB = resultSet.getObject("state").toString();
            StateCode stateCode;
            stateCode = validState(stateDB);

            clientAccountList.add(
                    new ClientAccount(
                            resultSet.getObject("NAME").toString(),
                            new PersonalName(resultSet.getObject("contact_last_name").toString(),
                                    resultSet.getObject("contact_first_name").toString(),
                                    resultSet.getObject("contact_middle_name").toString()),
                            new Address(resultSet.getObject("street").toString(),
                                    resultSet.getObject("city").toString(), stateCode,
                                    resultSet.getObject("postal_code").toString())
                            )
            );
            System.out.println("HERE!!!" + resultSet.getObject("NAME"));
            System.out.println("clientAccountList size: " + clientAccountList.size());
        }
        return clientAccountList;
    }



    /**
     * Get all of the consultant in the database, selects all rows from the consultants table.
     *
     * @return
     */
    public List<Consultant> getConsultants() throws SQLException {

        List<Consultant> consultantList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM consultants");

        while(resultSet.next()) {

            consultantList.add(
                    new Consultant(
                            new PersonalName(resultSet.getObject("LAST_NAME").toString(),
                                    resultSet.getObject("FIRST_NAME").toString(),
                                    resultSet.getObject("MIDDLE_NAME").toString()))
            );
            System.out.println("HERE!!!" + resultSet.getObject("LAST_NAME"));
            System.out.println("consultantList size: " + consultantList.size());
        }

        return consultantList;
    }



//Query

    /**
     * Get clients monthly invoice.
     *
     * @param client
     * @param month
     * @param year
     * @return
     */
    public Invoice getInvoice(ClientAccount client, int month, int year) {

        return new Invoice(client, Month.of(month), year);
    }

}
