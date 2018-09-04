package flowigor.ie.floowuk.screen.home.view;

/*** Created by igorfrankiv on 19/03/2018.*/
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import static org.junit.Assert.assertEquals;
import floowuk.floowrx.screens.home.FloowHomeActivity;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
import flowigor.ie.floowuk.R;
import flowigor.ie.floowuk.BuildConfig;
import flowigor.ie.floowuk.screen.home.model.HomePresenterModelTest0;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class FloowHomeActivityTest {

    private FloowHomeActivity mainActivity;
    private HomePresenterModelTest0 mHomePresenterModelTest0;

    @Before public void setUp() throws Exception {
        mainActivity = Robolectric.setupActivity(FloowHomeActivity.class);
//        mainActivity = Robolectric.buildActivity(FloowHomeActivity.class)
//                         .create()
//                         .resume()
//                         .get();
         mHomePresenterModelTest0 = new HomePresenterModelTest0();
    }

    @Test
    public void testFloowHomeActivityTest() throws Exception {
        Button btnShowJourneysRX = (Button) mainActivity.findViewById( R.id.btnShowJourneysRX );
        assertEquals("Show Journeys", btnShowJourneysRX.getText());
        // ------------------------------------------------------------
        ImageView imgDistance = (ImageView) mainActivity.findViewById( R.id.imgDistance );

        int topMarginImgDistance = ((LinearLayout.LayoutParams) imgDistance.getLayoutParams()).topMargin;
        assertEquals(5, topMarginImgDistance);

        int leftMarginImgDistance = ((LinearLayout.LayoutParams) imgDistance.getLayoutParams()).leftMargin;
        assertEquals(20, leftMarginImgDistance);

        int layoutWidthImgDistance = ((LinearLayout.LayoutParams) imgDistance.getLayoutParams()).width;
        assertEquals(40, layoutWidthImgDistance);

        int layoutHeightImgDistance = ((LinearLayout.LayoutParams) imgDistance.getLayoutParams()).height;
        assertEquals(40, layoutHeightImgDistance);
        // ------------------------------------------------------------
        TextView tvDistance = (TextView) mainActivity.findViewById(R.id.tvDistance);
        assertEquals("0 m", tvDistance.getText());

        int leftMargintvDistance = ((LinearLayout.LayoutParams) tvDistance.getLayoutParams()).leftMargin;
        assertEquals(5, leftMargintvDistance);
        // ------------------------------------------------------------
         ImageView imgClock = (ImageView) mainActivity.findViewById( R.id.imgClock );

         int topMarginimgClock = ((LinearLayout.LayoutParams) imgClock.getLayoutParams()).topMargin;
         assertEquals(5, topMarginimgClock);

         int leftMarginimgClock = ((LinearLayout.LayoutParams) imgClock.getLayoutParams()).leftMargin;
         assertEquals(20, leftMarginimgClock);

         int layoutWidthimgClock = ((LinearLayout.LayoutParams) imgClock.getLayoutParams()).width;
         assertEquals(40, layoutWidthimgClock);

         int layoutHeightimgClock = ((LinearLayout.LayoutParams) imgClock.getLayoutParams()).height;
         assertEquals(40, layoutHeightimgClock);
//                    android:padding="5dp"
        // ------------------------------------------------------------
          TextView tvDuration = (TextView) mainActivity.findViewById(R.id.tvDuration);
          assertEquals("0 min", tvDuration.getText());

          int leftMargintvDuration = ((LinearLayout.LayoutParams) tvDuration.getLayoutParams()).leftMargin;
          assertEquals(5, leftMargintvDuration);
       // ------------------------------------------------------------
        Button buttonstopService = (Button) mainActivity.findViewById( R.id.buttonstopService );
        assertEquals("Stop", buttonstopService.getText());
       // ------------------------------------------------------------
         Button buttonstartService = (Button) mainActivity.findViewById( R.id.buttonstartService );
         assertEquals("Start", buttonstartService.getText());
       // ------------------------------------------------------------              />
    }

    @Test
    public void shouldStartNextActivityWhenButtonIsClicked()
    {
       Button buttonstopService = (Button) mainActivity.findViewById(R.id.buttonstopService);
       Button btnShowJourneysRX= (Button) mainActivity.findViewById(R.id.btnShowJourneysRX);

        mainActivity. mListOfUserLocations = mHomePresenterModelTest0.getListOfUserLocations();

        buttonstopService.performClick();

        btnShowJourneysRX.performClick();

        Intent expectedIntent = new Intent(mainActivity, ListOfJourneys.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());

        System.out.println(" expectedIntent.getComponent() = "+ expectedIntent.getComponent().toString());
        System.out.println(" actual.getComponent() = "        + actual        .getComponent().toString());
    }

     @Test
     public void testDataOnFloowHomeActivityTest() throws Exception {
           // ------------------------------------------------------------
             TextView tvDuration = (TextView) mainActivity.findViewById(R.id.tvDuration);
             tvDuration.setText("1 hour 37 min");
             assertEquals("1 hour 37 min", tvDuration.getText());
          // ------------------------------------------------------------
          // ------------------------------------------------------------
              TextView tvDistance = (TextView) mainActivity.findViewById(R.id.tvDistance);
              tvDistance.setText("132 m");
              assertEquals("132 m", tvDistance.getText());
          // ------------------------------------------------------------
     }
}
