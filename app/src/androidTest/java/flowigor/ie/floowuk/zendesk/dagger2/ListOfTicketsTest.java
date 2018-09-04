package flowigor.ie.floowuk.zendesk.dagger2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import javax.inject.Inject;
import org.junit.runner.RunWith;
import floowuk.floowrx.app.RxMvpApp;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import floowuk.zendeskigorlibrary.screens.ListOfTickets;
import floowuk.zendeskigorlibrary.screens.mvp.IListView;
import floowuk.zendeskigorlibrary.screens.mvp.ListPresenter;

/*** Created by igorfrankiv on 03/05/2018.*/

@RunWith(AndroidJUnit4.class)
public class ListOfTicketsTest {

    @Inject
    ListPresenter mListPresenter;

    private IListView mIListView = Mockito.mock(IListView.class);

    @Rule
    public ActivityTestRule<ListOfTickets> activityTestRule =  new ActivityTestRule<>(ListOfTickets.class);

    @Before
    public void setUp() {

        DaggerITestListOfTicketsComponent.builder()
                .iRxMvpAppComponent(RxMvpApp.get(activityTestRule.getActivity()).component())
                .listOfTicketsModule(new TestListOfTicketsModule  (mIListView) )
                .build().inject(this);
    }

    @Test
    public void testListOfTicketsResultsTest() {
        mListPresenter.onCreate();
        mListPresenter.unsubscribe();
    }
}
