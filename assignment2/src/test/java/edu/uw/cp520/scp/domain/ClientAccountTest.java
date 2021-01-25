package edu.uw.cp520.scp.domain;

import edu.uw.cp520.scp.util.PersonalName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientAccountTest {


    @Test
    public void getContactTest() {

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName);

        assertEquals(personalName, clientAccount.getContact());
    }

    @Test
    public void personalNameTest() {

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName);

        assertEquals(name, clientAccount.getName());
    }

    @Test
    public void isBillableTrueTest() {

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName);

        assertEquals(true, clientAccount.isBillable());
    }


    @Test
    public void setContactTest() {

        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName);

        PersonalName personalName2 = new PersonalName("Anderson", "Neo", "Matrix");
        clientAccount.setContact(personalName2);

        assertEquals(personalName2, clientAccount.getContact());
    }

}