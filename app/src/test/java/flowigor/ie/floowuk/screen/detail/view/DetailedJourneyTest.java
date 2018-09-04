package flowigor.ie.floowuk.screen.detail.view;

/*** Created by igorfrankiv on 16/03/2018. */
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import floowuk.floowrx.model.UserLocation;
import floowuk.floowrx.model.UserLocations;
import floowuk.floowrx.screens.detail.DetailedJourney;
import floowuk.floowrx.utils.TimeUtil;
import flowigor.ie.floowuk.BuildConfig;
import flowigor.ie.floowuk.R;
import flowigor.ie.floowuk.screen.detail.model.DetailPresenterModelTest;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DetailedJourneyTest
{
    /***** ***** ***** ***** ***** ***** ***** *****
     *
     *
     * The GOUGLE MAP IS TESTED IN Android Test
     *
     *
     ***** ***** ***** ***** ***** ***** ***** *****/

   private DetailedJourney mDetailedJourney;
   private DetailPresenterModelTest mDetailPresenterModelTest;

   @Before
    public void setUp() throws Exception {
          mDetailedJourney          = Robolectric.setupActivity(DetailedJourney.class);
          mDetailPresenterModelTest = new DetailPresenterModelTest();
    }

    @Test
    public void testDefaultUIElementsOfDetailedJourney() throws Exception {
//--------------------------------------------------------------------------------------------------
        assertThat( mDetailedJourney.btnDeleteUserJourney.getText().toString(), equalTo("Delete this journey") );
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
        ImageView imgIcClock = (ImageView) mDetailedJourney.findViewById(R.id.imgIcClock);

        int topMarginImgIcClock = ((LinearLayout.LayoutParams) imgIcClock.getLayoutParams()).topMargin;
        assertEquals(5, topMarginImgIcClock);

        int leftMarginImgIcClock = ((LinearLayout.LayoutParams) imgIcClock.getLayoutParams()).leftMargin;
        assertEquals(20, leftMarginImgIcClock);

        int layoutWidthImgIcClock = ((LinearLayout.LayoutParams) imgIcClock.getLayoutParams()).width;
        assertEquals(40, layoutWidthImgIcClock);

        int layoutHeightImgIcClock = ((LinearLayout.LayoutParams) imgIcClock.getLayoutParams()).height;
        assertEquals(40, layoutHeightImgIcClock);
//        int paddingImgIcClock = ((LinearLayout.LayoutParams) imgIcClock.getLayoutParams()).padding;
//        assertEquals(5, paddingImgIcClock);
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
        assertThat( mDetailedJourney.tvDuration.getText().toString(), equalTo("0 min") );

        TextView tvDuration = (TextView) mDetailedJourney.findViewById(R.id.tvDuration);

        int leftMarginTvDuration = ((LinearLayout.LayoutParams) tvDuration.getLayoutParams()).leftMargin;
        assertEquals(5, leftMarginTvDuration);
//--------------------------------------------------------------------------------------------------
        assertThat( mDetailedJourney.tvStartAndFinishTimes.getText().toString(),
                equalTo("0") );

        TextView tvStartAndFinishTimes = (TextView) mDetailedJourney.findViewById(R.id.tvStartAndFinishTimes);

        int leftMargintvStartAndFinishTimes = ((LinearLayout.LayoutParams) tvStartAndFinishTimes.getLayoutParams()).leftMargin;
        assertEquals(5, leftMargintvStartAndFinishTimes);
//--------------------------------------------------------------------------------------------------
    }

    @Test
    public void testDataOnUIElementsOfDetailedJourney() throws Exception {
        mDetailedJourney.getDetailedJourney( mDetailPresenterModelTest.getUserLocations() );
//--------------------------------------------------------------------------------------------------
        UserLocations userLocations = mDetailPresenterModelTest.getUserLocations();
        UserLocation userLocation = userLocations.getListOfUserLocations().get(userLocations.getListOfUserLocations().size() - 1);
        String dateStart = userLocations.getListOfUserLocations().get(0).getTime();
        String dateStop = userLocation.getTime();

        String tvDuration = TimeUtil.durationOfJourney ( dateStart,  dateStop);
        System.out.println(" tvDuration util = "+ tvDuration );
        System.out.println(" tvDuration ui = "+ mDetailedJourney.tvDuration.getText().toString() );

        assertThat( mDetailedJourney.tvDuration.getText().toString(), equalTo(tvDuration) );
//--------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------
        System.out.println(DetailedJourney.STARTED+dateStart+DetailedJourney.FINISHED+ dateStop  );
        System.out.println(" tvStartAndFinishTimes = "+ mDetailedJourney.tvStartAndFinishTimes.getText().toString()  );

        assertThat( mDetailedJourney.tvStartAndFinishTimes.getText().toString(), equalTo(DetailedJourney.STARTED+dateStart+DetailedJourney.FINISHED+dateStop) );
//--------------------------------------------------------------------------------------------------
    }

}