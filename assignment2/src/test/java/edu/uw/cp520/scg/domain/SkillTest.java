package edu.uw.cp520.scg.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Skill class.
 *
 * @author Tim Brown
 */
class SkillTest {

    @Test
    void getRate() {
        assertEquals(75, Skill.PROJECT_MANAGER.getRate());
    }

    @Test
    void testToString() {
        assertEquals("Skill{rate=50}", Skill.SOFTWARE_ENGINEER.toString());
    }
}