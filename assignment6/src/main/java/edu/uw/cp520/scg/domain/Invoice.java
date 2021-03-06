package edu.uw.cp520.scg.domain;

import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.StateCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 *
 * Invoice encapsulates the attributes and behavior to create client
 * invoices for a given time period from time cards. The Invoice maintains
 * are collection of invoice line items; each containing date, hours and
 * other billing information, these constitute what is being billed for
 * with this Invoice. The invoice will limit the items billed on it to
 * a single month and also has a separate invoice date which reflects
 * the date the invoice was generated. The invoicing business' name and
 * address are obtained from a properties file. The name of the property
 * file is specified by the PROP_FILE_NAME static member.
 *
 * @author Tim Brown
 */
public class Invoice {

    /** Client for this Invoice. */
    private ClientAccount clientAccount;
    /** Month for which this Invoice is being created. */
    private Month month;
    /** Year for which this Invoice is being created. */
    private int invoiceYear;
    /** Invoice of line items for the account. */
    private List<InvoiceLineItem> invoiceLineItemList = new ArrayList<>();

    /**Business address from properties file.*/
    private static Address BIZ_ADDRESS;
    /**Business name from properties file.*/
    private static String BIZ_NAME;

    /**
     *
     * Construct an Invoice for a client. The time period is set from
     * the beginning to the end of the month specified.
     *
     * @param clientAccount
     * @param month
     * @param invoiceYear
     */
    public Invoice(ClientAccount clientAccount, Month month, int invoiceYear) {
        this.clientAccount = clientAccount;
        this.month = month;
        this.invoiceYear = invoiceYear;
    }

