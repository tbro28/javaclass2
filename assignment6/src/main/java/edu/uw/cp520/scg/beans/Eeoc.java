package edu.uw.cp520.scg.beans;

/**
 * The EEOC monitors SCG's terminations. Listens for
 * TerminationEvents and maintains a count of each type
 * of termination event, and makes these counts available.
 * All TerminationEvents are logged.
 *
 */
public class Eeoc implements TerminationListener {


    /**
     * Simply prints a message indicating that the consultant
     * was fired and adjusts the forced termination count.
     *
     * Invoked when a consultant is involuntarily terminated.
     *
     * @param evt
     */
    @Override
    public void forcedTermination(TerminationEvent evt) {

    }

    /**
     * Gets the forced termination count.
     *
     * Invoked when a consultant is voluntarily terminated.
     *
     * @param evt
     */
    @Override
    public void voluntaryTermination(TerminationEvent evt) {

    }
}
