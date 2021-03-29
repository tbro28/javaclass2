package edu.uw.cp520.scg.net.client.InvoiceClient;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.net.cmd.AddClientCommand;
import edu.uw.cp520.scg.net.server.CommandProcessor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.Month;
import java.util.List;

/**
 * The client of the InvoiceServer. Connects to the server, sends commands
 * to add clients, consultants and time cards and then has
 * the server create an invoice.
 */
public class InvoiceClient {


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
    public InvoiceClient(String host, int port, List<TimeCard> timeCardList) throws UnknownHostException {

        this.host = host;
        this.port = port;
        this.timeCardList = timeCardList;

        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address);
    }


    /**
     * Send the command to the server to create invoices for the specified month.
     *
     * @param out
     * @param month
     * @param year
     */
    public void createInvoices(ObjectOutputStream out, Month month, int year) {

    }


    /**
     * Runs this InvoiceClient, sending clients, consultants, and time cards to the server,
     * then sending the command to create invoices for a specified month.
     *
     */
    public void run() {
        InetAddress inetAddress = null;
        Socket socket = null;

        try {
            //inetAddress = InetAddress.getLocalHost();
            socket = new Socket(host, port);
            //socket = new Socket(inetAddress, 10888);
            System.out.println("InvoiceClient: " + socket.getInetAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //CommandProcessor commandProcessor = new CommandProcessor(socket, accounts, consultants, server);
/*        AddClientCommand addClientCommand;

        for(ClientAccount clientAccount : accounts) {
            System.out.println(clientAccount.getName());
            addClientCommand = new AddClientCommand(clientAccount);
            addClientCommand.execute();
        }*/
        //invoiceClient.sendClients();
    }


    /**
     * Send some new clients to the server.
     *
     * @param out
     */
    public void sendClients(ObjectOutputStream out) {

    }


    /**
     * Send some new consultants to the server.
     *
     * @param out
     */
    public void sendConsultants(ObjectOutputStream out) {

    }


    /**
     * Send the disconnect command to the server.
     *
     * @param out
     * @param server
     */
    public void sendDisconnect(ObjectOutputStream out, Socket server) {

    }


    /**
     * Send the shutdown command to the server, this is done on a separate connection to the server.
     *
     * @param host
     * @param port
     */
    public static void	sendShutdown(String host, int port) {

    }


    /**
     * Send the time cards to the server.
     *
     * @param out
     */
    public void sendTimeCards(ObjectOutputStream out) {

    }
}
