package edu.uw.cp520.scg.beans;

import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.util.PersonalName;

import java.beans.*;
import java.util.Objects;


/**
 * A consultant who is kept on staff (receives benefits).
 *
 */
public class StaffConsultant extends Consultant {

    /**Pay rate property name.*/
    public static final String PAY_RATE_PROPERTY_NAME = "payRate";
    /**Sick leave hours property name.*/
    public static final String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";
    /**Vacation hours property name.*/
    public static final String VACATION_HOURS_PROPERTY_NAME = "vacationHours";

    private int payRate;
    private int sickLeaveHours;
    private int vacationHours;

    //private EventListenerList listenerList = new EventListenerList();
    private PropertyChangeSupport pcs =
            new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs =
            new VetoableChangeSupport(this);

    /**
     * Constructor.
     *
     * @param name to initially set when an instance is created.
     */
/*    public StaffConsultant(PersonalName name) {
        super(name);
    }*/

    /**
     * Constructor with initialization parameters.
     *
     * @param name
     * @param payRate
     * @param sickLeaveHours
     * @param vacationHours
     */
    public StaffConsultant(PersonalName name, int payRate, int sickLeaveHours, int vacationHours) {
        super(name);
        this.payRate = payRate;
        this.sickLeaveHours = sickLeaveHours;
        this.vacationHours = vacationHours;
    }


    /**
     * Register, de-register, and fire events?
     *
     */


    /**
     * Adds a payRate property change listener.
     * This will only register the change listener for the payRate property of the bean.
     *
     * @param l
     */
    public synchronized void addPayRateListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
    }

    /**
     * Adds a general property change listener.
     * If you use this listener you will be notified by anything using pcs.
     *
     * @param l
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * Adds a sickLeaveHours property change listener.
     *
     * @param l
     */
    public synchronized void addSickLeaveHoursListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Adds a vacationHours property change listener.
     *
     * @param l
     */
    public synchronized void addVacationHoursListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Adds a vetoable change listener, only applicable to payRate changes.
     *
     * @param l
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vcs.addVetoableChangeListener(l);
    }


    /**
     * Removes a payRate property change listener.
     * This will only de-register the change listener for the payRate property of the bean.
     *
     * @param l
     */
    public synchronized void removePayRateListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
    }

    /**
     * Remove a general property change listener.
     *
     * @param l
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * Removes a sickLeaveHours property change listener.
     *
     * @param l
     */
    public synchronized void removeSickLeaveHoursListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Removes a vacationHours property change listener.
     *
     * @param l
     */
    public synchronized void removeVacationHoursListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Removes a vetoable change listener.
     *
     * @param l
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vcs.removeVetoableChangeListener(l);
    }


    /**
     * Return the current pay rate.
     *
     * @return
     */
    public int getPayRate() {
        return payRate;
    }

    /**
     * Sets a new pay rate.
     * If vetoed, then an exception is thrown.
     *
     * @param payRate
     * @throws PropertyVetoException
     */
    public void setPayRate(int payRate) throws PropertyVetoException {

        /* Storing the rate because it could be vetoed.
        And in a multi-threaded environment this could
        cause incorrect values to be used in the event
        that a veto does happen.
        You don't want to actually change the objects payRate
        until after firing the the veto event.
        */
        int oldPayRate =this.payRate;

        /* Used by veto listeners, in this case they will check
        to see if the rate is over 5%.
         */
        vcs.fireVetoableChange(PAY_RATE_PROPERTY_NAME,
                this.payRate, payRate);

        this.payRate = payRate;

        // Notifies any listeners to the change.
        pcs.firePropertyChange(PAY_RATE_PROPERTY_NAME,
                oldPayRate, payRate);
    }


    /**
     * Returns the sick leave hours and fire the related event.
     *
     * @return
     */
    public int getSickLeaveHours() {
        return sickLeaveHours;
    }

    /**
     * Set the sick leave hours.
     *
     *
     * @param sickLeaveHours
     */
    public void setSickLeaveHours(int sickLeaveHours) {
        /* Store the old value to avoid multi-threading issues?
        Even if it can't be vetoed?
        After re-watching explanation:
        For veto, you are notifying that you INTEND on doing something.
        For property change, you are notifying that something HAS happened.
        Therefore, this isn't right:
            pcs.firePropertyChange(SICK_LEAVE_HOURS_PROPERTY_NAME,
                    this.sickLeaveHours, sickLeaveHours);
            this.sickLeaveHours = sickLeaveHours;
         */
        int oldSickLeaveHours = this.sickLeaveHours;
        this.sickLeaveHours = sickLeaveHours;
        pcs.firePropertyChange(SICK_LEAVE_HOURS_PROPERTY_NAME,
                oldSickLeaveHours, sickLeaveHours);
    }


    /**
     * Return the vacation hours.
     *
     * @return
     */
    public int getVacationHours() {
        return vacationHours;
    }

    /**
     * Set the vacation hours and fire the related event.
     *
     * @param vacationHours
     */
    public void setVacationHours(int vacationHours) {
        int oldVacationHours = this.vacationHours;
        this.vacationHours = vacationHours;
        pcs.firePropertyChange(VACATION_HOURS_PROPERTY_NAME,
                oldVacationHours, vacationHours);
    }





    /**
     * Compare names for equivalence.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StaffConsultant that = (StaffConsultant) o;
        return payRate == that.payRate && sickLeaveHours == that.sickLeaveHours && vacationHours == that.vacationHours;
    }

    /**
     * Calculate the hash code.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), payRate, sickLeaveHours, vacationHours);
    }
}
