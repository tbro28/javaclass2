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

        String strWeekStartingDate = "Week Starting: "
                + weekStartingDay.format(dateFormatter);

        //Can only be 41 characters long before overlapping
        // with the week date string.
        String consultantName = consultant.getName().toString().substring(0,
                Math.min(consultant.getName().toString().length(), 41));

        //https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
        StringBuilder sb = new StringBuilder();

        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        formatter.format("=".repeat(68)+"\n");

        formatter.format(consultantName)
        .format(" ".repeat(41-consultantName.length()))
        .format(strWeekStartingDate+"\n\n")

        //Billable time formatting.
        .format("Billable Time:\n")

        .format("%-29s", "Account")
        .format("%-12s", "Date")
        .format("%-7s", "Hours")
        .format("%-20s", "Skill")
        .format("\n")

        .format("-".repeat(27)+"  ")
        .format("-".repeat(10)+"  ")
        .format("-".repeat(5)+"  ")
        .format("-".repeat(20))
        .format("\n");

        for (int i = 0; i < 7; i++) {
            for (ConsultantTime consultantTime : consultingHours) {
                if(consultantTime.isBillable() && consultantTime.getDate()
                        .equals(weekStartingDay.plusDays(i))) {
                    formatter.format("%-29s",
                            consultantTime.getAccount().getName())
                    .format("%-12s", consultantTime.getDate())
                    .format("%5s  ", consultantTime.getHours())
                    .format("%-20s", consultantTime.getSkill())
                    .format("\n");
                }
            }
        }

        //Non-billable time formatting.
        formatter.format("\n")
        .format("Non-billable Time:\n")

        .format("%-29s", "Account")
        .format("%-12s", "Date")
        .format("%-7s", "Hours")
        .format("%-20s", "Skill")
        .format("\n")

        .format("-".repeat(27)+"  ")
        .format("-".repeat(10)+"  ")
        .format("-".repeat(5)+"  ")
        .format("-".repeat(20))
        .format("\n");

        for (int i = 0; i < 7; i++) {
            for (ConsultantTime consultantTime : consultingHours) {
                if(!consultantTime.isBillable() && consultantTime.getDate()
                        .equals(weekStartingDay.plusDays(i))) {
                    formatter.format("%-29s",
                            consultantTime.getAccount().getName())
                            .format("%-12s", consultantTime.getDate())
                            .format("%5s  ", consultantTime.getHours())
                            .format("%-20s", consultantTime.getSkill())
                            .format("\n");
                }
            }
        }

        //Summary section of the report.
        formatter.format("\n")
        .format("Summary:\n")

        .format("%-41s", "Total Billable:")
        .format("%5s\n", this.getTotalBillableHours())
        .format("%-41s", "Total Non-Billable:")
        .format("%5s\n", this.getTotalNonBillableHours())
        .format("%-41s", "Total Hours:")
        .format("%5s\n", this.getTotalHours())

        .format("=".repeat(68));

        String strReport = formatter.toString();

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
