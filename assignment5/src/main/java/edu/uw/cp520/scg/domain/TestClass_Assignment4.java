package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;

public class TestClass_Assignment4 {

    public static void main(final String[] args) {

        StateCode state = StateCode.WA;

        //System.out.println(state.name());
        //System.out.println(state.getName());

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("Bro2", "Tim", "Middle");

        Consultant consultant1 = new Consultant(personalName);
        Consultant consultant2 = new Consultant(personalName2);

        LocalDate localDate = LocalDate.of(2017, 1, 15);
        LocalDate localDate2 = LocalDate.of(2017, 2, 15);
        LocalDate localDate3 = LocalDate.of(2017, 2, 15);
        LocalDate localDate4 = LocalDate.of(2017, 2, 10);

        TimeCard timeCard1 = new TimeCard(consultant1, localDate);
        TimeCard timeCard2 = new TimeCard(consultant2, localDate2);
        TimeCard timeCard3 = new TimeCard(consultant2, localDate3);
        TimeCard timeCard4 = new TimeCard(consultant2, localDate4);

        List<TimeCard> timeCardList = new ArrayList<>();
        timeCardList.add(timeCard1);
        timeCardList.add(timeCard2);
        timeCardList.add(timeCard3);
        timeCardList.add(timeCard4);

        //      business.name
        //(StateCode stateCode, String streetNumber, String city, String postalCode)
        Address address = new Address("TimsStreetNumber", "TimsCity", StateCode.WA, "TimsPostalCode");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);
        ClientAccount clientAccount2 = new ClientAccount(name, personalName2, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 25);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime4 = new ConsultantTime(localDate2, clientAccount, Skill.PROJECT_MANAGER, 75);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate2, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.UNKNOWN_SKILL, 100);

        ConsultantTime consultantTime5 = new ConsultantTime(localDate3, clientAccount2, Skill.SOFTWARE_ENGINEER, 5);
        ConsultantTime consultantTime6 = new ConsultantTime(localDate3, clientAccount2, Skill.SOFTWARE_ENGINEER, 10);
        ConsultantTime consultantTime7 = new ConsultantTime(localDate4, clientAccount2, Skill.SYSTEM_ARCHITECT, 15);
        ConsultantTime consultantTime8 = new ConsultantTime(localDate4, clientAccount2, Skill.SYSTEM_ARCHITECT, 20);



        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);
        consultantTimeList.add(consultantTime4);

        consultantTimeList.add(consultantTime5);
        consultantTimeList.add(consultantTime6);
        consultantTimeList.add(consultantTime7);
        consultantTimeList.add(consultantTime8);


        timeCard1.addConsultantTime(consultantTime1);
        timeCard1.addConsultantTime(consultantTime2);
        timeCard1.addConsultantTime(consultantTime3);
        timeCard1.addConsultantTime(consultantTime4);

        timeCard2.addConsultantTime(consultantTime5);
        timeCard2.addConsultantTime(consultantTime6);
        timeCard2.addConsultantTime(consultantTime7);
        timeCard2.addConsultantTime(consultantTime8);

        //ClientAccount clientAccount = new ClientAccount(String "Client Name", PersonalName personalName);
        Month month = Month.of(1);
        int invoiceYear = 2020;

        Invoice invoice = new Invoice(clientAccount, month, invoiceYear);

        invoice.extractLineItems(timeCard1);
        invoice.extractLineItems(timeCard2);

        /*
        System.out.println(invoice.getStartDate());
        System.out.println(invoice.getTotalCharges());
        System.out.println(invoice.getTotalHours());

        System.out.println(invoice.toString());
        */

//System.out.println(invoice.toReportString());

        final TimeCardConsultantComparator timeCardConsultantComparator = new TimeCardConsultantComparator();
        System.out.println(timeCardConsultantComparator.compare(timeCard1, timeCard2));

        TimeCardListUtil timeCardListUtil = new TimeCardListUtil();

        timeCardListUtil.sortByStartDate(timeCardList);


 //       getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange)

        DateRange dateRange = new DateRange("2017-01-16", "2017-02-01");

        System.out.println(dateRange.getStartDate() +" & " +dateRange.getEndDate());

        List<TimeCard> filtered = TimeCardListUtil.getTimeCardsForDateRange(timeCardList, dateRange);

        for(TimeCard timeCard : filtered) {
            System.out.println(timeCard.toString());
            System.out.println(timeCard.getWeekStartingDay());
        }

        TimeCardListUtil.getTimeCardsForDateRange(timeCardList, dateRange)
        .stream()
        .forEach(System.out::println);



        /*Collections.sort(timeCardList, Comparator.comparing(TimeCardConsultantComparator::compare);
                                .comparingInt(Integer::intValue)
*/
/*
        Arrays.sort(timeCardList);
        TimeCardListUtil timeCardListUtil = new TimeCardListUtil();
        timeCardList -> timeCardListUtil
        timeCardListUtil.
        TimeCardListUtil.sortByStartDate(timeCardList);

        Stream<TimeCard> times = Stream.of(timeCard1);
        times.forEach(System.out::println);
*/

      //  for(InvoiceLineItem invoiceLineItem : invoice.extractLineItems(timeCard)) {

      //  }
    }
}
