package edu.uw.cp520.scg.net.server;

import app.Assignment08;
import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.net.cmd.AddClientCommand;
import edu.uw.cp520.scg.net.cmd.AddConsultantCommand;
import edu.uw.cp520.scg.net.cmd.AddTimeCardCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
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
     *//*

    public void execute(CreateInvoicesCommand command) {

    }


    */
    /**
     * Execute a DisconnectCommand.
     *
      * @param command
     *//*

    public void execute(DisconnectCommand command) {

    }


    */
    /**
     * Execute a ShutdownCommand.
     *
      * @param command
     *//*

    public void execute(ShutdownCommand command) {

    }


    */


    /**
     * Set the output directory name.
     *
      * @param outPutDirectoryName
     */

    public void setOutPutDirectoryName(String outPutDirectoryName) {
        this.outPutDirectoryName = outPutDirectoryName;
    }




}
