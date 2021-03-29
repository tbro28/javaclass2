package edu.uw.cp520.scg.net.server;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * The server for creating new clients,
 * consultants and time cards as well as creation of account invoices.
 */
public class InvoiceServer {

    List<ClientAccount> clientAccountList;
    List<Consultant> consultantList;
    int port;
    String outputDirectory;




    public InvoiceServer(int port, List<ClientAccount> clientList,
                         List<Consultant> consultantList, String outputDirectoryName) {

        this.clientAccountList = clientList;
        this.consultantList = consultantList;
        this.port = port;  //should be port 10888.
        this.outputDirectory = outputDirectoryName;

    }

    /**
     *     Run this server, establishing connections,
     *     receiving commands, and dispatching them to the CommandProcesser.
     *
     */
    public void run() {

        //Book page 237-38.
        try (var s = new ServerSocket(port)) {
            try(Socket incoming = s.accept()) {


                serviceConnection(incoming);

                //????????????????

                System.out.println("Run method on the server.");



                //Try with resources does this close:
                //Might need for multi-threading.
                incoming.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }




    }



    void serviceConnection(Socket client) throws IOException {

        InputStream inputStream = client.getInputStream();


        //For serialization.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);






    }



    void shutdown() {

    }




}
