package zendeskigorlibrary.view;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import floowuk.zendeskigorlibrary.screens.ListOfTickets;
import floowuk.zendeskigorlibrary.screens.viewadapt.ZendeskListViewAdapter;
import flowigor.ie.floowuk.BuildConfig;
import flowigor.ie.floowuk.R;
import zendeskigorlibrary.model.TicketsResultsTest;

import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.android.controller.ActivityController;

import static org.robolectric.Shadows.shadowOf;

/*** Created by igorfrankiv on 04/05/2018.*/

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ListOfTicketsUITest {

    private TicketsResultsTest mTicketsResultsTest;
    ShadowActivity myActivityShadow;
    RecyclerView currentRecyclerView;
    ActivityController<ListOfTickets> activityController;
    private ZendeskListViewAdapter mZendeskListViewAdapter;


    @Before
    public void setUp() throws Exception {
        mTicketsResultsTest = new TicketsResultsTest();
        activityController = Robolectric.buildActivity( ListOfTickets.class);
        activityController.create().start().visible();

        myActivityShadow = shadowOf(activityController.get());

        mZendeskListViewAdapter = new ZendeskListViewAdapter(mTicketsResultsTest.getTicketsResults(), RuntimeEnvironment.application);

        currentRecyclerView = ((RecyclerView) myActivityShadow.findViewById(R.id.recycler_view));
        currentRecyclerView.setAdapter(mZendeskListViewAdapter);
    }

    @Test
    public void test()throws Exception{

    }
}


