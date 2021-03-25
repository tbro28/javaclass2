package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;
import edu.uw.cp520.scg.net.client.InvoiceClient.InvoiceClient;
import edu.uw.cp520.scg.net.cmd.AddClientCommand;
import edu.uw.cp520.scg.net.server.CommandProcessor;
import edu.uw.cp520.scg.net.server.InvoiceServer;
import edu.uw.ext.util.ListFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates and prints an invoice from the data in the database.
 *
 */
public class Assignment08Server {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment08Server.class);
    /**
     * Start of the driver to read data from the DB and print it to the screen.
     *
     * @param args
     */
    public static void main(String args[]) {



        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);

        // Print them
        //ListFactory.printTimeCards(timeCards);

        
        



        //ObjectOutputStream

        //(int port, List<ClientAccount> clientList,
        //                   List<Consultant> consultantList, String outputDirectoryName)
        InvoiceServer server = new InvoiceServer(10888, accounts, consultants, "./");

        server.run();


/*        //InvoiceClient(String host, int port, List<TimeCard> timeCardList)
        try {
            InvoiceClient invoiceClient = new InvoiceClient("127.0.0.1", 10888, timeCards);




            //invoiceClient.


            InetAddress inetAddress = null;
            Socket socket = null;

            try {
                inetAddress = InetAddress.getLocalHost();
                socket = new Socket();// ("127.0.0.1", 10888);
                //socket = new Socket(inetAddress, 10888);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            CommandProcessor commandProcessor = new CommandProcessor(socket, accounts, consultants, server);


            AddClientCommand addClientCommand;

            for(ClientAccount clientAccount : accounts)
                addClientCommand = new AddClientCommand(clientAccount);

            //invoiceClient.sendClients();



        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/


    }
}
