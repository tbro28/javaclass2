package edu.uw.cp520.scg.beans;

import edu.uw.cp520.scg.domain.Consultant;

import java.time.LocalDate;
import java.util.EventObject;
import java.util.Optional;

/**
 * Event used to notify listeners of a Consultant's
 * enrollment or cancellation of medical or dental benefits.
 *
 */
public class BenefitEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public BenefitEvent(Object source) {
        super(source);
    }

    /**
     *     Creates a dental cancellation event.
     *
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    static BenefitEvent	cancelDental(Object source, Consultant consultant, LocalDate effectiveDate) {

    }

    /**
     *     Creates a medical cancellation event.
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    static BenefitEvent	cancelMedical(Object source, Consultant consultant, LocalDate effectiveDate)

    /**
     *     Creates a dental enrollment event.
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    static BenefitEvent	enrollDental(Object source, Consultant consultant, LocalDate effectiveDate)

    /**
     *     Creates a medical enrollment event.
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    static BenefitEvent	enrollMedical(Object source, Consultant consultant, LocalDate effectiveDate)

    /**
     *     Gets the consultant that was terminated.
     *
     * @return
     */
    public Consultant getConsultant() {
        //return ...
    }

    /**
     *     Gets the dental enrollment status.
     *
     * @return
     */
    public Optional<Boolean> getDentalStatus()

    /**
     *     Gets the effective date.
     *
     * @return
     */
    public LocalDate getEffectiveDate()

    /**
     *     Gets the medical enrollment status.
     *
     * @return
     */
    public Optional<Boolean> getMedicalStatus()


}
