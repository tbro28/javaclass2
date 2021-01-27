package edu.uw.cp520.scg.domain;

import edu.uw.ext.util.ListFactory;

import java.io.Console;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 02 application.
 */
public final class Assignment02 {
    /** Character encoding to use. */
    private static final String ENCODING = "ISO-8859-1";

    /**
     * Prevent instantiation.
     */
    private Assignment02() {
    }
 
    /**
     * The application method.
     *
     * @param args Command line arguments.
     * @throws Exception if raised
     */
    public static void main(final String[] args) throws Exception {
        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateListsPreAddress(accounts, consultants, timeCards);

        // Print 'em
        Console console = System.console();
        @SuppressWarnings("resource")  // don't want to close console or System.out
        PrintWriter consoleWrtr = (console != null) ? console.writer() 
                                                    : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);

        ListFactory.printTimeCards(timeCards, consoleWrtr);
    }

}
