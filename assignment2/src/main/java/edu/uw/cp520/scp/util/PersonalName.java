package edu.uw.cp520.scp.util;

import java.util.Objects;

/**
 *
 * Encapsulates the first, middle and last name of a person.
 *
 * @author Tim Brown
 */
public class PersonalName {

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
        this.lastName = "";
        this.firstName = "";
        this.middleName = "";
    }

    /**
     * Constructor that takes in a last and first name.
     *
     * @param lastName
     * @param firstName
     */
    public PersonalName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
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


//Can there be null values?  Maybe middle name?

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

        PersonalName that = (PersonalName) obj;

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
        return "PersonalName{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
