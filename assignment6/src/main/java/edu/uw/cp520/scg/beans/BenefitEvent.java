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


    //https://www.baeldung.com/java-optional
    private Optional<Boolean> dentalStatus;
    private Optional<Boolean> medicalStatus;
    private Consultant consultant;
    private LocalDate effectiveDate;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
/*
    public BenefitEvent(Object source) {
        super(source);
    }
*/


    /**
     * Constructs a prototypical Event.
     *
     * @param source
     * @param dentalStatus
     * @param medicalStatus
     * @param consultant
     * @param effectiveDate
     */
    public BenefitEvent(Object source, Optional<Boolean> dentalStatus,
                        Optional<Boolean> medicalStatus, Consultant consultant,
                        LocalDate effectiveDate) {
        super(source);
        this.dentalStatus = dentalStatus;
        this.medicalStatus = medicalStatus;
        this.consultant = consultant;
        this.effectiveDate = effectiveDate;
    }

    /**
     * Creates a dental enrollment event.
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    public static BenefitEvent	enrollDental(Object source, Consultant consultant, LocalDate effectiveDate) {
        return new BenefitEvent(source, Optional.of(true), Optional.empty(), consultant, effectiveDate);
    }

    /**
     * Creates a medical enrollment event.
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    public static BenefitEvent	enrollMedical(Object source, Consultant consultant, LocalDate effectiveDate) {
        return new BenefitEvent(source, Optional.empty(), Optional.of(true), consultant, effectiveDate);
    }



    /**
     * Creates a dental cancellation event.
     *
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    public static BenefitEvent	cancelDental(Object source, Consultant consultant, LocalDate effectiveDate) {
        return new BenefitEvent(source, Optional.of(false), Optional.empty(), consultant, effectiveDate);
    }

    /**
     * Creates a medical cancellation event.
     *
     * @param source
     * @param consultant
     * @param effectiveDate
     * @return
     */
    public static BenefitEvent	cancelMedical(Object source, Consultant consultant, LocalDate effectiveDate) {
        return new BenefitEvent(source, Optional.empty(), Optional.of(false), consultant, effectiveDate);
    }


    /**
     *     Gets the consultant that was terminated.
     *
     * @return
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     *     Gets the dental enrollment status.
     *
     * @return
     */
    public Optional<Boolean> getDentalStatus() {
        return dentalStatus;
    }

    /**
     *     Gets the effective date.
     *
     * @return
     */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *     Gets the medical enrollment status.
     *
     * @return
     */
    public Optional<Boolean> getMedicalStatus() {
        return medicalStatus;
    }
}
