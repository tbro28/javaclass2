package edu.uw.cp520.scp.domain;

public enum NonBillableAccount implements Account {
    ;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isBillable() {
        return false;
    }
}
