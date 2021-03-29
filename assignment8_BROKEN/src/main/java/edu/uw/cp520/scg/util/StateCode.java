package edu.uw.cp520.scg.util;

/**
 *
 * Encapsulates the state code information.
 *
 * @author Tim Brown
 */
public enum StateCode {

    /**Enum for CA.*/
    CA("California"),
    /**Enum for WA.*/
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
        return valueOf(stateName);
    }

    /**
     * The name of the state.
     *
     * @return the state name.
     */
    public String getName() {
        return stateName;
    }
}
