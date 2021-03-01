package edu.uw.cp520.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import edu.uw.cp520.scg.domain.ConsultantTime;

/**
 *
 * A consultants time, maintains date, skill, account and hours data.
 *
 * @author Tim Brown
 */
public class ConsultantTime implements Serializable {

    /**Holds the skill of the consultant.*/
    Skill skill;

    /**Holds the account of the consultant.*/
    Account account;

    /**Holds the date for the consultant time.*/
    LocalDate date;

    /**Holds the hours of the consultant time.*/
    int hours;

    /**
     * Constructor for the class to set initial values.
     *
     * @param date
     * @param account
     * @param skillType
     * @param hours
     */
    public ConsultantTime(LocalDate date, Account account,
                          Skill skillType, int hours) {

        if (hours <= 0)
            throw new IllegalArgumentException((Integer.toString(hours)));

        this.skill = skillType;
        this.account = account;
        this.date = date;
        this.hours = hours;
    }

    /**
     * Determines if the consultant time is billable.
     *
     * @return a boolean.
     */
    public boolean isBillable() {
        return account.isBillable();
    }

    /**
     * Gets the consultant's skill.
     *
     * @return the skill.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Sets the consultant's skill for the time.
     *
     * @param skill
     */
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    /**
     * Returns the associated account object.
     *
     * @return the related account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the account information.
     *
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the date that is set.
     *
     * @return the date that has been set.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the consultant time.
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the hours related to the consultant's time.
     *
     * @return returns the hours that are set for the object.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Sets the consultant's hours.
     *
     * @param hours
     */
    public void setHours(int hours) {

        if (hours <= 0)
            throw new IllegalArgumentException((Integer.toString(hours)));

        this.hours = hours;
    }

    /**
     * Implements an equals method for the consultant class.
     *
     * @param obj
     * @return a boolean for whether the objects are equal or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ConsultantTime that = (ConsultantTime) obj;

        return hours == that.hours && skill == that.skill
                && Objects.equals(account, that.account)
                && Objects.equals(date, that.date);
    }

    /**
     * Implements a hashcode method for the consultant class.
     *
     * @return a hashcode of the current object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(skill, account, date, hours);
    }

    /**
     * Implements a string method for the consultant class.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "ConsultantTime{" +
                "skill=" + skill +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }
}
