package edu.uw.cp520.scp.domain;

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



    Consultant consultant;
    LocalDate weekStartingDay;
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
     * @return
     */
    public List<ConsultantTime> getBillableHoursForClient(String clientName) {

        List<ConsultantTime> consultantClientTime = new ArrayList<>();

        for(ConsultantTime consultantTime : consultingHours) {

            if(consultantTime.getAccount().getName().equals(clientName))

                if(consultantTime.isBillable()) {
                    //System.out.println("HERE");
                    consultantClientTime.add(consultantTime);
                }
        }

        return consultantClientTime;
    }


    /**
     *
     * Get the consultant;
     *
     * @return
     */
    public Consultant getConsultant() {
        return consultant;
    }


    /**
     *
     * Return a list of consulting times.
     *
     * @return
     */
    public List<ConsultantTime> getConsultingHours() {
        return consultingHours;
    }


    /**
     *
     * Get total billable hours regardless of client.
     *
     * @return
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
     * @return
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
     * @return
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
     * @return
     */
    public LocalDate getWeekStartingDay() {
        return weekStartingDay;
    }


    /**
     *
     * Returns a formatted report.
     *
     * @return
     */
    public String toReportString() {


        return "REPORT TO DO!!!!!";


    }



    /**
     *
     * Return a string version of the TimeCard object.
     *
     * @return
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
