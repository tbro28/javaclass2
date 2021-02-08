package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ConsultantTime class.
 *
 * @author Tim Brown
 */
class ConsultantTimeTest {

    //      business.name
    //(StateCode stateCode, String streetNumber, String city, String postalCode)
    Address address = new Address("streetNumber", "city", StateCode.WA,"postalCode");

    Consultant consultant;
    LocalDate localDate;
    TimeCard timeCard;

    ConsultantTime consultantTime1;
    ConsultantTime consultantTime2;
    ConsultantTime consultantTime3;

    ClientAccount clientAccount;


    @BeforeEach
    void setUp() {
        consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        localDate = LocalDate.of(2021, 1, 15);
        timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        clientAccount = new ClientAccount(name, personalName, address);

        consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.SOFTWARE_TESTER, 50);
        consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.SICK_LEAVE, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);
    }


    @AfterEach
    void tearDown() {
    }


    @Test
    void constructorZeroHours() {
        assertThrows(IllegalArgumentException.class, () -> new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 0));
    }

    @Test
    void setToZeroHours() {
        assertThrows(IllegalArgumentException.class, () -> consultantTime1.setHours(0));
    }

    @Test
    void isBillableTrue() {
        assertTrue(consultantTime1.isBillable());
    }

    @Test
    void isBillableFalse() {
        assertFalse(consultantTime3.isBillable());
    }

    @Test
    void getSkill() {
        assertEquals(Skill.PROJECT_MANAGER, consultantTime1.getSkill());
    }

    @Test
    void setSkill() {
        consultantTime1.setSkill(Skill.SYSTEM_ARCHITECT);

        assertEquals(Skill.SYSTEM_ARCHITECT, consultantTime1.getSkill());
    }

    @Test
    void getAccountClient() {
        assertEquals(clientAccount, consultantTime1.getAccount());
    }

    @Test
    void getAccountNonBillable() {
        assertEquals(NonBillableAccount.SICK_LEAVE, consultantTime3.getAccount());
    }

    @Test
    void setAccount() {

        String name = "ChangedAccount";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount changedAccount = new ClientAccount(name, personalName, address);

        consultantTime1.setAccount(changedAccount);

        assertEquals(changedAccount, consultantTime1.getAccount());
    }

    @Test
    void getDate() {
        LocalDate localDateTest = LocalDate.of(2021, 1, 15);

        assertEquals(localDateTest, consultantTime1.getDate());
    }

    @Test
    void setDate() {
        LocalDate localDateTest = LocalDate.of(2020, 12, 15);
        consultantTime1.setDate(localDateTest);

        assertEquals(localDateTest, consultantTime1.getDate());
    }

    @Test
    void getHours() {
        assertEquals(50, consultantTime2.getHours());
    }

    @Test
    void setHours() {
        consultantTime2.setHours(100);

        assertEquals(100, consultantTime2.getHours());
    }

    @Test
    void testEqualsBillable() {
        ConsultantTime consultantTimeDuplicate2 = new ConsultantTime(localDate, clientAccount, Skill.SOFTWARE_TESTER, 50);

        assertTrue(consultantTime2.equals(consultantTimeDuplicate2));
    }

    @Test
    void testEqualsNonBillable() {
        ConsultantTime consultantTimeDuplicate3 = new ConsultantTime(localDate, NonBillableAccount.SICK_LEAVE, Skill.PROJECT_MANAGER, 50);

        assertTrue(consultantTime3.equals(consultantTimeDuplicate3));
    }

    @Test
    void testHashCode() {
        ConsultantTime consultantTimeDuplicate2 = new ConsultantTime(localDate, clientAccount, Skill.SOFTWARE_TESTER, 50);

        assertEquals(consultantTimeDuplicate2.hashCode(), consultantTime2.hashCode());

    }

    @Test
    void testToString() {

        System.out.println(consultantTime1);

        assertEquals("ConsultantTime{skill=Project Manager, date=2021-01-15, hours=50}",
                consultantTime1.toString());
    }
}