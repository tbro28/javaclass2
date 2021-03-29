package edu.uw.cp520.scg.net.cmd;

/**
 * The command to add a ClientAccount to a list maintained by
 * the server, target type is ClientAccount.
 */
public class ShutdownCommand extends AbstractCommand<Void> {

    /**
     * Constructor.
     *
     */
    public ShutdownCommand() {
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
