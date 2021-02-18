package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientAccountTest {

    Address address = new Address( "streetNumber", "city", StateCode.WA,"postalCode");

    @Test
    public void getContactTest() {
        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        assertEquals(personalName, clientAccount.getContact());
    }

    @Test
    public void personalNameTest() {
        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        assertEquals(name, clientAccount.getName());
    }

    @Test
    public void isBillableTrueTest() {
        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        assertEquals(true, clientAccount.isBillable());
    }

    @Test
    public void setContactTest() {
        String name = "TimBiz";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");

        ClientAccount clientAccount = new ClientAccount(name, personalName, address);

        PersonalName personalName2 = new PersonalName("Anderson", "Neo", "Matrix");
        clientAccount.setContact(personalName2);

        assertEquals(personalName2, clientAccount.getContact());
    }
}