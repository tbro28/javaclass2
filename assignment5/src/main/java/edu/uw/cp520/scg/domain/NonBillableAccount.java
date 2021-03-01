package edu.uw.cp520.scg.domain;

/**
 *
 * Accounts which can not be billed - non-billable accounts,
 * such as sick leave, vacation, or business development.
 *
 * @author Tim Brown
 */
public enum NonBillableAccount implements Account {

    /**
     * Sick leave.
     */
    SICK_LEAVE("Sick Leave"),
    /**
     * Vacation.
     */
    VACATION("Vacation"),
    /**
     * Business development.
     */
    BUSINESS_DEVELOPMENT("Business development");

    /**
     * Is the name of the account.
     */
    private String name;

    /**
     * Sets the name.
     *
     * @param name
     */
    NonBillableAccount(String name) {
        this.name = name;
    }

    /**
     * Returns the account name.
     *
     * @return the account name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This enum is non-billable, so it always returns false.
     *
     * @return false.
     */
    @Override
    public boolean isBillable() {
        return false;
    }

    /**
     * Creates a string of the enum.
     *
     * @return a string of the enum.
     */
    @Override
    public String toString() {
        return "NonBillableAccount{" +
                "name='" + name + '\'' +
                '}';
    }
}
