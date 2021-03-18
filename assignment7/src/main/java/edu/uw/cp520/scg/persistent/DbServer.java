package edu.uw.cp520.scg.persistent;

import edu.uw.cp520.scg.domain.*;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


/**
 * Provides a programmatic interface to store and access objects in the database.
 *
 */
public class DbServer {
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
            throwables.printStackTrace();
        }
    }


    /**
     * Add a client to the database, inserts one row in the clients table.
     *
     * @param client
     */
    public void addClient(ClientAccount client) throws SQLException {
        /*
        INSERT INTO clients (name, street, city, state, postal_code,
                contact_last_name, contact_first_name, contact_middle_name)
        VALUES ('Acme Industries', '1616 Index Ct.', 'Redmond', 'WA', '98055',
                'Coyote', 'Wiley', 'E');*/
        List<String> clientInfo = new ArrayList<>();

        for(String i : clientInfo) {
            System.out.println(i);
        }

        try (
                Connection connection = DriverManager.getConnection(this.url, this.userName, this.password);
                Statement statement = connection.createStatement();
                ) {
            StringBuilder sb = new StringBuilder();
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
            statement.executeUpdate(sb.toString());
        }
    }


    /**
     * Add a consultant to the database, inserts one row in the consultants table.
     *
     * @param consultant
     */
    public void addConsultant(Consultant consultant) {

        /*
        INSERT INTO consultants (last_name, first_name, middle_name)
        VALUES ('Architect', 'Ann', 'S.');*/
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO consultants (last_name, first_name, middle_name) ")
                .append("VALUES (")
                .append("'" + consultant.getName().getLastName() + "', ")
                .append("'" + consultant.getName().getFirstName() + "', ")
                .append("'" + consultant.getName().getMiddleName() + "')");
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
     * Using prepared statements helps avoid SQL injection.
     *
     * @param timecard
     */
    public void addTimeCard(TimeCard timecard) {
        int consultantId = -1;
        int timeCardId = -1;

        /* Select consultant id
        SELECT id
        FROM consultants
        WHERE last_name = 'Architect'
        AND first_name = 'Ann'
        AND middle_name = 'S.';*/
        String consultantQuery = "SELECT id FROM consultants WHERE last_name = ? " +
                "AND first_name = ? " +
                "AND middle_name = ?";

        /* Insert time card
        INSERT INTO timecards (consultant_id, start_date)
        VALUES (1, '2017/03/01');*/
        String timecardQuery = "INSERT INTO timecards (consultant_id, start_date) VALUES (?, ?)";

        /* Insert non-billable hours
        INSERT INTO non_billable_hours (account_name, timecard_id, date, hours)
        VALUES ('VACATION', 1, '2017/03/13', 8);*/
        String nonbillableHours = "INSERT INTO non_billable_hours (account_name, timecard_id, date, hours) VALUES (?, ?, ?, ?)";

        /* Insert billable hours
        INSERT INTO billable_hours (client_id, timecard_id, date, skill, hours)
        VALUES ((SELECT DISTINCT id
                FROM clients
                WHERE name = 'Acme Industries'),
                3, '2005/03/12', 'Software Engineer', 8);*/
        String billableHours = "INSERT INTO billable_hours (client_id, timecard_id, date, skill, hours) VALUES (?, ?, ?, ?, ?)";
        String clientId = "SELECT DISTINCT id FROM clients WHERE name = ?";
        int clientIntId = -1;

        try(
                Connection connection = DriverManager.getConnection(this.url, this.userName, this.password);
                PreparedStatement psConsultant = connection.prepareStatement(consultantQuery);
                PreparedStatement psTimecard = connection.prepareStatement(timecardQuery, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement psNonebillable = connection.prepareStatement(nonbillableHours);
                PreparedStatement psBillable = connection.prepareStatement(billableHours);
                PreparedStatement psClientId = connection.prepareStatement(clientId);
                ) {

            //Getting the consultant ID.
            final String lastName = timecard.getConsultant().getName().getLastName();
            final String firstName = timecard.getConsultant().getName().getFirstName();
            final String middleName = timecard.getConsultant().getName().getMiddleName();
            psConsultant.setString(1, lastName);
            psConsultant.setString(2, firstName);
            psConsultant.setString(3, middleName);

            ResultSet rs = psConsultant.executeQuery();
            if(rs.next())
                consultantId = Integer.parseInt(rs.getObject("ID").toString());

            //Insert a timecard record providing the consultant id and start date.
            psTimecard.setInt(1, consultantId);
            psTimecard.setDate(2, Date.valueOf(timecard.getWeekStartingDay()));
            psTimecard.executeUpdate();

            //Obtain the timecard id of the just inserted time card.
            rs = psTimecard.getGeneratedKeys();
            if(rs.next())
                timeCardId = rs.getInt(1);

            /*Insert billable and non-billable hours record for the just created timecard,
            using the timecard id to identify the timecard the hours are associated with*/
            for(ConsultantTime consultantTime : timecard.getConsultingHours()) {
                if (!consultantTime.isBillable()) {
                    psNonebillable.setString(1, ((NonBillableAccount)consultantTime.getAccount()).name());
                    psNonebillable.setInt(2, timeCardId);
                    psNonebillable.setDate(3, Date.valueOf(consultantTime.getDate()));
                    psNonebillable.setInt(4, consultantTime.getHours());

                    psNonebillable.executeUpdate();
                }
                else {
                    //Get the client Id.
                    psClientId.setString(1, consultantTime.getAccount().getName());
                    ResultSet rsCid = psClientId.executeQuery();
                    if(rsCid.next())
                        clientIntId = rsCid.getInt(1);

                    psBillable.setInt(1, clientIntId);
                    psBillable.setInt(2, timeCardId);
                    psBillable.setDate(3, Date.valueOf(consultantTime.getDate()));
                    psBillable.setString(4, consultantTime.getSkill().name());
                    psBillable.setInt(5, consultantTime.getHours());

                    psBillable.executeUpdate();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
        }
        return null;
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
        }
        return clientAccountList;
    }


    /**
     * Get all of the consultant in the database,
     * selects all rows from the consultants table.
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
        }
        return consultantList;
    }


    /**
     * Get clients monthly invoice.
     *
     * @param client
     * @param month
     * @param year
     * @return
     */
    public Invoice getInvoice(ClientAccount client, int month, int year) throws SQLException {
        /*
        Select invoice items
        SELECT b.date, c.last_name, c.first_name, c.middle_name,
                b.skill, s.rate, b.hours
        FROM billable_hours b, consultants c, skills s, timecards t
        WHERE b.date between Date('2017-03-01') AND Date('2017-03-31')
        AND b.client_id = (SELECT DISTINCT id
        FROM clients
        WHERE name = 'Acme Industries')
        AND b.skill = s.name
        AND b.timecard_id = t.id
        AND c.id = t.consultant_id;
        */
        String invoiceQuery = "SELECT b.date, c.last_name, c.first_name, c.middle_name, b.skill, " +
                "s.rate, b.hours FROM billable_hours b, consultants c, skills s, timecards t " +
                "WHERE b.date between ? AND ? " +
                "AND b.client_id = ? AND b.skill = s.name " +
                "AND b.timecard_id = t.id AND c.id = t.consultant_id";
        String clientId = "SELECT DISTINCT id FROM clients WHERE name = ?";
        int clientIntId = -1;
        Invoice invoice = new Invoice(client, Month.of(month), year);

        try (
                Connection connection = DriverManager.getConnection(this.url, this.userName, this.password);
                PreparedStatement psClientId = connection.prepareStatement(clientId);
                PreparedStatement psInvoice = connection.prepareStatement(invoiceQuery);
                ) {
            //Get the client Id.
            psClientId.setString(1, client.getName());
            ResultSet rsCid = psClientId.executeQuery();
            if(rsCid.next())
                clientIntId = rsCid.getInt(1);

            //Get the dates
            Month monthObj = Month.of(month).plus(1);
            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = LocalDate.of(year, monthObj, 1).minusDays(1);

            psInvoice.setDate(1, Date.valueOf(startDate));
            psInvoice.setDate(2,Date.valueOf(endDate));
            psInvoice.setInt(3, clientIntId);

            //Create the invoice by adding the line items
            /*   public InvoiceLineItem(LocalDate date, Consultant consultant, Skill skill, int hours) {
                    this.date = date;
                    this.consultant = consultant;
                    this.skill = skill;
                    this.hours = hours;
            }*/
            try ( ResultSet rs = psInvoice.executeQuery()) {
                while(rs.next()) {
                    Date date = Date.valueOf(rs.getObject(1).toString());
                    LocalDate localDate = date.toLocalDate();
                    PersonalName personalName = new PersonalName(rs.getObject(2).toString(), rs.getObject(3).toString(), rs.getObject(4).toString());
                    Consultant consultant = new Consultant(personalName);

                    InvoiceLineItem invoiceLineItem = new InvoiceLineItem(localDate, consultant,
                            Skill.valueOf(rs.getObject(5).toString()),
                            Integer.parseInt(rs.getObject(7).toString()));
                    invoice.addLineItem(invoiceLineItem);
                }
            }
        }
        return invoice;
    }
}
