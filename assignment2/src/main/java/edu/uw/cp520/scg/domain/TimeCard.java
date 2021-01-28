package edu.uw.cp520.scg.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 *
 * Represents a time card capable of storing a collection
 * of a consultant's billable and non-billable hours for a week.
 *
 * @author Tim Brown
 */
public class TimeCard {

    /**
     * Consultant associated to the timecard.
     */
    Consultant consultant;

    /**
     * Local date to start at.
     */
    LocalDate weekStartingDay;

    /**
     * List to hold consulting time.
     */
    List<ConsultantTime> consultingHours = new ArrayList<>();


    /**
     *
     * Constructor that sets the consultant and week starting day.
     *
     * @param consultant
     * @param weekStartingDay
     */
    public TimeCard(Consultant consultant, LocalDate weekStartingDay) {
        this.consultant = consultant;
        this.weekStartingDay = weekStartingDay;
    }


    /**
     *
     * Add a consultant time to a time card.
     *
     * @param consultantTime
     */
    public void addConsultantTime(ConsultantTime consultantTime){
        consultingHours.add(consultantTime);
    }


    /**
     *
     * Return a list of billable hours for a given client.
     *
     * @param clientName
     * @return the billable hours.
     */
    public List<ConsultantTime> getBillableHoursForClient(String clientName) {

        List<ConsultantTime> consultantClientTime = new ArrayList<>();

        for(ConsultantTime consultantTime : consultingHours) {
            if(consultantTime.getAccount().getName().equals(clientName))
                if(consultantTime.isBillable()) {
                    consultantClientTime.add(consultantTime);
                }
        }

        return consultantClientTime;
    }


    /**
     *
     * Get the consultant.
     *
     * @return the consultant.
     */
    public Consultant getConsultant() {
        return consultant;
    }


    /**
     *
     * Return a list of consulting times.
     *
     * @return the consulting hours.
     */
    public List<ConsultantTime> getConsultingHours() {
        return consultingHours;
    }


    /**
     *
     * Get total billable hours regardless of client.
     *
     * @return the total billable hours.
     */
    public int getTotalBillableHours() {
        int totalBillableHours = 0;

        for(ConsultantTime consultantTime : consultingHours) {
            if(consultantTime.isBillable()) {
                totalBillableHours += consultantTime.getHours();
            }
        }

        return totalBillableHours;
    }


    /**
     *
     * Get total hours regardless of client.
     *
     * @return the total hours, both billable and non-billable.
     */
    public int getTotalHours() {
        int totalHours = 0;

        for(ConsultantTime consultantTime : consultingHours) {
            totalHours += consultantTime.getHours();
        }

        return totalHours;
    }


    /**
     *
     * Get total non-billable hours regardless of client.
     *
     * @return the total non-billable hours.
     */
    public int getTotalNonBillableHours() {
        int totalNonBillableHours = 0;

        for(ConsultantTime consultantTime : consultingHours) {
            if(!consultantTime.isBillable()) {
                totalNonBillableHours += consultantTime.getHours();
            }
        }

        return totalNonBillableHours;
    }


    /**
     *
     * Get the week starting day.
     *
     * @return the week starting day.
     */
    public LocalDate getWeekStartingDay() {
        return weekStartingDay;
    }


    /**
     *
     * Returns a formatted report.
     *
     * @return a formatted time card report.
     */
    public String toReportString() {

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("MMM dd, yyyy");
        //System.out.println(weekStartingDay.format(dateFormatter));

        String strWeekStartingDate = "Week Starting: " + weekStartingDay.format(dateFormatter);

        //Can only be 41 characters long before overlapping with the week date string.
        String consultantName = consultant.getName().toString().substring(0, Math.min(consultant.getName().toString().length(), 41));



        //https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
        StringBuilder sb = new StringBuilder();

        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        formatter.format("=".repeat(68)+"\n");

        formatter.format(consultantName)
        .format(" ".repeat(41-consultantName.length()))
        .format(strWeekStartingDate+"\n\n")

        .format("Billable Time:\n")

        .format("%-29s", "Account")
        .format("%-12s", "Date")
        .format("%-7s", "Hours")
        .format("%-20s", "Skill")
        .format("\n")

        .format("-".repeat(27)+"  ")
        .format("-".repeat(10)+"  ")
        .format("-".repeat(5)+"  ")
        .format("-".repeat(18))
        .format("\n");

        for (int i = 0; i < 7; i++) {

            //System.out.println(weekStartingDay.plusDays(i));

            for (ConsultantTime consultantTime : consultingHours) {

                //System.out.println(consultantTime.getHours());

                if(consultantTime.isBillable() && consultantTime.getDate().equals(weekStartingDay.plusDays(i))) {
                    formatter.format("%-20s", consultantTime.getAccount().getName());
                    formatter.format("%-20s", consultantTime.getDate());
                    formatter.format("%-20s", consultantTime.getHours());
                    formatter.format("%-20s", consultantTime.getSkill().name());
                    formatter.format("\n");

                }
            }

        }


        formatter.format("=".repeat(68));



        //System.out.println(formatter);

        String strReport = formatter.toString();

        formatter.close();


        String result2 = String.format("|%-20s|", "Hello World");

        //formatter.format("-%-2s-", "Hello World");

        String result5 = String.format("|%20s|", "Hello World")     // |$$$$$$$$$Hello$World|
                .replace(' ', '$');

        String result6 = String.format("|%20s|", "")     // |$$$$$$$$$Hello$World|
                .replace(' ', '-');

        String result7 = String.format("%68s", "=");     // |$$$$$$$$$Hello$World|


/*
        System.out.println(weekStartingDay);
        System.out.println(weekStartingDay.plusDays(1));
        System.out.println(weekStartingDay.plusDays(2));

        DateTimeFormatter dateFormatter2 =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(weekStartingDay.format(dateFormatter));
        System.out.printf("%1$tb %1$td, %1$tY%n", weekStartingDay);


        System.out.println(result2);
        //System.out.println(formatter);
        System.out.println(result5);
        System.out.println(result6);
        System.out.println(result7);
*/


        return strReport;


    }


    /**
     *
     * Return a string version of the TimeCard object.
     *
     * @return a string version of the object.
     */
    @Override
    public String toString() {
        return "TimeCard{" +
                "consultant=" + consultant +
                ", weekStartingDay=" + weekStartingDay +
                ", consultingHours=" + consultingHours +
                '}';
    }
}
