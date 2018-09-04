package floowuk.zendeskigorlibrary.screens.mvp;

import android.support.annotation.NonNull;
import floowuk.zendeskigorlibrary.model.TicketsResults;
/*** Created by igorfrankiv on 01/05/2018. ***/

public interface IOnCompleteGetTickets {
    void onErrorGettingTickets(@NonNull String reason);
    void onGettingTickets(@NonNull TicketsResults ticketsResults);
}
