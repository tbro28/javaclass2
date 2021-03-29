package edu.uw.cp520.scg.util;

import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.TimeCardConsultantComparator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for the TimeCardConsultantComparator class.
 *
 * @author Russ Moul
 */
public final class TimeCardConsultantComparatorTest {
    /** String literal for "Coder". */
    private static final String CODER = "Coder";
    /** String literal for "Jane". */
    private static final String JANE = "Jane";
    /** String literal for "John". */
    private static final String JOHN = "John";
    /** String literal for "Q.". */
    private static final String Q_DOT = "Q.";

    /**
     * Tests the compare method.
     */
    @Test
    public void testCompare() {
        final TimeCardConsultantComparator timeCardConsultantComparator = new TimeCardConsultantComparator();
        final LocalDate date = LocalDate.now();

        final TimeCard firstTimeCard = new TimeCard(new Consultant(new PersonalName(CODER,
                JANE, Q_DOT)), date);
        TimeCard secondTimeCard = new TimeCard(new Consultant(new PersonalName(CODER,
                JANE, Q_DOT)), date);

        assertTrue(timeCardConsultantComparator.compare(firstTimeCard, secondTimeCard) == 0);

        secondTimeCard = new TimeCard(new Consultant(new PersonalName(CODER, JOHN,
                Q_DOT)), date);
        assertTrue(timeCardConsultantComparator.compare(firstTimeCard, secondTimeCard) < 0);
        assertTrue(timeCardConsultantComparator.compare(secondTimeCard, firstTimeCard) > 0);
    }
}
