package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.PersonalName;

/**
 *
 * A billable Account that additionally has client contact information.
 *
 * @author Tim Brown
 */
public class ClientAccount implements Account {

    /**
     * Holds the account name.
     */
    String name;

    /**
     * Holds the person object with related information.
     */
    PersonalName personalName;

    /**
     * The only constructor that requires two parameters.
     *
     * @param name
     * @param personalName
     */
    public ClientAccount(String name, PersonalName personalName) {
        this.name = name;
        this.personalName = personalName;
    }

    /**
     * Returns the person object with name information.
     *
     * @return the personName object.
     */
    public PersonalName getContact() {
        return personalName;
    }

    /**
     * Interface method implementation of the getName method.
     *
     * @return the name string.
     */
    @Override
    public String getName() {
        return name;
    }


    //Because the enum is nonbillable, isn't this one always true???
    //Or is it actually set?

    /**
     * Always true since this object is billable.
     *
     * @return true.
     */
    @Override
    public boolean isBillable() {
        return true;
    }

    /**
     * Sets the personal contact object information.
     *
     * @param contact
     */
    public void setContact(PersonalName contact) {
        this.personalName = contact;
    }

}
