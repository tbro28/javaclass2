package edu.uw.cp520.scg.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the NonBillableAccount class.
 *
 * @author Tim Brown
 */
class NonBillableAccountTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("Sick Leave", NonBillableAccount.SICK_LEAVE.getName());
    }

    @Test
    void isBillable() {
        assertFalse(NonBillableAccount.VACATION.isBillable());
    }

    @Test
    void testToString() {
        assertEquals("NonBillableAccount{name='Business development'}", NonBillableAccount.BUSINESS_DEVELOPMENT.toString());
    }
/*
    @Test
    void values() {
        for(NonBillableAccount nonBillableAccount : NonBillableAccount.values())
            System.out.println(nonBillableAccount);
    }

    @Test
    void valueOf() {
        System.out.println(NonBillableAccount.valueOf("VACATION"));
    }

 */

}