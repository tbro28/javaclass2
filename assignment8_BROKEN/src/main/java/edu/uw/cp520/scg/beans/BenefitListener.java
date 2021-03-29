package edu.uw.cp520.scg.beans;

import java.util.EventListener;

/**
 *
 * Interface for accepting notification of consultant changes
 * in medical and dental enrollment.
 *
 *
 */
public interface BenefitListener extends EventListener {

    /**
     * Invoked when a consultant is cancels dental.
     *
     * @param event
     */
    void dentalCancellation(BenefitEvent event);

    /**
     * Invoked when a consultant is enrolls in dental.
     *
     * @param event
     */
    void dentalEnrollment(BenefitEvent event);

    /**
     * Invoked when a consultant is cancels medical.
     *
     * @param event
     */
    void medicalCancellation(BenefitEvent event);

    /**
     * Invoked when a consultant is enrolls in medical.
     *
     * @param event
     */
    void medicalEnrollment(BenefitEvent event);

}
