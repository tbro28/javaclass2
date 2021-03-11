package edu.uw.cp520.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The EEOC monitors SCG's terminations. Listens for
 * TerminationEvents and maintains a count of each type
 * of termination event, and makes these counts available.
 * All TerminationEvents are logged.
 *
 */
public class Eeoc implements TerminationListener {

    private static Logger log = LoggerFactory.getLogger(Eeoc.class);

    private int voluntaryTermCount = 0;
    private int forcedTermCount = 0;

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

        voluntaryTermCount++;
        log.info("Consultant was fired: " + evt.getConsultant().toString());
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

        forcedTermCount++;
        log.info("Consultant quit voluntarily: " + evt.getConsultant().toString());
    }

    /**
     * Gets the forced termination count.
     *
     * @return forced count.
     */
    public int forcedTerminationCount() {
        return forcedTermCount;
    }

    /**
     * Gets the voluntary termination count.
     *
     * @return voluntary count.
     */
    public int voluntaryTerminationCount() {
        return voluntaryTermCount;
    }


}
