package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.domain.Account;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;

import java.io.Serializable;

/**
 *
 * A billable Account that additionally has client contact information.
 *
 * @author Tim Brown
 */
public class ClientAccount implements Account, Comparable<ClientAccount>, Serializable {

    /**
     * Holds the account name.
     */
    String name;

    /**
     * Holds the person object with related information.
     */
    PersonalName personalName;

    /**
     * Holds the client account address.
     */
    Address address;

    /**
     * The only constructor; requires three parameters.
     *
     * @param name
     * @param personalName
     * @param address
     */
    public ClientAccount(String name, PersonalName
            personalName, Address address) {
        this.name = name;
        this.personalName = personalName;
        this.address = address;
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


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * The natural ordering of ClientAccount is ascending order by
     * name,
     * contact
     * and finally address.
     *
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(ClientAccount o) {
        int diff = 0;

        diff = this.name.compareTo(o.getName());
        if ( diff != 0) {
            return diff;
        }
        diff = this.getContact().compareTo(o.getContact());
        if ( diff != 0) {
            return diff;
        }
        diff = this.address.compareTo(o.address);
        if ( diff != 0) {
            return diff;
        }

        return 0;
    }
}
