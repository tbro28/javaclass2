package app;

import edu.uw.cp520.scg.domain.*;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import persistent.DbServer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InitDb {

        /*
      Driver class: org.apache.derby.jdbc.ClientDriver (usage optional)
      URL:          jdbc:derby://localhost:1527/memory:scgDb
      Username:     student
      Password:     student
     */

    private static final String URL = "jdbc:derby://localhost:1527/memory:scgDb";
    private static final String USERNAME = "student";
    private static final String PASSWORD = "student";
    private static final String QUERY_STRING = "SELECT * from skills";






/*    public InitDb() throws SQLException {
    }*/

    //try with resources to auto-close connections to the DB.

    static Connection connection;

    public static void main(String args[]){


        Address address = new Address( "streetNumberDiff", "city", StateCode.WA,"postalCode");
        String name = "TimBiz1";
        String name2 = "TimBiz2";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("Brown22222222", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);
        ClientAccount clientAccount2 = new ClientAccount(name2, personalName2, address);


        Consultant consultant = new Consultant(personalName);


        LocalDate localDate = LocalDate.of(2021, 1, 15);
        TimeCard timeCard = new TimeCard(consultant, localDate);

        ConsultantTime consultantTime1 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 50);
        ConsultantTime consultantTime2 = new ConsultantTime(localDate, clientAccount, Skill.PROJECT_MANAGER, 75);
        ConsultantTime consultantTime3 = new ConsultantTime(localDate, NonBillableAccount.BUSINESS_DEVELOPMENT, Skill.PROJECT_MANAGER, 50);

        List<ConsultantTime> consultantTimeList = new ArrayList<>();
        consultantTimeList.add(consultantTime1);
        consultantTimeList.add(consultantTime2);
        consultantTimeList.add(consultantTime3);

        timeCard.addConsultantTime(consultantTime1);
        timeCard.addConsultantTime(consultantTime2);
        timeCard.addConsultantTime(consultantTime3);



        DbServer dbServer = new DbServer(URL, USERNAME, PASSWORD);

        try {



 /*
            dbServer.addConsultant(consultant);
            dbServer.getConsultants();

            dbServer.addClient(clientAccount);
            dbServer.addClient(clientAccount2);
            dbServer.getClients();
           */

            dbServer.addConsultant(consultant);
            dbServer.addClient(clientAccount);
            dbServer.addClient(clientAccount2);

            dbServer.addTimeCard(timeCard);


            //SQLException throwables
        } catch (Exception e) {
            //throwables.printStackTrace();
        }


        System.out.println("Query successful.");


    }



/*    catch (SQLException e) {

        e.printStackTrace();

    }*/



}
