package edu.uw.cp520.scg.beans;

import edu.uw.cp520.scg.domain.Consultant;

import java.io.Serializable;
import java.util.EventObject;

/**
 * Event used to notify listeners of a Consultant's termination.
 *
 */
public class TerminationEvent extends EventObject implements Serializable {

    private Consultant consultant;
    private boolean voluntary;


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public TerminationEvent(Object source, Consultant consultant, boolean voluntary) {
        super(source);
        this.consultant = consultant;
        this.voluntary = voluntary;
    }

    /**
     *     Gets the consultant that was terminated.
     *
     * @return
     */
    public Consultant getConsultant() {
        return this.consultant;
    }

    /**
     *     Gets the voluntary termination status.
     *
     * @return
     */
    public boolean isVoluntary() {
        return this.voluntary;
    }

}
