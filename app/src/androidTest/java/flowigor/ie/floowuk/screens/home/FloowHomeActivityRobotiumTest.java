package flowigor.ie.floowuk.screens.home;

/*** Created by igorfrankiv on 18/03/2018.*/

import org.junit.Rule;
import org.junit.Test;
import org.junit.After; 
import org.junit.Before; 
import com.robotium.solo.Solo;
import org.junit.runner.RunWith;
import android.content.Context;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiObjectNotFoundException;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import flowigor.ie.floowuk.screens.home.model.DetailPresenterModelTest;
import flowigor.ie.floowuk.screens.home.model.HomePresenterModelTest0;
import flowigor.ie.floowuk.screens.home.model.HomePresenterModelTest1;
import flowigor.ie.floowuk.screens.home.model.HomePresenterModelTest2;
import floowuk.floowrx.helpers.DBHelper;
import floowuk.floowrx.model.JourneyDATA;
import floowuk.floowrx.screens.detail.DetailedJourney;
import floowuk.floowrx.screens.home.FloowHomeActivity;
import floowuk.floowrx.screens.listofjourneys.ListOfJourneys;
import floowuk.floowrx.utils.JSONUtil;

@RunWith(AndroidJUnit4.class)
public class FloowHomeActivityRobotiumTest {

    HomePresenterModelTest0 mHomePresenterModelTest0;
    HomePresenterModelTest1 mHomePresenterModelTest1;
    HomePresenterModelTest2 mHomePresenterModelTest2;
//--------------------------------------------------------------------------------------------------
    private UiDevice uiDevice = UiDevice.getInstance(getInstrumentation());

    private UiObject mMarker0;
    private UiObject mMarker1;
    private UiObject mMarker2;
    private UiObject mMarker3;
//--------------------------------------------------------------------------------------------------
    private static final String TV_DURATION_DEFAULT_TEXT = "0 min";

    private static final String BTN_SHOW_JOURNEYS = "Show Journeys";

    private static final String BUTTON_START_SERVICE = "Start";

    private static final String TV_DISTANCE_DEFAULT_TEXT = "0 m";

    private static final String BUTTON_STOP_SERVICE = "Stop";

    @Rule
    public ActivityTestRule<FloowHomeActivity> activityTestRule =  new ActivityTestRule<>(FloowHomeActivity.class);

    private Solo solo;

    private Context instrumentationCtx;
    private DBHelper mDBHelper;
//    private static final String TEST_FILE_PREFIX = "test_";

    private DetailPresenterModelTest mDetailPresenterModelTest;

    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), activityTestRule.getActivity());
        mDetailPresenterModelTest = new DetailPresenterModelTest();
        instrumentationCtx = InstrumentationRegistry.getTargetContext();//InstrumentationRegistry.getContext();
//        RenamingDelegatingContext context
//                = new RenamingDelegatingContext(instrumentationCtx, TEST_FILE_PREFIX);
        mHomePresenterModelTest0 = new HomePresenterModelTest0();
        mHomePresenterModelTest1 = new HomePresenterModelTest1();
        mHomePresenterModelTest2 = new HomePresenterModelTest2();

        mDBHelper = new DBHelper(instrumentationCtx);
        //---------------------HOME PAGE----WRITE --------------------------------
// Given
        JourneyDATA journeyDATA0 = mHomePresenterModelTest0.getJourneyDATA();
        String timeStamp0 = mHomePresenterModelTest0.getTimeStamp();
        String jsonStr0 = JSONUtil.createJsonString(journeyDATA0.getmListOfUserLocations(), journeyDATA0.getIsRecorded());

// Given
        JourneyDATA journeyDATA1 = mHomePresenterModelTest1.getJourneyDATA();
        String timeStamp1 = mHomePresenterModelTest1.getTimeStamp();
        String jsonStr1 = JSONUtil.createJsonString(journeyDATA1.getmListOfUserLocations(), journeyDATA1.getIsRecorded());

// Given
        JourneyDATA journeyDATA2 = mHomePresenterModelTest2.getJourneyDATA();
        String timeStamp2 = mHomePresenterModelTest2.getTimeStamp();
        String jsonStr2 = JSONUtil.createJsonString(journeyDATA2.getmListOfUserLocations(), journeyDATA2.getIsRecorded());
        System.out.println(jsonStr2);

