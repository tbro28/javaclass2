package edu.uw.cp520.scg.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * Encapsulates a range of two dates, inclusive of the start date and end date.
 */
public class DateRange {

    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Construct a DateRange given two date strings in the correct format.
     *
     * @param start
     * @param end
     */
    public DateRange(String start, String end) {

        startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println(startDate.toString() + " ::: ");
        System.out.println(endDate.toString());

    }

    /**
     * Construct a DateRange given two dates.
     *
     * @param startDate
     * @param endDate
     */
    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Construct a DateRange for the given month,
     * the date range shall span the entire month,
     * from the first day of the month through the last day of the month.
     *
     * @param month
     * @param year
     */
    public DateRange(Month month, int year) {

        Month monthPlusOne = month.plus(1);

        startDate = LocalDate.of(year, month, 1);
        endDate = LocalDate.of(year, monthPlusOne, 1);
        endDate = endDate.minusDays(1);

        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println("---------------");
    }

    /**
     * Returns the start date for this DateRange.
     * @return
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date for this DateRange.
     * @return
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns true if the specified date is within
     * the range start date <= date <= end date.
     *
     * @param date
     * @return
     */
    public boolean isInRange(LocalDate date) {

        boolean isInRange = false;

        if( date.equals(startDate)
            || date.equals(endDate)
            || (date.isAfter(startDate) && date.isBefore(endDate))
        )
            isInRange = true;

        return  isInRange;
    }
}
