package flowigor.ie.floowuk.zendesk.retrofitzendesk;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;


import junit.framework.Assert;

import java.lang.annotation.Annotation;

import floowuk.zendeskigorlibrary.constants.UserParam;
import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.zendesk.ZendeskService;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class ZendeskServiceMockAdapterTest extends InstrumentationTestCase {

    private MockRetrofit mockRetrofit;
    private Retrofit retrofit;

@Override
public void setUp() throws Exception {
    super.setUp();
        retrofit = new Retrofit.Builder().baseUrl( UserParam.API_BASE_URL )
                .client(new OkHttpClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
}

    @SmallTest
    public void testRandomTicketsResultsRetrieval() throws Exception {

        BehaviorDelegate<ZendeskService> delegate = mockRetrofit.create(ZendeskService.class);

        ZendeskService mockQodService = new MockZendeskService(delegate);

        //Actual Test
        Call<TicketsResults> quote = mockQodService.getTickets(UserParam.URL);

        Response<TicketsResults> quoteOfTheDayResponse = quote.execute();

        //Asserting response
        Assert.assertTrue(quoteOfTheDayResponse.isSuccessful());

        Assert.assertEquals("This is test ticket1", quoteOfTheDayResponse.body().getResults().get(0).getDescription());

        Log.e("testZendeskServiceHelperTest ",  quoteOfTheDayResponse.body().getResults().get(0).getDescription());
    }

    @SmallTest
    public void testFailedTicketsResultsRetrieval() throws Exception {

        BehaviorDelegate<ZendeskService> delegate = mockRetrofit.create(ZendeskService.class);

        MockFailedZendeskService mockQodService = new MockFailedZendeskService(delegate);

        //Actual Test
        Call<TicketsResults> quote = mockQodService.getTickets(UserParam.URL);

        Response<TicketsResults> quoteOfTheDayResponse = quote.execute();
        //Asserting response
        Assert.assertFalse(quoteOfTheDayResponse.isSuccessful());

        Assert.assertEquals(404, quoteOfTheDayResponse.code());

        Converter<ResponseBody, ZendeskServiceErrorResponse> errorConverter = retrofit.responseBodyConverter(ZendeskServiceErrorResponse.class, new Annotation[0]);

        ZendeskServiceErrorResponse error = errorConverter.convert(quoteOfTheDayResponse.errorBody());

        Assert.assertEquals("Data Not Found", error.getError().getMessage());

    }
}