package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ClientAccountTest {

    Address address = new Address( "streetNumber", "city", StateCode.WA,"postalCode");
    Address address2 = new Address( "streetNumberDiff", "city", StateCode.WA,"postalCode");

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

    @Test
    public void EqualCompareToTest() {
        String name = "TimBiz";

        PersonalName personalName1 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount1 = new ClientAccount(name, personalName1, address);

        PersonalName personalName2 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount2 = new ClientAccount(name, personalName1, address);

        assertEquals(0, clientAccount1.compareTo(clientAccount2));
    }

    @Test
    public void NotEqualNameCompareToTest() {
        String name = "TimBiz";
        String name2 = "TimBizDiff";

        PersonalName personalName1 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount1 = new ClientAccount(name, personalName1, address);

        PersonalName personalName2 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount2 = new ClientAccount(name2, personalName1, address);

        assertNotEquals(0, clientAccount1.compareTo(clientAccount2));
    }

    @Test
    public void NotEqualContactCompareToTest() {
        String name = "TimBiz";

        PersonalName personalName1 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount1 = new ClientAccount(name, personalName1, address);

        PersonalName personalName2 = new PersonalName("BrownDiff", "Tom", "Jack");
        ClientAccount clientAccount2 = new ClientAccount(name, personalName2, address);

        assertNotEquals(0, clientAccount1.compareTo(clientAccount2));
    }

    @Test
    public void NotEqualAddressCompareToTest() {
        String name = "TimBiz";

        PersonalName personalName1 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount1 = new ClientAccount(name, personalName1, address);

        PersonalName personalName2 = new PersonalName("Brown", "Tom", "Jack");
        ClientAccount clientAccount2 = new ClientAccount(name, personalName1, address2);

        assertNotEquals(0, clientAccount1.compareTo(clientAccount2));
    }
}