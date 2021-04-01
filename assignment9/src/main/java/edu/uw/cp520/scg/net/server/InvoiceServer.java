package edu.uw.cp520.scg.net.server;

import app.Assignment09Server;
import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.net.cmd.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

/**
 * The server for creating new clients,
 * consultants and time cards as well as creation of account invoices.
 */
public class InvoiceServer {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InvoiceServer.class);

    List<ClientAccount> clientAccountList;
    List<Consultant> consultantList;
    int port;
    String outputDirectory;
    ServerSocket serverSocket;


    /**
     * Constructor.
     *
     * @param port
     * @param clientList
     * @param consultantList
     * @param outputDirectoryName
     */
    public InvoiceServer(int port, List<ClientAccount> clientList,
                         List<Consultant> consultantList, String outputDirectoryName) {

        this.clientAccountList = clientList;
        this.consultantList = consultantList;
        this.port = port;  //should be port 10888.
        this.outputDirectory = outputDirectoryName;
    }


    /**
     * Run this server,
     * establishing connections,
     * and starts a separate CommandProcessor
     * to receive commands, and execute them.
     */
    public void startServer() {

        //Register shutdown hook (slide 45).
        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new InvoiceServerShutdownHook(
                clientAccountList, consultantList, outputDirectory));

        //Russ
        //Keep track of processor.
        int processorNumber = 0;

        //Book page 237-38.
        //From the run method.
        try (var s = new ServerSocket(port)) {
            serverSocket = s;

            while(!serverSocket.isClosed()) {
                try {
                    Socket incoming = s.accept();
                    processorNumber++;

                    CommandProcessor commandProcessor = new CommandProcessor(incoming, "T_"+processorNumber, clientAccountList, consultantList, this);

                    File serverDirectory = new File(outputDirectory, Integer.toString(processorNumber));
                    if (!serverDirectory.exists()) {
                        if (!serverDirectory.mkdirs()) {
                            log.error("Cannot create directory.");
                            return;
                        }
                    }
                    commandProcessor.setOutPutDirectory(serverDirectory);
                    //Sets the name of the thread using the number.
                    //This will do all of the work.
                    Thread thread = new Thread(commandProcessor, Integer.toString(processorNumber));
                    thread.start();


                    //serviceConnection(incoming);
                    //Move method content to CommandProcessor.


                    //????????????????
                    System.out.println("Run method on the server.");
                    //Try with resources does this close:
                    //Might need for multi-threading.
                    incoming.close();


                } catch (SocketException e) {
                    //Caught in case the accept() above is interrupted and the connection is closed.
                    e.printStackTrace();
                } catch (IOException eio) {
                    if(!serverSocket.isClosed()) {
                        log.error("Connection failed.", eio);
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            log.error("Unable to close socket.", e);
                        }
                    }
                    else
                        log.info("Shutting down.");

                    //eio.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
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
