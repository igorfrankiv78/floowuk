package flowigor.ie.floowuk.zendesk;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.robotium.solo.Solo;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import floowuk.zendeskigorlibrary.constants.UserParam;
import floowuk.zendeskigorlibrary.screens.ListOfTickets;

/*** Created by igorfrankiv on 07/05/2018. ***/

@RunWith(AndroidJUnit4.class)
public class ListOfTicketsTest extends InstrumentationTestCase {
    
    @Rule
    public ActivityTestRule<ListOfTickets> mActivityRule =  new ActivityTestRule<>(
//                                ListOfTickets.class, true, false);
                               ListOfTickets.class, true, true);

    private Solo solo;
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
           solo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityRule.getActivity());
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        UserParam.API_BASE_URL = server.url("/").toString();
        UserParam.URL =  server.url("/").toString();
    }
 // ------------------------------------------------------------------------
    /*
    MAKE THE APP VISABLE ON THE PHONE, OTHERWISE THE TEST APP BELLOW WILL FAIL!!!
     */
    @Test
    public void testListOfTicketsIsShown() throws Exception {
        String fileName = "tickets_200_ok_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        getInstrumentation().getContext(),
                        fileName)
                )
        );

             Intent intent = new Intent();
             mActivityRule.launchActivity(intent); // getInstrumentation().getContext()

         solo.unlockScreen();
                solo.assertCurrentActivity("Expected ListOfTickets Activity", ListOfTickets.class);

                boolean isTvDistanceText = solo.searchText(("This is a test ticket"));
                assertTrue(("This is a test ticket"), isTvDistanceText);
    }

    @Test
    public void testWhenError() throws Exception {
        String fileName = "tickets_404_not_found.json";

        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

            Intent intent = new Intent();
            mActivityRule.launchActivity(intent);

        solo.unlockScreen();
        solo.assertCurrentActivity("Expected ListOfTickets Activity", ListOfTickets.class);
        boolean error = solo.searchText(( "Error"));
        assertTrue(( "Error"), error);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
//--------------------------------------------------------------------------------------------------------------
}
