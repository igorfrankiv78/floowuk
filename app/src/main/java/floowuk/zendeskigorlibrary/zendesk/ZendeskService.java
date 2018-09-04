package floowuk.zendeskigorlibrary.zendesk;

import floowuk.zendeskigorlibrary.model.TicketsResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
/*** Created by igorfrankiv on 27/04/2018. */

public interface ZendeskService {

    @GET
    Call<TicketsResults> getTickets(@Url String url);
}
