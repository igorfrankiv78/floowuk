package flowigor.ie.floowuk.zendesk.retrofitzendesk;

import java.net.URLConnection;
import java.util.List;

import floowuk.zendeskigorlibrary.model.Tickets;
import flowigor.ie.floowuk.zendesk.util.NetworkUtil;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;
import floowuk.zendeskigorlibrary.constants.UserParam;
import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;
import flowigor.ie.floowuk.zendesk.model.TicketsResultsTest;

public class MockZendeskService implements ZendeskService
{
    private final BehaviorDelegate<ZendeskService> delegate;

    public MockZendeskService(BehaviorDelegate<ZendeskService> service) {
    this.delegate = service;
}

    @Override
    public Call<TicketsResults> getTickets(String url) {
//        TicketsResults lTicketsResults = new TicketsResults( networkTest() );
        TicketsResults lTicketsResults = new TicketsResults( new TicketsResultsTest().getTickets() );
        return delegate.returningResponse(lTicketsResults).getTickets( UserParam.URL );
    }

    public List<Tickets> networkTest() {
        URLConnection urlConnection = NetworkUtil.sendGetRequest( UserParam.URL, NetworkUtil.getAuthStr(UserParam.USERNAME, UserParam.PASSWORD) );

        String jsonData = NetworkUtil.readMultipleLinesRespone( urlConnection );

        return NetworkUtil.readJSONFile( jsonData );
    }

}
