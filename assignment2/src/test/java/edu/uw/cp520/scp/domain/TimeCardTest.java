package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scp.util.PersonalName;
import org.junit.jupiter.api.Test;

import java.lang.ref.Cleaner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {


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

    @Test
    void getConsultingHours() {

        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2021, 1, 15);
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
    }

    @Test
    void getTotalHours() {
    }

    @Test
    void getTotalNonBillableHours() {
    }

    @Test
    void getWeekStartingDay() {
    }

    @Test
    void toReportString() {
    }

    @Test
    void testToString() {
    }
}