package edu.uw.cp520.scg.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}