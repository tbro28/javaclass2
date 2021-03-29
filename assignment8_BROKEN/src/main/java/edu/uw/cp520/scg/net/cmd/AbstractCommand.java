package edu.uw.cp520.scg.net.cmd;

import edu.uw.cp520.scg.net.server.CommandProcessor;

/**
 * The superclass of all Command objects,
 * implements the command role in the Command design pattern.
 *
 * @param <T>
 */
public class AbstractCommand<T>  implements Command<T>{

    private T target;
    private CommandProcessor commandProcessor;


    public AbstractCommand() {
    }

    public AbstractCommand(T target) {
        this.target = target;
    }


    /**
     * The method called by the receiver.
     */
    @Override
    public void execute() {

    }

    /**
     * Gets the CommandProcessor receiver for this Command.
     *
     * @return
     */
    @Override
    public CommandProcessor getReceiver() {

        System.out.println("getReceiver");

        return commandProcessor;

    }

    /**
     * Return the target of this Command.
     *
     * @return
     */
    @Override
    public T getTarget() {
        return target;
    }

    /**
     * Set the CommandProcessor that will execute this Command.
     *
     * @param receiver
     */
    @Override
    public void setReceiver(CommandProcessor receiver) {

    }

    /**
     * A string representation of this command.
     */
    @Override
    public String toString() {
        return "AbstractCommand{" +
                "target=" + target +
                ", commandProcessor=" + commandProcessor +
                '}';
    }
}
