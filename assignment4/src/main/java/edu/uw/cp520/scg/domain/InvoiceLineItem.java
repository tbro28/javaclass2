package edu.uw.cp520.scg.domain;

import java.time.LocalDate;

/**
 *
 * Encapsulates a single billable item to be included in an invoice.
 *
 * @author Tim Brown
 */
public class InvoiceLineItem {

    /**date the service was provided.*/
    private LocalDate date;
    /**name of consultant providing the service.*/
    private Consultant consultant;
    /**the sevice/skill provided.*/
    private Skill skill;
    /**number of hours.*/
    private int hours;

    /**
     * Constructor for class; the only one.
     *
     * @param date
     * @param consultant
     * @param skill
     * @param hours
     */
    public InvoiceLineItem(LocalDate date, Consultant consultant,
                           Skill skill, int hours) {
        this.date = date;
        this.consultant = consultant;
        this.skill = skill;
        this.hours = hours;
    }

    /**
     * Get the date for this line item.
     *
     * @return the date for the invoice.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the consultant for this line item.
     *
     * @return the consultant for the line item.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Get the skill for this line item.
     *
     * @return the skill used for the line item.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Get the hours for this line item.
     *
     * @return the hours for a line item.
     */
    public int getHours() {
        return hours;
    }

//Check that this is right
//https://canvas.uw.edu/courses/1424631/files/72052478?fd_cookie_set=1
    /**
     * Get the charge for this line item.
     *
     * @return the charge for a given line item.
     */
    public int getCharge() {
        return skill.getRate() * hours;
    }

    /**
     * Print the date, consultant, skill, hours and charge for this line item.
     *
     * @return
     */
    @Override
    public String toString() {
        return "InvoiceLineItem{" +
                "date=" + date +
                ", consultant=" + consultant +
                ", skill=" + skill +
                ", hours=" + hours +
                '}';
    }
}
