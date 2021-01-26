package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scp.util.PersonalName;
import edu.uw.ext.util.ListFactory;

import java.io.Console;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * A driver class to exercise the functionality.
 *
 * @author Tim Brown
 */
public class TimeCardDriver {

    private static final String ENCODING = "ISO-8859-1";

    public static void main(String[] args) throws Exception {


//        edu.uw.cp520.scp.domain.Consultant consultant = new edu.uw.cp520.scp.domain.Consultant(new PersonalName("Brown", "Tom", "Jack"));
        edu.uw.cp520.scp.domain.Consultant consultant = new edu.uw.cp520.scp.domain.Consultant(new PersonalName());
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        edu.uw.cp520.scp.domain.TimeCard timeCard = new edu.uw.cp520.scp.domain.TimeCard(consultant, localDate);

        String name = "TimBiz";
        //PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName = new PersonalName();
        edu.uw.cp520.scp.domain.ClientAccount clientAccount = new edu.uw.cp520.scp.domain.ClientAccount(name, personalName);

       System.out.println(timeCard.getConsultant().getName());



/*
                List<ClientAccount> accounts = new ArrayList();
        List<Consultant> consultants = new ArrayList();
        List<TimeCard> timeCards = new ArrayList();


        ListFactory.populateListsPreAddress(accounts, consultants, timeCards);
        Console console = System.console();
        PrintWriter consoleWrtr = console != null ? console.writer() : new PrintWriter(new OutputStreamWriter(System.out, "ISO-8859-1"), true);
        ListFactory.printTimeCards(timeCards, consoleWrtr);*/
    }

}
