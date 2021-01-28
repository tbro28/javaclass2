package edu.uw.cp520.scg.domain;

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
    PROJECT_MANAGER(75, "Project Manager"),
    /**
     * Software engineer value.
     */
    SOFTWARE_ENGINEER(50, "Software Engineer"),
    /**
     * Software tester value.
     */
    SOFTWARE_TESTER(25, "Software Tester"),
    /**
     * System architect value.
     */
    SYSTEM_ARCHITECT(100, "System Architect"),
    /**
     * Unknown skill value.
     */
    UNKNOWN_SKILL(150, "Unknown Skill");

    /**
     * Holds the rate value.
     */
    private int rate;

    /**
     * Holds the skill name value.
     */
    private String skillName;

    /**
     * Sets the pay rate for the skill.
     *
     * @param rate is the rate of pay.
     * @param skillName is the name of the skill.
     */
    Skill(int rate, String skillName) {
        this.rate = rate;
        this.skillName = skillName;
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
        return skillName;
    }

}
