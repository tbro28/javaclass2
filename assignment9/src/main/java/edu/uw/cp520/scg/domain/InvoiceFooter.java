package edu.uw.cp520.scg.domain;

import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

/**
 *
 * The InvoiceFooter class.
 * Primarily used for printing invoices.
 *
 * @author Tim Brown
 */
public class InvoiceFooter {

    /**Business name needed for the footer information.*/
    String businessName;

    /**Page number set to 1.*/
    int pageNumber = 1;

    /**
     *
     * Constructor for an InvoiceFooter.
     *
     * @param businessName
     */
    public InvoiceFooter(String businessName) {
        this.businessName = businessName;
    }


    /**
     * Increment the page number for the invoice.
     */
    public void incrementPageNumber() {
        pageNumber++;
    }

    /**
     *
     * Creates and returns the footer for printing.
     *
     * @return a string of the footer oject.
     */
    @Override
    public String toString() {

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        //https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
        StringBuilder sb = new StringBuilder();

        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        formatter.format("%-68s", businessName)
                .format("Page:")
                .format("%6s", pageNumber+"\n");

        formatter.format("=".repeat(78)+"\n\n");

        String strReport = formatter.toString();

        return strReport;
    }
}
