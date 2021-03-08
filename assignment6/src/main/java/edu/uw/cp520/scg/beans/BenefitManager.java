package edu.uw.cp520.scg.beans;

import java.beans.PropertyChangeEvent;

public class BenefitManager {

    public BenefitManager() {
    }


    /**
     *     Invoked when a consultant is cancels dental.
     *
     * @param evnt
     */
    void dentalCancellation(BenefitEvent evnt)

    /**
     *     Invoked when a consultant is enrolls in dental.
     *
     * @param evnt
     */
    void dentalEnrollment(BenefitEvent evnt)

    /**
     *     Invoked when a consultant is cancels medical.
     *
     * @param evnt
     */
    void medicalCancellation(BenefitEvent evnt)

    /**
     *     Invoked when a consultant is enrolls in medical.
     *
     * @param evnt
     */
    void medicalEnrollment(BenefitEvent evnt)

    /**
     *     Logs the change.
     *
     * @param evt
     */
    void propertyChange(PropertyChangeEvent evt)


}
