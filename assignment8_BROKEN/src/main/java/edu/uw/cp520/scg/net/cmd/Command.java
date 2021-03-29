package edu.uw.cp520.scg.net.cmd;

import edu.uw.cp520.scg.net.server.CommandProcessor;

import java.io.Serializable;

/**
 * The interface of all Command objects, implements the command role
 * in the Command design pattern.
 *
 * @param <T>
 */
public interface Command<T> extends Serializable {

    /**
     * The method called by the receiver.
     */
    void execute();

    /**
     * Gets the CommandProcessor receiver for this Command.
     *
     * @return
     */
    CommandProcessor getReceiver();

    /**
     * Return the target of this Command.
     *
     * @return
     */
    T getTarget();


    /**
     * Set the CommandProcessor that will execute this Command.
     *
     * @param receiver
     */
    void setReceiver(CommandProcessor receiver);

}