// When
        Boolean isFirstRecordInserted = mDBHelper.writeJourneyInDB(jsonStr0, timeStamp0);

        Boolean isSecondRecordInserted = mDBHelper.writeJourneyInDB(jsonStr1, timeStamp1);

        Boolean isThirdRecordInserted = mDBHelper.writeJourneyInDB(jsonStr2, timeStamp2);

// Then
        assertEquals(isFirstRecordInserted, true);
        assertEquals(isSecondRecordInserted, true);
        assertEquals(isThirdRecordInserted, true);
//---------------------HOME PAGE----HOW MANY RECORDS IN DATABASE----------
    }

    @Test
    public void testFloowHomeActivity() throws Exception {
        //Unlock the lock screen
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected FloowHomeActivity Activity", FloowHomeActivity.class);

        boolean isTvDistanceText = solo.searchText(TV_DISTANCE_DEFAULT_TEXT);
        assertTrue(TV_DISTANCE_DEFAULT_TEXT, isTvDistanceText);

        boolean isTvDurationText = solo.searchText(TV_DURATION_DEFAULT_TEXT);
        assertTrue(TV_DURATION_DEFAULT_TEXT, isTvDurationText);

        boolean isBtnShowJourneys = solo.searchText(BTN_SHOW_JOURNEYS);
        assertTrue(BTN_SHOW_JOURNEYS, isBtnShowJourneys);

        boolean isButtonstartService = solo.searchText(BUTTON_START_SERVICE);
        assertTrue(BUTTON_START_SERVICE, isButtonstartService);

        boolean isbuttonstopService = solo.searchText(BUTTON_STOP_SERVICE);
        assertTrue(BUTTON_STOP_SERVICE, isbuttonstopService);

        //Click on Show Journeys
        solo.clickOnView(solo.getView( flowigor.ie.floowuk.R.id.btnShowJourneysRX));// solo.clickOnView(R.id.myBtn)
        //Assert that NoteEditor activity is opened
//        try{
            solo.assertCurrentActivity("Expected ListOfJourneys Activity", ListOfJourneys.class);
//                } catch (junit.framework.ComparisonFailure e){
//            mDBHelper.getDetailedRecord(  1  );
//            mDBHelper.getDetailedRecord(  2  );
//            mDBHelper.getDetailedRecord(  3  );
//        }
//        junit.framework.ComparisonFailure: Expected ListOfJourneys Activity expected:
//        <...wuk.floowrx.screens.[listofjourneys.ListOfJourneys]>
//        but was:<...wuk.floowrx.screens.[home.FloowHomeActivity]>
//--------------------------------------------------------------------------------------------------
        /******************************************
        Check if all the records were displayed from the data base!
         ******************************************/
        boolean firstCellId = solo.searchText("1");
        assertTrue("1", firstCellId);

        boolean firstTimeStamp = solo.searchText(mHomePresenterModelTest0.getTimeStamp());
        assertTrue(mHomePresenterModelTest0.getTimeStamp(), firstTimeStamp);

        boolean secondCellId = solo.searchText("2");
        assertTrue("2", secondCellId);
        // searchForText has a timeout of 5 seconds
        boolean secondTimeStamp = solo.searchText(mHomePresenterModelTest1.getTimeStamp());
        assertTrue(mHomePresenterModelTest1.getTimeStamp(), secondTimeStamp);

        boolean thirdCellId = solo.searchText("3");
        assertTrue("3", thirdCellId);

        boolean thirdTimeStamp = solo.searchText(mHomePresenterModelTest2.getTimeStamp());
        assertTrue(mHomePresenterModelTest2.getTimeStamp(), thirdTimeStamp);
//--------------------------------------------------------------------------------------------------
        testFirstRecordInDetailedJourneyActivity();
        solo.clickOnView(solo.getView( flowigor.ie.floowuk.R.id.btnDeletePath));

//--------------------------------------------------------------------------------------------------
        testSecondRecordInDetailedJourneyActivity();
        solo.clickOnView(solo.getView( flowigor.ie.floowuk.R.id.btnDeletePath));

//--------------------------------------------------------------------------------------------------
        testThirdRecordInDetailedJourneyActivity();
        solo.clickOnView(solo.getView( flowigor.ie.floowuk.R.id.btnDeletePath));

//--------------------------------------------------------------------------------------------------
            /******************************************
            Check if all the records were deleted from the data base!
             ******************************************/
            boolean firstCellIdDeleted = solo.searchText("1");
            assertFalse("1", firstCellIdDeleted);

            boolean firstTimeStampDeleted = solo.searchText(mHomePresenterModelTest0.getTimeStamp());
            assertFalse(mHomePresenterModelTest0.getTimeStamp(), firstTimeStampDeleted);

            boolean secondCellIdDeleted = solo.searchText("2");
            assertFalse("2", secondCellIdDeleted);

            boolean secondTimeStampDeleted = solo.searchText(mHomePresenterModelTest1.getTimeStamp());
            assertFalse(mHomePresenterModelTest1.getTimeStamp(), secondTimeStampDeleted);

            boolean thirdCellIdDeleted = solo.searchText("3");
            assertFalse("3", thirdCellIdDeleted);

            boolean thirdTimeStampDeleted = solo.searchText(mHomePresenterModelTest2.getTimeStamp());
            assertFalse(mHomePresenterModelTest2.getTimeStamp(), thirdTimeStampDeleted);
//--------------------------------------------------------------------------------------------------
        solo.goBack();// solo.goBackToActivity("FloowHomeActivity");
        solo.assertCurrentActivity("Expected FloowHomeActivity Activity", FloowHomeActivity.class);
//--------------------------------------------------------------------------------------------------
//        solo.takeScreenshot();
////        deleteNotes();
//        } catch (junit.framework.ComparisonFailure e){
//            mDBHelper.getDetailedRecord(  1  );
//            mDBHelper.getDetailedRecord(  2  );
//            mDBHelper.getDetailedRecord(  3  );
//        }
    }
    
    public void testFirstRecordInDetailedJourneyActivity(){

           solo.clickInRecyclerView(0);

           solo.assertCurrentActivity("Expected DetailedJourney Activity", DetailedJourney.class);

           mMarker0 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest0.getListOfUserLocations().get(0).getTime()));

           mMarker1 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest0.getListOfUserLocations().get(1).getTime()));

           mMarker2 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest0.getListOfUserLocations().get(2).getTime()));

           try {
               mMarker0.click();
               mMarker1.click();
               mMarker2.click();

           } catch (UiObjectNotFoundException e) {
               e.printStackTrace();
           }
       }
       
    public void testSecondRecordInDetailedJourneyActivity(){
           solo.clickInRecyclerView(0);
           solo.assertCurrentActivity("Expected DetailedJourney Activity", DetailedJourney.class);
           mMarker0 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest1.getListOfUserLocations().get(0).getTime()));

           mMarker1 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest1.getListOfUserLocations().get(1).getTime()));

           mMarker2 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest1.getListOfUserLocations().get(2).getTime()));

           mMarker3 = uiDevice.findObject(new UiSelector().
                   descriptionContains(mHomePresenterModelTest1.getListOfUserLocations().get(3).getTime()));

           try {
               mMarker0.click();
               mMarker1.click();
               mMarker2.click();
               mMarker3.click();

           } catch (UiObjectNotFoundException e) {
               e.printStackTrace();
           }
    }

    public void testThirdRecordInDetailedJourneyActivity(){
        solo.clickInRecyclerView(0);
        solo.assertCurrentActivity("Expected DetailedJourney Activity", DetailedJourney.class);
        mMarker0 = uiDevice.findObject(new UiSelector().
                descriptionContains(mHomePresenterModelTest2.getListOfUserLocations().get(0).getTime()));

        mMarker1 = uiDevice.findObject(new UiSelector().
                descriptionContains(mHomePresenterModelTest2.getListOfUserLocations().get(1).getTime()));

        mMarker2 = uiDevice.findObject(new UiSelector().
                descriptionContains(mHomePresenterModelTest2.getListOfUserLocations().get(2).getTime()));

        mMarker3 = uiDevice.findObject(new UiSelector().
                descriptionContains(mHomePresenterModelTest2.getListOfUserLocations().get(3).getTime()));

        try {
            mMarker0.click();
            mMarker1.click();
            mMarker2.click();
            mMarker3.click();

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}