    /**
     * Static initializer instead of using a method.
     */
    static {
        try (InputStream input = new FileInputStream(
                "src/main/resources/invoice.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            BIZ_NAME = prop.getProperty("business.name");
            BIZ_ADDRESS = new Address(prop.getProperty("business.street"),
                    prop.getProperty("business.city"),
                    StateCode.valueOf(prop.getProperty("business.state")),
                    prop.getProperty("business.zip"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * Add an invoice line item to this Invoice.
     *
     * @param invoiceLineItem
     */
    public void addLineItem( InvoiceLineItem invoiceLineItem ) {
        invoiceLineItemList.add(invoiceLineItem);
    }

    /**
     *
     * Extract the billable hours for this Invoice's client
     * from the input TimeCard and add them to the collection of line items.
     *
     * @param timeCard
     */
    public void extractLineItems(TimeCard timeCard) {

        List<ConsultantTime> billableHours = timeCard
                .getBillableHoursForClient(clientAccount.getName());

        billableHours.stream()
                .filter(e -> invoiceYear == e.getDate().getYear()
                        && month == e.getDate().getMonth())
                .forEach(e -> this.addLineItem(
                        new InvoiceLineItem(e.getDate(),
                                timeCard.getConsultant(),
                                e.getSkill(),
                                e.getHours()
                        )
                ));

        /*
        for (ConsultantTime consultantTime : billableHours) {
            if(invoiceYear == consultantTime.getDate().getYear()
                    && month == consultantTime.getDate().getMonth()) {
                if (consultantTime.isBillable()) {
                    this.addLineItem(
                            new InvoiceLineItem(consultantTime.getDate(),
                            timeCard.consultant,
                            consultantTime.getSkill(),
                            consultantTime.getHours()));
                }
            }
        }
        */
    }

    /**
     *
     * Get the client for this Invoice.
     *
     * @return the client account.
     */
    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    /**
     *
     * Get the invoice month.
     *
     * @return the invoice Month object.
     */
    public Month getInvoiceMonth() {
        return month;
    }

    /**
     *
     * Get the start date for this Invoice, this is the earliest date a
     * ConsultantTime instance may have and still be billed on this invoice.
     *
     * @return the start date.
     */
    public LocalDate getStartDate() {
        return LocalDate.of(invoiceYear, month.getValue(), 1);
    }

    /**
     *
     * Get the total charges for this Invoice.
     *
     * @return the total charges on the invoice.
     */
    public int getTotalCharges() {

        int charges = 0;

        for(InvoiceLineItem invoiceLineItem : invoiceLineItemList) {
            charges += invoiceLineItem.getCharge();
        }

        return charges;
    }

    /**
     *
     * Get the total hours for this Invoice.
     *
     * @return the total hours for a given invoice.
     */
    public int getTotalHours() {

        int hours = 0;

        for(InvoiceLineItem invoiceLineItem : invoiceLineItemList) {
            hours += invoiceLineItem.getHours();
        }

        return hours;
    }

    /**
     *
     * Create a formatted string containing the printable invoice.
     *
     * @return a printable invoice String.
     */
    public String toReportString() {

        LocalDate invoiceDate = LocalDate.now();

        int numericalMonth = month.getValue();

        LocalDate invoiceMonthYear =
                LocalDate.of(invoiceYear, numericalMonth, 1);

        InvoiceHeader invoiceHeader =
                new InvoiceHeader(BIZ_NAME, BIZ_ADDRESS,
                        clientAccount, invoiceDate, invoiceMonthYear);

        InvoiceFooter invoiceFooter = new InvoiceFooter(BIZ_NAME);

        StringBuilder sb = new StringBuilder();

        // Send all output to the Appendable object sb
        Formatter formatter = new Formatter(sb, Locale.US);

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");

        int invoiceLineItemCount =  invoiceLineItemList.size();
        int currentInvoiceLineItem = 0;
        int numberOfPages = (int) Math.ceil(invoiceLineItemCount / 5.0);

        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols =
                ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);

        for(int page = 1; page <= numberOfPages; page++) {

            formatter.format(invoiceHeader.toString())

                    .format("%-12s", "Date")
                    .format("%-29s", "Consultant")
                    .format("%-20s", "Skill")
                    .format("%-7s", "Hours")
                    .format("%-10s", "Charge")
                    .format("\n")

                    .format("-".repeat(10)+"  ")
                    .format("-".repeat(27)+"  ")
                    .format("-".repeat(18)+"  ")
                    .format("-".repeat(5)+"  ")
                    .format("-".repeat(10))
                    .format("\n");

            for(int itemNumber = 0; itemNumber < 5; itemNumber++) {

                if(currentInvoiceLineItem == invoiceLineItemCount)
                    break;

                formatter.format("%-12s", invoiceLineItemList
                        .get(currentInvoiceLineItem).getDate()
                        .format(dateFormatter));
                formatter.format("%-29s", invoiceLineItemList
                        .get(currentInvoiceLineItem).getConsultant()
                        .getName().toString().substring(12));
                formatter.format("%-20s", invoiceLineItemList
                        .get(currentInvoiceLineItem).getSkill());
                formatter.format("%5s", invoiceLineItemList
                        .get(currentInvoiceLineItem).getHours());
                formatter.format("%12s", nf.format(invoiceLineItemList
                        .get(currentInvoiceLineItem).getCharge()).trim());
                formatter.format("\n");
                currentInvoiceLineItem++;

            }

            if(page == numberOfPages) {
                formatter.format("\n")
                    .format("%-61s", "Total:")
                    .format("%5s", this.getTotalHours())
                    .format("%12s", nf.format(this.getTotalCharges()).trim())
                    .format("\n");
            }
            else
                formatter.format("\n");

            formatter.format("\n\n");

            //Format footer
            formatter.format(invoiceFooter.toString());
            invoiceFooter.incrementPageNumber();
        }

        String strReport = formatter.toString();

        return strReport;
    }

    /**
     *
     * Create a string representation of this object, suitable for printing.
     *
     * @return a string representation of the Invoice object.
     */
    @Override
    public String toString() {

        String invoiceString = "";

        for(InvoiceLineItem invoiceLineItem : invoiceLineItemList) {
            invoiceString += invoiceLineItem.getDate()+":"
                    +invoiceLineItem.getSkill()+":"
                    +invoiceLineItem.getHours()
                    +":"+invoiceLineItem.getConsultant()+"\n";
        }

        return "Invoice:\n" +
                "clientAccount=" + clientAccount.getName() +
                ", \nmonth=" + month +
                ", \ninvoiceYear=" + invoiceYear +
                ", \ninvoiceLineItemList=\n" + invoiceString;
    }

}
