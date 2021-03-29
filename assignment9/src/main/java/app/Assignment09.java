package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.net.client.InvoiceClient.InvoiceClient;
import edu.uw.ext.util.ListFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and prints an invoice from the data in the database.
 * Working Russ Version for Assignment 8. * Working Russ Version for Assignment 8.
 *
 */
public class Assignment09 {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment09.class);
    /**
     * Start of the driver to read data from the DB and print it to the screen.
     *
     * @param args
     */
    public static void main(String args[]) {

        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);

        InvoiceClient invoiceClient = new InvoiceClient("127.0.0.1", 10888, timeCards);
        invoiceClient.run();
        InvoiceClient.sendShutdown("127.0.0.1", 10888);
    }
}
