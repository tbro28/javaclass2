package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.PersonalName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Consultant class.
 *
 * @author Tim Brown
 */
public class ConsultantTest {

    Consultant consultant;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createConsultant() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        consultant = new Consultant(personalName);

        assertNotNull(consultant);
    }

    @Test
    void getNameOfConsultant() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        consultant = new Consultant(personalName);

        assertEquals(personalName, consultant.getName());
    }

    @Test
    void getStringOfConsultant() {
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        consultant = new Consultant(personalName);

        assertEquals("Consultant{name=Consultant: Brown, Tom, Jack}",
                consultant.toString());
    }
}
