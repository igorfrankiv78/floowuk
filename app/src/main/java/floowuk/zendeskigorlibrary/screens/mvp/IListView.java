package floowuk.zendeskigorlibrary.screens.mvp;

import android.support.annotation.NonNull;
import floowuk.zendeskigorlibrary.model.TicketsResults;

/*** Created by igor on 03/06/2017.*/
public interface IListView {
    void successInGettingTickets(@NonNull TicketsResults ticketsResults);
    void errorInGettingTickets(String reason);
}
