package zendeskigorlibrary.model;

import java.util.List;
import java.util.ArrayList;
import floowuk.zendeskigorlibrary.model.Tickets;
import floowuk.zendeskigorlibrary.model.TicketsResults;

/*** Created by igorfrankiv on 04/05/2018.*/

public class TicketsResultsTest {

   private List<Tickets> tickets;
   private TicketsResults ticketsResults;

    public TicketsResultsTest(){
        tickets = new ArrayList<>();
        ticketsResults = new TicketsResults(tickets);

        tickets.add( new Tickets (  103,  "99",  "This is test ticket",   "new") );
        tickets.add( new Tickets (  104,  "34",  "This is test ticket",   "open") );
        tickets.add( new Tickets (  105,  "32",  "This is test ticket",   "pending") );
    }

    public List<Tickets> getTickets() {
        return tickets;
    }

    public TicketsResults getTicketsResults() {
        return ticketsResults;
    }
}
