package flowigor.ie.floowuk.screen.list.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import floowuk.floowrx.model.UserLocationsDB;
import floowuk.floowrx.screens.listofjourneys.viewadapt.ListOfJourneysAdapter;
import flowigor.ie.floowuk.BuildConfig;
//import org.robolectric.Shadows;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;
import org.robolectric.shadows.ShadowLog;
//import org.robolectric.shadows.ShadowListView;
//import org.robolectric.shadows.ShadowLog;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
import flowigor.ie.floowuk.R;
import flowigor.ie.floowuk.screen.list.model.ListPresenterModelTest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/*** Created by igorfrankiv on 16/04/2018.*/

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ListOfJourneysUITest {

    private ListOfJourneys mListOfJourneys;
    private ListPresenterModelTest mListPresenterModelTest;
    private ListOfJourneysAdapter listOfJourneysAdapter;
    private RecyclerView recyclerView;

    ActivityController<ListOfJourneys> activityController;
    ShadowActivity myActivityShadow;
    RecyclerView currentRecyclerView;

    @Before
    public void setUp() throws Exception {

//        mListOfJourneys = Robolectric.setupActivity( ListOfJourneys.class );
        mListPresenterModelTest = new ListPresenterModelTest();
//
//        assertNotNull("Mainactivity not intsantiated",mListOfJourneys);
//        recyclerView = (RecyclerView)mListOfJourneys.findViewById(R.id.recycler_view);//getting the list layout xml
//        ShadowLog.stream = System.out; //This is for printing log messages in console

        activityController = Robolectric.buildActivity( ListOfJourneys.class );
        activityController.create().start().visible();

        myActivityShadow = shadowOf(activityController.get());
        listOfJourneysAdapter = new ListOfJourneysAdapter( RuntimeEnvironment.application );

        currentRecyclerView = ((RecyclerView) myActivityShadow.findViewById(R.id.recycler_view));
        currentRecyclerView.setAdapter(listOfJourneysAdapter);
        listOfJourneysAdapter.setdata( mListPresenterModelTest.getUserLocationsDB() );

    }

    @Test
    public void test()throws Exception{
//        activityController.provideAllJourneys( mListPresenterModelTest.getUserLocationsDB());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        currentRecyclerView.getChildAt(0).performClick();
    }

    @Test
    public void shouldFindListView()throws Exception{

//        ShadowListView shadowListView = Shadows.shadowOf(recyclerView);
//        shadowListView.populateItems();

//        assertNotNull("ListView not found ", recyclerView);
//        ShadowListView shadowListView = Shadows.shadowOf(recyclerView); //we need to shadow the list view
////
//        shadowListView.populateItems();// will populate the adapter
////        ShadowLog.d("Checking the first country name in adapter " ,
////                ((UserLocationsDB)recyclerView.getAdapter().getItemId(0)
//////                        getAdapter().getItemId(0)).getCountry()
////        );
////
////        assertTrue("Country Japan doesnt exist", "Japan".equals(((recyclerView) lstView.getAdapter().getItem(0)).getCountry()));
////        assertTrue(3==lstView.getChildCount());
    }

    @Test
    public void testDefaultUIElementsOfDetailedJourney() throws Exception {
//        <ImageView android:id="@+id/imageLogo"
//        android:layout_width="match_parent"
//        android:layout_height="@dimen/60dp"
//        android:scaleType="fitXY"
//        android:src="@drawable/harbour_bridge"
//        tools:layout_editor_absoluteX="@dimen/image_logo_absolute_x"
//        tools:layout_editor_absoluteY="@dimen/image_logo_absolute_y"
//                />
       ImageView imageLogo = (ImageView) mListOfJourneys.findViewById(R.id.imageLogo);
        int leftMarginimageLogo = ((LinearLayout.LayoutParams) imageLogo.getLayoutParams()).height;
        assertEquals(60, leftMarginimageLogo);
        //--------------------------------------------------------------------------------------------------
        TextView textTitle1 = (TextView) mListOfJourneys.findViewById(R.id.textTitle1);
//        System.out.println(" text1 = "+textTitle1.getText().toString()    );
//        assertThat( textTitle1.getText().toString(), equalTo("tit l") );
        //--------------------------------------------------------------------------------------------------
        mListOfJourneys.provideAllJourneys( mListPresenterModelTest.getUserLocationsDB());

//        TextView
//        android:id="@+id/textTitle1"
//        android:layout_width="170dp"
//        android:layout_height="wrap_content"
//        android:layout_marginBottom="@dimen/8dp"
//        android:layout_marginLeft="@dimen/8dp"
//        android:layout_marginTop="@dimen/8dp"
//        android:text="tit l"
        //--------------------------------------------------------------------------------------------------
        // System.out.println(" text2 = "+textTitle1.getText().toString()    );
        int leftMarginTvDuration = ((LinearLayout.LayoutParams) textTitle1.getLayoutParams()).width;
        assertEquals(170, leftMarginTvDuration);
        //--------------------------------------------------------------------------------------------------
//        TextView
//        android:id="@+id/textDesc1"
//        android:layout_width="170dp"
//        android:layout_height="wrap_content"
//        android:layout_marginBottom="@dimen/id_textv_margin_bottom"
//        android:layout_marginLeft="@dimen/id_textv_margin_left"
//        android:layout_marginTop="@dimen/id_textv_margin_top"
//        android:text="desc 1"
//
//        TextView
//        android:id="@+id/textTitle2"
//        android:layout_width="170dp"
//        android:layout_height="wrap_content"
//        android:layout_marginBottom="@dimen/id_textv_margin_bottom"
//        android:layout_marginLeft="@dimen/id_textv_margin_left"
//        android:layout_marginTop="@dimen/id_textv_margin_top"
//        android:text="tit 2"
//        android:textAlignment="center"/>
//
//        TextView
//        android:id="@+id/textDesc2"
//        android:layout_width="170dp"
//        android:layout_height="wrap_content"
//        android:layout_marginBottom="@dimen/id_textv_margin_bottom"
//        android:layout_marginLeft="@dimen/id_textv_margin_left"
//        android:layout_marginTop="@dimen/id_textv_margin_top"
//        android:text="desc 2"
//        android:textAlignment="center"/>
    }
}
