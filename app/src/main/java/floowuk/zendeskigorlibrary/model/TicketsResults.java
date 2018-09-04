package floowuk.zendeskigorlibrary.model;

import java.util.List;
import android.support.annotation.NonNull;

/*** Created by igor on 03/06/2017. */
public final class TicketsResults {

    private final List<Tickets> tickets;

    public TicketsResults (@NonNull List<Tickets> tickets)
    {
       this.tickets = tickets;
    }

    public List<Tickets> getResults() {
        return tickets;
    }
}
