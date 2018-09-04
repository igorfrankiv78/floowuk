package flowigor.ie.floowuk.helpers;

    import java.util.List;
    import org.junit.Test;
    import org.junit.After;
    import org.junit.Before;
    import org.junit.runner.RunWith;
    import floowuk.floowrx.utils.JSONUtil;
    import flowigor.ie.floowuk.BuildConfig;
    import org.robolectric.RuntimeEnvironment;
    import org.robolectric.annotation.Config;
    import floowuk.floowrx.helpers.DBHelper;
    import floowuk.floowrx.model.JourneyDATA;
    import floowuk.floowrx.model.UserLocations;
    import org.robolectric.RobolectricTestRunner;
    import floowuk.floowrx.model.UserLocationsDB;
    import flowigor.ie.floowuk.screen.home.model.HomePresenterModelTest0;
    import flowigor.ie.floowuk.screen.home.model.HomePresenterModelTest1;
    import flowigor.ie.floowuk.screen.home.model.HomePresenterModelTest2;
    import static org.junit.Assert.assertEquals;
/*** Created by igorfrankiv on 03/04/2018.*/

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class DBHelperTest {

    private DBHelper mDBHelper;
    HomePresenterModelTest0 mHomePresenterModelTest0;
    HomePresenterModelTest1 mHomePresenterModelTest1;
    HomePresenterModelTest2 mHomePresenterModelTest2;

    @Before
    public void setUp() {
        mDBHelper = new DBHelper(RuntimeEnvironment.application);
        mHomePresenterModelTest0 = new HomePresenterModelTest0();
        mHomePresenterModelTest1 = new HomePresenterModelTest1();
        mHomePresenterModelTest2 = new HomePresenterModelTest2();
    }

    @Test
    public void testDBHelper() {
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
// Given
        Integer numOfRecordsExpected = 3;
// When
        Integer numOfRecords = mDBHelper.getAllOfTheJourneys();
// Then
        assertEquals(numOfRecords, numOfRecordsExpected);
//---------------------LIST PAGE----SHOW ALL RECORDS FROM DATABASE--------
// When
        mDBHelper.getAllJourneys().subscribe(this::testListPageShowAllRecordsFromDB);
//---------------------DETAILED PAGE----GET A RECORD FROM DATABASE--------
// Given
        Integer id = 3;
// When
        mDBHelper.getDetailedRecord(  id  ).subscribe(this::testDetailedPageGetUserLocationsDB);
//---------------------DETAILED PAGE----DELETE A RECORD FROM DATABASE--------
// When
        mDBHelper.deleteUserJourney( id ).subscribe(this::testDetailedPageDeleteUserLocationsDB);// DetailModel.SUCCESS_MESSAGE
    }

    public void testDetailedPageGetUserLocationsDB(UserLocations userLocations) {
// Given
        double longitude = -6.276708;
        double latitude = 53.3518131;
        double distance = 0.0;
// Then
        assertEquals(userLocations.getListOfUserLocations().get(0).getLongitude(), longitude,0.0 );
        assertEquals(userLocations.getListOfUserLocations().get(0).getLatitude(), latitude, 0.0 );
        assertEquals(userLocations.getListOfUserLocations().get(0).getTime(), "03/21/2018 22:50:43");
        assertEquals(userLocations.getListOfUserLocations().get(0).getDistance(), distance, 0.0);
    }

    public void testDetailedPageDeleteUserLocationsDB(Integer num){
// Given
        Integer numExpected = 1;
// Then
        assertEquals( num, numExpected );
    }

    public void testListPageShowAllRecordsFromDB(List<UserLocationsDB> listofUserLocationsDB){
// Given
        int numExpected = 3;
// Then
        if(listofUserLocationsDB.size() > 2) {
            assertEquals(listofUserLocationsDB.size(), numExpected);
            assertEquals(listofUserLocationsDB.get(0).getTimeStamp(), "18 Mar 2018 10:43:05 PM");
            assertEquals(listofUserLocationsDB.get(1).getTimeStamp(), "18 Mar 2018 10:47:06 PM");
            assertEquals(listofUserLocationsDB.get(2).getTimeStamp(), "21 Mar 2018 10:51:30 PM");
        }
    }

    @After
    public void  tearDown() {
//        dbHelper.clearDb() // A function just to clear the DB
    }
}