package floowuk.zendeskigorlibrary.helpers;

import retrofit2.Call;
import android.util.Log;
import retrofit2.Callback;
import retrofit2.Response;
import floowuk.zendeskigorlibrary.constants.UserParam;
import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.screens.mvp.ListModel;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;
import floowuk.zendeskigorlibrary.screens.mvp.IOnCompleteGetTickets;

/*** Created by igorfrankiv on 03/05/2018. ***/

public class ZendeskServiceHelper {

    private ZendeskService mZendeskService;

    public ZendeskServiceHelper (ZendeskService zendeskService){
        mZendeskService = zendeskService;
    }

    public void getAllTheTickets( IOnCompleteGetTickets iOnCompleteGetTickets ){
        mZendeskService.getTickets( UserParam.URL ).enqueue(new Callback<TicketsResults>() {

            @Override
            public void onResponse(Call<TicketsResults> call, Response<TicketsResults> response) {
                if (response.isSuccessful()) {
                    iOnCompleteGetTickets.onGettingTickets(response.body());
                }
                else {
                    iOnCompleteGetTickets.onErrorGettingTickets(ListModel.ERROR_MESSAGE);//response.errorBody();
                }
            }

            @Override
            public void onFailure(Call<TicketsResults> call, Throwable t) {
                    iOnCompleteGetTickets.onErrorGettingTickets(t.getMessage());
            }

        });
    }
}
