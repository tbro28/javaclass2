package edu.uw.cp520.scg.util;

public enum StateCode {

    CA("California"),
    WA("Washington");

    private String stateName;

    StateCode(String stateName) {
        this.stateName = stateName;
    }

    public String getName() {
        return stateName;
    }
}
