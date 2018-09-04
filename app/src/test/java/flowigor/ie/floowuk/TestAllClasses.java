package flowigor.ie.floowuk;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import flowigor.ie.floowuk.model.TestDataForBroadcast;
import flowigor.ie.floowuk.model.TestDeleteItemLocationsDB;
import flowigor.ie.floowuk.model.TestDistanceResult;
import flowigor.ie.floowuk.model.TestJourneyDATA;
import flowigor.ie.floowuk.model.TestLastKnownResults;
import flowigor.ie.floowuk.model.TestLastRecord;
import flowigor.ie.floowuk.model.TestLocationData;
import flowigor.ie.floowuk.model.TestLocationRXData;
import flowigor.ie.floowuk.model.TestMyObservable;
import flowigor.ie.floowuk.model.TestUserLocation;
import flowigor.ie.floowuk.model.TestUserLocations;
import flowigor.ie.floowuk.model.TestUserLocationsDB;
import flowigor.ie.floowuk.screen.detail.mvp.DetailPresenterTest;
import flowigor.ie.floowuk.screen.detail.reactivex.DetailedJourneyObservableSubsTest;
import flowigor.ie.floowuk.screen.home.mvp.HomePresenterTest;
import flowigor.ie.floowuk.screen.home.reactivex.FloowHomeActivityObservableSubsTest;
import flowigor.ie.floowuk.screen.list.mvp.ListPresenterTest;
import flowigor.ie.floowuk.screen.list.reactivex.ListOfJourneysObservableSubsTest;
import flowigor.ie.floowuk.screen.list.view.ListOfJourneysUITest;
import flowigor.ie.floowuk.utils.TestCalculationByDistance;
import flowigor.ie.floowuk.utils.TestFloowUtil;
import flowigor.ie.floowuk.utils.TestJSONUtil;
import flowigor.ie.floowuk.utils.TestSharedPreferencesUtil;
import flowigor.ie.floowuk.utils.TestTimeUtil;

/*** Created by igorfrankiv on 20/04/2018. ***/

public class TestAllClasses {


    @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
// -------------------------------------------------------------------------------------------------
                flowigor.ie.floowuk.screen.detail.dagger2.DetailedJourneyTest.class,
                DetailPresenterTest.class,
                DetailedJourneyObservableSubsTest.class,
                flowigor.ie.floowuk.screen.detail.view.DetailedJourneyTest.class,
// -------------------------------------------------------------------------------------------------
                flowigor.ie.floowuk.screen.home.dagger2.FloowHomeActivityTest.class,
                HomePresenterTest.class,
                FloowHomeActivityObservableSubsTest.class,
                flowigor.ie.floowuk.screen.home.view.FloowHomeActivityTest.class,
// -------------------------------------------------------------------------------------------------
                flowigor.ie.floowuk.screen.list.dagger2. ListOfJourneysTest.class,
                ListPresenterTest.class,
                ListOfJourneysObservableSubsTest.class,
                ListOfJourneysUITest.class,
// -------------------------------------------------------------------------------------------------
                flowigor.ie.floowuk.screen.list.dagger2. ListOfJourneysTest.class,
                ListPresenterTest.class,
                ListOfJourneysObservableSubsTest.class,


//                ListOfJourneysUITest.class, // STILL TO BE TESTED WITH RobolectricTestRunner



// -------------------------------------------------------------------------------------------------
                flowigor.ie.floowuk.services.dagger2.FloowServiceLocatorTest.class,
                flowigor.ie.floowuk.services.mvp.ServicePresenterTest.class,
                flowigor.ie.floowuk.services.reactivex.FloowServiceLocatorObservableSubsTest.class,
// -------------------------------------------------------------------------------------------------
                TestCalculationByDistance.class,
                TestFloowUtil.class,
                TestJSONUtil.class,
                TestSharedPreferencesUtil.class,
                TestTimeUtil.class,
// -------------------------------------------------------------------------------------------------
                TestDataForBroadcast.class,
                TestDeleteItemLocationsDB.class,
                TestDistanceResult.class,
                TestJourneyDATA.class,
                TestLastKnownResults.class,
                TestLastRecord.class,
                TestLocationData.class,
                TestLocationRXData.class,

                TestMyObservable.class,
                TestUserLocation.class,
                TestUserLocations.class,
                TestUserLocationsDB.class
// -------------------------------------------------------------------------------------------------
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}
