package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scp.util.PersonalName;

public class Consultant {

    PersonalName name;

    public Consultant(PersonalName name) {
        this.name = name;
    }

    public PersonalName getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "name=" + name +
                '}';
    }




}
