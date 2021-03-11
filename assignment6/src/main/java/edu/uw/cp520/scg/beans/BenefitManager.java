package edu.uw.cp520.scg.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Tracks changes in employee benifits. Listens for any PropertyChangeEvent
 * and simply logs them. Additionally, Listens for any BenefitEvent and logs
 * those as well. No other actions are taken in response to any event.
 *
 */
public class BenefitManager implements BenefitListener, PropertyChangeListener {

    private static Logger log = LoggerFactory.getLogger(BenefitManager.class);

/*
    public BenefitManager() {
    }
*/


    /**
     *     Invoked when a consultant is cancels dental.
     *
     * @param evnt
     */
    public void dentalCancellation(BenefitEvent evnt) {
        log.info("Dental cancellation: " + evnt.getConsultant());
    }

    /**
     *     Invoked when a consultant is enrolls in dental.
     *
     * @param evnt
     */
    public void dentalEnrollment(BenefitEvent evnt) {
        log.info("Dental enrollment: " + evnt.getConsultant());
    }


    /**
     *     Invoked when a consultant is cancels medical.
     *
     * @param evnt
     */
    public void medicalCancellation(BenefitEvent evnt) {
        log.info("Medical cancellation: " + evnt.getConsultant());
    }

    /**
     *     Invoked when a consultant is enrolls in medical.
     *
     * @param evnt
     */
    public void medicalEnrollment(BenefitEvent evnt) {
        log.info("Medical enrollment: " + evnt.getConsultant());
    }

    /**
     *     Logs the change.
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
        log.info("Benefit Manager: " + evt.getPropertyName());
        log.info("Benefit Manager toString: " + evt.toString());
    }


}
