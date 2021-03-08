package edu.uw.cp520.scg.beans;

import edu.uw.cp520.scg.domain.Consultant;

/**
 * Responsible for modifying the pay rate,
 * sick leave and vacation hours of staff consultants.
 * Provide methods for registration of BenefitListeners,
 * and TerminationListeners.
 *
 */
public class HumanResourceManager {

    public HumanResourceManager() {
    }


    /**
     *     Accepts the resignation of a consultant and fires a
     *     voluntary termination event for the consultant.
     *
     *
     * @param c
     */
    void acceptResignation(
            Consultant c)

    /**
     *     Adds a benefit listener.
     *
     * @param l
     */
    void addBenefitListener(BenefitListener l)

    /**
     *     Adds a termination listener.
     *
     * @param l
     */
    void addTerminationListener(TerminationListener l)

    /**
     *     Sets the pay rate for a staff consultant and logs
     *     whether the pay rate change was approved or rejected (vetoed).
     *
     * @param c
     * @param newPayRate
     */
    void adjustPayRate(StaffConsultant c, int newPayRate)

    /**
     *     Sets the sick leave hours for a staff consultant.
     *
     * @param c
     * @param newSickLeaveHours
     */
    void adjustSickLeaveHours(StaffConsultant c, int newSickLeaveHours)

    /**
     *     Sets the vacation hours for a staff consultant.
     *
     * @param c
     * @param newVacationHours
     */
    void adjustVacationHours(StaffConsultant c, int newVacationHours)

    /**
     *     Cancel a consultant's enrollment in dental,
     *     and fires a dental cancellation event.
     *
     * @param c
     */
    void cancelDental(Consultant c)

    /**
     *     Cancel a consultant's enrollment in medical,
     *     and fires a medical cancellation event.
     *
     * @param c
     */
    void cancelMedical(Consultant c)

    /**
     *     Enroll a consultant in dental, and fires a
     *     dental enrollment event.
     *
     * @param c
     */
    void enrollDental(Consultant c)

    /**
     *     Enroll a consultant in medical,
     *     and fires a medical enrollment event.
     *
     * @param c
     */
    void enrollMedical(Consultant c)

    /**
     *     Removes a benefit listener.
     *
     * @param l
     */
    void removeBenefitListener(BenefitListener l)

    /**
     *     Removes a termination listener.
     *
     * @param l
     */
    void removeTerminationListener(TerminationListener l)

    /**
     *     Fires an involuntary termination event
     *     for the staff consultant.
     *
     * @param c
     */
    void terminate(Consultant c)

}
