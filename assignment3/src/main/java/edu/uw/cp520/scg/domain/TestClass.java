package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TestClass {

    public static void main(final String[] args) {

        StateCode state = StateCode.WA;

        //System.out.println(state.name());
        //System.out.println(state.getName());


        Consultant consultant = new Consultant(new PersonalName("Brown", "Tom", "Jack"));
        LocalDate localDate = LocalDate.of(2020, 1, 15);
        LocalDate localDate2 = LocalDate.of(2020, 2, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        //      business.name
        //(StateCode stateCode, String streetNumber, String city, String postalCode)
        Address address = new Address("TimsStreetNumber", "TimsCity", StateCode.WA, "TimsPostalCode");


        ClientAccount clientAccount = new ClientAccount(name, personalName, address);


        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 25);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime4 = new ConsultantTime(localDate2, clientAccount, Skill.PROJECT_MANAGER, 75);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 100);

        ConsultantTime consultantTime5 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 5);
        ConsultantTime consultantTime6 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 10);
        ConsultantTime consultantTime7 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 15);
        ConsultantTime consultantTime8 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 20);



        List<ConsultantTime> consultantTimeList = new ArrayList<>();
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
        timeCard.addConsultantTime(consultantTime3);
        timeCard.addConsultantTime(consultantTime4);

        timeCard.addConsultantTime(consultantTime5);
        timeCard.addConsultantTime(consultantTime6);
        timeCard.addConsultantTime(consultantTime7);
        timeCard.addConsultantTime(consultantTime8);


        //ClientAccount clientAccount = new ClientAccount(String "Client Name", PersonalName personalName);
        Month month = Month.of(1);
        int invoiceYear = 2020;



        Invoice invoice = new Invoice(clientAccount, month, invoiceYear);

        invoice.extractLineItems(timeCard);
/*
System.out.println(invoice.getStartDate());
System.out.println(invoice.getTotalCharges());
System.out.println(invoice.getTotalHours());

System.out.println(invoice.toString());
*/
System.out.println(invoice.toReportString());

      //  for(InvoiceLineItem invoiceLineItem : invoice.extractLineItems(timeCard)) {

      //  }



    }

}
