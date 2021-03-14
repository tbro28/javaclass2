package edu.uw.cp520.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.text.DecimalFormat;

/**
 * Approves or rejects compensation changes. Listens for
 * PropertyChangeEvents on the payRate property, any pay
 * rate increase in excess of will be vetoed.
 *
 * The rejection (veto) or acceptance of each pay rate
 * change will be logged as will any successful pay rate change.
 *
 */
public class CompensationManager implements PropertyChangeListener, VetoableChangeListener {

    private static Logger log = LoggerFactory.getLogger(CompensationManager.class);

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
        log.info("APPROVED pay rate change, from " + evt.getOldValue() + " to " + evt.getNewValue() + " for " + evt.getSource().toString().substring(12));
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
        //This will need to do the logic to veto if the raise is over 5%.
        DecimalFormat df = new DecimalFormat("#.######");

        int oldValue = (int) evt.getOldValue();
        int newValue = (int) evt.getNewValue();

        double fivePercentLimit = oldValue * .05;
        double raiseValue = newValue - oldValue;
        double percentRaise = raiseValue / oldValue;

        if (raiseValue > fivePercentLimit) {
            log.info("(" + newValue + " - " + oldValue + ")/" + oldValue + " = " + df.format(percentRaise));
            throw new PropertyVetoException("REJECTED pay rate change, from " + evt.getOldValue() + " to " + evt.getNewValue() + " for " + evt.getSource().toString().substring(12), evt);
        }
        log.info("(" + newValue + " - " + oldValue + ")/" + oldValue + " = " + df.format(percentRaise));
    }
}
