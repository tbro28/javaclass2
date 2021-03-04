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
public class Assignment5ExtraCode {

    private static Logger log = LoggerFactory.getLogger(Assignment5ExtraCode.class);
    private static final String ENCODING = "ISO-8859-1";

    public static void main(final String[] args) {

        List<TimeCard> tCardList  = new ArrayList<>();;
        List<ClientAccount> clientCardList  = new ArrayList<>();;

        /**
         * TimeCard deserialization.
         */
        Path path = Path.of("TimeCardList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));

            //Deserialize the list.
            tCardList = (List<TimeCard>) in.readObject();

            //Display its data
            for (TimeCard t : tCardList) {
                log.info("De-serialized TimeCard: " + t);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Client deserialization.
         */
        path = Path.of("ClientList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));

            //deserialize the List
            clientCardList = (List<ClientAccount>) in.readObject();
            //display its data
            for (ClientAccount t : clientCardList) {
                log.info("De-serialized ClientAccount: " + t.getName());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }













        // Create lists to be populated by factory
/*        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();*/
        //ListFactory.populateLists(accounts, consultants, timeCards);
        // Print them
        //ListFactory.printTimeCards(timeCards);

        // Use the list util methods
        Console console = System.console();
        try {
            @SuppressWarnings("resource")  // don't want to close console or System.out
            PrintWriter consoleWrtr = (console != null) ? console.writer()
                    : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);
/*
            Consultant carl = consultants.get(0);
            final List<TimeCard> selected = TimeCardListUtil.getTimeCardsForConsultant(timeCards, carl);
            final int count = selected.size();
            consoleWrtr.printf("Counted %d time cards for %s%n",count, carl);
            if (count != 2) {
                log.error(String.format("Bad time card count for %s", carl));
            }

            TimeCardListUtil.sortByStartDate(timeCards);
            consoleWrtr.println("Time cards by date:");
            for (TimeCard tc : timeCards) {
                consoleWrtr.printf("  %s, %s%n", tc.getWeekStartingDay(), tc.getConsultant());
            }

            TimeCardListUtil.sortByConsultantName(timeCards);
            consoleWrtr.println("Time cards by consultant:");
            for (TimeCard tc : timeCards) {
                consoleWrtr.printf("  %s, %s%n", tc.getWeekStartingDay(), tc.getConsultant());
            }

            accounts.clear();
            consultants.clear();
            timeCards.clear();

            ListFactory.populateLists(accounts, consultants, timeCards);
*/


            // Create the Invoices
            final List<Invoice> invoices = ListFactory.createInvoices(clientCardList, tCardList);
            // Print them
            consoleWrtr.println();
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println("=============================== I N V O I C E S ==================================");
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println();
            ListFactory.printInvoices(invoices, consoleWrtr);

            // Now print it to a file
            try (PrintWriter fileWriter = new PrintWriter("invoices.txt", ENCODING)) {
                ListFactory.printInvoices(invoices, fileWriter);
            } catch (final IOException ex) {
                log.error("Unable to print invoices to file.", ex);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Printing of invoices failed.", e);
        }


























    }
}
