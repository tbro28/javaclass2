package edu.uw.cp520.scg.beans;

import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.util.PersonalName;

import javax.swing.event.EventListenerList;
import java.beans.*;
import java.util.Objects;

public class StaffConsultant extends Consultant {

    /**Pay rate property name.*/
    public static final String PAY_RATE_PROPERTY_NAME = "payRate";
    /**Pay rate property name.*/
    public static final String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";
    /**Vacation hours property name.*/
    public static final String VACATION_HOURS_PROPERTY_NAME = "vacationHours";

    private int payRate;
    private int sickLeaveHours;
    private int vacationHours;

    private EventListenerList listenerList = new EventListenerList();

    private PropertyChangeSupport vacaSickPcs =
            new PropertyChangeSupport(this);
    private VetoableChangeSupport payVcs =
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
     * Adds a payRate property change listener.
     *
     * @param I
     */
    public synchronized void addPayRateListener(PropertyChangeListener I) {


    }

    /**
     * Adds a general property change listener.
     *
     * @param I
     */
    public synchronized void addPropertyChangeListener(PropertyChangeListener I) {


    }

    /**
     * Adds a sickLeaveHours property change listener.
     *
     * @param I
     */
    public synchronized void addSickLeaveHoursListener(PropertyChangeListener I) {


        listenerList.add(PropertyChangeListener.class, l);


    }

    /**
     * Adds a vacationHours property change listener.
     *
     * @param I
     */
    public synchronized void addVacationHoursListener(PropertyChangeListener I) {

        //firePropertyChangeEvent
        listenerList.add(PropertyChangeListener.class, l);

    }

    /**
     * Adds a vetoable change listener, only applicable to payRate changes.
     *
     * @param I
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener I) {


    }


    /**
     * Removes a payRate property change listener.
     *
     * @param I
     */
    public synchronized void removePayRateListener(PropertyChangeListener I) {}

    /**
     * Remove a general property change listener.
     *
     * @param I
     */
    public synchronized void removePropertyChangeListener(PropertyChangeListener I) {}

    /**
     * Removes a sickLeaveHours property change listener.
     *
     * @param I
     */
    public synchronized void removeSickLeaveHoursListener(PropertyChangeListener I) {}

    /**
     * Removes a vacationHours property change listener.
     *
     * @param I
     */
    public synchronized void removeVacationHoursListener(PropertyChangeListener I) {}

    /**
     * Removes a vetoable change listener.
     *
     * @param I
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener I) {}




    public int getPayRate() {
        return payRate;
    }

    public void setPayRate(int payRate) throws PropertyVetoException {

        payVcs.fireVetoableChange(PAY_RATE_PROPERTY_NAME,
                this.payRate, payRate);
        this.payRate = payRate;
    }

    public int getSickLeaveHours() {
        return sickLeaveHours;
    }

    public void setSickLeaveHours(int sickLeaveHours) {

        vacaSickPcs.firePropertyChange(SICK_LEAVE_HOURS_PROPERTY_NAME,
                this.sickLeaveHours, sickLeaveHours);

        this.sickLeaveHours = sickLeaveHours;
    }

    public int getVacationHours() {
        return vacationHours;
    }

    public void setVacationHours(int vacationHours) {

        vacaSickPcs.firePropertyChange(VACATION_HOURS_PROPERTY_NAME,
                this.vacationHours, sickLeaveHours);

        this.vacationHours = vacationHours;
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
