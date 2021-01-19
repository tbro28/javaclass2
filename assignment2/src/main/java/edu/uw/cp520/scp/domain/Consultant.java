package edu.uw.cp520.scp.domain;

public class Consultant {

    Consultant name;

    public Consultant(Consultant name) {
        this.name = name;
    }

    public Consultant getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Consultant{" +
                "name=" + name +
                '}';
    }
}
