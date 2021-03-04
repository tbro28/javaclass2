package edu.uw.cp520.scg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Encapsulates the first, middle and last name of a person.
 *
 * @author Tim Brown
 */
public class PersonalName implements Comparable<edu.uw.cp520.scg.util.PersonalName>, Serializable {

    /**
     * A person's name details.
     */
    private String lastName;
    private String firstName;
    private String middleName;

    /**
     * Empty constructor.
     */
    public PersonalName() {
        this("", "", "");
    }

    /**
     * Constructor that takes in a last and first name.
     *
     * @param lastName
     * @param firstName
     */
    public PersonalName(String lastName, String firstName) {
        this(lastName, firstName, "");
    }

    /**
     * Constructor that takes a last, first, and middle name.
     *
     * @param lastName of the person
     * @param firstName of the person
     * @param middleName of the person
     */
    public PersonalName(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    /**
     * Get a person's last name.
     *
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set a person's last name.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * Get a person's first name.
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set a person's first name.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get a person's middle name.
     *
     * @return middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Set a person's middle name.
     *
     * @param middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Override the equals method.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        edu.uw.cp520.scg.util.PersonalName that = (edu.uw.cp520.scg.util.PersonalName) obj;

        return Objects.equals(lastName, that.lastName)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(middleName, that.middleName);
    }

    /**
     * Create a hash code.
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, middleName);
    }

    /**
     * Override the toString method.
     *
     * @return
     */
    @Override
    public String toString() {

        String pName = "";


        if(middleName.equals(""))
            pName = "Consultant: " +
                    lastName + ", " +
                    firstName;
        else
            pName = "Consultant: " +
                    lastName + ", " +
                    firstName + ", " +
                    middleName;

        return pName;
    }

    /**
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
    public int compareTo(edu.uw.cp520.scg.util.PersonalName o) {
        int diff = 0;

        diff = this.lastName.compareTo((o.getLastName()));
        if ( diff != 0) {
            return diff;
        }
        diff = this.firstName.compareTo(o.getFirstName());
        if ( diff != 0) {
            return diff;
        }
        diff = this.middleName.compareTo(o.getMiddleName());
        if ( diff != 0) {
            return diff;
        }

        return 0;
    }
}
