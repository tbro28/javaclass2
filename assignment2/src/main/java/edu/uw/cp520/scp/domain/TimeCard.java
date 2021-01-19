package edu.uw.cp520.scp.domain;

import java.time.LocalDate;
import java.util.List;

public class TimeCard {

    Consultant consultant;
    LocalDate date;
    List<ConsultantTime> consultingHours;


    public TimeCard(Consultant consultant, LocalDate date) {
        this.consultant = consultant;
        this.date = date;
    }

    public void addConsultantTime(ConsultantTime consultantTime){

        consultingHours.add(consultantTime);

    }




}
