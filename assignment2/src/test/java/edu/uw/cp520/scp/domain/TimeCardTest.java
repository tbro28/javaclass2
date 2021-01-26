package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scp.util.PersonalName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.ref.Cleaner;
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

    /*
    Consultant consultant;
    LocalDate localDate;
    TimeCard timeCard;

    public TimeCardTest() {
        consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        localDate = LocalDate.of(2021, 1, 15);
        timeCard = new TimeCard(consultant, localDate);
    }
     */

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
        ClientAccount clientAccount = new ClientAccount(name, personalName);

        //ConsultantTime(LocalDate date, Account account, Skill skillType, int hours)
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
        ClientAccount clientAccount = new ClientAccount(name, personalName);

        //ConsultantTime(LocalDate date, Account account, Skill skillType, int hours)
        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);

        //System.out.println(timeCard);

        assertEquals(consultantTimeList, timeCard.getBillableHoursForClient(name));
    }

    @Test
    void getConsultant() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        assertEquals(consultant, timeCard.getConsultant());
    }



    /**
     * Timecards to test methods.
     */
    private TimeCard createTestTimeCards() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName);

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

        return timeCard;
    }


    @Test
    void getConsultingHours() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        //TimeCard timeCard = createTestTimeCards();
        TimeCard timeCard = new TimeCard(consultant, localDate);




        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName);

        //String name = "TimBiz";
   //     PersonalName personalName2 = new PersonalName("Wonder", "Stevie", "Jack");
    //    ClientAccount clientAccount = new ClientAccount(name, personalName);


        //ConsultantTime(LocalDate date, Account account, Skill skillType, int hours)
        //System.out.println(NonBillableAccount.BUSINESS_DEVELOPMENT.getName());
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

        //System.out.println(timeCard);

        assertEquals(consultantTimeList, timeCard.getConsultingHours());
    }

    @Test
    void getTotalBillableHours() {
        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount = new ClientAccount(name, personalName);

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
        ClientAccount clientAccount = new ClientAccount(name, personalName);

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
        ClientAccount clientAccount = new ClientAccount(name, personalName);

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

        System.out.println(timeCard.weekStartingDay + " --- "+timeCard.weekStartingDay.plusDays(30));

        assertEquals(localDate, timeCard.getWeekStartingDay());
    }

    @Test
    void toReportString() {

        //Not sure...maybe compare StringBuilders.
        //You'd have to completely rebuild it here I believe???

    }

    @Test
    void testToString() {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        assertEquals("TimeCard{consultant=Consultant{name=PersonalName{lastName='Brown', " +
                "firstName='Tom', middleName='Jack'}}, weekStartingDay=2021-01-15, consultingHours=[]}",
                timeCard.toString());
    }
}