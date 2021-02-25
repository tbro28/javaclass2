package edu.uw.cp520.scg.util;

import java.util.Objects;
import edu.uw.cp520.scg.util.Address;

/**
 *
 * Represents an Address.
 *
 * @author Tim Brown
 */
public final class Address implements Comparable<Address> {

    private StateCode stateCode;
    private String streetNumber;
    private String city;
    private String postalCode;

    /**
     * Constructor of the Address class.
     *
     * @param streetNumber
     * @param city
     * @param stateCode
     * @param postalCode
     */
    public Address(String streetNumber, String city,
                   StateCode stateCode, String postalCode) {
        this.stateCode = stateCode;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    /**
     * Get the state for this address.
     *
     * @return the state code.
     */
    public StateCode getState() {
        return stateCode;
    }

    /**
     * Get the street number number for this address.
     *
     * @return the street number.
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Gets the city for this address.
     *
     * @return the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the postal code for this address.
     *
     * @return the postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Implement the equals method.
     *
     * @param o
     * @return whether the two Address objects are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return stateCode == address.stateCode
                && Objects.equals(streetNumber, address.streetNumber)
                && Objects.equals(city, address.city)
                && Objects.equals(postalCode, address.postalCode);
    }

    /**
     * Implement the hashcode.
     *
     * @return a hash of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(stateCode, streetNumber, city, postalCode);
    }

    /**
     * Create a string version of the object.
     *
     * @return a string object.
     */
    @Override
    public String toString() {
        return "Address{" +
                "stateCode=" + stateCode +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
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
    public int compareTo(Address o) {
        int diff = 0;

        diff = this.stateCode.compareTo((o.getState()));
        if ( diff != 0) {
            return diff;
        }
        diff = this.streetNumber.compareTo(o.getStreetNumber());
        if ( diff != 0) {
            return diff;
        }
        diff = this.city.compareTo(o.getCity());
        if ( diff != 0) {
            return diff;
        }
        diff = this.postalCode.compareTo(o.getPostalCode());
        if ( diff != 0) {
            return diff;
        }

        return 0;
    }
}
