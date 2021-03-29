package edu.uw.cp520.scg.util;

import edu.uw.cp520.scg.domain.TimeCard;

import java.util.Comparator;

/**
 * Compares two TimeCard objects ascending order by (in precedence order)
 * consultant, time card starting date, total billable hours
 * and non-billable hours.
 */
public class TimeCardConsultantComparator implements Comparator<TimeCard> {

    /**
     * Compare time cards based on
     * the consultant,
     * the beginning date,
     * total billable hours
     * and lastly total non-billable hours
     *
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p>
     * public int compare(TimeCard firstTimeCard, TimeCard secondTimeCard)
     * Compares its two arguments, in ascending order
     * by (in precedence order) consultant,
     * the starting date, total billable hours
     * and lastly total non-billable hours
     *
     * Specified by:
     * compare in interface Comparator<TimeCard>
     *
     * Parameters:
     * firstTimeCard - the first object to be compared.
     * secondTimeCard - the second object to be compared.
     *
     * Returns:
     * a negative integer, zero, or a positive integer
     * as the first argument is less than,
     * equal to, or greater than the second.
     *
     * @param firstTimeCard the first object to be compared.
     * @param secondTimeCard the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(TimeCard firstTimeCard, TimeCard secondTimeCard) {
        int diff = 0;

        diff = firstTimeCard.getConsultant()
                .compareTo(secondTimeCard.getConsultant());
        if ( diff != 0) {
            return diff;
        }
        diff = firstTimeCard.getWeekStartingDay()
                .compareTo(secondTimeCard.getWeekStartingDay());
        if ( diff != 0) {
            return diff;
        }
        diff = Integer.compare(firstTimeCard.getTotalBillableHours(),
                secondTimeCard.getTotalBillableHours());
        if ( diff != 0) {
            return diff;
        }
        diff = Integer.compare(firstTimeCard.getTotalNonBillableHours(),
                secondTimeCard.getTotalNonBillableHours());
        return diff;
    }

}
