package edu.uw.cp520.scg.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Address class.
 *
 * @author Tim Brown
 */
class AddressTest {

    Address address;

    @BeforeEach
    void setUp() {

        address = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");

    }

    @Test
    void testEquals() {
        Address testAddress = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");

        assertTrue(address.equals(testAddress));
    }

    @Test
    void testHashCode() {

        Address testAddress = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");

        assertEquals(testAddress.hashCode(), address.hashCode());
    }

    @Test
    void testToString() {
        String addressString = "Address{stateCode=WA, streetNumber='TestStreet', city='TestCity', postalCode='TestPostalCode'}";

        assertEquals(addressString, address.toString());
    }
}