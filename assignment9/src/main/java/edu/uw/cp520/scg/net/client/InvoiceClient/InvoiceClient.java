package edu.uw.cp520.scg.net.client.InvoiceClient;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.net.cmd.*;
import edu.uw.cp520.scg.net.server.CommandProcessor;
import edu.uw.cp520.scg.util.Address;
import edu.uw.cp520.scg.util.PersonalName;
import edu.uw.cp520.scg.util.StateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * The client of the InvoiceServer. Connects to the server, sends commands
 * to add clients, consultants and time cards and then has
 * the server create an invoice.
 */
public class InvoiceClient implements Runnable {

    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InvoiceClient.class);


    String host;
    int port;
    List<TimeCard> timeCardList;


    /**
     * Construct an InvoiceClient with a host and port for the server.
     *
     * @param host
     * @param port
     * @param timeCardList
     */
    public InvoiceClient(String host, int port, List<TimeCard> timeCardList) {
        this.host = host;
        this.port = port;
        this.timeCardList = timeCardList;
    }


    /**
     * Runs this InvoiceClient, sending clients, consultants, and time cards to the server,
     * then sending the command to create invoices for a specified month.
     *
     */
    @Override
    public void run() {
        ObjectOutputStream out = null;

        try (
                Socket server = new Socket(host, port);
                ) {
            //inetAddress = InetAddress.getLocalHost();
            //socket = new Socket(inetAddress, 10888);
            System.out.println("InvoiceClient: " + server.getInetAddress());
            server.shutdownInput();
            out = new ObjectOutputStream(server.getOutputStream());
            sendClients(out);
            sendConsultants(out);
            out.writeObject("NOT_A_COMMAND");

            sendTimeCards(out);
            createInvoices(out, Month.of(2), 2017);
            sendDisconnect(out, server);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Send some new clients to the server.
     *
     * @param out
     */
    public void sendClients(ObjectOutputStream out) {

        AddClientCommand command = null;

        String bizName1 = "TimBiz";
        String bizName2 = "TimB2";
        PersonalName personalName = new PersonalName("Brown", "Tom", "Jack");
        PersonalName personalName2 = new PersonalName("ZBro2", "Tim", "Middle");

        Address address = new Address("TimsStreetNumber", "TimsCity", StateCode.WA, "TimsPostalCode");

        //Serialize
        ClientAccount clientAccount = new ClientAccount(bizName1, personalName, address);
        ClientAccount clientAccount2 = new ClientAccount(bizName2, personalName2, address);

        command = new AddClientCommand(clientAccount);
        sendCommand(out, command);
        command = new AddClientCommand(clientAccount2);
        sendCommand(out, command);
    }


    /**
     * Send some new consultants to the server.
     *
     * @param out
     */
    public void sendConsultants(ObjectOutputStream out) {
        AddConsultantCommand command = null;

        command = new AddConsultantCommand(new Consultant(
                new PersonalName("Jones", "FooBar", "Q")));
        sendCommand(out, command);

        command = new AddConsultantCommand(new Consultant(
                new PersonalName("Bug", "Don", "Q")));
        sendCommand(out, command);
    }


    /**
     * Send the time cards to the server.
     *
     * @param out
     */
    public void sendTimeCards(ObjectOutputStream out) {
        AddTimeCardCommand command = null;

        for(final TimeCard timeCard : timeCardList) {
            command = new AddTimeCardCommand(timeCard);
            sendCommand(out, command);
        }
    }


    /**
     * Send the disconnect command to the server.
     *
     * @param out
     * @param server
     */
    public void sendDisconnect(ObjectOutputStream out, Socket server) {
        final DisconnectCommand command = new DisconnectCommand();
        sendCommand(out, command);
        try {
            server.close();
        }
        catch (IOException e) {
            log.warn("Error closing socket.", e);
        }
    }


    /**
     * Send the command to the server to create invoices for the specified month.
     *
     * @param out
     * @param month
     * @param year
     */
    public void createInvoices(ObjectOutputStream out, Month month, int year) {
        LocalDate date = LocalDate.of(year, month, 1);
        CreateInvoicesCommand command = new CreateInvoicesCommand(date);
        sendCommand(out, command);
    }


    /**
     * Send the shutdown command to the server, this is done on a separate connection to the server.
     *
     * @param host
     * @param port
     */
    public static void	sendShutdown(String host, int port) {
        try(Socket server = new Socket(host, port);) {
            System.out.println("Shutdown:" + server.getInetAddress().getHostName());
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
            server.shutdownInput();
            ShutdownCommand command = new ShutdownCommand();
            sendCommand(out, command);
            server.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends the command to the server.
     *
     * @param out
     * @param command
     */
    private static void sendCommand(ObjectOutputStream out, Command<?> command) {
        try {
            out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            log.error("Unable to write command.", e);
        }
    }

}
