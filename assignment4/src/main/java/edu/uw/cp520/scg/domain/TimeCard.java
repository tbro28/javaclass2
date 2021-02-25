package edu.uw.cp520.scg.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * Represents a time card capable of storing a collection
 * of a consultant's billable and non-billable hours for a week.
 *
 * @author Tim Brown
 */
public class TimeCard implements Comparable<TimeCard> {

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

        consultingHours.stream()
                .filter(e -> e.getAccount().getName()
                        .equals(clientName) && e.isBillable())
                .forEach(consultantClientTime::add);
                //.forEach(e -> consultantClientTime.add(e))

        /*
        for(ConsultantTime consultantTime : consultingHours) {
            if(consultantTime.getAccount().getName().equals(clientName))
                if(consultantTime.isBillable()) {
                    consultantClientTime.add(consultantTime);
                }
        }
        */

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

    /**
     * TimeCard's natural ordering should be in ascending order by
     * beginning date,
     * consultant,
     * total billable hours
     * and finally total non-billable hours.
     *
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(TimeCard o) {

        int diff = 0;

        diff = this.getWeekStartingDay().compareTo(o.getWeekStartingDay());
        if ( diff != 0) {
            return diff;
        }
        diff = this.getConsultant().compareTo(o.getConsultant());
        if ( diff != 0) {
            return diff;
        }
        diff = Integer.compare(this.getTotalBillableHours(),
                o.getTotalBillableHours());
        if ( diff != 0) {
            return diff;
        }
        diff = Integer.compare(this.getTotalNonBillableHours(),
                o.getTotalNonBillableHours());
        if ( diff != 0) {
            return diff;
        }

        return 0;
    }

}
