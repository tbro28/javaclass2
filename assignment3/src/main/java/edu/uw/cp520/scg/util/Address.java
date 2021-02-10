package edu.uw.cp520.scg.util;

import java.util.Objects;

/**
 *
 * Represents an Address.
 *
 * @author Tim Brown
 */
public class Address {

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

    @Override
    public int hashCode() {
        return Objects.hash(stateCode, streetNumber, city, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "stateCode=" + stateCode +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
