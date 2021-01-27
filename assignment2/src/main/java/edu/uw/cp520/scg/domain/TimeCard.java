package edu.uw.cp520.scg.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


        return "REPORT TO DO!!!!!";


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
