package app;

import edu.uw.cp520.scg.domain.ClientAccount;
import edu.uw.cp520.scg.domain.Consultant;
import edu.uw.cp520.scg.domain.TimeCard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Assignment5 {

    public static void main(final String[] args) {

        /**
         * Timecard deserialization.
         */
        Path path = Path.of("src/main/resources/TimeCardList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));

            //deserialize the List
            List<TimeCard> tCardList = (List<TimeCard>) in.readObject();
            //display its data
            for (TimeCard t : tCardList) {
                System.out.println("Recovered TimeCard: " + t);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Client deserialization.
         */
        path = Path.of("src/main/resources/ClientList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));

            //deserialize the List
            List<ClientAccount> clientCardList = (List<ClientAccount>) in.readObject();
            //display its data
            for (ClientAccount t : clientCardList) {
                System.out.println("Recovered ClientAccount: " + t.getName());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         * Consultant deserialization.
         * Log the name of the consultant.
         */

        List<Consultant> consultantList = new ArrayList<>();

        path = Path.of("src/main/resources/ConsultantList.ser");
        try {
            ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path));

            //deserialize the List
            int count = in.readInt();
            int start = 0;


            for(int i = 0; i < count; i++) {
                System.out.println("Recovered Consultant: " + (String) in.readObject());
                consultantList.add((Consultant) in.readObject());
            }

/*
            List<Consultant> conCardList = (List<Consultant>) in.readObject();
            //display its data
            for (Consultant t : conCardList) {
                System.out.println("Recovered Consultant: " + t);
            }
*/

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }







    }


}
