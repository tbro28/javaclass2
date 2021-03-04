package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.domain.*;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TimeCard class.
 *
 * @author Tim Brown
 */
class TimeCardTest {

    Address address = new Address("streetNumber", "city", StateCode.WA,"postalCode");

    @Test
    void constructor() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        assertNotNull(timeCard);
    }

    @Test
    void addConsultantTime() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);

        timeCard.addConsultantTime(consultantTime);

        assertEquals(50, timeCard.getTotalBillableHours());
    }

    @Test
    void getBillableHoursForClient() {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);

        assertEquals(consultantTimeList, timeCard.getBillableHoursForClient(name));
    }

    @Test
    void getConsultant() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        assertEquals(consultant, timeCard.getConsultant());
    }

    @Test
    void getConsultingHours() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);

        assertEquals(consultantTimeList, timeCard.getConsultingHours());
    }

    @Test
    void getTotalBillableHours() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);

        assertEquals(100, timeCard.getTotalBillableHours());
    }

    @Test
    void getTotalHours() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);

        assertEquals(150, timeCard.getTotalHours());
    }

    @Test
    void getTotalNonBillableHours() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);

        assertEquals(50, timeCard.getTotalNonBillableHours());
    }

    @Test
    void getWeekStartingDay() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        assertEquals(localDate, timeCard.getWeekStartingDay());
    }

    @Test
    void toReportString() throws IOException {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);

        Path fileName = Path.of("./src/test/java/edu/uw/cp520/scg/domain/report");
        List<String> listString = Files.readAllLines(fileName);

        for (String line : listString)
            assertTrue(timeCard.toReportString().contains(line));
    }

    @Test
    void testToString() {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        assertEquals("TimeCard{consultant=Consultant{name=Consultant: Brown, Tom, Jack}, " +
                        "weekStartingDay=2021-01-15, consultingHours=[]}",
                timeCard.toString());
    }

    @Test
    void toCompareEquals() throws IOException {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);
        TimeCard timeCard2 = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);
        timeCard2.addConsultantTime(consultantTime1);
        timeCard2.addConsultantTime(consultantTime2);
        timeCard2.addConsultantTime(consultantTime3);

        assertEquals(0, timeCard.compareTo(timeCard2));
    }

    @Test
    void toCompareNotEqualDiffStartingDate() throws IOException {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        LocalDate localDate2 = LocalDate.of(2021, 1, 16);
        TimeCard timeCard = new TimeCard(consultant, localDate);
        TimeCard timeCard2 = new TimeCard(consultant, localDate2);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);
        timeCard2.addConsultantTime(consultantTime1);
        timeCard2.addConsultantTime(consultantTime2);
        timeCard2.addConsultantTime(consultantTime3);

        assertNotEquals(0, timeCard.compareTo(timeCard2));
    }
}