package edu.uw.cp520.scg.net.server;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.net.cmd.Command;

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
    ServerSocket serverSocket;

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
            serverSocket = s;

            while(!s.isClosed()) {
                try (Socket incoming = s.accept()) {
                    serviceConnection(incoming);
                    //????????????????
                    System.out.println("Run method on the server.");
                    //Try with resources does this close:
                    //Might need for multi-threading.
                    incoming.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void serviceConnection(Socket client) throws IOException {

        InputStream inputStream = client.getInputStream();
        //For serialization.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        CommandProcessor commandProcessor = new CommandProcessor(client, clientAccountList, consultantList, this);
        commandProcessor.setOutPutDirectoryName(outputDirectory);

        while(!client.isClosed()) {
            try {
                Object object = objectInputStream.readObject();

                if(object == null) {
                    client.close();
                }
                else if(object instanceof Command<?>) {
                    Command<?> command = (Command<?>)object;
                    command.setReceiver(commandProcessor);
                    command.execute();
                }
                else {
                    System.out.println("Not a valid command.");
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Shuts down the server.
     */
    void shutdown() {
        if(serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
