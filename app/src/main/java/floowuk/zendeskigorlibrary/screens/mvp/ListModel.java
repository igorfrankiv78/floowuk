package floowuk.zendeskigorlibrary.screens.mvp;

import rx.Observable;
import android.util.Log;
import android.support.annotation.NonNull;
import floowuk.floowrx.model.MyObservable;
import floowuk.zendeskigorlibrary.helpers.ZendeskServiceHelper;
import floowuk.zendeskigorlibrary.model.TicketsResults;

/*** Created by igor on 03/06/2017.*/

public class ListModel implements IListModel {

   public static final String ERROR_MESSAGE = "No Data!";

    private ZendeskServiceHelper mZendeskServiceHelper;
    private MyObservable<TicketsResults> mDeleteItemLocationsDBObs;

    public ListModel(ZendeskServiceHelper zendeskServiceHelper, MyObservable myObservable){
        mZendeskServiceHelper = zendeskServiceHelper;
        mDeleteItemLocationsDBObs = myObservable;
    }

    public Observable<TicketsResults> getAllTheTickets( ){

        mZendeskServiceHelper.getAllTheTickets(new IOnCompleteGetTickets() {

            @Override
            public void onErrorGettingTickets(@NonNull String reason) {
//                mIListView.errorInGettingTickets(reason);//   throw new Exception();
                mDeleteItemLocationsDBObs.add( null );
            }

            @Override
            public void onGettingTickets(@NonNull TicketsResults ticketsResults) {
                mDeleteItemLocationsDBObs.add( ticketsResults );
            }

        });

        return mDeleteItemLocationsDBObs.getObservable();
    }
}