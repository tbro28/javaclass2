package edu.uw.cp520.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Tracks changes in employee benefits. Listens for any PropertyChangeEvent
 * and simply logs them. Additionally, Listens for any BenefitEvent and logs
 * those as well. No other actions are taken in response to any event.
 *
 */
public class BenefitManager implements BenefitListener, PropertyChangeListener {

    private static Logger log = LoggerFactory.getLogger(BenefitManager.class);

    /**
     * Invoked when a consultant is cancels dental.
     *
     * @param evnt
     */
    public void dentalCancellation(BenefitEvent evnt) {
        log.info(evnt.getConsultant().toString().substring(12) + " cancelled dental, " + evnt.getEffectiveDate());
    }

    /**
     * Invoked when a consultant is enrolls in dental.
     *
     * @param evnt
     */
    public void dentalEnrollment(BenefitEvent evnt) {
        log.info(evnt.getConsultant().toString().substring(12) + " enrolled in dental, " + evnt.getEffectiveDate());
    }

    /**
     * Invoked when a consultant is cancels medical.
     *
     * @param evnt
     */
    public void medicalCancellation(BenefitEvent evnt) {
        log.info(evnt.getConsultant().toString().substring(12) + " cancelled medical, " + evnt.getEffectiveDate());
    }

    /**
     * Invoked when a consultant is enrolls in medical.
     *
     * @param evnt
     */
    public void medicalEnrollment(BenefitEvent evnt) {
        log.info(evnt.getConsultant().toString().substring(12) + " enrolled in medical, " + evnt.getEffectiveDate());
    }

    /**
     * Logs the change.
     *
     * Specified by:
     * propertyChange in interface PropertyChangeListener
     *
     * Parameters:
     * evt - a property change event for the sickLeaveHours or vacationHours, or payRate property
     *
     * @param evt
     */
    public void propertyChange(PropertyChangeEvent evt) {
        log.info(evt.getPropertyName() + " changed from "
                + evt.getOldValue() + " to " + evt.getNewValue()
                + " for " + evt.getSource().toString().substring(12));
    }
}
