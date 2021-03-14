package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.ext.util.ListFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 * De-serializes the lists created by the app.InitLists program.
 *
 * @author Tim
 */
public class Assignment05 {

    private static Logger log = LoggerFactory.getLogger(Assignment05.class);
    private static final String ENCODING = "ISO-8859-1";

    /**
     * Main program for de-serialization.
     *
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(final String[] args) {

        List<TimeCard> tCardList  = new ArrayList<>();;
        List<ClientAccount> clientCardList  = new ArrayList<>();;

        /**
         * TimeCard deserialization.
         */
        Path path = Path.of("TimeCardList.ser");
        try {
            ObjectInputStream in =
                    new ObjectInputStream(Files.newInputStream(path));

            //Deserialize the list.
            tCardList = (List<TimeCard>) in.readObject();
        } catch (ClassNotFoundException e) {
            log.error("Could not find the class to " +
                    "de-serialize the object(s).");
            e.printStackTrace();
        }
        catch (IOException e) {
            log.error("Could not read in the list of " +
                    "object(s) to de-serialize.");
            e.printStackTrace();
        }

        /**
         * Client deserialization.
         */
        path = Path.of("ClientList.ser");
        try {
            ObjectInputStream in =
                    new ObjectInputStream(Files.newInputStream(path));

            //deserialize the List
            clientCardList = (List<ClientAccount>) in.readObject();
        } catch (ClassNotFoundException e) {
            log.error("Could not find the class to " +
                    "de-serialize the object(s).");
            e.printStackTrace();
        }
        catch (IOException e) {
            log.error("Could not read in the list of " +
                    "object(s) to de-serialize.");
            e.printStackTrace();
        }

        // Use the list util methods
        Console console = System.console();
        try {
            @SuppressWarnings("resource")  // don't want to close console or System.out
            PrintWriter consoleWrtr = (console != null) ? console.writer()
                    : new PrintWriter(
                            new OutputStreamWriter(System.out, ENCODING), true);

            // Create the Invoices
            final List<Invoice> invoices
                    = ListFactory.createInvoices(clientCardList, tCardList);
            // Print them
            consoleWrtr.println();
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println("=============================== I N V O I C E S ==================================");
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println();
            ListFactory.printInvoices(invoices, consoleWrtr);

            // Now print it to a file
            try (PrintWriter fileWriter =
                         new PrintWriter("invoices.txt", ENCODING)) {
                ListFactory.printInvoices(invoices, fileWriter);
            } catch (final IOException ex) {
                log.error("Unable to print invoices to file.", ex);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Printing of invoices failed.", e);
        }
    }
}
