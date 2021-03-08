package edu.uw.cp520.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

/**
 * Approves or rejects compensation changes. Listens for
 * PropertyChangeEvents on the payRate property, any pay
 * rate increase in excess of will be vetoed. The rejection
 * (veto) or acceptance of each pay rate change will be logged
 * as will any successful pay rate change.
 *
 */
public class CompensationManager implements PropertyChangeListener, VetoableChangeListener {


    /**
     * Processes to final pay rate change.
     *
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    /**
     * Rejects any raise over 5%.
     *
     * This method gets called when a constrained property is changed.
     *
     * @param evt a {@code PropertyChangeEvent} object describing the
     *            event source and the property that has changed.
     * @throws PropertyVetoException if the recipient wishes the property
     *                               change to be rolled back.
     */
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {

    }
}
