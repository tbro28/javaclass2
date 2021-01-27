package edu.uw.cp520.scp.domain;

/**
 *
 * Skill that a client may be billed for, consists of a name and an hourly rate.
 *
 * @author Tim Brown
 */
public enum Skill {

    /**
     * Project manager value.
     */
    PROJECT_MANAGER(75),
    /**
     * Software engineer value.
     */
    SOFTWARE_ENGINEER(50),
    /**
     * Software tester value.
     */
    SOFTWARE_TESTER(25),
    /**
     * System architect value.
     */
    SYSTEM_ARCHITECT(100),
    /**
     * Unknown skill value.
     */
    UNKNOWN_SKILL(150);

    /**
     * Holds the rate value.
     */
    private int rate;

    /**
     * Sets the pay rate for the skill.
     *
     * @param rate is the rate of pay.
     */
    Skill(int rate) {
        this.rate = rate;
    }

    /**
     * Returns the pay rate set for a given skill.
     *
     * @return the pay rate.
     */
    public int getRate() {

        return rate;
    }

    /**
     * Creates a string of the enum.
     *
     * @return a string version of the enum.
     */
    @Override
    public String toString() {
        return "Skill{" +
                "rate=" + rate +
                '}';
    }

}
