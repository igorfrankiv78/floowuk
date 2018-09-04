package floowuk.zendeskigorlibrary.screens.mvp;

import rx.Observable;
import floowuk.zendeskigorlibrary.model.TicketsResults;
/*** Created by igor on 03/06/2017.*/
public interface IListModel {

     Observable<TicketsResults> getAllTheTickets();
}
