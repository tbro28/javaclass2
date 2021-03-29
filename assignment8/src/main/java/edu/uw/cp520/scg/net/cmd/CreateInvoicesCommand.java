package edu.uw.cp520.scg.net.cmd;

import edu.uw.cp520.scg.domain.TimeCard;

import java.time.LocalDate;

/**
 * The command to add a ClientAccount to a list maintained by
 * the server, target type is ClientAccount.
 */
public class CreateInvoicesCommand extends AbstractCommand<LocalDate> {

    /**
     * Constructor.
     *
     * @param target
     */
    public CreateInvoicesCommand(LocalDate target) {
        super(target);
    }

    /**
     * The method called by the receiver.
     */
    @Override
    public void execute() {
        System.out.println("CreateInvoicesCommand Object.................");
        getReceiver().execute(this);
    }
}
