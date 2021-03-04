package app;

import edu.uw.cp520.scg.domain.*;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import edu.uw.cp520.scg.util.TimeCardListUtil;
import edu.uw.ext.util.ListFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class InitLists {

    private static Logger log = LoggerFactory.getLogger(InitLists.class);
    private static final String ENCODING = "ISO-8859-1";


    public static void main(final String[] args) {

        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        // Print them
        ListFactory.printTimeCards(timeCards);

        // Use the list util methods
        Console console = System.console();
        try {
            @SuppressWarnings("resource")  // don't want to close console or System.out
            PrintWriter consoleWrtr = (console != null) ? console.writer()
                    : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);

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

            // Create the Invoices
            final List<Invoice> invoices = ListFactory.createInvoices(accounts, timeCards);
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


        /**
         * Timecard serialization.
         * Note: The timecards reference the consultants, so the
         * consultants don't need to be serialized separately.
         */
        Path path = Path.of("TimeCardList.ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(timeCards);
            out.close();

        } catch (IOException e) {
            log.error("Could not serialize TimeCard list of object(s).");
            e.printStackTrace();
        }

        /**
         * Client serialization.
         */
        path = Path.of("ClientList.ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(accounts);
            out.close();

        } catch (IOException e) {
            log.error("Could not serialize Client list of object(s).");
            e.printStackTrace();
        }

        /**
         * Consultant serialization.
         * Log the name of the consultant.
         */
/*        path = Path.of("/ConsultantList.ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));

            int objectCount = consultantList.size();

            out.writeInt(objectCount);

            for( Consultant consultant : consultantList) {

                out.writeObject(consultant.getName().toString());
                out.writeObject(consultant);

            }
            out.writeObject(null);

            //out.writeObject(consultantList);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

}
