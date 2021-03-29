package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.domain.NonBillableAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Unit tests for the NonBillableAccount class.
 *
 * @author Tim Brown
 */
class NonBillableAccountTest {

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
        assertEquals("name=Business development", NonBillableAccount.BUSINESS_DEVELOPMENT.toString());
    }
}