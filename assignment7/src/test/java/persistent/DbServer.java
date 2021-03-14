package persistent;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.Invoice;
import edu.uw.cp520.scg.domain.TimeCard;

import java.time.Month;
import java.util.List;

public class DbServer {

    List<ClientAccount> clientAccountList;
    List<Consultant> consultantList;


    public void addClient(ClientAccount client) {

    }



    public void addConsultant(Consultant consultant) {

    }



    public void addTimeCard(TimeCard timecard) {

    }



    public List<ClientAccount> getClients() {

        return clientAccountList;
    }



    public List<Consultant> getConsultants() {

        return consultantList;
    }


//Query
    public Invoice getInvoice(ClientAccount client, int month, int year) {

        return new Invoice(client, Month.of(month), year);
    }




}
