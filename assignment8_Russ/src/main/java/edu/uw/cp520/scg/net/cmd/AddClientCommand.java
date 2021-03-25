package edu.uw.cp520.scg.net.cmd;

import edu.uw.cp520.scg.domain.ClientAccount;
import java.io.Serializable;

/**
 * The command to add a ClientAccount to a list maintained by
 * the server, target type is ClientAccount.
 */
public class AddClientCommand extends AbstractCommand<ClientAccount> {


    public AddClientCommand(ClientAccount target) {
        super(target);
    }

    /**
     * The method called by the receiver.
     */
    @Override
    public void execute() {
        System.out.println("AddClientCommend Object.................");
        getReceiver().execute(this);
    }
}
