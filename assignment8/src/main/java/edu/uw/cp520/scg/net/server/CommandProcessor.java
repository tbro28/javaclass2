package edu.uw.cp520.scg.net.server;

import app.Assignment08;
import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.net.cmd.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The command processor for the invoice server.
 */
public class CommandProcessor {

    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(CommandProcessor.class);

    Socket connection;
    List<ClientAccount> clientList;
    List<Consultant> consultantList;
    List<TimeCard> timeCardList = new ArrayList<>();
    InvoiceServer server;
    String outPutDirectoryName;


    /**
     * Constructor.
     *
     * @param connection
     * @param clientList
     * @param consultantList
     * @param server
     */
    public CommandProcessor(Socket connection, List<ClientAccount> clientList,
                            List<Consultant> consultantList, InvoiceServer server) {

        this.connection = connection;
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.server = server;
    }


    /**
     * Execute an AddClientCommand.
     *
     * @param command
     */
    public void execute(AddClientCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString());
        clientList.add(command.getTarget());
    }


    /**
     * Execute and AddConsultantCommand.
     *
      * @param command
     */

    public void execute(AddConsultantCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString());
        consultantList.add(command.getTarget());
    }


    /**
     * Execute and AddTimeCardCommand.
     *
     * @param command
     */

    public void execute(AddTimeCardCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString());
        timeCardList.add(command.getTarget());
    }



    /**
     * Execute a CreateInvoicesCommand.
     *
      * @param command
     */

    public void execute(CreateInvoicesCommand command) {
        Invoice invoice = null;
        LocalDate date = command.getTarget(); //??????????????
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMMyyyy");
        String monthString = formatter.format(date);

        for(ClientAccount clientAccount : clientList) {
            invoice = new Invoice(clientAccount, date.getMonth(), date.getYear());

            for(TimeCard timeCard : timeCardList) {
                invoice.extractLineItems(timeCard);
            }
            if(invoice.getTotalHours() > 0) {

                File serverDirectory = new File(outPutDirectoryName);
                if(!serverDirectory.exists()) {
                    if (!serverDirectory.mkdirs()) {
                        log.error("Cannot create directory.");
                        return;
                    }
                }

                String outputFileName = String.format("%s_%s_invoice.txt",
                        clientAccount.getName(), monthString);
                File outFile = new File(outPutDirectoryName, outputFileName);

                try(
                        PrintStream printOut = new PrintStream(new FileOutputStream(outFile), true);
                        ) {

                    printOut.println(invoice.toReportString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Execute a DisconnectCommand.
     *
      * @param command
     */

    public void execute(DisconnectCommand command) {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Execute a ShutdownCommand.
     *
      * @param command
     */

    public void execute(ShutdownCommand command) {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.shutdown();
        }
    }


    /**
     * Set the output directory name.
     *
      * @param outPutDirectoryName
     */

    public void setOutPutDirectoryName(String outPutDirectoryName) {
        this.outPutDirectoryName = outPutDirectoryName;
    }
}
