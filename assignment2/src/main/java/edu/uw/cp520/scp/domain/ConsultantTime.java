package edu.uw.cp520.scp.domain;

import java.time.LocalDate;
import java.util.Objects;

public class ConsultantTime {

    Skill skill;
    Account account;
    LocalDate date;
    int hours;


    public ConsultantTime(LocalDate date, Account account, Skill skillType, int hours) {
        this.skill = skillType;
        this.account = account;
        this.date = date;
        this.hours = hours;
    }

    public boolean isBillable() {
        return account.isBillable();
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ConsultantTime that = (ConsultantTime) obj;

        return hours == that.hours && skill == that.skill && Objects.equals(account, that.account) && Objects.equals(date, that.date);
    }
/*
    public boolean equals(Object obj){
        return true;
    }
*/

    @Override
    public int hashCode() {
        return Objects.hash(skill, account, date, hours);
    }


    @Override
    public String toString() {
        return "ConsultantTime{" +
                "skill=" + skill +
                ", account=" + account +
                ", date=" + date +
                ", hours=" + hours +
                '}';
    }
}
