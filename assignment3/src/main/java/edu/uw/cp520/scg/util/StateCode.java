package edu.uw.cp520.scg.util;

public enum StateCode {

    CA("California"),
    WA("Washington");

    private String stateName;

    StateCode(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Returns the state name.
     *
     * @param stateName
     * @return the state name.
     */
    StateCode forName(String stateName) {
        return StateCode.valueOf(stateName);
    }

    /**
     *
     *
     * @return
     */
    public String getName() {
        return stateName;
    }
}
