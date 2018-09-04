package flowigor.ie.floowuk.zendesk.retrofitzendesk;

import retrofit2.Call;
import android.util.Log;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.mock.Calls;
import retrofit2.mock.BehaviorDelegate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import floowuk.zendeskigorlibrary.constants.UserParam;
import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;

public class MockFailedZendeskService implements ZendeskService
{
    private static final String TAG = "MockFailedQOD";
    private final BehaviorDelegate<ZendeskService> delegate;

    public MockFailedZendeskService(BehaviorDelegate<ZendeskService> restServiceBehaviorDelegate) {
        this.delegate = restServiceBehaviorDelegate;
    }

    @Override
    public Call<TicketsResults> getTickets(String url) {
        Error error = new Error();
        error.setCode(404);
        error.setMessage("Data Not Found");
        ZendeskServiceErrorResponse zendeskServiceErrorResponse = new ZendeskServiceErrorResponse();
        zendeskServiceErrorResponse.setError(error);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(zendeskServiceErrorResponse);
            Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/json") ,json));
            return delegate.returning(Calls.response(response)).getTickets(UserParam.URL);// return delegate.returningResponse(response).getTickets(UserParam.URL);
        } catch (JsonProcessingException e) {
            Log.e(TAG, "JSON Processing exception:",e);
            return Calls.failure(e);
        }
    }

}
