package edu.uw.cp520.scg.util;

import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for processing TimeCard lists.
 */
public class TimeCardListUtil {

    /**
     * Get a list of TimeCards for the specified consultant.
     *
     * @param timeCards
     * @param consultant
     * @return the time cards for the supplied consultant.
     */
    public static List<TimeCard> getTimeCardsForConsultant(
            List<TimeCard> timeCards, Consultant consultant) {

        List<TimeCard> timeCardForConsultantList = timeCards.stream()
                .filter(e -> e.getConsultant().equals(consultant))
                .collect(Collectors.toList());

        return timeCardForConsultantList;
    }

    /**
     * Get a list of TimeCards that cover dates that fall within a date range.
     *
     * @param timeCards
     * @param dateRange
     * @return the time cards for the supplied date range.
     */
    public static List<TimeCard> getTimeCardsForDateRange(
            List<TimeCard> timeCards, DateRange dateRange) {

        List<TimeCard> timeCardDateRangeList = timeCards.stream()
                .filter(e -> dateRange.isInRange(e.getWeekStartingDay()))
                .collect(Collectors.toList());

        return timeCardDateRangeList;
    }

    /**
     * Sorts this list into ascending order by consultant name.
     * The TimeCardConsultantComparator orders by consultant first.
     *
     * @param timeCards
     */
    public static void sortByConsultantName(List<TimeCard> timeCards) {
        Collections.sort(timeCards, new TimeCardConsultantComparator());
    }

    /**
     * Sorts this list into ascending order, by the start date.
     * Timecard's natural ordering sorts by date first.
     *
     * @param timeCards
     */
    public static void sortByStartDate(List<TimeCard> timeCards) {
        Collections.sort(timeCards);
    }

}
