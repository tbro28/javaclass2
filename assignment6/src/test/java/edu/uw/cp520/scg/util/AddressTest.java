package edu.uw.cp520.scg.util;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.StateCode;
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

    @Test
    void testCompareToEquals() {

        Address testAddress1 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");
        Address testAddress2 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");

        assertEquals(0, testAddress1.compareTo(testAddress2));
    }

    @Test
    void testCompareToDifferentStreet() {

        Address testAddress1 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");
        Address testAddress2 = new Address("TestStreetDiff", "TestCity", StateCode.WA, "TestPostalCode");

        assertNotEquals(0, testAddress1.compareTo(testAddress2));
    }

    @Test
    void testCompareToDifferentCity() {

        Address testAddress1 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");
        Address testAddress2 = new Address("TestStreet", "TestCityDiff", StateCode.WA, "TestPostalCode");

        assertNotEquals(0, testAddress1.compareTo(testAddress2));
    }

    @Test
    void testCompareToDifferentState() {

        Address testAddress1 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");
        Address testAddress2 = new Address("TestStreet", "TestCity", StateCode.CA, "TestPostalCode");

        assertNotEquals(0, testAddress1.compareTo(testAddress2));
    }

    @Test
    void testCompareToDifferentPostalCode() {

        Address testAddress1 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCode");
        Address testAddress2 = new Address("TestStreet", "TestCity", StateCode.WA, "TestPostalCodeDiff");

        assertNotEquals(0, testAddress1.compareTo(testAddress2));
    }

}