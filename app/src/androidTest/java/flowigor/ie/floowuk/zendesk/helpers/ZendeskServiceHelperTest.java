package flowigor.ie.floowuk.zendesk.helpers;

import android.util.Log;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import javax.inject.Inject;
import junit.framework.Assert;
import org.junit.runner.RunWith;
import floowuk.floowrx.app.RxMvpApp;
import floowuk.floowrx.model.MyObservable;
import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import floowuk.zendeskigorlibrary.helpers.ZendeskServiceHelper;
import floowuk.zendeskigorlibrary.model.TicketsResults;
import floowuk.zendeskigorlibrary.screens.ListOfTickets;
import floowuk.zendeskigorlibrary.screens.mvp.IListView;
import floowuk.zendeskigorlibrary.screens.mvp.IOnCompleteGetTickets;
import floowuk.zendeskigorlibrary.screens.mvp.ListPresenter;
import flowigor.ie.floowuk.zendesk.dagger2.DaggerITestListOfTicketsComponent;
import flowigor.ie.floowuk.zendesk.dagger2.TestListOfTicketsModule;
import flowigor.ie.floowuk.zendesk.model.TicketsResultsTest;

/*** Created by igorfrankiv on 09/05/2018. */
@RunWith(AndroidJUnit4.class)
public class ZendeskServiceHelperTest {

    @Inject
    ListPresenter mListPresenter;

    @Inject
    ZendeskServiceHelper mZendeskServiceHelper;

    @Inject
    MyObservable deleteItemLocationsDBObs;

    private IListView mIListView = Mockito.mock(IListView.class);

    private TicketsResultsTest mTicketsResultsTest;

    @Rule
    public ActivityTestRule<ListOfTickets> activityTestRule =  new ActivityTestRule<>(ListOfTickets.class);

    @Before
    public void setUp() {

        mTicketsResultsTest = new TicketsResultsTest();

        DaggerITestListOfTicketsComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(activityTestRule.getActivity()).component())
                .listOfTicketsModule(new TestListOfTicketsModule(mIListView) )
                .build().inject(this);
    }

    @Test
    public void testZendeskServiceHelperSuccess() {

        mZendeskServiceHelper.getAllTheTickets(new IOnCompleteGetTickets() {

            @Override
            public void onErrorGettingTickets(@NonNull String reason) {
            }

            @Override
            public void onGettingTickets(@NonNull TicketsResults ticketsResults) {
                deleteItemLocationsDBObs.add(ticketsResults);

                Assert.assertEquals("This is a test ticket", ticketsResults.getResults().get(0).getDescription());

                Assert.assertEquals(45, ticketsResults.getResults().size() );
            }
        });
    }
}
