package edu.uw.cp520.scp.domain;

/**
 *
 * Accounts which can not be billed - non-billable accounts,
 * such as sick leave, vacation, or business development.
 *
 * @author Tim Brown
 */
public enum NonBillableAccount implements Account {
    SICK_LEAVE("Sick Leave"),
    VACATION("Vacation"),
    BUSINESS_DEVELOPMENT("Business development");


    private String name;

    NonBillableAccount(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isBillable() {
        return false;
    }

    @Override
    public String toString() {
        return "NonBillableAccount{" +
                "name='" + name + '\'' +
                '}';
    }
}
