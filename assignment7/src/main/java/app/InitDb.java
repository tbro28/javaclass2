package app;

import edu.uw.cp520.scg.domain.*;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import edu.uw.cp520.scg.persistent.DbServer;
import edu.uw.cp520.scg.util.TimeCardListUtil;
import edu.uw.ext.util.ListFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InitDb {

        /*
      Driver class: org.apache.derby.jdbc.ClientDriver (usage optional)
      URL:          jdbc:derby://localhost:1527/memory:scgDb
      Username:     student
      Password:     student
     */

    private static final String ENCODING = "ISO-8859-1";

    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InitDb.class);

    private static final String URL = "jdbc:derby://localhost:1527/memory:scgDb";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "student";


    public static void main(final String[] args) throws Exception {
        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        // Print them
        ListFactory.printTimeCards(timeCards);

        DbServer dbServer = new DbServer(URL, USERNAME, PASSWORD);

        // Use the list util methods
        Console console = System.console();
        try {

            for(Consultant consultant : consultants) {
                dbServer.addConsultant(consultant);
            }

            for(ClientAccount clientAccount : accounts) {
                dbServer.addClient(clientAccount);
            }

            for(TimeCard timeCard : timeCards) {
                dbServer.addTimeCard(timeCard);
            }

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
        } catch (UnsupportedEncodingException e) {
            log.error("Printing of invoices failed.", e);
        }




/*
        Address address = new Address( "streetNumberDiff", "city", StateCode.WA,"postalCode");
        String name = "TimBiz1";
        String name2 = "TimBiz2";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("Brown22222222", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);
        ClientAccount clientAccount2 = new ClientAccount(name2, personalName2, address);


        Consultant consultant = new Consultant(personalName);


        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 75);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);

*/

/*

        DbServer dbServer = new DbServer(URL, USERNAME, PASSWORD);

        try {

            dbServer.addConsultant(consultant);
            dbServer.getConsultants();

            dbServer.addClient(clientAccount);
            dbServer.addClient(clientAccount2);
            dbServer.getClients();


            */
/*dbServer.addConsultant(consultant);
            dbServer.addClient(clientAccount);
            dbServer.addClient(clientAccount2);
*//*


            dbServer.addTimeCard(timeCard);

            Invoice invoice = dbServer.getInvoice(clientAccount, 1, 2021);

            System.out.println(invoice.toReportString());


            //SQLException throwables
        } catch (Exception e) {
            //throwables.printStackTrace();
        }
*/


        System.out.println("Query successful.");


    }
}
