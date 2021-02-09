package edu.uw.cp520.scg.util;

import edu.uw.cp520.scg.domain.Skill;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateCodeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void forName() {
        assertEquals(StateCode.WA, StateCode.WA.forName("WA"));
    }

    @Test
    void getName() {
        assertEquals("Washington", StateCode.WA.getName());
    }

    @Test
    void values() {
    }

    @Test
    void valueOf() {
    }
}