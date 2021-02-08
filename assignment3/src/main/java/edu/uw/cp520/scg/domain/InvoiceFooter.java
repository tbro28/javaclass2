package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

public class InvoiceFooter {

    String businessName;
    int pageNumber = 1;

    /**
     *
     * Constructor for an InvoiceHeader.
     *
     * @param businessName
     */
    public InvoiceFooter(String businessName) {
        this.businessName = businessName;
    }


    public void incrementPageNumber() {
        pageNumber++;
    }

    @Override
    public String toString() {


        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        //Can only be 41 characters long before overlapping
        // with the week date string.
/*        String consultantName = consultant.getName().toString().substring(0,
                Math.min(consultant.getName().toString().length(), 41));
*/
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
