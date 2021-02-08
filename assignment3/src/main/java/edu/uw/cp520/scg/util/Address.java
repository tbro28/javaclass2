package edu.uw.cp520.scg.util;

import java.util.Objects;

public class Address {

    private StateCode stateCode;
    private String streetNumber;
    private String city;
    private String postalCode;

    public Address(String streetNumber, String city, StateCode stateCode, String postalCode) {
        this.stateCode = stateCode;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return stateCode == address.stateCode && Objects.equals(streetNumber, address.streetNumber) && Objects.equals(city, address.city) && Objects.equals(postalCode, address.postalCode);
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
