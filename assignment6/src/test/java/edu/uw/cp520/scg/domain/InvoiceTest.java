package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Invoice class.
 *
 * @author Tim Brown
 */
class InvoiceTest {

    Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
    LocalDate localDate = LocalDate.of(2020, 1, 15);
    LocalDate localDate2 = LocalDate.of(2020, 2, 15);
    TimeCard timeCard = new TimeCard(consultant, localDate);

    PersonalName personalName;
    Address address;
    ClientAccount clientAccount;

    ConsultantTime consultantTime1;
    ConsultantTime consultantTime2;
    ConsultantTime consultantTime3;
    ConsultantTime consultantTime4;

    List<ConsultantTime> consultantTimeList = new ArrayList<>();

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

        consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 25);
        consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        consultantTime4 = new ConsultantTime(localDate2, clientAccount, Skill.PROJECT_MANAGER, 75);
        consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 100);

        ConsultantTime consultantTime5 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 5);
        ConsultantTime consultantTime6 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 10);
        ConsultantTime consultantTime7 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 15);
        ConsultantTime consultantTime8 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 20);

        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);
        consultantTimeList.add(consultantTime4);

        consultantTimeList.add(consultantTime5);
        consultantTimeList.add(consultantTime6);
        consultantTimeList.add(consultantTime7);
        consultantTimeList.add(consultantTime8);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);

        month = Month.of(1);
        invoiceYear = 2020;
        invoice = new Invoice(clientAccount, month, invoiceYear);
        startDate = LocalDate.of(2020, 1, 1);
    }

    @Test
    void addLineItem() {
        String testItem = "2020-01-15:Project Manager:25:Consultant: Brown, Tom, Jack";
        LocalDate localDate = LocalDate.of(2020,1,1);

        invoice.addLineItem(new InvoiceLineItem(consultantTime1.getDate(), timeCard.consultant, consultantTimeList.get(0).getSkill(), consultantTimeList.get(0).getHours()));

        assertTrue(invoice.toString().contains(testItem));
    }

    @Test
    void extractLineItems() {
        String lineItem1 = "2020-01-15:Project Manager:25:Consultant: Brown, Tom, Jack";
        String lineItem2 = "2020-01-15:Project Manager:50:Consultant: Brown, Tom, Jack";

        invoice.extractLineItems(timeCard);

        assertTrue(invoice.toString().contains(lineItem1));
        assertTrue(invoice.toString().contains(lineItem2));
    }

    @Test
    void getClientAccount() {
        assertTrue(invoice.getClientAccount().equals(clientAccount));
    }

    @Test
    void getInvoiceMonth() {
        assertEquals(month, invoice.getInvoiceMonth());
    }

    @Test
    void getStartDate() {
        assertEquals(startDate, invoice.getStartDate());
    }


    @Test
    void getTotalCharges() {
        assertEquals(startDate, invoice.getStartDate());
    }

    @Test
    void getTotalHours() {
        invoice.extractLineItems(timeCard);

        assertEquals(75, invoice.getTotalHours());
    }

    @Test
    void toReportString() throws IOException {
        invoice.extractLineItems(timeCard);

        Path fileName = Path.of("./src/test/java/edu/uw/cp520/scg/domain/invoice");
        List<String> listString = Files.readAllLines(fileName);

        for (String line : listString) {
            assertTrue(invoice.toReportString().contains(line));
        }
    }

    @Test
    void testToString() {
        assertTrue(invoice.toString().contains("clientAccount=TimBiz"));
        assertTrue(invoice.toString().contains("month=JANUARY"));
        assertTrue(invoice.toString().contains("invoiceYear=2020"));
    }
}