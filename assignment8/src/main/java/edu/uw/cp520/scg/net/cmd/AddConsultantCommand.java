package edu.uw.cp520.scg.net.cmd;

import edu.uw.cp520.scg.domain.Consultant;

/**
 * The command to add a ClientAccount to a list maintained by
 * the server, target type is ClientAccount.
 */
public class AddConsultantCommand extends AbstractCommand<Consultant> {


    public AddConsultantCommand(Consultant target) {
        super(target);
    }

    /**
     * The method called by the receiver.
     */
    @Override
    public void execute() {
        System.out.println("AddConsultantCommand Object.................");
        getReceiver().execute(this);
    }
}
