package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.util.PersonalName;

import edu.uw.cp520.scg.beans.BenefitEvent;
import edu.uw.cp520.scg.beans.BenefitListener;
import edu.uw.cp520.scg.beans.BenefitManager;
import edu.uw.cp520.scg.beans.CompensationManager;
import edu.uw.cp520.scg.beans.Eeoc;
import edu.uw.cp520.scg.beans.HumanResourceManager;
import edu.uw.cp520.scg.beans.StaffConsultant;

/**
 * JUnit test for the change events and listeners.
 *
 * @author Russ Moul
 */
public final class Assignment06Test {
    /** Initial pay rate for coder. */
    private static final int CODER_INITIAL_PAY_RATE = 9524;

    /** Initial pay rate for architect. */
    private static final int ARCHITECT_INITIAL_PAY_RATE = 10000;

    /** Initial pay rate for tester. */
    private static final int TESTER_INITIAL_PAY_RATE = 5000;

    /** Initial pay rate for engineer. */
    private static final int ENGINEER_INITIAL_PAY_RATE = 7500;

    /** Initial value assigned to sick leave hours for all consultants. */
    private static final int INITIAL_SICK_LEAVE_HOURS = 80;

    /** Test value for sick leave hours update. */
    private static final int TEST_SICK_LEAVE_HOURS = 320;

    /** Initial value assigned to vacation hours for all consultants. */
    private static final int INITIAL_VACATION_HOURS = 40;

    /** Test value for vacation hours update. */
    private static final int TEST_VACATION_HOURS = 240;

    /** Test value for valid pay rate adjustment. */
    private static final int VALID_RAISE = 10000;

    /** Test value for invalid pay rate adjustment. */
    private static final int INVALID_RAISE = 10501;

    /** Property name for sickLeaveHours. */
    private static final String SICK_LEAVE_HOURS_PROP = "sickLeaveHours";

    /** Property name for vacationHours. */
    private static final String VACATION_HOURS_PROP = "vacationHours";

    /** Property name for payRate. */
    private static final String PAY_RATE_PROP = "payRate";

    /** Consultant enrolling in medical. */
    private static final int MEDICAL_ENROLLEE = 0;

    /** Consultant canceling medical. */
    private static final int MEDICAL_CANCEL = 1;

    /** Consultant enrolling in dental. */
    private static final int DENTAL_ENROLLEE = 2;

    /** Consultant canceling dental. */
    private static final int DENTAL_CANCEL = 3;

    /** Consultants for testing. */
    private List<StaffConsultant> consultantList;

    /** A specific consultant for testing. */
    private StaffConsultant staffConsultant;

    /** HR object for testing. */
    private HumanResourceManager hrServer;

    /** EEOC object for testing. */
    private Eeoc watchDog;

    /** Property listener for testing. */
    private TestPropertyListener testPropertyListener;
    
    /** Benefit listener for testing. */
    private TestBenefitListener testBenefitListener;


    /** Property listener for testing purposes, keeps track of the last event. */
    private static class TestPropertyListener implements PropertyChangeListener, VetoableChangeListener {
        /** The last event. */
        private PropertyChangeEvent lastEvent;

        /**
         * Gets the last event and clears it.
         *
         * @return the last event, null if an event has not arrived since last called.
         */
        public PropertyChangeEvent lastEvent() {
            final PropertyChangeEvent tmp = lastEvent;
            lastEvent = null;
            return tmp;
        }

        /**
         * Simply records the event.
         *
         * @param event the change event
         */
        @Override
        public void propertyChange(final PropertyChangeEvent event) {
            this.lastEvent = event;
        }

        /**
         * Simply records the event.
         *
         * @param event the change event
         */
        @Override
        public void vetoableChange(final PropertyChangeEvent event) {
            this.lastEvent = event;
        }
    }

    /** Benefit listener for testing purposes, keeps track of benefit enrollments. */
    private static class TestBenefitListener implements BenefitListener {
    	Consultant medicalEnrollConsultant = null;
    	Consultant medicalCancelConsultant = null;
    	Consultant dentalEnrollConsultant = null;
    	Consultant dentalCancelConsultant = null;
 
		@Override
		public void medicalEnrollment(BenefitEvent evnt) {
			medicalEnrollConsultant = evnt.getConsultant();
		}

		@Override
		public void medicalCancellation(BenefitEvent evnt) {
			medicalCancelConsultant = evnt.getConsultant();
		}

		@Override
		public void dentalEnrollment(BenefitEvent evnt) {
			dentalEnrollConsultant = evnt.getConsultant();
		}

		@Override
		public void dentalCancellation(BenefitEvent evnt) {
			dentalCancelConsultant = evnt.getConsultant();
		}
    }

