package edu.uw.cp520.scg.net.cmd;

import java.time.LocalDate;

/**
 * The command to add a ClientAccount to a list maintained by
 * the server, target type is ClientAccount.
 */
public class DisconnectCommand extends AbstractCommand<Void> {

    /**
     * Constructor.
     *
     */
    public DisconnectCommand() {
        super();
    }

    /**
     * The method called by the receiver.
     */
    @Override
    public void execute() {
        System.out.println("DisconnectCommand Object.................");
        getReceiver().execute(this);
    }
}
