package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scp.util.PersonalName;

public class ClientAccount implements Account {


    String name;
    PersonalName personalName;

    public ClientAccount(String name, PersonalName personalName) {
        this.name = name;
        this.personalName = personalName;
    }


    public PersonalName getContact() {
        return personalName;
    }


    @Override
    public String getName() {
        return name;
    }


    //Because the enum is nonbillable, isn't this one always true???
    //Or is it actually set?
    @Override
    public boolean isBillable() {
        return true;
    }


    public void setContact(PersonalName contact) {
        this.personalName = contact;
    }

}
