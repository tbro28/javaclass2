package edu.uw.cp520.scg.net.cmd;

import edu.uw.cp520.scg.domain.TimeCard;

/**
 * The command to add a ClientAccount to a list maintained by
 * the server, target type is ClientAccount.
 */
public class AddTimeCardCommand extends AbstractCommand<TimeCard> {


    public AddTimeCardCommand(TimeCard target) {
        super(target);
    }

    /**
     * The method called by the receiver.
     */
    @Override
    public void execute() {
        System.out.println("AddTimeCardCommand Object.................");
        getReceiver().execute(this);
    }
}
