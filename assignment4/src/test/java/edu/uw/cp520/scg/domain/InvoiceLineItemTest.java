package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the InvoiceLineItem class.
 *
 * @author Tim Brown
 */
class InvoiceLineItemTest {

    Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
    LocalDate localDate = LocalDate.of(2020, 1, 15);
    LocalDate localDate2 = LocalDate.of(2020, 2, 15);
    TimeCard timeCard = new TimeCard(consultant, localDate);

    PersonalName personalName;
    Address address;
    ClientAccount clientAccount;

    Invoice invoice;
    Month month;
    int invoiceYear;
    LocalDate startDate;

    /**
     * Test data setup for testing the Invoice class.
     */
    @BeforeEach
    void setUp() {
        String name = "TimBiz";
        personalName = new PersonalName("Brown", "Tom", "Jack");
        address = new Address("TimsStreetNumber", "TimsCity", StateCode.WA, "TimsPostalCode");
        clientAccount = new ClientAccount(name, personalName, address);

        month = Month.of(1);
        invoiceYear = 2020;
        invoice = new Invoice(clientAccount, month, invoiceYear);
        startDate = LocalDate.of(2020, 1, 1);
    }

    @Test
    void testToString() {
        String lineItem = "InvoiceLineItem{date=2020-01-15, consultant=Consultant{name=Consultant: Brown, Tom, Jack}, skill=Project Manager, hours=25}";

        InvoiceLineItem invoiceLineItem = new InvoiceLineItem(localDate, timeCard.consultant, Skill.PROJECT_MANAGER, 25);

        System.out.println(invoiceLineItem.toString());

        assertEquals(lineItem, invoiceLineItem.toString());
    }
}