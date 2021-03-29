package edu.uw.cp520.scg.net.server;

import app.Assignment08;
import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.net.cmd.AddClientCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
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
    InvoiceServer server;




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
        //System.out.println("CommandProcessor class - execute: " + command.toString());
        clientList.add(command.getTarget());
    }

/*

    */
/**
     * Execute and AddConsultantCommand.
     *
      * @param command
     *//*

    void execute(AddConsultantCommand command) {

    }


    */
/**
     * Execute and AddTimeCardCommand.
     *
     * @param command
     *//*

    void execute(AddTimeCardCommand command) {

    }


    */
/**
     * Execute a CreateInvoicesCommand.
     *
      * @param command
     *//*

    void execute(CreateInvoicesCommand command) {

    }


    */
/**
     * Execute a DisconnectCommand.
     *
      * @param command
     *//*

    void execute(DisconnectCommand command) {

    }


    */
/**
     * Execute a ShutdownCommand.
     *
      * @param command
     *//*

    void execute(ShutdownCommand command) {

    }


    */
/**
     * Set the output directory name.
     *
      * @param outPutDirectoryName
     *//*

    void setOutPutDirectoryName(String outPutDirectoryName) {

    }

*/


}
