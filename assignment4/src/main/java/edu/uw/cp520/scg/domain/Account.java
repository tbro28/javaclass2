package edu.uw.cp520.scg.domain;

/**
 *
 * Defines an account as having a name
 * and being either billable or no-billable,
 * all accounts must implement.
 *
 * @author Tim Brown
 */
public interface Account {

    /**
     *
     * Method to return a name.
     *
     * @return the appropriate name.
     */
    String getName();

    /**
     * Method to determine is billable.
     *
     * @return a boolean with whether the implementing instance is billable.
     */
    boolean isBillable();

}
