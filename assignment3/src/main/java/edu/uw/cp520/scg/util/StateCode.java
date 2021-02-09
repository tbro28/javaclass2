package edu.uw.cp520.scg.util;

public enum StateCode {

    CA("California"),
    WA("Washington");

    private String stateName;

    StateCode(String stateName) {
        this.stateName = stateName;
    }

    StateCode forName(String stateName) {
        return StateCode.valueOf(stateName);
    }

    public String getName() {
        return stateName;
    }
}
