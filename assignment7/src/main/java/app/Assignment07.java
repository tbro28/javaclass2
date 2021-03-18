package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.persistent.DbServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;

/**
 * Creates and prints an invoice from the data in the database.
 *
 */
public class Assignment07 {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InitDb.class);

    private static final String URL = "jdbc:derby://localhost:1527/memory:scgDb";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "student";

    public static void main(String args[]) {
        DbServer dbServer = new DbServer(URL, USERNAME, PASSWORD);

        try {
            List<ClientAccount> clientAccountList = dbServer.getClients();
            for(ClientAccount clientAccount : clientAccountList) {
                Invoice invoice = dbServer.getInvoice(clientAccount, 3, 2017);
                System.out.println(invoice.toReportString());
            }
        } catch( SQLException throwables ) {
            throwables.printStackTrace();
        }

        System.out.println("Query consultants:");
        try {
            List<Consultant> consultants = dbServer.getConsultants();
            for(Consultant consultant : consultants)
                log.info(consultant.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
