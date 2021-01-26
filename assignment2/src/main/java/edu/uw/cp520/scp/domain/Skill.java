package edu.uw.cp520.scp.domain;

/**
 *
 * Skill that a client may be billed for, consists of a name and an hourly rate.
 *
 * @author Tim Brown
 */
public enum Skill {

    PROJECT_MANAGER(75),
    SOFTWARE_ENGINEER(50),
    SOFTWARE_TESTER(25),
    SYSTEM_ARCHITECT(100),
    UNKNOWN_SKILL(150);

    private int rate;

//    private Skill() {
//    }

    Skill(int rate) {
        this.rate = rate;
    }

    public int getRate() {

        return rate;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "rate=" + rate +
                '}';
    }

}
