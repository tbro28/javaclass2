package edu.uw.cp520.scg.net.server;

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
 *
 *
 *
 * 7.	Each CommandProcessor should write its invoices into a
 * unique directory under the target/ directory.
 *
 * 8.	The ShutdownCommand should cause the server to terminate,
 * without invoking System.exit(). This should in turn cause the execution of a shutdown hook
 * that simply writes the current client and consultant lists into a  sub-directory of the
 * target/ directory.
 *
 * 9.	IMPORTANT: Any access to shared resources (lists, files, etc.) must be protected
 * using synchronization.
 *
 *
 */
public class CommandProcessor implements Runnable {

    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(CommandProcessor.class);

    private Socket connection;
    private List<ClientAccount> clientList;
    private List<Consultant> consultantList;
    private List<TimeCard> timeCardList = new ArrayList<>();
    private InvoiceServer server;
    private File outPutDirectoryName;
    private String name;


    /**
     * Constructor.
     *
     * @param connection
     * @param clientList
     * @param consultantList
     * @param server
     */
    public CommandProcessor(Socket connection, String name, List<ClientAccount> clientList,
                            List<Consultant> consultantList, InvoiceServer server) {

        this.connection = connection;
        this.name = name;
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.server = server;
    }


    /**
     * Execute an AddClientCommand.
     * Since it's modifying client list in needs to be locked.
     *
     * @param command
     */
    public void execute(AddClientCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString()
            + " -- " + name);

        ClientAccount newAccount = command.getTarget();

        synchronized (clientList) {
            //there will be duplicates, so check and add if not already in there.
            if(!clientList.contains(newAccount))
                clientList.add(newAccount);
        }
    }


    /**
     * Execute and AddConsultantCommand.
     * Since it's modifying client list in needs to be locked.
     *
     * @param command
     */
    public void execute(AddConsultantCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString()
            + " -- " + name);

        Consultant newConsultant = command.getTarget();

        synchronized (consultantList) {
            //there will be duplicates, so check and add if not already in there.
            if(!clientList.contains(newConsultant))
                consultantList.add(newConsultant);
        }
    }


    /**
     * Execute and AddTimeCardCommand.
     *
     * @param command
     */
    public void execute(AddTimeCardCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString()
                + " -- " + name);

        timeCardList.add(command.getTarget());
    }


    /**
     * Execute a CreateInvoicesCommand.
     *
      * @param command
     */
    public void execute(CreateInvoicesCommand command) {
        log.info("CommandProcessor class - execute: " + command.toString()
            + " -- " + name);

        Invoice invoice = null;
        LocalDate date = command.getTarget(); //??????????????
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMMyyyy");
        String monthString = formatter.format(date);

        synchronized (clientList) {
            for (ClientAccount clientAccount : clientList) {
                invoice = new Invoice(clientAccount, date.getMonth(), date.getYear());

                for (TimeCard timeCard : timeCardList) {
                    invoice.extractLineItems(timeCard);
                }
                if (invoice.getTotalHours() > 0) {
                    String outputFileName = String.format("%s_%s_invoice.txt",
                            clientAccount.getName(), monthString);
                    File outFile = new File(outPutDirectoryName, outputFileName);
                    try (
                            PrintStream printOut = new PrintStream(new FileOutputStream(outFile), true);
                    ) {
                        printOut.println(invoice.toReportString());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
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
    public void setOutPutDirectory(File outPutDirectoryName) {
        this.outPutDirectoryName = outPutDirectoryName;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        log.info("Running process: " + name);

        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(connection.getInputStream());
            connection.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (!connection.isClosed()) {
                try {
                    Object object = inputStream.readObject();

                    if (object == null) {
                        break;
                    } else if (object instanceof Command<?>) {
                        Command<?> command = (Command<?>) object;
                        command.setReceiver(this);
                        command.execute();
                    } else {
                        System.out.println("Not a valid command.");
                    }
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                inputStream.close();
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