    /**
     * Initialize all the objects used for testing.
     */
    @BeforeEach
    public void setUp() {
        // Create some Consultants
        consultantList = new ArrayList<StaffConsultant>();
        staffConsultant = new StaffConsultant(
                          new PersonalName("Coder", "Kalvin"), CODER_INITIAL_PAY_RATE,
                          INITIAL_SICK_LEAVE_HOURS, INITIAL_VACATION_HOURS);
        consultantList.add(staffConsultant);
        consultantList.add(new StaffConsultant(
                           new PersonalName("Architect", "Amber", "K."), ARCHITECT_INITIAL_PAY_RATE,
                           INITIAL_SICK_LEAVE_HOURS, INITIAL_VACATION_HOURS));
        consultantList.add(new StaffConsultant(
                           new PersonalName("Tester", "Teddy", "B."), TESTER_INITIAL_PAY_RATE,
                           INITIAL_SICK_LEAVE_HOURS, INITIAL_VACATION_HOURS));
        consultantList.add(new StaffConsultant(
                           new PersonalName("Engineer", "Ernie"), ENGINEER_INITIAL_PAY_RATE,
                           INITIAL_SICK_LEAVE_HOURS, INITIAL_VACATION_HOURS));

        // create the server
        hrServer = new HumanResourceManager();
        watchDog = new Eeoc();
        hrServer.addTerminationListener(watchDog);

        final CompensationManager compMgr = new CompensationManager();
        final BenefitManager bm = new BenefitManager();
        hrServer.addBenefitListener(bm);
        testBenefitListener = new TestBenefitListener();
        hrServer.addBenefitListener(testBenefitListener);
        testPropertyListener = new TestPropertyListener();
        for (StaffConsultant sc : consultantList) {
            sc.addVetoableChangeListener(compMgr);
            sc.addPayRateListener(compMgr);
            sc.addSickLeaveHoursListener(bm);
            sc.addVacationHoursListener(bm);
            sc.addPropertyChangeListener(testPropertyListener);
        }
    }

    /** Test the vetoable property (payRate) */
    @Test
    public void testVeto() {
        hrServer.adjustPayRate(staffConsultant, VALID_RAISE);
        assertEquals(VALID_RAISE, staffConsultant.getPayRate());
        PropertyChangeEvent event = testPropertyListener.lastEvent();
        assertEquals(PAY_RATE_PROP, event.getPropertyName());
        assertEquals(VALID_RAISE, event.getNewValue());
        assertEquals(staffConsultant, event.getSource());

        // this should be vetoed, so the pay rate won't be set
        hrServer.adjustPayRate(staffConsultant, INVALID_RAISE);
        assertEquals(VALID_RAISE, staffConsultant.getPayRate());
        event = testPropertyListener.lastEvent();
        assertNull(event);
    }

    /** Test the termination events */
    @Test
    public void testTerminations() {
        assertEquals(0, watchDog.voluntaryTerminationCount());
        assertEquals(0, watchDog.forcedTerminationCount());

        // Terminate two employees
        final Iterator<StaffConsultant> iter = consultantList.iterator();
        if (iter.hasNext()) {
            final StaffConsultant consultant = iter.next();
            hrServer.acceptResignation(consultant);
        }

        assertEquals(1, watchDog.voluntaryTerminationCount());
        if (iter.hasNext()) {
            final StaffConsultant consultant = iter.next();
            hrServer.terminate(consultant);
        }
        assertEquals(1, watchDog.forcedTerminationCount());

    }

    /** Test the simple properties (sickLeaveHours and vacationHours) */
    @Test
    public void testBenefits() {
        hrServer.adjustSickLeaveHours(staffConsultant, TEST_SICK_LEAVE_HOURS);
        PropertyChangeEvent event = testPropertyListener.lastEvent();
        assertEquals(SICK_LEAVE_HOURS_PROP, event.getPropertyName());
        assertEquals(TEST_SICK_LEAVE_HOURS, event.getNewValue());
        assertEquals(staffConsultant, event.getSource());

        hrServer.adjustVacationHours(staffConsultant, TEST_VACATION_HOURS);
        event = testPropertyListener.lastEvent();
        assertEquals(VACATION_HOURS_PROP, event.getPropertyName());
        assertEquals(TEST_VACATION_HOURS, event.getNewValue());
        assertEquals(staffConsultant, event.getSource());

        hrServer.enrollMedical(consultantList.get(MEDICAL_ENROLLEE));
        hrServer.enrollDental(consultantList.get(DENTAL_ENROLLEE));
        hrServer.cancelMedical(consultantList.get(MEDICAL_CANCEL));
        hrServer.cancelDental(consultantList.get(DENTAL_CANCEL));
        assertEquals(consultantList.get(MEDICAL_ENROLLEE),testBenefitListener.medicalEnrollConsultant);
        assertEquals(consultantList.get(DENTAL_ENROLLEE),testBenefitListener.dentalEnrollConsultant);
        assertEquals(consultantList.get(MEDICAL_CANCEL),testBenefitListener.medicalCancelConsultant);
        assertEquals(consultantList.get(DENTAL_CANCEL),testBenefitListener.dentalCancelConsultant);
    }
}
