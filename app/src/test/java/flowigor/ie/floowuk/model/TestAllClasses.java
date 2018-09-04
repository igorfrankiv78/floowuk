package flowigor.ie.floowuk.model;

/*** Created by igorfrankiv on 06/04/2018. */
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

public class TestAllClasses {

    @Test
    public void testClasses() {

        Result result = JUnitCore.runClasses(
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
            );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

            System.out.println(result.wasSuccessful());
    }
}
