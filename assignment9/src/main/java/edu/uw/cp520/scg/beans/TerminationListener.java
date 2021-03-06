package edu.uw.cp520.scg.beans;

import java.util.EventListener;

/**
 * Interface for accepting notification of consultant terminations.
 *
 */
public interface TerminationListener extends EventListener {

    /**
     * Invoked when a consultant is involuntarily terminated.
     *
     * @param evt
     */
    void forcedTermination(TerminationEvent evt);

    /**
     * Invoked when a consultant is voluntarily terminated.
     *
     * @param evt
     */
    void voluntaryTermination(TerminationEvent evt);

}
