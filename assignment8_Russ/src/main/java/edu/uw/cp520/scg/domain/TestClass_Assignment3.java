package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestClass_Assignment3 {

    public static void main(final String[] args) {
        StateCode state = StateCode.WA;

        String bizName1 = "TimBiz";
        String bizName2 = "TimB2";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("ZBro2", "Tim", "Middle");

        //Serialize
        Consultant consultant1 = new Consultant(personalName);
        Consultant consultant2 = new Consultant(personalName2);

        LocalDate localDate = LocalDate.of(2020, 1, 20);
        LocalDate localDate2 = LocalDate.of(2020, 1, 15);

        //Serialize
        TimeCard timeCard1 = new TimeCard(consultant1, localDate);
        TimeCard timeCard2 = new TimeCard(consultant2, localDate2);

        Address address = new Address("TimsStreetNumber", "TimsCity", StateCode.WA, "TimsPostalCode");

        //Serialize
        ClientAccount clientAccount = new ClientAccount(bizName1, personalName, address);
        ClientAccount clientAccount2 = new ClientAccount(bizName2, personalName2, address);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 25);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime4 = new ConsultantTime(localDate2, clientAccount, Skill.PROJECT_MANAGER, 75);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.UNKNOWN_SKILL, 100);

        ConsultantTime consultantTime5 = new ConsultantTime(localDate, clientAccount2, Skill.SOFTWARE_ENGINEER, 5);
        ConsultantTime consultantTime6 = new ConsultantTime(localDate, clientAccount2, Skill.SOFTWARE_ENGINEER, 10);
        ConsultantTime consultantTime7 = new ConsultantTime(localDate, clientAccount2, Skill.SYSTEM_ARCHITECT, 15);
        ConsultantTime consultantTime8 = new ConsultantTime(localDate, clientAccount2, Skill.SYSTEM_ARCHITECT, 20);



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

/*

        //ClientAccount clientAccount = new ClientAccount(String "Client Name", PersonalName personalName);
        Month month = Month.of(1);
        int invoiceYear = 2020;

        Invoice invoice = new Invoice(clientAccount, month, invoiceYear);

        invoice.extractLineItems(timeCard1);
        invoice.extractLineItems(timeCard2);

        */
/*
        System.out.println(invoice.getStartDate());
        System.out.println(invoice.getTotalCharges());
        System.out.println(invoice.getTotalHours());

        System.out.println(invoice.toString());
         *//*


        System.out.println(invoice.toReportString());
*/





        //  for(InvoiceLineItem invoiceLineItem : invoice.extractLineItems(timeCard)) {

      //  }


        System.out.println("Consultant comparison: " + consultant1.compareTo(consultant2));
        System.out.println("Consultant comparison: " + consultant1.compareTo(consultant1));
        System.out.println("Timecard comparison: " + timeCard1.compareTo(timeCard2));
        System.out.println("Timecard comparison: " + timeCard1.compareTo(timeCard1));


        List<TimeCard> timeCardList = new ArrayList<>();
        timeCardList.add(timeCard1);
        timeCardList.add(timeCard2);

        List<ClientAccount> clientAccountList = new ArrayList<>();
        clientAccountList.add(clientAccount);
        clientAccountList.add(clientAccount2);

        List<Consultant> consultantList = new ArrayList<>();
        consultantList.add(consultant1);
        consultantList.add(consultant2);


        /**
         * Timecard serialization.
         */
        Path path = Path.of("src/main/resources/TimeCardList.ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));

            out.writeObject(timeCardList);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Client serialization.
         */
        path = Path.of("src/main/resources/ClientList.ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));

            out.writeObject(clientAccountList);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Consultant serialization.
         * Log the name of the consultant.
         */
        path = Path.of("src/main/resources/ConsultantList.ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path));

            out.writeObject(consultantList);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        List<TimeCard> tCardListDeserialized = null;
        List<ClientAccount> clientCardListDeserialized = null;
        List<Consultant> conCardListDeserialized = null;


        /**
         * Timecard deserialization.
         */
        path = Path.of("src/main/resources/TimeCardList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream( Files.newInputStream(path));

            //deserialize the List
            tCardListDeserialized = (List<TimeCard>)in.readObject();
            //display its data
            for(TimeCard t : tCardListDeserialized){
                System.out.println("Recovered TimeCard: " + t);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Client deserialization.
         */
        path = Path.of("src/main/resources/ClientList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream( Files.newInputStream(path));

            //deserialize the List
            clientCardListDeserialized = (List<ClientAccount>)in.readObject();
            //display its data
            for(ClientAccount t : clientCardListDeserialized){
                System.out.println("Recovered ClientAccount: " + t.getName());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Consultant deserialization.
         * Log the name of the consultant.
         */
        path = Path.of("src/main/resources/ConsultantList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream( Files.newInputStream(path));

            //deserialize the List
            conCardListDeserialized = (List<Consultant>)in.readObject();
            //display its data
            for(Consultant t : conCardListDeserialized){
                System.out.println("Recovered Consultant: " + t);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //ClientAccount clientAccount = new ClientAccount(String "Client Name", PersonalName personalName);
        Month monthSerial = Month.of(1);
        int invoiceYearSerial = 2020;

        Invoice invoiceSerial = new Invoice(clientCardListDeserialized.get(0), monthSerial, invoiceYearSerial);

        invoiceSerial.extractLineItems(tCardListDeserialized.get(0));
        invoiceSerial.extractLineItems(timeCard2);

        /*
        System.out.println(invoice.getStartDate());
        System.out.println(invoice.getTotalCharges());
        System.out.println(invoice.getTotalHours());

        System.out.println(invoice.toString());
         */

        System.out.println(invoiceSerial.toReportString());














        //timeCardList.add(timeCard3);

        final TimeCardConsultantComparator timeCardConsultantComparator = new TimeCardConsultantComparator();
        System.out.println("timeCardConsultantComparator: " + timeCardConsultantComparator.compare(timeCard1, timeCard2));

/*
        TimeCardListUtil timeCardListUtil = new TimeCardListUtil();
        timeCardListUtil.sortByStartDate(timeCardList);
*/


        //       getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange)



        DateRange dateRange = new DateRange("2020-01-10", "2020-02-01");
        System.out.println(dateRange.getStartDate() +" & " +dateRange.getEndDate());
        List<TimeCard> filteredDate = TimeCardListUtil.getTimeCardsForDateRange(timeCardList, dateRange);

        for(TimeCard timeCard : filteredDate) {
            System.out.println("\ngetTimeCardsForDateRange: \n" + timeCard.toString());
            System.out.println(timeCard.getWeekStartingDay() + "\n");
        }

        System.out.println("\ngetTimeCardsForConsultant: ");
        List<TimeCard> filteredConsultant = TimeCardListUtil.getTimeCardsForConsultant(timeCardList, consultant1);
        for(TimeCard tc : filteredConsultant) {
            System.out.println(tc.toString());
        }

        System.out.println("\nSorted by consultant: ");
        TimeCardListUtil.sortByConsultantName(timeCardList);
        timeCardList.stream()
                .forEach(System.out::println);

        System.out.println("\nSorted by start date: ");
        TimeCardListUtil.sortByStartDate(timeCardList);
        timeCardList.stream()
                .forEach(System.out::println);


        /*
        System.out.println("where is this:");
        TimeCardListUtil.getTimeCardsForDateRange(timeCardList, dateRange)
                .stream()
                .forEach(System.out::println);
*/


    }
}
