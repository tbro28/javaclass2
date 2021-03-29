package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.util.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

/**
 *
 * The InvoiceHeader class.
 * Primarily used for printing invoices.
 *
 * @author Tim Brown
 */
public class InvoiceHeader {

    /**Name of the business.*/
    String businessName;
    /**Holds the business address.*/
    Address businessAddress;
    /**Set to the client account.*/
    ClientAccount client;
    /**Contains the invoice date.*/
    LocalDate invoiceDate;
    /**Contains the invoice month.*/
    LocalDate invoiceForMonth;

    /**
     *
     * Constructor for an InvoiceHeader.
     *
     * @param businessName
     * @param businessAddress
     * @param client
     * @param invoiceDate
     * @param invoiceForMonth
     */
    public InvoiceHeader(String businessName, Address businessAddress,
                         ClientAccount client, LocalDate invoiceDate,
                         LocalDate invoiceForMonth) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.client = client;
        this.invoiceDate = invoiceDate;
        this.invoiceForMonth = invoiceForMonth;
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

        formatter.format(businessName+"\n")
                .format(businessAddress.getStreetNumber()+"\n")
                .format(businessAddress.getCity()+", ")
                .format(businessAddress.getState()+ " ")
                .format(businessAddress.getPostalCode()+"\n\n")

                .format("Invoice for: \n")
                .format(client.getName()+"\n")
                .format(client.address.getStreetNumber()+"\n")
                .format(client.address.getCity()+", ")
                .format(client.address.getState()+" ")
                .format(client.address.getPostalCode()+"\n")
                .format(client.personalName.toString().substring(12)+"\n\n")

                .format("Invoice For Month Of: ")
                .format(invoiceForMonth.getMonth().name().substring(0,1)
                        +invoiceForMonth.getMonth().name()
                        .substring(1).toLowerCase(Locale.ROOT)+" ")
                .format(Integer.toString(invoiceForMonth.getYear())+"\n")

                .format("Invoice Date: ")
                .format(invoiceDate.format(dateFormatter)+"\n\n");

        String strReport = formatter.toString();

        return strReport;



    }
}
