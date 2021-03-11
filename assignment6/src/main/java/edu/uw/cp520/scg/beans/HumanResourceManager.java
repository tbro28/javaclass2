package edu.uw.cp520.scg.beans;

import edu.uw.cp520.scg.domain.Consultant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EventListener;
import java.util.List;
//import javax.swing.event.EventListenerList;
import java.beans.PropertyVetoException;
import java.time.LocalDate;

import edu.uw.cp520.scg.beans.HumanResourceManager.listenerList;

/**
 * Responsible for modifying the pay rate,
 * sick leave and vacation hours of staff consultants.
 * Provide methods for registration of BenefitListeners,
 * and TerminationListeners.
 *
 */
public class HumanResourceManager {

    private static Logger log = LoggerFactory.getLogger(HumanResourceManager.class);

    private EventListenerList listenerList = new EventListenerList();

    public HumanResourceManager() {
    }



    /**
     *     Adds a benefit listener.
     *
     * @param l
     */
    public void addBenefitListener(BenefitListener l) {
        listenerList.add(l);
    }

    /**
     *     Adds a termination listener.
     *
     * @param l
     */
    public void addTerminationListener(TerminationListener l)


    /**
     *     Removes a benefit listener.
     *
     * @param l
     */
    public void removeBenefitListener(BenefitListener l)

    /**
     *     Removes a termination listener.
     *
     * @param l
     */
    public void removeTerminationListener(TerminationListener l)



    /**
     *     Accepts the resignation of a consultant and fires a
     *     voluntary termination event for the consultant.
     *
     * @param c
     */
    public void acceptResignation(Consultant c) {

        fireEvent()
        TerminationEvent(this, c, true);


    }







    /**
     *     Sets the pay rate for a staff consultant and logs
     *     whether the pay rate change was approved or rejected (vetoed).
     *
     * @param c
     * @param newPayRate
     */
    public void adjustPayRate(StaffConsultant c, int newPayRate) {

        try {
            c.setPayRate(newPayRate);
            log.info("Pay rate adjusted for: " + c.getName().toString());
        } catch (PropertyVetoException e) {
            log.info("Pay rate DENIED for: " + c.getName().toString());
        }

    }

    /**
     *     Sets the sick leave hours for a staff consultant.
     *
     * @param c
     * @param newSickLeaveHours
     */
    public void adjustSickLeaveHours(StaffConsultant c, int newSickLeaveHours) {
        c.setSickLeaveHours(newSickLeaveHours);
    }

    /**
     *     Sets the vacation hours for a staff consultant.
     *
     * @param c
     * @param newVacationHours
     */
    public void adjustVacationHours(StaffConsultant c, int newVacationHours) {
        c.setVacationHours(newVacationHours);
    }

    /**
     *     Cancel a consultant's enrollment in dental,
     *     and fires a dental cancellation event.
     *
     * @param c
     */
    public void cancelDental(Consultant c) {
        //cancelDental(Object source, Consultant consultant, LocalDate effectiveDate)

        BenefitEvent benefitEvent;

        benefitEvent.
        new BenefitEvent(this, c, LocalDate.now());

    }

    /**
     *     Cancel a consultant's enrollment in medical,
     *     and fires a medical cancellation event.
     *
     * @param c
     */
    public void cancelMedical(Consultant c)

    /**
     *     Enroll a consultant in dental, and fires a
     *     dental enrollment event.
     *
     * @param c
     */
    public void enrollDental(Consultant c)

    /**
     *     Enroll a consultant in medical,
     *     and fires a medical enrollment event.
     *
     * @param c
     */
    public void enrollMedical(Consultant c)


    /**
     *     Fires an involuntary termination event
     *     for the staff consultant.
     *
     * @param c
     */
    public void terminate(Consultant c)

}
