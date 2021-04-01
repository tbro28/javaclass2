package edu.uw.cp520.scg.net.server;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.domain.TimeCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * A shutdown hook that simply writes the current client and
 * consultant lists into a  sub-directory of the target/ directory.
 */
public class InvoiceServerShutdownHook extends Thread {

    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InvoiceServerShutdownHook.class);


    private List<ClientAccount> clientList;
    private List<Consultant> consultantList;
    private String outputDirectoryName;

    /**
     * Constructor.
     */
    public InvoiceServerShutdownHook(List<ClientAccount> clientList,
                                     List<Consultant> consultantList,
                                     String outputDirectoryName) {
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.outputDirectoryName = outputDirectoryName;
    }

    /**
     * Called by the Runtime when a shutdown signal is received.
     */
    @Override
    public void run() {

        File serverDirectory = new File(outputDirectoryName);
        if(!serverDirectory.exists()) {
            if (!serverDirectory.mkdirs()) {
                log.error("Cannot create directory.");
                return;
            }
        }
        String outputFileName = "client_list.txt";
        File outFileClients = new File(outputDirectoryName, outputFileName);
        try(
                PrintStream printOut = new PrintStream(new FileOutputStream(outFileClients), true);
        ) {
            synchronized (clientList) {
                for (ClientAccount clientAccount : clientList) {
                    printOut.println(clientAccount.getName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //File serverDirectory = new File(outputDirectoryName);
        if(!serverDirectory.exists()) {
            if (!serverDirectory.mkdirs()) {
                log.error("Cannot create directory.");
                return;
            }
        }
        outputFileName = "consultant_list.txt";
        File outFileConsultants = new File(outputDirectoryName, outputFileName);
        try(
                PrintStream printOut = new PrintStream(new FileOutputStream(outFileConsultants), true);
        ) {
            synchronized (consultantList) {
                for (Consultant consultantAccount : consultantList) {
                    printOut.println(consultantAccount.getName());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
