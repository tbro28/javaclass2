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
     * @return
     */
    public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant) {

        List<TimeCard> timeCardForConsultantList = timeCards.stream()
                .filter(e -> e.getConsultant().equals(consultant))
                .collect(Collectors.toList());

/*        timeCards.stream()
                .filter(e -> e.getConsultant().equals(consultant))
                .forEach(System.out::println);*/

        return timeCardForConsultantList;
    }

    /**
     * Get a list of TimeCards that cover dates that fall within a date range.
     *
     * @param timeCards
     * @param dateRange
     * @return
     */
    public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange) {

/*        for(TimeCard timeCard : timeCards) {
            System.out.println("getTimeCardsForDateRange - " + dateRange.isInRange(timeCard.getWeekStartingDay()) + " " + timeCard.toString());
            //System.out.println("getTimeCardsForDateRange - " + dateRange.isInRange(timeCard.getWeekStartingDay()) + " " + timeCard.toString());
        }*/

        List<TimeCard> timeCardDateRangeList = timeCards.stream()
                //.filter(e -> dateRange.isInRange(e.getWeekStartingDay()) || dateRange.isInRange(e.getWeekStartingDay().plusDays(7)))
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

/*        System.out.println("sortByConsultantName1: ");
        timeCards.stream()
                .sorted(Comparator.comparing(TimeCard::getConsultant))
                .collect(Collectors.toList());
              //  .forEach(System.out::println);*/

/*        System.out.println("sortByConsultantName2: ");
        timeCards.stream()
                .forEach(System.out::println);*/
    }

    /**
     * Sorts this list into ascending order, by the start date.
     * Timecard's natural ordering sorts by date first.
     *
     * @param timeCards
     */
    public static void sortByStartDate(List<TimeCard> timeCards) {
        Collections.sort(timeCards);

/*


        //Just by date, not using the C
        System.out.println("sByStartDate1:");
        timeCards.stream()
                .sorted(Comparator.comparing(TimeCard::getWeekStartingDay))
                .collect(Collectors.toList())
                .forEach(System.out::println);

System.out.println("***************************************************");

        Collections.sort(timeCards);
*/

/*
        System.out.println("sortByStartDate:");
        for(TimeCard tc : timeCards) {
            System.out.println(tc.toString());
        }
*/

    }





}
