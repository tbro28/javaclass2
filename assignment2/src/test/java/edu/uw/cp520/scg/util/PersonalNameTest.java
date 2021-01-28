package edu.uw.cp520.scg.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the PersonalName class.
 *
 * @author Tim Brown
 */
class PersonalNameTest {


    @Test
    void constructorEmpty() {
        PersonalName personalName = new PersonalName();

        assertNotNull(personalName);
    }

    @Test
    void constructorFirstAndLastName() {
        PersonalName personalName = new PersonalName("Brown", "Tom");

        assertNotNull(personalName);
    }

    @Test
    void getLastName() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        assertEquals("Brown", personalName.getLastName());
    }

    @Test
    void setLastName() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        personalName.setLastName("Bryant");

        assertEquals("Bryant", personalName.getLastName());
    }

    @Test
    void getFirstName() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        assertEquals("Tom", personalName.getFirstName());
    }

    @Test
    void setFirstName() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        personalName.setFirstName("Tim");

        assertEquals("Tim", personalName.getFirstName());
    }

    @Test
    void getMiddleName() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        assertEquals("Jack", personalName.getMiddleName());
    }

    @Test
    void setMiddleName() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        personalName.setMiddleName("Middle");

        assertEquals("Middle", personalName.getMiddleName());
    }

    @Test
    void testEquals() {
        PersonalName personalName1 = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("Brown", "Tom", "Jack");

        assertTrue(personalName1.equals(personalName2));
    }

    @Test
    void testHashCode() {
        PersonalName personalName1 = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("Brown", "Tom", "Jack");

        assertTrue(personalName1.hashCode() == personalName2.hashCode());
    }

    @Test
    void testToString() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        System.out.println(personalName.toString());

        assertEquals("Consultant: Brown, Tom, Jack", personalName.toString());
    }

    @Test
    void testToStringWithNOMiddleName() {
        PersonalName personalName = new PersonalName("Brown", "Tom");

        System.out.println(personalName.toString());

        assertEquals("Consultant: Brown, Tom", personalName.toString());
    }
}